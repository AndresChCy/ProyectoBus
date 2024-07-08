package Vistas;

import Modelo.CalendarioViajes;
import Modelo.Ciudades;
import Modelo.ViajeBus;

import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;

public class ComandoAsignarRuta implements Comandos{
    private Ciudades origen;
    private Ciudades destino;
    private String dia;
    private String mes ;

    public ComandoAsignarRuta() {
        origen = Ciudades.values()[0];
        destino = Ciudades.values()[0];
    }
    public void setOrigen(Ciudades origen){
        this.origen = origen;
    }
    public void setDestino(Ciudades destino){
        this.destino = destino;
    }
    public void setDia(String p1){
        this.dia = p1;
    }
    public void setMes(String p2){
        this.mes = p2;
    }
    public void execute(){
        int d;
        int m;
        try {
            d = Integer.parseInt(dia);
            m = Integer.parseInt(mes);
        }catch(RuntimeException e){throw new RuntimeException("Fecha invalida");}
        LocalDate fecha = LocalDate.of(LocalDate.now().getYear(), m, d);
        CalendarioViajes.getInstance().llenarDatos(origen, destino, fecha);
        CalendarioViajes.getInstance().apuntarDia(origen, destino, fecha);
        CalendarioViajes.getInstance().notificar();

    }
}
