package Modelo;

public class Estandar extends Asiento {
    public Estandar() {
        super();
    }

    public String getCategoria() {
        return "Estandar";
    }

    @Override
    public float getMultiplicador() {
        return 1.1F;
    }
}
