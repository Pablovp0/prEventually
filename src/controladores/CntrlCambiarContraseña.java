package controladores;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import interfaces.InterfazCambiarContraseña;
import prEventually.ConexionConBaseDeDatos;
import prEventually.Sesion;
import pruebas.PRUEBATOTAL;

public class CntrlCambiarContraseña implements ActionListener{
	
	ConexionConBaseDeDatos conexionBD;
	InterfazCambiarContraseña iCc;
	
	public CntrlCambiarContraseña(ConexionConBaseDeDatos connbd, InterfazCambiarContraseña i) {
		iCc = i;
		conexionBD = connbd;
	}
	
	public void actionPerformed(ActionEvent arg0) {
		
		Sesion s = CntrlInicioSesion.getSesion();
		String contrasenaAntigua = iCc.getContra().getText();
		String contrasenaNueva = iCc.getContraNueva().getText();
		JPanel cardParent = (JPanel) iCc.getParent();
		
		if(!contrasenaAntigua.equals(s.getContraseña())) {
			
			JOptionPane.showMessageDialog(cardParent,
	        	    "Contrasena incorrecta",
	        	    "Error",
	        	    JOptionPane.ERROR_MESSAGE);
			
		} else {
			
			conexionBD.actualizarContraseña(s.getNusuario(), contrasenaNueva);
			JOptionPane.showMessageDialog(cardParent,
	        	    "Su contraseña ha sido cambiada.",
	        	    "Contraseña actualizada",
	        	    JOptionPane.DEFAULT_OPTION);
			CardLayout ccl = (CardLayout) (cardParent.getLayout());
			ccl.show(cardParent, PRUEBATOTAL.PANELPRINCIPAL);
			iCc.getContra().setText(null);
			iCc.getContraNueva().setText(null);
			
			
		}
		
	}

}
