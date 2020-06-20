package controladores;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import interfaces.InterfazCrearEvento;
import interfaces.InterfazListaEventos;
import interfaces.InterfazPrincipal;
import prEventually.ConexionConBaseDeDatos;
import prEventually.Evento;
import pruebas.PRUEBATOTAL;

public class CntrlCrearEvento implements ActionListener{
	
	ConexionConBaseDeDatos conexionBD;
	InterfazCrearEvento cePanel;
	InterfazPrincipal ip;
	
	public CntrlCrearEvento(ConexionConBaseDeDatos connbd, InterfazCrearEvento ce, InterfazPrincipal i) {
		conexionBD = connbd;
		cePanel = ce;
		ip=i;
	}
	public void popUpError(JPanel parent, String err) {
		JOptionPane.showMessageDialog(parent,
        	    err,
        	    "Mensaje",
        	    JOptionPane.ERROR_MESSAGE);
	}
	public void popUp(JPanel parent, String msj) {
		JOptionPane.showMessageDialog(parent,
        	    msj,
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
		
		InterfazListaEventos iLePanel = ip.getPanelListaEventos();
		
		if(ev.getFecha() == null || ev.getFecha().length()==0 ||
				ev.getLugar() == null || ev.getLugar().length()==0 ||
				ev.getNombre()==null || ev.getNombre().length()==0) {
			popUpError(cePanel, "Campo vacio");
		}else if(conexionBD.existeEvento(ev.getNombre())){
			popUpError(cePanel, "Ya existe un evento con ese nombre");
		}else {
			
			int eventoID = conexionBD.crearEvento(ev);
			ev.setId(eventoID);
			popUp(cePanel, "Evento creado");
			cePanel.getNombre().setText(null);
			cePanel.getFecha().setText(null);
			cePanel.getLugar().setText(null);
			
			iLePanel.actualizarListaEventos();
			
			//mismo efecto que volver
			JPanel cardParent = (JPanel) cePanel.getParent();
			CardLayout ccl = (CardLayout) (cardParent.getLayout());
			ccl.show(cardParent, PRUEBATOTAL.PANELPRINCIPAL);
			PRUEBATOTAL.setVentanaTamaño(800, 600);
		}
		
	}
	
	
	
}
