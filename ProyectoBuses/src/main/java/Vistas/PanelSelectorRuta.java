package Vistas;

import Modelo.Ciudades;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

/**
 * PanelSelectorRuta es un JPanel que permite a los usuarios seleccionar una ruta,
 * incluyendo origen, destino y fecha de viaje, y luego avanzar para ver los horarios disponibles.
 */
public class PanelSelectorRuta extends JPanel implements TemasObserver {
    private final MenuSuperiorPanelInicial menuSuperiorPanelInicial; // Panel superior del menú
    private final PanelConfiguracion panelConfiguracion; // Panel de configuración
    private final Selector selectorOrigen; // Selector de ciudad de origen
    private final Selector selectorDestino; // Selector de ciudad de destino
    private final FechaViaje fechaViaje; // Componente de selección de fecha de viaje
    private BufferedImage imagenFondo; // Imagen de fondo del panel
    private final BotonAvanzar botonAvanzar; // Botón para avanzar al siguiente paso

    private static final Map<String, Ciudades> Targets = new HashMap<>(); // Mapa para convertir nombres de ciudades a objetos Ciudades
    static {
        for (Ciudades ciudades : Ciudades.values()) {
            Targets.put(ciudades.toString(), ciudades);
        }
    }

    /**
     * Constructor de la clase PanelSelectorRuta.
     * Configura los componentes y carga el tema aleatorio.
     */
    public PanelSelectorRuta(Comandos avanzar, Temas temas) {
        // Seleccionar un tema aleatorio y obtener su imagen de fondo
        imagenFondo = Temas.temaSeleccionado.imagen;
        ComandoAsignarRuta asignarRuta = new ComandoAsignarRuta();
        OperadorComandos command = new OperadorComandos(asignarRuta);
        command.addComando(avanzar);

        String[] ciudades = new String[Ciudades.values().length];
        for (Ciudades ciudad : Ciudades.values()) {
            ciudades[ciudad.ordinal()] = ciudad.toString();
        }

        // Inicializar componentes
        panelConfiguracion = new PanelConfiguracion(temas, this);
        menuSuperiorPanelInicial = new MenuSuperiorPanelInicial(panelConfiguracion);
        selectorOrigen = new Selector("Origen", ciudades);
        selectorDestino = new Selector("Destino", ciudades);
        fechaViaje = new FechaViaje(asignarRuta);
        botonAvanzar = new BotonAvanzar(command, "VER HORARIOS");

        // Añadir oyentes a los selectores de origen y destino
        selectorOrigen.getComboBox().addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    Ciudades origen = Targets.get(e.getItem());
                    asignarRuta.setOrigen(origen);
                }
            }
        });

        selectorDestino.getComboBox().addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    Ciudades fin = Targets.get(e.getItem());
                    asignarRuta.setDestino(fin);
                }
            }
        });

        // Configuración de layout nulo para posicionamiento absoluto
        this.setLayout(null);

        // Añadir componentes al panel
        this.add(botonAvanzar);
        this.add(selectorOrigen);
        this.add(selectorDestino);
        this.add(fechaViaje);
        this.add(menuSuperiorPanelInicial);
        panelConfiguracion.setEnabled(false);
        panelConfiguracion.setVisible(false);
        this.add(panelConfiguracion);
        setComponentZOrder(panelConfiguracion, 0);
        setComponentZOrder(botonAvanzar, 1);
        setComponentZOrder(selectorDestino, 2);
        setComponentZOrder(selectorOrigen, 3);
    }

    /**
     * Método sobrecargado para dibujar el componente.
     * Dibuja la imagen de fondo con ajuste de opacidad y posiciona los componentes.
     *
     * @param g Objeto Graphics para dibujar en el panel.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int altoPanel = getHeight();
        int anchoPanel = getWidth();
        int altoMenuSuperior = (int) (altoPanel * 0.1);

        // Dibujar la imagen de fondo con ajuste de opacidad
        if (imagenFondo != null) {
            BufferedImage imagenAjustada = ajustarOpacidad(imagenFondo); // Reducción de la opacidad al 80%

            int anchoImagen = imagenAjustada.getWidth();
            int altoImagen = imagenAjustada.getHeight();

            double proporcionPanel = (double) anchoPanel / altoPanel;
            double proporcionImagen = (double) anchoImagen / altoImagen;

            int anchoDibujo, altoDibujo;

            if (proporcionPanel > proporcionImagen) {
                anchoDibujo = anchoPanel;
                altoDibujo = (int) (anchoDibujo / proporcionImagen);
            } else {
                altoDibujo = altoPanel;
                anchoDibujo = (int) (altoDibujo * proporcionImagen);
            }

            int x = (anchoPanel - anchoDibujo) / 2;
            int y = (altoPanel - altoDibujo) / 2;

            g.drawImage(imagenAjustada, x, y, anchoDibujo, altoDibujo, this);
        }

        // Posicionar el menú superior
        menuSuperiorPanelInicial.setBounds(0, 0, anchoPanel, altoMenuSuperior);

        if (this.isEnabled()) {
            // Calcular posiciones y dimensiones para los selectores y la fecha de viaje
            int margenSuperior = altoMenuSuperior + (int) (altoPanel * 0.25);
            int altoSelector = (int) (altoPanel * 0.1);
            int anchoSelector = (int) (anchoPanel * 0.3);

            int posXOrigen = (int) (anchoPanel * 0.2);
            selectorOrigen.setBounds(posXOrigen, margenSuperior, anchoSelector, altoSelector);

            int posXDestino = posXOrigen + anchoSelector;
            selectorDestino.setBounds(posXDestino, margenSuperior, anchoSelector, altoSelector);

            int posXFecha = (int) (anchoPanel * 0.35);
            int posYFecha = margenSuperior + altoSelector + (int) (altoPanel * 0.2);
            fechaViaje.setBounds(posXFecha, posYFecha, anchoSelector, altoSelector);

            int posXAvanzar = (int) (anchoPanel * 0.8);
            int posYAvanzar = (int) (altoPanel * 0.87);
            int anchoAvanzar = (int) (anchoPanel * 0.17);
            int altoAvanzar = (int) (altoPanel * 0.1);
            botonAvanzar.setBounds(posXAvanzar, posYAvanzar, anchoAvanzar, altoAvanzar);
        }

        // Posicionar el panel de configuración
        int anchoConfig = (int) (anchoPanel * 0.5);
        int altoConfig = (int) (altoPanel * 0.5);
        int posXConfig = (int) (anchoPanel * 0.25);
        int posYConfig = altoMenuSuperior + (int) (altoPanel * 0.25);
        panelConfiguracion.setBounds(posXConfig, posYConfig, anchoConfig, altoConfig);
    }

    /**
     * Método para ajustar la opacidad de una imagen.
     *
     * @param imagenOriginal Imagen original.
     * @return Imagen con la opacidad ajustada.
     */
    private BufferedImage ajustarOpacidad(BufferedImage imagenOriginal) {
        if (imagenOriginal == null) {
            return null;
        }

        // Crear una imagen compatible para la nueva imagen con la opacidad deseada
        BufferedImage imagenAjustada = new BufferedImage(
                imagenOriginal.getWidth(), imagenOriginal.getHeight(), BufferedImage.TYPE_INT_ARGB);

        // Obtener el contexto gráfico de la nueva imagen
        Graphics2D g2d = imagenAjustada.createGraphics();

        // Aplicar el composite para ajustar la opacidad (80%)
        AlphaComposite alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f);
        g2d.setComposite(alphaComposite);

        // Dibujar la imagen original en la nueva imagen
        g2d.drawImage(imagenOriginal, 0, 0, null);

        // Liberar recursos del contexto gráfico
        g2d.dispose();

        return imagenAjustada;
    }

    /**
     * Método que actualiza el tema del panel, incluyendo la imagen de fondo.
     */
    public void updateTema() {
        imagenFondo = Temas.temaSeleccionado.imagen;
        repaint();
    }
}