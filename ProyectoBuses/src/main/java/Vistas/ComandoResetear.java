package Vistas;

import Modelo.Asiento;

import java.util.ArrayList;

public class ComandoResetear implements Comandos{
    private ArrayList<Asiento> quitar;
    public ComandoResetear(ArrayList<Asiento> quitar){
        this.quitar = quitar;
    }
    public void execute(){
        quitar.clear();
    }
}
