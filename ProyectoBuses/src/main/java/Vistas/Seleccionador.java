package Vistas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.List;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class Seleccionador extends JPanel {

    private final List<String> ciudades;
    private final JComboBox<String> comboBox;
    private final DefaultComboBoxModel<String> comboBoxModel;
    private final JTextField campoTexto;
    private boolean isAdjusting = false;

    /**
     * Constructor de la clase SeleccionadorCiudad.
     * Inicializa el componente con una lista de ciudades.
     * @param ciudades Lista de ciudades disponibles para la selección.
     */
    public Seleccionador(List<String> ciudades) {
        this.ciudades = ciudades;
        this.setOpaque(false); // Hacer el fondo transparente

        // Modelo del ComboBox con la lista de ciudades
        comboBoxModel = new DefaultComboBoxModel<>(ciudades.toArray(new String[0]));
        comboBox = new JComboBox<>(comboBoxModel);
        comboBox.setEditable(true); // Permitir edición del texto en el ComboBox

        // Obtener el campo de texto interno del ComboBox
        campoTexto = (JTextField) comboBox.getEditor().getEditorComponent();

        // Agregar un listener al documento para actualizar las sugerencias al editar
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
                    campoTexto.setText(texto); // Establecer el texto filtrado
                    comboBox.setPopupVisible(!matches.isEmpty()); // Mostrar el desplegable si hay coincidencias
                    isAdjusting = false;
                });
            }
        });

        // Configurar el layout del panel y añadir el ComboBox al centro
        setLayout(new BorderLayout());
        add(comboBox, BorderLayout.CENTER);

        // Configurar la fuente para el campo de texto y el ComboBox
        Font fuente = new Font("Consolas", Font.BOLD, 20);
        campoTexto.setFont(fuente);
        comboBox.setFont(fuente);

        // Ajustar el tamaño del campo de texto al redimensionar el panel
        ajustarTamanoCampoTexto();
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                ajustarTamanoCampoTexto();
            }
        });
    }

    /**
     * Método privado para ajustar el tamaño del campo de texto del ComboBox.
     * Ajusta la anchura del campo de texto al ancho actual del ComboBox.
     */
    private void ajustarTamanoCampoTexto() {
        comboBox.setPreferredSize(new Dimension(getWidth(), comboBox.getPreferredSize().height));

        revalidate(); // Validar el layout para reflejar los cambios
        repaint(); // Repintar el componente
    }

    /**
     * Método para obtener la ciudad seleccionada en el ComboBox.
     * @return Nombre de la ciudad seleccionada.
     */
    public String getCiudadSeleccionada() {
        return (String) comboBox.getSelectedItem();
    }

    /**
     * Método para cambiar la lista de ciudades disponibles en el ComboBox.
     * @param nuevasCiudades Nueva lista de ciudades.
     */
    public void setCambiarCiudades(List<String> nuevasCiudades) {
        ciudades.clear();
        ciudades.addAll(nuevasCiudades);
        comboBoxModel.removeAllElements();
        for (String ciudad : ciudades) {
            comboBoxModel.addElement(ciudad);
        }
    }
}