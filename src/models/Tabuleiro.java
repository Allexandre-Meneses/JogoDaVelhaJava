package models;

import java.util.ArrayList;

import utils.ScannerJogo;
import utils.Validações;

public class Tabuleiro {
    ArrayList<Campo> tabuleiro = new ArrayList<Campo>();

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

    public Jogada realizaJogada(Jogador jogador, Jogo jogo, Tabuleiro atual) {
        ScannerJogo sc = new ScannerJogo();
        Campo campo;
        int linha,coluna;

        do {

            boolean isValido;
            do{

                isValido = true;

                System.out.print(jogador.nome + " Digite a linha que deseja marcar:");
                linha = sc.scanner.nextInt();
                if(linha > 2) isValido = false;
                System.out.print(jogador.nome + " Digite a coluna que deseja marcar:");
                coluna = sc.scanner.nextInt();
                if(coluna > 2) isValido = false;
                System.out.println("");

                if(!isValido) {
                    System.out.println("------------------------------------");
                    System.out.println("Digite uma Coordenada Válida!");
                    System.out.println("------------------------------------");
                }
            
            }
            while(!isValido);

            campo = getCampoNaCoordenada(linha, coluna);
            System.out.println(jogador.nome + " Jogou na coordenada: (" + linha + "," + coluna + ")");
        
            if (!Validações.campoValido(campo)){
                jogo.imprimeTabu(atual);
                System.out.println("");
                System.out.println("Campo inválido, digite um campo válido!");
                System.out.println("----------------------------------------");
            }
        }
        while(!Validações.campoValido(campo));

        campo.simbolo = jogador.peca;
        Jogada nova = new Jogada(jogador, campo);

        return nova;
    }

    Campo getCampoNaCoordenada(int linha, int coluna) {
        for (Campo tmp : tabuleiro) {
            if (tmp.coordenada.linha == linha &&
                tmp.coordenada.coluna == coluna)
            return tmp;
        }
        return null;
    }
}