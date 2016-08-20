package view.gui_utility;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.time.Year;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import control.CheckerImpl;
import control.Container;
import control.SortExcursion;
import control.SortExcursionImpl;
import control.SortMemberImpl;
import control.Unit;
import control.myUtil.MyOptional;
import extra.sito.ExcursionOnlineGetterImpl;
import extra.sito.Regioni;
import model.escursioni.Campo;
import model.escursioni.EventiDiZona;
import model.escursioni.Excursion;
import model.escursioni.Gemellaggi;
import model.escursioni.UscitaSquadriglia;
import model.exception.ObjectNotContainedException;
import model.reparto.Member;
import view.excursion_manager.utility.ShowEditExcursion;
import view.fee_manager.utility.MemberExcursionFeeJDialog;
import view.fee_manager.utility.MemberFeeJDialog;
import view.gui_utility.SearchElementJDialog.SearchType;
import view.unit_manager.utility.AddMemberJDialog;
import view.unit_manager.utility.EditMemberInfoJDialog;
import view.unit_manager.utility.ShowMemberInfoJDialogImpl;

public class EditableElementScrollPanelImpl<E> extends MyJPanelImpl implements EditableElementScrollPanel<E> {
	public enum Type {
		UNITFEE, UNITEXCFEE, MANAGERSQUAD, OVERVIEWSQUAD, SQEXCFEE, OVERVIEWUNIT, UNITEXC, SQEXC, SQFEE, EXCPARTECIPANT, EXCONLINE;
	}

	private static final long serialVersionUID = 9037769890822002300L;
	private List<E> memList;
	private final JPanel panelMember;
	private final JPanel sortPanel;
	private final JScrollPane scroll;
	private final static int FONTSIZE = 15;
	private final static int FONTSIZEBUTTON = 16;
	private final EditableElementScrollPanel<E> me;
	private final Type type;
	private String squadName;
	
	//private final RepartoImpl rep;
	private Map<Member, List<Excursion>> mapPagamenti = new HashMap<>();

