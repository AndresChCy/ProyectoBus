package Modelo;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDateTime;

/**
 * Clase que representa un Pasaje.
 */
public class Pasaje {
    private Pasajero pasajero;
    private Asiento asiento;
    private Ciudades destino;
    private Ciudades origen;
    private int numAsiento;
    private int viajePrecio;
    private LocalDateTime fecha;
    private Descuentos descuento;

    /**
     * Constructor de Pasaje que inicializa las variables.
     * @param Pasajero  Pasajero dueño del pasaje.
     * @param Asiento   Asiento del pasajero.
     * @param Origen    Ciduad de origen del viaje.
     * @param Destino   Ciudad de destino del viaje.
     * @param Fecha     Fecha del viaje.
     * @param Precio    Precio del viaje.
     */
    public Pasaje(Pasajero Pasajero, Asiento Asiento, Ciudades Origen, Ciudades Destino, LocalDateTime Fecha, int Precio,Descuentos descuento) {
        this.pasajero = Pasajero;
        this.asiento = Asiento;
        this.numAsiento = asiento.getNumero();
        this.origen = Origen;
        this.destino = Destino;
        this.fecha = Fecha;
        this.viajePrecio = Precio;
        this.descuento = descuento;
    }

    /**
     * Metodo para obtener información del pasaje.
     * @return  String con la informacion en formato natural de la respectiva variable.
     */
    public String obtenerPasaje() {
        return "Nombre: "+pasajero.getNombre()+" "+pasajero.getApellido()+"\n"+
                "Número de Asiento: "+asiento.getNumero()+
                " TIPO: " +asiento.getCategoria() +"\n"+
                "Origen: "+origen+"\n"+
                "Destino: "+destino+"\n"+
                "Fecha: "+fecha+"\n"+
                "DESCUENTO: " + descuento.toString()+"\n"+
                "Precio del Viaje: "+viajePrecio+"\n";
    }

    /**
     * Metodo para ponerle un nombre al pasaje
     * @return el nombre del pasaje
     */
    public String nombrar(){
        String titulo = new String("Pasajero");
        titulo += origen.ordinal() ;
        titulo += destino.ordinal();
        titulo += fecha.getDayOfYear();
        titulo += fecha.getHour();
        titulo += fecha.getMinute();
        titulo += numAsiento;
        return titulo;
    }

    /**
     * Metodo para serializar en un archivo txt el pasaje
     */
    public void imprimir(){
        FileWriter informe = null ;
        PrintWriter escritor = null;
        try {
            File archivo = new File("Test pasajes/"+nombrar()+".txt");
            informe = new FileWriter(archivo);
            escritor = new PrintWriter(informe);

            escritor.println("----PASAJE BUS----");
            escritor.println(obtenerPasaje());
            escritor.println("\n");

        } catch (Exception e) {
            System.out.println("Error:"+e.getMessage());
        } finally {
            escritor.close();
        }
    }
}
