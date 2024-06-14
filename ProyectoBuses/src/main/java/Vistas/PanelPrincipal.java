package Vistas;

import javax.swing.*;
import java.awt.*;

public class PanelPrincipal extends JPanel {
    private CardLayout paneles;
    private JPanel panelActual;

    public PanelPrincipal() {
        paneles = new CardLayout();
        panelActual = new JPanel(paneles);

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH; // Permitir que los componentes se expandan
        gbc.weightx = 1.0; // Permitir expansión horizontal
        gbc.weighty = 1.0; // Permitir expansión vertical

        // Establecer el color de fondo del panel principal como negro
        this.setBackground(Color.BLACK);

        // Crear los paneles
        JPanel panelSelectorRuta = PanelActual.crearPanelSelectorRuta(this);
        JPanel panelHorarios = PanelActual.crearPanelHorarios(this);
        JPanel panelAsientos = PanelActual.crearPanelAsientos(this);
        JPanel panelInformacionPasajero = PanelActual.crearPanelInformacionPasajero(this);

        // Agregar paneles a panelActual con su respectivo nombre
        panelActual.add(panelSelectorRuta, "Selector de Ruta");
        panelActual.add(panelHorarios, "Horarios de Salida");
        panelActual.add(panelAsientos, "Asientos");
        panelActual.add(panelInformacionPasajero, "Información del pasajero");

        add(panelActual);

        // Agregar el panelActual al centro del PanelPrincipal
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(panelActual, gbc);
    }

    // Métodos para cambiar las tarjetas
    public void mostrarPanelHorarios() {
        paneles.show(panelActual, "Horarios de Salida");
    }

    public void mostrarPanelAsientos() {
        paneles.show(panelActual, "Asientos");
    }

    public void mostrarPanelInformacionPasajero() {
        paneles.show(panelActual, "Información del pasajero");
    }
}
