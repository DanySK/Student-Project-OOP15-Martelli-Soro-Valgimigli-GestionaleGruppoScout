package view.gestioneReparto.utility;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.Arrays;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JTextField;

import model.MemberImpl;
import model.Roles;
import model.Squadron;
import model.exception.ObjectNotContainedException;
import view.general_utility.WarningNotice;
import view.gui_utility.MyJFrameSingletonImpl;
import view.gui_utility.MyJPanelImpl;

public class EditMemberInfoJDialog extends JDialog {

	private static final long serialVersionUID = -7599555866898270972L;
	private int fontSize=15;
	private String squadName;
	private Squadron squadImpl;
	
	public EditMemberInfoJDialog(MemberImpl mem){
		super();
		
		try {
			squadImpl=MyJFrameSingletonImpl.getInstance().getUnit().getReparto()
					.getSquadronOfMember(mem);
			squadName=squadImpl.getNome();
		} catch (ObjectNotContainedException e) {
			//new WarningNotice("111111"+e.getMessage());
		}
		
		//pannelli per dare il layout scelto
		MyJPanelImpl panel = new MyJPanelImpl(new BorderLayout());
		MyJPanelImpl panelCenter= new MyJPanelImpl(new GridLayout(0, 2));
		MyJPanelImpl panelBottom=new MyJPanelImpl();
		//promessa e totem
		JTextField totem=new JTextField();
		JComboBox<String> promessa = new JComboBox<>();
		promessa.addItem(mem.getPromise()?"fatta":"da fare");
		promessa.addItem(mem.getPromise()?"da fare":"fatta");
		if(mem.hasTotem())totem.setText(mem.getTotem());
		//tutor
		JTextField tutorName=new JTextField();
		JTextField tutorPhone=new JTextField();
		JTextField tutorMail=new JTextField();
		//squadriglie
		JComboBox<String> squad=new JComboBox<>();
		squad.addItem(squadName);
		MyJFrameSingletonImpl.getInstance().getUnit().getContainers().getSquadrons().stream().forEach(t->{
			if(!t.getNome().equals(squadName))squad.addItem(t.getNome());
		});
		//ruoli
		JComboBox<Roles> role=new JComboBox<>();
		role.addItem(squadImpl.getMembri().get(mem));
		Arrays.asList(Roles.values()).stream().forEach(k->{if(!k.equals(squadImpl.getMembri().get(mem)))role.addItem(k);});
		/*
		 * Tutti i componenti aggiunti nell'ordine desiderato
		 */
		panelCenter.add(panel.createJLabel("Ruolo: ", fontSize));
		panelCenter.add(role);
		panelCenter.add(panel.createJLabel( "Promessa: ", fontSize));
		panelCenter.add(promessa);
		panelCenter.add(panel.createJLabel( "Totem: ", fontSize));
		panelCenter.add(totem);
		panelCenter.add(panel.createJLabel( "Cambia Squadriglia", fontSize));
		panelCenter.add(squad);
		panelCenter.add(panel.createJLabel( "Tutor ", fontSize));
		panelCenter.add(tutorName);
		panelCenter.add(panel.createJLabel( "Tel. Tutor:", fontSize));
		panelCenter.add(tutorPhone);
		panelCenter.add(panel.createJLabel( "Mail Tutor: ",fontSize));
		panelCenter.add(tutorMail);
		//se il tutor è già presente lo setto in modo che l'utente ne sia a conoscenza
		if(mem.getTutor().isPresent()){
			tutorName.setText(mem.getTutor().get().getName().get());
			tutorMail.setText(mem.getTutor().get().getEmail().get());
			tutorPhone.setText(mem.getTutor().get().getPhone().get().toString());
			
		}
		/*
		 * pulsante salva
		 */
		panelBottom.add(panel.createButton("Salva", g->{
			mem.setPromise(((String)promessa.getSelectedItem()).equals("fatta")?true:false);
			//controllo promessa ed eventualmente setto il totem
			if(mem.getPromise())mem.setTotem(totem.getText());
			//aggiorno se il ruolo è stato cambiato
			if(!((Roles)role.getSelectedItem()).equals(squadImpl.getMembri().get(mem))){
				MyJFrameSingletonImpl.getInstance().getUnit().changeMemberFromSq(mem, squadImpl, (Roles)role.getSelectedItem());
			}
			//se è stata cambiata la squadriglia di appartenenza sposto il membro
			if(!((String)squad.getSelectedItem()).equals(squadName)){
				MyJFrameSingletonImpl.getInstance().getUnit().changeMemberFromSq(mem,
						MyJFrameSingletonImpl.getInstance().getUnit().getContainers().findSquadron(((String)squad.getSelectedItem())), 
						MyJFrameSingletonImpl.getInstance().getUnit().getContainers().findSquadron(squadName).getMembri().get(mem));
			}
			//inserisco eventuale tutor(su tuturo giòà presenti non ha effetti
			if(!tutorName.getText().isEmpty()){
				mem.setTutorName(tutorName.getText());
				mem.setTutorMail(tutorMail.getText());
				mem.setTutorPhone(Long.parseLong(tutorPhone.getText()));
			}
			//comunico che è necessario il salvataggio
			MyJFrameSingletonImpl.getInstance().setNeedToSave();
			this.dispose();
		}));
		panelBottom.add(panel.createButton("Annulla", g->{
			this.dispose();
		}));
		
		panel.add(panelCenter, BorderLayout.CENTER);
		panel.add(panelBottom,BorderLayout.SOUTH);
		panel.add(panel.createJLabel("<html><U>Modifica informazioni membro<U><html>", fontSize),BorderLayout.NORTH);
		this.add(panel);
		this.pack();
		this.setLocationRelativeTo(MyJFrameSingletonImpl.getInstance());
		
	}
}
