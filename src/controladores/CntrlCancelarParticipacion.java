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

public class CntrlCancelarParticipacion implements ActionListener {

	InterfazPrincipal ip;
	ConexionConBaseDeDatos conexionBD;

	public CntrlCancelarParticipacion(ConexionConBaseDeDatos bd, InterfazPrincipal i) {
		conexionBD = bd;
		ip = i;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		InterfazListaEventos iLePanel = ip.getPanelListaEventos();
		Sesion s = CntrlInicioSesion.getSesion();
		Evento ev = (Evento) (iLePanel.getcbEventos().getSelectedItem());
		JPanel cardParent = (JPanel) ip.getParent();

		if (!ev.usuarioParticipa(s.getNusuario())) {
			JOptionPane.showMessageDialog(cardParent, "No participas en el evento '" + ev.getNombre() + "'.", "Error",
					JOptionPane.ERROR_MESSAGE);
		} else {
			ev.dardeBajaUsuario(s.getNusuario());
			conexionBD.eliminarParticipacion(s.getNusuario(), ev.getId());
			System.out.println(ev.getParticipantes());
			JOptionPane.showMessageDialog(cardParent,
					"Tu participacion en el evento '" + ev.getNombre() + "' ha sido cancelada.",
					"Participación cancelada", JOptionPane.DEFAULT_OPTION);
			iLePanel.actualizarListaParticipantes(ev.getId());
		}

	}

}
