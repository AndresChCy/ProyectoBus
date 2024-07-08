package Vistas;

import Modelo.CalendarioViajes;
import Modelo.Descuentos;
import Modelo.Pasaje;
import Modelo.Pasajero;
import VentanaError.VentanaErr;

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
        Descuentos tipo ;
        Pasajero pasajero;
        Pasaje pasaje;
        ArrayList<Pasaje> pasajes = new ArrayList<>();

        for (int i = 0; i < paneles.size(); i++) {
            nombre = paneles.get(i).getNombre();
            apellido = paneles.get(i).getApellido();
            correo = paneles.get(i).getCorreo();
            tipo = paneles.get(i).getDescuento();
            pasajero = new Pasajero(nombre, apellido, correo, tipo);
            pasaje = CalendarioViajes.getInstance().getViaje().comprarPasaje(pasajero, paneles.get(i).getAsiento());
            pasajes.add(pasaje);
            if(nombre == null || apellido == null || correo == null || nombre.equals("Escriba aquí")
            || apellido.equals("Escriba aquí") || correo.equals("Escriba aquí")){
                pasajes.clear();
                throw new RuntimeException("Faltan datos en uno de los pasajeros.");
            }
        }
        for (Pasaje aux: pasajes){
            aux.imprimir();
        }
        new VentanaErr("Compra exitosa! Revisar pasajes en la carpeta Test Pasajes");

    }
}
