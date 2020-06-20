package controladores;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import interfaces.InterfazInicioSesion;
import prEventually.ConexionConBaseDeDatos;
import prEventually.Sesion;
import pruebas.PRUEBATOTAL;

public class CntrlInicioSesion implements ActionListener {

	ConexionConBaseDeDatos conexionBD;
	InterfazInicioSesion iniPanel;
	static Sesion s;

	public CntrlInicioSesion(ConexionConBaseDeDatos connbd, InterfazInicioSesion ini) {
		conexionBD = connbd;
		iniPanel = ini;
	}

	public void popUpError(JPanel parent, String err) {
		JOptionPane.showMessageDialog(parent,
        	    err,
        	    "Error",
        	    JOptionPane.ERROR_MESSAGE);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String usuario = iniPanel.getUsuario().getText();
		String contrasena = iniPanel.getContra().getText();
		JPanel cardParent = (JPanel) iniPanel.getParent();
		
//		if(usuario==null || usuario.length()==0 || contrasena == null || contrasena.length()==0){
//			popUpError(cardParent, "Campos en blanco");
//			
//		}else
		
			if(conexionBD.iniciarSesion(usuario, contrasena) != null) {
				
				s = new Sesion(usuario, contrasena);
				
				CardLayout ccl = (CardLayout) (cardParent.getLayout());
				ccl.show(cardParent, PRUEBATOTAL.PANELPRINCIPAL);
			
				iniPanel.getUsuario().setText(null);
				iniPanel.getContra().setText(null);
				
				PRUEBATOTAL.setVentanaTamaño(800, 600);
				
		} else {
			popUpError(cardParent, "Usuario o contrasena incorrectos");
			
		}	
	}
	
	public static Sesion getSesion() {
		return s;
	}
	
	public static void setSesion(String nU, String pass) {
		s = new Sesion(nU, pass);
	}
}
