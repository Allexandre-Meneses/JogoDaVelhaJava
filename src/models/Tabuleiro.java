package models;

import java.util.ArrayList;

public class Tabuleiro {
    ArrayList<Campo> tabuleiro = new ArrayList<Campo>();
    Jogo jogo = Jogo.getInstance();

    public Tabuleiro() {
        for(int i = 0; i < 3; i++) {
            for(int c = 0; c < 3; c++) {
                this.tabuleiro.add(new Campo(new Coordenada(i, c), " "));
            }
        }
    }


    public boolean verificaVelha() {
        for (int tmp = 0; tmp < 3; tmp++) {
            Campo campoUm = getCampoNaCoordenada(tmp, 0);
            Campo campoDois = getCampoNaCoordenada(tmp, 1);
            Campo campoTres = getCampoNaCoordenada(tmp, 2);

            if(campoUm.isEmpty() || campoDois.isEmpty() || campoTres.isEmpty()){
                return false;
            }
        }

        return true;
    }

    public boolean verificaHorizontal() {
        for (int tmp = 0; tmp < 3; tmp++) {
        Campo campoUm = getCampoNaCoordenada(tmp, 0);
        Campo campoDois = getCampoNaCoordenada(tmp, 1);
        Campo campoTres = getCampoNaCoordenada(tmp, 2);

        if(!campoUm.isEmpty() && !campoDois.isEmpty() && !campoTres.isEmpty()) {
            if(campoUm.simbolo == campoDois.simbolo && campoDois.simbolo == campoTres.simbolo) {
                return true;
                }
            }
        }
        return false;

    }

    public boolean verificaVertical() {
        for (int tmp = 0; tmp < 3; tmp++) {
            Campo campoUm = getCampoNaCoordenada(0, tmp);
            Campo campoDois = getCampoNaCoordenada(1, tmp);
            Campo campoTres = getCampoNaCoordenada(2, tmp);

            if(!campoUm.isEmpty() && !campoDois.isEmpty() && !campoTres.isEmpty()) {
                if(campoUm.simbolo == campoDois.simbolo && campoDois.simbolo == campoTres.simbolo) {
                    return true;
                }                
            }

        }
        return false;
    }

    public boolean verificaDiagonal() {
            Campo campoUm = getCampoNaCoordenada(0, 0);
            Campo campoDois = getCampoNaCoordenada(1, 1);
            Campo campoTres = getCampoNaCoordenada(2, 2);
            Campo campoQuatro = getCampoNaCoordenada(0, 2);
            Campo campoCinco = getCampoNaCoordenada(2, 0);

            if(!campoUm.isEmpty() && !campoDois.isEmpty() && !campoTres.isEmpty()) {
                if (campoUm.simbolo == campoDois.simbolo &&
                campoDois.simbolo == campoTres.simbolo){
                    return true;
                }
            }            

            if(!campoQuatro.isEmpty() && !campoDois.isEmpty() && !campoCinco.isEmpty()) {
                if (campoQuatro.simbolo == campoDois.simbolo &&
                campoDois.simbolo == campoCinco.simbolo) {
                    return true;
                }
            }

        return false;
        }

    public Jogada realizaJogada(Jogador jogador, Tabuleiro atual, Campo campo) {

        campo.simbolo = jogador.peca;
        Jogada nova = new Jogada(jogador, campo);

        return nova;
        
    }

    public Campo getCampoNaCoordenada(int linha, int coluna) {
        for (Campo tmp : tabuleiro) {
            if (tmp.coordenada.linha == linha &&
                tmp.coordenada.coluna == coluna)
            return tmp;
        }
        return null;
    }

    public boolean campoValido(Campo campo) {
        if(campo.isEmpty()){
            return true;
        }
        return false;
    }
}