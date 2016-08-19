package view.gestione_eventi.utility;

import java.awt.BorderLayout;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import control.Unit;
import control.ProjectFactoryImpl;
import control.myUtil.MyOptional;
import model.escursioni.Excursion;
import model.exception.IllegalDateException;
import view.general_utility.WarningNotice;

import view.gui_utility.MyJFrameSingletonImpl;
import view.gui_utility.MyJPanel;
import view.gui_utility.MyJPanelImpl;
import view.gestione_eventi.SquadronExcursionPane;
import view.gestione_eventi.UnitExcursionPane;;
/**
 * Class used to display a JDialog where user can add an excursion
 * @author Giovanni Martelli
 *
 */
public class AddExcursionJDialog extends JDialog {
	public enum TypeExcursion {
		Campo, Gemellaggio, Evento_di_Zona, Uscita, Uscita_Squadriglia;

	}

	private static final long serialVersionUID = -6908793366965929992L;
	private final static int FONTSIZE = 15;
	private final TypeExcursion type;
	private final MyJPanelImpl panel = new MyJPanelImpl(new BorderLayout());
	private final MyJPanelImpl panelInter = new MyJPanelImpl(new GridLayout(0, 2));
	private final MyJPanelImpl panelBot = new MyJPanelImpl(new FlowLayout(FlowLayout.RIGHT));
	private final JTextField nome = new JTextField();
	private final JTextField price = new JTextField();
	private final MyJPanelImpl data = new MyJPanelImpl(new GridLayout(1, 6));
	private final JTextField gg = new JTextField();
	private final JTextField mm = new JTextField();
	private final JTextField aa = new JTextField();
	private final JTextField durata = new JTextField("1");
	private final JTextField ggF = new JTextField();
	private final JTextField mmF = new JTextField();
	private final JTextField aaF = new JTextField();
	private final JTextField location = new JTextField();
	private MyJPanelImpl dataFine = new MyJPanelImpl(new GridLayout(1, 6));
	private final JTextArea area = new JTextArea();
	private boolean perData;
	private List<String> reparti = new ArrayList<>();
	private final Unit unit;
	private LocalDate end;
	private String squadName;

