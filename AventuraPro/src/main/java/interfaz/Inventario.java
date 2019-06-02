package interfaz;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import clases.Arma;
import clases.Objeto;
import clases.Personaje;
import enemigos.Enemigo;
import enemigos.Ladron;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class Inventario extends JDialog{
	private JTextField select;
	public Inventario(Personaje per,HUD hud) {
		setLayout(null);
		setTitle("Inventario");
		setSize(240,320);
		JList list =inicializarLista(per);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane sp = new JScrollPane(list,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		sp.setBounds(10, 11, 220, 210);
		add(sp);
		
		JButton btnUsar = new JButton("Usar");
		btnUsar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				select.setText(list.getSelectedValue().toString());
				String op = list.getSelectedValue().toString();
				elijeObjeto(op,hud,per);
				dispose();
			}
		});
		btnUsar.setBounds(10, 232, 105, 23);
		add(btnUsar);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnSalir.setBounds(125, 232, 105, 23);
		add(btnSalir);
		
		select = new JTextField();
		select.setBounds(10, 266, 220, 20);
		add(select);
		select.setColumns(10);
	}
	
	public Inventario(Personaje per,HUD hud,Enemigo en) {
		setLayout(null);
		setTitle("Inventario");
		setSize(240,320);
		JList list =inicializarLista(per);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane sp = new JScrollPane(list,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		sp.setBounds(10, 11, 220, 210);
		add(sp);
		
		JButton btnUsar = new JButton("Usar");
		btnUsar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				select.setText(list.getSelectedValue().toString());
				String op = list.getSelectedValue().toString();
				Enemigo enActual=en;
				elijeObjeto(op,hud,per);
				if(per.isCombate()) {
					if(enActual.getClass().getName().equals("enemigos.Ladron")) {//...un ladron...
						Ladron lad=(Ladron)enActual;
						
							if(enActual.getHp()>0){//Si esta vivo...
								if(lad.getOroRobado()==0) {//Roba oro si todavia no ha robado
									hud.getTp().setText(hud.getTp().getText()+"\n"+lad.robarOro(per)+"\n");
								}else {//Si ya ha robado, entonces intenta huir
									int rnd=(int) (Math.random()*100);
									hud.getTp().setText(hud.getTp().getText()+"\n"+lad.getNombre()+" intenta huir!\n");
									if (rnd<=50) {//50% de probabilidad de huir...
										hud.getTp().setText(hud.getTp().getText()+"\n"+lad.getNombre()+" ha huido con "+lad.getOroRobado()+" de oro...\n");
										hud.getEscenario().getCasillas()[hud.getPosEn()[0]][hud.getPosEn()[1]].setEl(null);
						            	enActual=null;
						            	lad=null;
						            	hud.getTOTG().stop();
						            	hud.setBackGroundMusic(hud.getPlains());
						            	hud.getBackGroundMusic().start();
						            	per.setCombate(false);
									
									}else {//Si no, falla
										hud.getTp().setText(hud.getTp().getText()+"\n"+lad.getNombre()+" no pudo huir!\n");
									}
								}
								
				            }else {//Si el ladron es derrotado antes de que escape, se recupera el oro
				            	
				            	hud.getTp().setText(hud.getTp().getText()+"\n"+enActual.getNombre()+" ha sido derrotado!");
				            	per.setOro(per.getOro()+lad.getOroRobado());
				            	hud.getTp().setText(hud.getTp().getText()+"\n"+per.getNombre()+" ha recuperado "+lad.getOroRobado()+" de oro!");
				            	hud.getEscenario().getCasillas()[hud.getPosEn()[0]][hud.getPosEn()[1]].setEl(null);
				            	enActual=null;
				            	lad=null;
				            	per.setCombate(false);
				            	hud.getTOTG().stop();
				            	hud.setBackGroundMusic(hud.getPlains());
				            	hud.getBackGroundMusic().start();
				            }
							
						}else {//...o un enemigo
							
								if(enActual.getHp()>0){//Si el nemigo vive, ataca al jugador
									hud.getTp().setText(hud.getTp().getText()+"\n");
									hud.getTp().setText(hud.getTp().getText()+enActual.atacar(per)+"\n");
					            }else {//Si muere, se elimina de la casilla
					            	
					            	hud.getTp().setText(hud.getTp().getText()+"\n"+enActual.getNombre()+" ha sido derrotado!");
					            	hud.getEscenario().getCasillas()[hud.getPosEn()[0]][hud.getPosEn()[1]].setEl(null);
					            	enActual=null;
					            	per.setCombate(false);
					            	hud.getTOTG().stop();
					            	hud.setBackGroundMusic(hud.getPlains());
					            	hud.getBackGroundMusic().start();
					            }
						}
				}
				
				
						
	
				dispose();
			}
		});
		btnUsar.setBounds(10, 232, 105, 23);
		add(btnUsar);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnSalir.setBounds(125, 232, 105, 23);
		add(btnSalir);
		
		select = new JTextField();
		select.setBounds(10, 266, 220, 20);
		add(select);
		select.setColumns(10);
	}
	public void elijeObjeto (String op,HUD hud,Personaje per) {
		switch(op) {
		case "Pocion":
			hud.getTp().setText(hud.getTp().getText()+"\n"+per.getNombre()+" ha usado una poción y se cura 15HP!");
			per.setHpActual(per.getHpActual()+15);
			if(per.getHpActual()>per.getHp()) {
				per.setHpActual(per.getHp());
			}
			per.getInventario().remove(op);
			break;
		case "Bomba de humo":
			if(per.isCombate()) {
				hud.getTp().setText(hud.getTp().getText()+"\n"+per.getNombre()+" ha usado una bomba de humo para escapar!"+"\n"+per.getNombre()+" ha huido!");
				per.getInventario().remove(op);
				per.setCombate(false);
				hud.getTOTG().stop();
            	hud.setBackGroundMusic(hud.getPlains());
            	hud.getBackGroundMusic().start();
			}
			else {
				hud.getTp().setText(hud.getTp().getText()+"\nEste objeto solo se puede usar en combate!");
			}
			break;
		case "Arco":
			hud.getTp().setText(hud.getTp().getText()+"\n"+per.getNombre()+" se equipa un arco");
			per.getInventario().put(per.getArmaEq().getNombre(),per.getArmaEq());
			per.setArmaEq(new Arma(7,2,true,3,20,"Arco"));
			per.getInventario().remove(op);
			break;
		case "Hacha de guerra":
			hud.getTp().setText(hud.getTp().getText()+"\n"+per.getNombre()+" se equipa un hacha de guerra");
			per.getInventario().put(per.getArmaEq().getNombre(),per.getArmaEq());
			per.setArmaEq(new Arma(10,3,false,3,25,"Hacha de guerra"));
			per.getInventario().remove(op);
			break;
		case "Espada corta":
			hud.getTp().setText(hud.getTp().getText()+"\n"+per.getNombre()+" se equipa una espada corta");
			per.getInventario().put(per.getArmaEq().getNombre(),per.getArmaEq());
			per.setArmaEq(new Arma(4,2,false,2,10,"Espada corta"));
			per.getInventario().remove(op);
			break;
			
		}
		
	}
	
	public JList inicializarLista(Personaje per){
		DefaultListModel<Objeto> listModel = new DefaultListModel<>();
		Iterator it=per.getInventario().values().iterator();
		while(it.hasNext()) {
            listModel.addElement((Objeto)it.next());
        }
		 return new JList(listModel);
	}
}
