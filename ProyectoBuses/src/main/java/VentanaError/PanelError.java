package VentanaError;

import javax.swing.*;
import java.awt.*;

/**
 * Panel para presentar graficamente el error ocurrido
 */
public class PanelError extends JPanel {
    public PanelError(String err){
        super();
        add(new JLabel(err), BorderLayout.CENTER);
    }
}
