package pruebas;

import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JPanel;

import controladores.CntrlEliminarCuenta;
import controladores.CntrlInicioSesion;
import controladores.CntrlRegistroUsuario;
import prEventually.*;
import interfaces.*;

public class PRUEBATOTAL {
	
	public final static String PRIMERPANEL = "INTERFAZ REGISTRO"; 
	public final static String SEGUNDOPANEL = "INTERFAZ INICIO DE SESION";
	public final static String PANELPRINCIPAL = "INTERFAZ PRINCIPAL";
	public final static String PANELELIMINARCUENTA = "INTERFAZ ELIMINAR CUENTA";
	
	public static void main(String[] args) {
		
		InterfazRegistroUsuario i = new InterfazRegistroUsuario();
		InterfazInicioSesion in = new InterfazInicioSesion();
		InterfazPrincipal ip = new InterfazPrincipal();
		InterfazEliminarCuenta iec = new InterfazEliminarCuenta();
		
		JPanel cards = new JPanel();
        //Create the panel that contains the "cards".
        cards = new JPanel(new CardLayout());
        cards.add(in, SEGUNDOPANEL);
        cards.add(i, PRIMERPANEL);
        cards.add(ip, PANELPRINCIPAL);
        cards.add(iec, PANELELIMINARCUENTA);
		JFrame ventana = new JFrame("Eventually");

		ventana.setSize(200, 175);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setContentPane(cards);
		ventana.pack();
		ventana.setVisible(true);

		ConexionConBaseDeDatos accesoBD;
		accesoBD = ConexionBaseDeDatosJDBC.getInstance();
		CntrlRegistroUsuario cReg = new CntrlRegistroUsuario(accesoBD, i);
		CntrlInicioSesion cIni = new CntrlInicioSesion(accesoBD, in);
		CntrlEliminarCuenta cEc = new CntrlEliminarCuenta(accesoBD, iec);
		i.controlador(cReg);
		in.controlador(cIni);
		iec.controlador(cEc);
		
		
		
	}

}
