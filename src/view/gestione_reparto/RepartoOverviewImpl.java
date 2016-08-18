package view.gestione_reparto;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import control.ProjectFactoryImpl;
import control.Unit;
import control.myUtil.MyOptional;
import model.Member;
import view.general_utility.WarningNotice;
import view.gestione_reparto.utility.AiutantiJDialog;
import view.gestione_reparto.utility.PanelCapiReparto;
import view.gui_utility.EditableMemberPanelImpl;
import view.gui_utility.EditableMemberPanelImpl.Type;
import view.gui_utility.MyJFrameSingletonImpl;
import view.gui_utility.MyJPanelImpl;

public class RepartoOverviewImpl {
	private final Unit unit;

	public RepartoOverviewImpl() {
		this.unit = MyJFrameSingletonImpl.getInstance().getUnit();
	}

	public class RepartoOverviewImplPane extends MyJPanelImpl {

		private static final long serialVersionUID = -4533965002766818616L;
		private final MyJPanelImpl panelLeft;
		private final MyJPanelImpl panelRight;
		private final MyJPanelImpl panelSxDx;
		private final MyJPanelImpl panelBot;
		private final MyJPanelImpl panelCenter;
		private final static int FONTSIZE = 19;
		private final static int FONTSIZELABEL = 18;
		private final static int FONTSIZEBUTTON = 12;
		private final EditableMemberPanelImpl<Member> pnMem = new EditableMemberPanelImpl<>(Type.OVERVIEWREP,
				MyOptional.empty());

		public RepartoOverviewImplPane() {
			super(new BorderLayout());

			this.panelCenter = new MyJPanelImpl(new GridLayout(2, 1));
			this.panelSxDx = new MyJPanelImpl(new BorderLayout());
			this.panelLeft = new MyJPanelImpl(new GridLayout(0, 2));
			this.panelRight = new MyJPanelImpl(new GridLayout(0, 1));
			this.panelBot = new MyJPanelImpl();
			updateAll();

		}

		private void dialogChef(final boolean sex) {
			final JDialog dial = new JDialog();
			final MyJPanelImpl outerPanel = new MyJPanelImpl(new BorderLayout());
			final MyJPanelImpl panelBot = new MyJPanelImpl();
			final PanelCapiReparto panCapo = new PanelCapiReparto("Nuovo Capo Reparto");
			outerPanel.add(panCapo, BorderLayout.CENTER);
			panelBot.add(createButton("OK", e -> {
				try {
					if (sex) {
						unit.getReparto().setCapoM(ProjectFactoryImpl.getLeaderM(panCapo.getNome(),
								panCapo.getSurname(), panCapo.getDate(), panCapo.getPhone()));
					} else {
						unit.getReparto().setCapoF(ProjectFactoryImpl.getLeaderF(panCapo.getNome(),
								panCapo.getSurname(), panCapo.getDate(), panCapo.getPhone()));
					}
					dial.dispose();
					updateAll();
				} catch (Exception f) {
					new WarningNotice(f.getMessage());
				}
			}));
			panelBot.add(createButton("Annulla", e -> {
				dial.dispose();
			}));
			outerPanel.add(panelBot, BorderLayout.SOUTH);
			dial.add(outerPanel);
			dial.pack();
			dial.setLocationRelativeTo(MyJFrameSingletonImpl.getInstance());
			dial.setVisible(true);
		}

