package Vistas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.List;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class SeleccionadorCiudad extends JPanel {

    private final List<String> ciudades;
    private final JComboBox<String> comboBox;
    private final DefaultComboBoxModel<String> comboBoxModel;
    private final JTextField campoTexto;
    private boolean isAdjusting = false;

    public SeleccionadorCiudad(List<String> ciudades) {
        this.ciudades = ciudades;
        this.setOpaque(false);

        comboBoxModel = new DefaultComboBoxModel<>(ciudades.toArray(new String[0]));
        comboBox = new JComboBox<>(comboBoxModel);
        comboBox.setEditable(true);

        campoTexto = (JTextField) comboBox.getEditor().getEditorComponent();
        campoTexto.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                actualizarSugerencias();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                actualizarSugerencias();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                actualizarSugerencias();
            }

            private void actualizarSugerencias() {
                if (isAdjusting) {
                    return;
                }

                isAdjusting = true;
                SwingUtilities.invokeLater(() -> {
                    String texto = campoTexto.getText().toLowerCase();
                    List<String> matches = ciudades.stream()
                            .filter(ciudad -> ciudad.toLowerCase().contains(texto))
                            .toList();
                    comboBoxModel.removeAllElements();
                    for (String match : matches) {
                        comboBoxModel.addElement(match);
                    }
                    campoTexto.setText(texto);
                    comboBox.setPopupVisible(!matches.isEmpty());
                    isAdjusting = false;
                });
            }
        });

        setLayout(new BorderLayout());
        add(comboBox, BorderLayout.CENTER);

        Font fuente = new Font("Consolas", Font.BOLD, 20);
        campoTexto.setFont(fuente);
        comboBox.setFont(fuente);

        ajustarTamanoCampoTexto();
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                ajustarTamanoCampoTexto();
            }
        });
    }

    private void ajustarTamanoCampoTexto() {
        comboBox.setPreferredSize(new Dimension(getWidth(), comboBox.getPreferredSize().height));

        revalidate();
        repaint();
    }

    public String getCiudadSeleccionada() {
        return (String) comboBox.getSelectedItem();
    }

    public void setCambiarCiudades(List<String> nuevasCiudades) {
        ciudades.clear();
        ciudades.addAll(nuevasCiudades);
        comboBoxModel.removeAllElements();
        for (String ciudad : ciudades) {
            comboBoxModel.addElement(ciudad);
        }
    }
}
