package states;

import models.Campo;

public abstract class CampoState {
    protected Campo campo;

    protected CampoState(Campo campo) {
        this.campo = campo;
    }

    public abstract boolean marcar(String peca);

}
