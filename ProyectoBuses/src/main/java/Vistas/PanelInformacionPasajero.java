package Vistas;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

public class PanelInformacionPasajero extends JPanel {
    private PanelTituloInfoPasajero panelTituloInfoPasajero;
    private List<PanelIntroducirInformacion> panelesInformacion;
    private PanelPrecioPagar panelPrecioPagar;
    private String[] etiquetas = {"Nombre: ", "Apellido: ", "Correo: "};
    private final BufferedImage imagenFondo;

    public PanelInformacionPasajero() {
        setLayout(null); // Usar layout nulo para posicionar componentes manualmente
        setBackground(PanelSelectorRuta.temaSeleccionado.colorTerciario);

        imagenFondo = PanelSelectorRuta.temaSeleccionado.imagen;

        panelTituloInfoPasajero = new PanelTituloInfoPasajero();
        panelesInformacion = new ArrayList<>();

        for (String etiqueta : etiquetas) {
            panelesInformacion.add(new PanelIntroducirInformacion(etiqueta));
        }

        panelPrecioPagar = new PanelPrecioPagar(24990);

        add(panelTituloInfoPasajero);
        for (PanelIntroducirInformacion panel : panelesInformacion) {
            add(panel);
        }
        add(panelPrecioPagar);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int anchoPanel = getWidth();
        int altoPanel = getHeight();

        int altoPanelTitulo = (int) (altoPanel * 0.15);
        int margenX = (int) (anchoPanel * 0.05);
        int margenY = (int) (altoPanel * 0.05);
        int anchoPanelInfo = (int) (anchoPanel * 0.9);
        int altoPanelInfo = (int) (altoPanel * 0.1);

        // Ajustar tamaño y posición del panel de título
        panelTituloInfoPasajero.setBounds(0, 0, anchoPanel, altoPanelTitulo);

        // Ajustar tamaño y posición de los paneles de información
        int posY = altoPanelTitulo + margenY;
        for (PanelIntroducirInformacion panel : panelesInformacion) {
            panel.setBounds(margenX, posY, anchoPanelInfo, altoPanelInfo);
            posY += altoPanelInfo + margenY;
        }

        // Ajustar tamaño y posición del panel de precio a pagar
        int posYPagar = altoPanel - (int) (altoPanel * 0.225) - margenY;
        int anchoPagar = anchoPanelInfo;
        int altoPagar = (int) (altoPanel * 0.225);

        panelPrecioPagar.setBounds(margenX, posYPagar, anchoPagar, altoPagar);

        if (imagenFondo != null) {
            BufferedImage imagenAjustada = ajustarOpacidad(imagenFondo, 0.3f); // Reducción de la opacidad al 70%

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
