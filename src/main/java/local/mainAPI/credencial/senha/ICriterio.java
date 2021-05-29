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
public interface ICriterio {
    
    public int getID();
    public String getNome();
    
    public void teste(int index, char c);
    
    public boolean getResultado();
    
    public String getMensagem();
    
}
