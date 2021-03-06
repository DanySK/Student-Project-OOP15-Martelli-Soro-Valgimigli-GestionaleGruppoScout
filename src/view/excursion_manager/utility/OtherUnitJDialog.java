package view.excursion_manager.utility;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.Arrays;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import model.escursioni.EventiDiZona;
import model.escursioni.Excursion;
import model.escursioni.Gemellaggi;
import model.exception.ObjectAlreadyContainedException;
import view.gui_utility.MyJFrameSingletonImpl;
import view.gui_utility.MyJPanelImpl;
import view.gui_utility.WarningNotice;

public class OtherUnitJDialog extends JDialog {

	private static final long serialVersionUID = 1710237645233014036L;
	private final static int FONTSIZE = 15;
/**
 * Class that allows to see or add other unit to an excursion of type Gemellaggi or EventiDiZona
 * @param exc excursion to be modified
 * @param editable false if only to see, true if also to add
 * 
 */
	public OtherUnitJDialog(final Excursion exc, final boolean editable) {
		super();
		final MyJPanelImpl panel = new MyJPanelImpl(new BorderLayout());
		final MyJPanelImpl inContainer = new MyJPanelImpl(new GridLayout(2, 1));
		final MyJPanelImpl bot = new MyJPanelImpl(new FlowLayout(FlowLayout.RIGHT));
		final JTextArea area = new JTextArea();
		area.setEditable(editable);

		final JScrollPane scroll = new JScrollPane(area);
		if (exc instanceof Gemellaggi) {
			((Gemellaggi) exc).getOtherUnits().stream().forEach(e -> {
				area.append(e);
			});
		} else {
			((EventiDiZona) exc).getOtherUnits().stream().forEach(e -> {
				area.append(e);
			});
		}
		if (editable) {
			inContainer.add(inContainer.createJLabel(
					"<html>Aggiungi altri reparti," + "<br>separandoli con il tasto \"INVIO\"", FONTSIZE));
		} else {
			inContainer.add(inContainer.createJLabel("Reparti che parteciperanno all'escursione", FONTSIZE));
		}
		inContainer.add(scroll);
		if (editable) {
			bot.add(bot.createButton("Annulla", FONTSIZE, e -> {
				dispose();
			}));
		}
		bot.add(bot.createButton("OK", FONTSIZE, e -> {
			if (editable) {
				if (exc instanceof Gemellaggi) {
					((Gemellaggi) exc).clearUnitsList();
					Arrays.asList(area.getText().split(System.lineSeparator())).stream().forEach(u -> {
						try {
							((Gemellaggi) exc).addOtherUnit(u);
						} catch (ObjectAlreadyContainedException e1) {
							new WarningNotice(e1.getMessage());
						}
					});

				} else {
					((EventiDiZona) exc).clearUnitsList();
					Arrays.asList(area.getText().split(System.lineSeparator())).stream().forEach(u -> {
						try {
							((EventiDiZona) exc).addOtherUnit(u);
						} catch (ObjectAlreadyContainedException e1) {
							new WarningNotice(e1.getMessage());
						}
					});
				}
				dispose();
			} else {
				dispose();
			}
		}));

		panel.add(inContainer, BorderLayout.CENTER);
		panel.add(bot, BorderLayout.SOUTH);
		this.add(panel);
		this.pack();
		this.setLocationRelativeTo(MyJFrameSingletonImpl.getInstance());
		this.setVisible(true);

	}

}
