package Vistas;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class PanelConfiguracion extends JPanel {
    private final Selector selectorTema;
    private final JButton botonCerrar;
    private final JPanel panel;
    private final JPanel panelConfig;
    private final JButton botonGuardar;
    private final BufferedImage iconoGuardar;

    /**
     * Constructor de la clase PanelConfiguracion.
     * @param tema Temas para configurar la apariencia.
     * @param panel Panel al cual pertenece este panel de configuración.
     */
    public PanelConfiguracion(Temas tema, JPanel panel) {
        setLayout(null); // Usar layout nulo para posicionar componentes manualmente
        setBackground(Temas.temaSeleccionado.colorSecundario);
        this.panel = panel;

        // Inicialización del selector de temas
        List<Temas.Tema> temas = tema.getTemas();
        String[] elementosSel = new String[temas.size()];
        for (int i = 0; i < temas.size(); i++) {
            elementosSel[i] = "Tema " + i;
        }
        selectorTema = new Selector("Cambiar tema", elementosSel);
        selectorTema.getComboBox().addItemListener(e -> tema.seleccionarTema(selectorTema.getComboBox().getSelectedIndex()));

        // Inicialización del botón de cerrar
        botonCerrar = new JButton("Cerrar");
        botonCerrar.addActionListener(e -> close());

        // Cargar la imagen de guardar desde el archivo
        try {
            iconoGuardar = ImageIO.read(Objects.requireNonNull(getClass().getResource("/guardar.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Inicialización del botón de guardar
        botonGuardar = new JButton() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (iconoGuardar != null) {
                    // Obtener dimensiones del botón
                    int anchoBoton = getWidth();
                    int altoBoton = getHeight();

                    // Obtener dimensiones de la imagen
                    int anchoImagen = iconoGuardar.getWidth();
                    int altoImagen = iconoGuardar.getHeight();

                    // Calcular escala para ajustar la imagen al tamaño del botón
                    double escala = Math.min((double) anchoBoton / anchoImagen, (double) altoBoton / altoImagen);
                    int anchoFinal = (int) (anchoImagen * escala);
                    int altoFinal = (int) (altoImagen * escala);

                    // Calcular posición centrada para la imagen en el botón
                    int x = (anchoBoton - anchoFinal) / 2;
                    int y = (altoBoton - altoFinal) / 2;

                    // Dibujar la imagen escalada en el centro del botón
                    g.drawImage(iconoGuardar, x, y, anchoFinal, altoFinal, this);
                }
            }

            @Override
            public Dimension getPreferredSize() {
                // Devolver las dimensiones de la imagen como tamaño preferido del botón
                if (iconoGuardar != null) {
                    return new Dimension(iconoGuardar.getWidth(), iconoGuardar.getHeight());
                }
                return super.getPreferredSize();
            }
        };

        // Acción al pulsar el botón de guardar
        botonGuardar.addActionListener(e -> System.out.println("Guardado"));

        // Configurar diseño del botón
        botonGuardar.setOpaque(false);
        botonGuardar.setBorderPainted(false);
        botonGuardar.setContentAreaFilled(false);

        // Panel para configurar la apariencia del panel de configuración
        panelConfig = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                int anchoPanel = getWidth();
                int altoPanel = getHeight();
                setBackground(Temas.temaSeleccionado.colorPrimario);

                // Posicionar y configurar el selector de temas y el botón de cerrar
                int margenX = (int) (anchoPanel * 0.05);
                int margenY = (int) (altoPanel * 0.05);
                int anchoDisponible = (int) (anchoPanel * 0.9);
                int altoPanelInfo = (int) (altoPanel * 0.45);
                selectorTema.setBounds(margenX, margenY, anchoDisponible, altoPanelInfo / 2);
                botonCerrar.setBounds(anchoPanel - margenX - 100, altoPanel - margenY - 30, 100, 30);
            }
        };
        panelConfig.add(selectorTema);
        panelConfig.add(botonCerrar);
        panelConfig.add(botonGuardar);
        add(panelConfig);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Temas.temaSeleccionado.colorPrimario);
        int anchoPanel = getWidth();
        int altoPanel = getHeight();

        // Configurar el tamaño y posición del panel de configuración
        int margenX = (int) (anchoPanel * 0.05);
        int margenY = (int) (altoPanel * 0.05);
        int anchoPanelC = (int) (anchoPanel * 0.9);
        int altoPanelC = (int) (altoPanel * 0.9);
        panelConfig.setBounds(margenX, margenY, anchoPanelC, altoPanelC);
        panelConfig.repaint();

        // Configurar el tamaño y posición del botón de guardar
        int medidaBotonGuardar = (int) (anchoPanel * 0.1);
        int posYGuardar = altoPanel - 2 * margenY - medidaBotonGuardar;
        botonGuardar.setBounds(margenX, posYGuardar, medidaBotonGuardar, medidaBotonGuardar);
    }

    /**
     * Método para deshabilitar el panel principal y mostrar este panel de configuración.
     */
    public void open() {
        panel.setEnabled(false);
        for (Component component : panel.getComponents()) {
            component.setEnabled(false);
        }
        setVisible(true); // Mostrar el panel de configuración
    }

    /**
     * Método para habilitar el panel principal y ocultar este panel de configuración.
     */
    public void close() {
        panel.setEnabled(true);
        for (Component component : panel.getComponents()) {
            component.setEnabled(true);
        }
        setVisible(false); // Ocultar el panel de configuración
    }
}
