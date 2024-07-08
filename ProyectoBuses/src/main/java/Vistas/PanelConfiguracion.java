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

    public PanelConfiguracion(Temas tema, JPanel panel){
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
        add(selectorTema);
        botonCerrar = new BotonAvanzar(null);
        botonCerrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                close();
            }
        });
        add(botonCerrar);
    }
    protected void paintComponent(Graphics g){
        super.paintComponent(g); // Llamar al método paintComponent de la superclase JPanel

        int anchoPanel = getWidth(); // Obtener el ancho del panel
        int altoPanel = getHeight(); // Obtener el alto del panel

        int margenX = (int) (anchoPanel * 0.05); // Margen horizontal
        int margenY = (int) (altoPanel * 0.05); // Margen vertical
        int anchoPanelInfo = (int) (anchoPanel * 0.5); // Ancho de los paneles de información
        int altoPanelInfo = (int) (altoPanel * 0.1); // Altura de los paneles de información

        selectorTema.setBounds(margenX,margenY,anchoPanelInfo,altoPanelInfo);
        margenY+= (int) (altoPanel*0.2);
        botonCerrar.setBounds(margenX,margenY,anchoPanelInfo,altoPanelInfo);

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
