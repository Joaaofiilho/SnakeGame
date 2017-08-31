public class Cobrinha {
    Cabeca cabeca;
    Corpo[] corpo;

    public Cobrinha(int tamCobrinha){
        cabeca = new Cabeca();
        corpo = new Corpo[tamCobrinha];
        for (int i = 0; i < corpo.length; i++) {
            corpo[i] = new Corpo();
        }
    }

    public void addCorpo(){
        Corpo[] aux = corpo;
        corpo = new Corpo[corpo.length + 1];
        for (int i = 0; i < aux.length; i++) {
            corpo[i] = aux[i];
        }
    }
}