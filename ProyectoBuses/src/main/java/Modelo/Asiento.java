package Modelo;
import java.util.ArrayList;

public abstract class Asiento {
    private int numero;
    private ArrayList<ViajeBus> reservas;

    public Asiento(int numero) {
        reservas = new ArrayList<>();
        this.numero = numero;
    }

    public int getNumero() {
        return numero;
    }

    public boolean isReservado(ViajeBus viaje) {
        return reservas.contains(viaje);
    }

    public abstract String getCategoria();
    public abstract float getMultiplicador();
}
