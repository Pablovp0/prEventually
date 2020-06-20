package interfaces;

import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import controladores.CntrlActualizar;
import controladores.CntrlCancelarParticipacion;
import controladores.CntrlEliminarEvento;
import controladores.CntrlExpulsarParticipantes;
import controladores.CntrlParticiparEvento;
import prEventually.*;
import pruebas.PRUEBATOTAL;

public class InterfazListaEventos extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private List<Evento> eventos;
	private List<String> participantes;

	private JLabel lbSlcEvento;
	private JComboBox<Evento> cbEventos;
	private JButton btActualizar;
	private JCheckBox chbOrganizo;
	private JCheckBox chbParticipo;
	private JPanel pnSlcEvento;

	private JLabel lbParticipantesEventoSeleccionado;
	private DefaultListModel<String> lmParticipantesEventoSeleccionado;
	private JList<String> lstParticipantesEventoSeleccionado;
	private JPanel pnListaParticipantes;

	private JButton btParticipar;
	private JButton btCancelarParticipacion;
	private JButton btEliminarEvento;
	private JButton btExpulsarParticipante;
	private JButton btCrearEvento;
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
		btActualizar = new JButton("Actualizar");
		chbOrganizo = new JCheckBox("Eventos que organizo");
		chbParticipo = new JCheckBox("Eventos en los que participo");
		cbEventos = new JComboBox<Evento>();
		for (Evento e : eventos) {
			cbEventos.addItem(e);
		}
		pnSlcEvento = new JPanel();
		pnSlcEvento.add(lbSlcEvento);
		pnSlcEvento.add(cbEventos);
		pnSlcEvento.add(btActualizar);
		pnSlcEvento.add(chbOrganizo);
		pnSlcEvento.add(chbParticipo);
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

		// panel de listas de participantes
		pnListaParticipantes = new JPanel();
		pnListaParticipantes.setLayout(new GridLayout(2, 1));
		lbParticipantesEventoSeleccionado = new JLabel("Participantes del evento seleccionado");
		lmParticipantesEventoSeleccionado = new DefaultListModel<>();
		Evento evSel = cbEventos.getItemAt(0);
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
		this.btCrearEvento = new JButton("CREAR EVENTO");
		pnBotones.add(btParticipar);
		pnBotones.add(btCancelarParticipacion);
		pnBotones.add(btEliminarEvento);
		pnBotones.add(btExpulsarParticipante);
		pnBotones.add(btCrearEvento);
		
		add(pnBotones);
		
		//Actualiza informacion evento y participantes al seleccionar evento en CB
		cbEventos.addActionListener(new ActionListener() {
			   @Override
			public void actionPerformed(ActionEvent e) {
				  if(cbEventos.getItemCount() != 0) {
					  

				      Evento ev = (Evento)cbEventos.getSelectedItem();
				      lbFechaEvento.setText(ev.getFecha());
				      lbLugarEvento.setText(ev.getLugar());
				      lbOrganizadorEvento.setText(ev.getOrganizador());
				      
				      actualizarListaParticipantes(ev.getId());
				     
				  }
			   }
			});
		
//		btCrearEvento.addActionListener(new ActionListener() {
//			   @Override
//			public void actionPerformed(ActionEvent e) {
//				   JPanel cardParent = (JPanel) InterfazPrincipal.getParent();
//				   CardLayout ccl = (CardLayout) (cardParent.getLayout());
//				   ccl.show(cardParent, PRUEBATOTAL.PANELCREAREVENTO);
//			   }
//			});
		
		
		
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
	
	public JCheckBox getChbOrganizo() {
		return chbOrganizo;
	}
	
	public JCheckBox getChbParticipo() {
		return chbParticipo;
	}
	
	public JButton getBtCrearEvento() {
		return btCrearEvento;
	}

	public void actualizarListaEventos() {
		ConexionConBaseDeDatos accesoBD;
		accesoBD = ConexionBaseDeDatosJDBC.getInstance();

		eventos = accesoBD.listaEventos();

		cbEventos.removeAllItems();
		for (Evento e : eventos) {
			cbEventos.addItem(e);
		}
	}
	
	public void actualizarListaEventosOrganizo(String nUsuario) {
		ConexionConBaseDeDatos accesoBD;
		accesoBD = ConexionBaseDeDatosJDBC.getInstance();

		eventos = accesoBD.listaEventos();

		cbEventos.removeAllItems();
		for (Evento e : eventos) {
			if(e.getOrganizador().equals(nUsuario)) {
				cbEventos.addItem(e);
			}
		}
	}
	
	public void actualizarListaEventosParticipo(String nUsuario) {
		ConexionConBaseDeDatos accesoBD;
		accesoBD = ConexionBaseDeDatosJDBC.getInstance();

		eventos = accesoBD.listaEventos();

		cbEventos.removeAllItems();
		for (Evento e : eventos) {
			if(e.getParticipantes().contains(nUsuario)) {
				cbEventos.addItem(e);
			}
		}
	}

	public void actualizarListaParticipantes(int idEv) {
		ConexionConBaseDeDatos accesoBD;
		accesoBD = ConexionBaseDeDatosJDBC.getInstance();
		participantes = accesoBD.listaParticipantesDeUnEvento(idEv);
		
		lmParticipantesEventoSeleccionado.removeAllElements();
		
		for (String s : participantes) {
			lmParticipantesEventoSeleccionado.addElement(s);
		}
		
	}
	
	public void controladorEliminarEvento(CntrlEliminarEvento c) {
		btEliminarEvento.addActionListener(c);
	}

	public void controladorParticiparEvento(CntrlParticiparEvento c) {
		btParticipar.addActionListener(c);
	}

	public void controladorCancelarParticipacion(CntrlCancelarParticipacion c) {
		btCancelarParticipacion.addActionListener(c);
	}

	public void controladorExpulsarParticipante(CntrlExpulsarParticipantes c) {
		btExpulsarParticipante.addActionListener(c);
	}
	
	public void controladorActualizar(CntrlActualizar c) {
		btActualizar.addActionListener(c);
	}
	
	public List<String> getParticipantesSeleccionados(){
		return lstParticipantesEventoSeleccionado.getSelectedValuesList();
	}
}
