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

import control.myUtil.myOptional;
import model.Member;
import model.MemberImpl;
import model.Roles;
import model.Squadron;
import view.general_utility.WarningNotice;
import view.gestioneReparto.utility.EditMemberInfoJDialog;
import view.gestioneReparto.utility.ShowMemberInfoJDialog;

public class SearchElementJDialog<E,K> extends JDialog {
	
	public enum SearchType{
		AssignCharge,
		ShowMember,
		EditMember,
		Excursion;
	}

	private static final long serialVersionUID = -2330303224987114376L;
	private final MyJPanelImpl panel;
	private JTextField first;
	private JTextField second;
	//private MyJPanelImpl panelCenterExternal;
	private MyJPanelImpl panelCenter;
	private MyJPanelImpl panelButton;
	private final int fontSizeLabel=15;
	private final E elem;
	private final SearchType type;
	private List<K> matches;
	private String charge;
	private JPanel parent;
	public SearchElementJDialog(SearchType t,E param,myOptional<String> charge, JPanel parent){
		super();
		this.elem=param;
		this.type=t;
		panel=new MyJPanelImpl(new BorderLayout());
		panelButton=new MyJPanelImpl(new FlowLayout(FlowLayout.LEFT));
		
		if(t.equals(SearchType.AssignCharge)||t.equals(SearchType.ShowMember) || t.equals(SearchType.EditMember)){
			this.parent=parent;
			this.charge=charge.orElse("");
			panel.add(panel.createJLabel( "Inserire almeno uno dei campi richiesti", fontSizeLabel+2),BorderLayout.NORTH);
			panelCenter=new MyJPanelImpl(new GridLayout(2,2));
			first=new JTextField();
			second=new JTextField();
			panelCenter.add(panel.createJLabel("Nome: ", fontSizeLabel));
			panelCenter.add(first);
			panelCenter.add(panel.createJLabel("Cognome: ", fontSizeLabel));
			panelCenter.add(second);
			
		}
		panelButton.add(panel.createButton("Annulla", e->{
			this.dispose();
		}));
		panelButton.add(panel.createButton("Cerca", e->{
			searchMatches();
		}));	
		panel.add(panelCenter,BorderLayout.CENTER);
		panel.add(panelButton,BorderLayout.SOUTH);
		this.add(panel);
		this.pack();
		this.setLocationRelativeTo(MyJFrameSingletonImpl.getInstance());
		this.setVisible(true);
	}
	
	@SuppressWarnings("unchecked")
	private void searchMatches(){
		if(type.equals(SearchType.AssignCharge) || type.equals(SearchType.ShowMember) || type.equals(SearchType.EditMember)){
			Map<Member,Roles> MemberSquad= MyJFrameSingletonImpl.getInstance().getUnit().getContainers()
					.findSquadron((String)elem).getMembri();
			matches=(List<K>)((!first.getText().isEmpty() &&!second.getText().isEmpty())?
					MemberSquad.keySet().stream().filter(t->t.getName().equals(first.getText())&&t.getSurname().equals(second.getText()))
					.collect(Collectors.toList())
					:(!first.getText().isEmpty())?MemberSquad.keySet().stream().filter(t->t.getName().equals(first.getText()))
							.collect(Collectors.toList())
							:MemberSquad.keySet().stream().filter(l->l.getSurname().equals(second.getText())).collect(Collectors.toList())
						);
			if(matches.isEmpty()){
				new WarningNotice("Nessun membro trovato con quel nome."+System.lineSeparator()
						+ "Controllare i dati inseriti e riprovare");
			}
			else{
				tooItemAndExecute();
			}
		}
		
	}
	
