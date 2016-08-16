package view.gestioneEventi.utility;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import control.Unit;
import control.ProjectFactoryImpl;
import control.myUtil.myOptional;
import model.Excursion;
import view.general_utility.WarningNotice;
import view.gestioneEventi.EventiReparto.EventiRepartoPane;
import view.gestioneEventi.EventiSquadriglia.EventiSquadrigliaPanel;
import view.gui_utility.MyJFrameSingletonImpl;
import view.gui_utility.MyJPanel;
import view.gui_utility.MyJPanelImpl;;

public class AddExcursionJDialog extends JDialog {
	public enum TypeExcursion{
		Campo,
		Gemellaggio,
		Evento_di_Zona,
		Uscita,
		Uscita_Squadriglia;
		
	}
	private static final long serialVersionUID = -6908793366965929992L;
	private final int fontSize=15;
	private final TypeExcursion type;
	private final MyJPanelImpl panel=new MyJPanelImpl(new BorderLayout());
	private final MyJPanelImpl panelInter= new MyJPanelImpl(new GridLayout(0, 2));
	private final MyJPanelImpl panelBot=new MyJPanelImpl(new FlowLayout(FlowLayout.RIGHT));
	private final JTextField nome=new JTextField();
	private final JTextField price=new JTextField();
	private final MyJPanelImpl data=new MyJPanelImpl(new GridLayout(1,6));
	private final JTextField gg=new JTextField();
	private final JTextField mm=new JTextField();
	private final JTextField aa=new JTextField();
	private final JTextField durata=new JTextField("GG");
	private final JTextField ggF=new JTextField();
	private final JTextField mmF=new JTextField();
	private final JTextField aaF=new JTextField();
	private  MyJPanelImpl dataFine=new MyJPanelImpl(new GridLayout(1,6));
	private final JTextArea area=new JTextArea();
	private boolean perData=false;
	private List<String>reparti=new ArrayList<>();
	private final Unit unit;
	private LocalDate start;
	private LocalDate end;
	private String squadName;
	
