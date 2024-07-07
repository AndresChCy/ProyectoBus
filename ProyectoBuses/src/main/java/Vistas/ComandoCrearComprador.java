package Vistas;

import Modelo.Asiento;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ComandoCrearComprador implements Comandos{
    private ArrayList<Asiento> paneles;
    private JPanel panelActual;
    public ComandoCrearComprador(JPanel panelActual){
        this.panelActual = panelActual;
        paneles = new ArrayList<>();
    }
    public void addPanel(Asiento asiento){
        paneles.add(asiento);
    }
    public void borrarPanel(Asiento asiento){
        paneles.remove(asiento);
    }
    public ArrayList getList(){return paneles;}
    public void execute(){
        if (paneles.size()!= 0) {
            CardLayout layout = (CardLayout) panelActual.getLayout();
            Comandos atras = new ComandoRetroceder(panelActual, layout);
            Comandos seguir = new ComandoAvanzar(panelActual, layout);
            Comandos limpiar = new ComandoLimpiar(panelActual);
            ComandoRegistrarPasajeros registrar = new ComandoRegistrarPasajeros();
            OperadorComandos oc1 = new OperadorComandos(limpiar);
            oc1.addComando(atras);
            PanelInformacionPasajero panel = new PanelInformacionPasajero(oc1, seguir,paneles.get(0));
            panelActual.add(panel);
            registrar.addPanel(panel);
            for (int i = 1; i < paneles.size(); i++) {
                panel = new PanelInformacionPasajero(new OperadorComandos(atras), seguir,paneles.get(i));
                panelActual.add(panel, "Info Pasajero " + i);
                registrar.addPanel(panel);
            }
            OperadorComandos terminar  = new OperadorComandos(registrar);
            terminar.addComando(seguir);
            terminar.addComando(limpiar);
            terminar.addComando(new ComandoResetear(paneles));
            panelActual.add(new PanelConfirmarCompra(terminar,atras), "Last");

        }
        else throw new RuntimeException("No se seleccionaron asientos");
    }
}