	@SuppressWarnings("unchecked")
	private void tooItemAndExecute(){
		JDialog dialInternal=new JDialog();
		Squadron squadImpl=MyJFrameSingletonImpl.getInstance().getUnit().getContainers().findSquadron((String)elem);
		MyJPanelImpl panInternal=new MyJPanelImpl(new BorderLayout());
		panInternal.add(panInternal.createJLabel( "Scegliere la corrispondenza desiderata", fontSizeLabel),BorderLayout.NORTH);
		MyJPanelImpl panMember= new MyJPanelImpl(new GridLayout(0, 1));
		MyJPanelImpl paneSelect=new MyJPanelImpl(new GridLayout(0,1));
		MyJPanelImpl panBot=new MyJPanelImpl(new BorderLayout());
		JTextArea area= new JTextArea();
		area.setEditable(false);
		panBot.add(panBot.createButton("Annulla", u->{
			dialInternal.dispose();
		}),BorderLayout.EAST);
		panInternal.add(panBot,BorderLayout.SOUTH);
		panInternal.add(panMember,BorderLayout.CENTER);
		panInternal.add(paneSelect,BorderLayout.EAST);
		if(type.equals(SearchType.AssignCharge)){
			matches.stream().forEach(e->{
				area.append("Nome: "+((Member)e).getName()+System.lineSeparator());
				area.append("Cognome: "+((Member)e).getSurname()+System.lineSeparator());
				area.append("Nascita: "+((Member)e).getBirthday().toString());
				panMember.add(area);
				paneSelect.add(paneSelect.createButton("Scegli", o->{
					dialInternal.dispose();
					String method="set"+charge.substring(0, charge.length()-2)+((charge.equals("Vice: "))?"capoSq":"Sq");
					try {
						if(charge.equals("Capo: ") && squadImpl.isCapoPresent()){
							squadImpl.removeCapo();
						}
						else if(charge.equals("Vice: ") && squadImpl.isVicecapoPresent()){
							squadImpl.removeVice();
						}
						else{
							if(squadImpl.isTricecapoPresent())squadImpl.removeTrice();
						}
						Method mt=MyJFrameSingletonImpl.getInstance().getUnit()
								.getContainers().findSquadron((String)elem).getClass().getDeclaredMethod(method, Member.class);
						//lancio metodo
						mt.invoke(squadImpl,(Member)e);
						MyJFrameSingletonImpl.getInstance().setNeedToSave();//setto che sono avvenuti dei cambiamenti
						new WarningNotice("Membro trovato e settato come "+charge.toLowerCase()+System.lineSeparator()
								+"Ricordati di salvare o perderai le modifiche");
						((EditableInfoPanelImpl)parent).updateInfo();
			
					}catch(Exception w){
						new WarningNotice(w.getMessage());
					}
				}));
				
			});
			panInternal.add(panMember, BorderLayout.CENTER);
			panInternal.add(paneSelect,BorderLayout.EAST);
			panInternal.add(panBot,BorderLayout.SOUTH);
			dialInternal.add(panInternal);
			dialInternal.pack();
			dialInternal.setLocationRelativeTo(this);
			dialInternal.setVisible(true);
		}
		else if(type.equals(SearchType.ShowMember) || type.equals(SearchType.EditMember)){
			matches.stream().forEach(e->{
				area.append("Nome: "+((Member)e).getName()+System.lineSeparator());
				area.append("Cognome: "+((Member)e).getSurname()+System.lineSeparator());
				area.append("Nascita: "+((Member)e).getBirthday().toString());
				panMember.add(area);
				paneSelect.add(paneSelect.createButton("Vedi", o->{
					dialInternal.dispose();
					if(type.equals(SearchType.ShowMember)){
						new ShowMemberInfoJDialog((Member)e).setVisible(true);
					}
					else{
						new EditMemberInfoJDialog((MemberImpl)e, (EditableMemberPanelImpl<Member>)parent).setVisible(true);
					}
				}));
			});
			panInternal.add(panMember, BorderLayout.CENTER);
			panInternal.add(paneSelect,BorderLayout.EAST);
			panInternal.add(panBot,BorderLayout.SOUTH);
			dialInternal.add(panInternal);
			dialInternal.pack();
			dialInternal.setLocationRelativeTo(this);
			dialInternal.setVisible(true);
		}
		
	}
	
	
}
