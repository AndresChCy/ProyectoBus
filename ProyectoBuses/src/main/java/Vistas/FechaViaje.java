package Vistas;

import javax.swing.*;
import java.awt.*;

public class FechaViaje extends JPanel {
    PanelIntroducirInformacion piiDia;
    PanelIntroducirInformacion piiMes;
    public FechaViaje() {
        this.setOpaque(false);
        piiDia = new PanelIntroducirInformacion("Día: ");
        piiMes = new PanelIntroducirInformacion("Mes: ");
        this.add(piiDia);
        this.add(piiMes);
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        piiDia.setBounds(0, 0, getWidth() / 2, getHeight());
        piiMes.setBounds(getWidth() / 2, 0, getWidth() / 2, getHeight());
    }
}
