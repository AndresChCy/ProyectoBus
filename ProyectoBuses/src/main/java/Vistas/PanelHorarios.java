package Vistas;

import Modelo.CalendarioObserver;
import Modelo.CalendarioViajes;
import Modelo.ViajeBus;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * PanelHorarios es un JPanel personalizado que muestra horarios disponibles,
 * un título y un botón para retroceder. Utiliza fuentes personalizadas y colores
 * del tema seleccionado.
 */
public class PanelHorarios extends JPanel implements CalendarioObserver,TemasObserver {
    private final PanelTitulo panelTitulo;
   // private final BotonRetroceder botonRetroceder;
    private final PanelHorariosDisponibles panelHorariosDisponibles;

    /**
     * Constructor de PanelHorarios.
     *
     * @ Referencia al panel principal que contiene este panel.
     */
    public PanelHorarios(Comandos retroceder,Comandos avanzar) {
        setLayout(null); // Establece el layout a nulo para poder posicionar los componentes manualmente
        setBackground(Temas.temaSeleccionado.colorPrimario); // Establece el color de fondo del panel
        OperadorComandos comandoAtras = new OperadorComandos(retroceder);
        OperadorComandos comandosAvanzar = new OperadorComandos(avanzar);
        // Creación del panel de título
        panelTitulo = new PanelTitulo("Horarios Disponibles",comandoAtras);

        // Creación del botón para retroceder
       // botonRetroceder = new BotonRetroceder(panelPrincipal);
        //botonRetroceder.setBounds(10, 10, 30, 30);

        // Creación del panel de horarios disponibles
        panelHorariosDisponibles = new PanelHorariosDisponibles(avanzar);
        panelHorariosDisponibles.setBounds(0, (int) (getHeight() * 0.3), getWidth(), (int) (getHeight() * 0.7));

        // Agregar componentes al panel principal
        add(panelTitulo);
        add(panelHorariosDisponibles);
        //add(botonRetroceder);
    }

    /**
     * Crea y configura el panel de horarios disponibles.
     *
     * Referencia al panel principal que contiene este panel.
     * @return El panel de horarios disponibles configurado.
     */

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int anchoPanel = getWidth();
        int altoPanel = getHeight();
        setBackground(Temas.temaSeleccionado.colorPrimario);

        // Actualizar el tamaño y posición del panel de horarios disponibles
        panelHorariosDisponibles.setBounds(0, (int) (altoPanel * 0.3), anchoPanel, (int) (altoPanel * 0.7));

        // Calcular posición y tamaño del botón para retroceder
        //int margenBoton = (int) (altoPanel * 0.1 * 0.3);
        //int medidaBoton = (int) (altoPanel * 0.3 * 0.3);
        //int margenTitulo = 2 * margenBoton + medidaBoton;

        // Obtener y ajustar el panel de título y el botón para retroceder
        //JPanel panelTitulo = (JPanel) getComponent(0);
        //panelTitulo.setBounds(margenTitulo, 0, anchoPanel - 2 * margenTitulo, (int) (altoPanel * 0.3));
        // Altura del título en relación al tamaño del panel principal
        int altoTitulo = (int) (altoPanel * 0.1);

        // Posiciona y dimensiona el panel de título de asientos
        panelTitulo.setBounds(0, 0, anchoPanel, altoTitulo);
        //botonRetroceder.setBounds(margenBoton, margenBoton, medidaBoton, medidaBoton);
    }
    public void update(){
        panelHorariosDisponibles.update();
    }
    public void updateTema(){
        panelHorariosDisponibles.updateTemas();
    }
}