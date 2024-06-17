package Vistas;

import javax.swing.*;
import java.awt.*;

public class PanelTituloInfoPasajero extends JPanel {
    FuentesPersonalizadas fuentesPersonalizadas;
    String fuente = "Kristen ITC";
    String mensaje = "Información del Pasajero:";

    public PanelTituloInfoPasajero() {
        this.setBackground(Color.ORANGE);
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
        g.setColor(Color.WHITE);
        g.drawString(mensaje, posXMensaje, posYMensaje + fm.getAscent());
    }
}