package pruebas;

import javax.swing.JFrame;

import prEventually.*;
import interfaces.*;

public class pruebaRegistro {

	public static void main(String[] args) {
		
		JFrame ventana = new JFrame("Eventually");
		
		ventana.setSize(200, 175);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setContentPane(new InterfazRegistroUsuario());
		ventana.pack(); 
		ventana.setVisible(true);
		
	}

}
