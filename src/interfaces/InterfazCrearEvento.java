package interfaces;

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

import controladores.CntrlCrearEvento;
import controladores.CntrlInicioSesion;
import controladores.CntrlVolverCrearEvento;
import pruebas.PRUEBATOTAL;

public class InterfazCrearEvento extends JPanel{
	
	private JButton bCrear;
	private JButton bVolver;
	private JTextField tfNombre;
	private JTextField tfFecha;
	private JTextField tfLugar;
	private JTextField tfOrganizador;
	private TextPrompt phNombre;
	private TextPrompt phFecha;
	private TextPrompt phLugar;
	private TextPrompt phOrganizador;
	

	public InterfazCrearEvento() {

		bCrear = new JButton("Crear");
		bVolver = new JButton("Volver");
		tfNombre = new JTextField(20);
		tfFecha = new JTextField(20);
		tfLugar = new JTextField(20);
		tfOrganizador = new JTextField(20);

		phNombre = new TextPrompt("Nombre del evento", tfNombre);
		phNombre.changeAlpha(0.75f);
		phNombre.changeStyle(Font.ITALIC);
		phFecha = new TextPrompt("Fecha del evento", tfFecha);
		phFecha.changeAlpha(0.75f);
		phFecha.changeStyle(Font.ITALIC);
		phLugar = new TextPrompt("Lugar del evento", tfLugar);
		phLugar.changeAlpha(0.75f);
		phLugar.changeStyle(Font.ITALIC);
		phOrganizador = new TextPrompt("Creado por...", tfOrganizador);
		phOrganizador.changeAlpha(0.75f);
		phOrganizador.changeStyle(Font.ITALIC);

		setLayout(new BorderLayout());

		JPanel botones = new JPanel();

		botones.setLayout(new GridLayout(1, 2));
		botones.add(bCrear);
		botones.add(bVolver);

		JPanel campos = new JPanel();
		campos.setLayout(new GridLayout(4, 1));
		campos.add(tfNombre);
		campos.add(tfFecha);
		campos.add(tfLugar);
		campos.add(tfOrganizador);

		add(botones, BorderLayout.SOUTH);
		add(campos, BorderLayout.CENTER);

		

	}

	public JTextField getNombre() {
		return tfNombre;
	}

	public JTextField getFecha() {
		return tfFecha;
	}
	
	public JTextField getLugar() {
		return tfLugar;
	}

	public JTextField getOrganizador() {
		return tfOrganizador;
	}
	
	public void controladorVolver (CntrlVolverCrearEvento c) {
		bVolver.addActionListener(c);
	}
	
	public void controladorCrear (CntrlCrearEvento c) {
		bCrear.addActionListener(c);
	}
	
}
