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

import control.Container;
import control.InfoProjectImpl;
import control.Unit;
import control.myUtil.MyOptional;
import model.escursioni.Excursion;
import model.exception.ObjectAlreadyContainedException;
import model.reparto.Member;
import model.reparto.Roles;
import view.excursion_manager.SquadronExcursionPane;
import view.excursion_manager.UnitExcursionPane;
import view.fee_manager.utility.MemberExcursionFeeJDialog;
import view.fee_manager.utility.MemberFeeJDialog;
import view.unit_manager.utility.EditMemberInfoJDialog;
import view.unit_manager.utility.ShowMemberInfoJDialogImpl;
/**
 * 
 * @author Giovanni Martelli
 *
 * @param <E>
 * @param <K>
 */
public class SearchElementJDialog<E, K> extends JDialog {

	public enum SearchType {
		MemberInExc,AssignCharge, ShowMember, EditMember, EditMemberRep, addMemberExc, removeExcursion, Excursion, tasseRep, tasseSquad, tasseSquadExc;
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
	//private Squadron squadImpl;
	private JTextArea area = new JTextArea();
	private final Container cnt;
	private final Unit unit;
	public SearchElementJDialog(final SearchType t, final E param, final MyOptional<String> stringParam, final JPanel parent) {
		super();
		this.elem = param;
		this.type = t;
		this.unit=MyJFrameSingletonImpl.getInstance().getUnit();
		this.cnt=unit.getContainers();
		panel = new MyJPanelImpl(new BorderLayout());
		panelButton = new MyJPanelImpl(new FlowLayout(FlowLayout.LEFT));

		if (t.equals(SearchType.AssignCharge) || t.equals(SearchType.ShowMember) || t.equals(SearchType.EditMember)
				|| type.equals(SearchType.addMemberExc) || type.equals(SearchType.EditMemberRep)
				|| type.equals(SearchType.tasseRep) || type.equals(SearchType.tasseSquad)
				|| type.equals(SearchType.tasseSquadExc) || type.equals(SearchType.MemberInExc)) {
			this.parent = parent;
			this.charge = stringParam.orElse("");
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
			final Map<Member, Roles> memberSquad = cnt.findSquadron((String) elem).getMembri();
			matches = (List<K>) ((!first.getText().isEmpty() && !second.getText().isEmpty())?
				cnt.getMemberNamedFromList(first.getText()
							,second.getText(), memberSquad.keySet().stream().collect(Collectors.toList()))
					: (!first.getText().isEmpty())?
							cnt.getMemberWithNameFromList(first.getText(), memberSquad.keySet()
									.stream().collect(Collectors.toList()))
							: cnt.getMemberWithSurnameFromList(second.getText(), memberSquad.keySet()
									.stream().collect(Collectors.toList())));
			if (matches.isEmpty()) {
				new WarningNotice("Nessun membro trovato con quel nome." + System.lineSeparator()
						+ "Controllare i dati inseriti e riprovare");
			} else {
				tooItemAndExecute();
			}
		} else if (type.equals(SearchType.tasseSquad) || type.equals(SearchType.tasseRep)
				|| type.equals(SearchType.tasseSquadExc) || type.equals(SearchType.MemberInExc)) {
			matches = (List<K>) ((!first.getText().isEmpty() && !second.getText().isEmpty())
					?	cnt.getMemberNamedFromList(first.getText()
							,second.getText(), ((EditableElementScrollPanel<Member>) parent).getList())
							
							
					: (!first.getText().isEmpty())
							? cnt.getMemberWithNameFromList(first.getText(),
									((EditableElementScrollPanel<Member>) parent).getList())
							: cnt.getMemberWithSurnameFromList(second.getText(),
									((EditableElementScrollPanel<Member>) parent).getList()));

			if (matches.isEmpty()) {
				new WarningNotice("Nessun membro trovato con quel nome." + System.lineSeparator()
						+ "Controllare i dati inseriti e riprovare");
			} else {
				tooItemAndExecute();
			}
		}/*else if(type.equals(SearchType.MemberInExc)){
			matches=(List<K>)
		}*/

		else if (type.equals(SearchType.addMemberExc) || type.equals(SearchType.EditMemberRep)
				|| type.equals(SearchType.tasseRep)) {
			matches = (List<K>) ((!first.getText().isEmpty() && !second.getText().isEmpty())
					?cnt.getMemberNamed(first.getText(), second.getText())
					: (!first.getText().isEmpty())
							? cnt.getMemberWithName(first.getText())
							:cnt.getMemberWithSurname(second.getText()));
			if (matches.isEmpty()) {
				
				new WarningNotice("Nessun membro trovato con quel nome." + System.lineSeparator()
						+ "Controllare i dati inseriti e riprovare");
			} else {
				
				tooItemAndExecute();
			}
		} else if (type.equals(SearchType.removeExcursion)) {
			matches = (List<K>) cnt.getExcursion().stream()
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
		final JDialog dialInternal = new JDialog();
		// squadImpl=new SquadronImpl("a", false);
		

		final MyJPanelImpl panInternal = new MyJPanelImpl(new BorderLayout());
		panInternal.add(panInternal.createJLabel("Scegliere la corrispondenza desiderata", fontSizeLabel),
				BorderLayout.NORTH);
		final MyJPanelImpl panMember = new MyJPanelImpl(new GridLayout(0, 1));
		final MyJPanelImpl paneSelect = new MyJPanelImpl(new GridLayout(0, 1));
		final MyJPanelImpl panBot = new MyJPanelImpl(new BorderLayout());

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
					final String method = "set" + charge.substring(0, charge.length() - 2)
							+ ((charge.equals("Vice: ")) ? "capoSq" : "Sq");
					try {
						if (charge.equals("Capo: ") && cnt.findSquadron((String)elem).isCapoPresent()) {
							cnt.findSquadron((String)elem).removeCapo();
						} else if (charge.equals("Vice: ") && cnt.findSquadron((String)elem).isVicecapoPresent()) {
							cnt.findSquadron((String)elem).removeVice();
						} else {
							if (cnt.findSquadron((String)elem).isTricecapoPresent()){
								cnt.findSquadron((String)elem).removeTrice();
							}
						}
						final Method mt = cnt
								.findSquadron((String) elem).getClass().getDeclaredMethod(method, Member.class);
						// lancio metodo
						mt.invoke(cnt.findSquadron((String)elem), (Member) e);
						MyJFrameSingletonImpl.getInstance().setNeedToSave();// setto
																			// che
																			// sono
																			// avvenuti
																			// dei
																			// cambiamenti
						new WarningNotice("Membro trovato e settato come " + charge.toLowerCase()
								+ System.lineSeparator() + "Ricordati di salvare o perderai le modifiche");
						((EditableInfoPanel) parent).updateInfo();

					} catch (Exception w) {

						new WarningNotice(w.getMessage());
					}
				}));

			});

		} else if (type.equals(SearchType.ShowMember) || type.equals(SearchType.EditMember)
				|| type.equals(SearchType.addMemberExc) || type.equals(SearchType.EditMemberRep)|| type.equals(SearchType.MemberInExc)) {
			area = new JTextArea();
			matches.stream().forEach(e -> {
				area.append("Nome: " + ((Member) e).getName() + System.lineSeparator());
				area.append("Cognome: " + ((Member) e).getSurname() + System.lineSeparator());
				area.append("Nascita: " + ((Member) e).getBirthday().toString() + System.lineSeparator());
				panMember.add(area);
				if (type.equals(SearchType.addMemberExc)|| type.equals(SearchType.MemberInExc)) {
					if (cnt.getExcursionNamed((String) elem)
							.containMember((Member) e)) {
						paneSelect.add(paneSelect.createButton("Disdici", o -> {
							try {
								cnt
										.getExcursionNamed((String) elem).removePartecipant((Member) e);
								MyJFrameSingletonImpl.getInstance().setNeedToSave();
								((EditableElementScrollPanel<Member>)parent).updateMember();
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
								cnt.getExcursionNamed((String) elem).addPartecipant((Member) e, false);
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
							new ShowMemberInfoJDialogImpl((Member) e).setVisible(true);
						} else {
							new EditMemberInfoJDialog((Member) e, (EditableElementScrollPanel<Member>) parent)
									.setVisible(true);
						}
					}));
				}
			});

		} else if (type.equals(SearchType.removeExcursion)) {
			matches.stream().forEach(e -> {
				area.append((new InfoProjectImpl()).getExcursionInfo((Excursion) e));
				panMember.add(area);
				paneSelect.add(paneSelect.createButton("Rimuovi", o -> {
					dialInternal.dispose();
					unit.removeExcursion((Excursion) e);
					if (parent instanceof UnitExcursionPane) {
						((UnitExcursionPane) parent).updatePaneInfo();
						((UnitExcursionPane) parent).updateExcursion();
					} else {
						((SquadronExcursionPane) parent).updatePaneInfo();
						((SquadronExcursionPane) parent).updateExcursion();
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
						new MemberFeeJDialog((Member) e, (EditableElementScrollPanel<Member>) parent).setVisible(true);
					} else {
						new MemberExcursionFeeJDialog((Member) e, (EditableElementScrollPanel<Member>) parent)
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
