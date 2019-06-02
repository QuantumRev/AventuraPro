/*
 * To change this license header, choose License Headers in Project Prothisties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import enemigos.Enemigo;
import enemigos.Goblin;
import enemigos.Ladron;
import interfaz.HUD;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.JTextPane;
import java.io.FileInputStream;
/**
 *
 * @author 1DAM
 */
public class Personaje extends Elemento implements Serializable {
    String nombre,status;
    int atk,spd,spdActual,hp,hpActual,lvl,exp,oro;
    Arma armaEq;
    HashMap<String,Objeto> inventario;
    int[] posPer;
    boolean vivo,huir,combate;
    String acc="nada";
    

    public Personaje(String nombre, String status, int atk, int spd,int spdActual, int hp,int hpActual, int lvl, int exp, int oro, Arma armaEq, int[] posPer) {
        this.nombre = nombre;
        this.status = status;
        this.atk = atk;
        this.spd = spd;
        this.spdActual=spdActual;
        this.hp = hp;
        this.hpActual=hpActual;
        this.lvl = lvl;
        this.exp = exp;
        this.oro = oro;
        this.armaEq = armaEq;
        this.inventario = new HashMap<String,Objeto>();
        this.inventario.put("Pocion",new Objeto(98,5,"Pocion"));
        this.posPer = posPer; 
        this.vivo=true;
        this.huir=false;
        this.combate=false;
        /*
        consola.atacar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				acc="Atacar";
			}
		});
        consola.objetos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				acc="Objetos";
			}
		});
        consola.huir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				acc="Huir";
			}
		});*/
    }
    

    //getter setter begin
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public int getSpdActual() {
        return spdActual;
    }

    public void setSpdActual(int spdActual) {
        this.spdActual = spdActual;
    }
    
    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getHpActual() {
        return hpActual;
    }

    public void setHpActual(int hpActual) {
        this.hpActual = hpActual;
    }
    
    public int getLvl() {
        return lvl;
    }

