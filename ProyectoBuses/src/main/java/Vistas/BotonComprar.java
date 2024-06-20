package Vistas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BotonComprar extends JButton {
    FuentesPersonalizadas fuentesPersonalizadas;
    String fuente = "Roboto";
    String mensaje = "COMPRAR";
    public BotonComprar() {
        this.setBackground(PanelSelectorRuta.temaSeleccionado.colorSecundario);

        fuentesPersonalizadas = new FuentesPersonalizadas(mensaje, fuente);

        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Comprado");
            }
        });
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        int anchoPanel = getWidth();
        int altoPanel = getHeight();
        int tamanoFuente = fuentesPersonalizadas.calcularTamanoLetras(anchoPanel, altoPanel, g);
        g.setFont(new Font(fuente, Font.BOLD, tamanoFuente));
        FontMetrics fm = g.getFontMetrics();
        int anchoMensaje = fm.stringWidth(mensaje) / 2;
        int posXMensaje = (anchoPanel / 2) - anchoMensaje;
        int posYMensaje = (altoPanel + fm.getAscent()) / 2;
        g.setColor(Color.BLACK);
        g.drawString(mensaje, posXMensaje + 2, posYMensaje + 2);
        g.setColor(new Color(27, 101, 64));
        g.drawString(mensaje, posXMensaje, posYMensaje);

        this.setFont(g.getFont());
    }
}
