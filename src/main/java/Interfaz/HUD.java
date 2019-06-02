package interfaz;

import javax.swing.JPanel;
import javax.swing.JTextPane;


import clases.Arma;
import clases.Casilla;
import clases.Elemento;
import clases.EscenarioTemp;
import clases.Objeto;
import clases.Personaje;
import clases.Tienda;
import enemigos.Enemigo;
import enemigos.Goblin;
import enemigos.Ladron;
import enemigos.SoldadoSerpiente;
import javazoom.jl.decoder.JavaLayerException;
import sonido.MusicPlayer;

import java.awt.Color;

import javax.sql.rowset.serial.SerialBlob;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HUD extends JPanel{
	private Personaje per;
	private EscenarioTemp escenario;
	private JPanel lblPanel;
	private JTextPane tp;
	private Enemigo enActual;
	private int[] posEn;
	public JButton up,down,left,right,atacar,huir,New_button;
	public JButton btnSalir = new JButton("Salir");
	public JButton btnGuardar = new JButton("Guardar");
	private JFrame venInv;
	private Ventana ventana;
	private MusicPlayer plains,TOTG;
	private Thread backGroundMusic;
	private Thread combatMusic;
	private Ladron lad;
	private HUD hud=this;
	private Connection con;
    
	
	/*public void updateMap() {
		for(int i=0;i<escenario.getCasillas().length;i++) {
			for(int j=0;j<escenario.getCasillas()[0].length;j++) {
				escenario.getCasillas()[i][j].setText("        "+escenario.getCasillas()[i][j].getVisual());
			}
		}
	}*/
	public JTextPane getTp() {
		return this.tp;
	}
	public EscenarioTemp getEscenario() {
		return this.escenario;
	}
	public int[] getPosEn() {
		return this.posEn;
	}
	public MusicPlayer getTOTG() {
		return this.TOTG;
	}
	public MusicPlayer getPlains() {
		return this.plains;
	}
	public Thread getBackGroundMusic() {
		return this.backGroundMusic;
	}
	public void setBackGroundMusic(MusicPlayer player) {
		this.backGroundMusic=new Thread(player);
	}
	public void mostrarTexto(String texto) {
		this.tp.setText(tp.getText()+"\n"+texto);
	}
	public HUD(Ventana v,EscenarioTemp esc,Personaje personaje) {
		this.per=personaje;
		this.escenario=esc;
		this.ventana=v;
		ventana.setSize(650,500);
		for(int i=0;i<escenario.getCasillas().length;i++) {
			for(int j=0;j<escenario.getCasillas()[0].length;j++) {
				if(escenario.getCasillas()[i][j].getEl()!=null){
					if(escenario.getCasillas()[i][j].getEl().getClass().getName().equals("enemigos.Ladron")) {
						System.out.println( "Lad:  "+escenario.getCasillas()[i][j].getEl().getClass().getName());
						lad=(Ladron)escenario.getCasillas()[i][j].getEl();
					}
				}
				
			}
		}
		 try {
				plains=new MusicPlayer("plains");
				backGroundMusic= new Thread(plains);
				backGroundMusic.start();
				
			} catch (JavaLayerException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		setBackground(Color.GRAY);
		setSize(650,500);
		setLayout(null);
		
		tp = new JTextPane();
		JScrollPane sp = new JScrollPane(tp,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		sp.setBounds(25, 25, 290, 229);
		add(sp);
		
		lblPanel = new JPanel();
		lblPanel.setBounds(325, 25, 300, 300);
		add(lblPanel);
		lblPanel.setLayout(new GridLayout(5, 5, 0, 0));
		for(int i=0;i<escenario.getCasillas().length;i++) {
			for(int j=0;j<escenario.getCasillas()[0].length;j++) {
				lblPanel.add(escenario.getCasillas()[i][j]);
			}
		}
		
		atacar = new JButton("Atacar");
		atacar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(enActual==null) {//Comprueba que exista un enemigo al cual atacar en una casilla adyacente
					tp.setText(tp.getText()+"\n"+per.getNombre()+" ataca al aire con todas sus fuerzas!..\nPero nada paso...");
				}else {//Comprueba si es...
					if(enActual.getClass().getName().equals("enemigos.Ladron")) {//...un ladron...
						
						Ladron lad=(Ladron)enActual;
						tp.setText(tp.getText()+per.atacar(enActual));
						
						if(enActual.getHp()>0){//Si esta vivo...
							if(lad.getOroRobado()==0) {//Roba oro si todavia no ha robado
								tp.setText(tp.getText()+"\n"+lad.robarOro(per)+"\n");
							}else {//Si ya ha robado, entonces intenta huir
								int rnd=(int) (Math.random()*100);
								tp.setText(tp.getText()+"\n"+lad.getNombre()+" intenta huir!\n");
								if (rnd<=50) {//50% de probabilidad de huir...
									tp.setText(tp.getText()+"\n"+lad.getNombre()+" ha huido con "+lad.getOroRobado()+" de oro...\n");
									escenario.getCasillas()[posEn[0]][posEn[1]].setEl(null);
					            	enActual=null;
					            	lad=null;
					            	TOTG.stop();
					            	backGroundMusic=new Thread(plains);
					            	backGroundMusic.start();
					            	per.setCombate(false);
								
								}else {//Si no, falla
									tp.setText(tp.getText()+"\n"+lad.getNombre()+" no pudo huir!\n");
								}
							}
							
			            }else {//Si el ladron es derrotado antes de que escape, se recupera el oro
			            	
			            	tp.setText(tp.getText()+"\n"+enActual.getNombre()+" ha sido derrotado!");
			            	per.setOro(per.getOro()+lad.getOroRobado());
			            	tp.setText(tp.getText()+"\n"+per.getNombre()+" ha recuperado "+lad.getOroRobado()+" de oro!");
			            	escenario.getCasillas()[posEn[0]][posEn[1]].setEl(null);
			            	enActual=null;
			            	lad=null;
			            	per.setCombate(false);
			            	TOTG.stop();
			            	backGroundMusic=new Thread(plains);
			            	backGroundMusic.start();
			            }
						
					}else {//...o un enemigo
						if(enActual.isRango()&&!per.getArmaEq().isRango()) {//Comprueba si el enemigo requiere un arma de rango y si el personaje tiene una equipada
							tp.setText(tp.getText()+"\n"+enActual.getNombre()+" solo puede ser atacado con un arma de rango!");
							tp.setText(tp.getText()+"\n"+enActual.atacar(per)+"\n");
						}
						else {//El personaje ataca
							tp.setText(tp.getText()+per.atacar(enActual));
							if(enActual.getHp()>0){//Si el nemigo vive, ataca al jugador
								tp.setText(tp.getText()+"\n");
								tp.setText(tp.getText()+enActual.atacar(per)+"\n");
				            }else {//Si muere, se elimina de la casilla
				            	
				            	tp.setText(tp.getText()+"\n"+enActual.getNombre()+" ha sido derrotado!");
				            	escenario.getCasillas()[posEn[0]][posEn[1]].setEl(null);
				            	enActual=null;
				            	per.setCombate(false);
				            	TOTG.stop();
				            	backGroundMusic=new Thread(plains);
				            	backGroundMusic.start();
				            }
						}
					}
					
				}
				if(per.getHpActual()<=0) {//Comprueba si el jugador vive
					tp.setText(tp.getText()+"\n"+per.getNombre()+" ha muerto!");
					JOptionPane.showMessageDialog(ventana,"RIP");
					ventana.irAInicio();
				}
				
			}
		});
		
		atacar.setBounds(25, 269, 89, 23);
		add(atacar);
		
		New_button = new JButton("New button");
		New_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		New_button.setBounds(25, 303, 89, 23);
		add(New_button);
		
		huir = new JButton("Huir");
		huir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!per.isCombate()) {
					tp.setText(tp.getText()+"\n"+per.getNombre()+" intenta escapar!...\n ...pero lo unico que lo persigue es el peso de sus desiciones...");
				}else {
					int rnd=(int) (Math.random()*100);
					tp.setText(tp.getText()+"\n"+per.getNombre()+" intenta escapar!...");
					if(rnd<=50) {
						per.setCombate(false);
						TOTG.stop();
						tp.setText(tp.getText()+"\n"+per.getNombre()+" ha huido!");
						backGroundMusic=new Thread(plains);
						backGroundMusic.start();
					}else {
						tp.setText(tp.getText()+"\n"+per.getNombre()+" no ha podido huir!");
						tp.setText(tp.getText()+enActual.atacar(per)+"\n");
					}
				}
				if(per.getHpActual()<=0) {//Comprueba si el jugador vive
					tp.setText(tp.getText()+"\n"+per.getNombre()+" ha muerto!");
					JOptionPane.showMessageDialog(ventana,"RIP");
					ventana.irAInicio();
				}
			}
		});
		huir.setBounds(226, 269, 89, 23);
		add(huir);
		
		JButton btnNewButton_3 = new JButton("Inventario");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!per.isCombate()) {
					Inventario inv = new Inventario(per,hud);
					inv.setVisible(true);
				}else {
					Inventario inv = new Inventario(per,hud,enActual);
					inv.setVisible(true);
				}
			}
		});
		btnNewButton_3.setBounds(124, 269, 89, 23);
		add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("New button");
		btnNewButton_4.setBounds(125, 303, 89, 23);
		add(btnNewButton_4);
		
		left = new JButton("<");
		left.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (per.isCombate()){
					tp.setText(tp.getText()+per.getNombre()+" no puede huir, esta en combate!\n");
				}
				else {
					interactuar(-1,0);
					/*if(lad!=null) {
						movLadron(1,0,lad);
					}*/
					
				}
				
			}
		});
		left.setBounds(25, 385, 89, 23);
		add(left);
		
		JButton btnNewButton_6 = new JButton("New button");
		btnNewButton_6.setBounds(226, 303, 89, 23);
		add(btnNewButton_6);
		
		right = new JButton(">");
		right.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (per.isCombate()){
					tp.setText(tp.getText()+per.getNombre()+" no puede huir, esta en combate!\n");
				}
				else {
				interactuar(1,0);
				/*if(lad!=null) {
					movLadron(-1,0,lad);
				}*/
				
				}
			}
		});
		
		right.setBounds(226, 385, 89, 23);
		add(right);
		
		up = new JButton("^");
		up.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (per.isCombate()){
					tp.setText(tp.getText()+per.getNombre()+" no puede huir, esta en combate!\n");
				}
				else {
				interactuar(0,-1);
				/*if(lad!=null) {
					movLadron(0,1,lad);
				}*/
				
				}
			}
		});
		up.setBounds(125, 351, 89, 23);
		add(up);
		
		down = new JButton("v");
		down.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (per.isCombate()){
					tp.setText(tp.getText()+per.getNombre()+" no puede huir, esta en combate!\n");
				}
				else {
				interactuar(0,1);
				/*if(lad!=null) {
					movLadron(0,-1,lad);
				}*/
				
				}
			}
		});
		down.setBounds(125, 416, 89, 23);
		add(down);
		
		btnSalir.setBounds(540, 430, 85, 23);
		add(btnSalir);
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(per.isCombate()) {
					JOptionPane.showMessageDialog(ventana,
						    "No se puede guardar la partida mientras estas en combate!",
						    "Aviso!",
						    JOptionPane.WARNING_MESSAGE);
				}else {
					FileOutputStream file;
					try {
						file = new FileOutputStream ("./savedata/"+per.getNombre()+".txt");
						ObjectOutputStream out = new ObjectOutputStream (file);
						out.writeObject(per); 
						out.writeObject(esc); 
			            out.close(); 
			            file.close(); 
			            guardarDB("./savedata/"+per.getNombre()+".txt");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
				}
				
			}
		});
		
		btnGuardar.setBounds(441, 430, 89, 23);
		add(btnGuardar);
		
		
		
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(per.isCombate()) {
					TOTG.stop();
				}
				if(venInv!=null) {
					venInv.dispose();
				}
				plains.stop();
				v.irAInicio();
			}
		});
		
		
	}
	
	public void interactuar(int dirx,int diry) {
		int[] voyA=per.getPosPer().clone();
		if (((voyA[0]==0)&&(diry==-1))||((voyA[0]==escenario.casillas.length-1)&&(diry==+1)) || ((voyA[1]==0)&&(dirx==-1))||((voyA[1]==escenario.casillas.length-1)&&(dirx==+1))){
			
		}else {
			voyA[0]=voyA[0]+diry;
			voyA[1]=voyA[1]+dirx;
				
			Elemento elementoDondeVoy=escenario.getCasillas()[voyA[0]][voyA[1]].getEl();
			if(elementoDondeVoy!=null) {
				System.out.println(elementoDondeVoy.getClass().getName() +"  "+elementoDondeVoy.toString());
				
				if(elementoDondeVoy.getClass().getName().equals("enemigos.Goblin")) {
					//No avanzar casilla, sino permitir atacarle
					per.setCombate(true);
					this.enActual =(Goblin)elementoDondeVoy;
					try {
						plains.stop();
						TOTG=new MusicPlayer("TOTG");
						combatMusic= new Thread (TOTG);
						combatMusic.start();
					} catch (JavaLayerException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					tp.setText(tp.getText()+"\n Un "+enActual.getNombre()+" ataca!\n");
					posEn=voyA;
				}
				else if(elementoDondeVoy.getClass().getName().equals("enemigos.SoldadoSerpiente")) {
					per.setCombate(true);
					this.enActual =(SoldadoSerpiente)elementoDondeVoy;
					try {
						plains.stop();
						TOTG=new MusicPlayer("TOTG");
						combatMusic= new Thread (TOTG);;
						combatMusic.start();
					} catch (JavaLayerException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					tp.setText(tp.getText()+"\n Un "+enActual.getNombre()+" ataca!\n");
					posEn=voyA;
				}
				else if(elementoDondeVoy.getClass().getName().equals("enemigos.Ladron")) {
					per.setCombate(true);
					try {
						plains.stop();
						TOTG=new MusicPlayer("TOTG");
						combatMusic= new Thread (TOTG);
						combatMusic.start();
					} catch (JavaLayerException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					this.enActual= (Ladron) elementoDondeVoy;
					tp.setText(tp.getText()+"\n Un "+enActual.getNombre()+" ataca!\n");
					posEn=voyA;
				}
				else if(elementoDondeVoy.getClass().getName().equals("clases.Objeto")) {
					if(elementoDondeVoy.toString().equals("$")) {
						per.setOro(per.getOro()+10);
						tp.setText(tp.getText()+"\n"+per.getNombre()+" ha obtenido 10 de oro!");
						escenario.getCasillas()[voyA[0]][voyA[1]].setEl(null);
					}
					
				}
				else if(elementoDondeVoy.getClass().getName().equals("clases.Arma")) {
					if ((elementoDondeVoy.toString().equals("Arco"))) {
						per.getInventario().put("Arco",new Arma(7,2,true,3,20,"Arco"));
						tp.setText(tp.getText()+"\n"+per.getNombre()+" ha obtenido un arco!");
						escenario.getCasillas()[voyA[0]][voyA[1]].setEl(null);
					}
				}
				else if(elementoDondeVoy.getClass().getName().equals("clases.Tienda")) {
					Tienda td = (Tienda) elementoDondeVoy;		
					tp.setText(tp.getText()+td.vender(per,ventana));
				}
				if(per.getHpActual()<=0) {
					tp.setText(tp.getText()+"\n"+per.getNombre()+" ha muerto!");
					JOptionPane.showMessageDialog(ventana,"RIP");
					ventana.irAInicio();
				}
			}else { //Si lo que hay es nulo solo te mueves
				per.setPosPer(voyA);
				escenario.getCasillas()[voyA[0]][voyA[1]].setEl(escenario.getCasillas()[voyA[0]-diry][voyA[1]-dirx].getEl());
				escenario.getCasillas()[voyA[0]-diry][voyA[1]-dirx].setEl(null);
			}
		}
		
		
	
	
	lblPanel.repaint();
	ventana.repaint();
}
	public void movLadron(int dirx,int diry,Ladron lad) {;
		int rnd=(int) (Math.random()*100);
		if(rnd<=60) {
			
			int[] voyA=lad.getPosLad().clone();
			if (((voyA[0]==0)&&(diry==-1))||((voyA[0]==escenario.casillas.length-1)&&(diry==+1)) || ((voyA[1]==0)&&(dirx==-1))||((voyA[1]==escenario.casillas.length-1)&&(dirx==+1))){
				
			}else {
				voyA[0]=voyA[0]+diry;
				voyA[1]=voyA[1]+dirx;
					
				Elemento elementoDondeVoy=escenario.getCasillas()[voyA[0]][voyA[1]].getEl();
				if(elementoDondeVoy!=null) {
					System.out.println(elementoDondeVoy.getClass().getName() +"Lad2:  "+elementoDondeVoy.toString());
					
					if(elementoDondeVoy.getClass().getName().equals("clases.Personaje")) {
						//No avanzar casilla, sino permitir atacarle
						if(per.isCombate()==false) {
							try {
								plains.stop();
								combatMusic= new Thread (new MusicPlayer("TOTG"));
								combatMusic.start();
							} catch (JavaLayerException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							tp.setText(tp.getText()+"\n Un "+enActual.getNombre()+" ataca!\n");
							per.setCombate(true);
							
						}
						
						this.enActual =(Ladron)lad;
						posEn[0]=voyA[0]-diry;
						posEn[1]=voyA[1]-dirx;
					}
					
					
				}else { //Si lo que hay es nulo solo te mueves
					lad.setPosLad(voyA);
					escenario.getCasillas()[voyA[0]][voyA[1]].setEl(escenario.getCasillas()[voyA[0]-diry][voyA[1]-dirx].getEl());
					escenario.getCasillas()[voyA[0]-diry][voyA[1]-dirx].setEl(null);
				}
			}
		}

	lblPanel.repaint();
	ventana.repaint();
}
public String checkElemento(Elemento el) {
	if (el.getClass().getName().equals("enemigos.Goblin")) {
		return "G";
	}
	else if (el.getClass().getName().equals("enemigos.SoldadoSerpiente")) {
		return "S";
	}
	else if (el.getClass().getName().equals("enemigos.Ladron")) {
		return "L";
	}
	else if (el.getClass().getName().equals("clases.Tienda")) {
		return "T";
	}
	else if (el.getClass().getName().equals("clases.Objeto")) {
		return "$";
	}
	else if (el.getClass().getName().equals("clases.Arma")) {
		return "A";
	}
	return "";
}
public static String convertFileBlob(String filePath) throws IOException {
	
	File file = new File(filePath);
	
	String fileContent ="";
	FileInputStream inputStream = null;
	try {
		
		inputStream = new FileInputStream(file);
		int intActual=0;
		do {
			intActual=inputStream.read();
			if(intActual!=-1) {
				fileContent+=(char)inputStream.read();
			}
			//System.out.println(intActual);
		}while(intActual!=-1);
		
		
	} catch (IOException e) {
		throw new IOException("Unable to convert file to byte array. " + e.getMessage());
	} finally {
		
		if (inputStream != null) {
			inputStream.close();
		}
	}
	return fileContent;
}
public void guardarDB(String filepath) {
	 try {
		con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/AventuraPro?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","aventurapro","123");
		PreparedStatement smt= con.prepareStatement("insert into PARTIDAS_GUARDADAS values( ? , ?)");
		smt.setString(1, "Pepe");
		smt.setBlob(2, new SerialBlob(convertFileBlob(filepath).getBytes()));
		smt.executeUpdate();
		/*ResultSet rs;
		Blob b=rs.getBlob("FILENAME");
		ObjectInputStream in = new ObjectInputStream (b.getBinaryStream());
		*/
		con.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

}
	
	
	
}
