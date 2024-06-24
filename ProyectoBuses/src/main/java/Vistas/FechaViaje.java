package Vistas;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

/**
 * FechaViaje es un JPanel que contiene dos subpaneles para introducir el día y el mes de un viaje.
 */
public class FechaViaje extends JPanel {
    PanelIntroducirInformacion piiDia;
    PanelIntroducirInformacion piiMes;

    /**
     * Constructor de FechaViaje.
     * Inicializa los subpaneles y los añade al panel principal.
     */
    public FechaViaje(ComandoAsignarRuta command) {
        this.setOpaque(false); // Establece el panel como no opaco para permitir la transparencia
        piiDia = new PanelIntroducirInformacion("Día: "); // Crea el panel para introducir el día
        piiMes = new PanelIntroducirInformacion("Mes: "); // Crea el panel para introducir el mes

        piiMes.getCampoTexto().getDocument().addDocumentListener(new EscuchaTextos() {
            @Override
            public void update(DocumentEvent e) {
                command.setMes(piiMes.getCampoTexto().getText());
            }
        });
        piiDia.getCampoTexto().getDocument().addDocumentListener(new EscuchaTextos() {
            @Override
            public void update(DocumentEvent e) {
                command.setDia(piiDia.getCampoTexto().getText());
            }
        });

                // Añade los subpaneles al panel principal
        this.add(piiDia);
        this.add(piiMes);
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

        // Establece la posición y tamaño del panel para introducir el día
        piiDia.setBounds(0, 0, anchoPanel / 2, altoPanel);

        // Establece la posición y tamaño del panel para introducir el mes
        piiMes.setBounds(anchoPanel / 2, 0, anchoPanel / 2, altoPanel);
    }
}