package Vistas;

import Modelo.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class PanelPrincipal extends JPanel {
    private final CardLayout paneles;
    private final JPanel panelActual;

    /**
     * Constructor de la clase PanelPrincipal.
     * Configura el layout y añade los paneles al panel principal.
     */
    public PanelPrincipal() {
        // Inicializar el CardLayout y el panel que lo contendrá
        paneles = new CardLayout();
        panelActual = new JPanel(paneles);

        // Configurar el layout del PanelPrincipal
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH; // Permitir que los componentes se expandan
        gbc.weightx = 1.0; // Permitir expansión horizontal
        gbc.weighty = 1.0; // Permitir expansión vertical

        // Establecer el color de fondo del panel principal como negro
        this.setBackground(Color.BLACK);

        //Crear los comandos necesarios
        Comandos avanzar = new ComandoAvanzar(panelActual,paneles);
        Comandos retroceder = new ComandoRetroceder(panelActual,paneles);
        ComandoCrearComprador informacion = new ComandoCrearComprador(panelActual);

        Temas tema = new Temas();

        // Crear los paneles individuales
        JPanel panelSelectorRuta = new PanelSelectorRuta(avanzar,tema);
        JPanel panelHorarios = new PanelHorarios(retroceder,avanzar);
        JPanel panelAsientos = new PanelAsientos(avanzar,retroceder,informacion);
        //JPanel panelInformacionPasajero = new PanelInformacionPasajero(retroceder);

        CalendarioViajes.getInstance().suscribir((CalendarioObserver) panelHorarios);
        CalendarioViajes.getInstance().suscribir((CalendarioObserver) panelAsientos);

        tema.suscribir((TemasObserver)panelSelectorRuta);
        tema.suscribir((TemasObserver)panelHorarios );
        tema.suscribir((TemasObserver) panelAsientos);

        PisoBus piso = new PisoBus(6);
        piso.addAsiento(1,1,new Estandar(1));

        // Agregar los paneles al panelActual con sus respectivos nombres
        panelActual.add(panelSelectorRuta, "Selector de Ruta");
        //panelActual.add(new PanelBus(piso),"prueba");
        panelActual.add(panelHorarios, "Horarios de Salida");
        panelActual.add(panelAsientos, "Asientos");
        //panelActual.add(panelInformacionPasajero, "Información del pasajero");
        panelActual.setBackground(Color.BLACK);
        // Agregar el panelActual al centro del PanelPrincipal usando GridBagConstraints
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(panelActual, gbc);

    }


    /**
     * Método para mostrar el panel "Selector de Ruta".
     * Utiliza CardLayout para cambiar la vista.
     */
    public void mostrarPanelSelectorRuta() {
        paneles.show(panelActual, "Selector de Ruta");
    }

    /**
     * Método para mostrar el panel "Horarios de Salida".
     * Utiliza CardLayout para cambiar la vista.
     */
    public void mostrarPanelHorarios() {
        paneles.show(panelActual, "Horarios de Salida");
    }

    /**
     * Método para mostrar el panel "Asientos".
     * Utiliza CardLayout para cambiar la vista.
     */
    public void mostrarPanelAsientos() {
        paneles.show(panelActual, "Asientos");
    }

    /**
     * Método para mostrar el panel "Información del pasajero".
     * Utiliza CardLayout para cambiar la vista.
     */
    public void mostrarPanelInformacionPasajero() {
        paneles.show(panelActual, "Información del pasajero");
    }
}