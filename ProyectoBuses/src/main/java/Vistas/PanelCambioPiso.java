package Vistas;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class PanelCambioPiso extends JPanel {
    FuentesPersonalizadas fp;
    final static String mensaje = "Piso ";
    final static String fuente = "Roboto";
    private int pisoActual = 1; // Piso inicial
    private final int PISO_MIN = 1;
    private final int PISO_MAX = 9;
    private JLabel etiquetaMensaje;
    private JButton botonSubir;
    private JButton botonBajar;
    private ImageIcon iconoArriba;
    private ImageIcon iconoAbajo;

    public PanelCambioPiso() {
        try {
            iconoArriba = new ImageIcon(ImageIO.read(getClass().getResource("/arriba.png")));
            iconoAbajo = new ImageIcon(ImageIO.read(getClass().getResource("/abajo.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.setBackground(PanelSelectorRuta.temaSeleccionado.colorSecundario);
        fp = new FuentesPersonalizadas(mensaje, fuente);
        setLayout(null);
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        etiquetaMensaje = new JLabel(mensaje + pisoActual, SwingConstants.CENTER);
        etiquetaMensaje.setFont(new Font(fuente, Font.BOLD, 20));
        add(etiquetaMensaje);

        botonSubir = new JButton();
        botonBajar = new JButton();

        botonSubir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cambiarPiso(1);
            }
        });

        botonBajar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cambiarPiso(-1);
            }
        });

        add(botonSubir);
        add(botonBajar);
    }

    private void cambiarPiso(int cambio) {
        int nuevoPiso = pisoActual + cambio;
        if (nuevoPiso >= PISO_MIN && nuevoPiso <= PISO_MAX) {
            pisoActual = nuevoPiso;
            etiquetaMensaje.setText(mensaje + pisoActual);
            repaint();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        int anchoPanel = getWidth();
        int altoPanel = getHeight();

        int anchoMensaje = (int) (anchoPanel * 0.6);
        int anchoBoton = (int) (anchoPanel * 0.2);

        String texto = mensaje + pisoActual;
        int tamanoFuente = fp.calcularTamanoLetras(anchoMensaje, altoPanel, g);
        etiquetaMensaje.setFont(new Font(fuente, Font.BOLD, tamanoFuente));

        etiquetaMensaje.setBounds(0, 0, anchoMensaje, altoPanel);

        int altoBoton = altoPanel;
        int posYBoton = 0;

        botonSubir.setBackground(PanelSelectorRuta.temaSeleccionado.colorSecundario);
        botonSubir.setIcon(redimensionarIcono(iconoArriba, anchoBoton, altoBoton));
        botonSubir.setBounds(anchoMensaje, posYBoton, anchoBoton, altoBoton);
        botonSubir.setBorderPainted(false);
        botonSubir.setFocusPainted(false);
        botonSubir.setContentAreaFilled(false);

        botonBajar.setBackground(PanelSelectorRuta.temaSeleccionado.colorSecundario);
        botonBajar.setIcon(redimensionarIcono(iconoAbajo, anchoBoton, altoBoton));
        botonBajar.setBounds(anchoMensaje + anchoBoton, posYBoton, anchoBoton, altoBoton);
        botonBajar.setBorderPainted(false);
        botonBajar.setFocusPainted(false);
        botonBajar.setContentAreaFilled(false);
    }

    private ImageIcon redimensionarIcono(ImageIcon icono, int ancho, int alto) {
        Image img = icono.getImage();
        double relacionAspecto = (double) img.getWidth(null) / img.getHeight(null);

        int nuevoAncho = ancho;
        int nuevoAlto = (int) (nuevoAncho / relacionAspecto);

        Image nuevaImg = img.getScaledInstance(nuevoAncho, nuevoAlto, Image.SCALE_SMOOTH);
        return new ImageIcon(nuevaImg);
    }
}
