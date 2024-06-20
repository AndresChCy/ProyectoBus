package Vistas;

import javax.swing.*;
import java.awt.*;

/**
 * El PanelInfoBus representa un panel que muestra información específica de un bus,
 * incluyendo el horario de salida y el precio mínimo, junto con un botón para comprar.
 */
public class PanelInfoBus extends JPanel {
    private final String horario; // Horario de salida del bus
    private final int precioDesde; // Precio mínimo desde donde comienza
    private final FuentesPersonalizadas mensajeHora; // Mensaje personalizado para el horario
    private final FuentesPersonalizadas mensajeAsiento; // Mensaje personalizado para el precio
    private final String fuente = "Roboto"; // Fuente utilizada en los mensajes
    private final JButton botonComprar; // Botón para realizar la compra

    /**
     * Constructor del PanelInfoBus.
     *
     * @param horario       Horario de salida del bus
     * @param precioMin     Precio mínimo desde donde comienza
     * @param panelPrincipal Instancia del PanelPrincipal para realizar acciones
     * @param color         Color de fondo del panel
     */
    public PanelInfoBus(String horario, int precioMin, PanelPrincipal panelPrincipal, Color color) {
        this.horario = horario;
        this.precioDesde = precioMin;
        this.setBorder(BorderFactory.createLineBorder(Color.WHITE)); // Borde blanco para separación visual
        mensajeHora = new FuentesPersonalizadas("Salida: " + horario, fuente); // Crear mensaje personalizado para el horario
        mensajeAsiento = new FuentesPersonalizadas("Desde: $" + precioMin, fuente); // Crear mensaje personalizado para el precio

        String comprar = "COMPRAR";
        botonComprar = new JButton(comprar); // Crear el botón de compra
        botonComprar.addActionListener(e -> panelPrincipal.mostrarPanelAsientos()); // Asociar acción de mostrar asientos al hacer clic
        botonComprar.setBackground(PanelSelectorRuta.temaSeleccionado.colorSecundario); // Color de fondo del botón
        botonComprar.setForeground(Color.BLACK); // Color del texto del botón
        this.setLayout(null); // Usar layout nulo para posicionar componentes manualmente
        this.add(botonComprar); // Agregar el botón al panel

        this.setBackground(color); // Establecer color de fondo del panel
    }

    /**
     * Método sobrescrito para dibujar el contenido personalizado del panel.
     *
     * @param g Objeto Graphics utilizado para dibujar
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Llamar al método paintComponent de la superclase JPanel

        int anchoPanel = getWidth(); // Obtener el ancho del panel
        int altoPanel = getHeight(); // Obtener el alto del panel

        // Calcular dimensiones y posiciones para el mensaje de horario
        int anchoInfo = (int) (anchoPanel * 0.35);
        int anchoMaxMensaje = (int) (anchoInfo * 0.90);
        int margenMensaje = (int) (altoPanel * 0.05);
        int altoMensaje = (int) (altoPanel * 0.9);

        // Dibujar el mensaje de horario
        int tamanoTextoHora = mensajeHora.calcularTamanoLetras(anchoMaxMensaje, altoMensaje, g);
        FontMetrics fm1 = g.getFontMetrics();
        g.setFont(new Font(fuente, Font.BOLD, tamanoTextoHora));
        g.setColor(Color.BLACK);
        g.drawString("Salida: " + horario, margenMensaje * 2 + 2, margenMensaje + fm1.getAscent() + 2);
        g.setColor(PanelSelectorRuta.temaSeleccionado.colorSecundario);
        g.drawString("Salida: " + horario, margenMensaje * 2, margenMensaje + fm1.getAscent());

        // Dibujar separador vertical
        int xSeparador = margenMensaje + anchoInfo;
        g.setColor(Color.GRAY);
        g.drawLine(xSeparador, margenMensaje, xSeparador, altoPanel - margenMensaje);

        // Dibujar el mensaje de precio mínimo
        int tamanoMensajeAsiento = mensajeAsiento.calcularTamanoLetras(anchoMaxMensaje, altoMensaje, g);
        FontMetrics fm2 = g.getFontMetrics();
        g.setFont(new Font(fuente, Font.BOLD, tamanoMensajeAsiento));
        g.setColor(Color.BLACK);
        g.drawString("Desde: $" + precioDesde, margenMensaje * 2 + xSeparador + 2, margenMensaje + fm2.getAscent() + 2);
        g.setColor(PanelSelectorRuta.temaSeleccionado.colorSecundario);
        g.drawString("Desde: $" + precioDesde, margenMensaje * 2 + xSeparador, margenMensaje + fm2.getAscent());

        // Ajustar tamaño y posición del botón de compra
        int anchoBotonComprar = (int) (anchoPanel * 0.2); // Reducir el ancho del botón
        botonComprar.setBounds(anchoPanel - margenMensaje - anchoBotonComprar, margenMensaje, anchoBotonComprar, altoPanel - 2 * margenMensaje);
        botonComprar.setFont(new Font(fuente, Font.BOLD, tamanoTextoHora)); // Establecer fuente del botón
    }
}