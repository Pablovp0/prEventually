package interfaces;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class InterfazInicioSesion extends JPanel{
	
	private JButton bCrearCuenta;
	private JButton bLogin;
	private JTextField tfUser;
	private JPasswordField tfPassword;
	private TextPrompt placeholderUser;
	private TextPrompt placeholderPassword;

	public InterfazInicioSesion(){
		
		bCrearCuenta = new JButton("Crear cuenta.");
		bLogin = new JButton("Iniciar sesi�n.");
		tfUser = new JTextField(20);
		tfPassword = new JPasswordField(20);


		placeholderUser = new TextPrompt("Usuario", tfUser);
		placeholderPassword = new TextPrompt("Contrase�a", tfPassword);
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
		
		add(botones, BorderLayout.CENTER);
		add(campos, BorderLayout.NORTH);
	
	}
}
