package Modelo;
import java.util.ArrayList;

public abstract class Asiento {
    private int Numero;
    private ArrayList<ViajeBus> reservas;

    public Asiento() {
        reservas = new ArrayList<>();
    }

    public void setNumero(int numero) {
        Numero = numero;
    }

    public int getNumero() {
        return Numero;
    }

    public boolean isReservado(ViajeBus viaje) {
        return reservas.contains(viaje);
    }

    public abstract String getCategoria();
    public abstract float getMultiplicador();
}
