package Vistas;

import Modelo.CalendarioViajes;
import Modelo.ViajeBus;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;

/**
 * PanelHorariosDisponibles es un JPanel que muestra una lista de paneles PanelInfoBus
 * y permite desplazarse verticalmente con la rueda del mouse.
 */
public class PanelHorariosDisponibles extends JPanel implements MouseWheelListener {
    private ArrayList<ViajeBus> viajes; // Array de datos auxiliares
    private PanelInfoBus[] panelesInfoBus; // Array de paneles de información de buses
    private int desplazamiento = 0; // Desplazamiento vertical
    private Color[] colores; // Colores alternados para los paneles
    private final Comandos avanzar;

    /**
     * Constructor de PanelHorariosDisponibles.
     *
     * @param avanzar Comando para avanzar.
     */
    public PanelHorariosDisponibles(Comandos avanzar) {
        colores = new Color[] {Color.WHITE, Temas.temaSeleccionado.colorTerciario};
        this.viajes = CalendarioViajes.getInstance().getDia();
        this.avanzar = avanzar;
        this.panelesInfoBus = new PanelInfoBus[viajes.size()];
        this.setLayout(null); // Layout nulo para posicionar los paneles manualmente
        this.addMouseWheelListener(this); // Agregar listener para la rueda del mouse
        this.inicializarPaneles(); // Método para inicializar los paneles PanelInfoBus
    }

    /**
     * Método privado para inicializar los paneles PanelInfoBus.
     */
    private void inicializarPaneles() {
        int anchoPanel = getWidth(); // Obtener el ancho del panel
        int altoPanel = getHeight(); // Obtener el alto del panel
        int altoPanelInfoBus = (int) (altoPanel * 0.1); // Altura de cada panel PanelInfoBus
        this.removeAll(); // Eliminar todos los componentes del panel
        OperadorComandos comandoAvanzar;
        panelesInfoBus = new PanelInfoBus[viajes.size()];
        for (int i = 0; i < viajes.size(); i++) {
            comandoAvanzar = new OperadorComandos(new ComandoAsignarViaje(i));
            comandoAvanzar.addComando(avanzar);
            Color color = colores[i % 2]; // Alternar colores entre blanco y el color terciario del tema
            panelesInfoBus[i] = new PanelInfoBus(viajes.get(i), comandoAvanzar, color); // Crear nuevo panel PanelInfoBus
            panelesInfoBus[i].setBounds(0, i * altoPanelInfoBus, anchoPanel, altoPanelInfoBus); // Establecer posición y tamaño del panel
            this.add(panelesInfoBus[i]); // Agregar el panel al PanelHorariosDisponibles
        }
        repaint();
    }

    /**
     * Método sobrescrito para dibujar el contenido del panel.
     *
     * @param g Objeto Graphics utilizado para dibujar.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Llamar al método paintComponent de la superclase JPanel
        int anchoPanel = getWidth(); // Obtener el ancho del panel
        int altoPanel = getHeight(); // Obtener el alto del panel
        int altoPanelInfoBus = (int) (altoPanel * 0.1); // Altura de cada panel PanelInfoBus

        // Iterar sobre los paneles PanelInfoBus y ajustar su posición según el desplazamiento
        for (int i = 0; i < viajes.size(); i++) {
            panelesInfoBus[i].setBounds(0, (i * altoPanelInfoBus) - desplazamiento, anchoPanel, altoPanelInfoBus);
        }
    }

    /**
     * Método sobrescrito para manejar eventos de rueda del mouse.
     * Permite desplazarse verticalmente por la lista de paneles PanelInfoBus.
     *
     * @param e Evento de rueda del mouse.
     */
    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        int notches = e.getWheelRotation(); // Obtener el número de notches (movimientos de la rueda)
        int altoPanel = getHeight(); // Obtener el alto del panel
        int altoPanelInfoBus = (int) (altoPanel * 0.1); // Altura de cada panel PanelInfoBus
        int maxDesplazamiento = Math.max(0, altoPanelInfoBus * viajes.size() - altoPanel); // Calcular el máximo desplazamiento permitido

        desplazamiento += notches * altoPanelInfoBus / 5; // Incrementar o decrementar el desplazamiento según el movimiento de la rueda
        desplazamiento = Math.max(0, Math.min(desplazamiento, maxDesplazamiento)); // Asegurar que el desplazamiento esté dentro de los límites

        repaint(); // Volver a dibujar el panel con el nuevo desplazamiento
    }

    /**
     * Actualiza los datos mostrados en el panel de horarios disponibles.
     */
    public void update() {
        this.viajes = CalendarioViajes.getInstance().getDia();
        inicializarPaneles();
    }

    /**
     * Actualiza el tema de los componentes del panel.
     */
    public void updateTemas() {
        colores = new Color[] {Color.WHITE, Temas.temaSeleccionado.colorTerciario};
        repaint();
    }
}