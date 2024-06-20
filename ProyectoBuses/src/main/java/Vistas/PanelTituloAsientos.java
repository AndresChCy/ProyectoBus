package Vistas;

import javax.swing.*;
import java.awt.*;

public class PanelTituloAsientos extends JPanel {
    private final FuentesPersonalizadas fuentesPersonalizadas;
    private final String fuente = "Broadway";
    private final String mensaje = "Seleccione Asiento";

    /**
     * Constructor de la clase PanelTituloAsientos.
     * Configura el fondo y las fuentes personalizadas.
     */
    public PanelTituloAsientos() {
        this.setBackground(PanelSelectorRuta.temaSeleccionado.colorPrimario); // Color de fondo según el tema seleccionado
        fuentesPersonalizadas = new FuentesPersonalizadas(mensaje, fuente); // Inicializar las fuentes personalizadas
    }

    /**
     * Método sobrecargado para dibujar el componente.
     * Dibuja el título centralizado y con colores personalizados.
     * @param g Objeto Graphics para dibujar en el panel.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        int anchoPanel = getWidth();
        int altoPanel = getHeight();

        // Calcular el tamaño de la fuente basado en las dimensiones del panel
        int tamanoFuente = fuentesPersonalizadas.calcularTamanoLetras(anchoPanel, altoPanel, g);
        g.setFont(new Font(fuente, Font.BOLD, tamanoFuente));

        // Obtener métricas de la fuente para calcular la posición del mensaje
        FontMetrics fm = g.getFontMetrics();
        int anchoMensaje = fm.stringWidth(mensaje) / 2;
        int posXMensaje = (anchoPanel / 2) - anchoMensaje;
        int posYMensaje = (int) (altoPanel * 0.05); // Posición vertical ajustada

        // Dibujar el mensaje en negro con un ligero desplazamiento para efecto de sombra
        g.setColor(Color.BLACK);
        g.drawString(mensaje, posXMensaje + 5, posYMensaje + fm.getAscent() + 5);

        // Dibujar el mensaje en el color secundario del tema seleccionado
        g.setColor(PanelSelectorRuta.temaSeleccionado.colorSecundario);
        g.drawString(mensaje, posXMensaje, posYMensaje + fm.getAscent());
    }
}