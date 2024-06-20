package Vistas;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * PanelInformacionPasajero es un JPanel que contiene información del pasajero,
 * incluyendo nombre, apellido, correo, y un panel para mostrar el precio a pagar.
 */
public class PanelInformacionPasajero extends JPanel {
    private final PanelTituloInfoPasajero panelTituloInfoPasajero; // Panel para el título de la información del pasajero
    private final List<PanelIntroducirInformacion> panelesInformacion; // Lista de paneles para introducir información
    private final PanelPrecioPagar panelPrecioPagar; // Panel para mostrar el precio a pagar
    private final BufferedImage imagenFondo; // Imagen de fondo del panel

    /**
     * Constructor de PanelInformacionPasajero.
     * Configura el layout nulo para posicionar los componentes manualmente.
     * Configura el color de fondo y carga la imagen de fondo desde el tema seleccionado.
     * Inicializa y agrega los componentes necesarios al panel.
     */
    public PanelInformacionPasajero() {
        setLayout(null); // Usar layout nulo para posicionar componentes manualmente
        setBackground(Color.WHITE); // Establecer color de fondo del panel

        imagenFondo = PanelSelectorRuta.temaSeleccionado.imagen; // Obtener la imagen de fondo del tema seleccionado

        panelTituloInfoPasajero = new PanelTituloInfoPasajero(); // Crear el panel de título de información de pasajero
        panelesInformacion = new ArrayList<>(); // Inicializar la lista para los paneles de introducción de información

        // Crear paneles de introducción de información para cada etiqueta
        String[] etiquetas = {"Nombre: ", "Apellido: ", "Correo: "};
        for (String etiqueta : etiquetas) {
            panelesInformacion.add(new PanelIntroducirInformacion(etiqueta));
        }

        panelPrecioPagar = new PanelPrecioPagar(24990); // Crear panel para mostrar el precio a pagar (ejemplo: 24990)

        // Agregar componentes al panel principal
        add(panelTituloInfoPasajero);
        for (PanelIntroducirInformacion panel : panelesInformacion) {
            add(panel);
        }
        add(panelPrecioPagar);
    }

    /**
     * Método sobrescrito para dibujar el contenido personalizado del panel.
     *
     * @param g Objeto Graphics utilizado para dibujar
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Llamar al método paintComponent de la superclase JPanel

        int anchoPanel = getWidth(); // Obtener el ancho del panel
        int altoPanel = getHeight(); // Obtener el alto del panel

        int altoPanelTitulo = (int) (altoPanel * 0.15); // Altura del panel de título
        int margenX = (int) (anchoPanel * 0.05); // Margen horizontal
        int margenY = (int) (altoPanel * 0.05); // Margen vertical
        int anchoPanelInfo = (int) (anchoPanel * 0.9); // Ancho de los paneles de información
        int altoPanelInfo = (int) (altoPanel * 0.1); // Altura de los paneles de información

        // Ajustar tamaño y posición del panel de título
        panelTituloInfoPasajero.setBounds(0, 0, anchoPanel, altoPanelTitulo);

        // Ajustar tamaño y posición de los paneles de introducción de información
        int posY = altoPanelTitulo + margenY;
        for (PanelIntroducirInformacion panel : panelesInformacion) {
            panel.setBounds(margenX, posY, anchoPanelInfo, altoPanelInfo);
            posY += altoPanelInfo + margenY;
        }

        // Ajustar tamaño y posición del panel de precio a pagar
        int posYPagar = altoPanel - (int) (altoPanel * 0.225) - margenY;
        int altoPagar = (int) (altoPanel * 0.225);

        panelPrecioPagar.setBounds(margenX, posYPagar, anchoPanelInfo, altoPagar);

        // Dibujar imagen de fondo si está disponible
        if (imagenFondo != null) {
            BufferedImage imagenAjustada = ajustarOpacidad(imagenFondo); // Reducción de la opacidad al 70%

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

            g.drawImage(imagenAjustada, x, y, anchoDibujo, altoDibujo, this); // Dibujar la imagen ajustada en el panel
        }
    }

    /**
     * Ajusta la opacidad de una imagen dada.
     *
     * @param imagenOriginal Imagen original
     * @return Imagen ajustada con la opacidad deseada
     */
    private BufferedImage ajustarOpacidad(BufferedImage imagenOriginal) {
        if (imagenOriginal == null) {
            return null;
        }

        // Creamos una imagen compatible para la nueva imagen con la opacidad deseada
        BufferedImage imagenAjustada = new BufferedImage(
                imagenOriginal.getWidth(), imagenOriginal.getHeight(), BufferedImage.TYPE_INT_ARGB);

        // Obtenemos el contexto gráfico de la nueva imagen
        Graphics2D g2d = imagenAjustada.createGraphics();

        // Aplicamos el composite para ajustar la opacidad
        AlphaComposite alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 0.3);
        g2d.setComposite(alphaComposite);

        // Dibujamos la imagen original en la nueva imagen
        g2d.drawImage(imagenOriginal, 0, 0, null);

        // Liberamos recursos del contexto gráfico
        g2d.dispose();

        return imagenAjustada;
    }
}