package Modelo;
import java.util.Date;

public class ViajeBus {
    private Bus bus;
    private Ciudades origen;
    private Ciudades destino;
    private Date fecha;
    private int ViajeID;
    private double precioViaje;

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

    public Pasaje ComprarPasaje(Pasajero pasajero, Asiento asiento) {
        precioViaje = precioViaje*asiento.getMultiplicador();
        Pasaje aux = new Pasaje(pasajero, asiento, origen, destino, fecha, precioViaje);
        return aux;
    }

    public Date getFecha() {
        return fecha;
    }

    public Ciudades getOrigen() {
        return origen;
    }

    public Ciudades getDestino() {
        return destino;
    }
}
