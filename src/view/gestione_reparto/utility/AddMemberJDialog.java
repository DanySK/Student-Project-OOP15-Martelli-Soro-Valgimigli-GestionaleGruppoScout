package view.gestione_reparto.utility;

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
	private final static int FONTSIZELABEL=19;
	private final static int FONTSIZE=15;
	private final EditableMemberPanelImpl<Member> panelParent;
	public AddMemberJDialog(final Unit unit,final EditableMemberPanelImpl<Member> parent, final MyOptional<String> squadName ){
		super();
		this.panelParent=parent;
		final MyJPanelImpl bottom=new MyJPanelImpl();
		final JPanel pan=new JPanel(new BorderLayout());
		final JPanel center=new JPanel(new GridLayout(0, 2));
		pan.add(center, BorderLayout.CENTER);
		pan.add(bottom.createJLabel( "<html><U>Dati Membro</U></html>", FONTSIZELABEL), BorderLayout.NORTH);
		final JPanel info=new JPanel(new GridLayout(2, 1));
		info.add(bottom.createJLabel( "* campi obbligatori", FONTSIZE));
		info.add(bottom.createJLabel( "Se i campi opzionali vengono lasciati vuoti il membro viene aggiunto incompleto", FONTSIZE));
		/*Dati Membro*/
		final JTextField name=new JTextField();
		final JTextField surname=new JTextField();
		/*Dati tutor*/
		final JTextField tutorName=new JTextField();
		final JTextField tutorPhone=new JTextField();
		final JTextField tutorMail=new JTextField();
		/*Data di nascita, verrà usato JPanel tmp*/
		final JTextField mm=new JTextField();
		final JTextField gg= new JTextField();
		final JTextField aa= new JTextField();
		/*Sesso*/
		final JRadioButton sexM=new JRadioButton("Maschio");
		final JRadioButton sexF=new JRadioButton("Femmina");
		final ButtonGroup sex= new ButtonGroup();
		/*Ruolo e squadriglia( dipende da se siamo in ruolo o in squadriglia, vedi di seguito)*/
		final JComboBox<Roles> roles = new JComboBox<>(Roles.values());
		final JComboBox<String> squad=new JComboBox<>();
		squad.addItem("nessuna squadriglia");
		unit.getContainers().getSquadrons().stream().forEach(e->{squad.addItem(e.getNome());});
		sex.add(sexM);
		sex.add(sexF);
		center.add(bottom.createJLabel( "* Nome: ", FONTSIZE));
		center.add(name);
		center.add(bottom.createJLabel( "* Cognome: ", FONTSIZE));
		center.add(surname);
		center.add(bottom.createJLabel( "* Sesso: ", FONTSIZE));
		JPanel tmp=new JPanel();
		tmp.add(sexM);
		tmp.add(sexF);
		center.add(tmp);
		/*questa parte andrebbe modificata utilizzando un JCalendar*/
		center.add(bottom.createJLabel( "* Data di nascita: ", FONTSIZE));
		tmp=new JPanel(new GridLayout(1, 6));
		tmp.add(bottom.createJLabel( "giorno", FONTSIZE-5));
		tmp.add(gg);
		tmp.add(bottom.createJLabel( "mese", FONTSIZE-5));
		tmp.add(mm);
		tmp.add(bottom.createJLabel( "anno", FONTSIZE-5));
		tmp.add(aa);
		center.add(tmp);
		
		center.add(bottom.createJLabel( "Ruolo: ", FONTSIZE));
		center.add(roles);
		/*la lista di squadriglie è disponibile se e solo se siamo in RepartoOverview*/
		if(!squadName.isPresent()){
			center.add(bottom.createJLabel( "Squadriglia:",FONTSIZE));
			center.add(squad);
		}
		/*parte del tutor*/
		center.add(bottom.createJLabel( "Tutor: ", FONTSIZE));
		center.add(tutorName);
		center.add(bottom.createJLabel( "Tel. Tutor: ", FONTSIZE));
		center.add(tutorPhone);
		center.add(bottom.createJLabel( "Mail Tutor: ", FONTSIZE));
		center.add(tutorMail);
		pan.add(center,BorderLayout.CENTER);
		tmp=new JPanel(new GridLayout(2, 1));
		/*aggiungo la parte delle due JLabel con le info*/
		tmp.add(info);
		
		bottom.add(bottom.createButton("Aggiungi", e->{
			
			try{
				/*scelgo costruttore in base alla presenza o meno dei campi del tutore*/
				
				unit.addMember((tutorName.getText().isEmpty())?
				ProjectFactoryImpl.getSimpleMember(name.getText(), surname.getText(), 
							LocalDate.of(Integer.parseInt(aa.getText()), Integer.parseInt(mm.getText()), Integer.parseInt(gg.getText())), sexM.isSelected())
					:ProjectFactoryImpl.getMember(name.getText(), surname.getText(), 
							LocalDate.of(Integer.parseInt(aa.getText()), Integer.parseInt(mm.getText()), Integer.parseInt(gg.getText())), sexM.isSelected(),
							MyOptional.of(tutorName.getText()), MyOptional.of(tutorMail.getText()), MyOptional.of(Long.parseLong(tutorPhone.getText()))));
				/*aggiungo il membro senza la squadriglia*/
				
				/*se siamo in GestioneSquadriglia aggiungo il membro a tale squadriglia*/
				if(squadName.isPresent()){
					unit.putMemberInSq(unit.getContainers().getMember(name.getText(), surname.getText())
							, unit.getContainers().findSquadron(squadName.get()), (Roles)roles.getSelectedItem());
				}
				/*altrimenti se siamo in RepartoOverview ed è stata selezionata una squadriglia inserisco il membro
				 * in tale squadriglia
				 */
				else if(!squadName.isPresent() && !((String)squad.getSelectedItem()).equals("nessuna squadriglia")){
					unit.putMemberInSq(unit.getContainers().getFreeMember().stream().filter(t->(t.getName().equals(name.getText())&& t.getSurname().equals(surname.getText()))).findFirst().get(),
							unit.getContainers().findSquadron((String)squad.getSelectedItem()), (Roles)roles.getSelectedItem());
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
