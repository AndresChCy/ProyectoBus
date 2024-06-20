package Vistas;

import javax.swing.*;
import java.awt.*;

/**
 * PanelPrecioPagar es un JPanel que muestra el precio a pagar y un botón para realizar la compra.
 * Utiliza fuentes personalizadas para el texto y ajusta la apariencia del mensaje del precio.
 */
public class PanelPrecioPagar extends JPanel {
    private final FuentesPersonalizadas fuentesPersonalizadas; // Objeto para las fuentes personalizadas
    private final String fuente = "Comic Sans MS"; // Fuente utilizada para el mensaje
    private final String mensaje; // Mensaje que muestra el precio
    private final BotonComprar botonComprar; // Botón para realizar la compra

    /**
     * Constructor de PanelPrecioPagar.
     * Configura el panel como transparente, inicializa el mensaje del precio y crea el botón de comprar.
     *
     * @param precio Precio a mostrar en el mensaje
     */
    public PanelPrecioPagar(int precio) {
        this.setOpaque(false); // Establecer el panel como transparente
        this.mensaje = "Precio: $" + precio; // Crear el mensaje con el precio

        botonComprar = new BotonComprar(); // Crear el botón de comprar
        fuentesPersonalizadas = new FuentesPersonalizadas(mensaje, fuente); // Instanciar las fuentes personalizadas

        this.add(botonComprar); // Agregar el botón de comprar al panel
    }

    /**
     * Método sobrescrito para dibujar el contenido personalizado del panel.
     *
     * @param g Objeto Graphics utilizado para dibujar
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Llamar al método paintComponent de la superclase

        int anchoPanel = getWidth(); // Obtener el ancho del panel
        int altoPanel = getHeight(); // Obtener el alto del panel

        // Calcular el tamaño de la fuente basado en las dimensiones actuales del panel
        int tamanoFuente = fuentesPersonalizadas.calcularTamanoLetras(anchoPanel, altoPanel, g);
        g.setFont(new Font(fuente, Font.BOLD, tamanoFuente)); // Establecer la fuente

        FontMetrics fm = g.getFontMetrics(); // Obtener las métricas de la fuente

        // Calcular la posición para dibujar el mensaje
        int posXMensaje = (int) (anchoPanel * 0.02);
        int posYMensaje = (int) (altoPanel * 0.05);

        g.setColor(Color.BLACK); // Establecer el color de dibujo del texto

        // Dibujar el mensaje completo
        g.drawString(mensaje, posXMensaje + 4, posYMensaje + fm.getAscent() + 4);

        // Buscar la posición del símbolo '$' en el mensaje
        int indiceDolar = mensaje.indexOf('$');
        g.setColor(PanelSelectorRuta.temaSeleccionado.colorTerciario);
        if (indiceDolar != -1) {
            // Dibujar la parte antes del símbolo '$'
            g.drawString(mensaje.substring(0, indiceDolar), posXMensaje, posYMensaje + fm.getAscent());

            // Dibujar el símbolo '$' en un color específico
            g.setColor(new Color(27, 101, 64));
            int posXPrecio = posXMensaje + fm.stringWidth(mensaje.substring(0, indiceDolar));
            g.drawString("$", posXPrecio, posYMensaje + fm.getAscent());

            // Dibujar la parte después del símbolo '$' en un color específico
            g.setColor(new Color(27, 101, 64));
            g.drawString(mensaje.substring(indiceDolar + 1), posXPrecio + fm.stringWidth("$"), posYMensaje + fm.getAscent());
        } else {
            // Si no se encuentra el símbolo '$', dibujar el mensaje completo en el color primario del tema
            g.drawString(mensaje, posXMensaje, posYMensaje + fm.getAscent());
        }

        // Calcular la posición y tamaño del botón de comprar
        int posXBoton = (int) (anchoPanel * 0.7);
        int anchoBoton = (int) (anchoPanel * 0.3);
        botonComprar.setBounds(posXBoton, 0, anchoBoton, altoPanel); // Establecer los límites del botón
    }
}