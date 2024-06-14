package Vistas;

public class ComandoMostrarInformacionPasajero implements Comandos {
    private PanelPrincipal panelPrincipal;

    public ComandoMostrarInformacionPasajero(PanelPrincipal panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    @Override
    public void execute() {
        panelPrincipal.mostrarPanelInformacionPasajero();
    }
}
