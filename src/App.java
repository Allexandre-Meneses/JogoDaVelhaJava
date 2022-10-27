import models.Jogador;
import models.Jogo;
import models.Partida;
import models.Tabuleiro;
import utils.ScannerJogo;
import utils.Validações;

public class App {
    public static void main(String[] args) throws Exception {
        //Iniciar Jogo
        Jogo jogo = new Jogo();

        //Laço Jogar novamente
        do{
            Partida partida = new Partida();
            if(jogo.partidas.size() > 0) {
                partida.jogadores = jogo.partidas.get(0).jogadores;
            }
            jogo.partidas.add(partida);
            Tabuleiro t = new Tabuleiro();
            Jogador jogadorDaVez;

            if(jogo.partidas.size()==1){
                // Adicionar Jogadores
                Jogador j1 = new Jogador(jogo.addJogador());
                partida.jogadores.add(j1);
                Jogador j2 = new Jogador(jogo.addJogador());
                partida.jogadores.add(j2);
            }
                //Laço
                do {
                    // Seleciona o jogador da Vez
                    jogadorDaVez = partida.quemJoga();
    
                    // Mostrar Tabuleiro
                    jogo.imprimeTabu(t);
    
                    // Realizar Jogada
                    partida.jogadas.add(t.realizaJogada(jogadorDaVez, jogo, t));
                }
                while(!partida.temVencedor(t) && !partida.deuVelha(t)); 

                if(partida.temVencedor(t)){
                    partida.setVencedor(jogadorDaVez);
                    jogo.imprimeTabu(t);
                    System.out.println("");
                    System.out.println("Parabéns!!! " + jogadorDaVez.nome + " Você ganhou!");
                    System.out.println("----------------------------------------------------");
                } else {
                    partida.setVencedor(new Jogador("Velha", "#"));
                    jogo.imprimeTabu(t);
                    System.out.println("");
                    System.out.println("Deu Velha!!!");
                    System.out.println("####################################");
                }

                jogo.mostraPlacar();
        }

        while(querJogar());

        jogo.mostrarHistóricoDePartidas();
        jogo.mostraPlacar();


        System.out.println("");
        System.out.println("Obrigado por Jogar!");
    
    }
    public boolean querJogar(){
        ScannerJogo sc = new ScannerJogo();
        System.out.println("Deseja jogar novamente?(S/N)");
        String resposta = sc.scanner.next();
        if(resposta.equalsIgnoreCase("S")){
            return true;
        }
        return false;
    }
}











