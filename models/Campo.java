package models;

public class Campo {
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