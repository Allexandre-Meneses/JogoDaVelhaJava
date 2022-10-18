package utils;

import models.Campo;

public class Validações {
    public static boolean querJogar(){
        ScannerJogo sc = new ScannerJogo();
        System.out.println("Deseja jogar novamente?(S/N)");
        String resposta = sc.scanner.next();
        if(resposta.equalsIgnoreCase("S")){
            return true;
        }
        return false;
    }

    public static boolean validaSimbolo(String simbolo){
        
        if(simbolo.equalsIgnoreCase("X")||
        simbolo.equalsIgnoreCase("O")){
            return true;
        }
        return false;
    }

    public static boolean campoValido(Campo campo) {
        if(campo.isEmpty()){
            return true;
        }
        return false;
    }
}
