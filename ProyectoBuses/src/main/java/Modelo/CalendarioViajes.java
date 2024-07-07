package Modelo;

import java.io.*;
import java.security.Guard;
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
public class CalendarioViajes implements Serializable {
    private static CalendarioViajes fechas;
    private ArrayList<ViajeBus>[][][] calendario;
    private LocalDate ultimoGuardado;

    private CalendarioViajes(){
        try{
            FileInputStream fileInputStream = new FileInputStream("InfCalendario.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            fechas = (CalendarioViajes) objectInputStream.readObject();
            objectInputStream.close();
            actualizarCalendario();
        }catch(Exception e){
            int numCiudades = Ciudades.values().length;
            calendario = new ArrayList[numCiudades][numCiudades][14];
            for(int i = 0 ;i<numCiudades;i++){
                for(int j = 0;j<numCiudades;j++){
                    for(int k = 0;k<14;k++){
                        calendario[i][j][k] = new ArrayList<>();
                    }
                }
            }
            ultimoGuardado = LocalDate.now();
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
            int numViajes = (int) (Math.floor(Math.random() * (7) + 4)); //Numeros entre 10 y 4
            for (int i = 0; i <= numViajes; i++) {
                hora = (int) (Math.floor(Math.random() * (24)));
                minutos = 5 * (int) (Math.floor(Math.random() * (12)));
                horario = LocalTime.of(hora, minutos);
                cualBus = (int) (Math.floor(Math.random() * (6)));
                creator.make(ModelosBus.values()[cualBus]);
                bus = creator.getBus();
                viaje = new ViajeBus(bus, origen, destino, LocalDateTime.of(dia, horario), 1);
                aux.add(viaje);
            }
            ordenarViajes(origen, destino, dia);
        }
    }
    public void ordenarViajes(Ciudades origen,Ciudades destino,LocalDate dia){
        int fecha = LocalDate.now().until(dia).getDays();
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
                dia1.remove(i);
            }
            break;
        }
    }
    public void actualizarCalendario(){
        int diff = LocalDate.now().getDayOfYear()-ultimoGuardado.getDayOfYear();
        int numCiudades = Ciudades.values().length;
        for(int i = 0 ;i<numCiudades;i++){
            for(int j = 0;j<numCiudades;j++){
                for(int k = 0;k<14;k++){
                    if(k+14>diff){
                        calendario[i][j][k] = calendario[i][j][k+diff];
                    }
                    else calendario[i][j][k] = new ArrayList<>();
                    if (k==0){actualizarDia(Ciudades.values()[i],Ciudades.values()[j]);}
                }
            }
        }
    }
    public ArrayList<ViajeBus>[][][] getCalendario(){
        return calendario;
    }

    public ArrayList<ViajeBus> getDia(Ciudades origen,Ciudades destino,LocalDate dia) {
        if (origen==destino) {
            throw new RuntimeException("El origen no puede ser el destino.");
        }

        int diff = LocalDate.now().until(dia).getDays();
        if(diff>=14){
            throw new RuntimeException("El dia sobrepasa el rango permitido.");
        }
        return calendario[origen.ordinal()][destino.ordinal()][diff];
    }
    public void Guardar() throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("InfCalendario.txt");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(this);
        objectOutputStream.close();
    }
}
