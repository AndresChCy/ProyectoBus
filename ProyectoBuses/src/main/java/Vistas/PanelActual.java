package Vistas;

import javax.swing.*;

public class PanelActual {
    public static JPanel crearPanelSelectorRuta(PanelPrincipal panelPrincipal) {
        JPanel panel = new PanelSelectorRuta();
        JButton botonSiguiente = new JButton("Siguiente a Horarios");
        botonSiguiente.addActionListener(e -> new ComandoMostrarHorarios(panelPrincipal).execute());
        panel.add(new JLabel("Seleccione Ruta y Fecha"));
        panel.add(botonSiguiente);
        return panel;
    }

    public static JPanel crearPanelHorarios(PanelPrincipal panelPrincipal) {
        JPanel panel = new PanelHorarios();
        JButton botonSiguiente = new JButton("Siguiente a Asientos");
        botonSiguiente.addActionListener(e -> new ComandoMostrarAsientos(panelPrincipal).execute());
        panel.add(new JLabel("Horarios Disponibles"));
        panel.add(botonSiguiente);
        return panel;
    }

    public static JPanel crearPanelAsientos(PanelPrincipal panelPrincipal) {
        JPanel panel = new PanelAsientos();
        JButton botonSiguiente = new JButton("Siguiente a Información del Pasajero");
        botonSiguiente.addActionListener(e -> new ComandoMostrarInformacionPasajero(panelPrincipal).execute());
        panel.add(new JLabel("Seleccione Asientos"));
        panel.add(botonSiguiente);
        return panel;
    }

    public static JPanel crearPanelInformacionPasajero(PanelPrincipal panelPrincipal) {
        JPanel panel = new PanelInformacionPasajero();
        JButton botonEnviar = new JButton("Enviar");
        panel.add(new JLabel("Ingrese Información del Pasajero"));
        panel.add(botonEnviar);
        return panel;
    }
}
