package Vistas;

/**
 * Implementación de la interfaz Comandos que muestra el panel de asientos en el PanelPrincipal.
 */
public class ComandoMostrarAsientos implements Comandos {
    private final PanelPrincipal panelPrincipal;

    /**
     * Constructor que recibe una instancia de PanelPrincipal para manipular la visualización.
     * @param panelPrincipal Instancia de PanelPrincipal donde se mostrará el panel de asientos.
     */
    public ComandoMostrarAsientos(PanelPrincipal panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    /**
     * Método execute() que ejecuta la acción de mostrar el panel de asientos en el PanelPrincipal.
     * Invoca el método correspondiente en PanelPrincipal.
     */
    @Override
    public void execute() {
        panelPrincipal.mostrarPanelAsientos();
    }
}
