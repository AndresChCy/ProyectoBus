package Vistas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/**
 * Clase SeleccionadorElementos
 *
 * Panel personalizado que contiene un JComboBox con autocompletado dinámico
 * basado en una lista de elementos proporcionada al inicializar el componente.
 */
public class SeleccionadorElementos extends JPanel {

    private final JComboBox<String> comboBox;
    private final DefaultComboBoxModel<String> comboBoxModel;
    private final JTextField campoTexto;

    /**
     * Constructor de la clase SeleccionadorElementos.
     * Inicializa el componente con una lista de elementos.
     *
     * @param elementos Lista de elementos disponibles para la selección.
     */
    public SeleccionadorElementos(String[] elementos) {
        this.setOpaque(false); // Hacer el fondo transparente

        // Modelo del ComboBox con la lista de elementos
        comboBoxModel = new DefaultComboBoxModel<>(elementos);
        comboBox = new JComboBox<>(comboBoxModel);
        comboBox.setEditable(true); // Permitir edición del texto en el ComboBox

        // Obtener el campo de texto interno del ComboBox
        campoTexto = (JTextField) comboBox.getEditor().getEditorComponent();

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
     * Método para obtener el elemento seleccionado en el ComboBox.
     *
     * @return Elemento seleccionado en el ComboBox.
     */
    public String getElementoSeleccionado() {
        return (String) comboBox.getSelectedItem();
    }

    /**
     * Método para obtener el JComboBox utilizado.
     *
     * @return JComboBox utilizado en el componente.
     */
    public JComboBox<String> getComboBox() {
        return comboBox;
    }
}