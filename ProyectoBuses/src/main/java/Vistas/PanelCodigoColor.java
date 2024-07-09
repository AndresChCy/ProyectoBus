package Vistas;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

/**
 * PanelCodigoColor es un JPanel que muestra colores e imágenes junto con etiquetas descriptivas.
 */
public class PanelCodigoColor extends JPanel {

    // Colores estáticos para los diferentes tipos de información
    private static final Color COLOR_OCUPADO = Color.RED;
    private static final Color COLOR_DISPONIBLE = Color.WHITE;
    private static final Color COLOR_SELECCIONADO = Color.YELLOW;
    private static final Color COLOR_PREFERENCIAL = Color.BLUE;

    /**
     * Constructor de PanelCodigoColor.
     * Configura el diseño del panel y agrega etiquetas coloreadas e imágenes con texto descriptivo.
     */
    public PanelCodigoColor() {
        this.setLayout(null); // Establece el layout a nulo para posicionar manualmente los componentes
        this.setBackground(new Color(55, 55, 55)); // Establece el color de fondo del panel

        Font fuente = new Font("Century Gothic", Font.PLAIN, 14); // Fuente para las etiquetas

        // Agrega etiquetas coloreadas con texto descriptivo
        agregarEtiquetaColoreada("OCUPADO", COLOR_OCUPADO, fuente);
        agregarEtiquetaColoreada("SELECCIONADO", COLOR_SELECCIONADO, fuente);
        agregarEtiquetaColoreada("DISPONIBLE", COLOR_DISPONIBLE, fuente);
        agregarEtiquetaColoreada("PREFERENCIAL", COLOR_PREFERENCIAL, fuente);

        // Agrega etiquetas con imágenes y texto descriptivo
        agregarEtiquetaImagen("ESTANDAR", "/estandar.png", fuente);
        agregarEtiquetaImagen("SEMI CAMA", "/semicama.png", fuente);
        agregarEtiquetaImagen("SALÓN CAMA", "/salóncama.png", fuente);
        agregarEtiquetaImagen("PREMIUM", "/premium.png", fuente);
    }

    /**
     * Agrega una etiqueta coloreada con un color específico y texto descriptivo.
     *
     * @param texto Texto descriptivo de la etiqueta.
     * @param color Color de fondo de la etiqueta.
     * @param font Fuente para el texto de la etiqueta.
     */
    private void agregarEtiquetaColoreada(String texto, Color color, Font font) {
        JPanel panelColor = new JPanel();
        panelColor.setBackground(color);

        JLabel etiqueta = new JLabel(texto);
        etiqueta.setForeground(Color.WHITE); // Color del texto
        etiqueta.setFont(font); // Aplica la fuente

        // Agrega la etiqueta coloreada al panel
        this.add(panelColor);
        this.add(etiqueta);
    }

    /**
     * Agrega una etiqueta con una imagen específica y texto descriptivo.
     *
     * @param texto Texto descriptivo de la etiqueta.
     * @param rutaImagen Ruta de la imagen a cargar.
     * @param font Fuente para el texto de la etiqueta.
     */
    private void agregarEtiquetaImagen(String texto, String rutaImagen, Font font) {
        // Carga la imagen desde el recurso del proyecto
        ImageIcon icono = new ImageIcon(Objects.requireNonNull(getClass().getResource(rutaImagen)));

        // Crea una etiqueta con la imagen cargada
        JLabel etiquetaImagen = new JLabel(icono);

        // Crea una etiqueta de texto descriptivo
        JLabel etiquetaTexto = new JLabel(texto);
        etiquetaTexto.setForeground(Color.WHITE); // Color del texto
        etiquetaTexto.setFont(font); // Aplica la fuente

        // Agrega la etiqueta de imagen y la etiqueta de texto al panel
        this.add(etiquetaImagen);
        this.add(etiquetaTexto);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        redimensionarComponentes(); // Redimensiona y posiciona los componentes en el panel
    }

    /**
     * Redimensiona y posiciona los componentes en el panel de acuerdo al tamaño actual del panel.
     */
    private void redimensionarComponentes() {
        int ancho = getWidth();
        int alto = getHeight();

        int tamanoCuadro = Math.min(ancho / 4, alto / 10); // Calcula el tamaño máximo para los cuadros o imágenes
        int anchoRectangulo = 2 * tamanoCuadro; // Ancho preferencial para las imágenes rectangulares

        int x = 10; // Posición inicial en X
        int y = 10; // Posición inicial en Y

        // Obtiene todos los componentes agregados al panel
        Component[] componentes = getComponents();

        // Itera sobre los componentes, ajustando su tamaño y posición
        for (int i = 0; i < componentes.length; i += 2) {
            Component cuadroOImagen = componentes[i];
            Component etiqueta = componentes[i + 1];

            if (cuadroOImagen instanceof JPanel) { // Si es un cuadro de color
                cuadroOImagen.setBounds(x, y, tamanoCuadro, tamanoCuadro); // Posiciona y ajusta el tamaño del cuadro
                etiqueta.setBounds(x + tamanoCuadro + 10, y, ancho - tamanoCuadro - 20, tamanoCuadro); // Posiciona la etiqueta de texto
                y += tamanoCuadro + 10; // Incrementa la posición en Y para el siguiente componente
            } else if (cuadroOImagen instanceof JLabel etiquetaImagen) { // Si es una etiqueta con imagen
                ImageIcon icono = (ImageIcon) etiquetaImagen.getIcon(); // Obtiene el icono de la imagen
                JLabel etiquetaTexto = (JLabel) etiqueta; // Convierte la etiqueta de texto

                // Ajusta el ancho de la etiqueta de imagen según el texto específico
                int anchoEtiqueta = (etiquetaTexto.getText().equals("SALÓN CAMA") || etiquetaTexto.getText().equals("PREMIUM")) ? anchoRectangulo : tamanoCuadro;

                // Escala la imagen para que se ajuste al tamaño adecuado
                Image imagenEscalada = icono.getImage().getScaledInstance(anchoEtiqueta, tamanoCuadro, Image.SCALE_SMOOTH);
                etiquetaImagen.setIcon(new ImageIcon(imagenEscalada)); // Establece la imagen escalada
                etiquetaImagen.setBounds(x, y, anchoEtiqueta, tamanoCuadro); // Posiciona y ajusta el tamaño de la etiqueta de imagen
                etiqueta.setBounds(x + anchoEtiqueta + 10, y, ancho - anchoEtiqueta - 20, tamanoCuadro); // Posiciona la etiqueta de texto
                y += tamanoCuadro + 10; // Incrementa la posición en Y para el siguiente componente
            }
        }
    }
}