		private void updateAll() {
			SwingUtilities.invokeLater(new Runnable() {

				@Override
				public void run() {

					panelCenter.removeAll();
					panelSxDx.removeAll();
					panelLeft.removeAll();
					panelRight.removeAll();
					panelBot.removeAll();
					panelLeft.add(createJLabel("Capo Maschio", FONTSIZE));
					panelLeft.add(createJLabel(
							unit.getReparto().getCapoM().getName() + " " + unit.getReparto().getCapoM().getSurname(),
							FONTSIZE));
					panelRight.add(createButton("cambia", FONTSIZEBUTTON, e -> {
						dialogChef(true);
					}));
					panelRight.add(createButton("edit", FONTSIZEBUTTON, e -> {
						final JDialog dial = new JDialog();
						final MyJPanelImpl panel = new MyJPanelImpl(new GridLayout(2, 1));
						final MyJPanelImpl outer = new MyJPanelImpl(new BorderLayout());
						final MyJPanelImpl bot = new MyJPanelImpl(new FlowLayout(FlowLayout.RIGHT));
						panel.add(createJLabel("<html><U>Nuovo numero di telefono</U></html>", FONTSIZELABEL));
						final JTextField field = new JTextField();
						panel.add(field);
						bot.add(createButton("Salva", p -> {
							try {
								MyJFrameSingletonImpl.getInstance().getUnit().getReparto().getCapoM()
										.setPhoneNumber(field.getText());
								dial.dispose();
								MyJFrameSingletonImpl.getInstance().setNeedToSave();
								updateAll();
							} catch (Exception kk) {
								new WarningNotice(kk.getMessage());
							}
						}));
						bot.add(createButton("Annulla", k -> {
							dial.dispose();
						}));
						outer.add(panel, BorderLayout.CENTER);
						outer.add(bot, BorderLayout.SOUTH);
						dial.add(outer);
						dial.pack();
						dial.setLocationRelativeTo(MyJFrameSingletonImpl.getInstance());
						dial.setVisible(true);

					}));
					panelLeft.add(createJLabel("", FONTSIZE));
					panelLeft.add(createJLabel("Tel: " + unit.getReparto().getCapoM().getPhoneNumber(), FONTSIZE));

					panelLeft.add(createJLabel("Capo Femmina", FONTSIZE));
					panelLeft.add(createJLabel(
							unit.getReparto().getCapoF().getName() + " " + unit.getReparto().getCapoF().getSurname(),
							FONTSIZE));
					panelRight.add(createButton("cambia", FONTSIZEBUTTON, e -> {
						dialogChef(false);
					}));
					panelRight.add(createButton("edit", FONTSIZEBUTTON, e -> {
						final JDialog dial = new JDialog();
						final MyJPanelImpl panel = new MyJPanelImpl(new GridLayout(2, 1));
						final MyJPanelImpl outer = new MyJPanelImpl(new BorderLayout());
						final MyJPanelImpl bot = new MyJPanelImpl(new FlowLayout(FlowLayout.RIGHT));
						panel.add(createJLabel("<html><U>Nuovo numero di telefono</U></html>", FONTSIZELABEL));
						final JTextField field = new JTextField();
						panel.add(field);
						bot.add(createButton("Salva", p -> {
							try {
								MyJFrameSingletonImpl.getInstance().getUnit().getReparto().getCapoF()
										.setPhoneNumber(field.getText());
								dial.dispose();
								MyJFrameSingletonImpl.getInstance().setNeedToSave();
								updateAll();
							} catch (Exception kk) {
								new WarningNotice(kk.getMessage());
							}
						}));
						bot.add(createButton("Annulla", k -> {
							dial.dispose();
						}));
						outer.add(panel, BorderLayout.CENTER);
						outer.add(bot, BorderLayout.SOUTH);
						dial.add(outer);
						dial.pack();
						dial.setLocationRelativeTo(MyJFrameSingletonImpl.getInstance());
						dial.setVisible(true);

					}));
					panelLeft.add(createJLabel("", FONTSIZE));
					panelLeft.add(createJLabel("Tel: " + unit.getReparto().getCapoF().getPhoneNumber(), FONTSIZE));

					panelLeft.add(createJLabel("Staff: ", FONTSIZE));
					panelLeft.add(createJLabel(unit.getReparto().getAiutanti().size() + " aiutanti", FONTSIZE));
					panelRight.add(createButton("edit", FONTSIZEBUTTON, e -> {
						new AiutantiJDialog();
					}));

					panelBot.add(createButton("<html>Crea<br>Squadriglia</html>", FONTSIZEBUTTON, e -> {
						final JDialog dial = new JDialog();
						final MyJPanelImpl pan = new MyJPanelImpl(new BorderLayout());
						final MyJPanelImpl info = new MyJPanelImpl(new GridLayout(2, 2));
						final MyJPanelImpl button = new MyJPanelImpl();
						info.add(createJLabel("Nome: ", FONTSIZE));
						final JTextField nome = new JTextField();
						info.add(nome);
						final JRadioButton sexM = new JRadioButton("Maschi");
						final JRadioButton sexF = new JRadioButton("Femmine");
						final ButtonGroup sex = new ButtonGroup();
						sex.add(sexM);
						sex.add(sexF);
						final MyJPanelImpl tmp = new MyJPanelImpl();
						tmp.add(sexM);
						tmp.add(sexF);
						info.add(createJLabel("Sesso: ", FONTSIZE));
						info.add(tmp);
						button.add(createButton("Annulla", k -> {
							dial.dispose();
						}));
						button.add(createButton("Aggiungi", k -> {
							try {
								unit.createSq(ProjectFactoryImpl.getSquadron(nome.getText(), sexM.isSelected()));
								MyJFrameSingletonImpl.getInstance().setNeedToSave();
								((GestioneRepartoMain) MyJFrameSingletonImpl.getInstance().getContenentPane())
										.addSquadToJTree(MyJFrameSingletonImpl.getInstance().getUnit().getContainers()
												.findSquadron(nome.getText()));
								dial.dispose();
							} catch (Exception f) {
								new WarningNotice(f.getMessage());
							}
						}));
						pan.add(createJLabel("<html><U>Nuova squadriglia</U></html>", FONTSIZE));
						pan.add(info, BorderLayout.CENTER);
						pan.add(button, BorderLayout.SOUTH);
						dial.add(pan);
						dial.pack();
						dial.setLocationRelativeTo(MyJFrameSingletonImpl.getInstance());
						dial.setVisible(true);

					}));
					panelBot.add(createButton("<html>Rimuovi<br>Squadriglia</html>", FONTSIZEBUTTON, e -> {
						final JDialog dial = new JDialog();
						final MyJPanelImpl panel = new MyJPanelImpl(new BorderLayout());
						final MyJPanelImpl panelBot = new MyJPanelImpl(new FlowLayout(FlowLayout.RIGHT));
						final JComboBox<String> squad = new JComboBox<>();
						MyJFrameSingletonImpl.getInstance().getUnit().getContainers().getSquadrons().stream()
								.forEach(t -> squad.addItem(t.getNome()));
						panel.add(squad, BorderLayout.CENTER);
						panelBot.add(createButton("Elimina", t -> {
							try {
								unit.removeSq(unit.getContainers().findSquadron((String) squad.getSelectedItem()));
								((GestioneRepartoMain) MyJFrameSingletonImpl.getInstance().getContenentPane())
										.removeSquadToJTree((String) squad.getSelectedItem());
								MyJFrameSingletonImpl.getInstance().setNeedToSave();
								dial.dispose();
							} catch (Exception f) {
								new WarningNotice(f.getMessage());
							}

						}));
						panelBot.add(createButton("Annulla", t -> {
							dial.dispose();
						}));
						panel.add(createJLabel("<html><U>Rimuovi squadriglia</U></html>", FONTSIZE),
								BorderLayout.NORTH);
						panel.add(panelBot, BorderLayout.SOUTH);
						dial.add(panel);
						dial.pack();
						dial.setLocationRelativeTo(MyJFrameSingletonImpl.getInstance());
						dial.setVisible(true);

					}));
					panelSxDx.add(panelLeft, BorderLayout.CENTER);
					panelSxDx.add(panelRight, BorderLayout.EAST);
					panelSxDx.add(panelBot, BorderLayout.SOUTH);
					panelCenter.add(panelSxDx);
					panelCenter.add(pnMem);
					add(panelCenter, BorderLayout.CENTER);
					add(createJLabel("<html><U>Gestione Reparto</U></html>", FONTSIZE + 2), BorderLayout.NORTH);
					validate();
					repaint();
				}
			});

		}

	}

	public String toString() {
		return unit.getName();
	}

}
