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
public class SoldadoSerpiente extends Enemigo{

    public SoldadoSerpiente(int atk, int spd, int hp, int lvl, int expDrop, String Nombre, boolean rango) {
        super(atk, spd, hp, lvl, expDrop, Nombre, rango);
    }

    
    public void ataqueVeneno(Personaje per){
        per.setHpActual(per.getHpActual()-(this.getAtk()/2));
        int rnd=(int) (Math.random()*100);
        if(rnd<=40){
            per.setStatus("Poison");
        }
    }
}