	@SuppressWarnings("unchecked")
	public EditableElementScrollPanelImpl(final Type typeParam, final MyOptional<String> parameter) {
		super(new BorderLayout());
		final SortMemberImpl sort=new SortMemberImpl(); 
		final SortExcursion sortExc=new SortExcursionImpl();
		
		this.type = typeParam;
		this.me = this;
		//this.rep = (RepartoImpl) MyJFrameSingletonImpl.getInstance().getUnit().getReparto();
		if (parameter.isPresent()) {
			this.squadName = parameter.get();
			
		}
		this.updateMember();
		
		this.panelMember = new JPanel(new FlowLayout(FlowLayout.LEFT));
		this.scroll = new JScrollPane(panelMember);
		scroll.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(192, 192, 192)));
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		this.panelMember.setPreferredSize(new Dimension(scroll.getWidth(), 2000));

		if (type.equals(Type.UNITEXC) || type.equals(Type.SQEXC)) {
			this.sortPanel = new JPanel();
			sortPanel.add(createJLabel("Ordina escursioni per: ", FONTSIZE));
			sortPanel.add(createButton("Data", e -> {
				memList = (List<E>) sortExc.sortByDateOfStart((List<Excursion>) memList);
				updateMemberBotton();
			}));
			sortPanel.add(createButton("Prezzo", e -> {
				memList = (List<E>) sortExc.sortByPrice((List<Excursion>) memList);
				updateMemberBotton();
			}));
		} else {
			this.sortPanel = new JPanel();
			sortPanel.add(createJLabel("Ordina Membri per: ", FONTSIZE));
			sortPanel.add(createButton("nome", e -> {
				memList = (List<E>) sort.sortByName((List<Member>) memList);
				updateMemberBotton();
			}));
			sortPanel.add(createButton("cognome", e -> {
				memList = (List<E>) sort.sortBySurname((List<Member>) memList);
				updateMemberBotton();
			}));
			sortPanel.add(createButton("età", e -> {
				memList = (List<E>) sort.sortByAge((List<Member>) memList);
				updateMemberBotton();
			}));
			sortPanel.add(createButton("<html>Cerca<br>Membro<hrml>", e -> {
				if (type.equals(Type.OVERVIEWSQUAD)) {
					new SearchElementJDialog<>(SearchType.ShowMember, parameter.get(), MyOptional.empty(), this);
				}
				if (type.equals(Type.MANAGERSQUAD)) {
					new SearchElementJDialog<>(SearchType.EditMember, parameter.get(), MyOptional.empty(), this);
				}
				if (type.equals(Type.OVERVIEWUNIT)) {
					new SearchElementJDialog<>(SearchType.EditMemberRep, MyOptional.empty(), MyOptional.empty(), this);
				}
				if (type.equals(Type.UNITFEE)) {
					new SearchElementJDialog<>(SearchType.tasseRep, MyOptional.empty(), MyOptional.empty(), this);
				}
				if (type.equals(Type.SQFEE)) {
					new SearchElementJDialog<>(SearchType.tasseSquad, MyOptional.empty(), MyOptional.empty(), this);
				}
				if (type.equals(Type.SQEXCFEE)) {
					new SearchElementJDialog<>(SearchType.tasseSquadExc, MyOptional.empty(), MyOptional.empty(), this);
				}
				if (type.equals(Type.EXCPARTECIPANT)){
					new SearchElementJDialog<>(SearchType.MemberInExc, parameter.get(), MyOptional.empty(), this);
				}
			}));
		}
		Arrays.asList(sortPanel.getComponents()).stream()
				.forEach(e -> e.setFont(new Font("Aria", Font.ITALIC, FONTSIZE)));
		add(sortPanel, BorderLayout.NORTH);
		this.add(scroll, BorderLayout.CENTER);
		this.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(0, 0, 0)));
	}
	/* (non-Javadoc)
	 * @see view.gui_utility.EditableElementScrollPane#updateMember()
	 */
	@Override
	@SuppressWarnings("unchecked")
	public final void updateMember() {
		Unit unit=MyJFrameSingletonImpl.getInstance().getUnit();
		Container cnt=unit.getContainers();
		if (type.equals(Type.MANAGERSQUAD) || type.equals(Type.OVERVIEWSQUAD)) {
			this.memList = (List<E>) cnt.findSquadron(squadName).getMembri().keySet().stream().collect(Collectors.toList());
			updateMemberBotton();
		} else if (type.equals(Type.OVERVIEWUNIT)) {
			this.memList = (List<E>) cnt.getMembers();
			updateMemberBotton();
		} else if (type.equals(Type.UNITFEE)) {
			this.memList = (List<E>) (new CheckerImpl()).noPaiedMembers(unit.getReparto());
			updateMemberBotton();
		} else if (type.equals(Type.SQEXCFEE)) {
			mapPagamenti = new HashMap<>();
			for (final Member i : cnt.findSquadron(squadName).getMembri().keySet()) {
				final List<Excursion> tmp = new ArrayList<>();
				cnt.getExcursion().stream().forEach(e -> {
					if (e.getNotPaied().contains(i)) {
						tmp.add(e);
					}
				});
				if (!tmp.isEmpty()){
					mapPagamenti.put(i, tmp.stream().collect(Collectors.toList()));
				}
			}
			this.memList = (List<E>) mapPagamenti.keySet().stream().collect(Collectors.toList());
			updateMemberBotton();
		} else if (type.equals(Type.UNITEXC)) {
			this.memList = (List<E>) cnt.getExcursion()
					.stream().filter(e -> !(e instanceof UscitaSquadriglia)).collect(Collectors.toList());
			updateMemberBotton();
		} else if (type.equals(Type.EXCONLINE)) {
			try {
				memList = new ArrayList<>();
				ExcursionOnlineGetterImpl.getExcursion(Regioni.valueOf(squadName)).stream().forEach(e -> {
					memList.add((E) e);
				});
				updateMemberBotton();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (type.equals(Type.SQEXC)) {
			this.memList = (List<E>) cnt.getExcursionOfSquadron(cnt.findSquadron(squadName));
			updateMemberBotton();
		} else if (type.equals(Type.SQFEE)) {
			this.memList = new ArrayList<>();
			final List<Member> mem = cnt.findSquadron(squadName).getMembri().keySet().stream().collect(Collectors.toList());
			mem.stream().forEach(e -> {
				if (unit.getReparto().getMembersNotPaid(Year.now().getValue()).contains(e)) {
					memList.add((E) e);
				}
			});
			updateMemberBotton();
		} else if (type.equals(Type.EXCPARTECIPANT)) {
			this.memList = (List<E>) cnt.getExcursionNamed(squadName).getAllPartecipants();
			updateMemberBotton();
		}else if(type.equals(Type.UNITEXCFEE)){
			mapPagamenti = new HashMap<>();
			for (final Member i : cnt.getFreeMember()) {
				final List<Excursion> tmp = new ArrayList<>();
				cnt.getExcursion().stream().forEach(e -> {
					if (e.getNotPaied().contains(i)) {
						tmp.add(e);
					}
				});
				if (!tmp.isEmpty()) {
					mapPagamenti.put(i, tmp.stream().collect(Collectors.toList()));
				}
			}
			this.memList = (List<E>) mapPagamenti.keySet().stream().collect(Collectors.toList());
			updateMemberBotton();
		}
	}

	private void updateMemberBotton() {
		Unit unit=MyJFrameSingletonImpl.getInstance().getUnit();
	
		SwingUtilities.invokeLater(new Runnable() {
			@SuppressWarnings("unchecked")
			public void run() {
				panelMember.removeAll();
				if (type.equals(Type.MANAGERSQUAD) || type.equals(Type.OVERVIEWUNIT)) {
					panelMember.add((createButton("<html>Aggiungi<br>Membro" + ((type.equals(Type.MANAGERSQUAD))
							? "<br>in squadriglia<html>" : "<br>in reparto</html>"), 16, e -> {
								if (type.equals(Type.MANAGERSQUAD)) {
									(new AddMemberJDialog(unit,
											(EditableElementScrollPanelImpl<Member>) me, MyOptional.of(squadName)))
													.setVisible(true);
								} else {
									(new AddMemberJDialog(unit,
											(EditableElementScrollPanelImpl<Member>) me, MyOptional.empty())).setVisible(true);
								}
							})));
				}

				memList.stream().forEach(f -> {
					panelMember.add(instanceJButton((E) f));
				});
				panelMember.validate();
				panelMember.repaint();
				scroll.revalidate();
				scroll.repaint();
				repaint();
				validate();
			}
		});
	}

	@SuppressWarnings("unchecked")
	private JButton instanceJButton(final E mem) {
		Unit unit=MyJFrameSingletonImpl.getInstance().getUnit();
		Container cnt=unit.getContainers();

		if (type.equals(Type.MANAGERSQUAD) || type.equals(Type.OVERVIEWUNIT)) {
			if (cnt.getFreeMember().contains(((Member) mem))) {
				return createButton(
						"<html>" + ((Member) mem).getName() + "<br>" + ((Member) mem).getSurname() + "</html>",
						Color.ORANGE, new Font("Aria", Font.ITALIC, FONTSIZEBUTTON), e -> {
							(new EditMemberInfoJDialog((Member) mem, (EditableElementScrollPanel<Member>) me))
									.setVisible(true);
						});
			} else {
				return createButton(
						"<html>" + ((Member) mem).getName() + "<br>" + ((Member) mem).getSurname() + "</html>",
						FONTSIZEBUTTON, e -> {
							(new EditMemberInfoJDialog((Member) mem, (EditableElementScrollPanel<Member>) me))
									.setVisible(true);
						});
			}
		} else if (type.equals(Type.OVERVIEWSQUAD)) {
			return createButton("<html>" + ((Member) mem).getName() + "<br>" + ((Member) mem).getSurname() + "</html>",
					FONTSIZEBUTTON, e -> {
						(new ShowMemberInfoJDialogImpl(((Member) mem))).setVisible(true);
					});
		} else if (type.equals(Type.SQEXCFEE) || type.equals(Type.UNITEXCFEE)) {
			return createButton("<html>" + ((Member) mem).getName() + "<br>" + ((Member) mem).getSurname() + "</html>",
					FONTSIZEBUTTON, e -> {
						(new MemberExcursionFeeJDialog(((Member) mem), (EditableElementScrollPanel<Member>) me))
								.setVisible(true);
					});
		} else if (type.equals(Type.UNITEXC)) {
			final String str = "(" + ((mem instanceof Campo) ? "Campo"
					: (mem instanceof EventiDiZona) ? "Evento di zona"
							: (mem instanceof Gemellaggi) ? "Gemellaggio" : "Uscita di reparto")
					+ ")";
			return createButton("<html>" + ((Excursion) mem).getName() + "<br>" + str + "<br>"
					+ ((Excursion) mem).getDateStart().toString() + "</html>", FONTSIZEBUTTON, e -> {
						new ShowEditExcursion((Excursion) mem, (EditableElementScrollPanel<Excursion>) me);

					});
		} else if (type.equals(Type.EXCONLINE)) {
			return createButton("<html>" + ((Excursion) mem).getName() + "<br>" + "(PiccoleOrme)" + "<br>"
					+ ((Excursion) mem).getDateStart().toString() + "</html>", FONTSIZEBUTTON, e -> {
						new ShowEditExcursion((Excursion) mem, (EditableElementScrollPanel<Excursion>) me);

					});
		} else if (type.equals(Type.SQEXC)) {
			return createButton("<html>" + ((Excursion) mem).getName() + "<br>" + "(Uscita Squadriglia)" + "<br>"
					+ ((Excursion) mem).getDateStart().toString() + "</html>", 16, e -> {
						new ShowEditExcursion((Excursion) mem, (EditableElementScrollPanel<Excursion>) me);
					});
		} else if (type.equals(Type.EXCPARTECIPANT)) {
			if(MyJFrameSingletonImpl.getInstance().getUnit().getContainers().
					getExcursionNamed(squadName).getNotPaied().contains((Member)mem)){
				return createButton("<html>" + ((Member) mem).getName() + "<br>" + ((Member) mem).getSurname(),
						Color.ORANGE,new Font("Aria", Font.ITALIC, FONTSIZEBUTTON), e -> {
							final ShowMemberInfoJDialogImpl dial = new ShowMemberInfoJDialogImpl((Member) mem);
							dial.addButtonToBot("<html>Disdici</html>", u -> {
								try {
									MyJFrameSingletonImpl.getInstance().getUnit().getContainers()
											.getExcursionNamed(squadName).removePartecipant((Member) mem);
									dial.dispose();
									updateMember();
								} catch (ObjectNotContainedException e1) {
									new WarningNotice(e1.getMessage());
								}

							});
							dial.validate();
							dial.repaint();
							dial.pack();
							dial.setVisible(true);
						});
			}
			
			return createButton("<html>" + ((Member) mem).getName() + "<br>" + ((Member) mem).getSurname(),
					FONTSIZEBUTTON, e -> {
						final ShowMemberInfoJDialogImpl dial = new ShowMemberInfoJDialogImpl((Member) mem);
						dial.addButtonToBot("<html>Disdici</html>", u -> {
							try {
								MyJFrameSingletonImpl.getInstance().getUnit().getContainers()
										.getExcursionNamed(squadName).removePartecipant((Member) mem);
								dial.dispose();
								updateMember();
							} catch (ObjectNotContainedException e1) {
								new WarningNotice(e1.getMessage());
							}

						});
						dial.validate();
						dial.repaint();
						dial.pack();
						dial.setVisible(true);
					});
		} else {
			return createButton("<html>" + ((Member) mem).getName() + "<br>" + ((Member) mem).getSurname(),
					FONTSIZEBUTTON, e -> {
						(new MemberFeeJDialog((Member) mem, (EditableElementScrollPanel<Member>) this))
								.setVisible(true);

					});
		}

	}
	/* (non-Javadoc)
	 * @see view.gui_utility.EditableElementScrollPane#getList()
	 */
	@Override
	public List<E> getList() {
		return memList.stream().collect(Collectors.toList());
	}
	/* (non-Javadoc)
	 * @see view.gui_utility.EditableElementScrollPane#forceUpdate(java.lang.String)
	 */
	@Override
	public void forceUpdate(String newParam) {
		this.squadName = newParam;
		this.updateMember();

	}

}
