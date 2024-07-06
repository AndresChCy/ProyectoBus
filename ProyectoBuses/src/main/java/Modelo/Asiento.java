package Modelo;
import java.util.ArrayList;

/**
 * Super clase que representa un asiento de bus.
 */
public abstract class Asiento {
    private int numero;
    private ArrayList<ViajeBus> reservas;

    /**
     * Constructor de Asiento.
     * @param numero    Numero que corresponde al asiento.
     */
    public Asiento(int numero) {
        reservas = new ArrayList<>();
        this.numero = numero;
    }

    /**
     * Método get que obtiene el numero del asiento.
     * @return
     */
    public int getNumero() {
        return numero;
    }

    /**
     * Método que verifica si el asiento está en un viaje en especifico.
     * @param viaje    Viaje en el que se quiere verificar.
     * @return         Boolean que verifica la veracidad.
     */
    public boolean isReservado(ViajeBus viaje) {
        return reservas.contains(viaje);
    }

    public void reservar(ViajeBus viaje){reservas.add(viaje);}

    /**
     * Método abstracto para obtener la categoria de las subclases.
     * @return  String con la categoria.
     */
    public abstract String getCategoria();

    /**
     * Método abstracto para obtener el multiplicador de las subclases.
     * @return
     */
    public abstract float getMultiplicador();
}
