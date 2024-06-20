package Vistas;

import javax.swing.*;
import java.awt.*;

public class PanelHorarios extends JPanel {
    private final FuentesPersonalizadas fuentesPersonalizadas;
    private final String mensaje = "Horarios Disponibles";
    private final String fuente = "Broadway";
    private final String[] arrayHorarios = {
            "12:00 A.M", "12:30 A.M", "1:00 A.M", "2:00 A.M", "3:00 A.M", "4:00 A.M", "5:00 A.M", "6:00 A.M",
            "7:00 A.M", "8:00 A.M", "9:00 A.M", "10:00 A.M", "10:30 A.M", "11:00 A.M", "12:00 P.M", "1:00 P.M",
            "2:00 P.M", "3:00 P.M", "4:00 P.M", "5:00 P.M", "6:00 P.M", "7:00 P.M", "8:00 P.M", "9:00 P.M",
            "10:00 P.M", "11:00 P.M"
    };
    private final BotonRetroceder botonRetroceder;

    public PanelHorarios(PanelPrincipal panelPrincipal) {
        setLayout(null);
        setBackground(PanelSelectorRuta.temaSeleccionado.colorPrimario);

        fuentesPersonalizadas = new FuentesPersonalizadas(mensaje, fuente);

        JPanel panelTitulo = getPanelTitulo();

        botonRetroceder = new BotonRetroceder(panelPrincipal);
        botonRetroceder.setBounds(10, 10, 30, 30); // Ajusta las coordenadas y dimensiones según tu diseño

        PanelHorariosDisponibles panelHorariosDisponibles = new PanelHorariosDisponibles(arrayHorarios, panelPrincipal);
        panelHorariosDisponibles.setBounds(0, (int) (getHeight() * 0.3), getWidth(), (int) (getHeight() * 0.7));

        add(panelTitulo);
        add(panelHorariosDisponibles);
        add(botonRetroceder);
    }

    private JPanel getPanelTitulo() {
        JPanel panelTitulo = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                setBackground(PanelSelectorRuta.temaSeleccionado.colorPrimario);
                int anchoPanel = getWidth();
                int altoPanel = getHeight();
                int tamanoFuente = fuentesPersonalizadas.calcularTamanoLetras(anchoPanel, altoPanel, g);
                g.setFont(new Font(fuente, Font.BOLD, tamanoFuente));
                FontMetrics fm = g.getFontMetrics();
                int anchoMensaje = fm.stringWidth(mensaje) / 2;
                int posXMensaje = (anchoPanel / 2) - anchoMensaje;
                g.setColor(PanelSelectorRuta.temaSeleccionado.colorSecundario);
                g.drawString(mensaje, posXMensaje + 2, altoPanel - fm.getHeight() / 2 + 2);
                g.setColor(PanelSelectorRuta.temaSeleccionado.colorTerciario);
                g.drawString(mensaje, posXMensaje, altoPanel - fm.getHeight() / 2);
            }
        };
        panelTitulo.setBounds(0, 0, getWidth(), (int) (getHeight() * 0.3));
        panelTitulo.setBackground(Color.BLACK);
        return panelTitulo;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int anchoPanel = getWidth();
        int altoPanel = getHeight();

        PanelHorariosDisponibles panelHorariosDisponibles = (PanelHorariosDisponibles) getComponent(1);
        panelHorariosDisponibles.setBounds(0, (int) (altoPanel * 0.3), anchoPanel, (int) (altoPanel * 0.7));

        int margenBoton = (int) (altoPanel * 0.1 * 0.3);
        int medidaBoton = (int) (altoPanel * 0.3 * 0.3);

        int margenTitulo = 2 * margenBoton + medidaBoton;

        JPanel panelTitulo = (JPanel) getComponent(0);
        panelTitulo.setBounds(margenTitulo, 0, anchoPanel - 2 * margenTitulo, (int) (altoPanel * 0.3));
        botonRetroceder.setBounds(margenBoton, margenBoton, medidaBoton, medidaBoton);
    }
}
