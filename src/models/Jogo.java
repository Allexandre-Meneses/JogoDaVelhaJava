package models;

import utils.ScannerJogo;
import java.util.ArrayList;

public class Jogo {
    Integer nPartida;
    public ArrayList<Partida> partidas = new ArrayList<Partida>();
    public Placar placar = Placar.getInstance();

    private static Jogo instanciaJogo;

    private Jogo() { }

    public static Jogo getInstance() {
        if (instanciaJogo == null ) {
            instanciaJogo = new Jogo();
        }
        return instanciaJogo;
    }

    public void iniciarPartida(Partida novaPartida) {
        this.partidas.add(novaPartida);
        this.placar.jogador1 = novaPartida.jogadores.get(0);
        this.placar.jogador2 = novaPartida.jogadores.get(1);
    }

    public boolean temVencedor(Partida p) {
        
        if(p.tabuleiro.verificaHorizontal()) {
            return true;
        }
        if(p.tabuleiro.verificaVertical()){
            return true;
        }
        if(p.tabuleiro.verificaDiagonal()){
            return true;
        }

        return false;        
    }

    public boolean deuVelha(Partida p) {
        if (p.tabuleiro.verificaVelha()){
            return true;
        }
        return false;
    }
    
    ArrayList<Jogador> getJogadores() {
        if(partidas.size() == 1){
            return this.partidas.get(partidas.size()-1).jogadores;
        }
        return null;

    }
    

    ScannerJogo sc = new ScannerJogo();

    public boolean validaSimbolo(String simbolo){
        
        if(simbolo.equalsIgnoreCase("X")||
        simbolo.equalsIgnoreCase("O")){
            return true;
        }
        return false;
    }
   
}
 