package Vistas;

import javax.swing.*;
import java.awt.*;

public class PanelActual {
    public static JPanel crearPanelSelectorRuta(PanelPrincipal panelPrincipal) {
        JPanel panel = new PanelSelectorRuta();
        panel.setLayout(new BorderLayout());

        JPanel inferior = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton botonSiguiente = new JButton("Siguiente a Horarios");
        botonSiguiente.addActionListener(e -> panelPrincipal.mostrarPanelHorarios());
        inferior.add(botonSiguiente);

        panel.add(new JLabel("Seleccione Ruta y Fecha"), BorderLayout.NORTH);
        panel.add(inferior, BorderLayout.SOUTH);
        return panel;
    }

    public static JPanel crearPanelHorarios(PanelPrincipal panelPrincipal) {
        PanelHorarios panelHorarios = new PanelHorarios();
        return panelHorarios;
    }

    public static JPanel crearPanelAsientos(PanelPrincipal panelPrincipal) {
        JPanel panel = new PanelAsientos();
        panel.setLayout(new BorderLayout());

        JPanel inferior = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton botonSiguiente = new JButton("Siguiente a Información del Pasajero");
        botonSiguiente.addActionListener(e -> panelPrincipal.mostrarPanelInformacionPasajero());
        inferior.add(botonSiguiente);

        panel.add(new JLabel("Seleccione Asientos"), BorderLayout.NORTH);
        panel.add(inferior, BorderLayout.SOUTH);
        return panel;
    }

    public static JPanel crearPanelInformacionPasajero(PanelPrincipal panelPrincipal) {
        JPanel panel = new PanelInformacionPasajero();
        panel.setLayout(new BorderLayout());

        JPanel inferior = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton botonEnviar = new JButton("Enviar");
        inferior.add(botonEnviar);

        panel.add(new JLabel("Ingrese Información del Pasajero"), BorderLayout.NORTH);
        panel.add(inferior, BorderLayout.SOUTH);
        return panel;
    }
}
