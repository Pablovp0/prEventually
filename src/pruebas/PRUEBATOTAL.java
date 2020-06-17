package pruebas;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import controladores.CntrlBotonCrearEvento;
import controladores.CntrlCrearEvento;
import controladores.CntrlEliminarCuenta;
import controladores.CntrlInicioSesion;
import controladores.CntrlRegistroUsuario;
import interfaces.*;
import prEventually.*;

public class PRUEBATOTAL {
	
	public final static String PANELREGISTRO = "INTERFAZ REGISTRO"; 
	public final static String PANELINICIOSESION = "INTERFAZ INICIO DE SESION";
	public final static String PANELPRINCIPAL = "INTERFAZ PRINCIPAL";
	public final static String PANELELIMINARCUENTA = "INTERFAZ ELIMINAR CUENTA";
	public final static String PANELCREAREVENTO = "INTERFAZ CREAR EVENTO";
	public final static String PANELLISTAEVENTOS = "INTERFAZ LISTA EVENTOS";
	
	public static void main(String[] args) {
		
		ConexionConBaseDeDatos accesoBD;
		accesoBD = ConexionBaseDeDatosJDBC.getInstance();
		
		List<Evento> listEventos = accesoBD.listaEventos();
		List<Usuario> listVacia = new ArrayList<>();
		listVacia = null;
		
		InterfazRegistroUsuario i = new InterfazRegistroUsuario();
		InterfazInicioSesion in = new InterfazInicioSesion();
		InterfazPrincipal ip = new InterfazPrincipal();
		InterfazEliminarCuenta iec = new InterfazEliminarCuenta();
		InterfazCrearEvento ice = new InterfazCrearEvento();
		InterfazListaEventos ile = new InterfazListaEventos(listEventos, listVacia);
		
		
		JPanel cards = new JPanel();
        //Create the panel that contains the "cards".
        cards = new JPanel(new CardLayout());
        cards.add(in, PANELINICIOSESION);
        cards.add(i, PANELREGISTRO);
        cards.add(ip, PANELPRINCIPAL);
        cards.add(iec, PANELELIMINARCUENTA);
        cards.add(ice, PANELCREAREVENTO);
        cards.add(ile,PANELLISTAEVENTOS);
		JFrame ventana = new JFrame("Eventually");

		ventana.setSize(200, 175);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setContentPane(cards);
		ventana.pack();
		ventana.setVisible(true);

	
		CntrlRegistroUsuario cReg = new CntrlRegistroUsuario(accesoBD, i);
		CntrlInicioSesion cIni = new CntrlInicioSesion(accesoBD, in);
		CntrlEliminarCuenta cEc = new CntrlEliminarCuenta(accesoBD, iec);
		CntrlBotonCrearEvento cBce = new CntrlBotonCrearEvento(ip);
		CntrlCrearEvento cCe = new CntrlCrearEvento(accesoBD, ice);
		ice.controladorCrear(cCe);
		ip.controladorBotonCrearEvento(cBce);
		i.controlador(cReg);
		in.controlador(cIni);
		iec.controlador(cEc);
	}

}
