/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package local.mainAPI.credencial.senha;

/**
 *
 * @author Vitor
 */
public class CriterioNumMinimoCaractere extends CriterioIplm implements ICriterio {

    private final int NUM_MIN_CARACTERE;
    private int numCaractere = 0;
    
    public CriterioNumMinimoCaractere(int _ID, String _Nome, int _numMin) {
        super(_ID, _Nome);
        this.NUM_MIN_CARACTERE = _numMin;
    }


    @Override
    public void teste(int index, char c) {
        numCaractere++;
    }

    @Override
    public boolean getResultado() {
        return this.numCaractere >= NUM_MIN_CARACTERE;
    }

    @Override
    public String getMensagem() {
        if(getResultado())
        {
            return "OK";
        }
        else
        {
            return "Número de caracteres menor que " + NUM_MIN_CARACTERE;
        }
    }
    
    
    
}
