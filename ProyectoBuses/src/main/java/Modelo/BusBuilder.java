package Modelo;

public class BusBuilder implements Builder {
    private Bus bus;
    private static int number = 1;
    private int numAsiento;

    public BusBuilder() {
        reset();
    }

    @Override
    public void reset() {
        bus = new Bus(number);
        number++;
        numAsiento = 1;
    }

    @Override
    public void addPisoBus(int NumFilas) {
        bus.addPisoBus(NumFilas);
    }

    @Override
    public void addAsientoEstandar(int piso, int Columna, int Fila) {
        Asiento aux = new Estandar(numAsiento);
        bus.addAsientoBus(aux, piso, Columna, Fila);
        numAsiento++;
    }

    @Override
    public void addAsientoSemiCama(int piso, int Columna, int Fila) {
        Asiento aux = new SemiCama(numAsiento);
        bus.addAsientoBus(aux, piso, Columna, Fila);
        numAsiento++;
    }

    @Override
    public void addAsientoSalonCama(int piso, int Columna, int Fila) {
        Asiento aux = new SalonCama(numAsiento);
        bus.addAsientoBus(aux, piso, Columna, Fila);
        numAsiento++;
    }

    @Override
    public Bus finalizar() {
        return bus;
    }
}
