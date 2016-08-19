package view.gui_utility;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import control.InfoProjectImpl;
import control.Unit;
import control.myUtil.Pair;
import model.reparto.Squadron;
import control.myUtil.MyOptional;
import view.gui_utility.SearchElementJDialog.SearchType;

/**
 * 
 * @author Giovanni Martelli
 *
 */
public class EditableInfoPanelImpl extends MyJPanelImpl {

	private static final long serialVersionUID = 3525477272751960285L;

	/*
	 * Enum contenente le voci per le info riguardanti la squadriglia
	 */
	enum ChefLabel {
		Nome("Nome: ", ""), Sesso("Sesso: ", ""), Capo("Capo: ", ""), Vice("Vice: ", ""), Trice("Trice: ",
				""), Membri("Numero di membri: ", "");
		private final String name;
		private String value;

		public String getName() {
			return this.name;
		}

		public String getValue() {
			return this.value;
		}

		public void setValue(final String valueParam) {
			this.value = valueParam;
		}

		ChefLabel(final String nameParam, final String valueParam) {
			this.name = nameParam;
			this.value = valueParam;
		}
	}

	private final String squadName;
	private final boolean editable;
	private final int fontSizeButton;
	private final int fontSizeLabel;
	private List<Pair<String, String>> list;
	private final MyJPanelImpl panelSx;
	private final MyJPanelImpl panelDx;
	private final MyJPanelImpl panelBot;
	private final Unit unit;

	/**
	 * Creates a JPanel for info
	 * 
	 * @param squad
	 * @param isManager
	 * @param fontLabel
	 * @param fontButton
	 */
	public EditableInfoPanelImpl(final String squad, final boolean isManager, final int fontLabel,
			final int fontButton) {
		super(new BorderLayout());
		this.editable = isManager;
		this.fontSizeLabel = fontLabel;
		this.fontSizeButton = fontButton;
		this.unit = MyJFrameSingletonImpl.getInstance().getUnit();
		this.list = new ArrayList<>();
		panelSx = new MyJPanelImpl(new GridLayout(0, 2));
		panelDx = new MyJPanelImpl(new GridLayout(0, 1));
		panelBot = new MyJPanelImpl(new GridLayout(0, 3));
		this.squadName = squad;
		final Squadron squadImpl = unit.getContainers().findSquadron(squadName);

		/*
		 * Aggiungo il panello con tutte le info
		 */
		this.updateInfo();
		/*
		 * Aggiung i bottoni Cancelleria, Batteria e Cassa
		 * 
		 */
		list.add(new Pair<>("Cancelleria", squadImpl.getNoteCancelleria()));
		list.add(new Pair<>("Batteria", squadImpl.getNoteBatteria()));
		list.add(new Pair<>("Cassa", squadImpl.getNoteCassa()));
		list.stream().forEach(e -> {
			panelBot.add(createButton("Note " + e.getX().toLowerCase(), k -> {
				final JDialog dial = new JDialog();
				final JPanel panel = new JPanel(new BorderLayout());
				final JPanel bot = new JPanel();
				final JTextArea area = createJTextArea("", isManager, fontLabel);
				if (!isManager) {
					area.setText(e.getY());
				}
				panel.add(area);
				if (editable) {
					bot.add(createButton("Ok", t -> {
						final String oldText = e.getY();
						try { /* Utilizzo la reflection */
							if (!area.getText().equals(oldText) && !area.getText().isEmpty()) {
								final Method m = squadImpl.getClass().getDeclaredMethod("setNote" + e.getX(),
										String.class);
								m.invoke(squadImpl,
										area.getText());/*
														 * chiamata al metodo
														 * trovato
														 */
								MyJFrameSingletonImpl.getInstance().setNeedToSave();
							}
							dial.dispose();
						} catch (Exception g) {
							new WarningNotice(g.getMessage());
						}
					}));
					bot.add(createButton("Annulla", t -> {
						dial.dispose();
					}));
				} else {
					bot.add(createButton("Ok", g -> {
						dial.dispose();
					}));
				}
				panel.add(createJLabel(
						"<html><U>" + (!editable ? "Note " : "Modifica note ") + e.getX().toLowerCase() + "</U></html>",
						fontLabel), BorderLayout.NORTH);
				panel.add(bot, BorderLayout.SOUTH);
				dial.add(panel);
				dial.pack();
				dial.setLocationRelativeTo(null);
				dial.setVisible(true);
			}));
		});
		for (final Component i : panelBot.getComponents()) {
			i.setFont(new Font("Aria", Font.ITALIC, fontButton + 3));
		}

	}

