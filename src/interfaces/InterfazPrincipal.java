package interfaces;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;

import controladores.CntrlActualizar;
import controladores.CntrlInicioSesion;
import prEventually.*;
import pruebas.PRUEBATOTAL;

public class InterfazPrincipal extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private JButton bCrearEvento = new JButton("Crear evento");
	private JButton bEliminar = new JButton("Eliminar cuenta");
	private JButton bCerrarSesion = new JButton("Cerrar sesion");
	private JButton bCambiarContraseña = new JButton("Cambiar contraseña");
	private JLabel lbU = new JLabel("Usuario:  ");
	private JLabel lbUsuario = new JLabel("");
	private JLabel lbM = new JLabel("Email:  ");
	private JLabel lbMail = new JLabel("");
	private JPanel panelPerfil = new JPanel();
	private JPanel panelInfoUsuario = new JPanel();
	private JPanel panelBotonesPerfil = new JPanel();
	private InterfazListaEventos panelListaEventos;
	private JLabel labelLogo = new JLabel(new ImageIcon(PRUEBATOTAL.logo));
	private InterfazModificarEvento ime;
	
	public InterfazPrincipal(InterfazModificarEvento i) {
		ime = i;
		
		JTabbedPane tabbedPane = new JTabbedPane();
		
		JComponent panel0 = crearPanelListaEventos();
		tabbedPane.addTab("Lista de eventos", null, panel0, "Vea la lista de eventos disponibles.");

		JComponent panel2 = crearPanelPerfil();
		tabbedPane.addTab("Perfil Usuario", null, panel2, "Cierre su sesión y elimine su cuenta.");
		
		
		add(tabbedPane);
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
		        PRUEBATOTAL.setVentanaTamaño(350, 150);
			}
		});
		
		panelListaEventos.getBtModificarEvento().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Evento ev = (Evento) (getPanelListaEventos().getcbEventos().getSelectedItem());
		        String nombreEvento = ev.getNombre();
		        ime.getNombre().setText("Vas a modificar el Evento: " + nombreEvento);
				JPanel cardParent = (JPanel) InterfazPrincipal.this.getParent();
				CardLayout cl = (CardLayout)(cardParent.getLayout());
		        cl.show(cardParent, PRUEBATOTAL.PANELMODIFICAREVENTO);
		        PRUEBATOTAL.setVentanaTamaño(350, 150);
		        
			}
		});
		
		
		return panelListaEventos;
	}

	public JPanel crearPanelPerfil() {

		setLayout(new BorderLayout());
		
		panelInfoUsuario.setLayout(new GridLayout(2,2));
		panelInfoUsuario.add(lbU);
		panelInfoUsuario.add(lbUsuario);
		panelInfoUsuario.add(lbM);
		panelInfoUsuario.add(lbMail);
		panelPerfil.add(panelInfoUsuario,BorderLayout.NORTH);
		
		panelBotonesPerfil.setLayout(new GridLayout(3,1));
		panelBotonesPerfil.add(bEliminar);
		panelBotonesPerfil.add(bCerrarSesion);
		panelBotonesPerfil.add(bCambiarContraseña);
		panelPerfil.add(panelBotonesPerfil,BorderLayout.SOUTH);
		
		panelPerfil.add(labelLogo, BorderLayout.NORTH);
		
		bCerrarSesion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JPanel cardParent = (JPanel) InterfazPrincipal.this.getParent();
				CardLayout cl = (CardLayout)(cardParent.getLayout());
		        cl.show(cardParent, PRUEBATOTAL.PANELINICIOSESION);
		        PRUEBATOTAL.setVentanaTamaño(PRUEBATOTAL.logo.getWidth(), PRUEBATOTAL.logo.getHeight() + 250); //350, 150 -> 713, 700
			}
		});
		
		bEliminar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JPanel cardParent = (JPanel) InterfazPrincipal.this.getParent();
				CardLayout cl = (CardLayout)(cardParent.getLayout());
		        cl.show(cardParent, PRUEBATOTAL.PANELELIMINARCUENTA);
		        PRUEBATOTAL.setVentanaTamaño(450, 150);
			}
		});
		
		bCambiarContraseña.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JPanel cardParent = (JPanel) InterfazPrincipal.this.getParent();
				CardLayout cl = (CardLayout)(cardParent.getLayout());
		        cl.show(cardParent, PRUEBATOTAL.PANELCAMBIARCONTRASEÑA);
		        PRUEBATOTAL.setVentanaTamaño(350, 150);
			}
		});
		
		return panelPerfil;
	}
	
	public JLabel getLbNUsuario() {
		return lbUsuario;
	}
	
	public JLabel getLbMail() {
		return lbMail;
	}
	
	public InterfazListaEventos getPanelListaEventos() {
		return panelListaEventos;
	}
	
	
	
	
}
