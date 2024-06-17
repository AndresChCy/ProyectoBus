package Vistas;

import javax.swing.*;
import java.awt.*;

public class PanelPrecioPagar extends JPanel {
    FuentesPersonalizadas fuentesPersonalizadas;
    String fuente = "Kristen ITC";
    String mensaje;
    BotonComprar botonComprar;
    public PanelPrecioPagar(int precio) {
        this.setBackground(Color.GREEN);
        this.mensaje = "Precio: $" + precio;

        botonComprar = new BotonComprar();
        fuentesPersonalizadas = new FuentesPersonalizadas(mensaje, fuente);

        this.add(botonComprar);
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int anchoPanel = getWidth();
        int altoPanel = getHeight();

        int tamanoFuente = fuentesPersonalizadas.calcularTamanoLetras(anchoPanel, altoPanel, g);
        g.setFont(new Font(fuente, Font.BOLD, tamanoFuente));
        FontMetrics fm = g.getFontMetrics();

        int posXMensaje = (int) (anchoPanel * 0.02);
        int posYMensaje = (int) (altoPanel * 0.05);
        g.setColor(Color.WHITE);
        g.drawString(mensaje, posXMensaje, posYMensaje + fm.getAscent());

        int posXBoton = (int) (anchoPanel * 0.7);
        int anchoBoton = (int) (anchoPanel * 0.3);
        botonComprar.setBounds(posXBoton, 0, anchoBoton, altoPanel);
    }
}
