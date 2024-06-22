package Modelo;

/**
 * Clase BusBuilder que implementa la interfaz Builder para construir objetos de tipo Bus.
 */
public class BusBuilder implements Builder {
    private Bus bus;
    private static int number = 1;
    private int numAsiento;

    /** Constructor de la clase BusBuilder */
    public BusBuilder() {
        reset();
    }

    /** Método que reinicia la construccioón del bus, creando uno nuevo. */
    @Override
    public void reset() {
        bus = new Bus(number);
        number++;
        numAsiento = 1;
    }

    /**
     * Método add que añade pisos al bus.
     * @param NumFilas  Numero de filas del piso.
     */
    @Override
    public void addPisoBus(int NumFilas) {
        bus.addPisoBus(NumFilas);
    }

    /**
     * Método add que añade un Asiento de tipo Estandar al bus.
     * @param piso      Piso en el que se va a añadir el asiento.
     * @param Columna   Columna de los asientos disponibles en el piso.
     * @param Fila      Fila de los asientos disponibles en el piso.
     */
    @Override
    public void addAsientoEstandar(int piso, int Columna, int Fila) {
        Asiento aux = new Estandar(numAsiento);
        bus.addAsientoBus(aux, piso, Columna, Fila);
        numAsiento++;
    }

    /**
     * Método add que añade un Asiento de tipo SemiCama al bus.
     * @param piso      Piso en el que se va a añadir el asiento.
     * @param Columna   Columna de los asientos disponibles en el piso.
     * @param Fila      Fila de los asientos disponibles en el piso.
     */
    @Override
    public void addAsientoSemiCama(int piso, int Columna, int Fila) {
        Asiento aux = new SemiCama(numAsiento);
        bus.addAsientoBus(aux, piso, Columna, Fila);
        numAsiento++;
    }

    /**
     * Método add que añade un Asiento de tipo SalonCama al bus.
     * @param piso      Piso en el que se va a añadir el asiento.
     * @param Columna   Columna de los asientos disponibles en el piso.
     * @param Fila      Fila de los asientos disponibles en el piso.
     */
    @Override
    public void addAsientoSalonCama(int piso, int Columna, int Fila) {
        Asiento aux = new SalonCama(numAsiento);
        bus.addAsientoBus(aux, piso, Columna, Fila);
        numAsiento++;
    }

    /**
     * Método para finalizar la construcción del bus.
     * @return  Bus creado.
     */
    @Override
    public Bus finalizar() {
        return bus;
    }
}
