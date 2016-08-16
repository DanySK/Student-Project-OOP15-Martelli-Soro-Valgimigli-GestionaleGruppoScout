package view.gui_utility;

import java.awt.BorderLayout;
import java.awt.Color;
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

import control.SortMemberImpl;
import control.SortExcursion;
import control.SortExcursionImpl;
import control.myUtil.myOptional;
import model.Campo;
import model.EventiDiZona;
import model.Excursion;
import model.Gemellaggi;
import model.Member;
import model.MemberImpl;
import model.RepartoImpl;
import model.Squadron;
import model.UscitaSquadriglia;
import view.gestioneReparto.utility.AddMemberJDialog;
import view.gestioneReparto.utility.EditMemberInfoJDialog;
import view.gestioneReparto.utility.ShowMemberInfoJDialog;
import view.gestioneTasse.utility.MemberTasseExcursionJDialog;
import view.gestioneTasse.utility.MemberTasseJDialog;
import view.gui_utility.SearchElementJDialog.SearchType;


public class EditableMemberPanelImpl<E> extends MyJPanelImpl{
	public enum Type{
		TasseReparto,
		GestioneSquadriglia,
		OverviewSquadriglia,
		TasseSquadrigliaEscursioni,
		OverviewReparto,
		RepartoEventi,
		SquadrigliaEventi,
		TasseSquadriglia;
		
	}
	private static final long serialVersionUID = 9037769890822002300L;
	private List<E> memList;
	private JPanel panelMember;
	private final JPanel sortPanel;
	private final JScrollPane scroll;
	private int fontSize=15;
	private final SortMemberImpl sort;
	private final SortExcursion sortExc;
	private final EditableMemberPanelImpl<E> me;
	private final Type type;
	private Squadron squadImpl;
	private final RepartoImpl rep;
	private Map<Member,List<Excursion>> mapPagamenti=new HashMap<>();
	@SuppressWarnings("unchecked")
	public EditableMemberPanelImpl(Type t, myOptional<String> squadName){
		super(new BorderLayout());
		this.type=t;
		this.me=this;
		this.rep=(RepartoImpl) MyJFrameSingletonImpl.getInstance().getUnit().getReparto();
		
		if(squadName.isPresent() ){
			squadImpl=MyJFrameSingletonImpl.getInstance().getUnit().getContainers()
				.findSquadron(squadName.get());
		}
		this.updateMember();
		this.sortExc=new SortExcursionImpl();
		this.sort=new SortMemberImpl();
		this.panelMember=new JPanel(new FlowLayout(FlowLayout.LEFT));
		this.scroll = new JScrollPane(panelMember);
		scroll.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(192,192,192)));
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		this.panelMember.setPreferredSize(scroll.getSize());
		if(type.equals(Type.RepartoEventi) || type.equals(Type.SquadrigliaEventi)){
			this.sortPanel=new JPanel();
			sortPanel.add(createJLabel("Ordina escursioni per: ", fontSize));
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
			sortPanel.add(createJLabel("Ordina Membri per: ", fontSize));
			
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
				if(type.equals(Type.OverviewSquadriglia)){
					new SearchElementJDialog<>(SearchType.ShowMember, squadName.get(), myOptional.empty(), this);
				}
				if(type.equals(Type.GestioneSquadriglia)){
					new SearchElementJDialog<>(SearchType.EditMember, squadName.get(), myOptional.empty(), this);
				}
			}));
		}
		Arrays.asList(sortPanel.getComponents()).stream().forEach(e->e.setFont(new Font("Aria", Font.ITALIC,fontSize)));
		add(sortPanel, BorderLayout.NORTH);
		this.add(scroll,BorderLayout.CENTER);
		this.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(0,0,0)));
	}
	
	@SuppressWarnings("unchecked")
	public void updateMember(){
		if(type.equals(Type.GestioneSquadriglia)||type.equals(Type.OverviewSquadriglia)){
			this.memList=(List<E>) squadImpl.getMembri().keySet().stream().collect(Collectors.toList());
			updateMemberBotton();
		}
		else if(type.equals(Type.OverviewReparto)){
			this.memList=(List<E>)rep.getMembriSenzaSquadriglia();
			updateMemberBotton();
		}
		else if(type.equals(Type.TasseReparto)){
			this.memList=(List<E>) rep.getMembersNotPaid(Year.now().getValue());
			updateMemberBotton();
		}
		else if(type.equals(Type.TasseSquadrigliaEscursioni)){
			mapPagamenti=new HashMap<>();
			for(Member i: squadImpl.getMembri().keySet()){
				List<Excursion> tmp= new ArrayList<>();
				MyJFrameSingletonImpl.getInstance().getUnit().getContainers().getExcursion().stream()
				.forEach(e->{
					if(e.getNonPaganti().contains(i)){tmp.add(e);};
				});
				if(tmp.size()>0){
					mapPagamenti.put(i, tmp.stream().collect(Collectors.toList()));
				}
			}
			this.memList=(List<E>) mapPagamenti.keySet().stream().collect(Collectors.toList());
			
			updateMemberBotton();
		}
		else if(type.equals(Type.RepartoEventi)){
			this.memList=(List<E>)MyJFrameSingletonImpl.getInstance().getUnit().getContainers().getExcursion()
					.stream().filter(e->!(e instanceof UscitaSquadriglia)).collect(Collectors.toList());
			updateMemberBotton();
		}
		else if(type.equals(Type.SquadrigliaEventi)){
			this.memList=(List<E>)MyJFrameSingletonImpl.getInstance().getUnit().getContainers().getExcursion()
					.stream().filter(e->e instanceof UscitaSquadriglia).collect(Collectors.toList());
			updateMemberBotton();
		}
		else if(type.equals(Type.TasseSquadriglia)){
			this.memList=new ArrayList<>();
			List<Member> mem = squadImpl.getMembri().keySet().stream().collect(Collectors.toList());
			mem.stream().forEach(e->{
				if(rep.getMembersNotPaid(Year.now().getValue()).contains(e)){
					memList.add((E) e);
				}
			});
			updateMemberBotton();
		}
	}
	
	private void updateMemberBotton(){
		SwingUtilities.invokeLater(new Runnable() {
			@SuppressWarnings("unchecked")
			public void run() {
				panelMember.removeAll();
				if(type.equals(Type.GestioneSquadriglia) || type.equals(Type.OverviewReparto)){
					panelMember.add((createButton("<html>Aggiungi<br>Membro"+	((type.equals(Type.GestioneSquadriglia))?
							"<br>in squadriglia<html>":"<br>in reparto</html>")
							,  16,e->{
								if(type.equals(Type.GestioneSquadriglia)){
						(new AddMemberJDialog(MyJFrameSingletonImpl.getInstance().getUnit()
								,(EditableMemberPanelImpl<Member>)me, myOptional.of(squadImpl.getNome()))).setVisible(true);
								}
								else{
									(new AddMemberJDialog(MyJFrameSingletonImpl.getInstance().getUnit()
											,(EditableMemberPanelImpl<Member>)me,myOptional.empty())).setVisible(true);
								}
					})));
				}
				
				memList.stream().forEach(f->{
					panelMember.add(instanceJButton((E)f));
				});
			/*	if(panelMember.getComponents().length==0){
					panelMember.add(createJLabel("noMember", "<html>Al momento non ci sono membri nella squadriglia<br>"
							+ "Visita la sezione di gestione della squadriglia per aggiungerne</html>", fontSizeLabel-7));
				}*/
				panelMember.validate();
				repaint();
				validate();
				}
		});
	}
	
	@SuppressWarnings("unchecked")
	private JButton instanceJButton(E mem){
		
		if(type.equals(Type.GestioneSquadriglia)|| type.equals(Type.OverviewReparto)){
			return createButton("<html>"+((Member)mem).getName()+"<br>"+((Member)mem).getSurname()+"</html>",  16,  e->{
				(new EditMemberInfoJDialog((MemberImpl)mem,(EditableMemberPanelImpl<Member>)me)).setVisible(true);
			});
		}
		else if(type.equals(Type.OverviewSquadriglia)){
			return createButton("<html>"+((Member)mem).getName()+"<br>"+((Member)mem).getSurname()+"</html>",  16,  e->{
				(new ShowMemberInfoJDialog(((Member)mem))).setVisible(true); 
			});
		}
		else if(type.equals(Type.TasseSquadrigliaEscursioni)){
			return createButton("<html>"+((Member)mem).getName()+"<br>"+((Member)mem).getSurname()+"</html>",  16,  e->{
				(new MemberTasseExcursionJDialog(((Member)mem),(EditableMemberPanelImpl<Member>)me)).setVisible(true);;
			});
		}
		else if(type.equals(Type.RepartoEventi)){
			String str="("+((mem instanceof Campo)?"Campo"
					:(mem instanceof EventiDiZona)?"Evento di zona"
							:(mem instanceof Gemellaggi)?"Gemellaggio"
									:"Uscita di reparto")+")";
			return createButton("<html>"+((Excursion)mem).getName()+"<br>"+str+"<br>"+
									((Excursion)mem).getDateStart().toString()+"</html>",16, e->{
				
			});
		}
		else if(type.equals(Type.SquadrigliaEventi)){
			return createButton("<html>"+((Excursion)mem).getName()+"<br>"+"(Uscita Squadriglia)"+"<br>"+
					((Excursion)mem).getDateStart().toString()+"</html>",16,e->{
						
					});
		}
		else{
			return createButton("<html>"+((Member)mem).getName()+"<br>"+((Member)mem).getSurname(), 16, e->{
				(new MemberTasseJDialog((MemberImpl)mem, (EditableMemberPanelImpl<Member>)this)).setVisible(true);
			
			});
		}
		
	}
	
	public void disableScroollBorder(){
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				scroll.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, new Color(0,0,0)));
				
			}
		});
	}
	
		
	
}
