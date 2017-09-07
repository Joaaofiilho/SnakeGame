public class Obstaculo {
    private int posicaoI;
    private int posicaoJ;
    private boolean disponivel;

    public Obstaculo(){
        disponivel = true;
    }

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
