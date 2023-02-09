package facade;

import java.util.ArrayList;

import models.Campo;
import models.Coordenada;
import models.Jogador;
import models.Jogo;
import models.Partida;
import models.Placar;
import models.Tabuleiro;

public class Facade {

    Jogo jogo = Jogo.getInstance();

    private static Facade instanciaSGPFacade;

    private Facade() { }

    public static Facade getInstance() {
        if (instanciaSGPFacade == null ) {
            instanciaSGPFacade = new Facade();
        }
        return instanciaSGPFacade;
    }

    public Partida iniciarPartida() {
        Partida novaPartida = new Partida();
        if (jogo.partidas.size() > 0) {
            novaPartida.jogadores = jogo.partidas.get(0).jogadores;
        }
        novaPartida.jogadorDaVez=jogo.partidas.get(jogo.partidas.size()-1).jogadas.get(1).jogador;
        novaPartida.tabuleiro = new Tabuleiro();
        jogo.iniciarPartida(novaPartida);
        return novaPartida;
    }

    public Partida iniciarPartida(Jogador j1, Jogador j2) {
        Partida novaPartida = new Partida();
        novaPartida.addJogadores(j1);
        novaPartida.addJogadores(j2);
        novaPartida.jogadorDaVez=j1;
        novaPartida.tabuleiro = new Tabuleiro();
        jogo.iniciarPartida(novaPartida);
        return novaPartida;
    }

    public boolean realizaJogada(Partida p, Coordenada c) {
        Campo campo = p.tabuleiro.getCampoNaCoordenada(c.linha, c.coluna);
        if(campo.getState().marcar(p.jogadorDaVez.peca)) {
            p.jogadas.add(p.tabuleiro.realizaJogada(p.jogadorDaVez, p.tabuleiro, campo));
            p.jogadorDaVez = p.quemJoga();
        } else {
            return false;
        }
        return true;
    }

    public boolean partidaEncerrada(Partida p) {
        return jogo.temVencedor(p) || jogo.deuVelha(p);
    }

    public Jogador setVencedor(Partida p) {
        if (jogo.temVencedor(p)){
            p.vencedor = p.jogadas.get(p.jogadas.size()-1).jogador;
            p.vencedor.nVitorias++;            
        } 
        if (jogo.deuVelha(p)){
            p.vencedor = jogo.placar.velha;
            p.vencedor.nVitorias++;
        }

        return p.vencedor;
    }

    public boolean primeiraPartida() {
        return jogo.partidas.size() == 0;
    }

    public Placar recuperarPlacar() {
        return jogo.placar;
    }

    public ArrayList<Partida> recuperaPartidas() {
        return jogo.partidas;
    }

    public Partida logsDaPartida(int nextInt) {
        return jogo.partidas.get(nextInt-1);
    }
    

}
