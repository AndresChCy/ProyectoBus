package Vistas;

import Modelo.CalendarioViajes;

import java.awt.*;
import java.io.IOException;

/**
 * Comando para guardar y serializar CalendarioViajes
 */
public class ComandoGuardar implements Comandos{
    /**
     * Constructor
     */
    public ComandoGuardar(){}

    /**
     * Serializa el CalendarioViajes
     */
    public void execute(){
        try {
            CalendarioViajes.getInstance().Guardar();
        }catch(IOException e){throw new RuntimeException("No se pudo guardar.");}

    }
}