	public AddExcursionJDialog(TypeExcursion type, myOptional<String> squadName, MyJPanel caller){
		super();
		if(squadName.isPresent()){
			this.squadName=squadName.get();
		}
		this.type=type;
		this.unit=MyJFrameSingletonImpl.getInstance().getUnit();
		panel.add(panel.createJLabel("Nuovo/a"+ type.toString(), fontSize+2),BorderLayout.NORTH);
		//casi base
		panelInter.add(panel.createJLabel("Nome: ", fontSize));
		panelInter.add(nome);
		panelInter.add(panel.createJLabel("Prezzo: ", fontSize));
		panelInter.add(price);
		panelInter.add(panel.createJLabel("Data Inizio: ", fontSize));
		
		data.add(panel.createJLabel("giorno",fontSize));
		data.add(gg);
		data.add(panel.createJLabel("mese", fontSize));
		data.add(mm);
		data.add(panel.createJLabel("anno", fontSize));
		data.add(aa);
		panelInter.add(data);
		if(!type.equals(TypeExcursion.Uscita)){
			panelInter.add(panel.createJLabel("Fine: ", fontSize));
			dataFine=new MyJPanelImpl();
			dataFine.add(panel.createButton("Data", 13,e->{
				perData=true;
				SwingUtilities.invokeLater(new Runnable() {
					
					public void run() {
						dataFine.removeAll();
						dataFine.setLayout(new GridLayout(1,6));
						dataFine.add(panel.createJLabel("giorno",fontSize));
						dataFine.add(ggF);
						dataFine.add(panel.createJLabel("mese", fontSize));
						dataFine.add(mmF);
						dataFine.add(panel.createJLabel("anno", fontSize));
						dataFine.add(aaF);
						dataFine.validate();
						dataFine.repaint();
					}
				});
			}));
			dataFine.add(panel.createButton("Durata", 13,e->{
				SwingUtilities.invokeLater(new Runnable() {
					
					@Override
					public void run() {
						dataFine.removeAll();
						dataFine.add(durata);
						dataFine.validate();
						dataFine.repaint();
						
					}
				});
			}));
			panelInter.add(dataFine);
		}
		
		if(type.equals(TypeExcursion.Gemellaggio) || type.equals(TypeExcursion.Evento_di_Zona)){
			panelInter.add(panel.createJLabel("Altri reparti", fontSize));
			panelInter.add(panel.createButton("Aggiungi",12, o->{
				JDialog dial=new JDialog();
				MyJPanelImpl pan= new MyJPanelImpl(new BorderLayout());
				MyJPanelImpl panN=new MyJPanelImpl(new GridLayout(2, 1));
				MyJPanelImpl panS=new MyJPanelImpl(new FlowLayout(FlowLayout.RIGHT));
				panN.add(pan.createJLabel("<html><U>Aggiungi Reparti</U><html>", fontSize+2));
				panN.add(pan.createJLabel("<html>Aggiungere i nomi dei reparti<br>"
						+ "separando un reparto dall'altro con il tasto \"INVIO\"</html>", fontSize));
				pan.add(panN,BorderLayout.NORTH);
			
				area.setPreferredSize(new Dimension(area.getWidth(),this.getHeight()));
				pan.add(area,BorderLayout.CENTER);
				panS.add(pan.createButton("Annulla", r->{
					dial.dispose();
				}));
				panS.add(pan.createButton("Aggiungi", r->{
				   reparti =Arrays.asList(area.getText().split(System.lineSeparator())) ;
				   dial.dispose();
				}));
				pan.add(panS,BorderLayout.SOUTH);
				dial.add(pan);
				dial.pack();
				dial.setLocationRelativeTo(this);
				dial.setVisible(true);
				
				
			}));
		}
		
		
		//JButton in basso
		
		panelBot.add(panelBot.createButton("Annulla", 15,e->{
			this.dispose();
		}));
		panelBot.add(panelBot.createButton("Aggiungi", 15,e->{
			try{
				unit.addExcursion(getMethod());
				if(type.equals(TypeExcursion.Uscita_Squadriglia)){
					((EventiSquadrigliaPanel)caller).updateEventi();
				}
				else{
					((EventiRepartoPane)caller).updateEventi();
				}
				MyJFrameSingletonImpl.getInstance().setNeedToSave();
			}catch(Exception l){
				new WarningNotice(l.getMessage());
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
	public Excursion getMethod() {
		try{
		Excursion exc;
		start=LocalDate.of(Integer.parseInt(aa.getText()), Integer.parseInt(mm.getText()), Integer.parseInt(gg.getText()));
		if(type.equals(TypeExcursion.Uscita)){
			exc=ProjectFactoryImpl.getStdExcursion(start, unit.getReparto(), nome.getText());
		}
		else if(perData){
			
			end=LocalDate.of(Integer.parseInt(aaF.getText()), Integer.parseInt(mmF.getText()), Integer.parseInt(ggF.getText()));
			if(type.equals(TypeExcursion.Campo)){
				exc=ProjectFactoryImpl.getCamp(start, end, unit.getReparto(),nome.getText());
			}
			else if(type.equals(TypeExcursion.Gemellaggio)){
				exc=ProjectFactoryImpl.getEventTwoUnit(start,end,unit.getReparto(),nome.getText(),reparti);
			}
			else if(type.equals(TypeExcursion.Evento_di_Zona)){
				exc=ProjectFactoryImpl.getLocalEvent(start, end, unit.getReparto(), nome.getText(), reparti);
				
			}
			else {//UscitaSquad
				exc=ProjectFactoryImpl.getSqExcursion(start, end, unit.getContainers().findSquadron(squadName), nome.getText());
			}
			
		}
		
		else{
			if(type.equals(TypeExcursion.Campo)){
				exc=ProjectFactoryImpl.getCamp(start, Integer.parseInt(durata.getText()), unit.getReparto(),nome.getText());
			}
			else if(type.equals(TypeExcursion.Gemellaggio)){
				exc=ProjectFactoryImpl.getEventMoreUnit(start, Integer.parseInt(durata.getText()), unit.getReparto(), nome.getText(), reparti);
			}
			else if(type.equals(TypeExcursion.Evento_di_Zona)){
				exc=ProjectFactoryImpl.getLocalEvent(start,Integer.parseInt(durata.getText()), unit.getReparto(), nome.getText(),reparti);
			}
			else {//UscitaSquad
				exc=ProjectFactoryImpl.getSqExcursion(start, Integer.parseInt(durata.getText()), unit.getContainers().findSquadron(squadName), nome.getText());
			}
			
		}
		exc.setPrice(Integer.parseInt(price.getText()));
		return exc;
		}catch(Exception e){
			new WarningNotice(e.getMessage());
		}
		return null;
	}
}
