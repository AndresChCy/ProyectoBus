package Modelo;

/**
 * Subclase de Asiento que representa un asiento estandar.
 */
public class Estandar extends Asiento {
    /**
     * Constructor de Estandar.
     * @param num   Numero del asiento.
     */
    public Estandar(int num) {
        super(num);
    }

    /**
     * Método get para obtener la categoría de Estandar.
     * @return  String con la categoría.
     */
    @Override
    public String getCategoria() {
        return "Estandar";
    }

    /**
     * Método get para obtener el multiplicador de Estandar.
     * @return  Multiplicador.
     */
    @Override
    public float getMultiplicador() {
        return 1.1F;
    }
}
