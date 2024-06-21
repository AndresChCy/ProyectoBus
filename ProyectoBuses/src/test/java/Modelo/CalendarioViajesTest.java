package Modelo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class CalendarioViajesTest {
    private CalendarioViajes viajes;

    @BeforeEach
    void setUp(){
        viajes = CalendarioViajes.getInstance();
    }

    @Test
    @DisplayName("Se ordena correctamente")
    void OrdenarViajesTest(){
        LocalDateTime t1 = LocalDateTime.now().plusSeconds(5);
        LocalDateTime t2 = LocalDateTime.now().plusSeconds(15);
        LocalDateTime t3 = LocalDateTime.now().plusSeconds(30);

        ViajeBus viaje1 = new ViajeBus(new Bus(),Ciudades.TEMUCO,Ciudades.CHILLAN,t1,1);
        ViajeBus viaje2 = new ViajeBus(new Bus(),Ciudades.TEMUCO,Ciudades.CHILLAN,t2,2);
        ViajeBus viaje3 = new ViajeBus(new Bus(),Ciudades.TEMUCO,Ciudades.CHILLAN,t3,3);
        viajes.añadirViaje(viaje2);
        viajes.añadirViaje(viaje3);
        viajes.añadirViaje(viaje1);
        viajes.ordenarViajes(Ciudades.TEMUCO,Ciudades.CHILLAN, LocalDate.now());
        assertEquals(viaje1, viajes.getCalendario()[Ciudades.TEMUCO.ordinal()][Ciudades.CHILLAN.ordinal()]
        [0].get(0));
    }

}