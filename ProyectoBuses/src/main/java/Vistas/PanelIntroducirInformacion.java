package Vistas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

/**
 * PanelIntroducirInformacion es un JPanel que contiene un mensaje y un campo de texto para introducir información.
 * El campo de texto muestra un mensaje inicial y cambia su apariencia cuando obtiene o pierde el foco.
 */
public class PanelIntroducirInformacion extends JPanel {
    private final FuentesPersonalizadas fuentesPersonalizadas; // Objeto para las fuentes personalizadas
    private final String fuente = "Roboto"; // Fuente utilizada para el mensaje y el campo de texto
    private final String mensaje; // Mensaje inicial del campo de texto
    private final JTextField campoTexto; // Campo de texto para introducir información

    /**
     * Constructor de PanelIntroducirInformacion.
     * Configura el color de fondo del panel y el mensaje inicial del campo de texto.
     * Inicializa las fuentes personalizadas y configura el comportamiento del campo de texto.
     *
     * @param mensaje Mensaje inicial para el campo de texto
     */
    public PanelIntroducirInformacion(String mensaje) {
        this.setBackground(Temas.temaSeleccionado.colorSecundario); // Establecer color de fondo del panel
        this.mensaje = mensaje;

        fuentesPersonalizadas = new FuentesPersonalizadas(mensaje, fuente); // Inicializar las fuentes personalizadas

        campoTexto = new JTextField("Escriba aquí", 8); // Crear el campo de texto con texto inicial y tamaño
        campoTexto.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (campoTexto.getText().equals("Escriba aquí")) {
                    campoTexto.setText(""); // Limpiar el texto inicial al obtener el foco
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (campoTexto.getText().isEmpty()) {
                    campoTexto.setText("Escriba aquí"); // Restaurar texto inicial si el campo está vacío al perder el foco
                }
            }
        });

        this.add(campoTexto); // Agregar el campo de texto al panel
    }

    /**
     * Método sobrescrito para dibujar el contenido personalizado del panel.
     *
     * @param g Objeto Graphics utilizado para dibujar
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Llamar al método paintComponent de la superclase JPanel

        this.setBackground(Temas.temaSeleccionado.colorSecundario);

        int anchoPanel = getWidth(); // Obtener el ancho del panel
        int altoPanel = getHeight(); // Obtener el alto del panel

        int anchoMensaje = (int) (anchoPanel * 0.3); // Ancho máximo para dibujar el mensaje
        int tamanoFuente = fuentesPersonalizadas.calcularTamanoLetras(anchoMensaje, altoPanel, g); // Calcular tamaño de la fuente
        g.setFont(new Font(fuente, Font.BOLD, tamanoFuente)); // Establecer la fuente y estilo
        FontMetrics fm = g.getFontMetrics(); // Obtener métricas de la fuente

        int posXMensaje = (int) (anchoPanel * 0.05); // Posición X para dibujar el mensaje
        int posYMensaje = (int) (altoPanel * 0.05); // Posición Y para dibujar el mensaje

        g.setColor(Color.BLACK); // Establecer color negro para el mensaje con sombra
        g.drawString(mensaje, posXMensaje + 2, posYMensaje + fm.getAscent() + 2); // Dibujar mensaje con sombra

        g.setColor(Temas.temaSeleccionado.colorTerciario); // Establecer color terciario del tema para el mensaje
        g.drawString(mensaje, posXMensaje, posYMensaje + fm.getAscent()); // Dibujar mensaje principal

        int posXCampoTexto = (int) (0.3 * anchoPanel); // Posición X para el campo de texto
        int anchoCampoTexto = (int) (0.7 * anchoPanel); // Ancho del campo de texto
        campoTexto.setFont(g.getFont()); // Establecer la fuente del campo de texto
        campoTexto.setForeground(Temas.temaSeleccionado.colorSecundario); // Color de texto del campo
        campoTexto.setBackground(Temas.temaSeleccionado.colorTerciario); // Color de fondo del campo
        campoTexto.setBounds(posXCampoTexto, 0, anchoCampoTexto, altoPanel); // Establecer límites del campo de texto
    }
    public JTextField getCampoTexto(){
        return campoTexto;
    }
}