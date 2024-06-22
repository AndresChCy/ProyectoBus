package Modelo;
import java.util.Date;

public class Pasaje {
    private Pasajero pasajero;
    private Asiento asiento;
    private Ciudades destino;
    private Ciudades origen;
    private int numAsiento;
    private double viajePrecio;
    private Date fecha;

    public Pasaje(Pasajero Pasajero, Asiento Asiento, Ciudades Origen, Ciudades Destino, Date Fecha, double Precio) {
        this.pasajero = Pasajero;
        this.asiento = Asiento;
        this.numAsiento = asiento.getNumero();
        this.origen = Origen;
        this.destino = Destino;
        this.fecha = Fecha;
        this.viajePrecio = Precio;
    }

    public String obtenerPasaje() {
        return "Nombre: "+pasajero.getNombre()+"\n"+
                "Número de Asiento: "+asiento.getNumero()+"\n"+
                "Origen: "+origen+"\n"+
                "Destino: "+destino+"\n"+
                "Fecha: "+fecha+"\n"+
                "Precio del Viaje: "+viajePrecio+"\n";
    }
}
