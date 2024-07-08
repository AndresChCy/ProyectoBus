package Modelo;


import java.io.*;
import java.security.Guard;

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
public class CalendarioViajes implements Serializable {

    private static CalendarioViajes fechas;
    private ArrayList<ViajeBus>[][][] calendario;
    private LocalDate ultimoGuardado;
    private ArrayList<ViajeBus> diaApuntado;
    private ViajeBus viajeApuntado;
    private ArrayList<CalendarioObserver> seguidores;


    /**
     * Constructor privado de CalendarioViajes
     * Si encuentra un archivo que contenga un estado guardado de esta clase se construye en base a esos datos
     * En caso contrario simplemente inicializara las propiedades con una matriz cubica para cada posible ciudad de origen y destino
     * La tercera dimensión es 14 representando que solo almacena los siguiente 14 dias
     */
    private CalendarioViajes(){
        try{
            String ruta = System.getProperty("user.dir") + "/SaveCalendar/InfCalendario.txt";
            FileInputStream fileInputStream = new FileInputStream(ruta);
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
        diaApuntado = calendario[0][1][0];
        seguidores = new ArrayList<>();
        //viajeApuntado = diaApuntado.get(0);
    }

    /**
     * Metodo para obtener la instancia estatica de esta clase
     * @return La instancia de esta clase
     */
    public static CalendarioViajes getInstance(){
        if(fechas == null){
            fechas = new CalendarioViajes();
        }
        return fechas;
    }

    /**
     * Añade un viaje al calendario
     * @param viaje viaje que se añade segun su origen destino y fecha
     */
    public void añadirViaje(ViajeBus viaje ){
        Ciudades orig = viaje.getOrigen();
        Ciudades est = viaje.getDestino();
        LocalDateTime fecha = viaje.getFecha();
        Period diff = LocalDate.now().until(fecha.toLocalDate());
        calendario[orig.ordinal()][est.ordinal()][diff.getDays()].add(viaje);
    }

    /**
     * Metodo para eliminar un viaje del calendario
     * @param viaje que quieres remover
     */
    public void quitarViaje(ViajeBus viaje){
        Ciudades orig = viaje.getOrigen();
        Ciudades est = viaje.getDestino();
        LocalDateTime fecha = viaje.getFecha();
        Period diff = LocalDate.now().until(fecha.toLocalDate());
        calendario[orig.ordinal()][est.ordinal()][diff.getDays()].remove(viaje);
    }

    /**
     * Metodo para llenar automaticamente con viajes una trayectoria de un dia especifico del calendario
     * @param origen Ciudad de origen
     * @param destino Ciudad de destino
     * @param dia Fecha del viaje
     */
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
            int numViajes = (int) (Math.floor(Math.random() * (8) + 4)); //Numeros entre 10 y 4
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

    /**
     * Ordena la lista de viajes de una especifica trayectoria y fecha segun la hora de salida
     * @param origen ciudad de origen
     * @param destino ciudad de destino
     * @param dia fecha del viaje
     */
    public void ordenarViajes(Ciudades origen,Ciudades destino,LocalDate dia){
        Collections.sort(getDia(origen,destino,dia),
                (v1,v2) -> v1.getFecha().compareTo(v2.getFecha()));
    }

    /**
     * Actualiza el dia actual para una trayectoria especifica (Elimina los viajes que ya haya pasado su hora)
     * @param origen ciudad de origen
     * @param destino ciudad de destino
     */
    public void actualizarDia(Ciudades origen,Ciudades destino) {
        if (origen==destino) {
            throw new RuntimeException("El origen no puede ser el destino.");
        }

        ArrayList<ViajeBus> dia1 = calendario[origen.ordinal()][destino.ordinal()][0];
        int numViajes = dia1.size();
        ordenarViajes(origen, destino, LocalDate.now());
        while(LocalDateTime.now().isAfter(dia1.get(0).getFecha())){
            calendario[origen.ordinal()][destino.ordinal()][0].remove(0);
        }
    }

    /**
     * Metodo para actualizar el calendario absolutamente
     * Se encargara de mover el calendario segun la fecha actual eliminando las ya pasadas
     */
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
        ultimoGuardado = LocalDate.now();
    }

    /**
     *
     * @return el calendario asociado a la clase
     */
    public ArrayList<ViajeBus>[][][] getCalendario(){
        return calendario;
    }

    /**
     * Si la trayectoria y la fecha es valida retorna la lista de viajes para ese dia y esa trayectoria
     * @param origen ciudad de origen
     * @param destino ciudad de destino
     * @param dia fecha del viaje
     * @return La lista de viajes con los parametros dados
     */
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

    /**
     * Metodo que serializa y genera un archivo que guarda la informacion del calendario
     * @throws IOException
     */
    public void Guardar() throws IOException {
        String ruta = System.getProperty("user.dir") + "/SaveCalendar/";
        File archivo = new File(ruta +"InfCalendario.txt");
        File carpeta = archivo.getParentFile();
        if (!carpeta.exists()){
            carpeta.mkdirs();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(archivo);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(this);
        objectOutputStream.close();
    }

    /**
     * selecciona un dia del calendario segun los parametros
     * @param origen ciudad de origen
     * @param destino ciudad de destino
     * @param dia fecha del viaje
     */
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

    /**
     * Se obtiene el dia seleccionado por el metodo apuntarDia()
     * @return el dia seleccionado
     */
    public ArrayList<ViajeBus> getDia(){
        return diaApuntado;
    }

    /**
     * Selecciona un viaje del dia apuntado
     * @param i
     */
    public void apuntarViaje(int i){
        viajeApuntado = this.diaApuntado.get(i);
    }

    /**
     * obtiene el viaje seleccionadoo con apuntarViaje()
     * @return el viaje seleccionado
     */
    public ViajeBus getViaje(){
        return viajeApuntado;
    }

    /**
     * Metodo para añadir objetos que quieran saber de los cambios del Calendario
     * @param observer CalendarioObserver para ser añadido
     */
    public void suscribir(CalendarioObserver observer){
        this.seguidores.add(observer);
    }

    /**
     * Manda a su lista de observdores a actualizarse.
     */
    public void notificar(){
        for (int i=0;i<seguidores.size();i++){
            seguidores.get(i).update();
        }
    }
}
