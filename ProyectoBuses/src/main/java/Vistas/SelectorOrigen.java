package Vistas;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SelectorOrigen extends JPanel {
    private List<String> ciudades;
    private SeleccionadorCiudad seleccionadorCiudad;
    private FuentesPersonalizadas fp;
    private String fuente = "Kristen ITC";
    private String mensaje = "Origen";
    private JPanel panelTitulo;

    public SelectorOrigen() {
        // Configurar el layout del panel principal como BorderLayout
        setLayout(new BorderLayout());

        // Establecer el fondo del panel como transparente
        setOpaque(false);

        fp = new FuentesPersonalizadas(mensaje, fuente);

        ciudades = new ArrayList<>();
        String[] ciudadesChile = {"Santiago", "Valparaíso", "Concepción", "Viña del Mar", "Antofagasta", "Valdivia",
                "Arica", "Puerto Montt", "La Serena", "Iquique", "Temuco", "Talca", "Rancagua", "Copiapó", "Coquimbo",
                "Osorno", "Chillán", "Quillota", "Punta Arenas", "Curicó", "Los Ángeles", "Puerto Varas", "Calama", "Vallenar",
                "San Felipe", "Los Andes", "La Calera", "Angol", "Linares", "San Antonio", "Ovalle", "Cauquenes", "Tocopilla",
                "San Fernando", "Pucón", "Santa Cruz", "Lota", "Puerto Natales", "San Vicente de Tagua Tagua", "Melipilla",
                "Coronel", "Puerto Aysén", "Coyhaique", "Villarrica", "Ancud", "Loncoche", "Castro", "Illapel", "Puerto Williams"};
        ciudades.addAll(Arrays.asList(ciudadesChile));
        seleccionadorCiudad = new SeleccionadorCiudad(ciudades);

        // Crear y configurar el panel para el título
        panelTitulo = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                int anchoPanel = getWidth();
                int altoPanel = getHeight();

                int tamanoFuente = fp.calcularTamanoLetras(anchoPanel, altoPanel, g);
                g.setFont(new Font(fuente, Font.BOLD, tamanoFuente));
                FontMetrics fm = g.getFontMetrics();

                int anchoMensaje = fm.stringWidth(mensaje);
                int posXMensaje = (anchoPanel - anchoMensaje) / 2;
                int posYMensaje = (altoPanel + fm.getAscent()) / 2; // Centrar verticalmente
                g.setColor(Color.WHITE);
                g.drawString(mensaje, posXMensaje, posYMensaje);
            }
        };

        // Establecer el fondo del panel de título como transparente
        panelTitulo.setOpaque(false);

        // Agregar el panel de título al centro del BorderLayout
        add(panelTitulo, BorderLayout.CENTER);

        // Agregar el selector de ciudad al sur del BorderLayout
        add(seleccionadorCiudad, BorderLayout.SOUTH);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int anchoPanel = getWidth();
        int altoPanel = getHeight();

        // Ajustar posición y tamaño del panel de título y del selector de ciudad
        int altoSelCiudad = (int) (altoPanel * 0.5);
        panelTitulo.setBounds(0, 0, anchoPanel, altoSelCiudad);
        seleccionadorCiudad.setBounds(0, altoSelCiudad, anchoPanel, altoSelCiudad);
    }
}
