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
import control.SortExcursion;
import control.SortExcursionImpl;
import control.SortMemberImpl;
import control.myUtil.MyOptional;
import extra.sito.ExcursionOnlineGetterImpl;
import extra.sito.Regioni;
import model.Campo;
import model.EventiDiZona;
import model.Excursion;
import model.Gemellaggi;
import model.Member;
import model.MemberImpl;
import model.RepartoImpl;
import model.Squadron;
import model.UscitaSquadriglia;
import model.exception.ObjectNotContainedException;
import view.general_utility.WarningNotice;
import view.gestione_eventi.utility.ShowEditExcursion;
import view.gestione_reparto.utility.AddMemberJDialog;
import view.gestione_reparto.utility.EditMemberInfoJDialog;
import view.gestione_reparto.utility.ShowMemberInfoJDialog;
import view.gestione_tasse.utility.MemberTasseExcursionJDialog;
import view.gestione_tasse.utility.MemberTasseJDialog;
import view.gui_utility.SearchElementJDialog.SearchType;


public class EditableMemberPanelImpl<E> extends MyJPanelImpl{
	public enum Type{
		RETTAREP,
		GESTIONESQUADRIGLIA,
		OVERVIEWSQUAD,
		TASSEEXCSQUAD,
		OVERVIEWREP,
		EXCREP,
		EXCSQUAD,
		RETTASQUAD,
		EXCPARTECIPANTI,
		EXCONLINE;
	}
	private static final long serialVersionUID = 9037769890822002300L;
	private List<E> memList;
	private final JPanel panelMember;
	private final JPanel sortPanel;
	private final JScrollPane scroll;
	private final static int FONTSIZE=15;
	private final static int FONTSIZEBUTTON=16;
	private final SortMemberImpl sort;
	private final SortExcursion sortExc;
	private final EditableMemberPanelImpl<E> me;
	private final Type type;
	private Squadron squadImpl;
	private String squadName;
	private final RepartoImpl rep;
	private Map<Member,List<Excursion>> mapPagamenti=new HashMap<>();
	@SuppressWarnings("unchecked")
	public EditableMemberPanelImpl(Type typeParam, MyOptional<String> squadName){
		super(new BorderLayout());
		this.type=typeParam;
		this.me=this;
		this.rep=(RepartoImpl) MyJFrameSingletonImpl.getInstance().getUnit().getReparto();
		if(squadName.isPresent() ){
			this.squadName=squadName.get();
			try{
				squadImpl=MyJFrameSingletonImpl.getInstance().getUnit().getContainers()
						.findSquadron(squadName.get());
			}catch(Exception e){
				
			}
		}
		this.updateMember();
		this.sortExc=new SortExcursionImpl();
		this.sort=new SortMemberImpl();
		this.panelMember=new JPanel(new FlowLayout(FlowLayout.LEFT));
		this.scroll = new JScrollPane(panelMember);
		scroll.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(192,192,192)));
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		this.panelMember.setPreferredSize(new Dimension(
	           scroll.getWidth(),
	           2000
	    ));
		
		if(type.equals(Type.EXCREP) || type.equals(Type.EXCSQUAD)){
			this.sortPanel=new JPanel();
			sortPanel.add(createJLabel("Ordina escursioni per: ", FONTSIZE));
			sortPanel.add(createButton("Data", e->{
				memList=(List<E>)sortExc.sortByDateOfStart((List<Excursion>)memList);
				updateMemberBotton();
			}));
			sortPanel.add(createButton("Prezzo", e->{
				memList=(List<E>)sortExc.sortByPrice((List<Excursion>)memList);
				updateMemberBotton();
			}));
		}
		else{
			this.sortPanel=new JPanel();
			sortPanel.add(createJLabel("Ordina Membri per: ", FONTSIZE));
			sortPanel.add(createButton("nome", e->{
				memList = (List<E>) sort.sortByName((List<Member>) memList);
				updateMemberBotton();
			}));
			sortPanel.add(createButton("cognome",e->{
				memList=(List<E>)sort.sortBySurname((List<Member>) memList);
				updateMemberBotton();
			}));
			sortPanel.add(createButton("età",e->{
				memList=(List<E>) sort.sortByAge((List<Member>) memList);
				updateMemberBotton();
			}));
			sortPanel.add(createButton("<html>Cerca<br>Membro<hrml>", e->{
				if(type.equals(Type.OVERVIEWSQUAD)){
					new SearchElementJDialog<>(SearchType.ShowMember, squadName.get(), MyOptional.empty(), this);
				}
				if(type.equals(Type.GESTIONESQUADRIGLIA)){
					new SearchElementJDialog<>(SearchType.EditMember, squadName.get(), MyOptional.empty(), this);
				}
				if(type.equals(Type.OVERVIEWREP)){
					new SearchElementJDialog<>(SearchType.EditMemberRep, MyOptional.empty(), MyOptional.empty(), this);
				}
				if(type.equals(Type.RETTAREP)){
					new SearchElementJDialog<>(SearchType.tasseRep, MyOptional.empty(), MyOptional.empty(), this);
				}
				if(type.equals(Type.RETTASQUAD)){
					new SearchElementJDialog<>(SearchType.tasseSquad, MyOptional.empty(), MyOptional.empty(), this);
				}
				if(type.equals(Type.TASSEEXCSQUAD)){
					new SearchElementJDialog<>(SearchType.tasseSquadExc, MyOptional.empty(), MyOptional.empty(), this);
				}
			}));
		}
		Arrays.asList(sortPanel.getComponents()).stream().forEach(e->e.setFont(new Font("Aria", Font.ITALIC,FONTSIZE)));
		add(sortPanel, BorderLayout.NORTH);
		this.add(scroll,BorderLayout.CENTER);
		this.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(0,0,0)));
	}
	
	@SuppressWarnings("unchecked")
	public final void updateMember(){
		if(type.equals(Type.GESTIONESQUADRIGLIA)||type.equals(Type.OVERVIEWSQUAD)){
			this.memList=(List<E>) squadImpl.getMembri().keySet().stream().collect(Collectors.toList());
			updateMemberBotton();
		}
		else if(type.equals(Type.OVERVIEWREP)){
			this.memList=(List<E>)rep.getAllMember();
			updateMemberBotton();
		}
		else if(type.equals(Type.RETTAREP)){
			this.memList=(List<E>)(new CheckerImpl()).noPaiedMembers(MyJFrameSingletonImpl.getInstance().getUnit().getReparto());
			updateMemberBotton();
		}
		else if(type.equals(Type.TASSEEXCSQUAD)){
			mapPagamenti=new HashMap<>();
			for(Member i: squadImpl.getMembri().keySet()){
				final List<Excursion> tmp= new ArrayList<>();
				MyJFrameSingletonImpl.getInstance().getUnit().getContainers().getExcursion().stream()
				.forEach(e->{
				
					if(e.getNotPaied().contains(i)){tmp.add(e);};
				});
				if(tmp.size()>0){
					mapPagamenti.put(i, tmp.stream().collect(Collectors.toList()));
				}
			}
			this.memList=(List<E>) mapPagamenti.keySet().stream().collect(Collectors.toList());
			updateMemberBotton();
		}
		else if(type.equals(Type.EXCREP)){
			this.memList=(List<E>)MyJFrameSingletonImpl.getInstance().getUnit().getContainers().getExcursion()
					.stream().filter(e->!(e instanceof UscitaSquadriglia)).collect(Collectors.toList());
			updateMemberBotton();
		}
		else if(type.equals(Type.EXCONLINE)){
			try{
				memList=new ArrayList<>();
				ExcursionOnlineGetterImpl.getExcursion(Regioni.valueOf(squadName)).stream().forEach(e->{
					memList.add((E)e);
				});
				updateMemberBotton();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		else if(type.equals(Type.EXCSQUAD)){
			this.memList=(List<E>)MyJFrameSingletonImpl.getInstance().getUnit().getContainers().getExcursion()
					.stream().filter(e->e instanceof UscitaSquadriglia).collect(Collectors.toList());
			updateMemberBotton();
		}
		else if(type.equals(Type.RETTASQUAD)){
			this.memList=new ArrayList<>();
			final List<Member> mem = squadImpl.getMembri().keySet().stream().collect(Collectors.toList());
			mem.stream().forEach(e->{
				if(rep.getMembersNotPaid(Year.now().getValue()).contains(e)){
					memList.add((E) e);
				}
			});
			updateMemberBotton();
		}
		else if(type.equals(Type.EXCPARTECIPANTI)){
			this.memList=(List<E>) MyJFrameSingletonImpl.getInstance().getUnit().getContainers().getExcursionNamed(squadName).getAllPartecipants();
			updateMemberBotton();
		}
	}
	
	private void updateMemberBotton(){
		SwingUtilities.invokeLater(new Runnable() {
			@SuppressWarnings("unchecked")
			public void run() {
				panelMember.removeAll();
				if(type.equals(Type.GESTIONESQUADRIGLIA) || type.equals(Type.OVERVIEWREP)){
					panelMember.add((createButton("<html>Aggiungi<br>Membro"+	((type.equals(Type.GESTIONESQUADRIGLIA))?
							"<br>in squadriglia<html>":"<br>in reparto</html>")
							,  16,e->{
								if(type.equals(Type.GESTIONESQUADRIGLIA)){
						(new AddMemberJDialog(MyJFrameSingletonImpl.getInstance().getUnit()
								,(EditableMemberPanelImpl<Member>)me, MyOptional.of(squadImpl.getNome()))).setVisible(true);
								}
								else{
									(new AddMemberJDialog(MyJFrameSingletonImpl.getInstance().getUnit()
											,(EditableMemberPanelImpl<Member>)me,MyOptional.empty())).setVisible(true);
								}
					})));
				}
				
				memList.stream().forEach(f->{
					panelMember.add(instanceJButton((E)f));
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
	private JButton instanceJButton(E mem){
		
		if(type.equals(Type.GESTIONESQUADRIGLIA)|| type.equals(Type.OVERVIEWREP)){
			if(MyJFrameSingletonImpl.getInstance().getUnit().getContainers().getFreeMember().contains(((Member)mem))){
				return createButton("<html>"+((Member)mem).getName()+"<br>"+((Member)mem).getSurname()+"</html>",
						Color.ORANGE, new Font("Aria", Font.ITALIC, FONTSIZEBUTTON), e->{
					(new EditMemberInfoJDialog((MemberImpl)mem,(EditableMemberPanelImpl<Member>)me)).setVisible(true);
				});
			}
			else{
				return createButton("<html>"+((Member)mem).getName()+"<br>"+((Member)mem).getSurname()+"</html>",FONTSIZEBUTTON,  e->{
					(new EditMemberInfoJDialog((MemberImpl)mem,(EditableMemberPanelImpl<Member>)me)).setVisible(true);
				});
			}
		}
		else if(type.equals(Type.OVERVIEWSQUAD)){
			return createButton("<html>"+((Member)mem).getName()+"<br>"+((Member)mem).getSurname()+"</html>",  FONTSIZEBUTTON,  e->{
				(new ShowMemberInfoJDialog(((Member)mem))).setVisible(true); 
			});
		}
		else if(type.equals(Type.TASSEEXCSQUAD)){
			return createButton("<html>"+((Member)mem).getName()+"<br>"+((Member)mem).getSurname()+"</html>",  FONTSIZEBUTTON,  e->{
				(new MemberTasseExcursionJDialog(((Member)mem),(EditableMemberPanelImpl<Member>)me)).setVisible(true);;
			});
		}
		else if(type.equals(Type.EXCREP)){
			String str="("+((mem instanceof Campo)?"Campo"
					:(mem instanceof EventiDiZona)?"Evento di zona"
							:(mem instanceof Gemellaggi)?"Gemellaggio"
									:"Uscita di reparto")+")";
			return createButton("<html>"+((Excursion)mem).getName()+"<br>"+str+"<br>"+
									((Excursion)mem).getDateStart().toString()+"</html>",FONTSIZEBUTTON, e->{
										new ShowEditExcursion((Excursion)mem, (EditableMemberPanelImpl<Excursion>) me);
				
			});
		}
		else if(type.equals(Type.EXCONLINE)){
			return createButton("<html>"+((Excursion)mem).getName()+"<br>"+"(PiccoleOrme)"+"<br>"+
					((Excursion)mem).getDateStart().toString()+"</html>",FONTSIZEBUTTON, e->{
						new ShowEditExcursion((Excursion)mem, (EditableMemberPanelImpl<Excursion>) me);

			});
		}
		else if(type.equals(Type.EXCSQUAD)){
			return createButton("<html>"+((Excursion)mem).getName()+"<br>"+"(Uscita Squadriglia)"+"<br>"+
					((Excursion)mem).getDateStart().toString()+"</html>",16,e->{
						new ShowEditExcursion((Excursion)mem,(EditableMemberPanelImpl<Excursion>) me);
					});
		}
		else if(type.equals(Type.EXCPARTECIPANTI)){
			return createButton("<html>"+((Member)mem).getName()+"<br>"+((Member)mem).getSurname(), FONTSIZEBUTTON, e->{
				ShowMemberInfoJDialog dial= new ShowMemberInfoJDialog((Member)mem);
				dial.addButtonToBot("<html>Non<br>Partecipare</html>", u->{
					try {
						MyJFrameSingletonImpl.getInstance().getUnit().getContainers().getExcursionNamed(squadName).removePartecipant((Member)mem);
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
		else{
			return createButton("<html>"+((Member)mem).getName()+"<br>"+((Member)mem).getSurname(), FONTSIZEBUTTON, e->{
				(new MemberTasseJDialog((MemberImpl)mem, (EditableMemberPanelImpl<Member>)this)).setVisible(true);
			
			});
		}
		
	}
	public List<E> getList(){
		return memList.stream().collect(Collectors.toList());
	}
	public void forceUpdate(String newParam){
		this.squadName=newParam;
		this.updateMember();
		
	}

	
		
	
}
