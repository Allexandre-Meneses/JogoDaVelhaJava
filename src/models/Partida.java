package models;

import java.util.ArrayList;

public class Partida {
    public ArrayList<Jogador> jogadores = new ArrayList<Jogador>();
    public ArrayList<Jogada> jogadas = new ArrayList<Jogada>();
    Jogador vencedor;

    public void setVencedor(Jogador vencedor) {
        this.vencedor = vencedor;
    }

    public boolean temVencedor(Tabuleiro atual) {
        
        if(atual.verificaHorizontal()) {
            return true;
        }
        if(atual.verificaVertical()){
            return true;
        }
        if(atual.verificaDiagonal()){
            return true;
        }

        return false;        
    }

    public boolean deuVelha(Tabuleiro t) {
        if (t.verificaVelha()){
            return true;
        }
        return false;
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

}

