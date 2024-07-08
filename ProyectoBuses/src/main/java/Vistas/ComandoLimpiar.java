package Vistas;

import javax.swing.*;
import java.util.ArrayList;

public class ComandoLimpiar implements Comandos {
    private JPanel panel;
    public ComandoLimpiar(JPanel cual){
        panel = cual;
    }
    public void execute(){
        for (int i=panel.getComponentCount()-1;2<i;i--){
            System.out.println(panel.getComponentCount());
            panel.remove(i);
        }
    }
}
