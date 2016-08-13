package view.gestioneEventi.utility;

import java.awt.BorderLayout;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.time.LocalDate;

import javax.swing.JDialog;
import javax.swing.JTextField;

import control.myUtil.myOptional;
import model.CampoImpl;
import view.general_utility.WarningNotice;
import view.gui_utility.MyJFrameSingletonImpl;
import view.gui_utility.MyJPanel;
import view.gui_utility.MyJPanelImpl;
import view.gestioneEventi.EventiReparto.EventiRepartoPane;;

public class AddExcursionJDialog extends JDialog {
	public enum TypeExcursion{
		Campo,
		Gemellaggio,
		Evento_di_Zona,
		Uscita,
		Uscita_Squadriglia;
		
	}
	private static final long serialVersionUID = -6908793366965929992L;
	private final int fontSize=19;
	public AddExcursionJDialog(TypeExcursion type, myOptional<String> squadName, MyJPanel caller){
		MyJPanelImpl panel=new MyJPanelImpl(new BorderLayout());
		MyJPanelImpl panelInter= new MyJPanelImpl(new GridLayout(0, 2));
		MyJPanelImpl panelBot=new MyJPanelImpl(new FlowLayout(FlowLayout.RIGHT));
		JTextField nome=new JTextField();
		JTextField price=new JTextField();
		MyJPanelImpl data=new MyJPanelImpl(new GridLayout(1,6));
		data.add(panel.createJLabel("giorno",fontSize));
		JTextField gg=new JTextField();
		data.add(gg);
		data.add(panel.createJLabel("mese", fontSize));
		JTextField mm=new JTextField();
		data.add(mm);
		data.add(panel.createJLabel("anno", fontSize));
		JTextField aa=new JTextField();
		data.add(aa);
		
		JTextField ggF=new JTextField();
		JTextField mmF=new JTextField();
		JTextField aaF=new JTextField();
		
		panel.add(panel.createJLabel("Nuovo/a"+ type.toString(), fontSize+2));
		//casi base
		panelInter.add(panel.createJLabel("Nome: ", fontSize));
		panelInter.add(nome);
		panelInter.add(panel.createJLabel("Prezzo: ", fontSize));
		panelInter.add(price);
		panelInter.add(panel.createJLabel("Data Inizio: ", fontSize));
		panelInter.add(data);
		if(!type.equals(TypeExcursion.Uscita)){
			MyJPanelImpl dataFine=new MyJPanelImpl(new GridLayout(1,6));
			dataFine.add(panel.createJLabel("giorno",fontSize));
			dataFine.add(ggF);
			dataFine.add(panel.createJLabel("mese", fontSize));
			dataFine.add(mmF);
			dataFine.add(panel.createJLabel("anno", fontSize));
			dataFine.add(aaF);
			panelInter.add(panel.createJLabel("Data Fine:", fontSize));
			panelInter.add(dataFine);
		}
		else{
			
		}
		
		
		//JButton in basso
		
		panelBot.add(panelBot.createButton("Annulla", e->{
			this.dispose();
		}));
		panelBot.add(panelBot.createButton("Aggiungi", e->{
			if(type.equals(TypeExcursion.Campo)){
				LocalDate start=LocalDate.of(Integer.parseInt(aa.getText()), Integer.parseInt(mm.getText()), Integer.parseInt(gg.getText()));
				LocalDate end=LocalDate.of(Integer.parseInt(aaF.getText()), Integer.parseInt(mmF.getText()), Integer.parseInt(ggF.getText()));
				try {
					MyJFrameSingletonImpl.getInstance().getUnit().addExcursion(new CampoImpl(start, end,
							MyJFrameSingletonImpl.getInstance().getUnit().getReparto(), nome.getText()));
				((EventiRepartoPane)caller).updateEventi();
				} catch (Exception e1) {
					new WarningNotice(e1.getMessage());
				}
			}
			this.dispose();
			MyJFrameSingletonImpl.getInstance().setNeedToSave();
			
			
		}));
		
		panel.add(panelInter,BorderLayout.CENTER);
		panel.add(panelBot,BorderLayout.SOUTH);
		this.add(panel);
		this.pack();
		this.setLocationRelativeTo(MyJFrameSingletonImpl.getInstance());
		this.setVisible(true);
		
	}
}
