package controladores;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import interfaces.InterfazModificarEvento;
import interfaces.InterfazPrincipal;
import main.Eventually;
import prEventually.ConexionConBaseDeDatos;
import prEventually.Evento;

public class CntrlModificarEvento implements ActionListener {

	ConexionConBaseDeDatos conexionBD;
	InterfazModificarEvento iMePanel;
	InterfazPrincipal ipPanel;

	public CntrlModificarEvento(ConexionConBaseDeDatos bd, InterfazModificarEvento i, InterfazPrincipal ip) {
		conexionBD = bd;
		iMePanel = i;
		ipPanel = ip;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JPanel cardParent = (JPanel) iMePanel.getParent();

		if (iMePanel.getFecha().getText().length() == 0 || iMePanel.getLugar().getText().length() == 0) {
			JOptionPane.showMessageDialog(cardParent, "Los campos no pueden estar en blanco", "Error",
					JOptionPane.ERROR_MESSAGE);
		} else {
			Evento ev = (Evento) ipPanel.getPanelListaEventos().getcbEventos().getSelectedItem();
			conexionBD.modificarEvento(iMePanel.getFecha().getText(), iMePanel.getLugar().getText(), ev.getId());
			JOptionPane.showMessageDialog(cardParent, "El evento " + ev.getNombre() + " ha sido modificado",
					"Evento modificado", JOptionPane.DEFAULT_OPTION);
			CardLayout ccl = (CardLayout) (cardParent.getLayout());
			ccl.show(cardParent, Eventually.PANELPRINCIPAL);
			Eventually.setVentanaTamaño(980, 600);

		}

	}

}
