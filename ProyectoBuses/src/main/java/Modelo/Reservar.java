package Modelo;

public class Reservar {
    private Pasajero pasajero;
    private Asiento asiento;
    private Bus bus;

    public Reservar(Pasajero cliente, Asiento asiento, Bus bus) {
        this.pasajero = cliente;
        this.asiento = asiento;
        this.bus = bus;
    }
}