	private boolean isUsefull(final String param) {
		/* controllo se è "utile" il campo richiesto */
		if (!editable) {// false per Overview-->solo Nome

			return !param.equals("Nome: ");
		} else {// false per Manager-->Nome,Numero di membri e sesso
			if (param.equals("Nome: ") || param.equals("Numero di membri: ") || param.equals("Sesso: ")) {
				return false;
			}
		}
		return true;
	}

	private JButton instanceJButton(final Pair<String, String> i) {
		/*
		 * nel caso di pannello per mostrare solo informazioni
		 */
		if (!editable) {
			final JButton ret = createButton("info", this.getBackground(),
					new Font("Aria", Font.ITALIC, fontSizeButton), e -> {
						final JDialog dial = new JDialog();
						final JPanel panel = new JPanel(new BorderLayout());
						final JPanel bot = new JPanel();
						final JTextArea area = createJTextArea("", false, 18);
						final String k = i.getY();
						final List<Pair<String, String>> areaText = (new InfoProjectImpl())
								.getMemberSpecificalInfo(unit.getContainers().getMember(k.substring(0, k.indexOf(32)),
										k.substring(k.indexOf(32) + 1)));

						areaText.forEach(t -> {
							area.append(t.getX() + ": " + t.getY() + System.lineSeparator());// per
																								// ogni
																								// info
																								// aggiungo
																								// una
																								// riga
																								// alla
																								// areaText
						});
						panel.add(area, BorderLayout.CENTER);
						bot.add(createButton("Ok", g -> {
							dial.dispose();
						}));
						panel.add(bot, BorderLayout.SOUTH);
						dial.add(panel);
						dial.pack();
						dial.setLocationRelativeTo(null);
						dial.setVisible(true);
					});
			if (i.getY().equals("non settato")) {
				ret.setEnabled(false);
			}
			return ret;
		}
		/*
		 * nel caso di pannello per modificare informazioni
		 */
		return createButton("edit", UIManager.getColor(new JButton()), new Font("Aria", Font.ITALIC, fontSizeButton),
				e -> {
					if (!i.getY().equals("non settato")) {
						/*
						 * Se diverso da "non settato" avviso l'utente che
						 * potrebbe perdere dei dati
						 */
						new WarningNotice("<html><U>ATTENZIONE!!</U><br>"
								+ "La carica è già stata assegnata.<br>Continuando e salvando verrà riassegnata.</html>");
					}
					new SearchElementJDialog<>(SearchType.AssignCharge, squadName, MyOptional.of(i.getX()), this);

				});

	}

	public final void updateInfo() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				Arrays.asList(ChefLabel.values()).stream().forEach(e -> {
					e.setValue("");
				});
				panelSx.removeAll();
				panelDx.removeAll();
				removeAll();
				list.clear();
				/* instanzio lista semicompleta */
				list = (new InfoProjectImpl()).getSquadronSpecificInfo(squadName, unit.getContainers());
				/* riempio la enum */
				Arrays.asList(ChefLabel.values()).stream().forEach(i -> {
					i.setValue(list.stream().filter(e -> e.getX().equals(i.getName())).findFirst()
							.orElse(new Pair<>(i.getName(), "non settato")).getY());
				});
				/*
				 * pulisco la lista, ci ricopio dentro l'enum e poi
				 * ri-inizializzo l'enum
				 */
				list.clear();
				Arrays.asList(ChefLabel.values()).stream().forEach(e -> {
					list.add(new Pair<>(e.getName(), e.getValue()));
				});
				Arrays.asList(ChefLabel.values()).stream().forEach(e -> {
					e.setValue("");
				});
				/* aggiungo elementi al pannello */
				list.stream().forEach(i -> {
					if (isUsefull(i.getX())) {
						panelSx.add(createJLabel(i.getX(), fontSizeLabel));
						panelSx.add(createJLabel(i.getY(), fontSizeLabel));
						if (i.getX().equals("Capo: ") || i.getX().equals("Vice: ") || i.getX().equals("Trice: ")) {
							panelDx.add(instanceJButton(i));
						} else {
							panelDx.add(createJLabel("", fontSizeLabel));
						}
					}
				});

				add(panelSx, BorderLayout.CENTER);
				add(panelDx, BorderLayout.EAST);
				add(panelBot, BorderLayout.SOUTH);
				repaint();
				validate();
			}
		});
	}
}
