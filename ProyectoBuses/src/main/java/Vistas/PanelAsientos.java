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
public class PanelAsientos extends JPanel implements CalendarioObserver {

    // Paneles relacionados con la visualización de asientos
    PanelBus panelBus;
    ArrayList<PanelBus> paneles;
    PanelCodigoColor panelCodigoColor;
    PanelCambioPiso panelCambioPiso;
    PanelTitulo panelTituloAsientos;
    ComandoCrearComprador informar ;

    /**
     * Constructor de PanelAsientos.
     * Inicializa los subpaneles y los añade al panel principal.
     */
    public PanelAsientos(Comandos avanzar,Comandos retroceder,ComandoCrearComprador compradores) {
        // Establece el color de fondo del panel principal
        setLayout(new GridBagLayout());
        this.setBackground(Color.DARK_GRAY);
        this.informar = compradores;
        OperadorComandos comandoAtras = new OperadorComandos(retroceder);
        comandoAtras.addComando(new ComandoResetear(informar.getList()));
        OperadorComandos comandosAvanzar = new OperadorComandos(informar);
        comandosAvanzar.addComando(avanzar);
        // Inicializa los subpaneles
        BotonAvanzar botonAvanzar = new BotonAvanzar(comandosAvanzar);
        panelCodigoColor = new PanelCodigoColor();
        panelTituloAsientos = new PanelTitulo("Seleccione Asiento",comandoAtras);
        paneles = new ArrayList<>();

        // Añade los subpaneles al panel principal
        this.add(botonAvanzar);
        this.add(panelTituloAsientos);
        this.add(panelCodigoColor);
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
       // panelBus.setPreferredSize(new Dimension(anchoPanelBus, altoPanelBus));
        panelBus.repaint();

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
    public void update(){
        try{
            this.remove(panelBus);
            this.remove(panelCambioPiso);
        }catch(Exception e){}
        try{
            //CardLayout asientos = new CardLayout();
            //panelBus = new JPanel(new GridBagLayout());

            ArrayList<PisoBus> pisos = CalendarioViajes.getInstance().getViaje().getBus().getPisosBus();
            paneles.clear();
            //asientos.addLayoutComponent(new PanelInformacionPasajero(new ComandoRetroceder(panelBus,asientos)),"prueba");
            for (PisoBus piso : pisos){
                System.out.println("AAAA");
                //asientos.addLayoutComponent(new PanelBus(piso),"Piso"+pisos.indexOf(piso));
                //panelBus = new PanelBus(piso);
                paneles.add(new PanelBus(piso,informar));
            }
            //JPanel panelPisos = new JPanel(asientos);
            //panelBus = new JPanel(new GridBagLayout());
            //asientos.first(panelPisos);
            //Comandos subirPiso = new ComandoAvanzar(panelPisos,asientos);
            //Comandos bajarPiso = new ComandoRetroceder(panelPisos,asientos);
            //panelCambioPiso = new PanelCambioPiso(subirPiso,bajarPiso);
            panelBus = paneles.get(0);
            panelCambioPiso = new PanelCambioPiso(this);
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.fill = GridBagConstraints.BOTH; // Permitir que los componentes se expandan
            gbc.weightx = 1.0; // Permitir expansión horizontal
            gbc.weighty = 1.0; // Permitir expansión vertical

            gbc.gridx = 0;
            gbc.gridy = 0;

            //panelBus.add(panelPisos, gbc);
            //this.setLayout(new GridBagLayout());
            this.add(panelBus);
            this.add(panelCambioPiso);
            repaint();
        }catch(NullPointerException e){
            System.out.println("No se encontro el bus" + e.getMessage());
        }
    }
    public ArrayList<PanelBus> getPaneles(){
        return paneles;
    }
    public void setPanelBus(int i){
        this.panelBus = paneles.get(i);
        remove(panelBus);
        add(panelBus);

    }
}