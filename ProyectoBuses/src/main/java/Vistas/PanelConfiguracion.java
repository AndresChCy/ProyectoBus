package Vistas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

public class PanelConfiguracion extends JPanel {
    private Selector selectorTema;
    private JButton botonCerrar;
    private JPanel panel;
    private JPanel panelConfig;

    public PanelConfiguracion(Temas tema, JPanel panel) {
        setLayout(null); // Usar layout nulo para posicionar componentes manualmente
        setBackground(Temas.temaSeleccionado.colorSecundario);
        this.panel = panel;
        List<Temas.Tema> temas = tema.getTemas();
        String[] elementosSel = new String[temas.size()];
        for (int i = 0; i < temas.size(); i++) {
            elementosSel[i] = "Tema " + i;
        }
        selectorTema = new Selector("Cambiar tema", elementosSel);
        selectorTema.getComboBox().addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                tema.seleccionarTema(selectorTema.getComboBox().getSelectedIndex());
            }
        });

        botonCerrar = new JButton("Cerrar");
        botonCerrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                close();
            }
        });

        panelConfig = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                int anchoPanel = getWidth();
                int altoPanel = getHeight();
                setBackground(Temas.temaSeleccionado.colorPrimario);
                int margenX = (int) (anchoPanel * 0.05); // Margen horizontal del 5%
                int margenY = (int) (altoPanel * 0.05); // Margen vertical del 5%
                int anchoDisponible = (int) (anchoPanel * 0.9); // Ancho que ocupa el 90% del panel
                int altoPanelInfo = (int) (altoPanel * 0.45); // Altura que ocupa el 45% del panel

                selectorTema.setBounds(margenX, margenY, anchoDisponible, altoPanelInfo / 2);
                botonCerrar.setBounds(anchoPanel - margenX - 100, altoPanel - margenY - 30, 100, 30);
            }
        };
        panelConfig.add(selectorTema);
        panelConfig.add(botonCerrar);
        add(panelConfig);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Llamar al método paintComponent de la superclase JPanel
        setBackground(Temas.temaSeleccionado.colorPrimario);
        int anchoPanel = getWidth(); // Obtener el ancho del panel
        int altoPanel = getHeight(); // Obtener el alto del panel

        int margenX = (int) (anchoPanel * 0.05); // Margen horizontal del 5%
        int margenY = (int) (altoPanel * 0.05); // Margen vertical del 5%
        int anchoPanelC = (int) (anchoPanel * 0.9); // Ancho que ocupa el 90% del panel
        int altoPanelC = (int) (altoPanel * 0.9); // Altura que ocupa el 90% del panel
        panelConfig.setBounds(margenX, margenY, anchoPanelC, altoPanelC);
        panelConfig.repaint();
    }

    public void open() {
        panel.setEnabled(false);
        for (Component component : panel.getComponents()) {
            component.setEnabled(false);
        }
        setVisible(true); // Mostrar el panel de configuración
    }

    public void close() {
        panel.setEnabled(true);
        for (Component component : panel.getComponents()) {
            component.setEnabled(true);
        }
        setVisible(false); // Ocultar el panel de configuración
    }
}