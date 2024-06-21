package Modelo;

public class BusBuilder implements Builder {
    private Bus bus;
    private static int number = 1;

    public BusBuilder() {
        Reset();
    }

    @Override
    public void Reset() {
        bus = new Bus(number);
        number++;
    }

    @Override
    public void addPisoBus(int NumFilas) {
        bus.addPisoBus(NumFilas);
    }

    @Override
    public void addAsientoEstandar(PisoBus piso, int Columna, int Fila) {
        Asiento aux = new Estandar();
        bus.addAsientoBus(aux, piso, Columna, Fila);
    }

    @Override
    public void addAsientoSemiCama(PisoBus piso, int Columna, int Fila) {
        Asiento aux = new SemiCama();
        bus.addAsientoBus(aux, piso, Columna, Fila);
    }

    @Override
    public void addAsientoSalonCama(PisoBus piso, int Columna, int Fila) {
        Asiento aux = new SalonCama();
        bus.addAsientoBus(aux, piso, Columna, Fila);
    }

    @Override
    public Bus Finalizar() {
        return bus;
    }
}
