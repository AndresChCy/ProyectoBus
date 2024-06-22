package Modelo;

/**
 * Subclase de Asiento que representa un asiento SalonCama.
 */
public class SalonCama extends Asiento {
    /**
     * Constructor de SalonCama.
     * @param num   Numero del asiento.
     */
    public SalonCama(int num) {
        super(num);
    }

    /**
     * Método get para obtener la categoría de SalonCama.
     * @return  String con la categoría.
     */
    @Override
    public String getCategoria() {
        return "SalonCama";
    }

    /**
     * Método get para obtener el multiplicador de SalonCama.
     * @return  Multiplicador.
     */
    @Override
    public float getMultiplicador() {
        return 1.8F;
    }
}
