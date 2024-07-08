package Vistas;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Temas {
    // Clase interna para definir un Tema
    public static class Tema {
        BufferedImage imagen;
        Color colorPrimario;
        Color colorSecundario;
        Color colorTerciario;

        public Tema(BufferedImage imagen, Color colorPrimario, Color colorSecundario, Color colorTerciario) {
            this.imagen = imagen;
            this.colorPrimario = colorPrimario;
            this.colorSecundario = colorSecundario;
            this.colorTerciario = colorTerciario;
        }
    }

    private final List<Tema> temas;
    private final Random random;
    private ArrayList<TemasObserver> subs;
    public static Tema temaSeleccionado ;

    /**
     * Constructor de la clase Temas.
     * Carga una lista predefinida de temas al inicializar.
     */
    public Temas() {
        temas = new ArrayList<>();
        long semilla = System.currentTimeMillis();
        random = new Random(semilla);
        subs = new ArrayList<>();
        try {
            // Cargar temas predefinidos con imágenes y colores
            cargarTema("/temas/tema_1.png", new Color(41, 48, 60), new Color(127, 105, 79), new Color(136, 167, 185));
            cargarTema("/temas/tema_2.png", new Color(16, 57, 90), new Color(62, 145, 220), new Color(117, 204, 249));
            cargarTema("/temas/tema_3.png", new Color(50, 55, 70), new Color(99, 124, 155), new Color(220, 222, 230));
            cargarTema("/temas/tema_4.png", new Color(107, 67, 39), new Color(204, 146, 77), new Color(251, 229, 121));
            cargarTema("/temas/tema_5.png", new Color(92, 69, 47), new Color(114, 98, 117), new Color(176, 145, 143));
            cargarTema("/temas/tema_6.png", new Color(78, 81, 77), new Color(105, 135, 136), new Color(223, 203, 181));
            cargarTema("/temas/tema_7.png", new Color(31, 29, 14), new Color(115, 103, 57), new Color(204, 190, 189));
            cargarTema("/temas/tema_8.png", new Color(40, 64, 73), new Color(118, 159, 158), new Color(142, 185, 198));
            cargarTema("/temas/tema_9.png", new Color(34, 42, 57), new Color(56, 99, 147), new Color(165, 187, 214));
            cargarTema("/temas/tema_10.png", new Color(55, 78, 87), new Color(77, 142, 191), new Color(213, 209, 206));
            cargarTema("/temas/tema_11.png", new Color(35, 32, 30), new Color(160, 117, 66), new Color(214, 211, 203));
            cargarTema("/temas/tema_12.png", new Color(46, 42, 12), new Color(139, 122, 53), new Color(246, 231, 194));
            cargarTema("/temas/tema_13.png", new Color(46, 42, 42), new Color(103, 94, 83), new Color(216, 216, 227));
            cargarTema("/temas/tema_14.png", new Color(28, 42, 44), new Color(24, 72, 105), new Color(175, 180, 173));
            cargarTema("/temas/tema_15.png", new Color(47, 54, 71), new Color(98, 129, 167), new Color(211, 215, 225));
            cargarTema("/temas/tema_16.png", new Color(56, 50, 18), new Color(120, 113, 50), new Color(179, 186, 151));
            cargarTema("/temas/tema_17.png", new Color(79, 93, 47), new Color(133, 150, 120), new Color(172, 196, 216));
            cargarTema("/temas/tema_18.png", new Color(90, 100, 41), new Color(157, 115, 82), new Color(207, 206, 191));
            cargarTema("/temas/tema_19.png", new Color(36, 38, 40), new Color(205, 159, 149), new Color(213, 202, 201));
            cargarTema("/temas/tema_20.png", new Color(46, 58, 56), new Color(67, 116, 155), new Color(181, 178, 168));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        seleccionarTemaAleatorio();
    }

    /**
     * Carga un tema con la imagen y los colores especificados.
     *
     * @param rutaImagen      Ruta de la imagen del tema.
     * @param colorPrimario   Color primario del tema.
     * @param colorSecundario Color secundario del tema.
     * @param colorTerciario  Color terciario del tema.
     * @throws IOException Si ocurre un error al cargar la imagen.
     */
    private void cargarTema(String rutaImagen, Color colorPrimario, Color colorSecundario, Color colorTerciario) throws IOException {
        BufferedImage imagenTema = ImageIO.read(Objects.requireNonNull(getClass().getResource(rutaImagen)));
        temas.add(new Tema(imagenTema, colorPrimario, colorSecundario, colorTerciario));
    }

    /**
     * Selecciona un tema aleatorio de la lista de temas cargados.
     *
     * @return El tema seleccionado aleatoriamente.
     */
    public void seleccionarTemaAleatorio() {
        int index = random.nextInt(temas.size());
        temaSeleccionado = temas.get(index);
        informar();
    }

    /**
     * Agrega un nuevo tema a la lista de temas.
     *
     * @param imagen           Imagen del tema.
     * @param colorPrimario    Color primario del tema.
     * @param colorSecundario  Color secundario del tema.
     * @param colorTerciario   Color terciario del tema.
     */
    public void agregarTema(BufferedImage imagen, Color colorPrimario, Color colorSecundario, Color colorTerciario) {
        temas.add(new Tema(imagen, colorPrimario, colorSecundario, colorTerciario));
    }
    public List<Tema> getTemas(){return temas;}
    public void seleccionarTema(int i){
        temaSeleccionado = temas.get(i);
        informar();

    }
    public void informar(){
        for (TemasObserver aux : subs){
            aux.updateTema();
        }
    }
    public void suscribir(TemasObserver panel){
        subs.add(panel);
    }
}
