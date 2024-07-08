package Vistas;

import javax.swing.*;
import java.awt.*;

/**
 * Comando para ir hacia el panel anterior en un cardLayout
 */
public class ComandoRetroceder implements Comandos{
    private JPanel panel;
    private CardLayout layout;

    /**
     * Constructor
     * @param panel panel que usa CardLayout
     * @param layout Layout del panel
     */
    public ComandoRetroceder(JPanel panel, CardLayout layout){
        this.panel = panel ;
        this.layout = layout;
    }

    /**
     * Cambia el panel del cardLayout al anterior
     */
    @Override
    public void execute(){
        layout.previous(panel);
        panel.repaint();
    }
}