    public void setLvl(int lvl) {
        this.lvl = lvl;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getOro() {
        return oro;
    }

    public void setOro(int oro) {
        this.oro = oro;
    }

    public Arma getArmaEq() {
        return armaEq;
    }

    public void setArmaEq(Arma armaEq) {
        this.armaEq = armaEq;
    }

    public HashMap<String, Objeto> getInventario() {
        return inventario;
    }

    public void setInventario(HashMap<String, Objeto> inventario) {
        this.inventario = inventario;
    }

    public int[] getPosPer() {
        return posPer;
    }

    public void setPosPer(int[] posPer) {
        this.posPer = posPer;
    }

    public boolean isVivo() {
        return vivo;
    }
    
    public void setAcc(String acc) {
    	this.acc=acc;
    }

    public void setVivo(boolean vivo) {
        this.vivo = vivo;
    }
    
    public boolean isHuir() {
		return huir;
	}


	public void setHuir(boolean huir) {
		this.huir = huir;
	}


	public boolean isCombate() {
		return combate;
	}


	public void setCombate(boolean combate) {
		this.combate = combate;
	}
    
    
    //getter setter end
    
    


	public void cogerObjeto(Objeto obj){
        this.inventario.put(obj.getNombre(),obj);
    }
	
	public void usarObjeto(Objeto o) {
		if(o.getClass().equals(Arma.class)) {
			
		}else {
			this.inventario.remove(o);
		}
	}
	
    public void checkStatus(){
        switch(this.status){
            case "Poison":
                this.hpActual=hpActual-3;
                break;
            case "Paralized":
                this.spdActual=spdActual/2;
                break;
            case "Normal":
                this.spdActual=spd;
                break;
        }
    }
    public void comprar(Objeto obj){
        this.oro=this.oro-obj.getValue();
        this.inventario.put(obj.getNombre(),obj);
    }
    public String muestaPagPer(){
        return "Nombre: "+this.nombre+" "+"HP: "+this.hp+"/"+this.hpActual+" Ataque: "+(this.atk+this.armaEq.getAtk())+"\n"+
               "Fuerza: "+this.atk+" Velocidad: "+this.spd+" Oro: "+this.oro;
                
    }
    
    public String atacar(Enemigo en){
    	String ret="";
        if((en.isRango()==true)&&(this.armaEq.isRango()==false)){
            ret+=("Necesitas un arma de rango!");
        }
        else{
            en.setHp(en.getHp()-(this.atk+this.armaEq.getAtk()));
            ret+=(en.getNombre()+" ha recibido "+(this.atk+this.armaEq.getAtk())+" de daño!");
        }  
        return ret;
    }
    public String abreInventario(){
    				String ret="";
                    Objeto salir = new Objeto(99,0,"Salir");
                   // this.getInventario().add(salir);
                    ret+=("Inventario: \n");
                    //Scanner scaObj = new Scanner(System.in);
                    for(int i=0;i<this.getInventario().size();i++){
                        ret+=(i+" "+this.getInventario().get(i).getNombre());
                    }
                    
                    ret+=("\nUsar objeto (id): ");
                    /*int op=scaObj.nextInt();
                    switch(this.getInventario().get(op).getNombre()){
                        case "Pocion":
                            this.setHpActual(this.getHpActual()+15);
                            ret+=(this.getNombre()+" se ha curado 15HP");
                            this.getInventario().remove(op);
                            break;
                        case "Bomba de humo":
                            if(this.combate){
                                this.huir=true;
                                ret+=(this.getNombre()+" ha usado una bomba de humo!");
                                this.getInventario().remove(op);
                            }
                            else{
                                ret+=(this.getInventario().get(op).getNombre()+" solo se puede usar en combate!");
                            }
                            break;
                        case "Arco":
                            this.getInventario().add(this.getArmaEq());
                            this.setArmaEq(new Arma(7,2,true,3,20,"Arco"));
                            this.getInventario().remove(op);
                            break;
                        case "Espada corta":
                            this.getInventario().add(this.getArmaEq());
                            this.setArmaEq(new Arma(4,2,false,2,10,"Espada corta"));
                            this.getInventario().remove(op);
                            break;
                        case "Hacha de guerra":
                            this.getInventario().add(this.getArmaEq());
                            this.setArmaEq(new Arma(10,3,false,3,25,"Hacha de guerra"));
                            this.getInventario().remove(op);
                            break;
                        case "Salir":
                            break;
                    }*/
                    this.getInventario().remove(salir);
                    return ret;
    }
    
    public String combate(Enemigo en){

    	String ret="";
        this.combate=true;
        Ladron ld=null;
        this.huir=false;
        if(en.getNombre().equals("Ladron")){
            ld=(Ladron)en;
        }
        ret+=("Un "+en.getNombre()+" ataca!");
        
        
            ret+=("Elija una accion: ");
            ret+=("Atacar - Objetos - Huir\n");
            /*while (!acc.equals("Atacar")&&(!acc.equals("Objetos"))&&(!acc.equals("Huir"))){
                ret+=("Atacar - Objetos - Huir\n");
            }
            */
            switch(acc){
                case "Atacar":
                    atacar(en);
                    acc="";
                    break;
                case "Objetos":
                    abreInventario();
                    acc="";
                    break;
                case "Huir":
                    int rnd=(int) (Math.random()*100);
                    if(rnd<=40){
                    huir=true;
                    }
                    else{
                        ret+=(this.getNombre()+" no ha podido huir!");
                    }
                    acc="";
                    break;    
            }
            if(huir==true){
                    ret+=(this.getNombre()+" ha huido!");
            }
            else if(ld==null){
                if(en.getHp()>0){
                ret+=("\n");
                en.atacar(this);
                }
                else{
                    ret+=(en.getNombre()+" ha sido derrotado!");
                }
                if(this.hpActual<=0){
                    this.vivo=false;
                    ret+=(this.nombre+" ha muerto!");
                }
                
                ret+=("\n");
                
            }
            else{
                if(ld.getHp()>0){
                    if(ld.getOroRobado()==0){
                        ld.robarOro(this);
                    }
                    else{
                        int rnd=(int) (Math.random()*100);
                        ret+=(ld.getNombre()+" intenta escapar!");
                        if(rnd<=35){
                             this.huir=true;
                             ret+=(ld.getNombre()+" ha huido con "+ld.getOroRobado()+" de oro!");
                             ret+=("\n");
                        }else{
                            ret+=(ld.getNombre()+" no ha podido huir!");
                        }
                    }
                }
                else{
                 ret+=(en.getNombre()+" ha sido derrotado!");
                 this.oro=this.oro+ld.getOroRobado();
                    ret+=(this.nombre+" ha recuperado "+ld.getOroRobado()+" de oro!");
                }
                if(this.hpActual<=0){
                    this.vivo=false;
                    ret+=(this.nombre+" ha muerto!");
                }
                
                ret+=("\n");
                
        }
        this.combate=false;
        this.huir=false;
        return ret;
    }


	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "P";
	}
	
		
    
    
    
    
    
    
    
    
    
    
}
