package Vistas;

import javax.swing.*;
import java.awt.*;

public class ComandoRetroceder implements Comandos{
    private JPanel panel;
    private CardLayout layout;
    public ComandoRetroceder(JPanel panel, CardLayout layout){
        this.panel = panel ;
        this.layout = layout;
    }
    public void execute(){
        layout.previous(panel);
    }
}
