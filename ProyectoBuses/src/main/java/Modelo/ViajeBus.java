package Modelo;
import java.util.Date;

/**
 * Clase que representa el viaje de un bus.
 */
public class ViajeBus {
    private Bus bus;
    private Ciudades origen;
    private Ciudades destino;
    private Date fecha;
    private int ViajeID;
    private double precioViaje;

    /**
     * Constructor de ViajeBus que asigna sus variables con los parametros correspondientes.
     * @param bus           Bus que empleará el viaje.
     * @param lugarSalida   Ciudad de salida.
     * @param lugarDestino  Ciudad de llegada.
     * @param fechaSalida   Fecha en la que se realizará el viaje.
     * @param viajeID       ID del viaje para aspectos tecnicos.
     */
    public ViajeBus(Bus bus, Ciudades lugarSalida, Ciudades lugarDestino, Date fechaSalida, int viajeID) {
        this.bus = bus;
        this.origen = lugarSalida;
        this.destino = lugarDestino;
        this.fecha = fechaSalida;
        this.ViajeID = viajeID;

        double dHorizontal = lugarSalida.getX() - lugarDestino.getX();
        double dVertical = lugarSalida.getY() - lugarDestino.getY();
        this.precioViaje = Math.sqrt(dHorizontal*dHorizontal + dVertical*dVertical)*1000;
    }

    /**
     * Método que compra un pasaje del respectivo viaje.
     * @param pasajero  Pasajero que compra el pasaje.
     * @param asiento   Asiento que reservó el pasajero.
     * @return          Pasaje comprado.
     */
    public Pasaje ComprarPasaje(Pasajero pasajero, Asiento asiento) {
        precioViaje = precioViaje*asiento.getMultiplicador();
        Pasaje aux = new Pasaje(pasajero, asiento, origen, destino, fecha, precioViaje);
        return aux;
    }

    /**
     * Método get para obtener la fecha del viaje.
     * @return  Fecha del viaje.
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * Método get para obtener la ciudad de salida del viaje.
     * @return  Ciudad de origen.
     */
    public Ciudades getOrigen() {
        return origen;
    }

    /**
     * Método get para obtener la ciudad de llegada del viaje.
     * @return  Ciudad de llegada.
     */
    public Ciudades getDestino() {
        return destino;
    }
}
