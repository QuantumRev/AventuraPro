package interfaz;
import javax.swing.JPanel;

import clases.EscenarioTemp;
import clases.Personaje;

import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Inicio extends JPanel{
	private Connection con;
	public Inicio(Ventana ven) {
		this.setSize(400,300);
		setLayout(null);
		
		JButton btnJuegoNuevo = new JButton("Nuevo Juego");
		btnJuegoNuevo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				ven.irAHud();
			}
		});
		btnJuegoNuevo.setBounds(75, 50, 250, 43);
		add(btnJuegoNuevo);
		
		JButton btnCargarJuego = new JButton("Cargar Juego");
		btnCargarJuego.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				//FileInputStream file;
				try {
					con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/AventuraPro?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","aventurapro","123");
					/*file = new FileInputStream ("./savedata/pepe.txt");
					ObjectInputStream in = new ObjectInputStream (file);*/
					
					Statement smt = con.createStatement();
					
					ResultSet rs=smt.executeQuery("select FILENAME from PARTIDAS_GUARDADAS;");
					rs.next();
					Blob b=rs.getBlob("FILENAME");
					
					System.out.println(b.getBinaryStream());
					ObjectInputStream in = new ObjectInputStream (b.getBinaryStream());
					System.out.println("Hola");
					Personaje per = (Personaje)in.readObject();
					EscenarioTemp esc = (EscenarioTemp)in.readObject(); 
					
		            in.close(); 
		            //file.close(); 
		            ven.cargaJuego(esc, per);

					//Thread t = new Thread(jug);
		         //   t.start();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnCargarJuego.setBounds(75, 115, 250, 43);
		add(btnCargarJuego);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ven.dispose();
			}
		});
		btnSalir.setBounds(270, 240, 89, 23);
		add(btnSalir);
	}
}
