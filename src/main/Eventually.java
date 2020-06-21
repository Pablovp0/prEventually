package main;

import java.awt.CardLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import controladores.CntrlActualizar;
import controladores.CntrlCambiarContraseña;
import controladores.CntrlCancelarParticipacion;
import controladores.CntrlCrearEvento;
import controladores.CntrlEliminarCuenta;
import controladores.CntrlEliminarEvento;
import controladores.CntrlExpulsarParticipantes;
import controladores.CntrlInicioSesion;
import controladores.CntrlModificarEvento;
import controladores.CntrlParticiparEvento;
import controladores.CntrlRegistroUsuario;
import interfaces.InterfazCambiarContraseña;
import interfaces.InterfazCrearEvento;
import interfaces.InterfazEliminarCuenta;
import interfaces.InterfazInicioSesion;
import interfaces.InterfazModificarEvento;
import interfaces.InterfazPrincipal;
import interfaces.InterfazRegistroUsuario;
import prEventually.ConexionBaseDeDatosJDBC;
import prEventually.ConexionConBaseDeDatos;

public class Eventually {

	public final static String PANELREGISTRO = "INTERFAZ REGISTRO";
	public final static String PANELINICIOSESION = "INTERFAZ INICIO DE SESION";
	public final static String PANELPRINCIPAL = "INTERFAZ PRINCIPAL";
	public final static String PANELELIMINARCUENTA = "INTERFAZ ELIMINAR CUENTA";
	public final static String PANELCREAREVENTO = "INTERFAZ CREAR EVENTO";
	public final static String PANELLISTAEVENTOS = "INTERFAZ LISTA EVENTOS";
	public final static String PANELCAMBIARCONTRASEÑA = "INTERFAZ CAMBIAR CONTRASEÑA";
	public final static String PANELMODIFICAREVENTO = "INTERFAZ MODIFICAR EVENTO";
	public static JFrame ventana;
	public static BufferedImage logo;

	public static void main(String[] args) {

		ConexionConBaseDeDatos accesoBD;
		accesoBD = ConexionBaseDeDatosJDBC.getInstance();
		logo = new BufferedImage(400, 4, BufferedImage.TYPE_INT_ARGB);

		try { // Si se lanza la excepcion la app no se inicia
			logo = ImageIO.read(new File("src/recursos/eventually_logo.png"));
		} catch (IOException e) {
			System.out.println(e);
		}

		InterfazRegistroUsuario i = new InterfazRegistroUsuario();
		InterfazInicioSesion in = new InterfazInicioSesion();
		InterfazEliminarCuenta iec = new InterfazEliminarCuenta();
		InterfazCrearEvento ice = new InterfazCrearEvento();
		InterfazCambiarContraseña icc = new InterfazCambiarContraseña();
		InterfazModificarEvento ime = new InterfazModificarEvento();
		InterfazPrincipal ip = new InterfazPrincipal(ime);

		JPanel cards = new JPanel();
		// Create the panel that contains the "cards".
		cards = new JPanel(new CardLayout());
		cards.add(in, PANELINICIOSESION);
		cards.add(i, PANELREGISTRO);
		cards.add(ip, PANELPRINCIPAL);
		cards.add(iec, PANELELIMINARCUENTA);
		cards.add(ice, PANELCREAREVENTO);
		cards.add(icc, PANELCAMBIARCONTRASEÑA);
		cards.add(ime, PANELMODIFICAREVENTO);
		ventana = new JFrame("Eventually");

		ventana.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		ventana.setContentPane(cards);
		ventana.pack();
		ventana.setVisible(true);
		ventana.setSize(logo.getWidth(), logo.getHeight() + 250); // 350, 150

		CntrlRegistroUsuario cReg = new CntrlRegistroUsuario(accesoBD, i);
		CntrlInicioSesion cIni = new CntrlInicioSesion(accesoBD, in, ip);
		CntrlEliminarCuenta cEc = new CntrlEliminarCuenta(accesoBD, iec);
		CntrlCrearEvento cCe = new CntrlCrearEvento(accesoBD, ice, ip);
		CntrlEliminarEvento cEe = new CntrlEliminarEvento(accesoBD, ip);
		CntrlParticiparEvento cPe = new CntrlParticiparEvento(accesoBD, ip);
		CntrlCancelarParticipacion cCp = new CntrlCancelarParticipacion(accesoBD, ip);
		CntrlExpulsarParticipantes cEp = new CntrlExpulsarParticipantes(accesoBD, ip);
		CntrlCambiarContraseña cCc = new CntrlCambiarContraseña(accesoBD, icc);
		CntrlActualizar cA = new CntrlActualizar(accesoBD, ip);
		CntrlModificarEvento cMe = new CntrlModificarEvento(accesoBD, ime, ip);
		ice.controladorCrear(cCe);
		i.controlador(cReg);
		in.controlador(cIni);
		iec.controlador(cEc);
		ip.getPanelListaEventos().controladorEliminarEvento(cEe);
		ip.getPanelListaEventos().controladorParticiparEvento(cPe);
		ip.getPanelListaEventos().controladorCancelarParticipacion(cCp);
		ip.getPanelListaEventos().controladorExpulsarParticipante(cEp);
		icc.controlador(cCc);
		ip.getPanelListaEventos().controladorActualizar(cA);
		ime.controladorModificar(cMe);
	}

	public static void setVentanaTamaño(int w, int h) {
		ventana.setSize(w, h);
	}
}
