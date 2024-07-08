package Vistas;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class BotonRetroceder extends JButton {
    private final BufferedImage imagenOriginal; // Imagen original cargada desde un archivo
    private BufferedImage imagenRedimensionada; // Imagen redimensionada para ajustarse al botón
    private Timer timer; // Timer para controlar la animación de agrandar/achicar
    private final int ANCHO_INICIAL; // Ancho inicial de la imagen original
    private final int ALTO_INICIAL; // Alto inicial de la imagen original
    private int ANCHO_MINIMO; // Ancho mínimo al que puede achicarse el botón
    private int ANCHO_MAXIMO; // Ancho máximo al que puede agrandarse el botón

    /**
     * Constructor de la clase BotonRetroceder.
     * @param retroceder PanelPrincipal al que se le notificará cuando se haga clic en el botón.
     */
    public BotonRetroceder(OperadorComandos retroceder) {
        super();

        // Cargar la imagen original desde un archivo en el recurso del proyecto
        try {
            imagenOriginal = ImageIO.read(Objects.requireNonNull(getClass().getResource("/retroceso.png")));
            ANCHO_INICIAL = imagenOriginal.getWidth();
            ALTO_INICIAL = imagenOriginal.getHeight();
            imagenRedimensionada = resizeImage(imagenOriginal, ANCHO_INICIAL, ALTO_INICIAL); // Redimensionar la imagen inicialmente
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        setOpaque(false); // Hacer que el botón no sea opaco para mostrar el fondo del panel
        setBorderPainted(false); // No dibujar borde alrededor del botón
        setContentAreaFilled(false); // No dibujar el área de contenido del botón
        addActionListener(e -> retroceder.execute()); // Agregar acción al hacer clic en el botón

        // Agregar un listener para escalar la imagen cuando el botón tenga tamaño válido
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                ajustarTamanos();
                redimensionarIcono();
            }
        });

        // Agregar listeners para iniciar y detener la animación al entrar y salir del botón
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

    /**
     * Método para dibujar la imagen redimensionada en el botón.
     * @param g Objeto Graphics para dibujar en el botón.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Dibujar la imagen escalada en el centro del botón
        if (imagenRedimensionada != null) {
            int x = (getWidth() - imagenRedimensionada.getWidth()) / 2;
            int y = (getHeight() - imagenRedimensionada.getHeight()) / 2;
            g.drawImage(imagenRedimensionada, x, y, this);
        }
    }

    /**
     * Método para ajustar los tamaños mínimo y máximo del botón.
     * Se llama cuando el botón cambia de tamaño.
     */
    private void ajustarTamanos() {
        int anchoBoton = getWidth();
        int altoBoton = getHeight();
        if (anchoBoton > 0 && altoBoton > 0) {
            ANCHO_MINIMO = anchoBoton / 2; // El ancho mínimo será la mitad del ancho del botón
            ANCHO_MAXIMO = anchoBoton; // El ancho máximo será el ancho completo del botón
        }
    }

    /**
     * Método para redimensionar la imagen del icono al tamaño actual del botón.
     * Se llama cuando el botón cambia de tamaño.
     */
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

    /**
     * Método para redimensionar una imagen dada a un tamaño específico.
     * @param imagenOriginal Imagen original que se va a redimensionar.
     * @param anchoDeseado Ancho deseado para la imagen redimensionada.
     * @param altoDeseado Alto deseado para la imagen redimensionada.
     * @return Imagen redimensionada al tamaño especificado.
     */
    private BufferedImage resizeImage(BufferedImage imagenOriginal, int anchoDeseado, int altoDeseado) {
        if (anchoDeseado <= 0 || altoDeseado <= 0) {
            return imagenOriginal;
        }
        BufferedImage imagenRedimensionada = new BufferedImage(anchoDeseado, altoDeseado, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = imagenRedimensionada.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(imagenOriginal, 0, 0, anchoDeseado, altoDeseado, null);
        g.dispose();
        return imagenRedimensionada;
    }

    /**
     * Método para iniciar la animación de agrandar/achicar el botón.
     * Se llama cuando el cursor entra en el área del botón.
     */
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
                if (nuevoAncho > 0) {
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

    /**
     * Método para detener la animación de agrandar/achicar el botón.
     * Se llama cuando el cursor sale del área del botón.
     */
    private void detenerAnimacion() {
        if (timer != null) {
            timer.cancel(); // Cancelar el Timer si está en ejecución
            timer = null;
            // Restaurar tamaño original al detener la animación
            imagenRedimensionada = resizeImage(imagenOriginal, getWidth(), getHeight());
            repaint(); // Volver a pintar para actualizar el cambio
        }
    }

    /**
     * Método para obtener el tamaño preferido del botón basado en la imagen original.
     * @return Dimension con el tamaño preferido del botón.
     */
    @Override
    public Dimension getPreferredSize() {
        // Devolver un tamaño preferido basado en la imagen original si está cargada
        if (imagenOriginal != null) {
            return new Dimension(ANCHO_INICIAL, ALTO_INICIAL);
        }
        return super.getPreferredSize();
    }
}