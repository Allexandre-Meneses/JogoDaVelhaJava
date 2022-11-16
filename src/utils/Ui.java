package utils;

import java.util.Scanner;
import models.Jogo;

public class Ui {
    public Scanner scanner = new Scanner(System.in);

    void continuar() {
        System.out.println("");
        System.out.println("Digite s para continuar!");
        scanner.next();
        System.out.println("\n");
    }

    public boolean chamaMenu(Jogo jogo){
        System.out.println("-------JOGO#DA#VELHA-------");
        System.out.println("|1| INICIAR PARTIDA");
        System.out.println("|2| MOSTRAR PLACAR");
        System.out.println("|3| MOSTRAR HISTÓRICO DE PARTIDAS");
        System.out.println("|4| MOSTRAR HISTÓRICO DE JOGADAS DE UMA PARTIDA");
        System.out.println("|0| SAIR");

        String opcao = scanner.next();
        
        switch(opcao){
            case "0" :
                return false;
            case "1" :
                return true;
            case "2" :
                jogo.mostraPlacar();
                continuar();
                return this.chamaMenu(jogo);
            case "3" :
                jogo.mostrarHistóricoDePartidas();
                continuar();
                return this.chamaMenu(jogo);
            case "4" :
                jogo.mostrarHistoricoDaPartida();
                continuar();
                return this.chamaMenu(jogo);

            default :
            
        }
        return false;
        
    }
}
