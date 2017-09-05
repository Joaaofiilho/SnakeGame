import java.nio.charset.Charset;

import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.TerminalSize;

public class Console {
    private int[][] tela = new int[12][12];
    private boolean fimDoJogo;
    private int timeAtual;
    private int timeLimite;
    private Terminal terminal;

    public Console() {
        setFimDoJogo(false);
        setTimeAtual(0);
        setTimeLimite(120000); //3 minutos 180000

        for (int i = 0; i < tela.length; i++) {
            for (int j = 0; j < tela.length; j++) {
                tela[i][j] = 0;
            }
        }
        terminal = TerminalFacade.createTerminal(System.in, System.out, Charset.forName("UTF8"));
    }
    
    

    final int timePorSegundo = 100;

    public boolean isFimDoJogo() {
        return fimDoJogo;
    }

    private void setFimDoJogo(boolean fimDoJogo) {
        this.fimDoJogo = fimDoJogo;
    }

    public int getTimeAtual() {
        return timeAtual;
    }

    public void setTimeAtual(int timeAtual) {
        this.timeAtual = timeAtual;
    }

    public void addTimeAtual(double time) {
        this.setTimeLimite(getTimeLimite()- timePorSegundo);
        this.timeAtual += time;
    }

    public int getTimeLimite() {
        return timeLimite;
    }

    private void setTimeLimite(int timeLimite) {
        this.timeLimite = timeLimite;
    }

    public void setTerminal(Terminal terminal){
    	this.terminal = terminal;
    }
    
    public Terminal getTerminal(){
    	return terminal;
    }
    
    //Métodos especiais
    public String converterParaMinuto(int num){
        String min = "";
        min = Integer.toString(num/60000) + ":" + (num%60000/1000 <= 9 ? "0"+Integer.toString(num%60000/1000) : Integer.toString(num%60000/1000));
        return min;
    }
    
    public void abrirTerminal(){
    	terminal.enterPrivateMode();
    	terminal.setCursorVisible(false);
    }
    
    public void ajustarTamanhoTerminal(){
    	TerminalSize terminalSize = terminal.getTerminalSize();
    }
    
    public void exibirTempo(){
    	String tempo = converterParaMinuto(getTimeLimite());
    	if(getTimeLimite() % 1000 == 0){
            System.out.printf("%s\n", converterParaMinuto(getTimeLimite()));
            for (int i = 0; i < tempo.length(); i++) {
				terminal.moveCursor(i + 50, 0);
				terminal.putCharacter(tempo.charAt(i));
			}
        }
    }
}