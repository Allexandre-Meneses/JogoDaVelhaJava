import java.util.ArrayList;
import java.util.Scanner;

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
        }

        while(Validacoes.querJogar());

        jogo.mostrarHistoricoDePartidas();


        System.out.println("");
        System.out.println("Obrigado por Jogar!");
    
    }
}

class Partida {
    ArrayList<Jogador> jogadores = new ArrayList<Jogador>();
    ArrayList<Jogada> jogadas = new ArrayList<Jogada>();
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

    Jogador quemJoga(){
        if (this.jogadas.size() == 0){
            return jogadores.get(0);
        }
        if (jogadas.get(jogadas.size()-1).jogador == jogadores.get(0)){
            return jogadores.get(1);
        }
        return jogadores.get(0);
    }

}

class Jogo {
    Integer nPartida;
    ArrayList<Partida> partidas = new ArrayList<Partida>();

    void mostrarHistoricoDePartidas(){
        System.out.println("----------------------------------");
        System.out.println("-------Histórico de Partidas------");
        System.out.println("----------------------------------");
        int count = 0;
        for(Partida tmp : partidas) {
            count++;
            System.out.println(count +"* Partida - Vencedor: " + tmp.vencedor.nome + " " + tmp.vencedor.peca);
        }
    }

    Jogador addJogador(){

        Scanner sc = new Scanner(System.in);
        String simbolo;

        System.out.println("Insira seu nome:");
        String nome = sc.next();

        System.out.println("Insira com qual Símbolo você deseja jogar:(X/O)");
        simbolo = sc.next();

        while(!Validacoes.validaSimbolo(simbolo)){
            System.out.println("Insira um símbolo válido: (X/O)");
            simbolo = sc.next();
        }

        Jogador jogador = new Jogador(nome, simbolo);

        return jogador;
    }   

    void imprimeTabu(Tabuleiro atual) {
        for (int i = 0; i < 3; i++) {
        Campo campoUm = atual.getCampoNaCoordenada(i, 0);
        Campo campoDois = atual.getCampoNaCoordenada(i, 1);
        Campo campoTres = atual.getCampoNaCoordenada(i, 2);

        System.out.println("["+campoUm.simbolo+"]"+"["+campoDois.simbolo+"]"+"["+campoTres.simbolo+"]");
        }
    }
   
}

class Jogada {
    Jogador jogador;
    Campo campo;

    Jogada(Jogador jogador, Campo campo){
        this.jogador = jogador;
        this.campo = campo;
    }

}

class Jogador {
    String nome;
    String peca;

    Jogador(String nome, String peca) {
        this.nome = nome;
        this.peca = peca;

    }

    Jogador(Jogador jogador) {
        this.nome = jogador.nome;
        this.peca = jogador.peca;
    }
}

class Tabuleiro {
    ArrayList<Campo> tabuleiro = new ArrayList<Campo>();

    Tabuleiro() {
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

    Jogada realizaJogada(Jogador jogador, Jogo jogo, Tabuleiro atual) {
        Scanner sc = new Scanner(System.in);
        Campo campo;
        int linha,coluna;

        do {

            System.out.println(jogador.nome + " Digite a linha que deseja marcar:");
            linha = sc.nextInt();
            System.out.println(jogador.nome + " Digite a coluna que deseja marcar:");
            coluna = sc.nextInt();

            campo = getCampoNaCoordenada(linha, coluna);
            System.out.println(jogador.nome + " Jogou na coordenada: (" + linha + "," + coluna + ")");
        
            if (!Validacoes.campoValido(campo)){
                jogo.imprimeTabu(atual);
                System.out.println("");
                System.out.println("Campo inválido, digite um campo válido!");
                System.out.println("----------------------------------------");
            }
        }
        while(!Validacoes.campoValido(campo));

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

class Campo {
    Coordenada coordenada;
    String simbolo;

    Campo(Coordenada coordenada, String simbolo) {
        this.coordenada = coordenada;
        this.simbolo = simbolo;
    }

    public boolean isEmpty() {
        if(this.simbolo == " ") {
            return true;
        }
        return false;
    }
}

class Coordenada {
    int linha;
    int coluna;

    Coordenada(int linha, int coluna){
        this.linha = linha;
        this.coluna = coluna;
    }
}

class Validacoes {
    static boolean querJogar(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Deseja jogar novamente?(S/N)");
        String resposta = sc.next();
        if(resposta.equalsIgnoreCase("S")){
            return true;
        }
        return false;
    }

    static boolean validaSimbolo(String simbolo){
        
        if(simbolo.equalsIgnoreCase("X")||
        simbolo.equalsIgnoreCase("O")){
            return true;
        }
        return false;
    }

    static boolean campoValido(Campo campo) {
        if(campo.isEmpty()){
            return true;
        }
        return false;
    }
}

