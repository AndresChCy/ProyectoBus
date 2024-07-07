package Modelo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CalendarioViajesGuardarTest {
    class MiHilo1 implements Runnable{
        public MiHilo1(){}
        public void run(){
            CalendarioViajes.getInstance();
            CalendarioViajes.getInstance().llenarDatos(Ciudades.TEMUCO,Ciudades.CHILLAN, LocalDate.now());
            try {
                CalendarioViajes.getInstance().Guardar();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    class MiHilo2 implements Runnable{
        public MiHilo2(){}
        public void run(){
            CalendarioViajes.getInstance();
            CalendarioViajes.getInstance().llenarDatos(Ciudades.CHILLAN,Ciudades.CONCEPCION,LocalDate.now());
        }
    }
    @Test
    @DisplayName("no Guardar Correctamente")
    void testNoGuardar(){
        MiHilo2 miHilo = new MiHilo2();
        new Thread(miHilo).start();
        CalendarioViajes.getInstance();
        assertTrue(CalendarioViajes.getInstance().getCalendario()[Ciudades.CHILLAN.ordinal()][Ciudades.CONCEPCION.ordinal()][0].isEmpty());
    }
    @Test
    @DisplayName("Si guarda correctamente ")
    void testGuardar(){
        MiHilo1 miHilo = new MiHilo1();
        new Thread(miHilo).start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        CalendarioViajes.getInstance();
        assertFalse(CalendarioViajes.getInstance().getCalendario()[Ciudades.TEMUCO.ordinal()][Ciudades.CHILLAN.ordinal()][0].isEmpty());
    }
}