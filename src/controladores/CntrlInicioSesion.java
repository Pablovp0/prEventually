package controladores;

import interfaces.InterfazInicioSesion;
import prEventually.ConexionConBaseDeDatos;
import prEventually.Usuario;
import pruebas.PRUEBATOTAL;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class CntrlInicioSesion implements ActionListener {

	ConexionConBaseDeDatos conexionBD;
	InterfazInicioSesion iniPanel;

	public CntrlInicioSesion(ConexionConBaseDeDatos connbd, InterfazInicioSesion ini) {
		conexionBD = connbd;
		iniPanel = ini;
	}

	public void actionPerformed(ActionEvent e) {
		
		String usuario = iniPanel.getUsuario().getText();
		String contrasena = iniPanel.getContra().getText();
		
		if(conexionBD.iniciarSesion(usuario, contrasena)) {
			
			
			JPanel cardParent = (JPanel) iniPanel.getParent();
			CardLayout ccl = (CardLayout) (cardParent.getLayout());
			ccl.show(cardParent, PRUEBATOTAL.PANELPRINCIPAL);
		}
		
	}

}
