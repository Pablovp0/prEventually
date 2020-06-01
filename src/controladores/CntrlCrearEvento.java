package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import interfaces.InterfazCrearEvento;
import interfaces.InterfazRegistroUsuario;
import prEventually.ConexionConBaseDeDatos;
import prEventually.Evento;
import prEventually.Usuario;

public class CntrlCrearEvento implements ActionListener{
	
	ConexionConBaseDeDatos conexionBD;
	InterfazCrearEvento cePanel;
	
	public CntrlCrearEvento(ConexionConBaseDeDatos connbd, InterfazCrearEvento ce) {
		conexionBD = connbd;
		cePanel = ce;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Evento ev = new Evento(0,
				cePanel.getNombre().getText(),
				cePanel.getFecha().getText(),
				cePanel.getLugar().getText(),
				cePanel.getOrganizador().getText());
		
		int eventoID = conexionBD.crearEvento(ev);
		ev.setId(eventoID);
		System.out.println("nuevo evento añadido");
		
	}
	
	
	
}
