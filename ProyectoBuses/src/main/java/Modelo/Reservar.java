package Modelo;

/**
 * Clase que simula la reservación de un asiento en un bus.
 */
public class Reservar {
    private Pasajero pasajero;
    private Asiento asiento;
    private Bus bus;

    /**
     * Constructor de Reservar.
     * @param cliente   Cliente que reserva.
     * @param asiento   Asiento que es reservado
     * @param bus       Bus en el que se reserva el asiento.
     */
    public Reservar(Pasajero cliente, Asiento asiento, Bus bus) {
        this.pasajero = cliente;
        this.asiento = asiento;
        this.bus = bus;
    }
}
