package Vistas;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class PanelSelectorRuta extends JPanel {
    private final MenuSuperiorPanelInicial menuSuperiorPanelInicial;
    private final SelectorCiudad selectorOrigen;
    private final SelectorCiudad selectorDestino;
    private final FechaViaje fechaViaje;
    private final BufferedImage imagenFondo;
    private final BotonAvanzar botonAvanzar;
    public static Temas.Tema temaSeleccionado;


    /**
     * Constructor de la clase PanelSelectorRuta.
     * Configura los componentes y carga el tema aleatorio.
     */
    public PanelSelectorRuta(OperadorComandos avanzar) {
        // Seleccionar un tema aleatorio y obtener su imagen de fondo
        Temas temas = new Temas();
        temaSeleccionado = temas.seleccionarTemaAleatorio();
        imagenFondo = temaSeleccionado.imagen;

        // Inicializar componentes
        menuSuperiorPanelInicial = new MenuSuperiorPanelInicial();
        selectorOrigen = new SelectorCiudad("Origen");
        selectorDestino = new SelectorCiudad("Destino");
        fechaViaje = new FechaViaje();
        botonAvanzar = new BotonAvanzar(avanzar);

        // Configuración de layout nulo para posicionamiento absoluto
        this.setLayout(null);

        // Añadir componentes al panel
        this.add(botonAvanzar);
        this.add(selectorOrigen);
        this.add(selectorDestino);
        this.add(fechaViaje);
        this.add(menuSuperiorPanelInicial);
    }

    /**
     * Método sobrecargado para dibujar el componente.
     * Dibuja la imagen de fondo con ajuste de opacidad y posiciona los componentes.
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
        botonAvanzar.setBounds(posXOrigen, (int)margenSuperior*2 ,anchoSelector,altoSelector);
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
}