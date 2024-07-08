package Vistas;

import Modelo.Asiento;

import java.util.ArrayList;

/**
 * Comando para limpiar una lista
 */
public class ComandoResetear implements Comandos{
    private ArrayList<Asiento> quitar;

    /**
     * Constructor
     * @param quitar Lista de asientos que se requiere limpiar
     */
    public ComandoResetear(ArrayList<Asiento> quitar){
        this.quitar = quitar;
    }

    /**
     * Limpia la lista de asientos
     */
    @Override
    public void execute(){
        quitar.clear();
    }
}
