package interfaces;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import controladores.CntrlCancelarParticipacion;
import controladores.CntrlEliminarCuenta;
import controladores.CntrlEliminarEvento;
import controladores.CntrlParticiparEvento;
import prEventually.*;

public class InterfazListaEventos extends JPanel{
	
	private List<Evento> eventos;
	private List<String> participantes;
	
	private JLabel lbSlcEvento;
	private JComboBox<Evento> cbEventos;
	private JPanel pnSlcEvento;
	
	private JLabel lbParticipantesEventoSeleccionado;
	private DefaultListModel<String> lmParticipantesEventoSeleccionado;
	private JList<String> lstParticipantesEventoSeleccionado;
	private JPanel pnListaParticipantes;
	
	private JButton btParticipar;
	private JButton btCancelarParticipacion;
	private JButton btEliminarEvento;
	private JButton btExpulsarParticipante;
	private JPanel pnBotones;
	
	private JLabel lbFecha;
	private JLabel lbFechaEvento;
	private JLabel lbLugar;
	private JLabel lbLugarEvento;
	private JLabel lbOrganizador;
	private JLabel lbOrganizadorEvento;
	private JPanel pnInfoEvento;
	
	public InterfazListaEventos (List<Evento> le, List<String> lu) {
		eventos = le;
		participantes = lu;

		setLayout(new GridLayout(0, 1));
		// panel de seleccion de eventos
		lbSlcEvento = new JLabel("Selecciona Evento");
		cbEventos = new JComboBox<Evento>();
		for (Evento e : eventos) {
			cbEventos.addItem(e);
		}
		pnSlcEvento = new JPanel();
		pnSlcEvento.add(lbSlcEvento);
		pnSlcEvento.add(cbEventos);
		add(pnSlcEvento);
		
		//panel informacion del evento
		
		lbFecha = new JLabel("Fecha: ");
		lbFechaEvento = new JLabel("");
		lbLugar = new JLabel("Lugar: ");
		lbLugarEvento = new JLabel("");
		lbOrganizador = new JLabel("Organizador: ");
		lbOrganizadorEvento = new JLabel("");
		pnInfoEvento = new JPanel();
		pnInfoEvento.setLayout(new GridLayout(3, 2));
		pnInfoEvento.add(lbFecha);
		pnInfoEvento.add(lbFechaEvento);
		pnInfoEvento.add(lbLugar);
		pnInfoEvento.add(lbLugarEvento);
		pnInfoEvento.add(lbOrganizador);
		pnInfoEvento.add(lbOrganizadorEvento);
		add(pnInfoEvento);

		// panel de listas de jugadores
		pnListaParticipantes = new JPanel();
		pnListaParticipantes.setLayout(new GridLayout(2, 1));
		lbParticipantesEventoSeleccionado = new JLabel("Participantes del evento seleccionado");
		lmParticipantesEventoSeleccionado = new DefaultListModel<>();
		Evento evSel = (Evento) cbEventos.getItemAt(0);
		ArrayList<String> partic = evSel.getParticipantes();
		for (String s : partic) {
			lmParticipantesEventoSeleccionado.addElement(s);
		}
		lstParticipantesEventoSeleccionado = new JList<String>(lmParticipantesEventoSeleccionado);
		pnListaParticipantes.add(lbParticipantesEventoSeleccionado);
		pnListaParticipantes.add(new JScrollPane(lstParticipantesEventoSeleccionado));
		add(pnListaParticipantes);

		// panel de botones
		pnBotones = new JPanel();
		this.btParticipar = new JButton("PARTICIPAR EVENTO");
		this.btCancelarParticipacion = new JButton("CANCELAR PARTICIPACION");
		this.btEliminarEvento = new JButton("ELIMINAR EVENTO");
		this.btExpulsarParticipante = new JButton("EXPULSAR PARTICIPANTE");
		pnBotones.add(btParticipar);
		pnBotones.add(btCancelarParticipacion);
		pnBotones.add(btEliminarEvento);
		pnBotones.add(btExpulsarParticipante);
		
		add(pnBotones);
		
		cbEventos.addActionListener(new ActionListener() {
			   public void actionPerformed(ActionEvent e) {
			      Evento ev = (Evento)cbEventos.getSelectedItem();
			      lbFechaEvento.setText(ev.getFecha());
			      lbLugarEvento.setText(ev.getLugar());
			      lbOrganizadorEvento.setText(ev.getOrganizador());
			      
			   }
			});
		
	}
	
	public JLabel getlbFechaEvento() {
		return lbFechaEvento;
	}
	
	public JLabel getlbLugarEvento() {
		return lbLugarEvento;
	}
	
	public JLabel getlbOrganizadorEvento() {
		return lbOrganizadorEvento;
	}
	
	public JComboBox<Evento> getcbEventos() {
		return cbEventos;
	}
	
	public void actualizarListaEventos() {
		ConexionConBaseDeDatos accesoBD;
		accesoBD = ConexionBaseDeDatosJDBC.getInstance();
		
		eventos = accesoBD.listaEventos();
		
		cbEventos = new JComboBox<Evento>();
		for (Evento e : eventos) {
			cbEventos.addItem(e);
		}
		
	}
	
	public void controladorEliminarEvento (CntrlEliminarEvento c) {
		btEliminarEvento.addActionListener(c);
	}
	
	public void controladorParticiparEvento (CntrlParticiparEvento c) {
		btParticipar.addActionListener(c);
	}
	
	public void controladorCancelarParticipacion (CntrlCancelarParticipacion c) {
		btCancelarParticipacion.addActionListener(c);
	}
	
	
}
