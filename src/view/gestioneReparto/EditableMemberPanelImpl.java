package view.gestioneReparto;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import control.InfoProjectImpl;
import control.SortMemberImpl;
import control.projectFactoryImpl;
import control.myUtil.Pair;
import control.myUtil.myOptional;
import model.Member;
import model.Roles;
import view.general_utility.WarningNotice;
import view.gui_utility.MyJFrameSingletonImpl;
import view.gui_utility.MyJPanelImpl;

public class EditableMemberPanelImpl extends MyJPanelImpl{
	
	private static final long serialVersionUID = 9037769890822002300L;
	private final boolean editable;
	private List<Member> memList;
	private final int fontSizeLabel=19;
	private final JPanel panelMember;
	private final JPanel sortPanel;
	private final JScrollPane scroll;
	private final String squadName;
	private int fontSize=15;
	private final SortMemberImpl sort;
	public EditableMemberPanelImpl(String squad, boolean edit) {
		super(new BorderLayout());
		this.squadName=squad;
		this.sort=new SortMemberImpl();
		this.panelMember=new JPanel(new GridLayout(0, 4));
		this.scroll = new JScrollPane(panelMember);
		scroll.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(192,192,192)));
		this.sortPanel=new JPanel();
		this.editable=edit;
		this.memList=MyJFrameSingletonImpl.getInstance().getUnit().getContainers().findSquadron(squadName).getMembri()
				.keySet().stream().collect(Collectors.toList());
		sortPanel.add(createJLabel("sort", "Ordina Membri per:", fontSize));
		sortPanel.add(createButton("nome", e->{
			this.memList=sort.sortByName(memList);
			this.updateMember();
		}));
		sortPanel.add(createButton("cognome",e->{
			this.memList=sort.sortBySurname(memList);
			this.updateMember();
		}));
		sortPanel.add(createButton("età",e->{
			this.memList=sort.sortByAge(memList);
			this.updateMember();
		}));
		
		Arrays.asList(sortPanel.getComponents()).stream().forEach(e->e.setFont(new Font("Aria", Font.ITALIC,fontSize)));
		this.add(sortPanel, BorderLayout.NORTH);
		this.add(scroll,BorderLayout.CENTER);
		this.updateMember();
		
		
	}
	
	
	public void updateMember(){
		this.memList=MyJFrameSingletonImpl.getInstance().getUnit().getContainers().findSquadron(squadName).getMembri()
				.keySet().stream().collect(Collectors.toList());
		SwingUtilities.invokeLater(new Runnable(){
		
			@Override
			public void run() {
				panelMember.removeAll();
				if(editable){
					JPanel tmp = new JPanel();
					tmp.add(createButton("<html>Aggiung<br>Membro<html>",  new Color(174,226,84), new Font("Aria", Font.ITALIC,16),e->{
						getAddJDialog().setVisible(true);
					}));
					panelMember.add(tmp);
				}
				memList.stream().forEach(f->{
					JPanel tmp= new JPanel();
					tmp.add(instanceJButton(f));
					panelMember.add(tmp);
				});
				if(panelMember.getComponents().length==0){
					panelMember.add(createJLabel("noMember", "<html>Al momento non ci sono membri nella squadriglia<br>"
							+ "Visita la sezione di gestione della squadriglia per aggiungerne</html>", fontSizeLabel-7));
				}
				panelMember.validate();
			}
			
		});
	}
	
	private JButton instanceJButton(Member mem){
		if(!editable){
			return createButton("<html>"+mem.getName()+"<br>"+mem.getSurname()+"</html>",  new Color(174,226,84), new Font("Aria", Font.ITALIC,16),  e->{
				List<Pair<String, String>> list = (new InfoProjectImpl()).getMemberSpecificalInfo(mem);
				JDialog dial = new JDialog();
				JPanel panel=new JPanel(new BorderLayout());
				JPanel bot= new JPanel();
				JTextArea area= createJTextArea("area", "", false, 18);
				list.forEach(t->{
					area.append(t.getX()+": "+t.getY()+System.lineSeparator());
				});
				area.append("Ruolo: "+MyJFrameSingletonImpl.getInstance().getUnit().getContainers().findSquadron(squadName)
						.getMembri().get(mem));
				panel.add(area,BorderLayout.CENTER);
				bot.add(createButton("Ok", g->{
					dial.dispose();
				}));
				panel.add(bot, BorderLayout.SOUTH);
				dial.add(panel);
				dial.pack();
				dial.setLocationRelativeTo(null);
				dial.setVisible(true);
				
			});
		}
		else return createButton("<html>"+mem.getName()+"<br>"+mem.getSurname()+"</html>",  new Color(174,226,84), new Font("Aria", Font.ITALIC,16),  e->{
			
		});
	}
	private JDialog getAddJDialog(){
		
		JDialog dial=new JDialog();
		JPanel pan=new JPanel(new BorderLayout());
		JPanel center=new JPanel(new GridLayout(0, 2));
		pan.add(center, BorderLayout.CENTER);
		pan.add(createJLabel("northLabel", "<html><U>Dati Membro</U></html>", fontSizeLabel), BorderLayout.NORTH);
		JPanel info=new JPanel(new GridLayout(2, 1));
		info.add(createJLabel("*", "* campi obbligatori", fontSize));
		info.add(createJLabel("**", "Se i campi opzionali vengono lasciati vuoti il membro viene aggiunto incompleto", fontSize));
		JPanel bottom=new JPanel();
		JTextField name=new JTextField();
		JTextField surname=new JTextField();
		JTextField tutorName=new JTextField();
		JTextField tutorPhone=new JTextField();
		JTextField tutorMail=new JTextField();
		JTextField mm=new JTextField();
		JTextField gg= new JTextField();
		JTextField aa= new JTextField();
		gg.setText("gg");
		mm.setText("mm");
		aa.setText("aa");
		JRadioButton sexM=new JRadioButton("Maschio");
		JRadioButton sexF=new JRadioButton("Femmina");
		ButtonGroup sex= new ButtonGroup();
		JComboBox<Roles> roles = new JComboBox<>(Roles.values());
		JComboBox<String> squad=new JComboBox<>();
		squad.addItem("nessuna squadriglia");
		MyJFrameSingletonImpl.getInstance().getUnit().getContainers().getSquadrons().stream()
				.forEach(e->{squad.addItem(e.getNome());});
		sex.add(sexM);
		sex.add(sexF);
		center.add(createJLabel("nome", "* Nome: ", fontSize));
		center.add(name);
		center.add(createJLabel("cognome", "* Cognome: ", fontSize));
		center.add(surname);
		center.add(createJLabel("sesso", "* Sesso: ", fontSize));
		JPanel tmp=new JPanel();
		tmp.add(sexM);
		tmp.add(sexF);
		center.add(tmp);
		center.add(createJLabel("nascita", "* Data di nascita: ", fontSize));
		tmp=new JPanel();
		tmp.add(createJLabel("giorno", "GG:", fontSize-5));
		tmp.add(gg);
		tmp.add(createJLabel("mese", "MM:", fontSize-5));
		tmp.add(mm);
		tmp.add(createJLabel("anno", "AA:", fontSize-5));
		tmp.add(aa);
		center.add(tmp);
		center.add(createJLabel("ruolo", "Ruolo: ", fontSize));
		center.add(roles);
		center.add(createJLabel("squa", "Squadriglia:",fontSize));
		center.add(squad);
		center.add(createJLabel("tutorname*", "Tutor: ", fontSize));
		center.add(tutorName);
		center.add(createJLabel("tutorphone*", "Tel. Tutor: ", fontSize));
		center.add(tutorPhone);
		center.add(createJLabel("tutorMail*", "Mail Tutor: ", fontSize));
		center.add(tutorMail);
		pan.add(center,BorderLayout.CENTER);
		tmp=new JPanel(new GridLayout(2, 1));
		tmp.add(info);
		bottom.add(createButton("Aggiungi", e->{
			
			try{
				Member mem=(tutorName.getText().isEmpty())?
				projectFactoryImpl.getSimpleMember(name.getText(), surname.getText(), 
							LocalDate.of(Integer.parseInt(aa.getText()), Integer.parseInt(mm.getText()), Integer.parseInt(gg.getText())), sexM.isSelected())
					:projectFactoryImpl.getMember(name.getText(), surname.getText(), 
							LocalDate.of(Integer.parseInt(aa.getText()), Integer.parseInt(mm.getText()), Integer.parseInt(gg.getText())), sexM.isSelected(),
							myOptional.of(tutorName.getText()), myOptional.of(tutorMail.getText()), myOptional.of(Long.parseLong(tutorPhone.getText())));
			
				MyJFrameSingletonImpl.getInstance().getUnit().addMember(mem);
				if(!((String)squad.getSelectedItem()).equals("nessuna squadriglia")){
					MyJFrameSingletonImpl.getInstance().getUnit().putMemberInSq(mem, MyJFrameSingletonImpl.getInstance().getUnit()
						.getContainers().findSquadron((String)squad.getSelectedItem()), (Roles)roles.getSelectedItem());
				}
				SwingUtilities.invokeLater(new Runnable(){
					@Override
					public void run() {
						updateMember();
						
					}
					
				});
				dial.dispose();
			}catch(Exception p){
				new WarningNotice("<html>I dati inseriti non sono corretti.<br>Controllare data di nascita e sesso</html>");
				
			}
					
		}));
		bottom.add(createButton("Annulla", e->{
			dial.dispose();
		}));
		tmp.add(bottom);
		pan.add(tmp, BorderLayout.SOUTH);
		dial.add(pan);		
		dial.pack();
		dial.setLocationRelativeTo(MyJFrameSingletonImpl.getInstance());
		return dial;
		
	}
}
