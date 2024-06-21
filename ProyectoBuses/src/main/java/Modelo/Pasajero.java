package Modelo;

public class Pasajero {
    private String nombre;
    private String Correo;

    public Pasajero(String nombre, String correo) {
        this.nombre = nombre;
        this.Correo = correo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return Correo;
    }
}

