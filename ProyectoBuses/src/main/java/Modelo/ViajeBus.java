package Modelo;

import java.time.*;

public class ViajeBus {
    private LocalDateTime fecha;
    private Ciudades origen;
    private Ciudades destino;
    public ViajeBus(LocalDateTime fecha,Ciudades origen, Ciudades destino ){
        this.fecha = fecha;
        this.origen = origen;
        this.destino = destino;
    }
}
