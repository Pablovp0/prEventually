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


public class CntrlExpulsarParticipantes implements ActionListener{
	ConexionConBaseDeDatos conexionBD;
	InterfazPrincipal ip;

	public CntrlExpulsarParticipantes(ConexionConBaseDeDatos connbd, InterfazPrincipal i) {
		conexionBD = connbd;
		ip = i;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		InterfazListaEventos iLePanel = ip.getPanelListaEventos();
		Sesion s = CntrlInicioSesion.getSesion();
		Evento eventoSeleccionado = (Evento) (iLePanel.getcbEventos().getSelectedItem());
		JPanel cardParent = (JPanel) ip.getParent();
		
		if(s.getNusuario().equals(eventoSeleccionado.getOrganizador())) {
			
			for(String nu : iLePanel.getParticipantesSeleccionados()) {
				eventoSeleccionado.dardeBajaUsuario(nu);
				conexionBD.eliminarParticipacion(nu, eventoSeleccionado.getId());
				
			}
			if(iLePanel.getParticipantesSeleccionados().size()==0) {
				JOptionPane.showMessageDialog(cardParent,
		        	    "Por favor selecciona algun participante",
		        	    "Error",
		        	    JOptionPane.ERROR_MESSAGE);
			}
			else if(iLePanel.getParticipantesSeleccionados().size()==1) {
				JOptionPane.showMessageDialog(cardParent,
		        	    "El participante " + iLePanel.getParticipantesSeleccionados() + " ha sido expulsado",
		        	    "Expulsion",
		        	    JOptionPane.ERROR_MESSAGE);
			}
			else {
				JOptionPane.showMessageDialog(cardParent,
		        	    "Los participantes " + iLePanel.getParticipantesSeleccionados() + " han sido expulsados",
		        	    "Expulsion",
		        	    JOptionPane.ERROR_MESSAGE);
			}
			iLePanel.actualizarListaParticipantes(eventoSeleccionado.getId());
			

		}
		
		else {
			JOptionPane.showMessageDialog(cardParent,
	        	    "No puedes expulsar a un participante porque no eres el organizador.",
	        	    "Error",
	        	    JOptionPane.ERROR_MESSAGE);
		}
	}
}
