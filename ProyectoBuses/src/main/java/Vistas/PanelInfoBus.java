package Vistas;

import javax.swing.*;
import java.awt.*;
import java.util.Random; //Solo para probar

public class PanelInfoBus extends JPanel {
    private String horario;
    private int precioDesde;
    private final String comprar = "COMPRAR";
    private final FuentesPersonalizadas mensajeHora;
    private final FuentesPersonalizadas mensajeAsiento;
    private final String fuente = "Roboto";
    private JButton botonComprar;
    private PanelPrincipal panelPrincipal;

    public PanelInfoBus(String horario, int precioMin, PanelPrincipal panelPrincipal) {
        this.horario = horario;
        this.precioDesde = precioMin;
        this.panelPrincipal = panelPrincipal;
        this.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        mensajeHora = new FuentesPersonalizadas("Salida: " + horario, fuente);
        mensajeAsiento = new FuentesPersonalizadas("Desde: $" + precioMin, fuente);

        // Crear el botón "Comprar"
        botonComprar = new JButton(comprar);
        botonComprar.addActionListener(e -> panelPrincipal.mostrarPanelAsientos());
        this.setLayout(null);
        this.add(botonComprar);

        //*****************
        //Solo para probar
        // ****************

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

        int anchoPanel = getWidth();
        int altoPanel = getHeight();

        int anchoInfo = (int) (anchoPanel * 0.35);
        int anchoMaxMensaje = (int) (anchoInfo * 0.90);
        int margenMensaje = (int) (altoPanel * 0.05);
        int altoMensaje = (int) (altoPanel * 0.9);

        // Dibujar el mensaje de hora
        int tamanoTextoHora = mensajeHora.calcularTamanoLetras(anchoMaxMensaje, altoMensaje, g);
        g.setFont(new Font(fuente, Font.BOLD, tamanoTextoHora));
        FontMetrics fm1 = g.getFontMetrics();
        g.drawString("Salida: " + horario, margenMensaje * 2, margenMensaje + fm1.getAscent());

        // Dibujar el mensaje del precio mínimo
        int tamanoMensajeAsiento = mensajeAsiento.calcularTamanoLetras(anchoMaxMensaje, altoMensaje, g);
        g.setFont(new Font(fuente, Font.BOLD, tamanoMensajeAsiento));
        FontMetrics fm2 = g.getFontMetrics();
        g.drawString("Desde: $" + precioDesde, margenMensaje * 2 + anchoInfo, margenMensaje + fm2.getAscent());

        // Configurar el botón "Comprar"
        int anchoBotonComprar = (int) (anchoPanel * 0.3);
        botonComprar.setBounds(2 * anchoInfo, 0, anchoBotonComprar, altoPanel);
        botonComprar.setFont(new Font(fuente, Font.BOLD, tamanoTextoHora));
    }
}
