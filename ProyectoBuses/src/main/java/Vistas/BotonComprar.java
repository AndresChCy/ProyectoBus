package Vistas;

import javax.swing.*;
import java.awt.*;

/**
 * Clase que representa un botón personalizado para realizar una compra.
 */
public class BotonComprar extends JButton {
    private final FuentesPersonalizadas fuentesPersonalizadas;
    private final String fuente = "Roboto";
    private final String mensaje = "COMPRAR";

    /**
     * Constructor de la clase BotonComprar.
     * Configura el color de fondo y fuente del botón, y añade un ActionListener para imprimir un mensaje al hacer clic.
     */
    public BotonComprar(OperadorComandos oc) {
        // Establecer el color de fondo del botón usando el color secundario del tema seleccionado
        this.setBackground(PanelSelectorRuta.temaSeleccionado.colorSecundario);

        // Inicializar objeto para gestionar fuentes personalizadas
        fuentesPersonalizadas = new FuentesPersonalizadas(mensaje, fuente);

        // Agregar ActionListener para manejar el evento de clic
        this.addActionListener(e -> oc.execute());
    }

    /**
     * Método para pintar el componente del botón con el mensaje "COMPRAR".
     * @param g Objeto Graphics utilizado para dibujar el componente.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int anchoPanel = getWidth();
        int altoPanel = getHeight();

        // Calcular y establecer el tamaño de la fuente para que el mensaje "COMPRAR" se ajuste al botón
        int tamanoFuente = fuentesPersonalizadas.calcularTamanoLetras(anchoPanel, altoPanel, g);
        g.setFont(new Font(fuente, Font.BOLD, tamanoFuente));
        FontMetrics fm = g.getFontMetrics();
        int anchoMensaje = fm.stringWidth(mensaje) / 2;
        int posXMensaje = (anchoPanel / 2) - anchoMensaje;
        int posYMensaje = (altoPanel + fm.getAscent()) / 2;

        // Dibujar el mensaje "COMPRAR" con sombra y luego con color personalizado
        g.setColor(Color.BLACK);
        g.drawString(mensaje, posXMensaje + 2, posYMensaje + 2); // Sombra
        g.setColor(new Color(27, 101, 64)); // Color personalizado
        g.drawString(mensaje, posXMensaje, posYMensaje); // Texto principal

        // Establecer la fuente del botón
        this.setFont(g.getFont());
    }
}
