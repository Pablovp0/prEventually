package controladores;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import interfaces.InterfazCrearEvento;
import pruebas.PRUEBATOTAL;

public class CntrlVolverCrearEvento implements ActionListener{
	
	InterfazCrearEvento icePanel;
	
	public CntrlVolverCrearEvento(InterfazCrearEvento ice) {
		icePanel = ice;
	}
	
	public void actionPerformed(ActionEvent e) {
		
		JPanel cardParent = (JPanel) icePanel.getParent();
		CardLayout ccl = (CardLayout) (cardParent.getLayout());
		ccl.show(cardParent, PRUEBATOTAL.PANELPRINCIPAL);
		
	}
	
	
	
}
