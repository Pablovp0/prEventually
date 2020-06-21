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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import controladores.CntrlInicioSesion;
import main.Eventually;
import prEventually.ConexionBaseDeDatosJDBC;
import prEventually.ConexionConBaseDeDatos;
import prEventually.Evento;

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
	private JLabel labelLogo = new JLabel(new ImageIcon(Eventually.logo));
	private InterfazModificarEvento ime;

	public InterfazPrincipal(InterfazModificarEvento i) {
		ime = i;

		JTabbedPane tabbedPane = new JTabbedPane();

		JComponent panel0 = crearPanelListaEventos();
		tabbedPane.addTab("Lista de eventos", null, panel0, "Ver la lista de eventos disponibles");

		JComponent panel2 = crearPanelPerfil();
		tabbedPane.addTab("Perfil Usuario", null, panel2, "Cerrar sesión, eliminar cuenta y modificar contraseña");

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
				CardLayout cl = (CardLayout) (cardParent.getLayout());
				cl.show(cardParent, Eventually.PANELCREAREVENTO);
				Eventually.setVentanaTamaño(350, 150);
			}
		});

		panelListaEventos.getBtModificarEvento().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Evento ev = (Evento) (getPanelListaEventos().getcbEventos().getSelectedItem());
				String nombreEvento = ev.getNombre();
				if (CntrlInicioSesion.getSesion().getNusuario().equals(ev.getOrganizador())) {
					ime.getNombre().setText("Vas a modificar el Evento: " + nombreEvento);
					JPanel cardParent = (JPanel) InterfazPrincipal.this.getParent();
					CardLayout cl = (CardLayout) (cardParent.getLayout());
					cl.show(cardParent, Eventually.PANELMODIFICAREVENTO);
					Eventually.setVentanaTamaño(350, 150);
					ime.getFecha().setText(ev.getFecha());
					ime.getLugar().setText(ev.getLugar());
				} else {
					JOptionPane.showMessageDialog(InterfazPrincipal.this.getParent(),
							"No puedes modificar este evento porque no eres el organizador.", "Error",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});

		return panelListaEventos;
	}

	public JPanel crearPanelPerfil() {

		setLayout(new BorderLayout());

		panelInfoUsuario.setLayout(new GridLayout(2, 2));
		panelInfoUsuario.add(lbU);
		panelInfoUsuario.add(lbUsuario);
		panelInfoUsuario.add(lbM);
		panelInfoUsuario.add(lbMail);
		panelPerfil.add(panelInfoUsuario, BorderLayout.NORTH);

		panelBotonesPerfil.setLayout(new GridLayout(3, 1));
		panelBotonesPerfil.add(bEliminar);
		panelBotonesPerfil.add(bCerrarSesion);
		panelBotonesPerfil.add(bCambiarContraseña);
		panelPerfil.add(panelBotonesPerfil, BorderLayout.SOUTH);

		panelPerfil.add(labelLogo, BorderLayout.NORTH);

		bCerrarSesion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JPanel cardParent = (JPanel) InterfazPrincipal.this.getParent();
				CardLayout cl = (CardLayout) (cardParent.getLayout());
				cl.show(cardParent, Eventually.PANELINICIOSESION);
				Eventually.setVentanaTamaño(Eventually.logo.getWidth(), Eventually.logo.getHeight() + 250); // 350, 150
																											// -> 713,
																											// 700
			}
		});

		bEliminar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JPanel cardParent = (JPanel) InterfazPrincipal.this.getParent();
				CardLayout cl = (CardLayout) (cardParent.getLayout());
				cl.show(cardParent, Eventually.PANELELIMINARCUENTA);
				Eventually.setVentanaTamaño(450, 150);
			}
		});

		bCambiarContraseña.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JPanel cardParent = (JPanel) InterfazPrincipal.this.getParent();
				CardLayout cl = (CardLayout) (cardParent.getLayout());
				cl.show(cardParent, Eventually.PANELCAMBIARCONTRASEÑA);
				Eventually.setVentanaTamaño(350, 150);
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
