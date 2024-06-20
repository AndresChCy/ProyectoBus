package Vistas;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SelectorDestino extends JPanel {
    private List<String> ciudades;
    private SeleccionadorCiudad seleccionadorCiudad;
    private FuentesPersonalizadas fp;
    private String fuente = "Broadway";
    private String mensaje = "Destino";
    private JPanel panelTitulo;

    public SelectorDestino() {
        // Configurar el layout del panel principal como BorderLayout
        setLayout(new BorderLayout());

        // Establecer el fondo del panel como transparente
        setOpaque(false);

        fp = new FuentesPersonalizadas(mensaje, fuente);

        ciudades = new ArrayList<>();
        String[] ciudadesChile = {
                "Santiago", "Viña del Mar", "Valparaíso", "Concepción", "Antofagasta", "Puerto Montt",
                "Arica", "La Serena", "Iquique", "Rancagua", "Talca", "Temuco", "Chillán", "Los Ángeles",
                "Valdivia", "Copiapó", "Quillota", "Osorno", "Calama", "Punta Arenas", "Curicó", "Quilpué",
                "Ovalle", "San Felipe", "Los Andes", "Linares", "San Antonio", "Melipilla", "San Fernando",
                "Pucón", "Villarrica", "La Ligua", "Cauquenes", "Puerto Varas", "Castro", "Ancud",
                "Talcahuano", "La Calera", "Tocopilla", "Coquimbo", "Angol", "Loncoche", "La Unión",
                "Limache", "Puerto Natales", "Lota", "Victoria", "Collipulli", "Coronel", "Lebu", "Coelemu",
                "Curanilahue", "Santa Cruz", "Paine", "Puerto Aysén", "Panguipulli", "Pitrufquén",
                "Vallenar", "Vicuña", "Salamanca", "Illapel", "Coyhaique", "Chaitén", "Puerto Cisnes",
                "Futrono", "Chonchi", "Hualpén", "Quellón", "Quirihue", "Puerto Octay", "Frutillar",
                "Llanquihue", "Puerto Williams", "San Carlos", "Curaco de Vélez", "Dalcahue", "Quemchi",
                "Puerto Quellón", "Puerto Ingeniero Ibáñez", "San José de la Mariquina", "Puerto Saavedra",
                "Pichilemu", "Lanco", "San Vicente de Tagua Tagua", "Lonquimay", "Pitrufquén", "Puerto Natales",
                "Cochrane", "Tolhuin", "Timaukel", "Coyhaique", "Alto Hospicio", "El Bosque", "Huechuraba",
                "Cerrillos", "Pudahuel", "Recoleta", "Quilicura", "Colina", "Lampa", "Padre Hurtado",
                "Peñaflor", "Talagante", "Melipilla", "Buin", "Calera de Tango", "Paine", "San Bernardo"
        };

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
                g.setColor(PanelSelectorRuta.temaSeleccionado.colorPrimario);
                g.drawString(mensaje, posXMensaje + 2, posYMensaje + 2);
                g.setColor(PanelSelectorRuta.temaSeleccionado.colorTerciario);
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
