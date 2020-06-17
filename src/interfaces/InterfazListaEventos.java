package interfaces;

import java.awt.GridLayout;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import prEventually.*;

public class InterfazListaEventos extends JPanel{
	
	List<Evento> eventos;
	List<Usuario> usuarios;
	
	JLabel lbSlcEvento;
	JComboBox<Evento> cbEventos;
	JPanel pnSlcEvento;
	
	JLabel lbParticipantesEventoSeleccionado;
	DefaultListModel<Usuario> lmParticipantesEventoSeleccionado;
	JList<Usuario> lstParticipantesEventoSeleccionado;
	JPanel pnListaParticipantes;
	
	JButton btParticipar;
	JButton btCancelarParticipacion;
	JButton btEliminarEvento;
	JButton btExpulsarParticipante;
	JPanel pnBotones;
	
	public InterfazListaEventos (List<Evento> le, List<Usuario> lu) {
		eventos = le;
		usuarios = lu;

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

		// panel de listas de jugadores
		pnListaParticipantes = new JPanel();
		pnListaParticipantes.setLayout(new GridLayout(2, 1));
		lbParticipantesEventoSeleccionado = new JLabel("Participantes del evento seleccionado");
		lmParticipantesEventoSeleccionado = new DefaultListModel<>();
		Evento evSel = (Evento) cbEventos.getItemAt(0);
		List<Usuario> partic = evSel.getParticipantes();
		for (Usuario u : partic) {
			lmParticipantesEventoSeleccionado.addElement(u);
		}
		lstParticipantesEventoSeleccionado = new JList<Usuario>(lmParticipantesEventoSeleccionado);
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
	}
	
}
