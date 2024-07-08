package Vistas;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * Interfaz EscuchaTextos
 *
 * Extiende DocumentListener y proporciona un método para manejar eventos de cambio en un documento.
 */
public interface EscuchaTextos extends DocumentListener {

    /**
     * Método llamado para manejar eventos de cambio en un documento.
     *
     * @param e Evento de cambio en el documento.
     */
    void update(DocumentEvent e);

    @Override
    default void insertUpdate(DocumentEvent e) {
        update(e);
    }

    @Override
    default void removeUpdate(DocumentEvent e) {
        update(e);
    }

    @Override
    default void changedUpdate(DocumentEvent e) {
        update(e);
    }
}
