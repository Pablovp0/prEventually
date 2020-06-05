package controladores;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import interfaces.InterfazInicioSesion;
import interfaces.InterfazRegistroUsuario;


	public class CntrlVolverRegistro implements ActionListener{
	
	static InterfazRegistroUsuario nuPanel;
	static InterfazInicioSesion isPanel;
	
	final static String PANELREGISTRO = "Panel registro";
	final static String PANELINICIOSESION = "Panel inicio sesion";
	
	public CntrlVolverRegistro(InterfazRegistroUsuario nu, InterfazInicioSesion is) {
		nuPanel = nu;
		isPanel = is;
	}
	
	protected static JPanel createCardLayoutPanel() {
		JPanel cards = new JPanel();
        //Create the panel that contains the "cards".
        cards = new JPanel(new CardLayout());
        cards.add(nuPanel, PANELREGISTRO);
        cards.add(isPanel, PANELINICIOSESION);

        return cards;
	}
	
	public void actionPerformed(ActionEvent e) {
		createCardLayoutPanel();
	}
	
	
	
}
