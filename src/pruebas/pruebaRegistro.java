package pruebas;

import javax.swing.JFrame;

import controladores.CntrlRegistroUsuario;
import prEventually.*;
import interfaces.*;

public class pruebaRegistro {

	public static void main(String[] args) {
		InterfazRegistroUsuario i = new InterfazRegistroUsuario();
		JFrame ventana = new JFrame("Eventually");

		ventana.setSize(200, 175);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setContentPane(i);
		ventana.pack();
		ventana.setVisible(true);

		ConexionConBaseDeDatos accesoBD;

		accesoBD = ConexionBaseDeDatosJDBC.getInstance();

		CntrlRegistroUsuario c = new CntrlRegistroUsuario(accesoBD, i);

		i.controlador(c);

	}

}
