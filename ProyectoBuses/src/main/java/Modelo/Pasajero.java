package Modelo;

/**
 * Clase que representa a un pasajero.
 */
public class Pasajero {
    private String nombre;
    private String apellido;
    private String correo;
    private Descuentos descuento;

    /**
     * Constructor de la clase Pasajero.
     * @param nombre    Nombre del pasajero.
     * @param correo    Correo del pasajero.
     */
    public Pasajero(String nombre,String apellido, String correo,Descuentos descuento) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.descuento =descuento;
    }

    /**
     * Método get para obtener el nombre del pasajero.
     * @return  Nombre del pasajero.
     */
    public String getNombre() {
        return nombre;
    }
    public String getApellido(){return apellido;}

    /**
     * Método get para obtener el correo del pasajero.
     * @return  Correo del pasajero.
     */
    public String getCorreo() {
        return correo;
    }
    public Descuentos getDescuento(){return descuento;}

}

