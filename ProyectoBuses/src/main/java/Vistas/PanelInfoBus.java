package Vistas;

import javax.swing.*;
import java.awt.*;

public class PanelInfoBus extends JPanel {
    private final String horario;
    private final int precioDesde;
    private final FuentesPersonalizadas mensajeHora;
    private final FuentesPersonalizadas mensajeAsiento;
    private final String fuente = "Roboto";
    private final JButton botonComprar;

    public PanelInfoBus(String horario, int precioMin, PanelPrincipal panelPrincipal, Color color) {
        this.horario = horario;
        this.precioDesde = precioMin;
        this.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        mensajeHora = new FuentesPersonalizadas("Salida: " + horario, fuente);
        mensajeAsiento = new FuentesPersonalizadas("Desde: $" + precioMin, fuente);

        String comprar = "COMPRAR";
        botonComprar = new JButton(comprar);
        botonComprar.addActionListener(e -> panelPrincipal.mostrarPanelAsientos());
        botonComprar.setBackground(PanelSelectorRuta.temaSeleccionado.colorSecundario);
        botonComprar.setForeground(Color.BLACK);
        this.setLayout(null);
        this.add(botonComprar);

        this.setBackground(color);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int anchoPanel = getWidth();
        int altoPanel = getHeight();

        int anchoInfo = (int) (anchoPanel * 0.35);
        int anchoMaxMensaje = (int) (anchoInfo * 0.90);
        int margenMensaje = (int) (altoPanel * 0.05);
        int altoMensaje = (int) (altoPanel * 0.9);

        int tamanoTextoHora = mensajeHora.calcularTamanoLetras(anchoMaxMensaje, altoMensaje, g);
        FontMetrics fm1 = g.getFontMetrics();
        g.setFont(new Font(fuente, Font.BOLD, tamanoTextoHora));
        g.setColor(Color.BLACK);
        g.drawString("Salida: " + horario, margenMensaje * 2 + 2, margenMensaje + fm1.getAscent() + 2);
        g.setColor(PanelSelectorRuta.temaSeleccionado.colorSecundario);
        g.drawString("Salida: " + horario, margenMensaje * 2, margenMensaje + fm1.getAscent());

        int xSeparador = margenMensaje + anchoInfo;
        g.setColor(Color.GRAY);
        g.drawLine(xSeparador, margenMensaje, xSeparador, altoPanel - margenMensaje);

        int tamanoMensajeAsiento = mensajeAsiento.calcularTamanoLetras(anchoMaxMensaje, altoMensaje, g);
        FontMetrics fm2 = g.getFontMetrics();
        g.setFont(new Font(fuente, Font.BOLD, tamanoMensajeAsiento));
        g.setColor(Color.BLACK);
        g.drawString("Desde: $" + precioDesde, margenMensaje * 2 + xSeparador + 2, margenMensaje + fm2.getAscent() + 2);
        g.setColor(PanelSelectorRuta.temaSeleccionado.colorSecundario);
        g.drawString("Desde: $" + precioDesde, margenMensaje * 2 + xSeparador, margenMensaje + fm2.getAscent());

        int anchoBotonComprar = (int) (anchoPanel * 0.2); // Reducir el ancho del botón
        botonComprar.setBounds(anchoPanel - margenMensaje - anchoBotonComprar, margenMensaje, anchoBotonComprar, altoPanel - 2 * margenMensaje);
        botonComprar.setFont(new Font(fuente, Font.BOLD, tamanoTextoHora));
    }
}
