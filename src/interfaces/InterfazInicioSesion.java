package interfaces;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controladores.CntrlInicioSesion;
import main.Eventually;

public class InterfazInicioSesion extends JPanel {

	private static final long serialVersionUID = 1L;

	public static String BT_INICIAR_SESION_ACCION_COMMAND = "BT_INICIAR_SESION_ACCION_COMMAND";

	private JLabel labelLogo;
	private JButton bCrearCuenta;
	private JButton bLogin;
	private JTextField tfUser;
	private JPasswordField tfPassword;
	private TextPrompt placeholderUser;
	private TextPrompt placeholderPassword;

	public InterfazInicioSesion() {

		bCrearCuenta = new JButton("Crear cuenta");
		bLogin = new JButton("Iniciar sesión");
		tfUser = new JTextField(20);
		tfPassword = new JPasswordField(20);

		labelLogo = new JLabel(new ImageIcon(Eventually.logo));

		placeholderUser = new TextPrompt("Usuario", tfUser);
		placeholderPassword = new TextPrompt("Contraseña", tfPassword);
		placeholderUser.changeAlpha(0.75f);
		placeholderUser.changeStyle(Font.ITALIC);
		placeholderPassword.changeAlpha(0.75f);
		placeholderPassword.changeStyle(Font.ITALIC);

		setLayout(new BorderLayout());

		JPanel botones = new JPanel();

		botones.setLayout(new GridLayout(1, 2));
		botones.add(bCrearCuenta);
		botones.add(bLogin);

		JPanel campos = new JPanel();
		campos.setLayout(new GridLayout(2, 1));
		campos.add(tfUser);
		campos.add(tfPassword);

		add(labelLogo, BorderLayout.NORTH);
		add(botones, BorderLayout.SOUTH);
		add(campos, BorderLayout.CENTER);

		bCrearCuenta.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tfUser.setText(null);
				tfPassword.setText(null);

				JPanel cardParent = (JPanel) InterfazInicioSesion.this.getParent();
				CardLayout cl = (CardLayout) (cardParent.getLayout());
				cl.show(cardParent, Eventually.PANELREGISTRO);
			}
		});

	}

	public JTextField getUsuario() {
		return tfUser;
	}

	public JTextField getContra() {
		return tfPassword;
	}

	public void controlador(CntrlInicioSesion c) {
		bLogin.addActionListener(c);
		bLogin.setActionCommand(BT_INICIAR_SESION_ACCION_COMMAND);
	}

}
