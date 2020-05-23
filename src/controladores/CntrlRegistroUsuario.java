package controladores;

import prEventually.*;
import interfaces.InterfazRegistroUsuario;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class CntrlRegistroUsuario implements ActionListener{
	
	ConexionConBaseDeDatos conexionBD;
	InterfazRegistroUsuario nuPanel;
	
	public CntrlRegistroUsuario(ConexionConBaseDeDatos connbd, InterfazRegistroUsuario nu) {
		conexionBD = connbd;
		nuPanel = nu;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Usuario u = new Usuario(0,
				nuPanel.getUser().getText(),
				nuPanel.getMail().getText(), 
				nuPanel.getPassword().getText());
		
		int usuarioID = conexionBD.registrarNuevoUsuario(u);
		u.setIdentificador(usuarioID);
		System.out.println("nuevo usuario añadido");
		
	}
	
}