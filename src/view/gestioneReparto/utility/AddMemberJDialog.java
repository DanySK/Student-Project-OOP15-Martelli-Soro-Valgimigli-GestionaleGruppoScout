package view.gestioneReparto.utility;

import java.awt.BorderLayout;

import java.awt.GridLayout;
import java.time.LocalDate;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import control.Unit;
import control.ProjectFactoryImpl;
import control.myUtil.MyOptional;
import model.Member;
import model.Roles;
import view.general_utility.WarningNotice;
import view.gui_utility.EditableMemberPanelImpl;
import view.gui_utility.MyJFrameSingletonImpl;
import view.gui_utility.MyJPanelImpl;


public class AddMemberJDialog extends JDialog{

	private static final long serialVersionUID = 3066382359932767590L;
	private final int fontSizeLabel=19;
	private int fontSize=15;
	private final EditableMemberPanelImpl<Member> panelParent;
	public AddMemberJDialog(Unit unit,EditableMemberPanelImpl<Member> parent, MyOptional<String> squadName ){
		super();
		this.panelParent=parent;
		
		MyJPanelImpl bottom=new MyJPanelImpl();
		JPanel pan=new JPanel(new BorderLayout());
		JPanel center=new JPanel(new GridLayout(0, 2));
		pan.add(center, BorderLayout.CENTER);
		pan.add(bottom.createJLabel( "<html><U>Dati Membro</U></html>", fontSizeLabel), BorderLayout.NORTH);
		JPanel info=new JPanel(new GridLayout(2, 1));
		info.add(bottom.createJLabel( "* campi obbligatori", fontSize));
		info.add(bottom.createJLabel( "Se i campi opzionali vengono lasciati vuoti il membro viene aggiunto incompleto", fontSize));
		/*Dati Membro*/
		JTextField name=new JTextField();
		JTextField surname=new JTextField();
		/*Dati tutor*/
		JTextField tutorName=new JTextField();
		JTextField tutorPhone=new JTextField();
		JTextField tutorMail=new JTextField();
		/*Data di nascita, verrà usato JPanel tmp*/
		JTextField mm=new JTextField();
		JTextField gg= new JTextField();
		JTextField aa= new JTextField();
		/*Sesso*/
		JRadioButton sexM=new JRadioButton("Maschio");
		JRadioButton sexF=new JRadioButton("Femmina");
		ButtonGroup sex= new ButtonGroup();
		/*Ruolo e squadriglia( dipende da se siamo in ruolo o in squadriglia, vedi di seguito)*/
		JComboBox<Roles> roles = new JComboBox<>(Roles.values());
		JComboBox<String> squad=new JComboBox<>();
		squad.addItem("nessuna squadriglia");
		unit.getContainers().getSquadrons().stream().forEach(e->{squad.addItem(e.getNome());});
		sex.add(sexM);
		sex.add(sexF);
		center.add(bottom.createJLabel( "* Nome: ", fontSize));
		center.add(name);
		center.add(bottom.createJLabel( "* Cognome: ", fontSize));
		center.add(surname);
		center.add(bottom.createJLabel( "* Sesso: ", fontSize));
		JPanel tmp=new JPanel();
		tmp.add(sexM);
		tmp.add(sexF);
		center.add(tmp);
		/*questa parte andrebbe modificata utilizzando un JCalendar*/
		center.add(bottom.createJLabel( "* Data di nascita: ", fontSize));
		tmp=new JPanel(new GridLayout(1, 6));
		tmp.add(bottom.createJLabel( "giorno", fontSize-5));
		tmp.add(gg);
		tmp.add(bottom.createJLabel( "mese", fontSize-5));
		tmp.add(mm);
		tmp.add(bottom.createJLabel( "anno", fontSize-5));
		tmp.add(aa);
		center.add(tmp);
		
		center.add(bottom.createJLabel( "Ruolo: ", fontSize));
		center.add(roles);
		/*la lista di squadriglie è disponibile se e solo se siamo in RepartoOverview*/
		if(!squadName.isPresent()){
			center.add(bottom.createJLabel( "Squadriglia:",fontSize));
			center.add(squad);
		}
		/*parte del tutor*/
		center.add(bottom.createJLabel( "Tutor: ", fontSize));
		center.add(tutorName);
		center.add(bottom.createJLabel( "Tel. Tutor: ", fontSize));
		center.add(tutorPhone);
		center.add(bottom.createJLabel( "Mail Tutor: ", fontSize));
		center.add(tutorMail);
		pan.add(center,BorderLayout.CENTER);
		tmp=new JPanel(new GridLayout(2, 1));
		/*aggiungo la parte delle due JLabel con le info*/
		tmp.add(info);
		
		bottom.add(bottom.createButton("Aggiungi", e->{
			
			try{
				/*scelgo costruttore in base alla presenza o meno dei campi del tutore*/
				Member mem=(tutorName.getText().isEmpty())?
				ProjectFactoryImpl.getSimpleMember(name.getText(), surname.getText(), 
							LocalDate.of(Integer.parseInt(aa.getText()), Integer.parseInt(mm.getText()), Integer.parseInt(gg.getText())), sexM.isSelected())
					:ProjectFactoryImpl.getMember(name.getText(), surname.getText(), 
							LocalDate.of(Integer.parseInt(aa.getText()), Integer.parseInt(mm.getText()), Integer.parseInt(gg.getText())), sexM.isSelected(),
							MyOptional.of(tutorName.getText()), MyOptional.of(tutorMail.getText()), MyOptional.of(Long.parseLong(tutorPhone.getText())));
				/*aggiungo il membro senza la squadriglia*/
				unit.addMember(mem);
				/*se siamo in GestioneSquadriglia aggiungo il membro a tale squadriglia*/
				if(squadName.isPresent()){
					unit.putMemberInSq(mem, unit.getContainers().findSquadron(squadName.get()), (Roles)roles.getSelectedItem());
				}
				/*altrimenti se siamo in RepartoOverview ed è stata selezionata una squadriglia inserisco il membro
				 * in tale squadriglia
				 */
				else if(!squadName.isPresent() && !((String)squad.getSelectedItem()).equals("nessuna squadriglia")){
					unit.putMemberInSq(mem,unit.getContainers().findSquadron((String)squad.getSelectedItem()), (Roles)roles.getSelectedItem());
				}
				SwingUtilities.invokeLater(new Runnable(){
					@Override
					public void run() {
						panelParent.updateMember();
						
					}
					
				});
				this.dispose();
			}catch(Exception p){
				new WarningNotice("I dati inseriti non sono corretti."+System.lineSeparator()+"Controllare data di nascita e sesso");
				
			}
					
		}));
		bottom.add(bottom.createButton("Annulla", e->{
			this.dispose();
		}));
		tmp.add(bottom);
		pan.add(tmp, BorderLayout.SOUTH);
		this.add(pan);		
		this.pack();
		this.setLocationRelativeTo(MyJFrameSingletonImpl.getInstance());
	}

}
