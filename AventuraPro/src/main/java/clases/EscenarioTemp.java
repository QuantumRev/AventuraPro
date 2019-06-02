/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

import enemigos.Goblin;
import enemigos.Ladron;
import enemigos.SoldadoSerpiente;
import interfaz.HUD;

/**
 *
 * @author 1DAM
 */
public class EscenarioTemp implements Serializable {
    public Casilla[][] casillas = new Casilla[5][5];
    public String guardCas="#";

    public EscenarioTemp() {
        creaEscenario(); 
        //this.setVisualCasilla("T", 4, 4);
        this.getCasillas()[4][4].setEl(new Tienda());
        //this.setVisualCasilla("G", 0, 3);
        this.getCasillas()[0][3].setEl(new Goblin(5,5,10,1,20,"Goblin",false));
        //this.setVisualCasilla("G", 1, 4);
        this.getCasillas()[1][4].setEl(new Goblin(5,5,10,1,20,"Goblin",false));
        //this.setVisualCasilla(")", 0, 4);
        this.getCasillas()[0][4].setEl(new Arma(7,2,true,3,20,"Arco"));
        //this.setVisualCasilla("S", 2, 4);
        this.getCasillas()[2][4].setEl(new SoldadoSerpiente(10,10,20,5,50,"Soldado Serpiente",true));
        //this.setVisualCasilla("L", 3, 1);
        this.getCasillas()[3][1].setEl(new Ladron(5,10,25,3,20,"Ladron",false,new int[] {3,1}));
        //this.setVisualCasilla("$", 2, 2);
        this.getCasillas()[2][2].setEl(new Objeto(1,10,"Bolsa de oro"));
        //this.getCasillas()[jugador.getPosPer()[0]][jugador.getPosPer()[1]].setEl(jugador);
        //this.setVisualCasilla("J", jugador.getPosPer()[0], jugador.getPosPer()[1]);
        
        
    }

    /*public Personaje getJugador() {
		return jugador;
	}

	public void setJugador(Personaje jugador) {
		this.jugador = jugador;
	}*/

	public Casilla[][] getCasillas() {
        return casillas;
    }

    public void setCasillas(Casilla[][] casillas) {
        this.casillas = casillas;
    }

    public String getGuardCas() {
        return guardCas;
    }

    public void setGuardCas(String guardCas) {
        this.guardCas = guardCas;
    }
    
    
    public void creaEscenario(){
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                int[] posCas={i,j};
                this.casillas[i][j]=new Casilla(posCas,"#");
            }
        }
    }
    public String imprimeEscenario(){
        String fin="";
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                fin+=this.casillas[i][j].getVisual()+" ";
                ;
            }
            fin+="\n";
        }
        return fin;
    }
    /*public void hasPersonje(Personaje per){
        for(int i=0;i<this.casillas.length;i++){
            for(int j=0;j<casillas[i].length;j++){
                if((i==per.getPosPer()[0])&&(j==per.getPosPer()[1])){
                    casillas[i][j].setVisual("P");
                }
            }
        }   
    }
    public void setVisualCasilla(String visual,int pos1,int pos2){
        casillas[pos1][pos2].setVisual(visual);
    }*/
    /*
    public void compruebaCasilla(Personaje per){
        Casilla cas = casillas[per.getPosPer()[0]][per.getPosPer()[1]];
        switch(cas.getVisual()){
            case "G":
                Enemigo en=(Enemigo) cas.getEl();
                per.combate(en);
                this.guardCas="#";
                break;
            case "$":
                consola.mostrarTexto("Has obtenido 10 de oro!");
                per.setOro(per.getOro()+10);  
                this.guardCas="#";
                break;
            case ")":
                Arma ar=(Arma) cas.getEl();
                per.getInventario().add(new Arma(7,2,true,3,20,"Arco"));
                consola.mostrarTexto("Has obtenido un Arco! ");
                this.guardCas="#";
                break;
            case "S":
                Enemigo en2=(Enemigo) cas.getEl();
                per.combate(en2);
                this.guardCas="#";
                break;
        }
    }*/
    /*
    public int[] moverPer(Personaje per, char mov){
        int[] posPer=per.getPosPer();
        switch(mov){
            case 'w':{
            casillas[posPer[0]][posPer[1]].setVisual(this.guardCas);
            posPer[0]-=1;
            if(posPer[0]==-1){
                posPer[0]=casillas.length-1;
            }
            this.guardCas=casillas[posPer[0]][posPer[1]].getVisual();
            compruebaCasilla(per);
            casillas[posPer[0]][posPer[1]].setVisual("P");
            break;
        }
        case 's':{
            casillas[posPer[0]][posPer[1]].setVisual(this.guardCas);
            posPer[0]+=1;
            if(posPer[0]==casillas.length){
                posPer[0]=0;
            }
            this.guardCas=casillas[posPer[0]][posPer[1]].getVisual();
            compruebaCasilla(per);
            casillas[posPer[0]][posPer[1]].setVisual("P");
            break;
        }
        case 'a':{
            casillas[posPer[0]][posPer[1]].setVisual(this.guardCas);
            posPer[1]-=1;
            if(posPer[1]==-1){
                posPer[1]=casillas.length-1;
            }
            this.guardCas=casillas[posPer[0]][posPer[1]].getVisual();
            compruebaCasilla(per);
            casillas[posPer[0]][posPer[1]].setVisual("P");
            break;
        }
        case 'd':{
            casillas[posPer[0]][posPer[1]].setVisual(this.guardCas);
            posPer[1]+=1;
            if(posPer[1]==casillas.length){
                posPer[1]=0;
            }
            this.guardCas=casillas[posPer[0]][posPer[1]].getVisual();
            compruebaCasilla(per);
            casillas[posPer[0]][posPer[1]].setVisual("P");
            break;
        }
        case 'e':{
            consola.mostrarTexto(per.muestaPagPer()+"\n");
            break;
        }
        case 'i':{
            per.abreInventario();
            break;
        }
        }
        
        return posPer;
    
    }*/
}
