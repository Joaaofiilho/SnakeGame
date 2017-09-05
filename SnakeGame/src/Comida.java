public class Comida {
    private int posicaoI;
    private int posicaoJ;
    private boolean disponivel;

    public Comida(){
        disponivel = true;
    }

    //Getters and Setters
    public int getPosicaoI() {
        return posicaoI;
    }

    public void setPosicaoI(int posicaoI) {
        this.posicaoI = posicaoI;
    }

    public int getPosicaoJ() {
        return posicaoJ;
    }

    public void setPosicaoJ(int posicaoJ) {
        this.posicaoJ = posicaoJ;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }
}
