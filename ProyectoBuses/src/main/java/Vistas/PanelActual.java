package Vistas;

import javax.swing.*;
import java.awt.*;

/**
 * PanelActual contiene métodos estáticos para crear diferentes paneles de la interfaz de usuario.
 */
public class PanelActual {

    /**
     * Crea y devuelve un JPanel para seleccionar ruta y fecha.
     *
     * @param panelPrincipal Instancia del panel principal que contiene este panel.
     * @return JPanel para seleccionar ruta y fecha.
     */
    public static JPanel crearPanelSelectorRuta(PanelPrincipal panelPrincipal) {
        JPanel panel = new PanelSelectorRuta();
        panel.setLayout(new BorderLayout());

        // Panel inferior con botón "Siguiente a Horarios"
        JPanel inferior = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton botonSiguiente = new JButton("Siguiente a Horarios");
        botonSiguiente.addActionListener(e -> panelPrincipal.mostrarPanelHorarios());
        inferior.add(botonSiguiente);

        // Añade etiqueta y panel inferior al panel principal
        panel.add(new JLabel("Seleccione Ruta y Fecha"), BorderLayout.NORTH);
        panel.add(inferior, BorderLayout.SOUTH);
        return panel;
    }

    /**
     * Crea y devuelve un JPanel para mostrar los horarios disponibles.
     *
     * @param panelPrincipal Instancia del panel principal que contiene este panel.
     * @return JPanel para mostrar los horarios.
     */
    public static JPanel crearPanelHorarios(PanelPrincipal panelPrincipal) {
        return new PanelHorarios(panelPrincipal);
    }

    /**
     * Crea y devuelve un JPanel para seleccionar asientos.
     *
     * @param panelPrincipal Instancia del panel principal que contiene este panel.
     * @return JPanel para seleccionar asientos.
     */
    public static JPanel crearPanelAsientos(PanelPrincipal panelPrincipal) {
        JPanel panel = new PanelAsientos(panelPrincipal);
        panel.setLayout(new BorderLayout());

        // Panel inferior con botón "Siguiente a Información del Pasajero"
        JPanel inferior = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton botonSiguiente = new JButton("Siguiente a Información del Pasajero");
        botonSiguiente.addActionListener(e -> panelPrincipal.mostrarPanelInformacionPasajero());
        inferior.add(botonSiguiente);

        // Añade etiqueta y panel inferior al panel principal
        panel.add(new JLabel("Seleccione Asientos"), BorderLayout.NORTH);
        panel.add(inferior, BorderLayout.SOUTH);
        return panel;
    }

    /**
     * Crea y devuelve un JPanel para ingresar información del pasajero.
     *
     * @return JPanel para ingresar información del pasajero.
     */
    public static JPanel crearPanelInformacionPasajero(PanelPrincipal panelPrincipal) {
        JPanel panel = new PanelInformacionPasajero(panelPrincipal);
        panel.setLayout(new BorderLayout());

        // Añade etiqueta y panel inferior al panel principal
        panel.add(new JLabel("Ingrese Información del Pasajero"), BorderLayout.NORTH);
        return panel;
    }
}