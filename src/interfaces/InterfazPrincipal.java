package interfaces;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import controladores.CntrlBotonCrearEvento;
import controladores.CntrlEliminarCuenta;
import controladores.CntrlRegistroUsuario;
import prEventually.*;
import pruebas.PRUEBATOTAL;

public class InterfazPrincipal extends JPanel {
	
	private JButton bCrearEvento = new JButton("Crear evento");
	private JButton bEliminar = new JButton("Eliminar cuenta");
	private JButton bCerrarSesion = new JButton("Cerrar Sesion");
	private JButton bParticipar = new JButton("Participar");
	private JButton bAnular = new JButton("Anular");
	private JPanel panelPrincipal = new JPanel();
	private JPanel panelEvento = new JPanel();
	private JPanel panelPerfil = new JPanel();
	
	
	public InterfazPrincipal() {
		JTabbedPane tabbedPane = new JTabbedPane();

//		List<Evento> le = null;
//		List<Usuario> lu = null;
//		JComponent panel0 = new InterfazListaEventos(le, lu);
		JComponent panel0 = crearPanelPrincipal();
		tabbedPane.addTab("Panel Principal", null, panel0, "wowowoow");
		
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
	
	public JPanel crearPanelPrincipal() {

		setLayout(new GridLayout(2, 1));
		panelPrincipal.add(bParticipar);
		panelPrincipal.add(bAnular);
		
		bParticipar.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			System.out.println("participar");
		}
		});
		
		bAnular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Anular");
			}
		});
		return panelPrincipal;
	}

	public JPanel crearPanelPerfil() {

		
		setLayout(new BorderLayout());
		
		panelPerfil.add(bEliminar);
		
		panelPerfil.add(bCerrarSesion);
		
		bCerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JPanel cardParent = (JPanel) InterfazPrincipal.this.getParent();
				CardLayout cl = (CardLayout)(cardParent.getLayout());
		        cl.show(cardParent, PRUEBATOTAL.PANELINICIOSESION);
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
