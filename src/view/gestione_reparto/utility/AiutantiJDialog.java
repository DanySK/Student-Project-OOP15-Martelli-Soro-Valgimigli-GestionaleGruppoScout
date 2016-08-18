package view.gestione_reparto.utility;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import control.ProjectFactoryImpl;
import model.exception.ObjectNotContainedException;
import view.general_utility.WarningNotice;
import view.gui_utility.MyJFrameSingletonImpl;
import view.gui_utility.MyJPanelImpl;

public class AiutantiJDialog extends JDialog {
	
	private static final long serialVersionUID = -3089581238215852232L;
	private final static int fontSize=18;
	private final MyJPanelImpl inPanel=new MyJPanelImpl(new BorderLayout());
	private final MyJPanelImpl panelLeft = new MyJPanelImpl(new GridLayout(0, 2));
	private final MyJPanelImpl panelRight=new MyJPanelImpl(new GridLayout(0,1));
	private final JScrollPane scroll=new JScrollPane(inPanel);
	public AiutantiJDialog(){
		super();
		final MyJPanelImpl panel=new MyJPanelImpl(new BorderLayout());
		final MyJPanelImpl bot=new MyJPanelImpl();
		bot.add(bot.createButton("Ok", fontSize, e->{
			this.dispose();
		}));
		bot.add(bot.createButton("Aggiungi staff", fontSize, y->{
			final JDialog dial= new JDialog();
			final MyJPanelImpl outerPanel=new MyJPanelImpl(new BorderLayout());
			final MyJPanelImpl panelBot=new MyJPanelImpl();
			final PanelCapiReparto panCapo= new PanelCapiReparto("Nuovo Capo Reparto");
			outerPanel.add(panCapo,BorderLayout.CENTER);
			panelBot.add(panelBot.createButton("OK", e->{
				try{
					if(sex){
						unit.getReparto().setCapoM( ProjectFactoryImpl.getLeaderM(panCapo.getNome(), panCapo.getSurname(), 
								panCapo.getDate(),panCapo.getPhone()));
					}
					else{
						unit.getReparto().setCapoF( ProjectFactoryImpl.getLeaderF(panCapo.getNome(), panCapo.getSurname(), 
								panCapo.getDate(),panCapo.getPhone()));
					}
					dial.dispose();
					updateAll();
				}catch(Exception f){
					new WarningNotice(f.getMessage());
				}
			}));
			panelBot.add(createButton("Annulla", e->{
				dial.dispose();
			}));
			outerPanel.add(panelBot, BorderLayout.SOUTH);
			dial.add(outerPanel);
			dial.pack();
			dial.setLocationRelativeTo(MyJFrameSingletonImpl.getInstance());
			dial.setVisible(true);
		}));
		this.populate();
	}
	
	private void populate(){
		SwingUtilities.invokeLater(new Runnable() {
			
			public void run() {
				panelLeft.removeAll();
				panelRight.removeAll();
				/*popolo Left e Right*/
				MyJFrameSingletonImpl.getInstance().getUnit().getReparto().getStaff().stream().forEach(e->{
					
					panelLeft.add(panelLeft.createJLabel("Aiutante:", fontSize));
					panelLeft.add(panelLeft.createJLabel(e.getName()+ " "+e.getSurname(), fontSize));
					panelLeft.add(panelLeft.createJLabel("", fontSize));
					panelLeft.add(panelLeft.createJLabel("Tel. "+e.getPhoneNumber(), fontSize));
					
					panelRight.add(panelRight.createButton("Rimuovi", fontSize,i->{
						try {
							MyJFrameSingletonImpl.getInstance().getUnit().getReparto().removeAiutante(e);
						} catch (ObjectNotContainedException e1) {
							new WarningNotice(e1.getMessage());
							populate();
						}
					}));
					panelRight.add(panelRight.createButton("edit",fontSize, i->{
						final JDialog dial = new JDialog();
						final MyJPanelImpl panel=new MyJPanelImpl(new GridLayout(2,1));
						final MyJPanelImpl outer=new MyJPanelImpl(new BorderLayout());
						final MyJPanelImpl bot=new MyJPanelImpl(new FlowLayout(FlowLayout.RIGHT));
						panel.add(bot.createJLabel("<html><U>Nuovo numero di telefono</U></html>", fontSize));
						final JTextField field= new JTextField();
						panel.add(field);
						bot.add(bot.createButton("Salva", p->{
							try{
								MyJFrameSingletonImpl.getInstance().getUnit().getReparto().getStaff().stream()
								.filter(t->t.equals(e)).findFirst().get().setPhoneNumber(field.getText());
								dial.dispose();
								MyJFrameSingletonImpl.getInstance().setNeedToSave();
								populate();
							}catch(Exception kk){
								new WarningNotice(kk.getMessage());
							}
						}));
						bot.add(bot.createButton("Annulla", k->{
							dial.dispose();
						}));
						outer.add(panel, BorderLayout.CENTER);
						outer.add(bot,BorderLayout.SOUTH);
						dial.add(outer);
						dial.pack();
						dial.setLocationRelativeTo(MyJFrameSingletonImpl.getInstance());
						dial.setVisible(true);
						
							
					}));
					
					
					
				});
				
			}
		});
	}
}
