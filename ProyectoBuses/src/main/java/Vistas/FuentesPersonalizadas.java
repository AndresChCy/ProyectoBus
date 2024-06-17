package Vistas;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

public class FuentesPersonalizadas {
    private static final double MAX_ANCHO_PORCENTAJE = 0.9;
    private static final double MAX_LARGO_PORCENTAJE = 0.9;
    String mensaje;
    String fuente;
    public FuentesPersonalizadas(String mensaje, String fuente) {
        this.mensaje = mensaje;
        this.fuente = fuente;
    }

    public int calcularTamanoLetras(int ancho, int largo, Graphics g) {
        int tamanoInicial = 10;

        if (g == null) {
            return tamanoInicial;
        }

        FontMetrics fm;
        int tamanoLetras = tamanoInicial;
        boolean esDemasiadoGrande = false;
        String[] lineas = this.mensaje.split("\n");

        do {
            g.setFont(new Font(this.fuente, Font.BOLD, tamanoLetras));
            fm = g.getFontMetrics();

            int maxAnchoLinea = 0;
            for (String linea : lineas) {
                int anchoLinea = fm.stringWidth(linea);
                if (anchoLinea > maxAnchoLinea) {
                    maxAnchoLinea = anchoLinea;
                }
            }

            int totalHeight = fm.getHeight() * lineas.length;

            if (maxAnchoLinea >= ancho * MAX_ANCHO_PORCENTAJE || totalHeight >= largo * MAX_LARGO_PORCENTAJE) {
                esDemasiadoGrande = true;
            } else {
                tamanoLetras++;
            }
        } while (!esDemasiadoGrande);

        return tamanoLetras - 1;
    }
}
