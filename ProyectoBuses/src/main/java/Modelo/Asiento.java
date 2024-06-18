package Modelo;

public class Asiento {
    private int Numero;
    private int Precio;
    private int Piso;
    private boolean reservado;

    public Asiento(int piso, int numero) {
        this.Piso = piso;
        this.Numero = numero;
        this.reservado = false;
    }

    public int getNumero() {
        return Numero;
    }

    public int getPiso() {
        return Piso;
    }

    public boolean isReservado() {
        return reservado;
    }

    public void setStateReserva(boolean reservado) {
        this.reservado = reservado;
    }
}
