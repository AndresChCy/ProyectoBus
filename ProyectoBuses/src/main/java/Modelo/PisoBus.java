package Modelo;

/**
 * Clase que simula un piso perteneciente a un bus.
 */
public class PisoBus {
    private Asiento[][] asientos;
    private int numAsientosActivos;
    private int numFilas;

    /**
     * Constructor de PisoBus
     * @param numFilas  Numero de filas que tendrá la matriz de asientos.
     */
    public PisoBus(int numFilas) {
        asientos = new Asiento[4][numFilas];
        this.numFilas = numFilas;
        this.numAsientosActivos = 0;
    }

    /**
     * Método add que añade un asiento al piso en una posición determinada de la matriz asientos.
     * @param fila      Fila de la matriz de asientos donde se quiere colocar el asiento.
     * @param columna   Columna de la matriz de asientos donde se quiere colocar el asiento.
     * @param asiento   Asiento que se desea agregar al piso.
     */
    public void addAsiento(int fila, int columna, Asiento asiento) {
        asientos[fila][columna] = asiento;
        numAsientosActivos++;
    }

    /**
     * Método get para obtener un asiento del piso en especifico.
     * @param fila      Fila de asientos de donde se quiere obtener el asiento.
     * @param columna   Columna de asientos de donde se quiere obtener el asiento.
     * @return          Asiento que se queria obtener.
     */
    public Asiento getAsiento(int fila, int columna) {
        return asientos[fila][columna];
    }

    /**
     * Método get para obtener el numero de filas en el piso.
     * @return  Numero que representa las filas.
     */
    public int getNumFilas() {
        return numFilas;
    }

    /**
     * Método get que obtiene los asientos activos actualmente en el piso.
     * @return  Numero de asientos activos.
     */
    public int getNumAsientosActivos() {
        return numAsientosActivos;
    }

    /**
     * Método get que obtiene el número de asientos total que se pueden colocar en el piso.
     * @return  Numero de asientos disponibles en el piso.
     */
    public int getNumAsientos() {
        return numFilas*4;
    }
    public Asiento[][] getAsientos(){return this.asientos;}
}
