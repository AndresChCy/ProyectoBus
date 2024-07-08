package Vistas;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

/**
 * JPanel para confirmar la compra de pasajes
 */
public class PanelConfirmarCompra extends JPanel {
    private final PanelTitulo titulo;
    private final BotonAvanzar botonAvanzar;
    private ImageIcon tarjetaGif;

    /**
     * Constructor que coloca todos los componentes del panel
     * @param avanzar OperadorComandos para el boton de comprar
     * @param atras Comando para volver hacia atras
     */
    public PanelConfirmarCompra(OperadorComandos avanzar, Comandos atras) {
        setLayout(null); // Usar layout nulo para posicionar componentes manualmente
        setBackground(Temas.temaSeleccionado.colorTerciario); // Establecer color de fondo del panel
        OperadorComandos volver = new OperadorComandos(atras);

        titulo = new PanelTitulo("CONFIRMAR PAGO", volver);
        botonAvanzar = new BotonAvanzar(avanzar, "Pagar");

        // Añadir el panel de título y el botón de avanzar
        add(botonAvanzar);
        add(titulo);

        // Cargar el GIF
        try {
            tarjetaGif = new ImageIcon(Objects.requireNonNull(getClass().getResource("/tarjeta.gif")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int anchoPanel = getWidth();
        int altoPanel = getHeight();

        int altoTitulo = (int) (altoPanel * 0.2);
        titulo.setBounds(0, 0, anchoPanel, altoTitulo);

        int altoBoton = (int) (altoPanel * 0.25);
        int posYBoton = altoPanel - altoBoton;
        botonAvanzar.setBounds(0, posYBoton, anchoPanel, altoBoton);

        // Dibujar el GIF en el centro del panel
        if (tarjetaGif != null) {
            int anchoImagen = tarjetaGif.getIconWidth();
            int altoImagen = tarjetaGif.getIconHeight();

            double proporcionPanel = (double) anchoPanel / altoPanel;
            double proporcionImagen = (double) anchoImagen / altoImagen;

            int anchoDibujo, altoDibujo;

            if (proporcionPanel > proporcionImagen) {
                altoDibujo = altoPanel / 2;
                anchoDibujo = (int) (altoDibujo * proporcionImagen);
            } else {
                anchoDibujo = anchoPanel / 2;
                altoDibujo = (int) (anchoDibujo / proporcionImagen);
            }

            int x = (anchoPanel - anchoDibujo) / 2;
            int y = (altoPanel - altoDibujo) / 2;

            // Dibujar la imagen usando el método paintIcon de ImageIcon
            tarjetaGif.paintIcon(this, g, x, y);
        }
    }
}