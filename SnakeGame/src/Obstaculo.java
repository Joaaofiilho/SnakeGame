import com.googlecode.lanterna.terminal.TerminalSize;

import java.util.Random;

public class Obstaculo extends Entidade{
    public Obstaculo(){ setDisponivel(true); }

    @Override
    public void gerar(Comida comida[], Obstaculo obstaculo[], TerminalSize terminalSize) {
        Random dado = new Random();
        setPosicaoI(dado.nextInt(terminalSize.getColumns()));
        setPosicaoJ(dado.nextInt(terminalSize.getRows()));
        //Repete até cair em um local onde já não haja uma fruta
        boolean isIgual;
        do {
            isIgual = false;
            for (int i = 0; i < obstaculo.length; i++) {
                for (int j = 0; j < comida.length; j++) {
                    if(!(obstaculo[i].getPosicaoI() == 0 && obstaculo[i].getPosicaoJ() == 0)) {
                        if (comida[j].getPosicaoI() == obstaculo[i].getPosicaoI()
                                && comida[j].getPosicaoJ() == obstaculo[i].getPosicaoJ()) {
                            isIgual = true;
                        }
                    }
                }
            }

            for (int i = 0; i < obstaculo.length; i++) {
                for (int j = i; j < obstaculo.length; j++) {
                    if(!(obstaculo[j].getPosicaoI() == 0 && obstaculo[j].getPosicaoJ() == 0)) {
                        if (obstaculo[i].getPosicaoI() == obstaculo[j].getPosicaoJ()
                                && obstaculo[i].getPosicaoJ() == obstaculo[j].getPosicaoJ()) {
                            isIgual = true;
                        }
                    }
                }
            }

            for (int i = 0; i < obstaculo.length; i++) {
                if(!(obstaculo[i].getPosicaoI() == 0 && obstaculo[i].getPosicaoJ() == 0)) {
                    if (obstaculo[i].getPosicaoI() == terminalSize.getColumns() / 2
                            && obstaculo[i].getPosicaoJ() == terminalSize.getRows() / 2) {
                        isIgual = true;
                    }
                }
            }

            if(getPosicaoI() < 4 || getPosicaoI() > terminalSize.getRows()-4
                    || getPosicaoJ() < 4 || getPosicaoJ() > terminalSize.getColumns()-4){
                isIgual = true;
            }

            setPosicaoI(dado.nextInt(terminalSize.getColumns()));
            setPosicaoJ(dado.nextInt(terminalSize.getRows()));
        }while(isIgual);
    }
}
