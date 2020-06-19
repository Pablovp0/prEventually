package controladores;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import interfaces.InterfazCambiarContrase�a;
import prEventually.ConexionConBaseDeDatos;
import prEventually.Sesion;
import pruebas.PRUEBATOTAL;

public class CntrlCambiarContrase�a implements ActionListener{
	
	ConexionConBaseDeDatos conexionBD;
	InterfazCambiarContrase�a iCc;
	
	public CntrlCambiarContrase�a(ConexionConBaseDeDatos connbd, InterfazCambiarContrase�a i) {
		iCc = i;
		conexionBD = connbd;
	}
	
	public void actionPerformed(ActionEvent arg0) {
		
		Sesion s = CntrlInicioSesion.getSesion();
		String contrasenaAntigua = iCc.getContra().getText();
		String contrasenaNueva = iCc.getContraNueva().getText();
		JPanel cardParent = (JPanel) iCc.getParent();
		
		if(!contrasenaAntigua.equals(s.getContrase�a())) {
			
			JOptionPane.showMessageDialog(cardParent,
	        	    "Contrasena incorrecta",
	        	    "Error",
	        	    JOptionPane.ERROR_MESSAGE);
			
		} else {
			
			conexionBD.actualizarContrase�a(s.getNusuario(), contrasenaNueva);
			JOptionPane.showMessageDialog(cardParent,
	        	    "Su contrase�a ha sido cambiada.",
	        	    "Contrase�a actualizada",
	        	    JOptionPane.DEFAULT_OPTION);
			CardLayout ccl = (CardLayout) (cardParent.getLayout());
			ccl.show(cardParent, PRUEBATOTAL.PANELPRINCIPAL);
			iCc.getContra().setText(null);
			iCc.getContraNueva().setText(null);
			
			
		}
		
	}

}
