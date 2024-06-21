package Modelo;
import java.util.Date;

import java.time.*;

public class ViajeBus {

    private Bus bus;
    private Ciudades origen;
    private Ciudades destino;
    private LocalDateTime fecha;
    private int ViajeID;
    private double precioViaje;

    public ViajeBus(Bus bus, Ciudades lugarSalida, Ciudades lugarDestino, LocalDateTime fechaSalida, int viajeID) {
        this.bus = bus;
        this.origen = lugarSalida;
        this.destino = lugarDestino;
        this.fecha = fechaSalida;
        this.ViajeID = viajeID;

        double dHorizontal = lugarSalida.getX() - lugarDestino.getX();
        double dVertical = lugarSalida.getY() - lugarDestino.getY();
        this.precioViaje = Math.sqrt(dHorizontal*dHorizontal + dVertical*dVertical);
    }

    public void ComprarPasaje(Pasajero pasajero, Asiento asiento) {}

    public Ciudades getOrigen() {
        return origen;
    }

    public Ciudades getDestino() {
        return destino;
    }
}
