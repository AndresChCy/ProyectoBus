package Vistas;

import Modelo.Asiento;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Objects;

/**
 * FilaBus es un JPanel que representa una fila de asientos en un bus.
 */
public class FilaBus extends JPanel {
    private final List<Asiento> asientos;
    private int sumaAsientos;

    /**
     * Constructor de la clase FilaBus.
     *
     * @param asientos Lista de tipos de asientos en la fila.
     */
    public FilaBus(List<Asiento> asientos,ComandoCrearComprador informar) {
            this.asientos = asientos;
            this.setLayout(null); // Usa un layout nulo para posicionar manualmente los botones

            calcularSumaAsientos(); // Calcula el número total de asientos para ajustar el ancho de los botones
            agregarBotones(informar); // Agrega botones según los tipos de asientos proporcionados
    }

    /**
     * Calcula el número total de asientos en la fila considerando distintos tipos de asientos.
     * Se utiliza para determinar cómo se dividen los botones en la fila.
     */
    private void calcularSumaAsientos() {
        sumaAsientos = 0;
            for (Asiento asiento : asientos) {
                try {
                    String tipAsiento = asiento.getCategoria();
                    if (Objects.equals(tipAsiento, "Estándar") || Objects.equals(tipAsiento, "Semi Cama")) {
                        sumaAsientos++;
                    } else if (Objects.equals(tipAsiento, "Salón Cama") || Objects.equals(tipAsiento, "Premium")) {
                        sumaAsientos += 2;
                    }

                    }catch(Exception e){sumaAsientos++;}
            }
            if (sumaAsientos == 0) {
                sumaAsientos = 1; // Evita una división por cero si no hay asientos válidos
            }

    }

    /**
     * Agrega botones de asiento a la fila, cada uno posicionado según el tipo de asiento y su cantidad.
     * También marca un asiento como preferencial de forma aleatoria para propósitos de prueba.
     */
    private void agregarBotones(ComandoCrearComprador informar) {
        int posXBoton = 0;
        int anchoBoton = getWidth() / sumaAsientos; // Ancho inicial de cada botón de asiento

        // Itera sobre los tipos de asientos y agrega los botones correspondientes
        for (Asiento value : asientos) {
            String asiento = "";
            BotonAsiento botonAsiento;
            try {
                asiento = value.getCategoria();
                botonAsiento = new BotonAsiento(value, informar);
                this.add(botonAsiento); // Agrega el botón al panel
            } catch (Exception e) {
                botonAsiento = new BotonAsiento(null, informar);
                this.add(botonAsiento);
            }

            // Ajusta la posición y tamaño del botón según el tipo de asiento
            if (asiento.equals("Salón Cama") || asiento.equals("Premium")) {
                botonAsiento.setBounds(posXBoton, 0, anchoBoton * 2, getHeight());
                posXBoton += anchoBoton * 2; // Avanza la posición para el siguiente botón
            } else {
                botonAsiento.setBounds(posXBoton, 0, anchoBoton, getHeight());
                posXBoton += anchoBoton; // Avanza la posición para el siguiente botón
            }
        }
    }

    /**
     * Sobrescribe el método paintComponent para ajustar la posición y tamaño de los botones de asiento al redibujar.
     *
     * @param g Objeto Graphics utilizado para dibujar componentes.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int anchoPanel = getWidth(); // Ancho actual del panel de la fila de asientos
        int altoPanel = getHeight(); // Alto actual del panel de la fila de asientos

        int anchoBoton = anchoPanel / sumaAsientos; // Calcula el ancho de cada botón de asiento
        int posXBoton = 0;

        // Itera sobre los componentes (botones de asiento) dentro del panel
        for (Component c : getComponents()) {
            BotonAsiento botonAsiento = (BotonAsiento) c;
            String tipo = botonAsiento.getTipoAsiento(); // Obtiene el tipo de asiento del botón

            // Ajusta la posición y tamaño del botón según el tipo de asiento
            if (tipo.equals("Salón Cama") || tipo.equals("Premium")) {
                botonAsiento.setBounds(posXBoton, 0, anchoBoton * 2, altoPanel);
                posXBoton += anchoBoton * 2; // Avanza la posición para el siguiente botón
            } else {
                botonAsiento.setBounds(posXBoton, 0, anchoBoton, altoPanel);
                posXBoton += anchoBoton; // Avanza la posición para el siguiente botón
            }
        }
    }
}