package Modelo;

public enum Ciudades {
    CHILLAN(-36, -72),
    CONCEPCION(-36,-73),
    TEMUCO(-38,-72),
    SANTIAGO(-33,-70);

    private final double horizontal;
    private final double vertical;
    Ciudades(double x, double y) {
        this.horizontal = x;
        this.vertical = y;
    }

    public double getX() {
        return horizontal;
    }

    public double getY() {
        return  vertical;
    }
}
