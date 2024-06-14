package Vistas;

public class ComandoMostrarAsientos implements Comandos {
    private PanelPrincipal panelPrincipal;

    public ComandoMostrarAsientos(PanelPrincipal panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    @Override
    public void execute() {
        panelPrincipal.mostrarPanelAsientos();
    }
}
