public class Console {
    private int[][] tela = new int[12][12];
    private boolean fimDoJogo;
    private int timeAtual;
    private int timeLimite;

    public Console() {
        setFimDoJogo(false);
        setTimeAtual(0);
        setTimeLimite(300000); //3 minutos 180000

        for (int i = 0; i < tela.length; i++) {
            for (int j = 0; j < tela.length; j++) {
                tela[i][j] = 0;
            }
        }
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

    public String converterParaMinuto(int num){
        String min = "";
        min = Integer.toString(num/60000) + ":" + (num%60000/1000 <= 9 ? "0"+Integer.toString(num%60000/1000) : Integer.toString(num%60000/1000));
        return min;
    }
}