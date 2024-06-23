package Vistas;

import java.util.ArrayList;

public class OperadorComandos {
    private ArrayList<Comandos> comandos;
    public OperadorComandos(Comandos comando){
        comandos = new ArrayList<>();
        comandos.add(comando);
    }
    public void addComando(Comandos comando){
        comandos.add(comando);
    }
    public void execute(){
        for (int i=0 ;i<comandos.size();i++){
            comandos.get(i).execute();
        }
    }
}
