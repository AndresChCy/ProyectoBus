package Vistas;

import Modelo.CalendarioViajes;

import java.awt.*;
import java.io.IOException;

public class ComandoGuardar implements Comandos{
    public ComandoGuardar(){}
    public void execute(){
        try {
            CalendarioViajes.getInstance().Guardar();
        }catch(IOException e){throw new RuntimeException("No se pudo guardar.");}

    }
}
