package Modelo;

import java.util.ArrayList;

/**
 * Clase singleton que representa la base de datos de los viajes en bus
 * Contiene una lista de viajes para cada trayectoria posible durante los
 * 14 dias siguientes.
 * Usamos el patrón Singleton ya que no tendria sentido que la empresa tenga 2 calendarios
 * de viajes distintos
 */
public class CalendarioViajes {
    private static CalendarioViajes fechas;
    private ArrayList<ViajeBus>[][][] calendario;

    private CalendarioViajes(){
        int numCiudades = Ciudades.values().length;
        calendario = new ArrayList[numCiudades][numCiudades][14];
    }
    public static CalendarioViajes getInstance(){
        if(fechas == null){
            fechas = new CalendarioViajes();
        }
        return fechas;
    }
}
