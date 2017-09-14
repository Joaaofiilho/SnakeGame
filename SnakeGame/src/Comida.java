import com.googlecode.lanterna.terminal.TerminalSize;

import java.util.Random;

public class Comida extends Entidade{

    public Comida(){
        setDisponivel(true);
    }

    @Override
    public void gerar(Comida comida[], Obstaculo obstaculo[], TerminalSize terminalSize) {
        Random dado = new Random();
        //Repete até cair em um local onde já não haja uma fruta
        boolean isIgual;
        do {
            setPosicaoI(dado.nextInt(terminalSize.getRows()));
            setPosicaoJ(dado.nextInt(terminalSize.getColumns()));
            isIgual = false;
            for (int i = 0; i < comida.length; i++) {
                for (int j = 0; j < obstaculo.length; j++) {
                    if(!(comida[i].getPosicaoI() == 0 && comida[i].getPosicaoJ() == 0)) {
                        if (comida[i].getPosicaoI() == obstaculo[j].getPosicaoI()
                                && comida[i].getPosicaoJ() == obstaculo[j].getPosicaoJ()) {
                            isIgual = true;
                        }
                    }
                }

            }

            for (int i = 0; i < comida.length; i++) {
                for (int j = i; j < comida.length; j++) {
                    if(!(comida[j].getPosicaoI() == 0 && comida[j].getPosicaoJ() == 0)) {
                        if (comida[i].getPosicaoI() == comida[j].getPosicaoJ()
                                && comida[i].getPosicaoJ() == comida[j].getPosicaoJ()) {
                            isIgual = true;
                        }
                    }
                }
            }

            for (int i = 0; i < comida.length; i++) {
                if(!(comida[i].getPosicaoI() == 0 && comida[i].getPosicaoJ() == 0)) {
                    if (comida[i].getPosicaoI() == terminalSize.getRows() / 2
                            && comida[i].getPosicaoJ() == terminalSize.getColumns() / 2) {
                        isIgual = true;
                    }
                }
            }

            if(getPosicaoI() < 4 || getPosicaoI() > terminalSize.getRows() - 2
                    || getPosicaoJ() < 2 || getPosicaoJ() > terminalSize.getColumns() - 2){
                isIgual = true;
            }
        }while(isIgual);
    }
}
