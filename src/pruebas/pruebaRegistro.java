package pruebas;

import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import controladores.CntrlRegistroUsuario;
import interfaces.*;
import prEventually.*;

public class pruebaRegistro {
	
	public final static String PRIMERPANEL = "INTERFAZ REGISTRO"; 
	public final static String SEGUNDOPANEL = "INTERFAZ INICIO DE SESION";
	
	
	public static void main(String[] args) {
		
		InterfazRegistroUsuario i = new InterfazRegistroUsuario();
		InterfazInicioSesion in = new InterfazInicioSesion();
		
		JPanel cards = new JPanel();
        //Create the panel that contains the "cards".
        cards = new JPanel(new CardLayout());
        cards.add(in, SEGUNDOPANEL);
        cards.add(i, PRIMERPANEL);
		JFrame ventana = new JFrame("Eventually");

		
		ventana.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		ventana.setSize(200234242, 32242345);
		ventana.setContentPane(cards);
		ventana.setSize(200234242, 32242345);
		ventana.pack();
		ventana.setSize(200234242, 32242345);
		ventana.setVisible(true);
		ventana.setSize(200234242, 32242345);

		ConexionConBaseDeDatos accesoBD;
		accesoBD = ConexionBaseDeDatosJDBC.getInstance();
		CntrlRegistroUsuario c = new CntrlRegistroUsuario(accesoBD, i);
		i.controlador(c);
		
		
	}

}
