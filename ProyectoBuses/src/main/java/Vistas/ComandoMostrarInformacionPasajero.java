package Vistas;

/**
 * Implementación de la interfaz Comandos que muestra el panel de información del pasajero en el PanelPrincipal.
 */
public class ComandoMostrarInformacionPasajero implements Comandos {
    private final PanelPrincipal panelPrincipal;

    /**
     * Constructor que recibe una instancia de PanelPrincipal para manipular la visualización.
     * @param panelPrincipal Instancia de PanelPrincipal donde se mostrará el panel de información del pasajero.
     */
    public ComandoMostrarInformacionPasajero(PanelPrincipal panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    /**
     * Método execute() que ejecuta la acción de mostrar el panel de información del pasajero en el PanelPrincipal.
     * Invoca el método correspondiente en PanelPrincipal.
     */
    @Override
    public void execute() {
        panelPrincipal.mostrarPanelInformacionPasajero();
    }
}
