package Vistas;

import javax.swing.*;
import java.awt.*;

public class PanelCodigoColor extends JPanel {

    private static final Color COLOR_OCUPADO = Color.RED;
    private static final Color COLOR_DISPONIBLE = Color.WHITE;
    private static final Color COLOR_SELECCIONADO = Color.YELLOW;
    private static final Color COLOR_PREFERENCIAL = Color.BLUE;

    public PanelCodigoColor() {
        this.setLayout(null);
        this.setBackground(new Color(55, 55, 55));

        Font fuente = new Font("Century Gothic", Font.PLAIN, 14);

        agregarEtiquetaColoreada("OCUPADO", COLOR_OCUPADO, fuente);
        agregarEtiquetaColoreada("SELECCIONADO", COLOR_SELECCIONADO, fuente);
        agregarEtiquetaColoreada("DISPONIBLE", COLOR_DISPONIBLE, fuente);
        agregarEtiquetaColoreada("PREFERENCIAL", COLOR_PREFERENCIAL, fuente);
        agregarEtiquetaImagen("ESTÁNDAR", "/estándar.png", fuente);
        agregarEtiquetaImagen("SEMI CAMA", "/semicama.png", fuente);
        agregarEtiquetaImagen("SALÓN CAMA", "/salóncama.png", fuente);
        agregarEtiquetaImagen("PREMIUM", "/premium.png", fuente);
    }

    private void agregarEtiquetaColoreada(String texto, Color color, Font font) {
        JPanel panelColor = new JPanel();
        panelColor.setBackground(color);
        JLabel etiqueta = new JLabel(texto);
        etiqueta.setForeground(Color.WHITE);
        etiqueta.setFont(font);
        this.add(panelColor);
        this.add(etiqueta);
    }

    private void agregarEtiquetaImagen(String texto, String rutaImagen, Font font) {
        ImageIcon icono = new ImageIcon(getClass().getResource(rutaImagen));
        JLabel etiquetaImagen = new JLabel(icono);
        JLabel etiquetaTexto = new JLabel(texto);
        etiquetaTexto.setForeground(Color.WHITE);
        etiquetaTexto.setFont(font);
        this.add(etiquetaImagen);
        this.add(etiquetaTexto);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        redimensionarComponentes();
    }

    private void redimensionarComponentes() {
        int ancho = getWidth();
        int alto = getHeight();

        int tamañoCuadro = Math.min(ancho / 4, alto / 10);
        int anchoRectangulo = 2 * tamañoCuadro;

        int x = 10;
        int y = 10;

        Component[] componentes = getComponents();
        for (int i = 0; i < componentes.length; i += 2) {
            Component cuadroOImagen = componentes[i];
            Component etiqueta = componentes[i + 1];

            if (cuadroOImagen instanceof JPanel) {
                cuadroOImagen.setBounds(x, y, tamañoCuadro, tamañoCuadro);
                etiqueta.setBounds(x + tamañoCuadro + 10, y, ancho - tamañoCuadro - 20, tamañoCuadro);
                y += tamañoCuadro + 10;
            } else if (cuadroOImagen instanceof JLabel) {
                JLabel etiquetaImagen = (JLabel) cuadroOImagen;
                ImageIcon icono = (ImageIcon) etiquetaImagen.getIcon();
                JLabel etiquetaTexto = (JLabel) etiqueta;

                int anchoEtiqueta = (etiquetaTexto.getText().equals("SALÓN CAMA") || etiquetaTexto.getText().equals("PREMIUM")) ? anchoRectangulo : tamañoCuadro;
                Image imagenEscalada = icono.getImage().getScaledInstance(anchoEtiqueta, tamañoCuadro, Image.SCALE_SMOOTH);
                etiquetaImagen.setIcon(new ImageIcon(imagenEscalada));
                etiquetaImagen.setBounds(x, y, anchoEtiqueta, tamañoCuadro);
                etiqueta.setBounds(x + anchoEtiqueta + 10, y, ancho - anchoEtiqueta - 20, tamañoCuadro);
                y += tamañoCuadro + 10;
            }
        }
    }
}
