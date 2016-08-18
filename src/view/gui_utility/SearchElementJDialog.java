package view.gui_utility;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import control.InfoProjectImpl;
import control.myUtil.MyOptional;
import model.Excursion;
import model.Member;
import model.MemberImpl;
import model.Roles;
import model.Squadron;
import model.exception.ObjectAlreadyContainedException;
import view.general_utility.WarningNotice;
import view.gestione_eventi.EventiReparto.EventiRepartoPane;
import view.gestione_eventi.EventiSquadriglia.EventiSquadrigliaPane;
import view.gestione_reparto.utility.EditMemberInfoJDialog;
import view.gestione_reparto.utility.ShowMemberInfoJDialog;
import view.gestione_tasse.utility.MemberTasseExcursionJDialog;
import view.gestione_tasse.utility.MemberTasseJDialog;

public class SearchElementJDialog<E, K> extends JDialog {

	public enum SearchType {
		AssignCharge, ShowMember, EditMember, EditMemberRep, addMemberExc, removeExcursion, Excursion, tasseRep, tasseSquad, tasseSquadExc;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -2330303224987114376L;
	private final MyJPanelImpl panel;
	private final JTextField first;
	private JTextField second;
	private final MyJPanelImpl panelCenter;
	private final MyJPanelImpl panelButton;
	private final int fontSizeLabel = 15;
	private final E elem;
	private final SearchType type;
	private List<K> matches;
	private String charge;
	private JPanel parent;
	private Squadron squadImpl;
	private JTextArea area = new JTextArea();

	public SearchElementJDialog(SearchType t, E param, MyOptional<String> charge, JPanel parent) {
		super();
		this.elem = param;
		this.type = t;
		panel = new MyJPanelImpl(new BorderLayout());
		panelButton = new MyJPanelImpl(new FlowLayout(FlowLayout.LEFT));

		if (t.equals(SearchType.AssignCharge) || t.equals(SearchType.ShowMember) || t.equals(SearchType.EditMember)
				|| type.equals(SearchType.addMemberExc) || type.equals(SearchType.EditMemberRep)
				|| type.equals(SearchType.tasseRep) || type.equals(SearchType.tasseSquad)
				|| type.equals(SearchType.tasseSquadExc)) {
			this.parent = parent;
			this.charge = charge.orElse("");
			panel.add(panel.createJLabel("Inserire almeno uno dei campi richiesti", fontSizeLabel + 2),
					BorderLayout.NORTH);
			panelCenter = new MyJPanelImpl(new GridLayout(2, 2));
			panelCenter.add(panel.createJLabel("Nome: ", fontSizeLabel));
			first = new JTextField();
			panelCenter.add(first);
			panelCenter.add(panel.createJLabel("Cognome: ", fontSizeLabel));
			second = new JTextField();
			panelCenter.add(second);

		} else {
			this.parent = parent;
			panel.add(panel.createJLabel("Inserire nome ", fontSizeLabel + 2), BorderLayout.NORTH);
			panelCenter = new MyJPanelImpl(new GridLayout(1, 2));
			panelCenter.add(panel.createJLabel("Nome: ", fontSizeLabel));
			first = new JTextField();
			panelCenter.add(first);
		}
		panelButton.add(panel.createButton("Annulla", e -> {
			this.dispose();
		}));
		panelButton.add(panel.createButton("Cerca", e -> {
			searchMatches();
		}));
		panel.add(panelCenter, BorderLayout.CENTER);
		panel.add(panelButton, BorderLayout.SOUTH);
		this.add(panel);
		this.pack();
		this.setLocationRelativeTo(MyJFrameSingletonImpl.getInstance());
		this.setVisible(true);
	}

