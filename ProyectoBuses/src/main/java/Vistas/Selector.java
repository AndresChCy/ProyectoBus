package Vistas;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Selector extends JPanel {
    private final SeleccionadorElementos seleccionador;
    private final FuentesPersonalizadas fp;
    private final String fuente = "Broadway";
    private final String mensaje ;
    private final JPanel panelTitulo;

    /**
     * Constructor de la clase SelectorDestino.
     * Inicializa el componente con una lista de ciudades y un selector de ciudad.
     */
    public Selector(String titulo,String[] elementos) {
        this.mensaje = titulo;
        // Configurar el layout del panel principal como BorderLayout
        setLayout(new BorderLayout());

        // Establecer el fondo del panel como transparente
        setOpaque(false);

        // Inicializar fuentes personalizadas
        fp = new FuentesPersonalizadas(mensaje, fuente);

        // Inicializar la lista de ciudades con ciudades de Chile
        List<String> ciudades = getStrings();

        // Crear el selector de ciudad con la lista de ciudades
        seleccionador = new SeleccionadorElementos(elementos);

        // Crear y configurar el panel para el título
        panelTitulo = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                int anchoPanel = getWidth();
                int altoPanel = getHeight();

                // Calcular tamaño de fuente basado en el tamaño del panel
                int tamanoFuente = fp.calcularTamanoLetras(anchoPanel, altoPanel, g);
                g.setFont(new Font(fuente, Font.BOLD, tamanoFuente));
                FontMetrics fm = g.getFontMetrics();

                // Centrar el mensaje verticalmente en el panel
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
        add(seleccionador, BorderLayout.SOUTH);
    }

    private static List<String> getStrings() {
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
        return new ArrayList<>(Arrays.asList(ciudadesChile));
    }

    /**
     * Método sobrescrito para dibujar el componente.
     * Ajusta la posición y tamaño del panel de título y del selector de ciudad.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int anchoPanel = getWidth();
        int altoPanel = getHeight();

        // Ajustar posición y tamaño del panel de título y del selector de ciudad
        int altoSelCiudad = (int) (altoPanel * 0.5);
        panelTitulo.setBounds(0, 0, anchoPanel, altoSelCiudad);
        seleccionador.setBounds(0, altoSelCiudad, anchoPanel, altoSelCiudad);
    }
    public JComboBox<String> getComboBox(){
        return seleccionador.getComboBox();
    }
}