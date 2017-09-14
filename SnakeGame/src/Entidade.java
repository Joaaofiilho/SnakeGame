import com.googlecode.lanterna.terminal.TerminalSize;

public abstract class Entidade {
    private int posicaoI;
    private int posicaoJ;
    private boolean disponivel;

    public Entidade(){
        posicaoI = 0;
        posicaoJ = 0;
    }

    public abstract void gerar(Comida comida[], Obstaculo obstaculo[], TerminalSize terminalSize);

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
