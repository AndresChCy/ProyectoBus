package Vistas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random; // Solo para probar

public class BotonAsiento extends JButton {
    private static final Map<String, String> RUTAS_IMAGENES = new HashMap<>();
    static {
        RUTAS_IMAGENES.put("Estándar", "/estándar.png");
        RUTAS_IMAGENES.put("Semi Cama", "/semicama.png");
        RUTAS_IMAGENES.put("Salón Cama", "/salóncama.png");
        RUTAS_IMAGENES.put("Premium", "/premium.png");
        RUTAS_IMAGENES.put("Vacío", "/vacío.png");
    }

    private static final Color COLOR_NORMAL = Color.WHITE;
    private static final Color COLOR_MOUSE_OVER = Color.LIGHT_GRAY;
    private static final Color COLOR_CLICKED = Color.YELLOW;
    private static final Color COLOR_COMPRADO = Color.RED;
    private static final Color COLOR_PREFERENCIAL = Color.BLUE;

    private String tipoAsiento;
    private BufferedImage imagenBoton;
    private Color backgroundColor;

    private boolean clicked = false;
    private boolean comprado = false;
    private boolean preferencial = false;

    public BotonAsiento(String tipoAsiento) {
        this.tipoAsiento = tipoAsiento;
        cargarIcono();
        initComponent();

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (!clicked && !comprado && !preferencial) {
                    setBackground(COLOR_MOUSE_OVER);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (!clicked && !comprado && !preferencial) {
                    setBackground(COLOR_NORMAL);
                }
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if (!comprado && !preferencial) {
                    clicked = !clicked;
                    if (clicked) {
                        setBackground(COLOR_CLICKED);
                    } else {
                        setBackground(COLOR_NORMAL);
                    }
                }
            }
        });

        // Simular estado comprado aleatorio
        simularEstadoCompradoAleatorio(); // Solo para probar
    }

    private void initComponent() {
        setBorderPainted(false);
        setContentAreaFilled(false);
        setOpaque(true);
        setBackground(COLOR_NORMAL);
        this.backgroundColor = COLOR_NORMAL;

        this.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                redimensionarIconos();
            }
        });
    }

    private void cargarIcono() {
        String rutaImagen = RUTAS_IMAGENES.get(tipoAsiento);
        if (rutaImagen != null) {
            try {
                imagenBoton = javax.imageio.ImageIO.read(getClass().getResource(rutaImagen));
            } catch (IOException ex) {
                System.out.println("Error al cargar imagen: " + ex.getMessage());
            }
        } else {
            System.out.println("No se encontró la ruta de imagen para el tipo de asiento: " + tipoAsiento);
        }
    }

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

    @Override
    public void setBackground(Color bg) {
        super.setBackground(bg);
        this.backgroundColor = bg;
    }

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

    public void setPreferencial(boolean preferencial) {
        this.preferencial = preferencial;
        if (preferencial) {
            setBackground(COLOR_PREFERENCIAL);
        } else {
            setBackground(COLOR_NORMAL);
        }
    }

    // Método temporal para simular aleatoriamente si el asiento está comprado o no
    public void simularEstadoCompradoAleatorio() {
        Random random = new Random();
        boolean estadoAleatorio = random.nextBoolean();
        setComprado(estadoAleatorio);
    }

    public String getTipoAsiento() {
        return tipoAsiento;
    }
}
