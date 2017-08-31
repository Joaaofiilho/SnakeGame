public class Executavel {
    //10 obstaculos 15 comidas
    //Direçao de movimento inicial da cobrinha gerado aleatoriamente
    //Tempo de duração de jogo
    //Iniciar jogo com 5 vidas
    //Ela só mudará de direçao quando o usuário digitar um método (<,^,>,v)
    //A cobra não pode passar por ela mesma, porém ela pode atravessar as bordas aparecendo do outro lado
    //Quando a cabeça da cobrinha tiver as mesmas coordenadas de uma comida, a comida somirá e a cobrinha crescerá
    //em uma unidade.
    //A cada três comidas, o jogador ganhará uma vida.
    //Quando a cabeça da cobrinha tiver as mesmas coordenadas de um obstáculo, a cobrinha some da tela e o jogador
    //perde uma vida.
    //Fim do jogo:
    //. O jogador perde todas as vida (Derrota)
    //. O jogador pega todas as comidas (Vitória)
    //. O tempo acabou. (Derrota)
    public static void main(String[] args) {
        Console console = new Console();
        while(!console.isFimDoJogo()){
            try {
                Thread.sleep(console.timePorSegundo);
                console.addTimeAtual(console.timePorSegundo);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(console.getTimeLimite() % 1000 == 0){
                System.out.printf("%s\n", console.converterParaMinuto(console.getTimeLimite()));
            }
        }
    }
}