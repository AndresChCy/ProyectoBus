package Vistas;

import javax.swing.*;
import java.awt.*;

public class PanelConfirmarCompra extends JPanel {

    public PanelConfirmarCompra(OperadorComandos avanzar ,Comandos atras){
        setLayout(new BorderLayout());
        OperadorComandos volver = new OperadorComandos(atras);
        add(new PanelTitulo("CONFIRMAR PAGO",volver),BorderLayout.NORTH);
        add(new BotonAvanzar(avanzar,"Pagar"),BorderLayout.CENTER);
    }
}
