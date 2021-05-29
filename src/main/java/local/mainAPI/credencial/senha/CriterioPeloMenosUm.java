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
public class CriterioPeloMenosUm extends CriterioIplm implements ICriterio {
    
    private final int numMinCaractere = 1;
    private int numCaractere = 0;
    private final String CARACTERE_PERMITIDO;

    public CriterioPeloMenosUm(int _ID, String _Nome, String CARACTERE_PERMITIDO) {
        super(_ID, _Nome);

        this.CARACTERE_PERMITIDO = CARACTERE_PERMITIDO;
    }

    @Override
    public void teste(int index, char c) {
        if(CARACTERE_PERMITIDO.indexOf(c) >= 0)
        {
            numCaractere++;
        }
    }

    @Override
    public boolean getResultado() {
        return this.numCaractere >= numMinCaractere;
    }

    @Override
    public String getMensagem() {
        if(getResultado())
        {
            return "OK";
        }
        else
        {
            return "Critério não atendido: " + this.getNome();
        }
    }
}
