package Modelo;

public class Estandar extends Asiento {
    public Estandar(int num) {
        super(num);
    }

    public String getCategoria() {
        return "Estandar";
    }

    @Override
    public float getMultiplicador() {
        return 1.1F;
    }
}
