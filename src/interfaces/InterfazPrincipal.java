package interfaces;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;

import controladores.CntrlActualizar;
import controladores.CntrlBotonCrearEvento;
import prEventually.*;
import pruebas.PRUEBATOTAL;

public class InterfazPrincipal extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private JButton bCrearEvento = new JButton("Crear evento");
	private JButton bEliminar = new JButton("Eliminar cuenta");
	private JButton bCerrarSesion = new JButton("Cerrar sesion");
	private JButton bCambiarContrase�a = new JButton("Cambiar contrase�a");
	private JLabel lbU = new JLabel("Usuario:");
	private JLabel lbUsuario = new JLabel("");
	private JLabel lbM = new JLabel("Email:");
	private JLabel lbMail = new JLabel("");
	private JPanel panelEvento = new JPanel();
	private JPanel panelPerfil = new JPanel();
	private InterfazListaEventos panelListaEventos;
	
	
	
	public InterfazPrincipal() {
		JTabbedPane tabbedPane = new JTabbedPane();
		
		JComponent panel0 = crearPanelListaEventos();
		tabbedPane.addTab("Lista de eventos", null, panel0, "Vea la lista de eventos disponibles.");
		
		JComponent panel1 = crearPanelEvento();
		tabbedPane.addTab("Eventos", null, panel1, ".");

		JComponent panel2 = crearPanelPerfil();
		tabbedPane.addTab("Perfil Usuario", null, panel2, "Cierre su sesi�n y elimine su cuenta.");
		
		
		add(tabbedPane);
	}

	public JPanel crearPanelEvento() {

		setLayout(new BorderLayout());
		panelEvento.add(bCrearEvento);
		return panelEvento;
		
		
	}
	
	public JPanel crearPanelListaEventos() {
		
		ConexionConBaseDeDatos accesoBD;
		accesoBD = ConexionBaseDeDatosJDBC.getInstance();
		
		List<Evento> le = accesoBD.listaEventos();
		List<String> lu = new ArrayList<>();
		
		panelListaEventos = new InterfazListaEventos(le, lu);
		
		panelListaEventos.getBtCrearEvento().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JPanel cardParent = (JPanel) InterfazPrincipal.this.getParent();
				CardLayout cl = (CardLayout)(cardParent.getLayout());
		        cl.show(cardParent, PRUEBATOTAL.PANELCREAREVENTO);
		        PRUEBATOTAL.setVentanaTama�o(350, 150);
			}
		});
		
		
		return panelListaEventos;
	}

	public JPanel crearPanelPerfil() {

		
		setLayout(new BorderLayout());
		
		panelPerfil.add(bEliminar);
		
		panelPerfil.add(bCerrarSesion);
		
		panelPerfil.add(bCambiarContrase�a);
		
		
		bCerrarSesion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JPanel cardParent = (JPanel) InterfazPrincipal.this.getParent();
				CardLayout cl = (CardLayout)(cardParent.getLayout());
		        cl.show(cardParent, PRUEBATOTAL.PANELINICIOSESION);
		        PRUEBATOTAL.setVentanaTama�o(PRUEBATOTAL.logo.getWidth(), PRUEBATOTAL.logo.getHeight() + 250); //350, 150 -> 713, 700
			}
		});
		
		bEliminar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JPanel cardParent = (JPanel) InterfazPrincipal.this.getParent();
				CardLayout cl = (CardLayout)(cardParent.getLayout());
		        cl.show(cardParent, PRUEBATOTAL.PANELELIMINARCUENTA);
		        PRUEBATOTAL.setVentanaTama�o(450, 150);
			}
		});
		
		bCambiarContrase�a.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JPanel cardParent = (JPanel) InterfazPrincipal.this.getParent();
				CardLayout cl = (CardLayout)(cardParent.getLayout());
		        cl.show(cardParent, PRUEBATOTAL.PANELCAMBIARCONTRASE�A);
		        PRUEBATOTAL.setVentanaTama�o(350, 150);
			}
		});
		
		
		return panelPerfil;
	}
	
	public void controladorBotonCrearEvento (CntrlBotonCrearEvento c) {
		bCrearEvento.addActionListener(c);
	}
	
	public InterfazListaEventos getPanelListaEventos() {
		return panelListaEventos;
	}
	
	
	
	
}
