package Vistas;

import Modelo.CalendarioViajes;
import Modelo.Descuentos;
import Modelo.Pasaje;
import Modelo.Pasajero;

import java.util.ArrayList;

public class ComandoRegistrarPasajeros implements Comandos{
    private ArrayList<PanelInformacionPasajero> paneles;
    public ComandoRegistrarPasajeros(){
        paneles = new ArrayList<>();
    }
    public void addPanel(PanelInformacionPasajero panel){
        paneles.add(panel);
    }

    public void execute(){
        String nombre;
        String apellido;
        String correo;
        Descuentos tipo = Descuentos.NO;
        Pasajero pasajero;
        Pasaje pasaje;
        for (int i=0;i<paneles.size();i++){
            nombre = paneles.get(i).getNombre();
            apellido = paneles.get(i).getApellido();
            correo = paneles.get(i).getCorreo();
            pasajero = new Pasajero(nombre,apellido,correo,tipo);
            pasaje =CalendarioViajes.getInstance().getViaje().comprarPasaje(pasajero,paneles.get(i).getAsiento());
            pasaje.imprimir();
        }
    }
}
