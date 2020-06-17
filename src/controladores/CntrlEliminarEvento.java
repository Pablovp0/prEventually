package controladores;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import interfaces.InterfazEliminarCuenta;
import interfaces.InterfazListaEventos;
import interfaces.InterfazPrincipal;
import prEventually.ConexionConBaseDeDatos;
import prEventually.Evento;
import prEventually.Sesion;
import pruebas.PRUEBATOTAL;

public class CntrlEliminarEvento implements ActionListener{

	ConexionConBaseDeDatos conexionBD;
	InterfazListaEventos iLePanel;
	InterfazPrincipal ipPanel;
	
	public CntrlEliminarEvento(ConexionConBaseDeDatos bd, InterfazListaEventos i) {
		conexionBD = bd;
		iLePanel = i;
	}
	
	public void actionPerformed(ActionEvent e) {
		Sesion s = CntrlInicioSesion.getSesion();
		Evento eventoSeleccionado = (Evento) (iLePanel.getcbEventos().getSelectedItem());
		
//		JPanel cardParent = (JPanel) iLePanel.getParent();
		
		if(s.getNusuario().equals(eventoSeleccionado.getOrganizador())) {
			
			conexionBD.eliminarEvento(eventoSeleccionado.getId());
			iLePanel.actualizarListaEventos();
			System.out.println("olaaaa");
			iLePanel.getcbEventos().removeAllItems();
			
//			JOptionPane.showMessageDialog(cardParent,
//	        	    "El evento ha sido cancelado.",
//	        	    "Evento eliminado",
//	        	    JOptionPane.ERROR_MESSAGE);
			
		} else {
//			JOptionPane.showMessageDialog(cardParent,
//	        	    "No eres el organizador de este evento.",
//	        	    "Error",
//	        	    JOptionPane.ERROR_MESSAGE);
			
		}
		
	}
	
	
	
}
