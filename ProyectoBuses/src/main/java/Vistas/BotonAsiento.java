package Vistas;

import Modelo.Asiento;
import Modelo.CalendarioViajes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Clase que representa un botón personalizado para un asiento del bus.
 */
public class BotonAsiento extends JButton {
    // Mapa estático para mapear tipos de asiento a sus respectivas rutas de imágenes
    private static final Map<String, String> RUTAS_IMAGENES = new HashMap<>();
    static {
        RUTAS_IMAGENES.put("Estándar", "/estándar.png");
        RUTAS_IMAGENES.put("SemiCama", "/semicama.png");
        RUTAS_IMAGENES.put("Salón Cama", "/salóncama.png");
        RUTAS_IMAGENES.put("Premium", "/premium.png");
        RUTAS_IMAGENES.put("Vacío", "/vacío.png");
    }

    // Colores estáticos para diferentes estados del botón
    private static final Color COLOR_NORMAL = Color.WHITE; // Color normal del botón
    private static final Color COLOR_MOUSE_OVER = Color.LIGHT_GRAY; // Color al pasar el mouse
    private static final Color COLOR_CLICKED = Color.YELLOW; // Color al hacer clic
    private static final Color COLOR_COMPRADO = Color.RED; // Color cuando el asiento está comprado
    private static final Color COLOR_PREFERENCIAL = Color.BLUE; // Color cuando el asiento es preferencial

    // Propiedades del botón y estado de control
    private final Asiento asiento; // Tipo de asiento asociado al botón
    private BufferedImage imagenBoton; // Imagen del botón cargada desde archivo

    private boolean clicked = false; // Indica si el botón ha sido clickeado
    private boolean comprado = false; // Indica si el asiento está comprado
    private boolean preferencial = false; // Indica si el asiento es preferencial

    /**
     * Constructor de la clase BotonAsiento.
     */
    public BotonAsiento(Asiento asiento,ComandoCrearComprador informar) {
        this.asiento = asiento;
        cargarIcono(); // Cargar la imagen asociada al tipo de asiento
        initComponent(); // Inicializar componentes y configurar listeners

        // Configurar listeners para eventos de ratón
        if(asiento != null) {
            this.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    // Cambiar color de fondo al pasar el mouse si no está en estado especial
                    if (!clicked && !comprado && !preferencial) {
                        setBackground(COLOR_MOUSE_OVER);
                    }
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    // Restaurar color de fondo al salir del mouse si no está en estado especial
                    if (!clicked && !comprado && !preferencial) {
                        setBackground(COLOR_NORMAL);
                    }
                }

                @Override
                public void mouseClicked(MouseEvent e) {
                    // Alternar el estado "clickeado" y cambiar el color de fondo
                    if (!comprado && !preferencial) {
                        clicked = !clicked;
                        if (clicked) {
                            setBackground(COLOR_CLICKED);
                            informar.addPanel(asiento);
                        } else {
                            setBackground(COLOR_NORMAL);
                            informar.borrarPanel(asiento);
                        }
                    }
                }
            });
        }
        try {
            if (Objects.requireNonNull(asiento).isReservado(CalendarioViajes.getInstance().getViaje())) {
                setComprado(true);
            }
        }catch(Exception ignored){}


    }

    /**
     * Inicializa los componentes del botón.
     */
    private void initComponent() {
        setBorderPainted(false);
        setContentAreaFilled(false);
        setOpaque(true);
        setBackground(COLOR_NORMAL);

        // Listener para redimensionar la imagen cuando el componente cambia de tamaño
        this.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                redimensionarIconos();
            }
        });
    }

    /**
     * Carga la imagen asociada al tipo de asiento desde el mapa estático de rutas de imágenes.
     */
    private void cargarIcono() {
        String rutaImagen;
        try {
            rutaImagen = RUTAS_IMAGENES.get(asiento.getCategoria());
        }catch (Exception e){rutaImagen = RUTAS_IMAGENES.get("Vacío");}
        if (rutaImagen != null) {
            try {
                imagenBoton = javax.imageio.ImageIO.read(Objects.requireNonNull(getClass().getResource(rutaImagen)));
            } catch (IOException ex) {
                System.out.println("Error al cargar imagen: " + ex.getMessage());
            }
        } else {
            System.out.println("No se encontró la ruta de imagen para el tipo de asiento: " /*+ asiento.getCategoria()*/);
        }
    }

    /**
     * Redimensiona la imagen del botón al tamaño actual del botón.
     */
    public void redimensionarIconos() {
        if (imagenBoton != null) {
            int anchoBoton = this.getWidth();
            int altoBoton = this.getHeight();
            if (anchoBoton > 0 && altoBoton > 0) {
                Image img = imagenBoton.getScaledInstance(anchoBoton, altoBoton, Image.SCALE_SMOOTH);
                ImageIcon icono = new ImageIcon(img);
                this.setIcon(icono);
            }
        }
    }

    /**
     * Establece el color de fondo del botón y actualiza el estado "comprado".
     * @param comprado true si el asiento está comprado, false si no lo está.
     */
    public void setComprado(boolean comprado) {
        this.comprado = comprado;
        if (comprado) {
            setBackground(COLOR_COMPRADO);
        } else {
            if (!clicked && !preferencial) {
                setBackground(COLOR_NORMAL);
            } else if (clicked) {
                setBackground(COLOR_CLICKED);
            }
        }
    }

    /**
     * Establece el color de fondo del botón y actualiza el estado "preferencial".
     * @param preferencial true si el asiento es preferencial, false si no lo es.
     */
    public void setPreferencial(boolean preferencial) {
        this.preferencial = preferencial;
        if (preferencial) {
            setBackground(COLOR_PREFERENCIAL);
        } else {
            setBackground(COLOR_NORMAL);
        }
    }

    /**
     * Obtiene el tipo de asiento asociado al botón.
     * @return String con el tipo de asiento.
     */
    public String getTipoAsiento() {
        try {
            return asiento.getCategoria();
        }catch (Exception e){return "Vacío"; }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (asiento != null) {
            String numero = String.valueOf(asiento.getNumero());
            g.setFont(new Font("Roboto", Font.BOLD, 15));
            g.setColor(Color.BLACK);
            FontMetrics metrics = g.getFontMetrics(g.getFont());
            int x = (getWidth() - metrics.stringWidth(numero)) / 2;
            int y = ((getHeight() - metrics.getHeight()) / 2) + metrics.getAscent();
            g.drawString(numero, x, y);
        }
    }
}