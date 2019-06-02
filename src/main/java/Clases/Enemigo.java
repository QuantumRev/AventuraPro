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
public class Enemigo extends Elemento{
    int atk,spd,hp,lvl,expDrop;
    String Nombre;
    boolean rango;

    public Enemigo(int atk, int spd, int hp, int lvl, int expDrop, String Nombre,boolean rango) {
        this.atk = atk;
        this.spd = spd;
        this.hp = hp;
        this.lvl = lvl;
        this.expDrop = expDrop;
        this.Nombre = Nombre;
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
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public boolean isRango() {
        return rango;
    }

    public void setRango(boolean rango) {
        this.rango = rango;
    }
   
    public void atacar(Personaje per){
        per.setHpActual(per.getHpActual()-this.getAtk());
        System.out.println(per.getNombre()+" ha recibido "+this.getAtk()+" de daño!");
    }
    
}
