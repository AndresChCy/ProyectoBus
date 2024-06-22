package Modelo;

public class SemiCama extends Asiento {
    public SemiCama(int num) {
        super(num);
    }

    public String getCategoria() {
        return "SemiCama";
    }

    @Override
    public float getMultiplicador() {
        return 1.4F;
    }
}
