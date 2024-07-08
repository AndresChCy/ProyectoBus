package Modelo;

/**
 * Interfaz Builder que define los métodos para construir un Bus.
 */
public interface Builder {
    /** Reinicia la construccion del bus */
    void reset();

    /**
     * Añade un piso al autobus.
     * @param NumFilas  Numero de filas de asientos en el piso.
     */
    void addPisoBus(int NumFilas);

    /**
     * Añade un asiento de tipo Estandar al bus.
     * @param piso      Piso en el que se va a añadir el asiento.
     * @param Columna   Columna de los asientos disponibles en el piso.
     * @param Fila      Fila de los asientos disponibles en el piso.
     */
    void addAsientoEstandar(int piso, int Columna, int Fila);

    /**
     * Añade un Asiento de tipo SemiCama al bus.
     * @param piso      Piso en el que se va a añadir el asiento.
     * @param Columna   Columna de los asientos disponibles en el piso.
     * @param Fila      Fila de los asientos disponibles en el piso.
     */
    void addAsientoSemiCama(int piso, int Columna, int Fila);

    /**
     * Añade un Asiento de tipo SemiCama al bus.
     * @param piso      Piso en el que se va a añadir el asiento.
     * @param Columna   Columna de los asientos disponibles en el piso.
     * @param Fila      Fila de los asientos disponibles en el piso.
     */
    void addAsientoSalonCama(int piso, int Columna, int Fila);
    void addAsientoPremium(int piso, int Columna, int Fila);

    /** Finaliza la construcción del bus */
    Bus finalizar();
}
