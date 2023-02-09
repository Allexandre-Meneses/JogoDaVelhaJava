package models;

public class Placar {
    public Jogador jogador1;
    public Jogador jogador2;
    public Jogador velha = new Velha();

    private static Placar instanciaPlacar;

    private Placar() { }

    public static Placar getInstance() {
        if (instanciaPlacar == null ) {
            instanciaPlacar = new Placar();
        }
        return instanciaPlacar;
    }
}
