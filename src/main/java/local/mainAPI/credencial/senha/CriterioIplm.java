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
public abstract class CriterioIplm implements ICriterio {
    
    private final int ID;
    private final String Nome;

    public CriterioIplm(int ID, String Nome) {
        this.ID = ID;
        this.Nome = Nome;
    }
    
    @Override
    public int getID() {
        return this.ID;
    }

    @Override
    public String getNome() {
        return this.Nome;
    }
    
    
}
