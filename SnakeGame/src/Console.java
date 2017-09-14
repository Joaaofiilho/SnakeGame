import java.awt.*;
import java.nio.charset.Charset;
import java.util.Random;

import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.TerminalSize;

public class Console {
    private boolean fimDoJogo;
    private boolean vitoria;
    private int timeDecorrido;
    private int timeLimite;
    private int qntFrutasPegas;
    private Terminal terminal;
    private TerminalSize terminalSize;
    private Cobrinha cobrinha;
    private Key tecla;
    private Comida[] comida;
    private Obstaculo[] obstaculo;
    //Construtor
    public Console() {
        setFimDoJogo(false);
        setVitoria(false);
        setTimeDecorrido(0);
        setTimeLimite(tempoDeJogo); //3 minutos 180000

        terminal = TerminalFacade.createTerminal(System.in, System.out, Charset.forName("UTF8"));
        terminalSize = terminal.getTerminalSize();

        cobrinha = new Cobrinha(terminalSize.getColumns()/2, terminalSize.getRows()/2);

        comida = new Comida[15];
        obstaculo = new Obstaculo[10];

        qntFrutasPegas = 0;

        for (int i = 0; i < comida.length; i++) {
            comida[i] = new Comida();
        }

        for (int i = 0; i < obstaculo.length; i++) {
            obstaculo[i] = new Obstaculo();
        }
    }

    //Metodos especiais

