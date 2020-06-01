package controladores;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import interfaces.InterfazPrincipal;
import pruebas.PRUEBATOTAL;

public class CntrlBotonCrearEvento implements ActionListener{

	InterfazPrincipal ipPanel;
	
	public CntrlBotonCrearEvento(InterfazPrincipal ip) {
		ipPanel = ip;
	}
	
	public void actionPerformed(ActionEvent e) {
		
		JPanel cardParent = (JPanel) ipPanel.getParent();
		CardLayout ccl = (CardLayout) (cardParent.getLayout());
		ccl.show(cardParent, PRUEBATOTAL.PANELCREAREVENTO);
		
	}
	
	
	
}
