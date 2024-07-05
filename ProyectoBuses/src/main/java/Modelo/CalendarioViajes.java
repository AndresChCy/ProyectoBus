package Modelo;

import java.time.temporal.ChronoUnit;
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
    private ArrayList<ViajeBus> diaApuntado;
    private ViajeBus viajeApuntado;
    private ArrayList<CalendarioObserver> seguidores;


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
        diaApuntado = calendario[0][1][0];
        seguidores = new ArrayList<>();
        //viajeApuntado = diaApuntado.get(0);
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

    public void llenarDatos(Ciudades origen,Ciudades destino,LocalDate dia ){
        ArrayList<ViajeBus> aux = getDia(origen,destino,dia);
        if (origen==destino) {
            throw new RuntimeException("El origen no puede ser el destino.");
        }
        if(aux.isEmpty()) {
            SintetizadorBuses creator = new SintetizadorBuses(new BusBuilder());
            int hora;
            int minutos;
            int cualBus;
            LocalTime horario;
            ViajeBus viaje;
            Bus bus;
            int numViajes = (int) (Math.floor(Math.random() * (0) + 4)); //Numeros entre 10 y 4
            for (int i = 0; i <= numViajes; i++) {
                hora = (int) (Math.floor(Math.random() * (24)));
                minutos = 5 * (int) (Math.floor(Math.random() * (12)));
                horario = LocalTime.of(hora, minutos);
                cualBus = (int) (Math.floor(Math.random() * (6)));
                creator.make(ModelosBus.values()[cualBus]);
                bus = creator.getBus();
                int id = hora*1000+minutos*100+cualBus*10+i;
                viaje = new ViajeBus(bus, origen, destino, LocalDateTime.of(dia, horario), id);
                aux.add(viaje);
            }
            ordenarViajes(origen, destino, dia);
            if(dia.getDayOfYear() == LocalDate.now().getDayOfYear()){
                actualizarDia(origen, destino);
            }
        }
    }
    public void ordenarViajes(Ciudades origen,Ciudades destino,LocalDate dia){
        Collections.sort(getDia(origen,destino,dia),
                (v1,v2) -> v1.getFecha().compareTo(v2.getFecha()));
    }
    public void actualizarDia(Ciudades origen,Ciudades destino) {
        if (origen==destino) {
            throw new RuntimeException("El origen no puede ser el destino.");
        }

        ArrayList<ViajeBus> dia1 = calendario[origen.ordinal()][destino.ordinal()][0];
        int numViajes = dia1.size();
        ordenarViajes(origen, destino, LocalDate.now());
        for (int i = 0;i < numViajes ;i++){
            if(LocalDateTime.now().isAfter(dia1.get(i).getFecha())){
                calendario[origen.ordinal()][destino.ordinal()][0].remove(i);
            }
            else break;
        }
    }
    public void actualizarCalendario(){}
    public ArrayList<ViajeBus>[][][] getCalendario(){
        return calendario;
    }

    public ArrayList<ViajeBus> getDia(Ciudades origen,Ciudades destino,LocalDate dia) {
        if (origen==destino) {
            throw new RuntimeException("El origen no puede ser el destino.");
        }

        int diff = (int)ChronoUnit.DAYS.between(LocalDate.now(),dia);
        System.out.println(diff);
        if(diff>=14){
            throw new RuntimeException("El dia sobrepasa el rango permitido.");
        }
        return calendario[origen.ordinal()][destino.ordinal()][diff];
    }
    public void apuntarDia(Ciudades origen,Ciudades destino,LocalDate dia){
        if (origen==destino) {
            throw new RuntimeException("El origen no puede ser el destino.");
        }

        int diff = LocalDate.now().until(dia).getDays();
        if(diff>=14){
            throw new RuntimeException("El dia sobrepasa el rango permitido.");
        }
        this.diaApuntado = calendario[origen.ordinal()][destino.ordinal()][diff];
    }
    public ArrayList<ViajeBus> getDia(){
        return diaApuntado;
    }
    public void apuntarViaje(int i){
        viajeApuntado = this.diaApuntado.get(i);
    }
    public ViajeBus getViaje(){
        return viajeApuntado;
    }
    public void suscribir(CalendarioObserver observer){
        this.seguidores.add(observer);
    }
    public void notificar(){
        for (int i=0;i<seguidores.size();i++){
            seguidores.get(i).update();
        }
    }
}
