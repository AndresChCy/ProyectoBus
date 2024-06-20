package Vistas;

import javax.swing.*;
import java.awt.*;

public class PanelPrecioPagar extends JPanel {
    private FuentesPersonalizadas fuentesPersonalizadas;
    private String fuente = "Comic Sans MS";
    private String mensaje;
    private BotonComprar botonComprar;

    public PanelPrecioPagar(int precio) {
        this.setOpaque(false);
        this.mensaje = "Precio: $" + precio;

        botonComprar = new BotonComprar();
        fuentesPersonalizadas = new FuentesPersonalizadas(mensaje, fuente);

        this.add(botonComprar);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int anchoPanel = getWidth();
        int altoPanel = getHeight();

        int tamanoFuente = fuentesPersonalizadas.calcularTamanoLetras(anchoPanel, altoPanel, g);
        g.setFont(new Font(fuente, Font.BOLD, tamanoFuente));
        FontMetrics fm = g.getFontMetrics();

        int posXMensaje = (int) (anchoPanel * 0.02);
        int posYMensaje = (int) (altoPanel * 0.05);

        g.setColor(Color.BLACK);
        g.drawString(mensaje, posXMensaje + 4, posYMensaje + fm.getAscent() + 4);

        int indiceDolar = mensaje.indexOf('$');
        if (indiceDolar != -1) {
            g.setColor(PanelSelectorRuta.temaSeleccionado.colorTerciario);
            g.drawString(mensaje.substring(0, indiceDolar), posXMensaje, posYMensaje + fm.getAscent());

            g.setColor(new Color(27, 101, 64));
            int posXPrecio = posXMensaje + fm.stringWidth(mensaje.substring(0, indiceDolar));
            g.drawString("$", posXPrecio, posYMensaje + fm.getAscent());
            g.setColor(new Color(27, 101, 64));
            g.drawString(mensaje.substring(indiceDolar + 1), posXPrecio + fm.stringWidth("$"), posYMensaje + fm.getAscent());
        } else {
            g.setColor(PanelSelectorRuta.temaSeleccionado.colorTerciario);
            g.drawString(mensaje, posXMensaje, posYMensaje + fm.getAscent());
        }

        int posXBoton = (int) (anchoPanel * 0.7);
        int anchoBoton = (int) (anchoPanel * 0.3);
        botonComprar.setBounds(posXBoton, 0, anchoBoton, altoPanel);
    }
}
