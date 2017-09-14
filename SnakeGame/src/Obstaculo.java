import com.googlecode.lanterna.terminal.TerminalSize;

import java.util.Random;

public class Obstaculo extends Entidade{
    private int posicaoI;
    private int posicaoJ;
    private boolean disponivel;

    public Obstaculo(){
        disponivel = true;
    }

    @Override
    public void gerar(Comida comida[], Obstaculo obstaculo[], TerminalSize terminalSize) {
        Random dado = new Random();
        setPosicaoI(2 + dado.nextInt(terminalSize.getColumns()-3));
        setPosicaoJ(2 + dado.nextInt(terminalSize.getRows()-4));
        //Repete até cair em um local onde já não haja uma fruta
        boolean isIgual;
        do {
            isIgual = false;
            for (int i = 0; i < obstaculo.length; i++) {
                for (int j = 0; j < comida.length; j++) {
                    if(comida[j].getPosicaoI() == obstaculo[i].getPosicaoI()
                            && comida[j].getPosicaoJ() == obstaculo[i].getPosicaoJ()){
                        isIgual = true;
                    }
                }
            }

            for (int i = 0; i < obstaculo.length; i++) {
                for (int j = i; j < obstaculo.length; j++) {
                    if(obstaculo[i].getPosicaoI() == obstaculo[j].getPosicaoJ()
                            && obstaculo[i].getPosicaoJ() == obstaculo[j].getPosicaoJ()){
                        isIgual = true;
                    }
                }
            }

            for (int i = 0; i < obstaculo.length; i++) {
                if(obstaculo[i].getPosicaoI() == terminalSize.getColumns()/2
                        && obstaculo[i].getPosicaoJ() == terminalSize.getRows()/2) {
                    isIgual = true;
                }
            }

            setPosicaoI(2 + dado.nextInt(terminalSize.getColumns()-3));
            setPosicaoJ(2 + dado.nextInt(terminalSize.getRows()-4));
        }while(isIgual);
    }
}
