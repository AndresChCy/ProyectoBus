package Modelo;

/**
 * Enumeracion que contiene las ciudades disponibles.
 */
public enum Ciudades {
    CHILLAN(-36, -72),
    CONCEPCION(-36,-73),
    TEMUCO(-38,-72),
    SANTIAGO(-33,-70);

    private final double horizontal;
    private final double vertical;

    /**
     * Constructor de Ciudades.
     * @param x Coordenadas X
     * @param y Coordenadas Y
     */
    Ciudades(double x, double y) {
        this.horizontal = x;
        this.vertical = y;
    }

    /**
     * Método get para obtener las coordenadas X.
     * @return  Coordenadas x
     */
    public double getX() {
        return horizontal;
    }

    /**
     * Método get para obtener las coordenadas Y.
     * @return  Coordenadas y
     */
    public double getY() {
        return  vertical;
    }
}
