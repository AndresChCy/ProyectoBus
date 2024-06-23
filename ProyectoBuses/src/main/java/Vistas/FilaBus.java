package Vistas;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Objects;
import java.util.Random; // Solo para pruebas

/**
 * FilaBus es un JPanel que representa una fila de asientos en un bus.
 */
public class FilaBus extends JPanel {
    private final List<String> asientos;
    private int sumaAsientos;

    /**
     * Constructor de la clase FilaBus.
     *
     * @param asientos Lista de tipos de asientos en la fila.
     */
    public FilaBus(List<String> asientos) {
        this.asientos = asientos;
        this.setLayout(null); // Usa un layout nulo para posicionar manualmente los botones

        calcularSumaAsientos(); // Calcula el número total de asientos para ajustar el ancho de los botones
        agregarBotones(); // Agrega botones según los tipos de asientos proporcionados
    }

    /**
     * Calcula el número total de asientos en la fila considerando distintos tipos de asientos.
     * Se utiliza para determinar cómo se dividen los botones en la fila.
     */
    private void calcularSumaAsientos() {
        sumaAsientos = 0;
        for (String asiento : asientos) {
            if (Objects.equals(asiento, "Estándar") || Objects.equals(asiento, "Semi Cama") || Objects.equals(asiento, "Vacío")) {
                sumaAsientos++;
            } else if (Objects.equals(asiento, "Salón Cama") || Objects.equals(asiento, "Premium")) {
                sumaAsientos += 2;
            }
        }
        if (sumaAsientos == 0) {
            sumaAsientos = 1; // Evita una división por cero si no hay asientos válidos
        }
    }

    /**
     * Crea un nuevo botón de asiento según el tipo de asiento especificado.
     *
     * @param tipo Tipo de asiento para crear el botón.
     * @return El botón de asiento creado.
     */
    private BotonAsiento crearBotonAsiento(String tipo) {
        return new BotonAsiento(tipo);
    }

    /**
     * Agrega botones de asiento a la fila, cada uno posicionado según el tipo de asiento y su cantidad.
     * También marca un asiento como preferencial de forma aleatoria para propósitos de prueba.
     */
    private void agregarBotones() {
        int posXBoton = 0;
        int anchoBoton = getWidth() / sumaAsientos; // Ancho inicial de cada botón de asiento

        // Genera un índice aleatorio para el botón preferencial para propósitos de prueba
        Random random = new Random();
        int indicePreferencial = random.nextInt(asientos.size());

        // Itera sobre los tipos de asientos y agrega los botones correspondientes
        for (int i = 0; i < asientos.size(); i++) {
            String asiento = asientos.get(i);
            BotonAsiento botonAsiento = crearBotonAsiento(asiento);
            this.add(botonAsiento); // Agrega el botón al panel

            // Marca el asiento como preferencial si es el índice aleatorio
            if (i == indicePreferencial) {
                botonAsiento.setPreferencial(true);
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