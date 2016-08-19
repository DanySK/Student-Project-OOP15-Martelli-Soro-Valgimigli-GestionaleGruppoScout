package view.excursion_manager.utility;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import control.InfoProjectImpl;
import control.myUtil.MyOptional;
import extra.sito.ExcursionOnline;
import model.escursioni.EventiDiZona;
import model.escursioni.Excursion;
import model.escursioni.GemellaggiImpl;
import view.gui_utility.EditableElementScrollPanelImpl;
import view.gui_utility.MyJFrameSingletonImpl;
import view.gui_utility.MyJPanelImpl;
import view.gui_utility.SearchElementJDialog;
import view.gui_utility.WarningNotice;
import view.gui_utility.SearchElementJDialog.SearchType;

public class ShowEditExcursion extends JDialog {

	private static final long serialVersionUID = 9016334026262612993L;
	private final MyJPanelImpl panel = new MyJPanelImpl(new BorderLayout());
	private final MyJPanelImpl panelCenterOuter = new MyJPanelImpl(new BorderLayout());
	private final MyJPanelImpl panelCenter = new MyJPanelImpl(new GridLayout(0, 2));
	private final MyJPanelImpl panelBot = new MyJPanelImpl(new FlowLayout(FlowLayout.RIGHT));
	private final MyJPanelImpl panelEdit = new MyJPanelImpl(new GridLayout(0, 1));
	private final static int FONTSIZE = 15;
	private final static int FONTBUTTONBIG = 13;
	private final static int FONTSIZEBUTTON = 10;
	private final Excursion exc;
	/**
	 * Class that allow to see and edit an excursion
	 * @param exc
	 * @param parent EditableMemberPanelImpl that called this JDialog
	 */
	public ShowEditExcursion(final Excursion exc, final EditableElementScrollPanelImpl<Excursion> parent) {
		super();
		this.exc = exc;
		panel.add(panelCenterOuter, BorderLayout.CENTER);
		panelCenterOuter.add(panelCenter, BorderLayout.CENTER);
		updateExcursion();
		panelBot.add(panelBot.createButton("Annulla", FONTBUTTONBIG, e -> {
			this.dispose();
		}));
		panelBot.add(panelBot.createButton("Ok", FONTBUTTONBIG, e -> {
			parent.updateMember();
			this.dispose();
		}));
		panel.add(panelBot, BorderLayout.SOUTH);
		panelCenterOuter.add(panelEdit, BorderLayout.EAST);
		this.add(panel);

	}

