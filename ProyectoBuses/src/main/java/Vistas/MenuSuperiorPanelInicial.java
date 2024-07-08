package Vistas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * MenuSuperiorPanelInicial es un JPanel que muestra un mensaje y un botón de configuraciones en la parte superior.
 */
public class MenuSuperiorPanelInicial extends JPanel {

    // Componentes del panel
    private final BotonConfiguraciones botonConfiguraciones;
    private final FuentesPersonalizadas fuentesPersonalizadas;

    // Mensaje y fuente utilizados
    private final String mensaje = "Planea tu Próximo Viaje con Nosotros";
    private final String fuente = "Century Gothic";

    /**
     * Constructor de MenuSuperiorPanelInicial.
     * Inicializa los componentes y configura el tema seleccionado.
     */
    public MenuSuperiorPanelInicial(PanelConfiguracion panelConfiguracion) {

        this.setBackground(Temas.temaSeleccionado.colorPrimario); // Establece el color de fondo basado en el tema seleccionado

        // Inicializa el botón de configuraciones y las fuentes personalizadas
        botonConfiguraciones = new BotonConfiguraciones();
        fuentesPersonalizadas = new FuentesPersonalizadas(mensaje, fuente);

        botonConfiguraciones.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelConfiguracion.open();
            }
        });

        // Agrega el botón de configuraciones al panel
        this.add(botonConfiguraciones);
    }

    /**
     * Sobrescribe el método paintComponent para personalizar la apariencia del panel.
     *
     * @param g Objeto Graphics para dibujar los componentes.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        this.setBackground(Temas.temaSeleccionado.colorPrimario);
        int altoPanel = getHeight(); // Alto del panel
        int anchoPanel = getWidth(); // Ancho del panel

        // Calcula las dimensiones y posiciones del botón de configuraciones
        int tamanoBotonConfiguraciones = (int) (altoPanel * 0.6);
        int posYBotonConfiguraciones = (int) (altoPanel * 0.2);
        int posXBotonConfiguraciones = (int) (anchoPanel - 0.8 * altoPanel);

        // Calcula las dimensiones y posiciones del mensaje
        int margenMensaje = (int) (altoPanel * 0.1);
        int altoMensaje = (int) (altoPanel * 0.8);
        int anchoMaxMensaje = (int) (anchoPanel * 0.75);

        // Calcula el tamaño del texto basado en las dimensiones disponibles
        int tamanoTexto = fuentesPersonalizadas.calcularTamanoLetras(anchoMaxMensaje, altoMensaje, g);
        g.setFont(new Font(fuente, Font.BOLD, tamanoTexto));
        FontMetrics fm = g.getFontMetrics();

        // Dibuja el mensaje con sombra
        g.setColor(Temas.temaSeleccionado.colorTerciario);
        g.drawString(mensaje, margenMensaje * 2 + 2, margenMensaje + fm.getAscent() + 2);

        // Dibuja el mensaje principal
        g.setColor(Temas.temaSeleccionado.colorSecundario);
        g.drawString(mensaje, margenMensaje * 2, margenMensaje + fm.getAscent());

        // Establece el tamaño y posición del botón de configuraciones
        botonConfiguraciones.setBounds(posXBotonConfiguraciones, posYBotonConfiguraciones, tamanoBotonConfiguraciones, tamanoBotonConfiguraciones);
    }
}