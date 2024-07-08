package Vistas;

import javax.swing.*;
import java.awt.*;

public class Selector extends JPanel {
    private final SeleccionadorElementos seleccionador;
    private final FuentesPersonalizadas fp;
    private final String fuente = "Broadway";
    private final String mensaje ;
    private final JPanel panelTitulo;

    /**
     * Constructor de la clase SelectorDestino.
     */
    public Selector(String titulo,String[] elementos) {
        this.mensaje = titulo;
        // Configurar el layout del panel principal como BorderLayout
        setLayout(new BorderLayout());

        // Establecer el fondo del panel como transparente
        setOpaque(false);

        // Inicializar fuentes personalizadas
        fp = new FuentesPersonalizadas(mensaje, fuente);

        // Crear el selector de ciudad con la lista de ciudades
        seleccionador = new SeleccionadorElementos(elementos);

        // Crear y configurar el panel para el título
        panelTitulo = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                int anchoPanel = getWidth();
                int altoPanel = getHeight();

                // Calcular tamaño de fuente basado en el tamaño del panel
                int tamanoFuente = fp.calcularTamanoLetras(anchoPanel, altoPanel, g);
                g.setFont(new Font(fuente, Font.BOLD, tamanoFuente));
                FontMetrics fm = g.getFontMetrics();

                // Centrar el mensaje verticalmente en el panel
                int anchoMensaje = fm.stringWidth(mensaje);
                int posXMensaje = (anchoPanel - anchoMensaje) / 2;
                int posYMensaje = (altoPanel + fm.getAscent()) / 2; // Centrar verticalmente
                g.setColor(Temas.temaSeleccionado.colorPrimario);
                g.drawString(mensaje, posXMensaje + 2, posYMensaje + 2);
                g.setColor(Temas.temaSeleccionado.colorTerciario);
                g.drawString(mensaje, posXMensaje, posYMensaje);
            }
        };

        // Establecer el fondo del panel de título como transparente
        panelTitulo.setOpaque(false);

        // Agregar el panel de título al centro del BorderLayout
        add(panelTitulo, BorderLayout.CENTER);

        // Agregar el selector de ciudad al sur del BorderLayout
        add(seleccionador, BorderLayout.SOUTH);
    }

    /**
     * Método sobrescrito para dibujar el componente.
     * Ajusta la posición y tamaño del panel de título y del selector de ciudad.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int anchoPanel = getWidth();
        int altoPanel = getHeight();

        // Ajustar posición y tamaño del panel de título y del selector de ciudad
        int altoSelCiudad = (int) (altoPanel * 0.5);
        panelTitulo.setBounds(0, 0, anchoPanel, altoSelCiudad);
        seleccionador.setBounds(0, altoSelCiudad, anchoPanel, altoSelCiudad);
    }
    public JComboBox<String> getComboBox(){
        return seleccionador.getComboBox();
    }
}