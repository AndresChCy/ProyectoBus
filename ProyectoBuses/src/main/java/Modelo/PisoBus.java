package Modelo;

public class PisoBus {
    private Asiento[][] asientos;
    private int numFilas;

    public PisoBus(int numFilas) {
        asientos = new Asiento[numFilas][4];
        this.numFilas = numFilas;
    }

    public void addAsiento(int fila, int columna, Asiento asiento) {
        asientos[fila][columna] = asiento;
    }

    public Asiento getAsiento(int fila, int columna) {
        return asientos[fila][columna];
    }


    public int getNumFilas() {
        return numFilas;
    }

    public int getNumAsientos() {
        return numFilas*4;
    }
}
