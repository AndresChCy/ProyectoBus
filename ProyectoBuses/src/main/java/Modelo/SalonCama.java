package Modelo;

public class SalonCama extends Asiento {
    public SalonCama() {
        super();
    }

    public String getCategoria() {
        return "SalonCama";
    }

    @Override
    public float getMultiplicador() {
        return 1.8F;
    }
}
