/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package local.mainAPI.credencial.senha;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Vitor
 */
public class CriterioSemRepeticao extends CriterioIplm implements ICriterio {

    private final Set<Character> conjuntoCaractere = new HashSet<>();
    private String mensagem = "OK";
    private boolean valido = true;
    
     public CriterioSemRepeticao(int _ID, String _Nome) {
        super(_ID, _Nome);

    }
    
    @Override
    public void teste(int index, char c) {
        this.conjuntoCaractere.add(c);
        if(this.conjuntoCaractere.size() != index + 1)
        {
            valido = false;
            mensagem = "Caractere " + c + " repetido!";
        }
    }

    @Override
    public boolean getResultado() {
        return this.valido;
    }

    @Override
    public String getMensagem() {
        return this.mensagem;
    }
    
}
