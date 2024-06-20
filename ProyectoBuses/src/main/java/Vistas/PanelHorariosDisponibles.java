package Vistas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.Random;

/**
 * PanelHorariosDisponibles es un JPanel que muestra una lista de paneles PanelInfoBus y permite desplazarse verticalmente con la rueda del mouse.
 */
public class PanelHorariosDisponibles extends JPanel implements MouseWheelListener {
    private final String[] arrayAuxiliar; // Array de datos auxiliares
    private final PanelInfoBus[] panelesInfoBus; // Array de paneles de información de buses
    private int desplazamiento = 0; // Desplazamiento vertical
    private final PanelPrincipal panelPrincipal; // Referencia al panel principal
    private final Color[] colores = new Color[] {Color.WHITE, PanelSelectorRuta.temaSeleccionado.colorTerciario}; // Colores alternados para los paneles
    private final Random random; // Objeto Random para generar números aleatorios

    /**
     * Constructor de PanelHorariosDisponibles.
     * @param arrayAuxiliar Array de datos auxiliares para inicializar los paneles PanelInfoBus
     * @param panelPrincipal Referencia al panel principal
     */
    public PanelHorariosDisponibles(String[] arrayAuxiliar, PanelPrincipal panelPrincipal) {
        this.arrayAuxiliar = arrayAuxiliar;
        this.panelPrincipal = panelPrincipal;
        this.panelesInfoBus = new PanelInfoBus[arrayAuxiliar.length];
        this.setLayout(null); // Layout nulo para posicionar los paneles manualmente
        this.addMouseWheelListener(this); // Agregar listener para la rueda del mouse
        this.random = new Random(); // Inicializar objeto Random (solo para pruebas)
        this.inicializarPaneles(); // Método para inicializar los paneles PanelInfoBus
    }

    /**
     * Método privado para inicializar los paneles PanelInfoBus.
     */
    private void inicializarPaneles() {
        int anchoPanel = getWidth(); // Obtener el ancho del panel
        int altoPanel = getHeight(); // Obtener el alto del panel
        int altoPanelInfoBus = (int) (altoPanel * 0.1); // Altura de cada panel PanelInfoBus

        for (int i = 0; i < arrayAuxiliar.length; i++) {
            int numeroRandom = random.nextInt(45001) + 5000; // Generar número aleatorio para el precio (solo para pruebas)

            Color color = colores[i % 2]; // Alternar colores entre blanco y el color terciario del tema
            panelesInfoBus[i] = new PanelInfoBus(arrayAuxiliar[i], numeroRandom, panelPrincipal, color); // Crear nuevo panel PanelInfoBus
            panelesInfoBus[i].setBounds(0, i * altoPanelInfoBus, anchoPanel, altoPanelInfoBus); // Establecer posición y tamaño del panel
            this.add(panelesInfoBus[i]); // Agregar el panel al PanelHorariosDisponibles
        }
    }

    /**
     * Método sobrescrito para dibujar el contenido del panel.
     * @param g Objeto Graphics utilizado para dibujar
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Llamar al método paintComponent de la superclase JPanel

        int anchoPanel = getWidth(); // Obtener el ancho del panel
        int altoPanel = getHeight(); // Obtener el alto del panel
        int altoPanelInfoBus = (int) (altoPanel * 0.1); // Altura de cada panel PanelInfoBus

        // Iterar sobre los paneles PanelInfoBus y ajustar su posición según el desplazamiento
        for (int i = 0; i < arrayAuxiliar.length; i++) {
            panelesInfoBus[i].setBounds(0, (i * altoPanelInfoBus) - desplazamiento, anchoPanel, altoPanelInfoBus);
        }
    }

    /**
     * Método sobrescrito para manejar eventos de rueda del mouse.
     * Permite desplazarse verticalmente por la lista de paneles PanelInfoBus.
     * @param e Evento de rueda del mouse
     */
    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        int notches = e.getWheelRotation(); // Obtener el número de notches (movimientos de la rueda)
        int altoPanel = getHeight(); // Obtener el alto del panel
        int altoPanelInfoBus = (int) (altoPanel * 0.1); // Altura de cada panel PanelInfoBus
        int maxDesplazamiento = Math.max(0, altoPanelInfoBus * arrayAuxiliar.length - altoPanel); // Calcular el máximo desplazamiento permitido

        desplazamiento += notches * altoPanelInfoBus / 5; // Incrementar o decrementar el desplazamiento según el movimiento de la rueda
        desplazamiento = Math.max(0, Math.min(desplazamiento, maxDesplazamiento)); // Asegurar que el desplazamiento esté dentro de los límites

        repaint(); // Volver a dibujar el panel con el nuevo desplazamiento
    }
}