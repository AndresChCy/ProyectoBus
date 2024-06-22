package Modelo;

/**
 * Subclase de Asiento que representa un asiento SemiCama.
 */
public class SemiCama extends Asiento {
    /**
     * Constructor de SemiCama.
     * @param num   Numero del asiento.
     */
    public SemiCama(int num) {
        super(num);
    }

    /**
     * Método get para obtener la categoría de SemiCama.
     * @return  String con la categoría.
     */
    @Override
    public String getCategoria() {
        return "SemiCama";
    }

    /**
     * Método get para obtener el multiplicador de SemiCama.
     * @return  Multiplicador.
     */
    @Override
    public float getMultiplicador() {
        return 1.4F;
    }
}
