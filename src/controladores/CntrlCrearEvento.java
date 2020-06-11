package controladores;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import interfaces.InterfazCrearEvento;
import interfaces.InterfazRegistroUsuario;
import prEventually.ConexionConBaseDeDatos;
import prEventually.Evento;
import prEventually.Usuario;
import pruebas.PRUEBATOTAL;

public class CntrlCrearEvento implements ActionListener{
	
	ConexionConBaseDeDatos conexionBD;
	InterfazCrearEvento cePanel;
	
	public CntrlCrearEvento(ConexionConBaseDeDatos connbd, InterfazCrearEvento ce) {
		conexionBD = connbd;
		cePanel = ce;
	}
	
	public void popUp(JPanel parent, String err) {
		JOptionPane.showMessageDialog(parent,
        	    err,
        	    "Mensaje",
        	    JOptionPane.PLAIN_MESSAGE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Evento ev = new Evento(0,
				cePanel.getNombre().getText(),
				cePanel.getFecha().getText(),
				cePanel.getLugar().getText(),
				CntrlInicioSesion.getSesion().getNusuario());
		
		if(ev.getFecha() == null || ev.getFecha().length()==0 ||
				ev.getLugar() == null || ev.getLugar().length()==0 ||
				ev.getNombre()==null || ev.getNombre().length()==0) {
			popUp(cePanel, "Campo vacio");
		}
		else {
			int eventoID = conexionBD.crearEvento(ev);
			ev.setId(eventoID);
			popUp(cePanel, "Evento creado");
			cePanel.getNombre().setText(null);
			cePanel.getFecha().setText(null);
			cePanel.getLugar().setText(null);
			
			//mismo efecto que volver
			JPanel cardParent = (JPanel) cePanel.getParent();
			CardLayout ccl = (CardLayout) (cardParent.getLayout());
			ccl.show(cardParent, PRUEBATOTAL.PANELPRINCIPAL);
		}
		
	}
	
	
	
}
