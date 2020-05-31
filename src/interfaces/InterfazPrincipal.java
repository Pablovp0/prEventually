package interfaces;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import controladores.CntrlRegistroUsuario;
import pruebas.PRUEBATOTAL;

public class InterfazPrincipal extends JPanel {
	
	

	public InterfazPrincipal() {
		JTabbedPane tabbedPane = new JTabbedPane();

		JComponent panel1 = crearPanelEvento();
		tabbedPane.addTab("Eventos", null, panel1, "cacahuete");

		JComponent panel2 = crearPanelPerfil();
		tabbedPane.addTab("Perfil Usuario", null, panel2, "albaricoque");

		add(tabbedPane);
	}

	public JPanel crearPanelEvento() {

		JPanel panelEvento = new JPanel();
		setLayout(new BorderLayout());
		JButton boton1 = new JButton("botonEvento");
		panelEvento.add(boton1);

		return panelEvento;
	}

	public JPanel crearPanelPerfil() {

		JPanel panelPerfil = new JPanel();
		setLayout(new BorderLayout());
		JButton bEliminar = new JButton("Eliminar cuenta");
		panelPerfil.add(bEliminar);
		JButton bCerrarSesion = new JButton("Cerrar Sesion");
		panelPerfil.add(bCerrarSesion);
		
		bCerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JPanel cardParent = (JPanel) InterfazPrincipal.this.getParent();
				CardLayout cl = (CardLayout)(cardParent.getLayout());
		        cl.show(cardParent, PRUEBATOTAL.SEGUNDOPANEL);
			}
		});
		
		bEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JPanel cardParent = (JPanel) InterfazPrincipal.this.getParent();
				CardLayout cl = (CardLayout)(cardParent.getLayout());
		        cl.show(cardParent, PRUEBATOTAL.PANELELIMINARCUENTA);
			}
		});
		
		
		return panelPerfil;
	}
	
	
}
