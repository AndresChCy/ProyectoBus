package Vistas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.Random; // Solo para probar

public class PanelHorariosDisponibles extends JPanel implements MouseWheelListener {
    private String[] arrayAuxiliar;
    private String[] tipoAsientoAuxiliar;
    private PanelInfoBus[] panelesInfoBus;
    private int desplazamiento = 0;
    private PanelPrincipal panelPrincipal;
    private Random random; // Solo para probar

    public PanelHorariosDisponibles(String[] arrayAuxiliar, String[] tipoAsientoAuxiliar, PanelPrincipal panelPrincipal) {
        this.arrayAuxiliar = arrayAuxiliar;
        this.tipoAsientoAuxiliar = tipoAsientoAuxiliar;
        this.panelPrincipal = panelPrincipal;
        this.panelesInfoBus = new PanelInfoBus[arrayAuxiliar.length];
        this.setLayout(null);
        this.setBackground(Color.BLACK);
        this.addMouseWheelListener(this);
        this.random = new Random(); // Solo para probar
        this.inicializarPaneles();
    }

    private void inicializarPaneles() {
        int anchoPanel = getWidth();
        int altoPanel = getHeight();
        int altoPanelInfoBus = (int) (altoPanel * 0.1);

        for (int i = 0; i < arrayAuxiliar.length; i++) {
            int numeroRandom = random.nextInt(45001) + 5000;; // Solo para probar

            panelesInfoBus[i] = new PanelInfoBus(arrayAuxiliar[i], numeroRandom, panelPrincipal);
            panelesInfoBus[i].setBounds(0, i * altoPanelInfoBus, anchoPanel, altoPanelInfoBus);
            this.add(panelesInfoBus[i]);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int anchoPanel = getWidth();
        int altoPanel = getHeight();
        int altoPanelInfoBus = (int) (altoPanel * 0.1);

        for (int i = 0; i < arrayAuxiliar.length; i++) {
            panelesInfoBus[i].setBounds(0, (i * altoPanelInfoBus) - desplazamiento, anchoPanel, altoPanelInfoBus);
        }
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        int notches = e.getWheelRotation();
        int altoPanel = getHeight();
        int altoPanelInfoBus = (int) (altoPanel * 0.1);
        int maxDesplazamiento = Math.max(0, altoPanelInfoBus * arrayAuxiliar.length - altoPanel);

        desplazamiento += notches * altoPanelInfoBus / 5;
        desplazamiento = Math.max(0, Math.min(desplazamiento, maxDesplazamiento));

        repaint();
    }
}
