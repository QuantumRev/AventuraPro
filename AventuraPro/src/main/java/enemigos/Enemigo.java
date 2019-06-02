/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enemigos;

import javax.swing.JTextPane;

import clases.Elemento;
import clases.Personaje;
import interfaz.HUD;

/**
 *
 * @author 1DAM
 */
public abstract class Enemigo extends Elemento{
    protected int atk,spd,hp,lvl,expDrop;
    protected String nombre;
    protected boolean rango;

    public Enemigo(int atk, int spd, int hp, int lvl, int expDrop, String nombre,boolean rango) {
        this.atk = atk;
        this.spd = spd;
        this.hp = hp;
        this.lvl = lvl;
        this.expDrop = expDrop;
        this.nombre = nombre;
        this.rango=rango;
    }

    public int getAtk() {
        return atk;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    public int getSpd() {
        return spd;
    }

    public void setSpd(int spd) {
        this.spd = spd;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getLvl() {
        return lvl;
    }

    public void setLvl(int lvl) {
        this.lvl = lvl;
    }

    public int getExpDrop() {
        return expDrop;
    }

    public void setExpDrop(int expDrop) {
        this.expDrop = expDrop;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String Nombre) {
        this.nombre = Nombre;
    }

    public boolean isRango() {
        return rango;
    }

    public void setRango(boolean rango) {
        this.rango = rango;
    }
    
    
    public String atacar(Personaje per){
        per.setHpActual(per.getHpActual()-this.getAtk());
        return per.getNombre()+" ha recibido "+this.getAtk()+" de daño!";
    }
    
}
