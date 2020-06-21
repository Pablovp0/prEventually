package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import interfaces.InterfazListaEventos;
import interfaces.InterfazPrincipal;
import prEventually.ConexionConBaseDeDatos;
import prEventually.Evento;
import prEventually.Sesion;

public class CntrlActualizar implements ActionListener {

	InterfazPrincipal ip;
	ConexionConBaseDeDatos conexionBD;

	public CntrlActualizar(ConexionConBaseDeDatos bd, InterfazPrincipal i) {
		conexionBD = bd;
		ip = i;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		InterfazListaEventos iLePanel = ip.getPanelListaEventos();
		JCheckBox organizo = iLePanel.getChbOrganizo();
		JCheckBox participo = iLePanel.getChbParticipo();
		Sesion s = CntrlInicioSesion.getSesion();
		Evento ev = (Evento) iLePanel.getcbEventos().getSelectedItem();
		JPanel cardParent = (JPanel) ip.getParent();

		if (!organizo.isSelected() && !participo.isSelected()) {
			iLePanel.actualizarListaEventos();
			iLePanel.actualizarListaParticipantes(ev.getId());
		} else if (organizo.isSelected() && participo.isSelected()) {
			JOptionPane.showMessageDialog(cardParent, "Seleccione una opción únicamente.", "Error",
					JOptionPane.ERROR_MESSAGE);
		} else if (organizo.isSelected() && !participo.isSelected()) {

			iLePanel.actualizarListaEventosOrganizo(s.getNusuario());
			iLePanel.actualizarListaParticipantes(ev.getId());

		} else {

			iLePanel.actualizarListaEventosParticipo(s.getNusuario());
			iLePanel.actualizarListaParticipantes(ev.getId());

		}

	}

}
