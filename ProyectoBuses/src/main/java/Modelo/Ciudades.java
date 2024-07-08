package Modelo;

import java.io.Serializable;

/**
 * Enumeración que contiene las ciudades disponibles.
 */
public enum Ciudades implements Serializable {
    CHILLAN(-36.6066, -72.1034),
    CONCEPCION(-36.8270, -73.0503),
    TEMUCO(-38.7359, -72.5904),
    SANTIAGO(-33.4489, -70.6693),
    VALPARAISO(-33.0458, -71.6197),
    LA_SERENA(-29.9027, -71.2519),
    ANTOFAGASTA(-23.6509, -70.3975),
    IQUIQUE(-20.2141, -70.1524),
    PUNTA_ARENAS(-53.1638, -70.9171),
    PUERTO_MONTT(-41.4689, -72.9411),
    TALCA(-35.4264, -71.6653),
    RANCAGUA(-34.1701, -70.7446),
    ARICA(-18.4783, -70.3126),
    COPIAPO(-27.3668, -70.3322),
    CURICO(-34.9825, -71.2366),
    PUERTO_VARAS(-41.3186, -72.9876),
    OSORNO(-40.5739, -73.1335),
    QUILLOTA(-32.8807, -71.2489),
    LOS_ANGELES(-37.4692, -72.3537),
    CHILLAN_VIEJO(-36.6493, -72.1407),
    TALCAHUANO(-36.7249, -73.1168),
    VALDIVIA(-39.8142, -73.2459),
    CALAMA(-22.4570, -68.9294),
    VINA_DEL_MAR(-33.0153, -71.5500),
    COQUIMBO(-29.9533, -71.3395),
    LINARES(-35.8466, -71.5935),
    MELIPILLA(-33.6894, -71.2156),
    LOTA(-37.0899, -73.1565),
    SAN_ANTONIO(-33.5934, -71.6210);

    private final double horizontal;
    private final double vertical;

    /**
     * Constructor de Ciudades.
     *
     * @param x Coordenadas X.
     * @param y Coordenadas Y.
     */
    Ciudades(double x, double y) {
        this.horizontal = x;
        this.vertical = y;
    }

    /**
     * Método get para obtener las coordenadas X.
     *
     * @return Coordenadas X.
     */
    public double getX() {
        return horizontal;
    }

    /**
     * Método get para obtener las coordenadas Y.
     *
     * @return Coordenadas Y.
     */
    public double getY() {
        return vertical;
    }
}
