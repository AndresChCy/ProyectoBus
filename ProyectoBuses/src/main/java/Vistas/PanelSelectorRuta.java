package Vistas;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class PanelSelectorRuta extends JPanel {
    private final MenuSuperiorPanelInicial menuSuperiorPanelInicial;
    private final SelectorOrigen selectorOrigen;
    private final SelectorDestino selectorDestino;
    private final FechaViaje fechaViaje;
    private final BufferedImage imagenFondo;
    public static Temas.Tema temaSeleccionado;

    public PanelSelectorRuta() {
        Temas temas = new Temas();
        temaSeleccionado = temas.seleccionarTemaAleatorio();

        imagenFondo = temaSeleccionado.imagen;
        menuSuperiorPanelInicial = new MenuSuperiorPanelInicial();
        selectorOrigen = new SelectorOrigen();
        selectorDestino = new SelectorDestino();
        fechaViaje = new FechaViaje();

        this.setLayout(null);

        this.add(selectorOrigen);
        this.add(selectorDestino);
        this.add(fechaViaje);
        this.add(menuSuperiorPanelInicial);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int altoPanel = getHeight();
        int anchoPanel = getWidth();

        int altoMenúSuperior = (int) (altoPanel * 0.1);

        if (imagenFondo != null) {
            BufferedImage imagenAjustada = ajustarOpacidad(imagenFondo, 0.85f); // Reducción de la opacidad al 85%

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

        menuSuperiorPanelInicial.setBounds(0, 0, anchoPanel, altoMenúSuperior);

        int margenSuperior = altoMenúSuperior + (int) (altoPanel * 0.25);
        int altoSelector = (int) (altoPanel * 0.1);
        int anchoSelector = (int) (anchoPanel * 0.3);

        int posXOrigen = (int) (anchoPanel * 0.2);
        int posYOrigen = margenSuperior;
        selectorOrigen.setBounds(posXOrigen, posYOrigen, anchoSelector, altoSelector);

        int posXDestino = posXOrigen + anchoSelector;
        int posYDestino = margenSuperior;
        selectorDestino.setBounds(posXDestino, posYDestino, anchoSelector, altoSelector);

        int posXFecha = (int) (anchoPanel * 0.35);
        int posYFecha = posYOrigen + altoSelector + (int) (altoPanel * 0.2);
        fechaViaje.setBounds(posXFecha, posYFecha, anchoSelector, altoSelector);
    }

    private BufferedImage ajustarOpacidad(BufferedImage imagenOriginal, float opacidad) {
        if (imagenOriginal == null) {
            return null;
        }

        // Creamos una imagen compatible para la nueva imagen con la opacidad deseada
        BufferedImage imagenAjustada = new BufferedImage(
                imagenOriginal.getWidth(), imagenOriginal.getHeight(), BufferedImage.TYPE_INT_ARGB);

        // Obtenemos el contexto gráfico de la nueva imagen
        Graphics2D g2d = imagenAjustada.createGraphics();

        // Aplicamos el composite para ajustar la opacidad
        AlphaComposite alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacidad);
        g2d.setComposite(alphaComposite);

        // Dibujamos la imagen original en la nueva imagen
        g2d.drawImage(imagenOriginal, 0, 0, null);

        // Liberamos recursos del contexto gráfico
        g2d.dispose();

        return imagenAjustada;
    }
}
