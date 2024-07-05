package Vistas;

import java.util.ArrayList;

public class ComandoResetear implements Comandos{
    private ArrayList<Integer> quitar;
    public ComandoResetear(ArrayList<Integer> quitar){
        this.quitar = quitar;
    }
    public void execute(){
        quitar.clear();
    }
}
