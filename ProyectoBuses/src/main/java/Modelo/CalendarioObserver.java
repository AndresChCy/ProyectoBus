package Modelo;

/**
 * Patron observer para la clase CalendarioViajes
 */
public interface CalendarioObserver {
    /**
     * metodo para notificar de un cambio de estado a los CalendarioObservers
     */
    public void update();
}