	public AddExcursionJDialog(final TypeExcursion type, final MyOptional<String> squadName, final MyJPanel caller) {
		super();
		/*Se la squadriglia è presente la utilizzo*/
		if (squadName.isPresent()) {
			this.squadName = squadName.get();
		}
		this.type = type;
		this.unit = MyJFrameSingletonImpl.getInstance().getUnit();
		panel.add(panel.createJLabel("Nuovo/a" + type.toString(), FONTSIZE + 2), BorderLayout.NORTH);
		/*Casi comuni a tutte le escursioni
		 * Viene aggiunta una JLabel a sx e un JTextField a destra
		 */
		panelInter.add(panel.createJLabel("Nome: ", FONTSIZE));
		panelInter.add(nome);
		panelInter.add(panel.createJLabel("Prezzo: ", FONTSIZE));
		panelInter.add(price);
		panelInter.add(panel.createJLabel("Luogo: ", FONTSIZE));
		panelInter.add(location);
		panelInter.add(panel.createJLabel("Data Inizio: ", FONTSIZE));
		data.add(panel.createJLabel("giorno", FONTSIZE));
		data.add(gg);
		data.add(panel.createJLabel("mese", FONTSIZE));
		data.add(mm);
		data.add(panel.createJLabel("anno", FONTSIZE));
		data.add(aa);
		panelInter.add(data);
		/*
		 * Tutte le escursioni hanno una data di fine, tranne Uscita
		 * Inserisco un JPanel che permette di scegliere tra l'inserimento della data, oppure quello della durata
		 */
		if (!type.equals(TypeExcursion.Uscita)) {
			panelInter.add(panel.createJLabel("Fine: ", FONTSIZE));
			dataFine = new MyJPanelImpl();
			dataFine.add(panel.createButton("Data", 13, e -> {
				perData = true;
				SwingUtilities.invokeLater(new Runnable() {

					public void run() {
						dataFine.removeAll();
						dataFine.setLayout(new GridLayout(1, 6));
						dataFine.add(panel.createJLabel("giorno", FONTSIZE));
						dataFine.add(ggF);
						dataFine.add(panel.createJLabel("mese", FONTSIZE));
						dataFine.add(mmF);
						dataFine.add(panel.createJLabel("anno", FONTSIZE));
						dataFine.add(aaF);
						dataFine.validate();
						dataFine.repaint();
					}
				});
			}));
			dataFine.add(panel.createButton("Durata", 13, e -> {
				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {
						dataFine.removeAll();
						dataFine.add(durata);
						dataFine.validate();
						dataFine.repaint();

					}
				});
			}));
			panelInter.add(dataFine);
		}

		if (type.equals(TypeExcursion.Gemellaggio) || type.equals(TypeExcursion.Evento_di_Zona)) {
			panelInter.add(panel.createJLabel("Altri reparti", FONTSIZE));
			panelInter.add(panel.createButton("Aggiungi", 12, o -> {
				final JDialog dial = new JDialog();
				final MyJPanelImpl pan = new MyJPanelImpl(new BorderLayout());
				final MyJPanelImpl panN = new MyJPanelImpl(new GridLayout(2, 1));
				final MyJPanelImpl panS = new MyJPanelImpl(new FlowLayout(FlowLayout.RIGHT));
				panN.add(pan.createJLabel("<html><U>Aggiungi Reparti</U><html>", FONTSIZE + 2));
				panN.add(pan.createJLabel("<html>Aggiungere i nomi dei reparti<br>"
						+ "separando un reparto dall'altro con il tasto \"INVIO\"</html>", FONTSIZE));
				pan.add(panN, BorderLayout.NORTH);

				area.setPreferredSize(new Dimension(area.getWidth(), this.getHeight()));
				pan.add(area, BorderLayout.CENTER);
				panS.add(pan.createButton("Annulla", r -> {
					dial.dispose();
				}));
				panS.add(pan.createButton("Aggiungi", r -> {
					reparti = Arrays.asList(area.getText().split(System.lineSeparator()));
					dial.dispose();
				}));
				pan.add(panS, BorderLayout.SOUTH);
				dial.add(pan);
				dial.pack();
				dial.setLocationRelativeTo(this);
				dial.setVisible(true);

			}));
		}

		// JButton in basso

		panelBot.add(panelBot.createButton("Annulla", 15, e -> {
			this.dispose();
		}));
		panelBot.add(panelBot.createButton("Aggiungi", 15, e -> {
			try {
				final Excursion ex = getExcursion();
				unit.addExcursion(ex);
				if (type.equals(TypeExcursion.Uscita_Squadriglia)) {
					((SquadronExcursionPane) caller).updateExcursion();
				} else {
					((UnitExcursionPane) caller).updateExcursion();
				}
				MyJFrameSingletonImpl.getInstance().setNeedToSave();
				this.dispose();
				MyJFrameSingletonImpl.getInstance().setNeedToSave();
			} catch (Exception l) {
				new WarningNotice(l.getMessage());

			}

		}));

		panel.add(panelInter, BorderLayout.CENTER);
		panel.add(panelBot, BorderLayout.SOUTH);
		this.add(panel);
		this.pack();
		this.setLocationRelativeTo(MyJFrameSingletonImpl.getInstance());
		this.setVisible(true);

	}
	
	private final Excursion getExcursion() throws IllegalDateException {

		Excursion exc;
		final LocalDate start = LocalDate.of(Integer.parseInt(aa.getText()), Integer.parseInt(mm.getText()),
				Integer.parseInt(gg.getText()));

		if (type.equals(TypeExcursion.Uscita)) {//caso Uscita
			exc = ProjectFactoryImpl.getStdExcursion(start, unit.getReparto(), nome.getText());
		} else if (perData) {//se è stato scelto di utilizzare la data

			end = LocalDate.of(Integer.parseInt(aaF.getText()), Integer.parseInt(mmF.getText()),
					Integer.parseInt(ggF.getText()));
			if (type.equals(TypeExcursion.Campo)) {
				exc = ProjectFactoryImpl.getCamp(start, end, unit.getReparto(), nome.getText());
			} else if (type.equals(TypeExcursion.Gemellaggio)) {
				exc = ProjectFactoryImpl.getEventTwoUnit(start, end, unit.getReparto(), nome.getText(), reparti);
			} else if (type.equals(TypeExcursion.Evento_di_Zona)) {
				exc = ProjectFactoryImpl.getLocalEvent(start, end, unit.getReparto(), nome.getText(), reparti);

			} else {// UscitaSquad
				exc = ProjectFactoryImpl.getSqExcursion(start, end, unit.getContainers().findSquadron(squadName),
						nome.getText());
			}

		}

		else {//se è stato scelto di utilizzare la durata
			if (type.equals(TypeExcursion.Campo)) {
				exc = ProjectFactoryImpl.getCamp(start, Integer.parseInt(durata.getText()), unit.getReparto(),
						nome.getText());
			} else if (type.equals(TypeExcursion.Gemellaggio)) {
				exc = ProjectFactoryImpl.getEventMoreUnit(start, Integer.parseInt(durata.getText()), unit.getReparto(),
						nome.getText(), reparti);
			} else if (type.equals(TypeExcursion.Evento_di_Zona)) {
				exc = ProjectFactoryImpl.getLocalEvent(start, Integer.parseInt(durata.getText()), unit.getReparto(),
						nome.getText(), reparti);
			} else {// UscitaSquad
				exc = ProjectFactoryImpl.getSqExcursion(start, Integer.parseInt(durata.getText()),
						unit.getContainers().findSquadron(squadName), nome.getText());
			}

		}
		exc.setPlace(location.getText());
		exc.setPrice(Integer.parseInt(price.getText()));
		return exc;

	}
}
