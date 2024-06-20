package Vistas;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

/**
 * FuentesPersonalizadas es una clase utilizada para calcular el tamaño óptimo de la fuente
 * basado en el mensaje y las dimensiones del área disponible.
 */
public class FuentesPersonalizadas {

    // Porcentaje máximo del ancho y largo del área disponible para el mensaje
    private static final double MAX_ANCHO_PORCENTAJE = 0.9;
    private static final double MAX_LARGO_PORCENTAJE = 0.9;

    // Mensaje y fuente utilizada para calcular el tamaño de la fuente
    private final String mensaje;
    private final String fuente;

    /**
     * Constructor de la clase FuentesPersonalizadas.
     *
     * @param mensaje El mensaje para el cual se calculará el tamaño de la fuente.
     * @param fuente  La fuente utilizada para mostrar el mensaje.
     */
    public FuentesPersonalizadas(String mensaje, String fuente) {
        this.mensaje = mensaje;
        this.fuente = fuente;
    }

    /**
     * Calcula el tamaño óptimo de la fuente que se adapte al ancho y largo dados del área disponible.
     *
     * @param ancho Ancho del área disponible para mostrar el mensaje.
     * @param largo Largo del área disponible para mostrar el mensaje.
     * @param g     Objeto Graphics utilizado para obtener la métrica de la fuente.
     * @return El tamaño óptimo de la fuente calculado.
     */
    public int calcularTamanoLetras(int ancho, int largo, Graphics g) {
        int tamanoInicial = 10; // Tamaño inicial de la fuente

        if (g == null) {
            return tamanoInicial;
        }

        FontMetrics fm;
        int tamanoLetras = tamanoInicial;
        boolean esDemasiadoGrande = false;
        String[] lineas = this.mensaje.split("\n"); // Divide el mensaje en líneas si hay saltos de línea

        do {
            g.setFont(new Font(this.fuente, Font.BOLD, tamanoLetras)); // Establece la fuente con el tamaño actual
            fm = g.getFontMetrics(); // Obtiene las métricas de la fuente actual

            int maxAnchoLinea = 0;
            for (String linea : lineas) {
                int anchoLinea = fm.stringWidth(linea); // Calcula el ancho de cada línea del mensaje
                if (anchoLinea > maxAnchoLinea) {
                    maxAnchoLinea = anchoLinea; // Encuentra el ancho máximo entre todas las líneas
                }
            }

            int totalHeight = fm.getHeight() * lineas.length; // Calcula la altura total del mensaje

            // Verifica si el tamaño actual de la fuente es demasiado grande para el área disponible
            if (maxAnchoLinea >= ancho * MAX_ANCHO_PORCENTAJE || totalHeight >= largo * MAX_LARGO_PORCENTAJE) {
                esDemasiadoGrande = true; // Si es demasiado grande, se detiene el bucle
            } else {
                tamanoLetras++; // Si no es demasiado grande, prueba con un tamaño de fuente mayor
            }
        } while (!esDemasiadoGrande);

        return tamanoLetras - 1; // Retorna el tamaño de la fuente calculado (se resta 1 porque el bucle incrementa el tamaño justo antes de verificar)
    }
}