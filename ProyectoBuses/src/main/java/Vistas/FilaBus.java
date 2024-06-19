package Vistas;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Objects;
import java.util.Random; // Solo para probar

public class FilaBus extends JPanel {
    private List<String> asientos;
    private int sumaAsientos;

    public FilaBus(List<String> asientos) {
        this.asientos = asientos;
        this.setLayout(null);

        calcularSumaAsientos();
        agregarBotones();
    }

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
            sumaAsientos = 1; // Evitar división por cero
        }
    }

    private BotonAsiento crearBotonAsiento(String tipo) {
        return new BotonAsiento(tipo);
    }

    private void agregarBotones() {
        int posXBoton = 0;
        int anchoBoton = getWidth() / sumaAsientos;

        // Generar un índice aleatorio para el botón preferencial para probar
        Random random = new Random();
        int indicePreferencial = random.nextInt(asientos.size());

        for (int i = 0; i < asientos.size(); i++) {
            String asiento = asientos.get(i);
            BotonAsiento botonAsiento = crearBotonAsiento(asiento);
            this.add(botonAsiento);

            if (i == indicePreferencial) {
                botonAsiento.setPreferencial(true);
            }

            if (asiento.equals("Salón Cama") || asiento.equals("Premium")) {
                botonAsiento.setBounds(posXBoton, 0, anchoBoton * 2, getHeight());
                posXBoton += anchoBoton * 2;
            } else {
                botonAsiento.setBounds(posXBoton, 0, anchoBoton, getHeight());
                posXBoton += anchoBoton;
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int anchoPanel = getWidth();
        int altoPanel = getHeight();

        int anchoBoton = anchoPanel / sumaAsientos;
        int posXBoton = 0;

        for (Component c : getComponents()) {
            BotonAsiento botonAsiento = (BotonAsiento) c;
            String tipo = botonAsiento.getTipoAsiento();

            if (tipo.equals("Salón Cama") || tipo.equals("Premium")) {
                botonAsiento.setBounds(posXBoton, 0, anchoBoton * 2, altoPanel);
                posXBoton += anchoBoton * 2;
            } else {
                botonAsiento.setBounds(posXBoton, 0, anchoBoton, altoPanel);
                posXBoton += anchoBoton;
            }
        }
    }
}
