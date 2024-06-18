package Modelo;
import java.util.ArrayList;

public class Bus {
    private String ID;
    private ArrayList<Asiento> Asientos;
    private int NumAsientosPiso;
    private int NumPisos;

    public Bus(String id, int numAsientosPorPiso, int numPisos) {
        this.ID = id;
        this.Asientos = new ArrayList<Asiento>();
        this.NumAsientosPiso = numAsientosPorPiso;
        this.NumPisos = numPisos;
    }
}
