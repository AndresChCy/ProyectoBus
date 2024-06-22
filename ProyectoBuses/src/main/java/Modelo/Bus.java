package Modelo;
import java.util.ArrayList;

public class Bus {
    private int ID;
    private ArrayList<PisoBus> pisosBus;

    public Bus(int ID) {
        this.ID = ID;
        pisosBus = new ArrayList<>();
    }

    public void addPisoBus(int NumFilas) {
        pisosBus.add(new PisoBus(NumFilas));
    }

    public void addAsientoBus(Asiento asiento, int numPiso, int Columna, int Fila) {
        if (pisosBus.size()<numPiso) {
            pisosBus.get(numPiso-1).addAsiento(Fila, Columna, asiento);
            //Añadir verificacion "PisoNoEstáLleno"
        } else {
            //tirar excepcion "PisoNoPerteneceAlBus".
        }
    }

    public int getNumeroTotalAsientos() {
        int totalAsientos = 0;
        for (PisoBus piso : pisosBus) {
            totalAsientos += piso.getNumAsientos();
        }
        return totalAsientos;
    }
}
