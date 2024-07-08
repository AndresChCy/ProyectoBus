package Vistas;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Comando para quitar paneles en CardLayout
 */
public class ComandoLimpiar implements Comandos {
    private JPanel panel;

    /**
     * Constructor
     * @param cual Panel que usa cardlayout y se le quieren quitar paneles
     */
    public ComandoLimpiar(JPanel cual){
        panel = cual;
    }

    /**
     * Borra todos los paneles excepto los base que estaban de un comienzo
     */
    @Override
    public void execute(){
        for (int i=panel.getComponentCount()-1;2<i;i--){
            System.out.println(panel.getComponentCount());
            panel.remove(i);
        }
    }
}
