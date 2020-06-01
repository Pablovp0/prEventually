package interfaces;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import controladores.CntrlBotonCrearEvento;
import controladores.CntrlEliminarCuenta;
import controladores.CntrlRegistroUsuario;
import pruebas.PRUEBATOTAL;

public class InterfazPrincipal extends JPanel {
	
	private JButton bCrearEvento = new JButton("Crear evento");
	private JButton bEliminar = new JButton("Eliminar cuenta");
	private JButton bCerrarSesion = new JButton("Cerrar Sesion");
	private JPanel panelEvento = new JPanel();
	private JPanel panelPerfil = new JPanel();
	
	
	public InterfazPrincipal() {
		JTabbedPane tabbedPane = new JTabbedPane();

		JComponent panel1 = crearPanelEvento();
		tabbedPane.addTab("Eventos", null, panel1, "cacahuete");

		JComponent panel2 = crearPanelPerfil();
		tabbedPane.addTab("Perfil Usuario", null, panel2, "albaricoque");

		add(tabbedPane);
	}

	public JPanel crearPanelEvento() {

		setLayout(new BorderLayout());
		panelEvento.add(bCrearEvento);
		return panelEvento;
		
		
	}

	public JPanel crearPanelPerfil() {

		
		setLayout(new BorderLayout());
		
		panelPerfil.add(bEliminar);
		
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
	
	public void controladorBotonCrearEvento (CntrlBotonCrearEvento c) {
		bCrearEvento.addActionListener(c);
	}
	
	
}
