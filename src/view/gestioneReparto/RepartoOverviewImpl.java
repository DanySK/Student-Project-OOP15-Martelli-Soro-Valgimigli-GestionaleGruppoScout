package view.gestioneReparto;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Arrays;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import control.Unit;
import control.ProjectFactoryImpl;
import control.myUtil.MyOptional;
import model.CapoImpl;
import view.general_utility.WarningNotice;
import view.gestioneReparto.utility.PanelCapiReparto;
import view.gui_utility.EditableMemberPanelImpl;
import view.gui_utility.EditableMemberPanelImpl.Type;
import view.gui_utility.MyJFrameSingletonImpl;
import view.gui_utility.MyJPanelImpl;

public class RepartoOverviewImpl  {
	private final Unit unit;
	

	
	
	public RepartoOverviewImpl() {
		this.unit=MyJFrameSingletonImpl.getInstance().getUnit();
		
		
	}
	public class RepartoOverviewImplPane extends MyJPanelImpl{
		private static final long serialVersionUID = -4533965002766818616L;
		private MyJPanelImpl panelLeft;
		private MyJPanelImpl panelRight;			
		private MyJPanelImpl panelSxDx;	
		private MyJPanelImpl panelBot;
		private final MyJPanelImpl panelCenter;
		private int fontSizeLabel=19;
		public RepartoOverviewImplPane(){
			super(new BorderLayout());
			this.panelCenter=new MyJPanelImpl(new GridLayout(2,1));
			this.panelSxDx=new MyJPanelImpl(new BorderLayout());
			this.panelLeft=new MyJPanelImpl(new GridLayout(0, 2));
			this.panelRight=new MyJPanelImpl(new GridLayout(0,1));
			this.panelBot=new MyJPanelImpl();//new FlowLayout(FlowLayout.LEFT));
			updateAll();
			
			
		}
		
		
		private void dialogChef(boolean sex){
			JDialog dial= new JDialog();
			MyJPanelImpl outerPanel=new MyJPanelImpl(new BorderLayout());
			MyJPanelImpl panelBot=new MyJPanelImpl();
			PanelCapiReparto panCapo= new PanelCapiReparto("Nuovo Capo Reparto");
			outerPanel.add(panCapo,BorderLayout.CENTER);
			panelBot.add(createButton("OK", e->{
				try{
					if(sex==true){
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
		}
		
		private void updateAll(){
			SwingUtilities.invokeLater(new Runnable() {
				
				@Override
				public void run() {

					panelCenter.removeAll();
					panelSxDx.removeAll();
					panelLeft.removeAll();
					panelRight.removeAll();
					panelBot.removeAll();
					panelLeft.add(createJLabel( "Capo Maschio", fontSizeLabel));
					panelLeft.add(createJLabel(unit.getReparto().getCapoM().getName()+" "
							+unit.getReparto().getCapoM().getSurname(),fontSizeLabel));
					panelRight.add(createButton("cambia", e->{
						dialogChef(true);
					}));
					panelRight.add(createButton("edit",e->{
						JDialog dial = new JDialog();
						MyJPanelImpl panel=new MyJPanelImpl(new GridLayout(2,1));
						MyJPanelImpl outer=new MyJPanelImpl(new BorderLayout());
						MyJPanelImpl bot=new MyJPanelImpl(new FlowLayout(FlowLayout.RIGHT));
						panel.add(createJLabel("<html><U>Nuovo numero di telefono</U></html>", 18));
						JTextField field= new JTextField();
						panel.add(field);
						bot.add(createButton("Salva", p->{
							MyJFrameSingletonImpl.getInstance().getUnit().getReparto().getCapoM().setPhoneNumber(field.getText());
							dial.dispose();
							MyJFrameSingletonImpl.getInstance().setNeedToSave();
							updateAll();
						}));
						bot.add(createButton("Annulla", k->{
							dial.dispose();
						}));
						outer.add(panel, BorderLayout.CENTER);
						outer.add(bot,BorderLayout.SOUTH);
						dial.add(outer);
						dial.pack();
						dial.setLocationRelativeTo(MyJFrameSingletonImpl.getInstance());
						dial.setVisible(true);
						
							
					}));
					panelLeft.add(createJLabel("", fontSizeLabel));
					panelLeft.add(createJLabel("Tel: "+unit.getReparto().getCapoM().getPhoneNumber(), fontSizeLabel));
					
					panelLeft.add(createJLabel("Capo Femmina", fontSizeLabel));
					panelLeft.add(createJLabel(unit.getReparto().getCapoF().getName()+" "
							+unit.getReparto().getCapoF().getSurname(),fontSizeLabel));
					panelRight.add(createButton("cambia", e->{
						dialogChef(false);
					}));
					panelRight.add(createButton("edit",e->{
						JDialog dial = new JDialog();
						MyJPanelImpl panel=new MyJPanelImpl(new GridLayout(2,1));
						MyJPanelImpl outer=new MyJPanelImpl(new BorderLayout());
						MyJPanelImpl bot=new MyJPanelImpl(new FlowLayout(FlowLayout.RIGHT));
						panel.add(createJLabel("<html><U>Nuovo numero di telefono</U></html>", 18));
						JTextField field= new JTextField();
						panel.add(field);
						bot.add(createButton("Salva", p->{
							MyJFrameSingletonImpl.getInstance().getUnit().getReparto().getCapoF().setPhoneNumber(field.getText());
							dial.dispose();
							MyJFrameSingletonImpl.getInstance().setNeedToSave();
							updateAll();
						}));
						bot.add(createButton("Annulla", k->{
							dial.dispose();
						}));
						outer.add(panel, BorderLayout.CENTER);
						outer.add(bot,BorderLayout.SOUTH);
						dial.add(outer);
						dial.pack();
						dial.setLocationRelativeTo(MyJFrameSingletonImpl.getInstance());
						dial.setVisible(true);
						
							
					}));
					panelLeft.add(createJLabel("", fontSizeLabel));
					panelLeft.add(createJLabel("Tel: "+unit.getReparto().getCapoF().getPhoneNumber(), fontSizeLabel));
					Arrays.asList(panelRight.getComponents()).stream().forEach(e->{e.setFont(new Font("Aria",Font.ITALIC,12));});
					panelBot.add(createButton("<html>Crea<br>Squadriglia</html>", e->{
						JDialog dial = new JDialog();
						MyJPanelImpl pan=new MyJPanelImpl(new BorderLayout());
						MyJPanelImpl info=new MyJPanelImpl(new GridLayout(2, 2));
						MyJPanelImpl button=new MyJPanelImpl();
						info.add(createJLabel( "Nome: ", fontSizeLabel));
						JTextField nome=new JTextField();
						info.add(nome);
						JRadioButton sexM=new JRadioButton("Maschi");
						JRadioButton sexF=new JRadioButton("Femmine");
						ButtonGroup sex= new ButtonGroup();
						sex.add(sexM);
						sex.add(sexF);
						MyJPanelImpl tmp=new MyJPanelImpl();
						tmp.add(sexM);
						tmp.add(sexF);
						info.add(createJLabel( "Sesso: ", fontSizeLabel));
						info.add(tmp);
						button.add(createButton("Annulla", k->{
							dial.dispose();
						}));
						button.add(createButton("Aggiungi", k->{
							try{
								unit.createSq(ProjectFactoryImpl.getSquadron(nome.getText(), sexM.isSelected()));
								MyJFrameSingletonImpl.getInstance().setNeedToSave();
								((GestioneRepartoMain)MyJFrameSingletonImpl.getInstance().getContenentPane())
									.addSquadToJTree(MyJFrameSingletonImpl.getInstance().getUnit().getContainers().findSquadron(nome.getText()));;
									dial.dispose();
							}catch(Exception f){
								new WarningNotice(f.getMessage());
							}
						}));
						pan.add(createJLabel( "<html><U>Nuova squadriglia</U></html>", fontSizeLabel));
						pan.add(info, BorderLayout.CENTER);
						pan.add(button,BorderLayout.SOUTH);
						dial.add(pan);
						dial.pack();
						dial.setLocationRelativeTo(MyJFrameSingletonImpl.getInstance());
						dial.setVisible(true);
					
					}));
					panelBot.add(createButton("<html>Rimuovi<br>Squadriglia</html>", e->{
						JDialog dial = new JDialog();
						MyJPanelImpl panel=new MyJPanelImpl(new BorderLayout());
						MyJPanelImpl panelBot=new MyJPanelImpl(new FlowLayout(FlowLayout.RIGHT));
						JComboBox<String> squad=new JComboBox<>();
						MyJFrameSingletonImpl.getInstance().getUnit().getContainers().getSquadrons().stream()
							.forEach(t->squad.addItem(t.getNome()));
						panel.add(squad,BorderLayout.CENTER);
						panelBot.add(createButton("Elimina",t->{
							try{
								unit.removeSq(unit.getContainers().findSquadron((String)squad.getSelectedItem()));
								((GestioneRepartoMain)MyJFrameSingletonImpl.getInstance().getContenentPane()).removeSquadToJTree((String)squad.getSelectedItem());
								MyJFrameSingletonImpl.getInstance().setNeedToSave();
								dial.dispose();
							}catch(Exception f){
								new WarningNotice(f.getMessage());
							}
							
						}));
						panelBot.add(createButton("Annulla", t->{
							dial.dispose();
						}));
						panel.add(createJLabel("<html><U>Rimuovi squadriglia</U></html>", fontSizeLabel),BorderLayout.NORTH);
						panel.add(panelBot,BorderLayout.SOUTH);
						dial.add(panel);
						dial.pack();
						dial.setLocationRelativeTo(MyJFrameSingletonImpl.getInstance());
						dial.setVisible(true);
						
					}));
					Arrays.asList(panelBot.getComponents()).stream().forEach(t->t.setFont(new Font("Aria",Font.ITALIC,12)));
					panelSxDx.add(panelLeft,BorderLayout.CENTER);
					panelSxDx.add(panelRight,BorderLayout.EAST);
					panelSxDx.add(panelBot, BorderLayout.SOUTH);
					panelCenter.add(panelSxDx);
					panelCenter.add(new EditableMemberPanelImpl<>(Type.OVERVIEWREP, MyOptional.empty()));
					add(panelCenter, BorderLayout.CENTER);
					add(createJLabel("<html><U>Gestione Reparto</U></html>", fontSizeLabel+2),BorderLayout.NORTH);
					validate();
					repaint();
				}
			});
		}
	}
	
	
	public String toString(){
		return unit.getName();
	}
	
	
	
}

