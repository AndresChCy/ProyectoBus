package Modelo;
import java.util.ArrayList;

public class Bus {
    private String ID;
    private ArrayList<PisoBus> pisosBus;

    public Bus() {
        this.ID = null;
        pisosBus = null;
    }

    public void setPisosBus(PisoBus piso) {
        pisosBus.add(piso);
    }

    public void setID(String id) {
        this.ID = id;
    }

    public int getNumeroTotalAsientos() {
        int totalAsientos = 0;
        for (PisoBus piso : pisosBus) {
            totalAsientos += piso.getNumAsientos();
        }
        return totalAsientos;
    }
}
