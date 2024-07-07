package VentanaError;

import javax.swing.*;
import java.awt.*;

public class PanelError extends JPanel {
    public PanelError(String err){
        super();
        add(new JLabel(err), BorderLayout.CENTER);
    }
}
