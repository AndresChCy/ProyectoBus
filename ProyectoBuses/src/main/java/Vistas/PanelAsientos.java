package Vistas;

import javax.swing.*;
import java.awt.*;

/**
 * PanelAsientos es un JPanel que contiene varios subpaneles relacionados con la visualización de asientos de buses.
 */
public class PanelAsientos extends JPanel {

    // Paneles relacionados con la visualización de asientos
    PanelBus panelBus;
    PanelCodigoColor panelCodigoColor;
    PanelCambioPiso panelCambioPiso;
    PanelTitulo panelTituloAsientos;

    /**
     * Constructor de PanelAsientos.
     * Inicializa los subpaneles y los añade al panel principal.
     */
    public PanelAsientos(OperadorComandos avanzar,OperadorComandos retroceder) {
        // Establece el color de fondo del panel principal
        this.setBackground(Color.DARK_GRAY);

        // Inicializa los subpaneles
        BotonAvanzar botonAvanzar = new BotonAvanzar(avanzar);
        panelBus = new PanelBus();
        panelCodigoColor = new PanelCodigoColor();
        panelCambioPiso = new PanelCambioPiso();
        panelTituloAsientos = new PanelTitulo("Seleccione Asiento",retroceder);

        // Añade los subpaneles al panel principal
        this.add(botonAvanzar);
        this.add(panelTituloAsientos);
        this.add(panelBus);
        this.add(panelCodigoColor);
        this.add(panelCambioPiso);
    }

    /**
     * Sobrescribe el método paintComponent para posicionar y redimensionar los subpaneles dentro del panel principal.
     *
     * @param g Objeto Graphics para pintar los componentes.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        int anchoPanel = getWidth(); // Ancho actual del panel principal
        int altoPanel = getHeight(); // Alto actual del panel principal

        // Márgenes para los componentes dentro del panel principal
        int margenX = (int) (anchoPanel * 0.02);
        int margenY = (int) (altoPanel * 0.0125);

        // Altura del título en relación al tamaño del panel principal
        int altoTitulo = (int) (altoPanel * 0.1);

        // Posiciona y dimensiona el panel de título de asientos
        panelTituloAsientos.setBounds(0, 0, anchoPanel, altoTitulo);

        // Dimensiones del panel de buses
        int anchoPanelBus = (int) (anchoPanel * 0.75);
        int altoPanelBus = (int) (altoPanel * 0.8625);

        // Posiciona y dimensiona el panel de buses dentro del panel principal
        panelBus.setBounds(margenX, margenY + altoTitulo, anchoPanelBus, altoPanelBus);

        // Posición y dimensiones del panel de código de color
        int posXCodigoColor = (int) (1.5 * margenX) + anchoPanelBus;
        int posYCodigoColor = margenY + altoTitulo;
        int anchoCodigoColor = (int) (anchoPanel * 0.2);
        int altoCodigoColor = (int) (altoPanel * 0.75);

        // Posiciona y dimensiona el panel de código de color dentro del panel principal
        panelCodigoColor.setBounds(posXCodigoColor, posYCodigoColor, anchoCodigoColor, altoCodigoColor);

        // Posición y dimensiones del panel de cambio de piso
        int posYCambioPiso = altoCodigoColor + posYCodigoColor + margenY;
        int anchoCambioPiso = (int) (anchoPanel * 0.2);
        int altoCambioPiso = (int) (altoPanel * 0.1);

        // Posiciona y dimensiona el panel de cambio de piso dentro del panel principal
        panelCambioPiso.setBounds(posXCodigoColor, posYCambioPiso, anchoCambioPiso, altoCambioPiso);
    }
}