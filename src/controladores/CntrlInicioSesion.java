package controladores;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import interfaces.InterfazInicioSesion;
import interfaces.InterfazPrincipal;
import prEventually.ConexionConBaseDeDatos;
import prEventually.Sesion;
import pruebas.PRUEBATOTAL;

public class CntrlInicioSesion implements ActionListener {

	ConexionConBaseDeDatos conexionBD;
	InterfazInicioSesion iniPanel;
	InterfazPrincipal ip;
	static Sesion s;

	public CntrlInicioSesion(ConexionConBaseDeDatos connbd, InterfazInicioSesion ini, InterfazPrincipal i) {
		conexionBD = connbd;
		iniPanel = ini;
		ip = i;
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
				
				PRUEBATOTAL.setVentanaTamaño(980, 600);
				
				ip.getLbNUsuario().setText(s.getNusuario());
				ip.getLbMail().setText(conexionBD.getMailUsuario(s.getNusuario()));
				
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
