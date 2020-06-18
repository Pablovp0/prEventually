package controladores;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import interfaces.InterfazListaEventos;
import interfaces.InterfazPrincipal;
import prEventually.ConexionConBaseDeDatos;
import prEventually.Evento;
import prEventually.Sesion;

public class CntrlEliminarEvento implements ActionListener{

	ConexionConBaseDeDatos conexionBD;
	InterfazPrincipal ipPanel;
	
	public CntrlEliminarEvento(ConexionConBaseDeDatos bd, InterfazPrincipal i) {
		conexionBD = bd;
		ipPanel = i;
	}
	
	public void actionPerformed(ActionEvent e) {
		InterfazListaEventos iLePanel = ipPanel.getPanelListaEventos();
		Sesion s = CntrlInicioSesion.getSesion();
		Evento eventoSeleccionado = (Evento) (iLePanel.getcbEventos().getSelectedItem());
		
		JPanel cardParent = (JPanel) ipPanel.getParent();
		
		if(s.getNusuario().equals(eventoSeleccionado.getOrganizador())) {
			
			conexionBD.eliminarEvento(eventoSeleccionado.getId());
			iLePanel.actualizarListaEventos();
			iLePanel.getcbEventos().removeAllItems();
			
			JOptionPane.showMessageDialog(cardParent,
	        	    "Has cancelado el evento seleccionado.",
	        	    "Evento eliminado",
	        	    JOptionPane.WARNING_MESSAGE);
			
		} else {
			JOptionPane.showMessageDialog(cardParent,
	        	    "No puedes eliminar este evento porque no eres el organizador.",
	        	    "Error",
	        	    JOptionPane.ERROR_MESSAGE);
			
		}
		
	}
	
	
	
}
