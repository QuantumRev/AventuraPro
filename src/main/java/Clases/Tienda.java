/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;

import interfaz.HUD;
import interfaz.Ventana;

/**
 *
 * @author 1DAM
 */
public class Tienda extends Elemento {
    Objeto[] inventario = {new Objeto(98,10,"Pocion"),new Objeto(97,5,"Bomba de humo"),new Arma(10,3,false,3,25,"Hacha de guerra")};

    public Tienda() {
        
    }

    public String vender(Personaje per,Ventana ventana){
    	
    	int n = JOptionPane.showOptionDialog(ventana,"Bienvenido","Tienda",JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE,
				null,
				inventario,
				inventario[2]);
    			String ret="";
                if(per.getOro()>=this.inventario[n].getValue()){
                	per.setOro(per.getOro()-this.inventario[n].value);
                    per.getInventario().put(this.inventario[n].getNombre(),this.inventario[n]);
                    ret+=("\n"+per.getNombre()+" ha comprado una "+this.inventario[n].getNombre()+"!");
                 }else{
                    JOptionPane.showMessageDialog(ventana,per.getNombre()+" no tiene suficiente oro!");
                 }
                 return ret;
    }
    @Override
	public String toString() {
		return "T";
	}
    
}
