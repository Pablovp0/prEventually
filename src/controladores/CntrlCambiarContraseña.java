package controladores;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import interfaces.InterfazCambiarContrase�a;
import main.Eventually;
import prEventually.ConexionConBaseDeDatos;
import prEventually.Sesion;

public class CntrlCambiarContrase�a implements ActionListener {

	ConexionConBaseDeDatos conexionBD;
	InterfazCambiarContrase�a iCc;

	public CntrlCambiarContrase�a(ConexionConBaseDeDatos connbd, InterfazCambiarContrase�a i) {
		iCc = i;
		conexionBD = connbd;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		Sesion s = CntrlInicioSesion.getSesion();
		String contrasenaAntigua = iCc.getContra().getText();
		String contrasenaNueva = iCc.getContraNueva().getText();
		JPanel cardParent = (JPanel) iCc.getParent();

		if (!contrasenaAntigua.equals(s.getContrase�a())) {

			JOptionPane.showMessageDialog(cardParent, "Contrasena incorrecta", "Error", JOptionPane.ERROR_MESSAGE);

		} else if (contrasenaNueva.equals(contrasenaAntigua)) {
			JOptionPane.showMessageDialog(cardParent, "La nueva contrase�a coincide con la antigua", "Error",
					JOptionPane.ERROR_MESSAGE);
		} else {

			conexionBD.actualizarContrase�a(s.getNusuario(), contrasenaNueva);
			JOptionPane.showMessageDialog(cardParent, "Su contrase�a ha sido cambiada", "Contrase�a actualizada",
					JOptionPane.DEFAULT_OPTION);
			CardLayout ccl = (CardLayout) (cardParent.getLayout());
			ccl.show(cardParent, Eventually.PANELPRINCIPAL);
			iCc.getContra().setText(null);
			iCc.getContraNueva().setText(null);
			Eventually.setVentanaTama�o(980, 600);
			CntrlInicioSesion.setSesion(s.getNusuario(), contrasenaNueva);

		}

	}

}
