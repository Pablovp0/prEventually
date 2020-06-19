package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import interfaces.InterfazListaEventos;
import interfaces.InterfazPrincipal;
import prEventually.ConexionConBaseDeDatos;
import prEventually.Evento;
import prEventually.Participación;
import prEventually.Sesion;

public class CntrlParticiparEvento implements ActionListener{
	
	InterfazPrincipal ip;
	ConexionConBaseDeDatos conexionBD;
	
	public CntrlParticiparEvento(ConexionConBaseDeDatos connbd, InterfazPrincipal i) {
		conexionBD = connbd;
		ip = i;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		InterfazListaEventos iLePanel = ip.getPanelListaEventos();
		Sesion s = CntrlInicioSesion.getSesion();
		Evento ev = (Evento) (iLePanel.getcbEventos().getSelectedItem());
		JPanel cardParent = (JPanel) ip.getParent();
		
		if(!ev.usuarioParticipa(s.getNusuario())) {
			ev.inscribirUsuario(s.getNusuario());
			Participación p = new Participación(0, ev.getId(), s.getNusuario());
			int participacionID = conexionBD.añadirParticipante(p);
			p.setId(participacionID);
			JOptionPane.showMessageDialog(cardParent,
	        	    "Se ha confirmado tu participación en el evento '" + ev.getNombre() + "'.",
	        	    "Participación confirmada",
	        	    JOptionPane.DEFAULT_OPTION);
			System.out.println(ev.getParticipantes());
			iLePanel.actualizarListaParticipantes(ev.getId());
		}else {
			JOptionPane.showMessageDialog(cardParent,
	        	    "Ya participas en el evento.",
	        	    "Error",
	        	    JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
}
