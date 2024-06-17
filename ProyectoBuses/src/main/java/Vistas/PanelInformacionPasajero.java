package Vistas;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PanelInformacionPasajero extends JPanel {
    private PanelTituloInfoPasajero panelTituloInfoPasajero;
    private List<PanelIntroducirInformacion> panelesInformacion;
    private PanelPrecioPagar panelPrecioPagar;
    private String[] etiquetas = {"Nombre: ", "Apellido: ", "Correo: "};

    public PanelInformacionPasajero() {
        setLayout(null); // Usar layout nulo para posicionar componentes manualmente
        setBackground(Color.RED);

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
    }
}
