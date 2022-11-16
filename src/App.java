import models.Jogador;
import models.Jogo;
import models.Partida;
import models.Tabuleiro;
import utils.ScannerJogo;
import utils.Ui;

public class App {
    public static void main(String[] args) throws Exception {
        //Iniciar Jogo
        Jogo jogo = new Jogo();
        Ui ui = new Ui();

        //Laço Jogar novamente
        do{

            Partida partida = new Partida();
            if(jogo.partidas.size() > 0) {
                partida.jogadores = jogo.partidas.get(0).jogadores;
            }
            jogo.partidas.add(partida);
            partida.tabuleiro = new Tabuleiro();
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
                jogo.imprimeTabu(partida.tabuleiro);
    
                // Realizar Jogada
                partida.jogadas.add(partida.tabuleiro.realizaJogada(jogadorDaVez, jogo, partida.tabuleiro));
            }
            while(!partida.temVencedor(partida.tabuleiro) && !partida.deuVelha(partida.tabuleiro)); 

            if(partida.temVencedor(partida.tabuleiro)){
                partida.setVencedor(jogadorDaVez);
                jogo.imprimeTabu(partida.tabuleiro);
                System.out.println("");
                System.out.println("Parabéns!!! " + jogadorDaVez.nome + " Você ganhou!");
                System.out.println("----------------------------------------------------");
            } else {
                partida.setVencedor(new Jogador("Velha", "#"));
                jogo.imprimeTabu(partida.tabuleiro);
                System.out.println("");
                System.out.println("Deu Velha!!!");
                System.out.println("####################################");
                }
            
        }
        while(ui.chamaMenu(jogo));

        System.out.println("");
        System.out.println("Obrigado por Jogar!");
    
    }
    
    public static boolean querSair(){
        ScannerJogo sc = new ScannerJogo();
        System.out.println("Deseja Realmente Sair?(S/N)");
        String resposta = sc.scanner.next();
        if(resposta.equalsIgnoreCase("S")){
            return true;
        }
        return false;
    }
}











