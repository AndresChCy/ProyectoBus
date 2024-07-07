package Modelo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class PasajeTest {
    private ViajeBus viaje;
    @BeforeEach
    void setUp(){
        LocalDateTime t1 = LocalDateTime.now().plusSeconds(5);
        SintetizadorBuses sintetizadorBuses = new SintetizadorBuses(new BusBuilder());
        sintetizadorBuses.make(ModelosBus.ESTANDAR);
        Bus bus = sintetizadorBuses.getBus();
        viaje = new ViajeBus(bus,Ciudades.TEMUCO,Ciudades.CHILLAN,t1,1);
    }
    @Test
    @DisplayName("Test para imprimir pasaje")
    void testImprimirPasaje(){
        Pasajero pasajero = new Pasajero("juan","navarro","a@gmail.com");
        Pasaje pasaje = viaje.comprarPasaje(pasajero,4,Descuentos.ESTUDIANTE);
        pasaje.imprimir();
    }

}