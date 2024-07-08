package Modelo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

/**
 * Clase que representa un Pasaje.
 */
public class Pasaje {
    private Pasajero pasajero;
    private Asiento asiento;
    private Ciudades destino;
    private Ciudades origen;
    private int numAsiento;
    private int viajePrecio;
    private LocalDateTime fecha;
    private Descuentos descuento;

    /**
     * Constructor de Pasaje que inicializa las variables.
     * @param pasajero  Pasajero dueño del pasaje.
     * @param asiento   Asiento del pasajero.
     * @param origen    Ciudad de origen del viaje.
     * @param destino   Ciudad de destino del viaje.
     * @param fecha     Fecha del viaje.
     * @param precio    Precio del viaje.
     * @param descuento Descuento aplicado al viaje.
     */
    public Pasaje(Pasajero pasajero, Asiento asiento, Ciudades origen, Ciudades destino, LocalDateTime fecha, int precio, Descuentos descuento) {
        this.pasajero = pasajero;
        this.asiento = asiento;
        this.numAsiento = asiento.getNumero();
        this.origen = origen;
        this.destino = destino;
        this.fecha = fecha;
        this.viajePrecio = precio;
        this.descuento = descuento;
    }

    /**
     * Método para obtener información del pasaje.
     * @return  String con la información en formato natural de la respectiva variable.
     */
    public String obtenerPasaje() {
        return "Nombre: " + pasajero.getNombre() + " " + pasajero.getApellido() + "\n" +
                "Número de Asiento: " + asiento.getNumero() +
                " TIPO: " + asiento.getCategoria() + "\n" +
                "Origen: " + origen + "\n" +
                "Destino: " + destino + "\n" +
                "Fecha: " + fecha + "\n" +
                "DESCUENTO: " + descuento.toString() + "\n" +
                "Precio del Viaje: " + viajePrecio + "\n";
    }

    /**
     * Método para ponerle un nombre al pasaje
     * @return el nombre del pasaje
     */
    public String nombrar() {
        String titulo = "Pasajero";
        titulo += origen.ordinal();
        titulo += destino.ordinal();
        titulo += fecha.getDayOfYear();
        titulo += fecha.getHour();
        titulo += fecha.getMinute();
        titulo += numAsiento;
        return titulo;
    }

    /**
     * Método para serializar en un archivo txt el pasaje
     */
    public void imprimir() {
        FileWriter informe = null;
        PrintWriter escritor;
        try {
            // Ruta relativa al directorio del proyecto
            String ruta = System.getProperty("user.dir") + "/Test pasajes/";
            File archivo = new File(ruta + nombrar() + ".txt");
            File carpeta = archivo.getParentFile();
            if (!carpeta.exists()){
                carpeta.mkdirs();
            }
            informe = new FileWriter(archivo);
            escritor = new PrintWriter(informe);

            escritor.println("----PASAJE BUS----");
            escritor.println(obtenerPasaje());
            escritor.println("\n");

        } catch (IOException e) {
            System.out.println("Error al crear el archivo: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (informe != null) {
                try {
                    informe.close();
                } catch (IOException e) {
                    System.out.println("Error al cerrar el FileWriter: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        }
    }
}