	@SuppressWarnings("unchecked")
	private void searchMatches() {

		if (type.equals(SearchType.AssignCharge) || type.equals(SearchType.ShowMember)
				|| type.equals(SearchType.EditMember)) {
			final Map<Member, Roles> memberSquad = MyJFrameSingletonImpl.getInstance().getUnit().getContainers()
					.findSquadron((String) elem).getMembri();
			matches = (List<K>) ((!first.getText().isEmpty() && !second.getText().isEmpty())
					? memberSquad.keySet().stream()
							.filter(t -> t.getName().equals(first.getText()) && t.getSurname().equals(second.getText()))
							.collect(Collectors.toList())
					: (!first.getText().isEmpty())
							? memberSquad.keySet().stream().filter(t -> t.getName().equals(first.getText()))
									.collect(Collectors.toList())
							: memberSquad.keySet().stream().filter(l -> l.getSurname().equals(second.getText()))
									.collect(Collectors.toList()));
			if (matches.isEmpty()) {
				new WarningNotice("Nessun membro trovato con quel nome." + System.lineSeparator()
						+ "Controllare i dati inseriti e riprovare");
			} else {
				tooItemAndExecute();
			}
		} else if (type.equals(SearchType.tasseSquad) || type.equals(SearchType.tasseRep)
				|| type.equals(SearchType.tasseSquadExc)) {
			List<Member> tmp = ((EditableMemberPanelImpl<Member>) parent).getList();
			matches = (List<K>) ((!first.getText().isEmpty() && !second.getText().isEmpty())
					? tmp.stream()
							.filter(t -> t.getName().equals(first.getText()) && t.getSurname().equals(second.getText()))
							.collect(Collectors.toList())
					: (!first.getText().isEmpty())
							? tmp.stream().filter(t -> t.getName().equals(first.getText())).collect(Collectors.toList())
							: tmp.stream().filter(l -> l.getSurname().equals(second.getText()))
									.collect(Collectors.toList()));

			if (matches.isEmpty()) {
				new WarningNotice("Nessun membro trovato con quel nome." + System.lineSeparator()
						+ "Controllare i dati inseriti e riprovare");
			} else {
				tooItemAndExecute();
			}
		}

		else if (type.equals(SearchType.addMemberExc) || type.equals(SearchType.EditMemberRep)
				|| type.equals(SearchType.tasseRep)) {
			matches = (List<K>) ((!first.getText().isEmpty() && !second.getText().isEmpty())
					? MyJFrameSingletonImpl.getInstance().getUnit().getReparto().getAllMember()
							.stream()
							.filter(g -> g.getName().equals(first.getText()) && g.getSurname().equals(second.getText()))
							.collect(Collectors.toList())
					: (!first.getText().isEmpty())
							? MyJFrameSingletonImpl.getInstance().getUnit().getReparto().getAllMember().stream()
									.filter(g -> g.getName().equals(first.getText())).collect(Collectors.toList())
							: MyJFrameSingletonImpl.getInstance().getUnit().getReparto().getAllMember().stream()
									.filter(g -> g.getSurname().equals(second.getText())).collect(Collectors.toList()));
			if (matches.isEmpty()) {
				new WarningNotice("Nessun membro trovato con quel nome." + System.lineSeparator()
						+ "Controllare i dati inseriti e riprovare");
			} else {
				tooItemAndExecute();
			}
		} else if (type.equals(SearchType.removeExcursion)) {
			matches = (List<K>) MyJFrameSingletonImpl.getInstance().getUnit().getContainers().getExcursion().stream()
					.filter(d -> d.getName().equals(first.getText())).collect(Collectors.toList());
			if (matches.isEmpty()) {
				new WarningNotice("Nessuna escursione trovata con quel nome." + System.lineSeparator()
						+ "Controllare i dati inseriti e riprovare");
			} else {
				tooItemAndExecute();
			}
		}

	}

