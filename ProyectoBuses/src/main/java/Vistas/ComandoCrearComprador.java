package Vistas;

import Modelo.Asiento;
import Modelo.CalendarioViajes;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Clase para crear paneles de pasajero segun una cantidad especifica en un CardLayout
 */
public class ComandoCrearComprador implements Comandos{
    private ArrayList<Asiento> paneles;
    private JPanel panelActual;
    private PanelPrecioPagar panelP;

    /**
     * Constructor
     * @param panelActual panel que usa CardLayout
     */
    public ComandoCrearComprador(JPanel panelActual){
        this.panelActual = panelActual;
        paneles = new ArrayList<>();
    }
    public void setPanelPrecio(PanelPrecioPagar panel){panelP = panel;}

    /**
     * Guarda el asiento para al momento de ejecutar usarlo en un panel
     * @param asiento el asiento a guardar
     */
    public void addPanel(Asiento asiento){
        paneles.add(asiento);
        if(panelP != null){
            panelP.actualizarPrecio((int) (panelP.getPrecio()+asiento.getMultiplicador()* CalendarioViajes.getInstance().getViaje().getPrecio()));
        }
    }

    /**
     * Quita un asiento guardado en la lista
     * @param asiento el cual quitar
     */
    public void borrarPanel(Asiento asiento){
        paneles.remove(asiento);
        if(panelP != null){
            panelP.actualizarPrecio((int)(panelP.getPrecio()-asiento.getMultiplicador()*CalendarioViajes.getInstance().getViaje().getPrecio()));
        }
    }
    public ArrayList getList(){return paneles;}

    /**
     * Crea paneles para introducir información de pasajeros segun la cantidad de asientos en la lista
     * Tambien al final del cardLayout coloca un panel final para confirmar la compra
     */
    @Override
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
