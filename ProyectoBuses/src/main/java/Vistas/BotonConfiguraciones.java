package Vistas;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class BotonConfiguraciones extends JButton {
    private ImageIcon iconoTuerca;
    private Image imagenOriginal;
    private double angulo = 0;
    private Timer timer;

    public BotonConfiguraciones() {
        // Cargar la imagen original
        try {
            imagenOriginal = ImageIO.read(getClass().getResource("/tuerca.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        setOpaque(false);
        setContentAreaFilled(false);  // No llenar el área de contenido
        setBorderPainted(false);      // No dibujar el borde

        // Agregar un listener para esperar a que el botón se muestre y tenga tamaño válido
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                escalarImagen(getWidth(), getHeight()); // Escalar imagen cuando el botón tenga tamaño válido
            }
        });

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Acción a realizar cuando se hace clic en el botón
                System.out.println("Botón de configuraciones pulsado");
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

        // Dibujar la imagen rotada
        if (iconoTuerca != null) {
            Graphics2D g2d = (Graphics2D) g.create();
            int x = (getWidth() - iconoTuerca.getIconWidth()) / 2;
            int y = (getHeight() - iconoTuerca.getIconHeight()) / 2;
            g2d.rotate(angulo, getWidth()/2, getHeight()/2);
            iconoTuerca.paintIcon(this, g2d, x, y);
            g2d.dispose();
        }
    }

    private void escalarImagen(int anchoDeseado, int altoDeseado) {
        if (imagenOriginal != null) {
            // Escalar la imagen al tamaño deseado
            Image imagenEscalada = imagenOriginal.getScaledInstance(anchoDeseado, altoDeseado, Image.SCALE_SMOOTH);
            iconoTuerca = new ImageIcon(imagenEscalada);
            setIcon(null);  // Ocultar cualquier icono inicial
            repaint();      // Volver a pintar para actualizar el cambio
        }
    }

    private void iniciarAnimacion() {
        if (timer == null) {
            timer = new Timer(50, e -> {
                angulo += Math.toRadians(5);  // Incremento del ángulo de rotación
                repaint();  // Volver a dibujar para actualizar la imagen rotada
            });
            timer.start();
        }
    }

    private void detenerAnimacion() {
        if (timer != null) {
            timer.stop();
            timer = null;
            angulo = 0;  // Reiniciar el ángulo cuando se detiene la animación
            repaint();  // Volver a dibujar para restaurar la imagen original
        }
    }

    @Override
    public Dimension getPreferredSize() {
        // Devolver un tamaño preferido basado en la imagen original si está cargada
        if (imagenOriginal != null) {
            return new Dimension(imagenOriginal.getWidth(null), imagenOriginal.getHeight(null));
        }
        return super.getPreferredSize();
    }
}
