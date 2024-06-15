package Vistas;

import javax.swing.*;
import java.awt.*;
import java.util.Random; //Solo para probar

public class PanelInfoBus extends JPanel {
    private String horario;
    private String tipoAsiento;
    private final String comprar = "COMPRAR";

    public PanelInfoBus(String horario, String tipoAsiento) {
        this.horario = horario;
        this.tipoAsiento = tipoAsiento;
        this.setBorder(BorderFactory.createLineBorder(Color.WHITE));

        //*****************
        //Solo para probar
        //*****************

        Random rand = new Random();
        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();
        Color randomColor = new Color(r, g, b);
        this.setBackground(randomColor);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 14));

        int anchoPanel = getWidth();
        int altoPanel = getHeight();
        int padding = 10;

        // Dibujar los textos de prueba
        g.drawString("Salida: " + horario, padding, altoPanel / 2);
        g.drawString(tipoAsiento, anchoPanel / 2 - padding, altoPanel / 2);
        g.drawString(comprar, anchoPanel - g.getFontMetrics().stringWidth(comprar) - padding, altoPanel / 2);
    }
}
