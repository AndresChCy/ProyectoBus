package Vistas;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * PanelCambioPiso es un JPanel que permite cambiar el número de piso mostrado con botones de subir y bajar.
 */
public class PanelCambioPiso extends JPanel {

    // Mensaje y fuente estática para el texto del piso
    final static String mensaje = "Piso ";
    final static String fuente = "Roboto";

    private int pisoActual = 1; // Piso inicial

    private JLabel etiquetaMensaje; // Etiqueta para mostrar el mensaje de piso actual
    private JButton botonSubir; // Botón para subir el piso
    private JButton botonBajar; // Botón para bajar el piso

    private ImageIcon iconoArriba; // Icono para el botón de subir
    private ImageIcon iconoAbajo; // Icono para el botón de bajar
    private final FuentesPersonalizadas fp;

    // Logger para la clase PanelCambioPiso
    private static final Logger logger = Logger.getLogger(PanelCambioPiso.class.getName());
    private PanelAsientos panelAsientos;

    /**
     * Constructor de PanelCambioPiso.
     * Configura el fondo, la fuente y los componentes iniciales del panel.
     */
    public PanelCambioPiso(PanelAsientos panelAsientos) {
        this.panelAsientos = panelAsientos;
        try {
            // Carga los iconos de las flechas desde archivos de imagen
            iconoArriba = new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResource("/arriba.png"))));
            iconoAbajo = new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResource("/abajo.png"))));
        } catch (IOException e) {
            // Registro de error si hay un problema al cargar la imagen
            logger.log(Level.SEVERE, "Error al cargar la imagen", e);
        }

        // Configura el fondo del panel
        this.setBackground(PanelSelectorRuta.temaSeleccionado.colorSecundario);

        // Inicializa la fuente personalizada
        fp = new FuentesPersonalizadas(mensaje, fuente);

        // Establece el layout del panel como nulo para posicionar manualmente los componentes
        setLayout(null);

        // Inicializa los componentes del panel
        inicializarComponentes();
    }

    /**
     * Inicializa y configura los componentes del panel.
     */
    private void inicializarComponentes() {
        // Etiqueta para mostrar el mensaje de piso actual
        etiquetaMensaje = new JLabel(mensaje + pisoActual, SwingConstants.CENTER);
        etiquetaMensaje.setFont(new Font(fuente, Font.BOLD, 20));
        add(etiquetaMensaje);

        // Botón para subir el piso
        botonSubir = new JButton();
        botonSubir.addActionListener(e -> {
            cambiarPiso(1); // Acción al hacer clic: subir el piso
        });

        // Botón para bajar el piso
        botonBajar = new JButton();
        botonBajar.addActionListener(e -> {
            cambiarPiso(-1); // Acción al hacer clic: bajar el piso
        });

        // Agrega los botones al panel
        add(botonSubir);
        add(botonBajar);
    }

    /**
     * Método para cambiar el número de piso.
     *
     * @param cambio Cantidad de pisos a subir (+1) o bajar (-1).
     */
    private void cambiarPiso(int cambio) {
        int nuevoPiso = pisoActual + cambio;
        try {
            System.out.println("xb");
            panelAsientos.setPanelBus(nuevoPiso - 1);
            panelAsientos.repaint();

            // Verifica que el nuevo piso esté dentro del rango permitido
            int PISO_MIN = 1;
            int PISO_MAX = 9;
            if (nuevoPiso >= PISO_MIN && nuevoPiso <= PISO_MAX) {
                pisoActual = nuevoPiso; // Actualiza el piso actual
                etiquetaMensaje.setText(mensaje + pisoActual); // Actualiza el texto de la etiqueta
                repaint(); // Vuelve a pintar el panel para reflejar los cambios visuales
            }
        }catch (Exception e){System.out.println("error captao");}
    }

    /**
     * Sobrescribe el método paintComponent para pintar los componentes del panel.
     *
     * @param g Objeto Graphics para pintar los componentes.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        int anchoPanel = getWidth();
        int altoPanel = getHeight();

        // Calcula los tamaños y posiciones de los componentes basados en el tamaño actual del panel
        int anchoMensaje = (int) (anchoPanel * 0.6);
        int anchoBoton = (int) (anchoPanel * 0.2);

        // Calcula el tamaño de la fuente para el mensaje
        int tamanoFuente = fp.calcularTamanoLetras(anchoMensaje, altoPanel, g);
        etiquetaMensaje.setFont(new Font(fuente, Font.BOLD, tamanoFuente));

        // Posiciona la etiqueta del mensaje
        etiquetaMensaje.setBounds(0, 0, anchoMensaje, altoPanel);

        // Configura y posiciona el botón de subir
        botonSubir.setBackground(PanelSelectorRuta.temaSeleccionado.colorSecundario);
        botonSubir.setIcon(redimensionarIcono(iconoArriba, anchoBoton));
        botonSubir.setBounds(anchoMensaje, 0, anchoBoton, altoPanel);
        botonSubir.setBorderPainted(false);
        botonSubir.setFocusPainted(false);
        botonSubir.setContentAreaFilled(false);

        // Configura y posiciona el botón de bajar
        botonBajar.setBackground(PanelSelectorRuta.temaSeleccionado.colorSecundario);
        botonBajar.setIcon(redimensionarIcono(iconoAbajo, anchoBoton));
        botonBajar.setBounds(anchoMensaje + anchoBoton, 0, anchoBoton, altoPanel);
        botonBajar.setBorderPainted(false);
        botonBajar.setFocusPainted(false);
        botonBajar.setContentAreaFilled(false);
    }

    /**
     * Redimensiona un icono al tamaño especificado manteniendo su relación de aspecto.
     *
     * @param icono Icono a redimensionar.
     * @param ancho Nuevo ancho del icono.
     * @return ImageIcon redimensionado.
     */
    private ImageIcon redimensionarIcono(ImageIcon icono, int ancho) {
        Image img = icono.getImage();
        double relacionAspecto = (double) img.getWidth(null) / img.getHeight(null);

        int nuevoAlto = (int) (ancho / relacionAspecto);

        Image nuevaImg = img.getScaledInstance(ancho, nuevoAlto, Image.SCALE_SMOOTH);
        return new ImageIcon(nuevaImg);
    }
}