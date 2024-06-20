package Vistas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class PanelBus extends JPanel {
    final static int MIN_COLUMNAS = 8;
    final static int MAX_COLUMNAS = 15;
    String[] busesTest;
    int sumaAsientos;
    int columnas;
    List<FilaBus> filaBuses;

    public PanelBus() {
        this.setOpaque(false);
        this.setLayout(null);
        filaBuses = new ArrayList<>();

        busesTest = new String[]{"Premium", "Salón Cama", "Salón Cama", "Vacío", "Semi Cama", "Semi Cama", "Vacío",
                "Estándar", "Estándar", "Estándar", "Estándar", "Estándar"}; // Para probar
        calcularSumaAsientos();
        int totalFilas = 5;
        int filaPasillo = totalFilas / 2;

        for (int i = 0; i < totalFilas; i++) {
            List<String> filaAsientos;
            if (i == filaPasillo) {
                String[] filaBusPasillo = new String[columnas];
                Arrays.fill(filaBusPasillo, "Vacío");
                filaAsientos = Arrays.asList(filaBusPasillo);
            } else {
                filaAsientos = Arrays.asList(busesTest);
            }
            FilaBus filaBus = new FilaBus(filaAsientos);
            filaBuses.add(filaBus);
            this.add(filaBus);
        }

        // Redibujar al cambiar el tamaño del panel
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                repaint();
            }
        });
    }

    private void calcularSumaAsientos() {
        sumaAsientos = 0;
        for (String asiento : busesTest) {
            if (Objects.equals(asiento, "Estándar") || Objects.equals(asiento, "Semi Cama") || Objects.equals(asiento, "Vacío")) {
                sumaAsientos++;
            } else if (Objects.equals(asiento, "Salón Cama") || Objects.equals(asiento, "Premium")) {
                sumaAsientos += 2;
            }
        }
        columnas = Math.min(MAX_COLUMNAS, Math.max(MIN_COLUMNAS, sumaAsientos));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int anchoPanel = getWidth();
        int altoPanel = getHeight();

        int anchoCuadro = anchoPanel / columnas;
        int altoFila = anchoCuadro;

        int totalAltoBus = altoFila * filaBuses.size();
        int offsetY = (altoPanel - totalAltoBus) / 2;

        for (int i = 0; i < filaBuses.size(); i++) {
            FilaBus filaActual = filaBuses.get(i);
            filaActual.setBounds(0, offsetY + i * altoFila, anchoCuadro * columnas, altoFila);
        }
    }
}
