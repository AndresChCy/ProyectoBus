package Modelo;

public class SintetizadorBuses {
    private Builder builder;

    public SintetizadorBuses(Builder builder){
        this.builder = builder;
    }
    public void  changeBuilder(Builder builder){
        this.builder = builder;
    }
    public void make(ModelosBus modelo){
        switch (modelo){
            case ESTANDAR:
                makeEstandar();
                break;
            case GRANDE:
                makeGrande();
                break;
            case WHALE:
                makeWhale();
                break;
            case VIP:
                makeVip();
                break;
            case VIAJESLARGOS:
                makeViajesLargos();
                break;
            case POPULAR:
                makePopular();
                break;
        }
    }
    public Bus getBus(){
        return builder.finalizar();
    }
    public void makeEstandar(){
        builder.reset();
        builder.addPisoBus(10);
        for (int i=0; i<10;i++){
            for( int j=0; j<4;j++){
                builder.addAsientoEstandar(1,i,j);
            }
        }
    }
    public void makeGrande(){
        builder.reset();
        builder.addPisoBus(11);
        builder.addPisoBus(10);
        for (int i=0; i<11;i++){
            for( int j=0; j<4;j++){
                builder.addAsientoEstandar(1,i,j);
            }
        }
        for (int i=0; i<10;i++){
            for( int j=0; j<4;j++){
                builder.addAsientoEstandar(2,i,j);
            }
        }
    }
    public void makeWhale(){
        builder.reset();
        builder.addPisoBus(13);
        builder.addPisoBus(13);
        builder.addPisoBus(6);
        for (int i=0; i<13;i++){
            for( int j=0; j<4;j++){
                if(i<4) {
                    builder.addAsientoSemiCama(1, i, j);
                }
                else builder.addAsientoEstandar(1, i, j);
            }
        }
        for (int i=0; i<13;i++){
            for( int j=0; j<4;j++){
                builder.addAsientoEstandar(2, i, j);
            }
        }
        for (int i=0; i<6;i++){
            for( int j=0; j<4;j++){
                builder.addAsientoSalonCama(3, i, j);
            }
        }
    }
    public void makeVip(){
        builder.reset();
        builder.addPisoBus(8);
        for (int i=0; i<8;i++) {
            for (int j = 0; j < 4; j++) {
                if(j != 1){
                    builder.addAsientoSalonCama(1, i, j);
                }
            }
        }
    }
    public void makeViajesLargos(){
        builder.reset();
        builder.addPisoBus(8);
        builder.addPisoBus(10);
        for (int i=0; i<8;i++) {
            for (int j = 0; j < 4; j++) {
                if(j != 1){
                    builder.addAsientoSalonCama(1, i, j);
                }
            }
        }
        for (int i=0; i<10;i++) {
            for (int j = 0; j < 4; j++) {
                if(i < 2){
                    builder.addAsientoSalonCama(2, i, j);
                }
                else builder.addAsientoSemiCama(2, i, j);
            }
        }
    }
    public void makePopular(){
        builder.reset();
        builder.addPisoBus(7);
        builder.addPisoBus(10);
        for (int i=0; i<7;i++) {
            for (int j = 0; j < 4; j++) {
                if(i < 5){
                    builder.addAsientoSemiCama(1, i, j);
                }
                else builder.addAsientoSalonCama(1, i, j);
            }
        }
        for (int i=0; i<10;i++) {
            for (int j = 0; j < 4; j++) {
                if(i < 2){
                    builder.addAsientoSemiCama(2, i, j);
                }
                else builder.addAsientoEstandar(2, i, j);
            }
        }
    }
}
