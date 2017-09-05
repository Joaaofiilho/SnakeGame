import java.util.Random;

public class Cobrinha {
    private int[] posAtualI;
    private int[] posAtualJ;
    private int tamAtual;
    private int direcao;
    private int qntVidas;

    //Construtor
    public Cobrinha(int posInicialI, int posInicialJ) {
        //Definindo o limite para ser do tamanho do layout
        posAtualI = new int[(posInicialI*2)*(posInicialJ*2)];
        posAtualJ = new int[(posInicialI*2)*(posInicialJ*2)];
        for (int i = 0; i < posAtualI.length; i++) {
            posAtualI[i] = 0;
        }
        for (int i = 0; i < posAtualJ.length; i++) {
            posAtualJ[i] = 0;
        }
        //Definindo primeira casa
        posAtualI[0] = posInicialI;
        posAtualJ[0] = posInicialJ;

        tamAtual = 1;

        Random dado = new Random();
        direcao = dado.nextInt(4);

        qntVidas = 5;
    }

    //Métodos Especiais
    public void aumentarTamanho(){
        setTamAtual(getTamAtual()+1);
    }

    public void perderVida(){
        setQntVidas(getQntVidas()-1);
    }
    //Getters e Setters

    public int[] getPosAtualI() {
        return posAtualI;
    }

    public void setPosAtualI(int[] posAtualI) {
        this.posAtualI = posAtualI;
    }

    public int[] getPosAtualJ() {
        return posAtualJ;
    }

    public void setPosAtualJ(int[] posAtualJ) {
        this.posAtualJ = posAtualJ;
    }

    public int getTamAtual() {
        return tamAtual;
    }

    public void setTamAtual(int tamAtual) {
        this.tamAtual = tamAtual;
    }

    public int getDirecao() {
        return direcao;
    }

    public void setDirecao(int direcao) {
        this.direcao = direcao;
    }

    public int getQntVidas() {
        return qntVidas;
    }

    public void setQntVidas(int qntVidas) {
        this.qntVidas = qntVidas;
    }
}