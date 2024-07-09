package Vistas;

import Modelo.CalendarioObserver;
import Modelo.CalendarioViajes;
import Modelo.PisoBus;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * PanelAsientos es un JPanel que contiene varios subpaneles relacionados con la visualización de asientos de buses.
 */
public class PanelAsientos extends JPanel implements CalendarioObserver, TemasObserver {

    // Paneles relacionados con la visualización de asientos
    PanelBus panelBus;
    ArrayList<PanelBus> paneles;
    PanelCodigoColor panelCodigoColor;
    PanelCambioPiso panelCambioPiso;
    PanelTitulo panelTituloAsientos;
    ComandoCrearComprador informar;
    BotonAvanzar botonAvanzar;
    PanelPrecioPagar precioPagar;

    /**
     * Constructor de PanelAsientos.
     * Inicializa los subpaneles y los añade al panel principal.
     * @param avanzar Comando para avanzar
     * @param retroceder Comando para retroceder
     * @param compradores Comando para crear compradores
     */
    public PanelAsientos(Comandos avanzar, Comandos retroceder, ComandoCrearComprador compradores) {
        // Establece el layout y el color de fondo del panel principal
        setLayout(new GridBagLayout());
        this.setBackground(Color.DARK_GRAY);
        this.informar = compradores;

        // Configuración de comandos
        OperadorComandos comandoAtras = new OperadorComandos(retroceder);
        comandoAtras.addComando(new ComandoResetear(informar.getList()));
        OperadorComandos comandosAvanzar = new OperadorComandos(informar);
        comandosAvanzar.addComando(avanzar);

        // Inicialización de los subpaneles
        botonAvanzar = new BotonAvanzar(comandosAvanzar, "CONTINUAR");
        panelCodigoColor = new PanelCodigoColor();
        panelTituloAsientos = new PanelTitulo("Seleccione Asiento", comandoAtras);
        paneles = new ArrayList<>();
        precioPagar = new PanelPrecioPagar(0);
        compradores.setPanelPrecio(precioPagar);

        // Añade los subpaneles al panel principal
        this.add(botonAvanzar);
        this.add(panelTituloAsientos);
        this.add(panelCodigoColor);
        this.add(precioPagar);
        //update();
    }

    /**
     * Sobrescribe el método paintComponent para posicionar y redimensionar los subpaneles dentro del panel principal.
     *
     * @param g Objeto Graphics para pintar los componentes.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Obtiene las dimensiones actuales del panel principal
        int anchoPanel = getWidth();
        int altoPanel = getHeight();

        // Márgenes para los componentes dentro del panel principal
        int margenX = (int) (anchoPanel * 0.02);
        int margenY = (int) (altoPanel * 0.0125);

        // Altura del título en relación al tamaño del panel principal
        int altoTitulo = (int) (altoPanel * 0.1);

        // Posiciona y dimensiona el panel de título de asientos
        panelTituloAsientos.setBounds(0, 0, anchoPanel, altoTitulo);

        // Dimensiones del panel de buses
        int anchoPanelBus = (int) (anchoPanel * 0.75);
        int altoPanelBus = (int) (altoPanel * 0.75);

        // Posiciona y dimensiona el panel de buses dentro del panel principal
        panelBus.setBounds(margenX, margenY + altoTitulo, anchoPanelBus, altoPanelBus);
        panelBus.repaint();

        // Posición y dimensiones del panel de código de color
        int posXCodigoColor = (int) (1.5 * margenX) + anchoPanelBus;
        int posYCodigoColor = margenY + altoTitulo;
        int anchoCodigoColor = (int) (anchoPanel * 0.2);
        int altoCodigoColor = (int) (altoPanel * 0.75);

        // Posiciona y dimensiona el panel de código de color dentro del panel principal
        panelCodigoColor.setBounds(posXCodigoColor, posYCodigoColor, anchoCodigoColor, altoCodigoColor);

        // Posición y dimensiones del panel de cambio de piso
        int posXCambioPiso = margenX;
        int posYCambioPiso = altoPanelBus + 2 * margenY + altoTitulo;
        int altoCambioPiso = (int) (altoPanel * 0.1);
        int anchoCambioPiso = (int) (anchoPanel * 0.25);

        // Posiciona y dimensiona el panel de cambio de piso dentro del panel principal
        panelCambioPiso.setBounds(posXCambioPiso, posYCambioPiso, anchoCambioPiso, altoCambioPiso);

        // Posición y dimensiones del panel de precio a pagar
        int posXPrecioPagar = (int) (anchoPanel * 0.17) + anchoCambioPiso + posXCambioPiso + margenX;
        int anchoPrecioPagar = (int) (anchoPanel * 0.5);
        int altoPrecioPagar = (int) (altoPanel * 0.1);
        precioPagar.setBounds(posXPrecioPagar, posYCambioPiso, anchoPrecioPagar, altoPrecioPagar);

        // Posición y dimensiones del botón de avanzar
        int posYBotonAvanzar = altoCodigoColor + posYCodigoColor + margenY;
        int anchoBotonAvanzar = (int) (anchoPanel * 0.2);
        int altoBotonAvanzar = (int) (altoPanel * 0.1);
        botonAvanzar.setBounds(posXCodigoColor, posYBotonAvanzar, anchoBotonAvanzar, altoBotonAvanzar);
    }

    /**
     * Actualiza los paneles dentro del PanelAsientos.
     * Elimina los paneles actuales y los reemplaza con nuevos basados en la información del bus.
     */
    public void update() {
        try {
            // Elimina los paneles actuales si existen
            this.remove(panelBus);
            this.remove(panelCambioPiso);
        } catch (Exception ignored) {}
        try {
            // Obtiene los pisos del bus y crea los paneles correspondientes
            ArrayList<PisoBus> pisos = CalendarioViajes.getInstance().getViaje().getBus().getPisosBus();
            paneles.clear();
            for (PisoBus piso : pisos) {
                paneles.add(new PanelBus(piso, informar));
            }
            panelBus = paneles.get(0);
            panelCambioPiso = new PanelCambioPiso(this);

            this.add(panelBus);
            this.add(panelCambioPiso);
            repaint();
        } catch (NullPointerException e) {
            System.out.println("No se encontró el bus: " + e.getMessage());
        }
    }

    /**
     * Obtiene la lista de paneles de bus.
     * @return Lista de paneles de bus.
     */
    public ArrayList<PanelBus> getPaneles() {
        return paneles;
    }

    /**
     * Establece el panel de bus actual basado en un índice.
     * @param i Indice del panel de bus.
     */
    public void setPanelBus(int i) {
        this.panelBus = paneles.get(i);
        remove(panelBus);
        add(panelBus);
    }

    /**
     * Actualiza el tema del panel.
     */
    public void updateTema() {}
}
