package models;

import states.CampoState;
import states.VazioState;

public class Campo {
    CampoState state;
    public Coordenada coordenada;
    public String simbolo;

    Campo(Coordenada coordenada, String simbolo) {
        this.state = new VazioState(this);
        this.coordenada = coordenada;
        this.simbolo = simbolo;
    }

    public void changeState(CampoState state) {
        this.state = state;
    }

    public CampoState getState() {
        return state;
    }

    public boolean isEmpty() {
        if(this.simbolo == " ") {
            return true;
        }
        return false;
    }
}