package interfaces;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controladores.CntrlEliminarCuenta;
import controladores.CntrlInicioSesion;
import pruebas.PRUEBATOTAL;

public class InterfazEliminarCuenta extends JPanel{
	
	public static String BT_INICIAR_SESION_ACCION_COMMAND = "BT_INICIAR_SESION_ACCION_COMMAND";

	private JButton bEliminarCuenta;
	private JButton bVolver;
	private JTextField tfUser;
	private JPasswordField tfPassword;
	private TextPrompt placeholderUser;
	private TextPrompt placeholderPassword;
	private JLabel lPregunta;

	public InterfazEliminarCuenta() {

		bEliminarCuenta = new JButton("Eliminar cuenta");
		bVolver = new JButton("Volver");
		tfUser = new JTextField(20);
		tfPassword = new JPasswordField(20);
		lPregunta = new JLabel("Rellena tus datos y pulsa en 'Eliminar cuenta' para borrar tu cuenta.");

		placeholderUser = new TextPrompt("Usuario", tfUser);
		placeholderPassword = new TextPrompt("Contraseña", tfPassword);
		placeholderUser.changeAlpha(0.75f);
		placeholderUser.changeStyle(Font.ITALIC);
		placeholderPassword.changeAlpha(0.75f);
		placeholderPassword.changeStyle(Font.ITALIC);

		setLayout(new BorderLayout());

		JPanel botones = new JPanel();

		botones.setLayout(new GridLayout(1, 2));
		botones.add(bEliminarCuenta);
		botones.add(bVolver);

		JPanel campos = new JPanel();
		campos.setLayout(new GridLayout(3, 1));
		campos.add(lPregunta);
		campos.add(tfUser);
		campos.add(tfPassword);

		add(botones, BorderLayout.SOUTH);
		add(campos, BorderLayout.CENTER);

		bVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfUser.setText(null);
				tfPassword.setText(null);
				
				JPanel cardParent = (JPanel) InterfazEliminarCuenta.this.getParent();
				CardLayout cl = (CardLayout) (cardParent.getLayout());
				cl.show(cardParent, PRUEBATOTAL.PANELPRINCIPAL);
			}
		});

	}

	public JTextField getUsuario() {
		return tfUser;
	}

	public JTextField getContra() {
		return tfPassword;
	}
	
	public void controlador (CntrlEliminarCuenta c) {
		bEliminarCuenta.addActionListener(c);
		bEliminarCuenta.setActionCommand(BT_INICIAR_SESION_ACCION_COMMAND);
	}
	
}
