package Vistas;

import Modelo.CalendarioViajes;

/**
 * Clase para apuntar a un viaje del CalendarioViajes
 */
public class ComandoAsignarViaje implements Comandos{
    private int numViaje;

    /**
     * Constructor
     * @param numViaje numero del viaje seleccionado
     */
    public ComandoAsignarViaje(int numViaje){
        this.numViaje = numViaje;
    }

    /**
     * Apunta al viaje segun el num de viaje del comando
     */
    @Override
    public void execute(){
        CalendarioViajes.getInstance().apuntarViaje(numViaje);
        CalendarioViajes.getInstance().notificar();
    }
}
