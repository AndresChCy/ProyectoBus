package Vistas;

import javax.swing.*;
import java.awt.*;

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

        // Crear los paneles individuales
        JPanel panelSelectorRuta = PanelActual.crearPanelSelectorRuta(this);
        JPanel panelHorarios = PanelActual.crearPanelHorarios(this);
        JPanel panelAsientos = PanelActual.crearPanelAsientos(this);
        JPanel panelInformacionPasajero = PanelActual.crearPanelInformacionPasajero();

        // Agregar los paneles al panelActual con sus respectivos nombres
        panelActual.add(panelSelectorRuta, "Selector de Ruta");
        panelActual.add(panelHorarios, "Horarios de Salida");
        panelActual.add(panelAsientos, "Asientos");
        panelActual.add(panelInformacionPasajero, "Información del pasajero");

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