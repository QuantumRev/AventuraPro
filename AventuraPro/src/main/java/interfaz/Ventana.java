package interfaz;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;

import clases.Arma;
import clases.Casilla;
import clases.EscenarioTemp;
import clases.Personaje;
import clases.Tienda;
import enemigos.Enemigo;
import enemigos.Goblin;
import enemigos.Ladron;
import enemigos.SoldadoSerpiente;
import javazoom.jl.decoder.JavaLayerException;
import sonido.MusicPlayer;



public class Ventana extends JFrame{
	private HUD hud;
	private Inicio inicio;
	
	public Ventana() {
		super();
		this.setTitle("Mi programa");
		this.setSize(400, 300);
		this.setResizable(false);
		this.setVisible(true);
		this.inicio=new Inicio(this);
        setContentPane(inicio);
	}
	
	public void irAHud() {
		if(hud==null) {
			Personaje jugador=new Personaje("Pepe","Normal",3,3,3,15,15,1,0,10,new Arma(4,2,false,2,10,"Espada corta"),new int[]{0,0});
			EscenarioTemp esc = new EscenarioTemp();
			esc.getCasillas()[jugador.getPosPer()[0]][jugador.getPosPer()[1]].setEl(jugador);
			hud=new HUD(this,esc,jugador);
		}
		this.inicio.setVisible(false);
		this.setSize(650,500);
		setContentPane(hud);
		hud.setVisible(true);
	}
	
	public void cargaJuego(EscenarioTemp esc,Personaje per) {
		if(hud==null) {
			hud=new HUD(this,esc,per);
		}
		this.inicio.setVisible(false);
		this.setSize(650,500);
		setContentPane(hud);
		hud.setVisible(true);
	}
	
	public void irAInicio() {
		if(hud!=null) {
			hud.setVisible(false);
			hud=null;
		}
		this.setSize(400, 300);
		this.setContentPane(inicio);
		inicio.setVisible(true);
	}

}