	private final void updateExcursion() {
		final Map<String, List<String>> info = (new InfoProjectImpl()).getExcursionSpacificalInfo(exc);
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				panelCenter.removeAll();
				panelEdit.removeAll();
				panelCenter.add(panelCenter.createJLabel("Nome: ", FONTSIZE));
				panelCenter.add(panelCenter.createJLabel(info.get("Nome").get(0), FONTSIZE));

				panelCenter.add(panelCenter.createJLabel("Luogo: ", FONTSIZE));
				panelCenter.add(panelCenter.createJLabel(info.get("Dove").get(0), FONTSIZE));

				panelCenter.add(panelCenter.createJLabel("Inizio: ", FONTSIZE));
				panelCenter.add(panelCenter.createJLabel(info.get("Data").get(0), FONTSIZE));

				if (!(exc instanceof ExcursionOnline)) {
					panelCenter.add(panelCenter.createJLabel("Fine: ", FONTSIZE));
					panelCenter.add(panelCenter.createJLabel(info.get("DataFine").get(0), FONTSIZE));
				}

				panelCenter.add(panelCenter.createJLabel("Prezzo: ", FONTSIZE));
				panelCenter.add(panelCenter.createJLabel(info.get("Prezzo").get(0), FONTSIZE));

				if (!(exc instanceof ExcursionOnline)) {
					panelEdit.add(getJButton("Nome"));
					panelEdit.add(getJButton("Dove"));
					panelEdit.add(getJButton("Quando"));
					panelEdit.add(getJButton("Fine"));
					panelEdit.add(getJButton("Prezzo"));
					panelCenter.add(panelCenter.createJLabel("Partecipanti", FONTSIZE));
					panelCenter.add(panelCenter.createButton("vedi", FONTSIZEBUTTON, e -> {
						final JDialog dial = new JDialog();
						final MyJPanelImpl panel = new MyJPanelImpl(new BorderLayout());
						final EditableElementScrollPanelImpl<Excursion> panCenter = new EditableElementScrollPanelImpl<Excursion>(
								EditableElementScrollPanelImpl.Type.EXCPARTECIPANTI, MyOptional.of(exc.getName()));
						panel.add(panCenter);
						dial.add(panel);
						dial.setPreferredSize(MyJFrameSingletonImpl.getInstance().getContenentPane().getSize());
						dial.pack();
						dial.setLocationRelativeTo(MyJFrameSingletonImpl.getInstance());
						dial.setVisible(true);
					}));
					panelEdit.add(panelCenter.createButton("<html>Aggiungi/<br>Rimuovi</html>", FONTSIZEBUTTON, e -> {
						new SearchElementJDialog<>(SearchType.addMemberExc, exc.getName(), MyOptional.empty(), null);
					}));

					if (exc instanceof EventiDiZona || exc instanceof GemellaggiImpl) {
						panelCenter.add(panelCenter.createJLabel("Reparti", FONTSIZE));
						panelCenter.add(panelCenter.createButton("Vedi", FONTSIZEBUTTON, e -> {
							new OtherUnitJDialog(exc, false);
						}));
						panelEdit.add(panelEdit.createButton("<html>Aggiungi/<br>Rimuovi</html>", FONTSIZEBUTTON, e -> {
							new OtherUnitJDialog(exc, true);
						}));

					}

				} else {
					panelEdit.add(panelEdit.createJLabel("", FONTSIZEBUTTON));
					panelEdit.add(panelEdit.createJLabel("", FONTSIZEBUTTON));
					panelEdit.add(panelEdit.createJLabel("", FONTSIZEBUTTON));
					panelEdit.add(panelEdit.createJLabel("", FONTSIZEBUTTON));
					panelCenter.add(panelCenter.createJLabel("Link Evento", FONTSIZE));
					panelCenter.add(panelEdit.createButton("Vedi", FONTSIZEBUTTON, e -> {
						final JDialog dial = new JDialog();
						final MyJPanelImpl pan = new MyJPanelImpl(new BorderLayout());
						final MyJPanelImpl flow = new MyJPanelImpl();
						pan.add(pan.createJTextArea(((ExcursionOnline) exc).getPiccoleOrmeUrl().toExternalForm(), false,
								FONTSIZE), BorderLayout.CENTER);
						flow.add(flow.createButton("OK", t -> {
							dial.dispose();
						}));
						dial.add(pan);
						pan.add(flow, BorderLayout.SOUTH);
						dial.pack();
						dial.setLocationRelativeTo(MyJFrameSingletonImpl.getInstance());
						dial.setVisible(true);

					}));
					panelEdit.add(panelCenter.createButton("<html>Apri nel<br>Browse</html>", FONTSIZEBUTTON, e -> {
						try {
							((ExcursionOnline) exc).openPiccoleOrmeUrl();
						} catch (Exception e1) {
							new WarningNotice(e1.getMessage());
						}
					}));
					panelCenter.add(panelCenter.createJLabel("Link Google Maps", FONTSIZE));
					panelCenter.add(panelEdit.createButton("Vedi", FONTSIZEBUTTON, e -> {
						try {
							final JDialog dial = new JDialog();
							final MyJPanelImpl pan = new MyJPanelImpl(new BorderLayout());
							final MyJPanelImpl flow = new MyJPanelImpl();
							pan.add(pan.createJTextArea(((ExcursionOnline) exc).getMapLink().toExternalForm(), false,
									FONTSIZE), BorderLayout.CENTER);
							flow.add(flow.createButton("OK", t -> {
								dial.dispose();
							}));
							dial.add(pan);
							pan.add(flow, BorderLayout.SOUTH);
							dial.pack();
							dial.setLocationRelativeTo(MyJFrameSingletonImpl.getInstance());
							dial.setVisible(true);
						} catch (Exception y) {
							new WarningNotice(y.getMessage());
						}
					}));
					panelEdit.add(panelCenter.createButton("<html>Apri nel<br>Browse</html>", FONTSIZEBUTTON, e -> {
						try {
							((ExcursionOnline) exc).openMapLink();
						} catch (Exception e1) {
							new WarningNotice(e1.getMessage());
						}
					}));
				}

				panel.validate();
				panel.repaint();

				pack();
				setLocationRelativeTo(MyJFrameSingletonImpl.getInstance());
				setVisible(true);

			}

		});

	}

	private JButton getJButton(final String type) {
		return panelEdit.createButton("Edit", FONTSIZEBUTTON, e -> {
			final JDialog dial = new JDialog();
			final MyJPanelImpl pan = new MyJPanelImpl(new BorderLayout());
			if (type.equals("Nome") || type.equals("Dove") || type.equals("Prezzo")) {
				final JTextField txt = new JTextField();
				pan.add(txt, BorderLayout.CENTER);
				pan.add(pan.createButton("OK", t -> {
					if (!txt.getText().isEmpty()) {
						if (type.equals("Nome")) {
							exc.setName(txt.getText());
						} else if (type.equals("Dove")) {
							exc.setPlace(txt.getText());
						} else if (type.equals("Prezzo")) {
							exc.setPrice(Integer.parseInt(txt.getText()));
						}
					}
					MyJFrameSingletonImpl.getInstance().setNeedToSave();
					dial.dispose();
					updateExcursion();
				}), BorderLayout.SOUTH);
			} else {
				final MyJPanelImpl date = new MyJPanelImpl(new GridLayout(1, 0));
				final JTextField mm = new JTextField();
				final JTextField aa = new JTextField();
				final JTextField gg = new JTextField();
				date.add(date.createJLabel("Giorno: ", FONTSIZE));
				date.add(gg);
				date.add(date.createJLabel("Mese: ", FONTSIZE));
				date.add(mm);
				date.add(date.createJLabel("Anno: ", FONTSIZE));
				date.add(aa);
				pan.add(date, BorderLayout.CENTER);

				pan.add(pan.createButton("OK", t -> {
					try {
						if (type.equals("Fine")) {
							if (!gg.getText().isEmpty() && !aa.getText().isEmpty() && !mm.getText().isEmpty()) {
								exc.setDateEnd((LocalDate.of(Integer.parseInt(aa.getText()),
										Integer.parseInt(mm.getText()), Integer.parseInt(gg.getText()))));
							}

						} else {
							if (!gg.getText().isEmpty() && !aa.getText().isEmpty() && !mm.getText().isEmpty()) {
								exc.setDateStart(LocalDate.of(Integer.parseInt(aa.getText()),
										Integer.parseInt(mm.getText()), Integer.parseInt(gg.getText())));
							}
						}
					} catch (Exception E) {
						new WarningNotice(E.getMessage());
					}
					MyJFrameSingletonImpl.getInstance().setNeedToSave();
					dial.dispose();
					updateExcursion();

				}), BorderLayout.SOUTH);

			}

			dial.add(pan);
			dial.pack();
			dial.setLocationRelativeTo(MyJFrameSingletonImpl.getInstance());
			dial.setVisible(true);
		});
	}

}
