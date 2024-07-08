package Vistas;

import Modelo.CalendarioViajes;

public class ComandoAsignarViaje implements Comandos{
    private int numViaje;
    public ComandoAsignarViaje(int numViaje){
        this.numViaje = numViaje;
    }
    public void execute(){
        CalendarioViajes.getInstance().apuntarViaje(numViaje);
        CalendarioViajes.getInstance().notificar();
    }
}
