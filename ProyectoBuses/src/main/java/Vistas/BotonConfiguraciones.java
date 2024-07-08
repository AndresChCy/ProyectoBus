package Vistas;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase que representa un botón de configuraciones con una imagen de una tuerca.
 */
public class BotonConfiguraciones extends JButton {
    private ImageIcon iconoTuerca; // Icono de la tuerca para ser mostrado en el botón
    private Image imagenOriginal; // Imagen original de la tuerca cargada desde un archivo
    private double angulo = 0; // Ángulo de rotación actual de la imagen
    private Timer timer; // Timer para controlar la animación de rotación
    private static final Logger logger = Logger.getLogger(BotonConfiguraciones.class.getName());

    /**
     * Constructor de la clase BotonConfiguraciones.
     * Carga la imagen de la tuerca desde el archivo y configura el comportamiento del botón.
     */
    public BotonConfiguraciones() {
        // Cargar la imagen original desde un archivo en el recurso del proyecto
        try {
            imagenOriginal = ImageIO.read(Objects.requireNonNull(getClass().getResource("/tuerca.png")));
        } catch (IOException e) {
            // Manejo de excepción si la carga de imagen falla
            logger.log(Level.SEVERE, "Error al cargar la imagen de la tuerca", e);
        }

        setOpaque(false); // Hacer que el botón no sea opaco para mostrar el fondo del panel
        setContentAreaFilled(false);  // No llenar el área de contenido del botón
        setBorderPainted(false);      // No dibujar el borde del botón

        // Agregar un listener para escalar la imagen cuando el botón tenga tamaño válido
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                escalarImagen(getWidth(), getHeight()); // Escalar imagen cuando el botón tenga tamaño válido
            }
        });

        // Agregar un listener para la acción de clic en el botón
        addActionListener(e -> {
            // Acción a realizar cuando se hace clic en el botón
            System.out.println("Botón de configuraciones pulsado");
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
     * Método para dibujar la imagen rotada en el botón.
     * @param g Objeto Graphics para dibujar en el botón.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Dibujar la imagen rotada con el ángulo actual
        if (iconoTuerca != null) {
            Graphics2D g2d = (Graphics2D) g.create();
            int x = (getWidth() - iconoTuerca.getIconWidth()) / 2; // Calcular posición X centrada
            int y = (getHeight() - iconoTuerca.getIconHeight()) / 2; // Calcular posición Y centrada
            g2d.rotate(angulo, (double) getWidth() / 2, (double) getHeight() / 2); // Rotar en torno al centro del botón
            iconoTuerca.paintIcon(this, g2d, x, y); // Dibujar la imagen rotada
            g2d.dispose();
        }
    }

    /**
     * Método para escalar la imagen de la tuerca al tamaño deseado.
     * @param anchoDeseado Ancho deseado para la imagen escalada.
     * @param altoDeseado Alto deseado para la imagen escalada.
     */
    private void escalarImagen(int anchoDeseado, int altoDeseado) {
        if (imagenOriginal != null) {
            // Escalar la imagen al tamaño deseado
            Image imagenEscalada = imagenOriginal.getScaledInstance(anchoDeseado, altoDeseado, Image.SCALE_SMOOTH);
            iconoTuerca = new ImageIcon(imagenEscalada);
            setIcon(null);  // Ocultar cualquier icono inicial del botón
            repaint();      // Volver a pintar para actualizar el cambio
        }
    }

    /**
     * Método para iniciar la animación de rotación de la imagen.
     */
    private void iniciarAnimacion() {
        if (timer == null) {
            timer = new Timer(50, e -> {
                angulo += Math.toRadians(5);  // Incrementar el ángulo de rotación
                repaint();  // Volver a dibujar para actualizar la imagen rotada
            });
            timer.start(); // Iniciar el Timer para ejecutar la animación
        }
    }

    /**
     * Método para detener la animación de rotación de la imagen.
     */
    private void detenerAnimacion() {
        if (timer != null) {
            timer.stop(); // Detener el Timer si está en ejecución
            timer = null;
            angulo = 0;  // Reiniciar el ángulo cuando se detiene la animación
            repaint();  // Volver a dibujar para restaurar la imagen original
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
            return new Dimension(imagenOriginal.getWidth(null), imagenOriginal.getHeight(null));
        }
        return super.getPreferredSize();
    }
}