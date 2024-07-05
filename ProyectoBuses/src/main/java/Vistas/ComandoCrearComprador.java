package Vistas;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ComandoCrearComprador implements Comandos{
    private ArrayList<Integer> paneles;
    private JPanel panelActual;
    public ComandoCrearComprador(JPanel panelActual){
        this.panelActual = panelActual;
        paneles = new ArrayList<>();
    }
    public void addPanel(int i){
        paneles.add(i);
    }
    public void borrarPanel(Integer i){
        paneles.remove(i);
    }
    public ArrayList getList(){return paneles;}
    public void execute(){
        if (paneles.size()!= 0) {
            CardLayout layout = (CardLayout) panelActual.getLayout();
            Comandos atras = new ComandoRetroceder(panelActual, layout);
            Comandos seguir = new ComandoAvanzar(panelActual, layout);
            Comandos limpiar = new ComandoLimpiar(panelActual);
            OperadorComandos oc1 = new OperadorComandos(limpiar);
            oc1.addComando(atras);
            panelActual.add(new PanelInformacionPasajero(oc1, seguir));
            for (int i = 1; i < paneles.size(); i++) {
                panelActual.add(new PanelInformacionPasajero(new OperadorComandos(atras), seguir), "Info Pasajero " + i);
            }
            OperadorComandos terminar  = new OperadorComandos(seguir);
            terminar.addComando(limpiar);
            terminar.addComando(new ComandoResetear(paneles));
            panelActual.add(new PanelConfirmarCompra(terminar,atras), "Last");

        }
    }
}
