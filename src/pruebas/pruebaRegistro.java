package pruebas;

import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JPanel;

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

		ventana.setSize(200, 175);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setContentPane(cards);
		ventana.pack();
		ventana.setVisible(true);

		ConexionConBaseDeDatos accesoBD;
		accesoBD = ConexionBaseDeDatosJDBC.getInstance();
		CntrlRegistroUsuario c = new CntrlRegistroUsuario(accesoBD, i);
		i.controlador(c);
		
		
		
	}

}
