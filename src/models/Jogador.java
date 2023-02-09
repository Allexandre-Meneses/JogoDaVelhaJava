package models;

public class Jogador {
    public String nome;
    public String peca;
    public int nVitorias = 0;

    public Jogador(String nome, String peca) {
        this.nome = nome;
        this.peca = peca;

    }

    /*public Jogador(Jogador jogador) {
        this.nome = jogador.nome;
        this.peca = jogador.peca;
    }*/
}