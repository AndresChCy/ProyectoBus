package Vistas;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class BotonRetroceder extends JButton {
    private BufferedImage imagenOriginal;
    private BufferedImage imagenRedimensionada;
    private Timer timer;
    private final int ANCHO_INICIAL;
    private final int ALTO_INICIAL;
    private int ANCHO_MINIMO;
    private int ANCHO_MAXIMO;

    public BotonRetroceder(PanelPrincipal panelPrincipal) {
        super();

        // Cargar la imagen original
        try {
            imagenOriginal = ImageIO.read(getClass().getResource("/retroceso.png"));
            ANCHO_INICIAL = imagenOriginal.getWidth();
            ALTO_INICIAL = imagenOriginal.getHeight();
            imagenRedimensionada = resizeImage(imagenOriginal, ANCHO_INICIAL, ALTO_INICIAL); // Redimensionar la imagen inicialmente
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        setOpaque(false);
        setBorderPainted(false);
        setContentAreaFilled(false);
        addActionListener(e -> panelPrincipal.mostrarPanelSelectorRuta());

        // Agregar un listener para escalar la imagen cuando el botón tenga tamaño válido
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                ajustarTamanos();
                redimensionarIcono();
            }
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                iniciarAnimacion();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                detenerAnimacion();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Dibujar la imagen escalada
        if (imagenRedimensionada != null) {
            int x = (getWidth() - imagenRedimensionada.getWidth()) / 2;
            int y = (getHeight() - imagenRedimensionada.getHeight()) / 2;
            g.drawImage(imagenRedimensionada, x, y, this);
        }
    }

    private void ajustarTamanos() {
        int anchoBoton = getWidth();
        int altoBoton = getHeight();
        if (anchoBoton > 0 && altoBoton > 0) {
            ANCHO_MINIMO = anchoBoton / 2;
            ANCHO_MAXIMO = anchoBoton;
        }
    }

    private void redimensionarIcono() {
        if (imagenOriginal != null) {
            int anchoBoton = getWidth();
            int altoBoton = getHeight();
            if (anchoBoton > 0 && altoBoton > 0) {
                // Escalar la imagen al tamaño del botón
                imagenRedimensionada = resizeImage(imagenOriginal, anchoBoton, altoBoton);
                repaint();  // Volver a pintar para actualizar el cambio
            }
        }
    }

    private BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) {
        if (targetWidth <= 0 || targetHeight <= 0) {
            return originalImage;
        }
        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = resizedImage.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
        g.dispose();
        return resizedImage;
    }

    private void iniciarAnimacion() {
        // Detener cualquier animación previa si existe
        detenerAnimacion();

        // Crear un nuevo Timer para la animación
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            boolean achicando = false; // Variable para controlar el estado de la animación
            long startTime = System.currentTimeMillis(); // Tiempo de inicio de la animación

            @Override
            public void run() {
                long tiempoActual = System.currentTimeMillis();
                long tiempoPasado = tiempoActual - startTime;

                // Calcular el tamaño deseado basado en el tiempo transcurrido
                int nuevoAncho;
                int nuevoAlto;
                if (!achicando) {
                    // Agrandar
                    double factorAgrandar = (double) tiempoPasado / 2000; // Agrandar cada 2 segundos
                    nuevoAncho = (int) (ANCHO_MINIMO + factorAgrandar * (ANCHO_MAXIMO - ANCHO_MINIMO));
                    nuevoAlto = nuevoAncho; // Mantener proporciones
                } else {
                    // Achicar
                    double factorAchicar = (double) tiempoPasado / 2000; // Achicar cada 2 segundos
                    nuevoAncho = (int) (ANCHO_MAXIMO + factorAchicar * (ANCHO_MINIMO - ANCHO_MAXIMO));
                    nuevoAlto = nuevoAncho; // Mantener proporciones
                }

                // Verificar que el tamaño no sea 0
                if (nuevoAncho > 0 && nuevoAlto > 0) {
                    // Aplicar el nuevo tamaño a la imagen redimensionada
                    imagenRedimensionada = resizeImage(imagenOriginal, nuevoAncho, nuevoAlto);
                    repaint();
                }

                // Reiniciar la animación si ha pasado 2 segundos (2000 ms)
                if (tiempoPasado >= 2000) {
                    achicando = !achicando; // Alternar entre achicar y agrandar
                    startTime = System.currentTimeMillis(); // Reiniciar el tiempo de inicio
                }
            }
        }, 0, 20); // Ejecutar cada 20 ms
    }

    private void detenerAnimacion() {
        if (timer != null) {
            timer.cancel();
            timer = null;
            // Restaurar tamaño original al detener la animación
            imagenRedimensionada = resizeImage(imagenOriginal, getWidth(), getHeight());
            repaint();
        }
    }

    @Override
    public Dimension getPreferredSize() {
        // Devolver un tamaño preferido basado en la imagen original si está cargada
        if (imagenOriginal != null) {
            return new Dimension(ANCHO_INICIAL, ALTO_INICIAL);
        }
        return super.getPreferredSize();
    }
}
