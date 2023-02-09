package states;

import models.Campo;

public class VazioState extends CampoState{

    public VazioState(Campo campo) {
        super(campo);
        //TODO Auto-generated constructor stub
    }

    @Override
    public boolean marcar(String peca) {
        campo.simbolo = peca;
        campo.changeState(new MarcadoState(campo));
        return true;
        
    }


    
}
