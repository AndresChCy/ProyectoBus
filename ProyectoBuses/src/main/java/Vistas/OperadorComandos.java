package Vistas;

import VentanaError.VentanaErr;

import java.util.ArrayList;

/**
 * Clase para ejecutar varios comandos y almacenarlos
 */
public class OperadorComandos {
    private ArrayList<Comandos> comandos;

    /**
     * Metodo constructor
     * @param comando inicial para ejecutar
     */
    public OperadorComandos(Comandos comando){
        comandos = new ArrayList<>();
        comandos.add(comando);
    }

    /**
     * Metodo para añadir mas comandos a la cola de comandos a ejecutar
     * @param comando
     */
    public void addComando(Comandos comando){
        comandos.add(comando);
    }

    /**
     * Ejecuta los comandos de todos los comandos en la lista en el orden correspondiente
     * Si hay un error abre una ventana de error para notificar
     */
    public void execute(){
        try {
            for (int i = 0; i < comandos.size(); i++) {
                comandos.get(i).execute();
            }
        }catch(RuntimeException e){
            new VentanaErr(e.getMessage());
        }
    }
}
