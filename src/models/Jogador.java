package models;

public class Jogador {
    public String nome;
    String peca;

    public Jogador(String nome, String peca) {
        this.nome = nome;
        this.peca = peca;

    }

    public Jogador(Jogador jogador) {
        this.nome = jogador.nome;
        this.peca = jogador.peca;
    }
}