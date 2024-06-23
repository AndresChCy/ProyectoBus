package Vistas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * PanelBus es un JPanel que muestra filas de asientos de diferentes tipos de buses.
 */
public class PanelBus extends JPanel {

    final static int MIN_COLUMNAS = 8;
    final static int MAX_COLUMNAS = 15;
    String[] busesTest; // Array para probar diferentes tipos de asientos
    int sumaAsientos; // Suma total de asientos en el panel
    int columnas; // Número de columnas basado en la suma de asientos
    List<FilaBus> filaBuses; // Lista de filas de buses

    /**
     * Constructor de PanelBus.
     * Inicializa el panel y configura las filas de asientos.
     */
    public PanelBus() {
        this.setOpaque(false); // Panel transparente
        this.setLayout(null); // Layout nulo para posicionar manualmente los componentes
        filaBuses = new ArrayList<>(); // Inicializa la lista de filas de buses

        // Ejemplo de tipos de asientos en un arreglo para probar
        busesTest = new String[]{"Premium", "Salón Cama", "Salón Cama", "Vacío", "Semi Cama", "Semi Cama", "Vacío",
                "Estándar", "Estándar", "Estándar", "Estándar", "Estándar"};
        calcularSumaAsientos(); // Calcula la suma total de asientos
        int totalFilas = 5; // Número total de filas
        int filaPasillo = totalFilas / 2; // Fila que contendrá solo "Vacío" como pasillo

        // Creación de las filas de buses
        for (int i = 0; i < totalFilas; i++) {
            List<String> filaAsientos;
            if (i == filaPasillo) {
                // Fila de pasillo
                String[] filaBusPasillo = new String[columnas];
                Arrays.fill(filaBusPasillo, "Vacío");
                filaAsientos = Arrays.asList(filaBusPasillo);
            } else {
                // Filas normales con tipos de asientos
                filaAsientos = Arrays.asList(busesTest);
            }
            FilaBus filaBus = new FilaBus(filaAsientos); // Crea una nueva fila de bus con los asientos correspondientes
            filaBuses.add(filaBus); // Agrega la fila de bus a la lista
            this.add(filaBus); // Agrega la fila de bus al panel
        }

        // Redibujar al cambiar el tamaño del panel
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                repaint(); // Vuelve a pintar el panel al cambiar el tamaño
            }
        });
    }

    /**
     * Calcula la suma total de asientos basados en el arreglo de tipos de asientos.
     * Actualiza la variable 'columnas' para ajustar el número de columnas en el panel.
     */
    private void calcularSumaAsientos() {
        sumaAsientos = 0;
        for (String asiento : busesTest) {
            if (Objects.equals(asiento, "Estándar") || Objects.equals(asiento, "Semi Cama") || Objects.equals(asiento, "Vacío")) {
                sumaAsientos++;
            } else if (Objects.equals(asiento, "Salón Cama") || Objects.equals(asiento, "Premium")) {
                sumaAsientos += 2;
            }
        }
        columnas = Math.min(MAX_COLUMNAS, Math.max(MIN_COLUMNAS, sumaAsientos)); // Limita las columnas entre MIN_COLUMNAS y MAX_COLUMNAS
    }

    /**
     * Sobrescribe el método paintComponent para ajustar y pintar las filas de buses en el panel.
     *
     * @param g Objeto Graphics para pintar los componentes.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int anchoPanel = getWidth(); // Ancho actual del panel
        int altoPanel = getHeight(); // Alto actual del panel

        int anchoCuadro = anchoPanel / columnas; // Ancho de cada cuadro (asiento)

        int totalAltoBus = anchoCuadro * filaBuses.size(); // Alto total ocupado por todas las filas de buses
        int offsetY = (altoPanel - totalAltoBus) / 2; // Offset vertical para centrar las filas de buses en el panel

        // Posiciona y ajusta cada fila de buses en el panel
        for (int i = 0; i < filaBuses.size(); i++) {
            FilaBus filaActual = filaBuses.get(i); // Obtiene la fila de bus actual
            filaActual.setBounds(0, offsetY + i * anchoCuadro, anchoCuadro * columnas, anchoCuadro); // Establece la posición y tamaño de la fila
        }
    }
}