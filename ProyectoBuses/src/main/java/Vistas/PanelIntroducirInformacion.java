package Vistas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class PanelIntroducirInformacion extends JPanel {
    FuentesPersonalizadas fuentesPersonalizadas;
    String fuente = "Roboto";
    String mensaje;
    JTextField campoTexto;

    public PanelIntroducirInformacion(String mensaje) {
        this.setBackground(PanelSelectorRuta.temaSeleccionado.colorSecundario);
        this.mensaje = mensaje;

        fuentesPersonalizadas = new FuentesPersonalizadas(mensaje, fuente);

        campoTexto = new JTextField("Escriba aquí", 8);
        campoTexto.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (campoTexto.getText().equals("Escriba aquí")) {
                    campoTexto.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (campoTexto.getText().isEmpty()) {
                    campoTexto.setText("Escriba aquí");
                }
            }
        });

        this.add(campoTexto);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int anchoPanel = getWidth();
        int altoPanel = getHeight();

        int anchoMensaje = (int) (anchoPanel * 0.3);
        int tamanoFuente = fuentesPersonalizadas.calcularTamanoLetras(anchoMensaje, altoPanel, g);
        g.setFont(new Font(fuente, Font.BOLD, tamanoFuente));
        FontMetrics fm = g.getFontMetrics();

        int posXMensaje = (int) (anchoPanel * 0.05);
        int posYMensaje = (int) (altoPanel * 0.05);

        g.setColor(Color.BLACK);
        g.drawString(mensaje, posXMensaje + 2, posYMensaje + fm.getAscent() + 2);

        g.setColor(PanelSelectorRuta.temaSeleccionado.colorTerciario);
        g.drawString(mensaje, posXMensaje, posYMensaje + fm.getAscent());

        int posXCampoTexto = (int) (0.3 * anchoPanel);
        int anchoCampoTexto = (int) (0.7 * anchoPanel);
        campoTexto.setFont(g.getFont());
        campoTexto.setForeground(PanelSelectorRuta.temaSeleccionado.colorSecundario);
        campoTexto.setBackground(PanelSelectorRuta.temaSeleccionado.colorTerciario);
        campoTexto.setBounds(posXCampoTexto, 0, anchoCampoTexto, altoPanel);
    }
}
