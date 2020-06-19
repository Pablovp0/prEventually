package interfaces;

import controladores.CntrlRegistroUsuario;
import pruebas.PRUEBATOTAL;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class InterfazRegistroUsuario extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	public static String BT_NEW_USER_ACCION_COMMAND = "BT_NEW_USER_ACCION_COMMAND";
	public static String BT_VOLVER_REGISTRO_ACCION_COMMAND = "BT_VOLVER_REGISTRO_ACCION_COMMAND";
	
	private JButton bRegistro;
	private JButton bVolver;
	private JTextField tfUser;
	private JPasswordField tfPassword;
//	private JPasswordField tfPassword2;
	private JTextField tfMail;
	private TextPrompt placeholderUser;
	private TextPrompt placeholderPassword;
	private TextPrompt placeholderMail;
//	private TextPrompt placeholderPassword2;
	
	public InterfazRegistroUsuario() {
		
		bRegistro = new JButton("Registrarse.");
		bVolver = new JButton("Volver.");
		tfUser = new JTextField(20);
		tfPassword = new JPasswordField(20);
//		tfPassword2 = new JPasswordField(20);
		tfMail = new JTextField(20);
		
		placeholderUser = new TextPrompt("Usuario", tfUser);
		placeholderPassword = new TextPrompt("Contraseña", tfPassword);
//		placeholderPassword2 = new TextPrompt("Confirme contraseña", tfPassword2);
		placeholderMail = new TextPrompt("Email", tfMail);
		
		placeholderUser.changeAlpha(0.75f);
		placeholderUser.changeStyle(Font.ITALIC);
		placeholderPassword.changeAlpha(0.75f);
		placeholderPassword.changeStyle(Font.ITALIC);
//		placeholderPassword2.changeAlpha(0.75f);
//		placeholderPassword2.changeStyle(Font.ITALIC);
		placeholderMail.changeAlpha(0.75f);
		placeholderMail.changeStyle(Font.ITALIC);
		
		setLayout(new BorderLayout());
		
		JPanel botones = new JPanel();

		botones.setLayout(new GridLayout(1, 2));
		botones.add(bRegistro);
		botones.add(bVolver);
		
		JPanel campos = new JPanel();
		campos.setLayout(new GridLayout(3, 1));
		campos.add(tfUser);
		campos.add(tfPassword);
//		campos.add(tfPassword2);
		campos.add(tfMail);
		
		
		add(botones, BorderLayout.SOUTH);
		add(campos, BorderLayout.CENTER);
		
		bVolver.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tfUser.setText(null);
				tfPassword.setText(null);
				tfMail.setText(null);
				
				JPanel cardParent = (JPanel) InterfazRegistroUsuario.this.getParent();
				CardLayout cl = (CardLayout)(cardParent.getLayout());
		        cl.show(cardParent, PRUEBATOTAL.PANELINICIOSESION);
			}
		});
		
		
	}
	
	public JTextField getUser() {
		return tfUser;
	}
	
	public JTextField getPassword() {
		return tfPassword;
	}
	
//	public JTextField getPassword2() {
//		return tfPassword2;
//	}
	
	public JTextField getMail() {
		return tfMail;
	}
	
	
	public void controlador(CntrlRegistroUsuario c) {
		bRegistro.addActionListener(c);
		bRegistro.setActionCommand(BT_NEW_USER_ACCION_COMMAND);
	}
	
}
