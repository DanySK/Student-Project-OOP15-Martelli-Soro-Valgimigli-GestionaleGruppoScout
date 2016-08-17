package view.gestioneEventi.utility;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EventObject;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import control.InfoProjectImpl;
import control.myUtil.MyOptional;
import model.EventiDiZona;
import model.Excursion;
import model.Gemellaggi;
import model.GemellaggiImpl;
import model.exception.ObjectAlreadyContainedException;
import model.exception.ObjectNotContainedException;
import view.general_utility.WarningNotice;
import view.gui_utility.EditableMemberPanelImpl;
import view.gui_utility.MyJFrameSingletonImpl;
import view.gui_utility.MyJPanelImpl;
import view.gui_utility.SearchElementJDialog;
import view.gui_utility.SearchElementJDialog.SearchType;

public class ShowEditExcursion extends JDialog{
	
	private static final long serialVersionUID = 9016334026262612993L;
	MyJPanelImpl panel=new MyJPanelImpl(new BorderLayout());
	MyJPanelImpl panelCenterOuter = new MyJPanelImpl(new BorderLayout());
	MyJPanelImpl panelCenter=new MyJPanelImpl(new GridLayout(0, 2));
	MyJPanelImpl panelBot=new MyJPanelImpl(new FlowLayout(FlowLayout.RIGHT));
	EditableMemberPanelImpl<Excursion> parent;
	MyJPanelImpl panelEdit = new MyJPanelImpl(new GridLayout(0,1));
	List<String>reparti=new ArrayList<>();
	int fontSize = 15;
	Excursion exc;
	public ShowEditExcursion(Excursion exc, EditableMemberPanelImpl<Excursion> parent){
		super();
		this.parent=parent;
		this.exc=exc;
		panel.add(panelCenterOuter,BorderLayout.CENTER);
		panelCenterOuter.add(panelCenter,BorderLayout.CENTER);
		updateExcursion();
		panelBot.add(panelBot.createButton("Annulla",13, e->{
			this.dispose();
		}));
		panelBot.add(panelBot.createButton("Ok",13, e->{
			parent.updateMember();
			this.dispose();
		}));
		panel.add(panelBot, BorderLayout.SOUTH);
		panelCenterOuter.add(panelEdit,BorderLayout.EAST);
		this.add(panel);
		
	}
	private final void updateExcursion(){
		final Map<String,List<String>> info= (new InfoProjectImpl()).getExcursionSpacificalInfo(exc);
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				panelCenter.removeAll();
				panelEdit.removeAll();
				panelCenter.add(panelCenter.createJLabel("Nome: ", fontSize));
				panelCenter.add(panelCenter.createJLabel(info.get("Nome").get(0), fontSize));
				panelEdit.add(getJButton("Nome"));
				
				panelCenter.add(panelCenter.createJLabel("Luogo: ", fontSize));
				panelCenter.add(panelCenter.createJLabel(info.get("Dove").get(0), fontSize));
				panelEdit.add(getJButton("Dove"));
				
				panelCenter.add(panelCenter.createJLabel("Inizio: ", fontSize));
				panelCenter.add(panelCenter.createJLabel(info.get("Data").get(0), fontSize));
				panelEdit.add(getJButton("Quando"));
				
				panelCenter.add(panelCenter.createJLabel("Fine: ", fontSize));
				panelCenter.add(panelCenter.createJLabel(info.get("Data").get(0), fontSize));
				panelEdit.add(getJButton("Fine"));
				
				panelCenter.add(panelCenter.createJLabel("Prezzo: ", fontSize));
				panelCenter.add(panelCenter.createJLabel(info.get("Prezzo").get(0), fontSize));
				panelEdit.add(getJButton("Prezzo"));
				
				panelCenter.add(panelCenter.createJLabel("Partecipanti", fontSize));
				panelCenter.add(panelCenter.createButton("vedi", 10, e->{
					JDialog dial = new JDialog();
					MyJPanelImpl panel = new MyJPanelImpl(new BorderLayout());
					EditableMemberPanelImpl<Excursion> panCenter= new EditableMemberPanelImpl<Excursion>
						(EditableMemberPanelImpl.Type.EXCPARTECIPANTI,MyOptional.of(exc.getName()));
					panel.add(panCenter);
					dial.add(panel);
					dial.setPreferredSize(MyJFrameSingletonImpl.getInstance().getContenentPane().getSize());
					dial.pack();
					dial.setLocationRelativeTo(MyJFrameSingletonImpl.getInstance());
					dial.setVisible(true);
				}));
				panelEdit.add(panelCenter.createButton("<html>Aggiungi/<br>Rimuovi</html>",10,e->{
					new SearchElementJDialog<>(SearchType.addMemberExc,exc.getName(), MyOptional.empty(), null);
				}));
				
				if(exc instanceof EventiDiZona || exc instanceof GemellaggiImpl ){
					panelCenter.add(panelCenter.createJLabel("Reparti", fontSize));
					panelCenter.add(panelCenter.createButton("Vedi"	,10, e->{
						new OtherUnitJDialog(exc, false);
					}));
					panelEdit.add(panelEdit.createButton("<html>Aggiungi/<br>Rimuovi</html>",10,e->{
						new OtherUnitJDialog(exc,true);
					}));
					
				}
				
				
				
				panel.validate();
				panel.repaint();
				
				pack();
				setLocationRelativeTo(MyJFrameSingletonImpl.getInstance());
				setVisible(true);
				
			}
			
		});
		
		
		
		//if(exc instanceof )
	}
	private JButton getJButton(String type){
		return panelEdit.createButton("Edit",10, e->{
			JDialog dial=new JDialog();
			MyJPanelImpl pan=new MyJPanelImpl(new BorderLayout());
			if(type.equals("Nome") || type.equals("Dove") || type.equals("Prezzo")){
				JTextField txt=new JTextField();
				pan.add(txt,BorderLayout.CENTER);
				pan.add(pan.createButton("OK", t->{
					if(!txt.getText().isEmpty()){
						if(type.equals("Nome")){exc.setName(txt.getText());}
						else if(type.equals("Dove")){exc.setPlace(txt.getText());}
						else if(type.equals("Prezzo")){exc.setPrice(Integer.parseInt(txt.getText()));}
					}
					dial.dispose();
					updateExcursion();
				}),BorderLayout.SOUTH);
			}
			else{
				MyJPanelImpl date = new MyJPanelImpl(new GridLayout(1, 0));
				JTextField mm=new JTextField();
				JTextField aa=new JTextField();
				JTextField gg=new JTextField();
				date.add(date.createJLabel("Giorno: ", fontSize));
				date.add(gg);
				date.add(date.createJLabel("Mese: ", fontSize));
				date.add(mm);
				date.add(date.createJLabel("Anno: ",fontSize));
				date.add(aa);
				pan.add(date,BorderLayout.CENTER);
				
				pan.add(pan.createButton("OK", t->{
					try {
						if(type.equals("Fine")){
							if(!gg.getText().isEmpty() && !aa.getText().isEmpty() && !mm.getText().isEmpty()){
								exc.setDateEnd((LocalDate.of(Integer.parseInt(aa.getText()), 
									Integer.parseInt(mm.getText()),Integer.parseInt(gg.getText()))));
							}
							
						}
						else{
							if(!gg.getText().isEmpty() && !aa.getText().isEmpty() && !mm.getText().isEmpty()){
								exc.setDateStart(LocalDate.of(Integer.parseInt(aa.getText()), 
									Integer.parseInt(mm.getText()),Integer.parseInt(gg.getText())));
							}
						}
					} catch(Exception E){
						new WarningNotice(E.getMessage());
					}
					dial.dispose();
					updateExcursion();
				
				}),BorderLayout.SOUTH);
				
			}
			
			dial.add(pan);
			dial.pack();
			dial.setLocationRelativeTo(MyJFrameSingletonImpl.getInstance());
			dial.setVisible(true);
		});
	}
	
	
	
}
