package states;

import models.Campo;

public class MarcadoState extends CampoState {

    protected MarcadoState(Campo campo) {
        super(campo);
        //TODO Auto-generated constructor stub
    }

    @Override
    public boolean marcar(String peca) {
        return false;
    }

}
