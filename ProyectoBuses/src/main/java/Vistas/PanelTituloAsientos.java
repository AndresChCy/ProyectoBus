package Vistas;

import javax.swing.*;
import java.awt.*;

public class PanelTituloAsientos extends JPanel {
    FuentesPersonalizadas fuentesPersonalizadas;
    String fuente = "Broadway";
    String mensaje = "Seleccione Asiento";

    public PanelTituloAsientos() {
        this.setBackground(PanelSelectorRuta.temaSeleccionado.colorPrimario);
        fuentesPersonalizadas = new FuentesPersonalizadas(mensaje, fuente);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int anchoPanel = getWidth();
        int altoPanel = getHeight();
        int tamanoFuente = fuentesPersonalizadas.calcularTamanoLetras(anchoPanel, altoPanel, g);
        g.setFont(new Font(fuente, Font.BOLD, tamanoFuente));
        FontMetrics fm = g.getFontMetrics();
        int anchoMensaje = fm.stringWidth(mensaje) / 2;
        int posXMensaje = (anchoPanel / 2) - anchoMensaje;
        int posYMensaje = (int) (altoPanel * 0.05);
        g.setColor(Color.BLACK);
        g.drawString(mensaje, posXMensaje + 5, posYMensaje + fm.getAscent() + 5);
        g.setColor(PanelSelectorRuta.temaSeleccionado.colorSecundario);
        g.drawString(mensaje, posXMensaje, posYMensaje + fm.getAscent());
    }
}