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

    public PanelConfiguracion(Temas tema, JPanel panel){
        setBackground(Temas.temaSeleccionado.colorSecundario);
        this.panel=panel;
        List<Temas.Tema> temas = tema.getTemas();
        String[] elementosSel = new String[temas.size()];
        for (int i=0 ;i<temas.size();i++){
            elementosSel[i] = "Tema "+i;
        }
        selectorTema = new Selector("Cambiar tema",elementosSel);
        selectorTema.getComboBox().addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                tema.seleccionarTema(selectorTema.getComboBox().getSelectedIndex());
            }
        });

        botonCerrar = new BotonAvanzar(null,"Cerrar");
        botonCerrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                close();
            }
        });
        panelConfig = new JPanel(){
            @Override
            protected void paintComponent(Graphics g){
                int anchoPanel = (int) getWidth();
                int altoPanel = (int) getHeight();
                setBackground(Temas.temaSeleccionado.colorPrimario);
                int margenX = (int) (anchoPanel * 0.1); // Margen horizontal
                int margenY = (int) (altoPanel * 0.1); // Margen vertical
                int anchoPanelInfo = (int) (anchoPanel * 0.5); // Ancho de los paneles de información
                int altoPanelInfo = (int) (altoPanel * 0.2); // Altura de los paneles de información

                selectorTema.setBounds(margenX,margenY,anchoPanelInfo,altoPanelInfo);
                margenY+= (int) (altoPanel*0.2);
                botonCerrar.setBounds(margenX,margenY,anchoPanelInfo,altoPanelInfo);
            }
        };
        panelConfig.add(selectorTema);
        panelConfig.add(botonCerrar);

    }
    protected void paintComponent(Graphics g){

        super.paintComponent(g); // Llamar al método paintComponent de la superclase JPanel
        setBackground(Temas.temaSeleccionado.colorPrimario);
        int anchoPanel = getWidth(); // Obtener el ancho del panel
        int altoPanel = getHeight(); // Obtener el alto del panel

        int margenX = (int) (anchoPanel * 0.15); // Margen horizontal
        int margenY = (int) (altoPanel * 0.15); // Margen vertical
        int anchoPanelC = (int) (anchoPanel*0.8);
        int altoPanelC = (int) (altoPanel*0.8);
        panelConfig.setBackground(Temas.temaSeleccionado.colorSecundario);
        panelConfig.setBounds(margenX,margenY,anchoPanelC,altoPanelC);
        panelConfig.repaint();

       /* margenX = (int) (anchoPanel * 0.1); // Margen horizontal
        margenY = (int) (altoPanel * 0.1); // Margen vertical
        int anchoPanelInfo = (int) (anchoPanel * 0.5); // Ancho de los paneles de información
        int altoPanelInfo = (int) (altoPanel * 0.2); // Altura de los paneles de información

        selectorTema.setBounds(margenX,margenY,anchoPanelInfo,altoPanelInfo);
        margenY+= (int) (altoPanel*0.2);
        botonCerrar.setBounds(margenX,margenY,anchoPanelInfo,altoPanelInfo);*/

    }
    public void open(){
        //panel.setEnabled(false);
        //for(Component component: panel.getComponents()){
          // component.setEnabled(false);
            //component.enableInputMethods(false);
       // }
        this.setEnabled(true);
        this.setVisible(true);
    }
    public void close(){
        //panel.setEnabled(true);
        //for(Component component: panel.getComponents()){
          //  component.setEnabled(true);
            //component.enableInputMethods(true);
        //}
        this.setEnabled(false);
        this.setVisible(false);
    }
}
