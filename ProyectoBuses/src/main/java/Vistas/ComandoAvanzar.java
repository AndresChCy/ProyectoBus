package Vistas;

import javax.swing.*;
import java.awt.*;

/**
 * Comando para ir al siguiente panel de una CardLayout
 */
public class ComandoAvanzar implements Comandos{
    private JPanel panel;
    private CardLayout layout;

    /**
     * Constructor para inicializar
     * @param panel El panel que contiene la layout
     * @param layout CardLayout en la cual avanzar
     */
    public ComandoAvanzar(JPanel panel, CardLayout layout){
        this.panel = panel ;
        this.layout = layout;
    }

    /**
     * Cambia al siguiente panel de la CardLayout
     */
    @Override
    public void execute(){
        layout.next(panel);
        panel.repaint();
    }
}
