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

import controladores.CntrlCambiarContrase�a;
import pruebas.PRUEBATOTAL;

public class InterfazCambiarContrase�a extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	private JButton bCambiarContrase�a;
	private JButton bVolver;
	private JPasswordField tfPassword;
	private TextPrompt placeholderPassword;
	private JPasswordField tfPasswordNueva;
	private TextPrompt placeholderPasswordNueva;
	private JLabel lEtiqueta;
	
	public InterfazCambiarContrase�a() {
		
		bCambiarContrase�a = new JButton("Confirmar cambio.");
		bVolver = new JButton("Volver.");
		tfPassword = new JPasswordField(20);
		tfPasswordNueva = new JPasswordField(20);
		lEtiqueta = new JLabel("Escribe tu antigua y nueva contrase�a.");
		
		placeholderPassword = new TextPrompt("Contrase�a antigua", tfPassword);
		placeholderPassword.changeAlpha(0.75f);
		placeholderPassword.changeStyle(Font.ITALIC);
		placeholderPasswordNueva = new TextPrompt("Contrase�a nueva", tfPasswordNueva);
		placeholderPasswordNueva.changeAlpha(0.75f);
		placeholderPasswordNueva.changeStyle(Font.ITALIC);
		
		setLayout(new BorderLayout());

		JPanel botones = new JPanel();

		botones.setLayout(new GridLayout(1, 2));
		botones.add(bCambiarContrase�a);
		botones.add(bVolver);

		JPanel campos = new JPanel();
		campos.setLayout(new GridLayout(3, 1));
		campos.add(lEtiqueta);
		campos.add(tfPassword);
		campos.add(tfPasswordNueva);

		add(botones, BorderLayout.SOUTH);
		add(campos, BorderLayout.CENTER);
		
		bVolver.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tfPassword.setText(null);
				
				JPanel cardParent = (JPanel) InterfazCambiarContrase�a.this.getParent();
				CardLayout cl = (CardLayout) (cardParent.getLayout());
				cl.show(cardParent, PRUEBATOTAL.PANELPRINCIPAL);
				tfPassword.setText(null);
				tfPasswordNueva.setText(null);
				PRUEBATOTAL.setVentanaTama�o(800, 600);
			}
		});
		
		
		
	}
	
	public JTextField getContra() {
			return tfPassword;
		}
		
	public JTextField getContraNueva() {
		return tfPasswordNueva;
		}
	
	public void controlador (CntrlCambiarContrase�a c) {
		bCambiarContrase�a.addActionListener(c);
	}
}
