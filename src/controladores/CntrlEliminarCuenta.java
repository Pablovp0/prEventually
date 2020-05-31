package controladores;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import interfaces.InterfazEliminarCuenta;
import prEventually.ConexionConBaseDeDatos;
import pruebas.PRUEBATOTAL;

public class CntrlEliminarCuenta implements ActionListener{
	
	ConexionConBaseDeDatos conexionBD;
	InterfazEliminarCuenta iEcPanel;

	public CntrlEliminarCuenta(ConexionConBaseDeDatos connbd, InterfazEliminarCuenta ec) {
		conexionBD = connbd;
		iEcPanel = ec;
	}

	public void actionPerformed(ActionEvent e) {
		
		String usuario = iEcPanel.getUsuario().getText();
		String contrasena = iEcPanel.getContra().getText();
		
		if(conexionBD.iniciarSesion(usuario, contrasena)) {
			
			conexionBD.eliminarCuenta(usuario);
			
			JPanel cardParent = (JPanel) iEcPanel.getParent();
			CardLayout ccl = (CardLayout) (cardParent.getLayout());
			ccl.show(cardParent, PRUEBATOTAL.SEGUNDOPANEL);
		}
		
	}
	
}
