package interfaces;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class InterfazPrincipal extends JPanel {
	private JButton boton1;
	
	public InterfazPrincipal() {
		setLayout(new BorderLayout());
		boton1 = new JButton("NO PULSAR");
		add(boton1);
	}
}
