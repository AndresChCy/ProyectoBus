package Vistas;

import Modelo.CalendarioObserver;
import Modelo.CalendarioViajes;
import Modelo.Estandar;
import Modelo.PisoBus;

import javax.swing.*;
import java.awt.*;


public class PanelPrincipal extends JPanel {

    /**
     * Constructor de la clase PanelPrincipal.
     * Configura el layout y añade los paneles al panel principal.
     */
    public PanelPrincipal() {
        // Inicializar el CardLayout y el panel que lo contendrá
        CardLayout paneles = new CardLayout();
        JPanel panelActual = new JPanel(paneles);

        // Configurar el layout del PanelPrincipal
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH; // Permitir que los componentes se expandan
        gbc.weightx = 1.0; // Permitir expansión horizontal
        gbc.weighty = 1.0; // Permitir expansión vertical

        // Establecer el color de fondo del panel principal como negro
        this.setBackground(Color.BLACK);

        //Crear los comandos necesarios
        Comandos avanzar = new ComandoAvanzar(panelActual, paneles);
        Comandos retroceder = new ComandoRetroceder(panelActual, paneles);
        ComandoCrearComprador informacion = new ComandoCrearComprador(panelActual);

        Temas tema = new Temas();

        // Crear los paneles individuales
        JPanel panelSelectorRuta = new PanelSelectorRuta(avanzar,tema);
        JPanel panelHorarios = new PanelHorarios(retroceder,avanzar);
        JPanel panelAsientos = new PanelAsientos(avanzar,retroceder,informacion);

        CalendarioViajes.getInstance().suscribir((CalendarioObserver) panelHorarios);
        CalendarioViajes.getInstance().suscribir((CalendarioObserver) panelAsientos);

        tema.suscribir((TemasObserver)panelSelectorRuta);
        tema.suscribir((TemasObserver)panelHorarios );
        tema.suscribir((TemasObserver) panelAsientos);

        PisoBus piso = new PisoBus(6);
        piso.addAsiento(1,1,new Estandar(1));

        // Agregar los paneles al panelActual con sus respectivos nombres
        panelActual.add(panelSelectorRuta, "Selector de Ruta");
        panelActual.add(panelHorarios, "Horarios de Salida");
        panelActual.add(panelAsientos, "Asientos");
        panelActual.setBackground(Color.BLACK);
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(panelActual, gbc);

    }
}