/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

/**
 *
 * @author 1DAM
 */
public class Ladron extends Enemigo{
    int oroRobado;

    public Ladron(int oroRobado, int atk, int spd, int hp, int lvl, int expDrop, String Nombre, boolean rango) {
        super(atk, spd, hp, lvl, expDrop, Nombre, rango);
        this.oroRobado = oroRobado;
    }

    public int getOroRobado() {
        return oroRobado;
    }

    public void setOroRobado(int oroRobado) {
        this.oroRobado = oroRobado;
    }
    
    public void robarOro(Personaje per){
        
    }
    
}
