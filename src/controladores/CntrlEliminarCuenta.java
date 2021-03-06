package controladores;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import interfaces.InterfazEliminarCuenta;
import main.Eventually;
import prEventually.ConexionConBaseDeDatos;
import prEventually.Sesion;

public class CntrlEliminarCuenta implements ActionListener {

	ConexionConBaseDeDatos conexionBD;
	InterfazEliminarCuenta iEcPanel;

	public CntrlEliminarCuenta(ConexionConBaseDeDatos connbd, InterfazEliminarCuenta ec) {
		conexionBD = connbd;
		iEcPanel = ec;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Sesion s = CntrlInicioSesion.getSesion();
		String contrasenaEscrita = iEcPanel.getContra().getText();
		JPanel cardParent = (JPanel) iEcPanel.getParent();

		if (contrasenaEscrita.equals(s.getContrase�a())) {

			conexionBD.eliminarCuenta(s.getNusuario());
			CardLayout ccl = (CardLayout) (cardParent.getLayout());
			ccl.show(cardParent, Eventually.PANELINICIOSESION);
			Eventually.setVentanaTama�o(Eventually.logo.getWidth(), Eventually.logo.getHeight() + 250); // 350, 150 ->
																										// 713, 700

		} else {
			JOptionPane.showMessageDialog(cardParent, "Contrasena incorrecta", "Error", JOptionPane.ERROR_MESSAGE);

		}

	}

}