	@SuppressWarnings("unchecked")
	private void tooItemAndExecute() {
		JDialog dialInternal = new JDialog();
		// squadImpl=new SquadronImpl("a", false);
		try {
			squadImpl = MyJFrameSingletonImpl.getInstance().getUnit().getContainers().findSquadron((String) elem);
		} catch (Exception e) {/*
								 * e.printStackTrace(); new
								 * WarningNotice(e.getMessage());
								 */
		}

		MyJPanelImpl panInternal = new MyJPanelImpl(new BorderLayout());
		panInternal.add(panInternal.createJLabel("Scegliere la corrispondenza desiderata", fontSizeLabel),
				BorderLayout.NORTH);
		MyJPanelImpl panMember = new MyJPanelImpl(new GridLayout(0, 1));
		MyJPanelImpl paneSelect = new MyJPanelImpl(new GridLayout(0, 1));
		MyJPanelImpl panBot = new MyJPanelImpl(new BorderLayout());

		area.setEditable(false);
		panBot.add(panBot.createButton("Annulla", u -> {
			dialInternal.dispose();
		}), BorderLayout.EAST);
		panInternal.add(panBot, BorderLayout.SOUTH);
		panInternal.add(panMember, BorderLayout.CENTER);
		panInternal.add(paneSelect, BorderLayout.EAST);
		if (type.equals(SearchType.AssignCharge)) {
			matches.stream().forEach(e -> {
				area = new JTextArea();
				area.append("Nome: " + ((Member) e).getName() + System.lineSeparator());
				area.append("Cognome: " + ((Member) e).getSurname() + System.lineSeparator());
				area.append("Nascita: " + ((Member) e).getBirthday().toString() + System.lineSeparator());
				panMember.add(area);
				paneSelect.add(paneSelect.createButton("Scegli", o -> {
					dialInternal.dispose();
					String method = "set" + charge.substring(0, charge.length() - 2)
							+ ((charge.equals("Vice: ")) ? "capoSq" : "Sq");
					try {
						if (charge.equals("Capo: ") && squadImpl.isCapoPresent()) {
							squadImpl.removeCapo();
						} else if (charge.equals("Vice: ") && squadImpl.isVicecapoPresent()) {
							squadImpl.removeVice();
						} else {
							if (squadImpl.isTricecapoPresent())
								squadImpl.removeTrice();
						}
						Method mt = MyJFrameSingletonImpl.getInstance().getUnit().getContainers()
								.findSquadron((String) elem).getClass().getDeclaredMethod(method, Member.class);
						// lancio metodo
						mt.invoke(squadImpl, (Member) e);
						MyJFrameSingletonImpl.getInstance().setNeedToSave();// setto
																			// che
																			// sono
																			// avvenuti
																			// dei
																			// cambiamenti
						new WarningNotice("Membro trovato e settato come " + charge.toLowerCase()
								+ System.lineSeparator() + "Ricordati di salvare o perderai le modifiche");
						((EditableInfoPanelImpl) parent).updateInfo();

					} catch (Exception w) {

						new WarningNotice(w.getMessage());
					}
				}));

			});

		} else if (type.equals(SearchType.ShowMember) || type.equals(SearchType.EditMember)
				|| type.equals(SearchType.addMemberExc) || type.equals(SearchType.EditMemberRep)) {
			area = new JTextArea();
			matches.stream().forEach(e -> {
				area.append("Nome: " + ((Member) e).getName() + System.lineSeparator());
				area.append("Cognome: " + ((Member) e).getSurname() + System.lineSeparator());
				area.append("Nascita: " + ((Member) e).getBirthday().toString() + System.lineSeparator());
				panMember.add(area);
				if (type.equals(SearchType.addMemberExc)) {
					if (MyJFrameSingletonImpl.getInstance().getUnit().getContainers().getExcursionNamed((String) elem)
							.containMember((Member) e)) {
						paneSelect.add(paneSelect.createButton("Remove", o -> {
							try {
								MyJFrameSingletonImpl.getInstance().getUnit().getContainers()
										.getExcursionNamed((String) elem).removePartecipant((Member) e);
								MyJFrameSingletonImpl.getInstance().setNeedToSave();
								dialInternal.dispose();
								this.dispose();
							} catch (Exception e1) {
								e1.printStackTrace();
								new WarningNotice(e1.getMessage());
							}
						}));
					} else {
						paneSelect.add(paneSelect.createButton("Partecipa", o -> {
							try {
								MyJFrameSingletonImpl.getInstance().getUnit().getContainers()
										.getExcursionNamed((String) elem).addPartecipant((Member) e, false);
								MyJFrameSingletonImpl.getInstance().setNeedToSave();
								dialInternal.dispose();
								this.dispose();
							} catch (ObjectAlreadyContainedException e1) {

								new WarningNotice(e1.getMessage());
							}
						}));
					}
				} else {
					paneSelect.add(paneSelect.createButton("Vedi", o -> {
						dialInternal.dispose();
						if (type.equals(SearchType.ShowMember)) {
							new ShowMemberInfoJDialog((Member) e).setVisible(true);
						} else {
							new EditMemberInfoJDialog((MemberImpl) e, (EditableMemberPanelImpl<Member>) parent)
									.setVisible(true);
						}
					}));
				}
			});

		} else if (type.equals(SearchType.removeExcursion)) {
			matches.stream().forEach(e -> {
				;
				area.append((new InfoProjectImpl()).getExcursionInfo((Excursion) e));
				panMember.add(area);
				paneSelect.add(paneSelect.createButton("Rimuovi", o -> {
					dialInternal.dispose();
					MyJFrameSingletonImpl.getInstance().getUnit().removeExcursion((Excursion) e);
					if (parent instanceof EventiRepartoPane) {
						((EventiRepartoPane) parent).updatePaneInfo();
						((EventiRepartoPane) parent).updateEventi();
					} else {
						((EventiSquadrigliaPane) parent).updatePaneInfo();
						((EventiSquadrigliaPane) parent).updateEventi();
					}

				}));

			});
		} else if (type.equals(SearchType.tasseRep) || type.equals(SearchType.tasseSquad)
				|| type.equals(SearchType.tasseSquadExc)) {
			matches.stream().forEach(e -> {
				area.append("Nome: " + ((Member) e).getName() + System.lineSeparator());
				area.append("Cognome: " + ((Member) e).getSurname() + System.lineSeparator());
				area.append("Nascita: " + ((Member) e).getBirthday().toString() + System.lineSeparator());
				panMember.add(area);
				paneSelect.add(paneSelect.createButton("paga", o -> {
					if (type.equals(SearchType.tasseRep) || type.equals(SearchType.tasseSquad)) {
						new MemberTasseJDialog((Member) e, (EditableMemberPanelImpl<Member>) parent).setVisible(true);
					} else {
						new MemberTasseExcursionJDialog((Member) e, (EditableMemberPanelImpl<Member>) parent)
								.setVisible(true);
					}
					dialInternal.dispose();
				}));

			});
		}
		panInternal.add(panMember, BorderLayout.CENTER);
		panInternal.add(paneSelect, BorderLayout.EAST);
		panInternal.add(panBot, BorderLayout.SOUTH);
		dialInternal.add(panInternal);
		dialInternal.pack();
		dialInternal.setLocationRelativeTo(this);
		dialInternal.setVisible(true);
	}

}
