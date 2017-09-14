import com.googlecode.lanterna.terminal.TerminalSize;

import java.util.Random;

public class Comida extends Entidade{

    public Comida(){
        setDisponivel(true);
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
                    if(!(comida[j].getPosicaoI() == 0 && comida[j].getPosicaoJ() == 0)) {
                        if (comida[j].getPosicaoI() == obstaculo[i].getPosicaoI()
                                && comida[j].getPosicaoJ() == obstaculo[i].getPosicaoJ()) {
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
                    if (comida[i].getPosicaoI() == terminalSize.getColumns() / 2
                            && comida[i].getPosicaoJ() == terminalSize.getRows() / 2) {
                        isIgual = true;
                    }
                }
            }

            setPosicaoI(2 + dado.nextInt(terminalSize.getColumns()-3));
            setPosicaoJ(2 + dado.nextInt(terminalSize.getRows()-4));
            System.out.println("teste");
        }while(isIgual);
    }
}
