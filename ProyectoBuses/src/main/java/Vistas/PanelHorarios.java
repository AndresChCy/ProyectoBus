package Vistas;

import javax.swing.*;
import java.awt.*;

public class PanelHorarios extends JPanel {
    private String[] arrayAuxiliar;
    private String[] tipoAsientoAuxiliar;
    private FuentesPersonalizadas fuentesPersonalizadas;
    private final String mensaje = "Horarios Disponibles";
    private final String fuente = "Kristen ITC";
    private PanelPrincipal panelPrincipal;

    public PanelHorarios(PanelPrincipal panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
        this.setLayout(null);
        this.setBackground(Color.BLACK);
        fuentesPersonalizadas = new FuentesPersonalizadas(mensaje, fuente);
        arrayAuxiliar = new String[]{"12:00 A.M", "12:30 A.M", "1:00 A.M", "2:00 A.M", "3:00 A.M", "4:00 A.M", "5:00 A.M", "6:00 A.M",
                "7:00 A.M", "8:00 A.M", "9:00 A.M", "10:00 A.M", "10:30 A.M", "11:00 A.M", "12:00 P.M", "1:00 P.M",
                "2:00 P.M", "3:00 P.M", "4:00 P.M", "5:00 P.M", "6:00 P.M", "7:00 P.M", "8:00 P.M", "9:00 P.M",
                "10:00 P.M", "11:00 P.M"};
        tipoAsientoAuxiliar = new String[]{"Estándar", "Semi Cama", "Salón Cama", "Primera Clase"};

        JPanel panelTitulo = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                int anchoPanel = getWidth();
                int altoPanel = getHeight();
                int tamanoFuente = fuentesPersonalizadas.calcularTamanoLetras(1, anchoPanel, altoPanel, g);
                g.setFont(new Font(fuente, Font.BOLD, tamanoFuente));
                FontMetrics fm = g.getFontMetrics();
                int anchoMensaje = fm.stringWidth(mensaje) / 2;
                int posXMensaje = (anchoPanel / 2) - anchoMensaje;
                g.setColor(Color.WHITE);
                g.drawString(mensaje, posXMensaje, altoPanel - fm.getHeight() / 2);
            }
        };
        panelTitulo.setBounds(0, 0, getWidth(), (int) (getHeight() * 0.3));
        panelTitulo.setBackground(Color.BLACK);

        PanelHorariosDisponibles panelHorariosDisponibles = new PanelHorariosDisponibles(arrayAuxiliar, tipoAsientoAuxiliar, panelPrincipal);
        panelHorariosDisponibles.setBounds(0, (int) (getHeight() * 0.3), getWidth(), (int) (getHeight() * 0.7));

        this.add(panelTitulo);
        this.add(panelHorariosDisponibles);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int anchoPanel = getWidth();
        int altoPanel = getHeight();

        JPanel panelTitulo = (JPanel) getComponent(0);
        panelTitulo.setBounds(0, 0, anchoPanel, (int) (altoPanel * 0.3));

        PanelHorariosDisponibles panelHorariosDisponibles = (PanelHorariosDisponibles) getComponent(1);
        panelHorariosDisponibles.setBounds(0, (int) (altoPanel * 0.3), anchoPanel, (int) (altoPanel * 0.7));
    }
}
