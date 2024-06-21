package Modelo;

import java.util.ArrayList;
import java.time.*;
import java.util.Collections;
import java.util.Comparator;


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
        for(int i = 0 ;i<numCiudades;i++){
            for(int j = 0;j<numCiudades;j++){
                for(int k = 0;k<14;k++){
                    calendario[i][j][k] = new ArrayList<>();
                }
            }
        }
    }
    public static CalendarioViajes getInstance(){
        if(fechas == null){
            fechas = new CalendarioViajes();
        }
        return fechas;
    }
    public void añadirViaje(ViajeBus viaje ){
        Ciudades orig = viaje.getOrigen();
        Ciudades est = viaje.getDestino();
        LocalDateTime fecha = viaje.getFecha();
        Period diff = LocalDate.now().until(fecha.toLocalDate());
        calendario[orig.ordinal()][est.ordinal()][diff.getDays()].add(viaje);
    }
    public void quitarViaje(ViajeBus viaje){
        Ciudades orig = viaje.getOrigen();
        Ciudades est = viaje.getDestino();
        LocalDateTime fecha = viaje.getFecha();
        Period diff = LocalDate.now().until(fecha.toLocalDate());
        calendario[orig.ordinal()][est.ordinal()][diff.getDays()].remove(viaje);
    }
    //Falta exceptions y completar metodos
    //Falta exception para evitar que origen = destino
    // Evitar que el dia sea mas tarde de 14 dias de hoy
    public void llenarDatos(Ciudades origen,Ciudades destino,LocalDate dia ){
        int hora;
        int minutos;
        LocalTime horario;
        ViajeBus viaje;
        Period diff = LocalDate.now().until(dia);
        Bus bus;
        int numViajes = (int) (Math.floor(Math.random()*(7)+4)); //Numeros entre 10 y 4
        for (int i = 0;i<=numViajes;i++ ){
            hora = (int) (Math.floor(Math.random()*(25)));
            minutos = 5 * (int) (Math.floor(Math.random()*(12)));
            horario = LocalTime.of(hora,minutos);
            bus = new Bus();
            viaje = new ViajeBus(bus,origen,destino,LocalDateTime.of(dia,horario),1 );
            calendario[origen.ordinal()][destino.ordinal()][diff.getDays()].add(viaje);
        }
        ordenarViajes(origen,destino,dia);
    }
    public void ordenarViajes(Ciudades origen,Ciudades destino,LocalDate dia){
        int fecha = LocalDate.now().until(dia).getDays();
        Collections.sort(calendario[origen.ordinal()][destino.ordinal()][fecha],
                (v1,v2) -> v1.getFecha().compareTo(v2.getFecha()));
    }
    public void actualizarDia(Ciudades origen,Ciudades destino){
        ArrayList<ViajeBus> dia1 = calendario[origen.ordinal()][destino.ordinal()][0];
        int numViajes = dia1.size();
        ordenarViajes(origen, destino, LocalDate.now());
        for (int i = 0;i < numViajes ;i++){
            if(LocalDateTime.now().isAfter(dia1.get(i).getFecha())){
                dia1.remove(i);
            }
            break;
        }
    }
    public void actualizarCalendario(){}
    public ArrayList<ViajeBus>[][][] getCalendario(){
        return calendario;
    }
}
