package utils;

import java.util.ArrayList;
import java.util.Scanner;

import models.Campo;
import models.Coordenada;
import models.Jogada;
import models.Jogador;
import models.Partida;
import models.Placar;
import models.Velha;
import facade.Facade;

public class Ui {
    public Scanner scanner = new Scanner(System.in);
    Facade facade = Facade.getInstance();

    private static Ui instanciaUi;

    private Ui() {  }

    public static Ui getInstance() {
        if (instanciaUi == null ) {
            instanciaUi = new Ui();
        }
        return instanciaUi;
    }

    void continuar() {
        System.out.println("");
        System.out.println("Digite s para continuar!");
        scanner.next();
        System.out.println("\n");
    }

    public boolean chamaMenu(){
        System.out.println("-------JOGO#DA#VELHA-------");
        System.out.println("|1| INICIAR PARTIDA");
        System.out.println("|2| MOSTRAR PLACAR");
        System.out.println("|3| MOSTRAR HISTÓRICO DE PARTIDAS");
        System.out.println("|4| MOSTRAR HISTÓRICO DE JOGADAS DE UMA PARTIDA");
        System.out.println("|0| SAIR");

        String opcao = scanner.next();
        
        Object jogo;
        switch(opcao){
            case "0" :
                return false;
            case "1" :
                iniciarPartida();
                return true;
            case "2" :
                mostraPlacar();
                continuar();
                return true;
            case "3" :
                mostrarHistoricoDePartidas();
                continuar();
                return true;
            case "4" :
                mostrarHistoricoDeJogadasDaPartida();
                continuar();
                return true;
            default :
                System.out.println("Digite uma Opção Válida!");
                return true;
            
        }        
    }

    private void iniciarPartida() {
        Partida partidaAtual;
        if (facade.primeiraPartida()) {
            partidaAtual = facade.iniciarPartida(new Jogador(pegaNome(), pegaSimbolo()), new Jogador(pegaNome(), pegaSimbolo()));
        } else {
            partidaAtual = facade.iniciarPartida();
        }
        // Laço da Partida
        do {
            imprimeTabu(partidaAtual);
            System.out.print(partidaAtual.jogadorDaVez.nome);
            while(!facade.realizaJogada(partidaAtual, new Coordenada(pegaLinha(), pegaColuna()))){
                System.out.println("Digite um campo válido");
                imprimeTabu(partidaAtual);
            }
        } while(!facade.partidaEncerrada(partidaAtual));
        Jogador vencedor = facade.setVencedor(partidaAtual);
        
        imprimeTabu(partidaAtual);

        if (vencedor instanceof Velha) {
            System.out.println("");
            System.out.println("Deu Velha!!!");
            System.out.println("####################################");
        } else 
        if (vencedor instanceof Jogador) {
            System.out.println("");
            System.out.println("Parabéns!!! " + vencedor.nome + " Você ganhou!");
            System.out.println("----------------------------------------------------");
        }
    }

    private int pegaColuna() {
        System.out.println("Digite a coluna que deseja marcar:");
        return scanner.nextInt();
    }

    private int pegaLinha() {
        System.out.println(" Digite a Linha que Deseja marcar:");
        return scanner.nextInt();
    }

    private String pegaSimbolo() {
        System.out.println("Com qual simbolo deseja Jogar?(X/O)");
        String simbolo = scanner.next();

        while(!validaSimbolo(simbolo)) {
            System.out.println("Insira um simbolo válido");
            simbolo = scanner.next(simbolo);
        }

        return simbolo;
    }

    private boolean validaSimbolo(String simbolo) {
        if (simbolo.equalsIgnoreCase("X")
        ||simbolo.equalsIgnoreCase("O")) {
            return true;
        }
        return false;
    }

    private String pegaNome() {
        System.out.println("Digite seu nome:");
        return scanner.next();
    }

    // Método que imprime o Tabuleiro da Partida
    public void imprimeTabu(Partida p) {
        for (int i = 0; i < 3; i++) {
        Campo campoUm = p.tabuleiro.getCampoNaCoordenada(i, 0);
        Campo campoDois = p.tabuleiro.getCampoNaCoordenada(i, 1);
        Campo campoTres = p.tabuleiro.getCampoNaCoordenada(i, 2);

        System.out.println("["+campoUm.simbolo+"]"+"["+campoDois.simbolo+"]"+"["+campoTres.simbolo+"]");
        }
    }

    // Método que mostrar o Placar das Partidas
    private void mostraPlacar() {
        Placar placar = facade.recuperarPlacar();
        System.out.println("-----------PLACAR DO JOGO------------");
        System.out.println("Jogador: " + placar.jogador1.nome + " com Símbolo :" + placar.jogador1.peca + ":" + placar.jogador1.nVitorias + " Vitórias!");
        System.out.println("Jogador: " + placar.jogador2.nome + " com Símbolo :" + placar.jogador2.peca + ":" + placar.jogador2.nVitorias + " Vitórias!");
        System.out.println("Velha # : " + placar.velha.nVitorias);
    }


    private void mostrarHistoricoDePartidas() {
        ArrayList<Partida> partidas = facade.recuperaPartidas();
        System.out.println("----------------------------------");
        System.out.println("-------Histórico de Partidas------");
        System.out.println("----------------------------------");
        int count = 0;
        for(Partida tmp : partidas) {
            count++;
            System.out.println(count +"* Partida - Vencedor: " + tmp.vencedor.nome + " " + tmp.vencedor.peca);
        } 
    }

    private void mostrarHistoricoDeJogadasDaPartida() {
        mostrarHistoricoDePartidas();
        System.out.println("Selecione Qual Partida Deseja Ver as Jogadas:");
        Partida partidaSelecionada = facade.logsDaPartida(scanner.nextInt());
        for (Jogada tmp : partidaSelecionada.jogadas) {
            System.out.println("Jogador: " + tmp.jogador.nome + " com Símbolo: " + tmp.jogador.peca + " Jogou na coordenada: " + tmp.campo.coordenada.linha + "X" + tmp.campo.coordenada.coluna);
        }
        imprimeTabu(partidaSelecionada);
    }    
}
