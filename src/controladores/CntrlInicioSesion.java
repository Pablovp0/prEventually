package controladores;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import interfaces.InterfazInicioSesion;
import prEventually.ConexionConBaseDeDatos;
import prEventually.Usuario;
import pruebas.PRUEBATOTAL;

public class CntrlInicioSesion implements ActionListener {

	ConexionConBaseDeDatos conexionBD;
	InterfazInicioSesion iniPanel;

	public CntrlInicioSesion(ConexionConBaseDeDatos connbd, InterfazInicioSesion ini) {
		conexionBD = connbd;
		iniPanel = ini;
	}

	public void popUp(JPanel parent, String err) {
		JOptionPane.showMessageDialog(parent,
        	    err,
        	    "Error",
        	    JOptionPane.PLAIN_MESSAGE);
	}
	
	public void actionPerformed(ActionEvent e) {
		
		String usuario = iniPanel.getUsuario().getText();
		String contrasena = iniPanel.getContra().getText();
		JPanel cardParent = (JPanel) iniPanel.getParent();
		
//		if(usuario==null || usuario.length()==0 || contrasena == null || contrasena.length()==0){
//			popUpError(cardParent, "Campos en blanco");
//			
//		}else
			if(conexionBD.iniciarSesion(usuario, contrasena)) {
			CardLayout ccl = (CardLayout) (cardParent.getLayout());
			ccl.show(cardParent, PRUEBATOTAL.PANELPRINCIPAL);
			
			iniPanel.getUsuario().setText(null);
			iniPanel.getContra().setText(null);
		} else {
			popUp(cardParent, "Usuario o contrasena incorrecto");
			
		}	
	}
}
