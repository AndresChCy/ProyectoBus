package Vistas;

public class ComandoMostrarHorarios implements Comandos {
    private PanelPrincipal panelPrincipal;

    public ComandoMostrarHorarios(PanelPrincipal panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    @Override
    public void execute() {
        panelPrincipal.mostrarPanelHorarios();
    }
}