    public void executarJogo(){
        executarTerminal();
        evocarEntidades();
        while(!isFimDoJogo()) {
            tecla = terminal.readInput();
            while (!isFimDoJogo() && tecla == null) {
                exibirVidas();
                exibirTempo();
                exibirQntFrutas();
                exibirParedes();
                moverCobrinha();
                exibirCobrinha();
                apagarCorpoCobrinha();
                try {
                    Thread.sleep(timePorSegundo);
                    addTimeAtual(timePorSegundo);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                tecla = terminal.readInput();
                //TODO condições para fim de jogo
                if(timeLimite <= 0){
                    setFimDoJogo(true);
                    setVitoria(false);
                }
                if(cobrinha.getQntVidas() == 0){
                    setFimDoJogo(true);
                    setVitoria(false);
                }
                if(getQntFrutasPegas() == comida.length){
                    setFimDoJogo(true);
                    setVitoria(true);
                }

                //Verificar se a cobrinha comeu alguma comida
                int posCobrinhaI[] = cobrinha.getPosAtualI();
                int posCobrinhaJ[] = cobrinha.getPosAtualJ();
                for (int i = 0; i < comida.length; i++) {
                    if(comida[i].isDisponivel() && posCobrinhaI[0] == comida[i].getPosicaoI() &&
                            posCobrinhaJ[0] == comida[i].getPosicaoJ()){
                        comida[i].setDisponivel(false);
                        cobrinha.aumentarTamanho();
                        aumentarQntFrutasPegas();
                        //Fazer a cobrinha ganhar uma vida
                        if(getQntFrutasPegas() % 3 == 0 && getQntFrutasPegas() != comida.length){
                            cobrinha.addVida();
                        }
                    }
                }

                //Verificar se a cobrinha bateu em algum obstaculo
                for (int i = 0; i < obstaculo.length; i++) {
                    if(obstaculo[i].isDisponivel() && posCobrinhaI[0] == obstaculo[i].getPosicaoI()
                            && posCobrinhaJ[0] == obstaculo[i].getPosicaoJ()){
                        obstaculo[i].setDisponivel(false);
                        cobrinha.perderVida();
                    }
                }
            }
            //Lendo as teclas e evitando que ela volte por onde tá indo
            if(tecla != null){
                if (tecla.getKind() == Key.Kind.ArrowLeft && cobrinha.getDirecao() != 2)
                    cobrinha.setDirecao(0);
                if (tecla.getKind() == Key.Kind.ArrowUp  && cobrinha.getDirecao() != 3)
                    cobrinha.setDirecao(1);
                if (tecla.getKind() == Key.Kind.ArrowRight && cobrinha.getDirecao() != 0)
                    cobrinha.setDirecao(2);
                if (tecla.getKind() == Key.Kind.ArrowDown && cobrinha.getDirecao() != 1)
                    cobrinha.setDirecao(3);
                if (tecla.getKind() == Key.Kind.Escape)
                    System.exit(0);
            }
        }
        //Vitória ou derrota
        if(isVitoria()){
            terminal.applyForegroundColor(Terminal.Color.GREEN);
            terminal.clearScreen();
            terminal.moveCursor(terminalSize.getColumns()/2-5, terminalSize.getRows()/2);
            terminal.putCharacter('V');

            terminal.moveCursor(terminalSize.getColumns()/2-4, terminalSize.getRows()/2);
            terminal.putCharacter('o');

            terminal.moveCursor(terminalSize.getColumns()/2-3, terminalSize.getRows()/2);
            terminal.putCharacter('c');

            terminal.moveCursor(terminalSize.getColumns()/2-2, terminalSize.getRows()/2);
            terminal.putCharacter('ê');

            terminal.moveCursor(terminalSize.getColumns()/2, terminalSize.getRows()/2);
            terminal.putCharacter('V');

            terminal.moveCursor(terminalSize.getColumns()/2+1, terminalSize.getRows()/2);
            terminal.putCharacter('e');

            terminal.moveCursor(terminalSize.getColumns()/2+2, terminalSize.getRows()/2);
            terminal.putCharacter('n');

            terminal.moveCursor(terminalSize.getColumns()/2+3, terminalSize.getRows()/2);
            terminal.putCharacter('c');

            terminal.moveCursor(terminalSize.getColumns()/2+4, terminalSize.getRows()/2);
            terminal.putCharacter('e');

            terminal.moveCursor(terminalSize.getColumns()/2+5, terminalSize.getRows()/2);
            terminal.putCharacter('u');

            terminal.moveCursor(terminalSize.getColumns()/2+6, terminalSize.getRows()/2);
            terminal.putCharacter('!');
        }else{
            terminal.applyForegroundColor(Terminal.Color.RED);
            terminal.clearScreen();
            if(getTimeLimite() <= 0){
                terminal.moveCursor(terminalSize.getColumns()/2-7, terminalSize.getRows()/2);
                terminal.putCharacter('T');

                terminal.moveCursor(terminalSize.getColumns()/2-6, terminalSize.getRows()/2);
                terminal.putCharacter('e');

                terminal.moveCursor(terminalSize.getColumns()/2-5, terminalSize.getRows()/2);
                terminal.putCharacter('m');

                terminal.moveCursor(terminalSize.getColumns()/2-4, terminalSize.getRows()/2);
                terminal.putCharacter('p');

                terminal.moveCursor(terminalSize.getColumns()/2-3, terminalSize.getRows()/2);
                terminal.putCharacter('o');

                terminal.moveCursor(terminalSize.getColumns()/2-1, terminalSize.getRows()/2);
                terminal.putCharacter('e');

                terminal.moveCursor(terminalSize.getColumns()/2, terminalSize.getRows()/2);
                terminal.putCharacter('s');

                terminal.moveCursor(terminalSize.getColumns()/2+1, terminalSize.getRows()/2);
                terminal.putCharacter('g');

                terminal.moveCursor(terminalSize.getColumns()/2+2, terminalSize.getRows()/2);
                terminal.putCharacter('o');

                terminal.moveCursor(terminalSize.getColumns()/2+3, terminalSize.getRows()/2);
                terminal.putCharacter('t');

                terminal.moveCursor(terminalSize.getColumns()/2+4, terminalSize.getRows()/2);
                terminal.putCharacter('a');

                terminal.moveCursor(terminalSize.getColumns()/2+5, terminalSize.getRows()/2);
                terminal.putCharacter('d');

                terminal.moveCursor(terminalSize.getColumns()/2+6, terminalSize.getRows()/2);
                terminal.putCharacter('o');

                terminal.moveCursor(terminalSize.getColumns()/2+7, terminalSize.getRows()/2);
                terminal.putCharacter('!');

            }else{
                terminal.moveCursor(terminalSize.getColumns()/2-5, terminalSize.getRows()/2);
                terminal.putCharacter('V');

                terminal.moveCursor(terminalSize.getColumns()/2-4, terminalSize.getRows()/2);
                terminal.putCharacter('o');

                terminal.moveCursor(terminalSize.getColumns()/2-3, terminalSize.getRows()/2);
                terminal.putCharacter('c');

                terminal.moveCursor(terminalSize.getColumns()/2-2, terminalSize.getRows()/2);
                terminal.putCharacter('ê');

                terminal.moveCursor(terminalSize.getColumns()/2, terminalSize.getRows()/2);
                terminal.putCharacter('P');

                terminal.moveCursor(terminalSize.getColumns()/2+1, terminalSize.getRows()/2);
                terminal.putCharacter('e');

                terminal.moveCursor(terminalSize.getColumns()/2+2, terminalSize.getRows()/2);
                terminal.putCharacter('r');

                terminal.moveCursor(terminalSize.getColumns()/2+3, terminalSize.getRows()/2);
                terminal.putCharacter('d');

                terminal.moveCursor(terminalSize.getColumns()/2+4, terminalSize.getRows()/2);
                terminal.putCharacter('e');

                terminal.moveCursor(terminalSize.getColumns()/2+5, terminalSize.getRows()/2);
                terminal.putCharacter('u');

                terminal.moveCursor(terminalSize.getColumns()/2+6, terminalSize.getRows()/2);
                terminal.putCharacter('!');
            }

        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }

    public void executarTerminal(){
        terminal.enterPrivateMode();
        terminal.applyForegroundColor(Terminal.Color.CYAN);
        terminal.setCursorVisible(false);
    }

    public void exibirTempo(){
        String tempo = converterParaMinuto(getTimeLimite());
        terminal.moveCursor( terminalSize.getColumns()/2-2, 0);
        terminal.putCharacter('[');
        for (int i = 0; i < tempo.length(); i++) {
            terminal.moveCursor(i + terminalSize.getColumns()/2-1, 0);
            terminal.putCharacter(tempo.charAt(i));
        }
        terminal.moveCursor( terminalSize.getColumns()/2-1 + tempo.length(), 0);
        terminal.putCharacter(']');
    }

    public void exibirParedes(){
        for (int x = 2; x < terminalSize.getRows()-1; x++) {
            terminal.moveCursor(0, x);
            terminal.putCharacter('|');
        }

        for (int x = 1; x < terminalSize.getColumns()-1; x++) {
            terminal.moveCursor(x, 1);
            terminal.putCharacter('-');
        }

        for (int x = 2; x < terminalSize.getRows()-1; x++) {
            terminal.moveCursor(terminalSize.getColumns(), x);
            terminal.putCharacter('|');
        }

        for (int x = 1; x < terminalSize.getColumns()-1; x++) {
            terminal.moveCursor(x, terminalSize.getRows());
            terminal.putCharacter('-');
        }
    }

    public void exibirVidas(){
        terminal.moveCursor(0, 0);
        terminal.putCharacter('V');

        terminal.moveCursor(1, 0);
        terminal.putCharacter('i');

        terminal.moveCursor(2, 0);
        terminal.putCharacter('d');

        terminal.moveCursor(3, 0);
        terminal.putCharacter('a');

        terminal.moveCursor(4, 0);
        terminal.putCharacter('s');

        terminal.moveCursor(5, 0);
        terminal.putCharacter(':');

        terminal.moveCursor(6, 0);
        terminal.putCharacter(' ');

        terminal.moveCursor(7, 0);
        terminal.putCharacter(Integer.toString(cobrinha.getQntVidas()).charAt(0));
    }

    public void exibirQntFrutas(){
        terminal.moveCursor(terminalSize.getColumns()-2, 0);
        terminal.putCharacter('5');

        terminal.moveCursor(terminalSize.getColumns()-3, 0);
        terminal.putCharacter('1');

        terminal.moveCursor(terminalSize.getColumns()-4, 0);
        terminal.putCharacter('/');

        if(getQntFrutasPegas() < 10){
            terminal.moveCursor(terminalSize.getColumns()-5, 0);
            terminal.putCharacter(Integer.toString(getQntFrutasPegas()).charAt(0));

            terminal.moveCursor(terminalSize.getColumns()-6, 0);
            terminal.putCharacter('0');
        }else{
            terminal.moveCursor(terminalSize.getColumns()-5, 0);
            terminal.putCharacter(Integer.toString(getQntFrutasPegas()).charAt(1));

            terminal.moveCursor(terminalSize.getColumns()-6, 0);
            terminal.putCharacter(Integer.toString(getQntFrutasPegas()).charAt(0));
        }

        //Blank
        terminal.moveCursor(terminalSize.getColumns()-8, 0);
        terminal.putCharacter(':');

        terminal.moveCursor(terminalSize.getColumns()-9, 0);
        terminal.putCharacter('s');

        terminal.moveCursor(terminalSize.getColumns()-10, 0);
        terminal.putCharacter('a');

        terminal.moveCursor(terminalSize.getColumns()-11, 0);
        terminal.putCharacter('t');

        terminal.moveCursor(terminalSize.getColumns()-12, 0);
        terminal.putCharacter('u');

        terminal.moveCursor(terminalSize.getColumns()-13, 0);
        terminal.putCharacter('r');

        terminal.moveCursor(terminalSize.getColumns()-14, 0);
        terminal.putCharacter('F');
    }

    public void exibirCobrinha(){
        int[] posCobrinhaI = cobrinha.getPosAtualI();
        int[] posCobrinhaJ = cobrinha.getPosAtualJ();

        terminal.moveCursor(posCobrinhaI[0], posCobrinhaJ[0]);
        terminal.putCharacter('O');
    }

    public void moverCobrinha(){
        int[] posCobrinhaI = cobrinha.getPosAtualI();
        int[] posCobrinhaJ = cobrinha.getPosAtualJ();

        //Fazer a cobrinha seguir ela mesma
        for (int i = cobrinha.getTamAtual(); i > 0; i--) {
            posCobrinhaI[i] = posCobrinhaI[i-1];
            posCobrinhaJ[i] = posCobrinhaJ[i-1];
        }
        /*
        0 -> Esquerda
        1 -> Cima
        2 -> Direita
        3 -> Baixo
         */
        if(cobrinha.getDirecao() == 0){
            posCobrinhaI[0]--;
        }
        if(cobrinha.getDirecao() == 1){
            posCobrinhaJ[0]--;
        }
        if(cobrinha.getDirecao() == 2){
            posCobrinhaI[0]++;
        }
        if(cobrinha.getDirecao() == 3){
            posCobrinhaJ[0]++;
        }

        //Fazer ela atravessar o layout
        if(posCobrinhaI[0] == 0){
            posCobrinhaI[0] = terminalSize.getColumns()-1;
        }
        if(posCobrinhaJ[0] == 1){
            posCobrinhaJ[0] = terminalSize.getRows()-2;
        }
        if(posCobrinhaI[0] == terminalSize.getColumns()){
            posCobrinhaI[0] = 1;
        }
        if(posCobrinhaJ[0] == terminalSize.getRows()-1){
            posCobrinhaJ[0] = 2;
        }

        cobrinha.setPosAtualI(posCobrinhaI);
        cobrinha.setPosAtualJ(posCobrinhaJ);
    }

    public void apagarCorpoCobrinha(){
        int[] posCobrinhaI = cobrinha.getPosAtualI();
        int[] posCobrinhaJ = cobrinha.getPosAtualJ();

        terminal.moveCursor(posCobrinhaI[cobrinha.getTamAtual()], posCobrinhaJ[cobrinha.getTamAtual()]);
        terminal.putCharacter(' ');
    }

    public void evocarEntidades(){
        for (Comida comidaAux: comida) {
            comidaAux.gerar(comida, obstaculo, terminalSize);
            terminal.moveCursor(comidaAux.getPosicaoI(), comidaAux.getPosicaoJ());
            terminal.putCharacter('á');
        }
        for (Obstaculo obstaculoAux: obstaculo) {
            obstaculoAux.gerar(comida, obstaculo, terminalSize);
            terminal.moveCursor(obstaculoAux.getPosicaoI(), obstaculoAux.getPosicaoJ());
            terminal.putCharacter('X');
        }
    }

    public void aumentarQntFrutasPegas(){
        setQntFrutasPegas(getQntFrutasPegas() + 1);
    }

    //utils
    public String converterParaMinuto(int num){
        String min = Integer.toString(num/60000) + ":" + (num%60000/1000 <= 9 ? "0"+Integer.toString(num%60000/1000) : Integer.toString(num%60000/1000));
        return min;
    }
    //Getters, Setters e Finals
    final static int timePorSegundo = 1000/10;

    final static int tempoDeJogo = 60000;

    public boolean isFimDoJogo() {
        return fimDoJogo;
    }

    private void setFimDoJogo(boolean fimDoJogo) {
        this.fimDoJogo = fimDoJogo;
    }

    public int getTimeDecorrido() {
        return timeDecorrido;
    }

    public void setTimeDecorrido(int timeDecorrido) {
        this.timeDecorrido = timeDecorrido;
    }

    public void addTimeAtual(int time) {
        this.setTimeLimite(getTimeLimite()- time);
        this.timeDecorrido += time;
    }

    public int getTimeLimite() {
        return timeLimite;
    }

    private void setTimeLimite(int timeLimite) {
        this.timeLimite = timeLimite;
    }

    public int getQntFrutasPegas() {
        return qntFrutasPegas;
    }

    public void setQntFrutasPegas(int qntFrutasPegas) {
        this.qntFrutasPegas = qntFrutasPegas;
    }

    public boolean isVitoria() {
        return vitoria;
    }

    public void setVitoria(boolean vitoria) {
        this.vitoria = vitoria;
    }
}