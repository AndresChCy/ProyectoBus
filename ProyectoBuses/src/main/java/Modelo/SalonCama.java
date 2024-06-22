package Modelo;

public class SalonCama extends Asiento {
    public SalonCama(int num) {
        super(num);
    }

    public String getCategoria() {
        return "SalonCama";
    }

    @Override
    public float getMultiplicador() {
        return 1.8F;
    }
}
