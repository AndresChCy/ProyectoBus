package Modelo;

public class AsientoPremium extends Asiento{
    public AsientoPremium(int i){super(i);}

    @Override
    public String getCategoria() {
        return "Premium";
    }
    @Override
    public float getMultiplicador(){
        return 2.0F;
    }
}
