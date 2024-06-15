package Vistas;

import javax.swing.*;
import java.awt.*;

public class PanelSelectorRuta extends JPanel {
    private final MenúSuperiorPanelInicial menúSuperiorPanelInicial;
    private final SelectorOrigen selectorOrigen;
    private final SelectorDestino selectorDestino;
    private final FechaViaje fechaViaje;
    public PanelSelectorRuta() {
        menúSuperiorPanelInicial = new MenúSuperiorPanelInicial();
        selectorOrigen = new SelectorOrigen();
        selectorDestino = new SelectorDestino();
        fechaViaje = new FechaViaje();

        this.setBackground(Color.GREEN);
        this.setLayout(null);

        this.add(selectorOrigen);
        this.add(selectorDestino);
        this.add(fechaViaje);
        this.add(menúSuperiorPanelInicial);
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        int altoPanel = getHeight();
        int anchoPanel = getWidth();

        int altoMenúSuperior = (int) (altoPanel * 0.1);
        menúSuperiorPanelInicial.setBounds(0, 0, anchoPanel, altoMenúSuperior);

        int margenSuperior = altoMenúSuperior + (int) (altoPanel * 0.25);
        int altoSelector = (int) (altoPanel * 0.1);
        int anchoSelector = (int) (anchoPanel * 0.3);

        int posXOrigen = (int) (anchoPanel * 0.2);
        int posYOrigen = margenSuperior;
        selectorOrigen.setBounds(posXOrigen, posYOrigen, anchoSelector, altoSelector);

        int posXDestino = posXOrigen + anchoSelector;
        int posYDestino = margenSuperior;
        selectorDestino.setBounds(posXDestino, posYDestino, anchoSelector, altoSelector);

        int posXFecha = (int) (anchoPanel * 0.35);
        int posYFecha = posYOrigen + altoSelector + (int) (altoPanel * 0.2);
        fechaViaje.setBounds(posXFecha, posYFecha, anchoSelector, altoSelector);
    }
}
