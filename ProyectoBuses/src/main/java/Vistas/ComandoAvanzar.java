package Vistas;

import javax.swing.*;
import java.awt.*;

public class ComandoAvanzar implements Comandos{
    private JPanel panel;
    private CardLayout layout;
    public ComandoAvanzar(JPanel panel, CardLayout layout){
        this.panel = panel ;
        this.layout = layout;
    }
    public void execute(){
        layout.next(panel);
    }
}
