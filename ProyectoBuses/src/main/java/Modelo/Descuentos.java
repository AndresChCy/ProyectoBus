package Modelo;

public enum Descuentos {
    NO(1),
    ESTUDIANTE(0.5),
    ADULTOMAYOR(0.6),
    SOCIO(0.4);

    private final double multiplicador;

    Descuentos(double i){
        multiplicador = i;
    }
    public double getDescuento(){return multiplicador;}

}
