package controladores;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import interfaces.InterfazRegistroUsuario;
import prEventually.*;
import pruebas.PRUEBATOTAL;




	public class CntrlRegistroUsuario implements ActionListener{
	
	ConexionConBaseDeDatos conexionBD;
	InterfazRegistroUsuario nuPanel;
	
	public CntrlRegistroUsuario(ConexionConBaseDeDatos connbd, InterfazRegistroUsuario nu) {
		conexionBD = connbd;
		nuPanel = nu;
	}

	public void popUpError(JPanel parent, String err) {
		JOptionPane.showMessageDialog(parent,
        	    err,
        	    "Error",
        	    JOptionPane.PLAIN_MESSAGE);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e){
		Usuario u = new Usuario(0,
				nuPanel.getUser().getText(),
				nuPanel.getMail().getText(), 
				nuPanel.getPassword().getText());
		JPanel cardParent = (JPanel) nuPanel.getParent();
		
		if(u.getUser()== null || u.getUser().length()==0 ||
				u.getPassword()==null || u.getPassword().length()==0 ||
				u.getMail()==null || u.getMail().length()==0) {
			popUpError(cardParent, "Campos en blanco");
			
		}else {
			int usuarioID = conexionBD.registrarNuevoUsuario(u);
			u.setIdentificador(usuarioID);
			popUpError(cardParent, "Nuevo usuario registrado");
			nuPanel.getUser().setText(null);
			nuPanel.getMail().setText(null);
			nuPanel.getPassword().setText(null);
			CardLayout ccl = (CardLayout) (cardParent.getLayout());
			ccl.show(cardParent, PRUEBATOTAL.PANELINICIOSESION);
		}

	}
	
	
	
}