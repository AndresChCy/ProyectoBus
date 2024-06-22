package Modelo;

/**
 * Clase que representa a un pasajero.
 */
public class Pasajero {
    private String nombre;
    private String Correo;

    /**
     * Constructor de la clase Pasajero.
     * @param nombre    Nombre del pasajero.
     * @param correo    Correo del pasajero.
     */
    public Pasajero(String nombre, String correo) {
        this.nombre = nombre;
        this.Correo = correo;
    }

    /**
     * Método get para obtener el nombre del pasajero.
     * @return  Nombre del pasajero.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Método get para obtener el correo del pasajero.
     * @return  Correo del pasajero.
     */
    public String getCorreo() {
        return Correo;
    }
}

