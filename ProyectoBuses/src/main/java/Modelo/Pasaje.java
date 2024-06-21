package Modelo;

public class Pasaje {
    private Pasajero pasajero;
    private Asiento asiento;

    public Pasaje(Pasajero pasajero, Asiento asiento) {
        this.pasajero = pasajero;
        this.asiento = asiento;
    }

    public String obtenerPasaje() {
        return "Nombre: "+pasajero.getNombre()+"\n"+"Asiento: "+asiento.getNumero()+"\n";
    }
}
