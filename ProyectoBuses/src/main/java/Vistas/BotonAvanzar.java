package Vistas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BotonAvanzar extends JButton {
    private final FuentesPersonalizadas fuentesPersonalizadas;
    private final String fuente = "Roboto";
    private final String mensaje = "CONTINUAR";
    private boolean isMouseOver = false;

    /**
     * Constructor de la clase BotonAvanzar.
     * Configura el color de fondo y fuente del botón, y añade un ActionListener para ejecutar un comando al hacer clic.
     */
    public BotonAvanzar(OperadorComandos command) {
        // Eliminar el background predeterminado para personalizar el pintado
        this.setContentAreaFilled(false);
        this.setOpaque(false);
        this.setBorderPainted(false);

        // Inicializar objeto para gestionar fuentes personalizadas
        fuentesPersonalizadas = new FuentesPersonalizadas(mensaje, fuente);

        // Agregar ActionListener para manejar el evento de clic
        this.addActionListener(e -> command.execute());

        // Agregar MouseListener para manejar el evento cuando el mouse está encima
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                isMouseOver = true;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                isMouseOver = false;
                repaint();
            }
        });
    }

    /**
     * Método para pintar el componente del botón con el mensaje "CONTINUAR".
     * @param g Objeto Graphics utilizado para dibujar el componente.
     */
    @Override
    protected void paintComponent(Graphics g) {
        // Pintar el fondo con opacidad
        Graphics2D g2d = (Graphics2D) g.create();
        int anchoPanel = getWidth();
        int altoPanel = getHeight();

        // Establecer el color de fondo con opacidad del 20%
        Color colorFondoConOpacidad = getFondoConOpacidad();
        g2d.setColor(colorFondoConOpacidad);
        g2d.fillRect(0, 0, anchoPanel, altoPanel);

        // Calcular y establecer el tamaño de la fuente para que el mensaje "CONTINUAR" se ajuste al botón
        int tamanoFuente = fuentesPersonalizadas.calcularTamanoLetras(anchoPanel, altoPanel, g2d);
        if (isMouseOver) {
            // Aumentar el tamaño de la fuente cuando el mouse está encima
            tamanoFuente = (int) (tamanoFuente * 1.1);
        }
        g2d.setFont(new Font(fuente, Font.BOLD, tamanoFuente));
        FontMetrics fm = g2d.getFontMetrics();
        int anchoMensaje = fm.stringWidth(mensaje) / 2;
        int posXMensaje = (anchoPanel / 2) - anchoMensaje;
        int posYMensaje = (altoPanel + fm.getAscent()) / 2;

        // Dibujar el mensaje "CONTINUAR" con sombra y luego con color personalizado
        g2d.setColor(Color.BLACK);
        g2d.drawString(mensaje, posXMensaje + 2, posYMensaje + 2); // Sombra
        g2d.setColor(Temas.temaSeleccionado.colorTerciario); // Color personalizado
        g2d.drawString(mensaje, posXMensaje, posYMensaje); // Texto principal

        // Liberar recursos del contexto gráfico
        g2d.dispose();

        // Establecer la fuente del botón
        this.setFont(g.getFont());
    }

    private Color getFondoConOpacidad() {
        Color colorFondo = Temas.temaSeleccionado.colorSecundario;
        Color colorFondoConOpacidad = new Color(colorFondo.getRed(), colorFondo.getGreen(), colorFondo.getBlue(), 51); // 51 es 20% de 255
        if (isMouseOver) {
            // Cambiar color y opacidad cuando el mouse está encima
            colorFondoConOpacidad = new Color(colorFondo.getRed(), colorFondo.getGreen(), colorFondo.getBlue(), 102); // 40% de 255
        }
        return colorFondoConOpacidad;
    }
}