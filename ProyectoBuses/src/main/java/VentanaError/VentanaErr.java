package VentanaError;

import Vistas.PanelPrincipal;

import javax.swing.*;
import java.awt.*;

public class VentanaErr extends JFrame {
    public VentanaErr(String err) {
        // Configurar título, tamaño y cierre de la ventana
        this.setTitle("Mensaje error");
        this.setSize(400, 100);
        this.setLayout(new BorderLayout());
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Establecer el tamaño mínimo permitido para la ventana
        this.setMinimumSize(new Dimension(400, 100));
        this.setMaximumSize(new Dimension(400, 100));

        // Crear e inicializar el panel principal
        PanelError panelError = new PanelError(err);

        // Agregar el panel principal al centro de la ventana
        this.add(panelError, BorderLayout.CENTER);
        JButton ok = new JButton("OK");
        ok.addActionListener(e -> this.dispose());
        this.add(ok,BorderLayout.SOUTH);

        // Hacer visible la ventana
        this.setVisible(true);
    }
}
