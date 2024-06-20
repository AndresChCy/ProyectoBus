package Vistas;

import javax.swing.*;
import java.awt.*;

public class MenuSuperiorPanelInicial extends JPanel {
    private BotonConfiguraciones botonConfiguraciones;
    private FuentesPersonalizadas fuentesPersonalizadas;
    private final String mensaje = "Planea tu Próximo Viaje con Nosotros";
    private final String fuente = "Century Gothic";
    Temas.Tema temaSeleccionado;
    public MenuSuperiorPanelInicial() {
        this.temaSeleccionado = PanelSelectorRuta.temaSeleccionado;
        this.setBackground(temaSeleccionado.colorPrimario);

        botonConfiguraciones = new BotonConfiguraciones();
        fuentesPersonalizadas = new FuentesPersonalizadas(mensaje, fuente);

        this.add(botonConfiguraciones);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        int altoPanel = getHeight();
        int anchoPanel = getWidth();

        int tamanoBotonConfiguraciones = (int) (altoPanel * 0.6);
        int posYBotonConfiguraciones = (int) (altoPanel * 0.2);
        int posXBotonConfiguraciones = (int) (anchoPanel - 0.8 * altoPanel);

        int margenMensaje = (int) (altoPanel * 0.1);
        int altoMensaje = (int) (altoPanel * 0.8);
        int anchoMaxMensaje = (int) (anchoPanel * 0.75);

        int tamanoTexto = fuentesPersonalizadas.calcularTamanoLetras(anchoMaxMensaje, altoMensaje, g);
        g.setFont(new Font(fuente, Font.BOLD, tamanoTexto));
        FontMetrics fm = g.getFontMetrics();

        g.setColor(PanelSelectorRuta.temaSeleccionado.colorTerciario);
        g.drawString(mensaje, margenMensaje * 2 + 2, margenMensaje + fm.getAscent() + 2);

        g.setColor(temaSeleccionado.colorSecundario);
        g.drawString(mensaje, margenMensaje * 2, margenMensaje + fm.getAscent());

        botonConfiguraciones.setBounds(posXBotonConfiguraciones, posYBotonConfiguraciones, tamanoBotonConfiguraciones, tamanoBotonConfiguraciones);
    }
}
