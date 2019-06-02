/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.io.Serializable;

import javax.swing.JLabel;

/**
 *
 * @author 1DAM
 */
public class Casilla extends JLabel implements Serializable {
    int[] posCasilla;
    Elemento el;

    public Casilla(int[] posCasilla,String visual) {
    	super(visual);
        this.posCasilla = posCasilla;
        this.el=null;
    }

    public int[] getPosCasilla() {
        return posCasilla;
    }

    public void setPosCasillaX(int[] posCasilla) {
        this.posCasilla = posCasilla;
    }

    public String getVisual() {
    	if(this.el!=null) {
    		return el.toString();
    	}
        return "#";
    }

    public Elemento getEl() {
        return el;
    }

    public void setEl(Elemento el) {
        this.el = el;
        if(el!=null) {
        	this.setText(el.toString());
        }else {
        	this.setText("#");
        }
    }
    
    
}
