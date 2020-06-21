package interfaces;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controladores.CntrlModificarEvento;
import main.Eventually;

public class InterfazModificarEvento extends JPanel {

	private static final long serialVersionUID = 1L;

	private JButton bModificar;
	private JButton bVolver;
	private JLabel lbNombre;
	private JTextField tfFecha;
	private JTextField tfLugar;
	private TextPrompt phFecha;
	private TextPrompt phLugar;

	public InterfazModificarEvento() {

		bModificar = new JButton("Modificar");
		bVolver = new JButton("Volver");
		lbNombre = new JLabel("");
		tfFecha = new JTextField(20);
		tfLugar = new JTextField(20);

		phFecha = new TextPrompt("Nueva fecha del evento", tfFecha);
		phFecha.changeAlpha(0.75f);
		phFecha.changeStyle(Font.ITALIC);
		phLugar = new TextPrompt("Nuevo lugar del evento", tfLugar);
		phLugar.changeAlpha(0.75f);
		phLugar.changeStyle(Font.ITALIC);

		setLayout(new BorderLayout());

		JPanel botones = new JPanel();

		botones.setLayout(new GridLayout(1, 2));
		botones.add(bModificar);
		botones.add(bVolver);

		JPanel campos = new JPanel();
		campos.setLayout(new GridLayout(3, 1));
		campos.add(lbNombre);
		campos.add(tfFecha);
		campos.add(tfLugar);

		add(botones, BorderLayout.SOUTH);
		add(campos, BorderLayout.CENTER);

		bVolver.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tfFecha.setText(null);
				tfLugar.setText(null);

				JPanel cardParent = (JPanel) InterfazModificarEvento.this.getParent();
				CardLayout ccl = (CardLayout) (cardParent.getLayout());
				ccl.show(cardParent, Eventually.PANELPRINCIPAL);
				Eventually.setVentanaTamaño(980, 600);
			}
		});

	}

	public JLabel getNombre() {
		return lbNombre;
	}

	public JTextField getFecha() {
		return tfFecha;
	}

	public JTextField getLugar() {
		return tfLugar;
	}

	public void controladorModificar(CntrlModificarEvento c) {
		bModificar.addActionListener(c);
	}

}
