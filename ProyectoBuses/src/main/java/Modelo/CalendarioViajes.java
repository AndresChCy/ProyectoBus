package Modelo;

import java.util.ArrayList;
import java.time.*;


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
    public void añadirViaje(ViajeBus viaje ){
    }
    public void quitarViaje(ViajeBus viaje){}
    //Falta exceptions y completar metodos
    public void llenarDatos(Ciudades origen,Ciudades destino,LocalDate dia ){
        int hora;
        int minutos;
        LocalTime horario;
        ViajeBus viaje;
        Period diff = LocalDate.now().until(dia);
        int numViajes = (int) (Math.floor(Math.random()*(7)+4)); //Numeros entre 10 y 4
        for (int i = 0;i<=numViajes;i++ ){
            hora = (int) (Math.floor(Math.random()*(25)));
            minutos = 5 * (int) (Math.floor(Math.random()*(12)));
            horario = LocalTime.of(hora,minutos);
            viaje = new ViajeBus(LocalDateTime.of(dia,horario),origen,destino );
            calendario[origen.ordinal()][destino.ordinal()][diff.getDays()].add(viaje);
        }
    }
    public void ordenarViajes(Ciudades origen,Ciudades destino,LocalDate dia){}
    public void actualizarDia(Ciudades origen,Ciudades destino){
        int numViajes = calendario[origen.ordinal()][destino.ordinal()][0].size();
        for (int i = 0;i < numViajes ;i++){
        }
    }
}
