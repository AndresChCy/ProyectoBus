package Modelo;
import java.util.Date;

/**
 * Clase que representa un Pasaje.
 */
public class Pasaje {
    private Pasajero pasajero;
    private Asiento asiento;
    private Ciudades destino;
    private Ciudades origen;
    private int numAsiento;
    private double viajePrecio;
    private Date fecha;

    /**
     * Constructor de Pasaje que inicializa las variables.
     * @param Pasajero  Pasajero dueño del pasaje.
     * @param Asiento   Asiento del pasajero.
     * @param Origen    Ciduad de origen del viaje.
     * @param Destino   Ciudad de destino del viaje.
     * @param Fecha     Fecha del viaje.
     * @param Precio    Precio del viaje.
     */
    public Pasaje(Pasajero Pasajero, Asiento Asiento, Ciudades Origen, Ciudades Destino, Date Fecha, double Precio) {
        this.pasajero = Pasajero;
        this.asiento = Asiento;
        this.numAsiento = asiento.getNumero();
        this.origen = Origen;
        this.destino = Destino;
        this.fecha = Fecha;
        this.viajePrecio = Precio;
    }

    /**
     * Método para obtener información del pasaje.
     * @return  String con la informacion en formato natural de la respectiva variable.
     */
    public String obtenerPasaje() {
        return "Nombre: "+pasajero.getNombre()+"\n"+
                "Número de Asiento: "+asiento.getNumero()+"\n"+
                "Origen: "+origen+"\n"+
                "Destino: "+destino+"\n"+
                "Fecha: "+fecha+"\n"+
                "Precio del Viaje: "+viajePrecio+"\n";
    }
}
