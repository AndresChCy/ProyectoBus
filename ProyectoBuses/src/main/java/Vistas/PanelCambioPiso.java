package Vistas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelCambioPiso extends JPanel {
    FuentesPersonalizadas fp;
    final static String mensaje = "Piso: ";
    final static String fuente = "Kristen ITC";
    private int pisoActual = 1; // Piso inicial
    private final int PISO_MIN = 1;
    private final int PISO_MAX = 9;
    private JLabel labelMensaje;
    private JButton botonUp;
    private JButton botonDown;

    public PanelCambioPiso() {
        this.setBackground(Color.CYAN);
        fp = new FuentesPersonalizadas(mensaje, fuente);
        setLayout(null);
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        labelMensaje = new JLabel(mensaje + pisoActual, SwingConstants.CENTER);
        labelMensaje.setFont(new Font(fuente, Font.BOLD, 20)); // Establecer fuente temporalmente
        add(labelMensaje);

        botonUp = new JButton();
        botonDown = new JButton();

        botonUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cambiarPiso(1);
            }
        });

        botonDown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cambiarPiso(-1);
            }
        });

        add(botonUp);
        add(botonDown);

    }

    private void cambiarPiso(int cambio) {
        int nuevoPiso = pisoActual + cambio;
        if (nuevoPiso >= PISO_MIN && nuevoPiso <= PISO_MAX) {
            pisoActual = nuevoPiso;
            labelMensaje.setText(mensaje + pisoActual);
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
        labelMensaje.setFont(new Font(fuente, Font.BOLD, tamanoFuente));

        labelMensaje.setBounds(0, 0, anchoMensaje, altoPanel);

        int altoBoton = altoPanel;
        int posYBoton = 0;

        botonUp.setFont(new Font(fuente, Font.BOLD, tamanoFuente));
        botonUp.setBounds(anchoMensaje, posYBoton, anchoBoton, altoBoton);

        botonDown.setFont(new Font(fuente, Font.BOLD, tamanoFuente));
        botonDown.setBounds(anchoMensaje + anchoBoton, posYBoton, anchoBoton, altoBoton);
    }
}
