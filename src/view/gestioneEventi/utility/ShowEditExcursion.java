package view.gestioneEventi.utility;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import control.InfoProjectImpl;
import model.Excursion;
import model.exception.IllegalDateException;
import view.general_utility.WarningNotice;
import view.gui_utility.EditableMemberPanelImpl;
import view.gui_utility.MyJFrameSingletonImpl;
import view.gui_utility.MyJPanelImpl;

public class ShowEditExcursion extends JDialog{
	MyJPanelImpl panel=new MyJPanelImpl(new BorderLayout());
	MyJPanelImpl panelCenterOuter = new MyJPanelImpl(new BorderLayout());
	MyJPanelImpl panelCenter=new MyJPanelImpl(new GridLayout(0, 2));
	MyJPanelImpl panelBot=new MyJPanelImpl(new FlowLayout(FlowLayout.RIGHT));
	EditableMemberPanelImpl<Excursion> parent;
	MyJPanelImpl panelEdit = new MyJPanelImpl(new GridLayout(0,1));
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
				
				panelCenter.add(panelCenter.createJLabel("Data", fontSize));
				panelCenter.add(panelCenter.createJLabel(info.get("Data").get(0), fontSize));
				panelEdit.add(getJButton("Quando"));
				
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
		return panelEdit.createButton("Edit",11, e->{
			JDialog dial=new JDialog();
			MyJPanelImpl pan=new MyJPanelImpl(new BorderLayout());
			if(type.equals("Nome") || type.equals("Dove")){
				JTextField txt=new JTextField();
				pan.add(txt,BorderLayout.CENTER);
				pan.add(pan.createButton("OK", t->{
					if(type.equals("Nome")){exc.setName(txt.getText());}
					else{exc.setPlace(txt.getText());}
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
						exc.setDateStart(LocalDate.of(Integer.parseInt(aa.getText()), 
								Integer.parseInt(mm.getText()),Integer.parseInt(gg.getText())));
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
