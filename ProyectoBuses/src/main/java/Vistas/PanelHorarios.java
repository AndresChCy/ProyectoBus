package Vistas;

import Modelo.CalendarioObserver;

import javax.swing.*;
import java.awt.*;

/**
 * PanelHorarios es un JPanel personalizado que muestra horarios disponibles,
 * un título y un botón para retroceder. Utiliza fuentes personalizadas y colores
 * del tema seleccionado.
 */
public class PanelHorarios extends JPanel implements CalendarioObserver, TemasObserver {
    private final PanelTitulo panelTitulo;
    private final PanelHorariosDisponibles panelHorariosDisponibles;

    /**
     * Constructor de PanelHorarios.
     *
     * @param retroceder Comando para retroceder.
     * @param avanzar Comando para avanzar.
     */
    public PanelHorarios(Comandos retroceder, Comandos avanzar) {
        // Establece el layout a nulo para poder posicionar los componentes manualmente
        setLayout(null);
        // Establece el color de fondo del panel según el tema seleccionado
        setBackground(Temas.temaSeleccionado.colorPrimario);

        // Configuración de comandos
        OperadorComandos comandoAtras = new OperadorComandos(retroceder);
        OperadorComandos comandosAvanzar = new OperadorComandos(avanzar);

        // Creación del panel de título
        panelTitulo = new PanelTitulo("Horarios Disponibles", comandoAtras);

        // Creación del panel de horarios disponibles
        panelHorariosDisponibles = new PanelHorariosDisponibles(avanzar);

        // Posiciona el panel de horarios disponibles
        panelHorariosDisponibles.setBounds(0, (int) (getHeight() * 0.3), getWidth(), (int) (getHeight() * 0.7));

        // Agregar componentes al panel principal
        add(panelTitulo);
        add(panelHorariosDisponibles);
    }

    /**
     * Sobrescribe el método paintComponent para posicionar y redimensionar los subpaneles dentro del panel principal.
     *
     * @param g Objeto Graphics para pintar los componentes.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Obtiene las dimensiones actuales del panel principal
        int anchoPanel = getWidth();
        int altoPanel = getHeight();
        // Establece el color de fondo del panel según el tema seleccionado
        setBackground(Temas.temaSeleccionado.colorPrimario);

        // Actualiza el tamaño y posición del panel de horarios disponibles
        panelHorariosDisponibles.setBounds(0, (int) (altoPanel * 0.3), anchoPanel, (int) (altoPanel * 0.7));

        // Altura del título en relación al tamaño del panel principal
        int altoTitulo = (int) (altoPanel * 0.1);
        // Posiciona y dimensiona el panel de título
        panelTitulo.setBounds(0, 0, anchoPanel, altoTitulo);
    }

    /**
     * Actualiza los datos mostrados en el panel de horarios disponibles.
     */
    public void update() {
        panelHorariosDisponibles.update();
    }

    /**
     * Actualiza el tema de los componentes del panel.
     */
    public void updateTema() {
        panelHorariosDisponibles.updateTemas();
    }
}