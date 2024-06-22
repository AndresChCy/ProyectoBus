package Modelo;

public interface Builder {
    void reset();
    void addPisoBus(int NumFilas);
    void addAsientoEstandar(int piso, int Columna, int Fila);
    void addAsientoSemiCama(int piso, int Columna, int Fila);
    void addAsientoSalonCama(int piso, int Columna, int Fila);
    Bus finalizar();

    //AÑADIR EXCEPCIONES, RECORDAR
}
