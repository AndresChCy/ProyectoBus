package Vistas;

import javax.swing.*;
import java.awt.*;

/**
 * PanelHorarios es un JPanel personalizado que muestra horarios disponibles,
 * un título y un botón para retroceder. Utiliza fuentes personalizadas y colores
 * del tema seleccionado.
 */
public class PanelHorarios extends JPanel {
    private final FuentesPersonalizadas fuentesPersonalizadas;
    private final String mensaje = "Horarios Disponibles";
    private final String fuente = "Broadway";
    private final BotonRetroceder botonRetroceder;
    private final PanelHorariosDisponibles panelHorariosDisponibles;

    /**
     * Constructor de PanelHorarios.
     *
     * @param panelPrincipal Referencia al panel principal que contiene este panel.
     */
    public PanelHorarios(PanelPrincipal panelPrincipal) {
        setLayout(null); // Establece el layout a nulo para poder posicionar los componentes manualmente
        setBackground(PanelSelectorRuta.temaSeleccionado.colorPrimario); // Establece el color de fondo del panel

        // Inicialización de las fuentes personalizadas
        fuentesPersonalizadas = new FuentesPersonalizadas(mensaje, fuente);

        // Creación del panel de título
        JPanel panelTitulo = crearPanelTitulo();

        // Creación del botón para retroceder
        botonRetroceder = new BotonRetroceder(panelPrincipal);
        botonRetroceder.setBounds(10, 10, 30, 30);

        // Creación del panel de horarios disponibles
        panelHorariosDisponibles = createPanelHorariosDisponibles(panelPrincipal);
        panelHorariosDisponibles.setBounds(0, (int) (getHeight() * 0.3), getWidth(), (int) (getHeight() * 0.7));

        // Agregar componentes al panel principal
        add(panelTitulo);
        add(panelHorariosDisponibles);
        add(botonRetroceder);
    }

    /**
     * Crea y configura el panel de horarios disponibles.
     *
     * @param panelPrincipal Referencia al panel principal que contiene este panel.
     * @return El panel de horarios disponibles configurado.
     */
    private PanelHorariosDisponibles createPanelHorariosDisponibles(PanelPrincipal panelPrincipal) {
        String[] arrayHorarios = {
                "12:00 A.M", "12:30 A.M", "1:00 A.M", "2:00 A.M", "3:00 A.M", "4:00 A.M", "5:00 A.M", "6:00 A.M",
                "7:00 A.M", "8:00 A.M", "9:00 A.M", "10:00 A.M", "10:30 A.M", "11:00 A.M", "12:00 P.M", "1:00 P.M",
                "2:00 P.M", "3:00 P.M", "4:00 P.M", "5:00 P.M", "6:00 P.M", "7:00 P.M", "8:00 P.M", "9:00 P.M",
                "10:00 P.M", "11:00 P.M"
        };
        // Crear el panel de horarios disponibles con los horarios y el panel principal como parámetro
        return new PanelHorariosDisponibles(arrayHorarios, panelPrincipal);
    }

    /**
     * Crea y configura el panel de título con el mensaje de horarios disponibles.
     *
     * @return El panel de título configurado.
     */
    private JPanel crearPanelTitulo() {
        JPanel panelTitulo = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Configuración del color de fondo del panel de título
                setBackground(PanelSelectorRuta.temaSeleccionado.colorPrimario);

                // Configuración de la fuente y tamaño del mensaje
                int anchoPanel = getWidth();
                int altoPanel = getHeight();
                int tamanoFuente = fuentesPersonalizadas.calcularTamanoLetras(anchoPanel, altoPanel, g);
                g.setFont(new Font(fuente, Font.BOLD, tamanoFuente));
                FontMetrics fm = g.getFontMetrics();
                int anchoMensaje = fm.stringWidth(mensaje) / 2;
                int posXMensaje = (anchoPanel / 2) - anchoMensaje;

                // Dibujar el mensaje en dos colores para efecto de sombra
                g.setColor(PanelSelectorRuta.temaSeleccionado.colorSecundario);
                g.drawString(mensaje, posXMensaje + 2, altoPanel - fm.getHeight() / 2 + 2);
                g.setColor(PanelSelectorRuta.temaSeleccionado.colorTerciario);
                g.drawString(mensaje, posXMensaje, altoPanel - fm.getHeight() / 2);
            }
        };
        panelTitulo.setBounds(0, 0, getWidth(), (int) (getHeight() * 0.3)); // Posición y tamaño del panel de título
        panelTitulo.setBackground(Color.BLACK); // Establece el color de fondo del panel de título
        return panelTitulo;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int anchoPanel = getWidth();
        int altoPanel = getHeight();

        // Actualizar el tamaño y posición del panel de horarios disponibles
        panelHorariosDisponibles.setBounds(0, (int) (altoPanel * 0.3), anchoPanel, (int) (altoPanel * 0.7));

        // Calcular posición y tamaño del botón para retroceder
        int margenBoton = (int) (altoPanel * 0.1 * 0.3);
        int medidaBoton = (int) (altoPanel * 0.3 * 0.3);
        int margenTitulo = 2 * margenBoton + medidaBoton;

        // Obtener y ajustar el panel de título y el botón para retroceder
        JPanel panelTitulo = (JPanel) getComponent(0);
        panelTitulo.setBounds(margenTitulo, 0, anchoPanel - 2 * margenTitulo, (int) (altoPanel * 0.3));
        botonRetroceder.setBounds(margenBoton, margenBoton, medidaBoton, medidaBoton);
    }
}