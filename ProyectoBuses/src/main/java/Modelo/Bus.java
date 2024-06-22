package Modelo;
import java.util.ArrayList;

/**
 * Clase que simula un Bus.
 */
public class Bus {
    private int ID;
    private ArrayList<PisoBus> pisosBus;

    /**
     * Constructor de la clase Bus.
     * @param ID    Numero que identifica al bus.
     */
    public Bus(int ID) {
        this.ID = ID;
        pisosBus = new ArrayList<>();
    }

    /**
     * Método add que añade pisos al bus.
     * @param NumFilas  Numero de filas que tendrá el piso.
     */
    public void addPisoBus(int NumFilas) {
        pisosBus.add(new PisoBus(NumFilas));
    }

    /**
     * Método add que añade asientos a un piso determinado en una posición determinada.
     * @param asiento   Asiento que se quiere añadir.
     * @param numPiso   Numero del piso del bus.
     * @param Columna   Columna de asientos en el piso del bus.
     * @param Fila      Fila de asientos en el piso del bus.
     */
    public void addAsientoBus(Asiento asiento, int numPiso, int Columna, int Fila) {
        if (pisosBus.size()<numPiso) {
            if (pisosBus.get(numPiso-1).getNumAsientos()>pisosBus.get(numPiso-1).getNumAsientosActivos()) {
                pisosBus.get(numPiso-1).addAsiento(Fila, Columna, asiento);
            } else {
                throw new RuntimeException("El piso ya no admite más asientos.");
            }
        } else {
            throw new RuntimeException("Se ha escogido un piso que no está en el bus.");
        }
    }

    /**
     * Método que obtiene el numero total de asientos en el bus.
     * @return  Número total de asientos en ambos pisos.
     */
    public int getNumeroTotalAsientos() {
        int totalAsientos = 0;
        for (PisoBus piso : pisosBus) {
            totalAsientos += piso.getNumAsientos();
        }
        return totalAsientos;
    }
}
