package Modelo;

public interface Builder {
    void Reset();
    void addPisoBus(int NumFilas);
    void addAsientoEstandar(PisoBus piso, int Columna, int Fila);
    void addAsientoSemiCama(PisoBus piso, int Columna, int Fila);
    void addAsientoSalonCama(PisoBus piso, int Columna, int Fila);
    Bus Finalizar();

    //AÑADIR EXCEPCIONES, RECORDAR
}
