/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enemigos;

import javax.swing.JTextPane;

import clases.Personaje;
import interfaz.HUD;

/**
 *
 * @author 1DAM
 */
public class Ladron extends Enemigo{
    int oroRobado;
    int[] posLad;
    public Ladron(int atk, int spd, int hp, int lvl, int expDrop, String nombre, boolean rango,int[] posLad) {
        super(atk, spd, hp, lvl, expDrop, nombre, rango);
        this.oroRobado = 0;
        this.posLad=posLad;
    }

    public int getOroRobado() {
        return oroRobado;
    }

    public void setOroRobado(int oroRobado) {
        this.oroRobado = oroRobado;
    }
    
    
    public int[] getPosLad() {
		return posLad;
	}

	public void setPosLad(int[] posLad) {
		this.posLad = posLad;
	}

	public String robarOro(Personaje per){
        String ret="";
    	int rnd=(int) (Math.random()*100);
        ret+=this.nombre+" intenta robar a "+per.getNombre()+"!\n";
        if(rnd<=70){
            this.oroRobado=per.getOro()/2;
            per.setOro(this.oroRobado);
           	ret+=this.nombre+" ha robado "+this.oroRobado+" de oro!\n";        
        }else{
            ret+=this.nombre+" no ha podido robar nada!\n";
        }
        return ret;
    }
    
    @Override
	public String toString() {
		return "L";
	}
    
}
