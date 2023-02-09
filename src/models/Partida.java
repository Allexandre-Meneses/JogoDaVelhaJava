package models;

import java.util.ArrayList;

public class Partida {
    public ArrayList<Jogador> jogadores = new ArrayList<Jogador>();
    public ArrayList<Jogada> jogadas = new ArrayList<Jogada>();
    public Jogador vencedor;
    public Jogador jogadorDaVez;
    public Tabuleiro tabuleiro;

    public void setVencedor(Jogador vencedor) {
        this.vencedor = vencedor;
    }


    public Jogador quemJoga(){
        if (this.jogadas.size() == 0){
            return jogadores.get(0);
        }
        if (jogadas.get(jogadas.size()-1).jogador == jogadores.get(0)){
            return jogadores.get(1);
        }
        return jogadores.get(0);
    }

    public void addJogadores(Jogador j) {
        this.jogadores.add(j);
    }

}

