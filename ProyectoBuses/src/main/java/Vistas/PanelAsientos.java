package Vistas;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PanelAsientos extends JPanel {
    final static int MAX_ASIENTOS = 60;
    int sumaEspacios;
    PanelBus panelBus;
    PanelCodigoColor panelCodigoColor;
    PanelCambioPiso panelCambioPiso;
    PanelTituloAsientos panelTituloAsientos;
    public PanelAsientos() {
        this.setBackground(Color.DARK_GRAY);
        panelBus = new PanelBus();
        panelCodigoColor = new PanelCodigoColor();
        panelCambioPiso = new PanelCambioPiso();
        panelTituloAsientos = new PanelTituloAsientos();

        this.add(panelTituloAsientos);
        this.add(panelBus);
        this.add(panelCodigoColor);
        this.add(panelCambioPiso);
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        int anchoPanel = getWidth();
        int altoPanel = getHeight();
        int margenX = (int) (anchoPanel * 0.02);
        int margenY = (int) (altoPanel * 0.0125);

        int altoTitulo = (int) (altoPanel * 0.1);

        panelTituloAsientos.setBounds(0, 0, anchoPanel, altoTitulo);

        int anchoPanelBus = (int) (anchoPanel * 0.75);
        int altoPanelBus = (int) (altoPanel * 0.8625);
        panelBus.setBounds(margenX, margenY + altoTitulo, anchoPanelBus, altoPanelBus);

        int posXCodigoColor = (int) (1.5 * margenX) + anchoPanelBus;
        int posYCodigoColor = margenY + altoTitulo;
        int anchoCodigoColor = (int) (anchoPanel * 0.2);
        int altoCodigoColor = (int) (altoPanel * 0.75);

        panelCodigoColor.setBounds(posXCodigoColor, posYCodigoColor, anchoCodigoColor, altoCodigoColor);

        int posYCambioPiso = altoCodigoColor + posYCodigoColor + margenY;
        int anchoCambioPiso = (int) (anchoPanel * 0.2);
        int altoCambioPiso = (int) (altoPanel * 0.1);

        panelCambioPiso.setBounds(posXCodigoColor, posYCambioPiso, anchoCambioPiso, altoCambioPiso);
    }
}
