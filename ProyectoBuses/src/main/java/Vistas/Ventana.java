package Vistas;

import javax.swing.JFrame;
import java.awt.*;

/**
 * La clase Ventana representa la ventana principal de la aplicación.
 * Extiende JFrame y contiene un panel principal donde se muestran los componentes.
 */
public class Ventana extends JFrame {

    /**
     * Constructor de la clase Ventana.
     * Configura las propiedades de la ventana principal y agrega el panel principal.
     */
    public Ventana() {
        // Configurar título, tamaño y cierre de la ventana
        this.setTitle("Reserva de Buses");
        this.setSize(1600, 900);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Establecer el tamaño mínimo permitido para la ventana
        this.setMinimumSize(new Dimension(800, 600));

        // Crear e inicializar el panel principal
        PanelPrincipal panelPrincipal = new PanelPrincipal();

        // Agregar el panel principal al centro de la ventana
        this.add(panelPrincipal, BorderLayout.CENTER);

        // Hacer visible la ventana
        this.setVisible(true);
    }

    /**
     * Método principal de la aplicación. Crea una instancia de la clase Ventana.
     * @param args Argumentos de línea de comandos (no se utilizan en esta aplicación).
     */
    public static void main(String[] args) {
        // Crear una nueva instancia de la ventana principal
        new Ventana();
    }
}

