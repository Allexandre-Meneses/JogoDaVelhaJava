package models;

import utils.ScannerJogo;
import utils.Validações;
import java.util.ArrayList;

public class Jogo {
    Integer nPartida;
    public ArrayList<Partida> partidas = new ArrayList<Partida>();

    public void mostrarHistóricoDePartidas(){
        System.out.println("----------------------------------");
        System.out.println("-------Histórico de Partidas------");
        System.out.println("----------------------------------");
        int count = 0;
        for(Partida tmp : partidas) {
            count++;
            System.out.println(count +"* Partida - Vencedor: " + tmp.vencedor.nome + " " + tmp.vencedor.peca);
        }
    }

    public Jogador addJogador(){
        ScannerJogo sc = new ScannerJogo();
        String simbolo;

        System.out.print("Insira seu nome:");
        String nome = sc.scanner.next();

        System.out.print("Insira com qual Símbolo você deseja jogar:(X/O)");
        simbolo = sc.scanner.next();

        while(!Validações.validaSimbolo(simbolo)){
            System.out.print("Insira um símbolo válido: (X/O)");
            simbolo = sc.scanner.next();
        }

        Jogador jogador = new Jogador(nome, simbolo);

        return jogador;
    }   

    public void imprimeTabu(Tabuleiro atual) {
        for (int i = 0; i < 3; i++) {
        Campo campoUm = atual.getCampoNaCoordenada(i, 0);
        Campo campoDois = atual.getCampoNaCoordenada(i, 1);
        Campo campoTres = atual.getCampoNaCoordenada(i, 2);

        System.out.println("["+campoUm.simbolo+"]"+"["+campoDois.simbolo+"]"+"["+campoTres.simbolo+"]");
        }
    }
   
}
