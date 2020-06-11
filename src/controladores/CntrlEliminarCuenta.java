package controladores;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import interfaces.InterfazEliminarCuenta;
import prEventually.ConexionConBaseDeDatos;
import prEventually.Sesion;
import pruebas.PRUEBATOTAL;

public class CntrlEliminarCuenta implements ActionListener{
	
	ConexionConBaseDeDatos conexionBD;
	InterfazEliminarCuenta iEcPanel;

	public CntrlEliminarCuenta(ConexionConBaseDeDatos connbd, InterfazEliminarCuenta ec) {
		conexionBD = connbd;
		iEcPanel = ec;
	}

	public void actionPerformed(ActionEvent e) {
		
		Sesion s = CntrlInicioSesion.getSesion();
		String contrasenaEscrita = iEcPanel.getContra().getText();
		JPanel cardParent = (JPanel) iEcPanel.getParent();
		
		if(contrasenaEscrita.equals(s.getContraseña())) {
			
			conexionBD.eliminarCuenta(s.getNusuario());
			CardLayout ccl = (CardLayout) (cardParent.getLayout());
			ccl.show(cardParent, PRUEBATOTAL.PANELINICIOSESION);
			
		} else {
			JOptionPane.showMessageDialog(cardParent,
	        	    "Contrasena incorrecta",
	        	    "Error",
	        	    JOptionPane.PLAIN_MESSAGE);
			
		}
		
	}
	
}
