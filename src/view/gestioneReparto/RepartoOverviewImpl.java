package view.gestioneReparto;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JDialog;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import control.Unit;
import control.projectFactoryImpl;
import control.myUtil.myOptional;
import model.CampoImpl;
import model.CapoImpl;
import view.gestioneReparto.utility.PanelCapiReparto;
import view.gui_utility.EditableMemberPanelImpl;
import view.gui_utility.MyJFrameSingletonImpl;
import view.gui_utility.MyJPanelImpl;
import view.gui_utility.EditableMemberPanelImpl.Type;

public class RepartoOverviewImpl  {
	private final Unit unit;
	

	
	
	public RepartoOverviewImpl() {
		this.unit=MyJFrameSingletonImpl.getInstance().getUnit();
		/*super(new BorderLayout());
		this.panelSxDx=new MyJPanelImpl(new BorderLayout());
		
		
		/*
		this.panelLeft=new MyJPanelImpl(new GridLayout(0,2));
		this.panelRight=new MyJPanelImpl(new GridLayout(0, 2));
		
		this.add( panelLeft, BorderLayout.CENTER);
		this.add( panelRight, BorderLayout.EAST);
		panelLeft.add(createJLabel("capoM", "Capo Maschio", fontSizeLabel));
		panelLeft.add(createJLabel("capoMValue", unit.getReparto().getCapoM().getName()+" "
				+unit.getReparto().getCapoM().getSurname(),fontSizeLabel));
		panelLeft.add(createJLabel("capoF", "Capo Femmina", fontSizeLabel));
		panelLeft.add(createJLabel("capoMValue", unit.getReparto().getCapoF().getName()+" "
				+unit.getReparto().getCapoF().getSurname(),fontSizeLabel));
		
		
		
		panelLeft.add(createButton("<html>Crea<br>Squadriglia</html>", e->{
			JDialog dial = new JDialog();
			MyJPanelImpl pan=new MyJPanelImpl(new BorderLayout());
			MyJPanelImpl info=new MyJPanelImpl(new GridLayout(2, 2));
			MyJPanelImpl button=new MyJPanelImpl();
			info.add(createJLabel("nomeSquad", "Nome: ", fontSizeLabel));
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
			info.add(createJLabel("sesso", "Sesso: ", fontSizeLabel));
			info.add(tmp);
			button.add(createButton("Annulla", k->{
				dial.dispose();
			}));
			button.add(createButton("Aggiungi", k->{
				unit.createSq(projectFactoryImpl.getSquadron(nome.getText(), sexM.isSelected()));
				MyJFrameSingletonImpl.getInstance().setNeedToSave();
				
				((GestioneRepartoMain)MyJFrameSingletonImpl.getInstance().getContenentPane())
					.addSquadToJTree(MyJFrameSingletonImpl.getInstance().getUnit().getContainers().findSquadron(nome.getText()));;
				dial.dispose();
			}));
			pan.add(createJLabel("northLabel", "<html><U>Nuova squadriglia</U></html>", fontSizeLabel));
			pan.add(info, BorderLayout.CENTER);
			pan.add(button,BorderLayout.SOUTH);
			dial.add(pan);
			dial.pack();
			dial.setLocationRelativeTo(MyJFrameSingletonImpl.getInstance());
			dial.setVisible(true);
			
		}));
		panelRight.add(createButton("edit", e->{
			
		}));
panelRight.add(createButton("edit", e->{
			
		}));
		
		
		*/
		
	}
	public class RepartoOverviewImplPane extends MyJPanelImpl{
		private static final long serialVersionUID = -4533965002766818616L;
		private MyJPanelImpl panelLeft;
		private MyJPanelImpl panelRight;			
		private MyJPanelImpl panelSxDx;	
		private MyJPanelImpl panelBot;
		private int fontSizeLabel=22;
		public RepartoOverviewImplPane(){
			super(new GridLayout(2,1));
			this.panelSxDx=new MyJPanelImpl(new BorderLayout());
			this.panelLeft=new MyJPanelImpl(new GridLayout(0, 2));
			this.panelRight=new MyJPanelImpl(new GridLayout(0,1));
			this.panelBot=new MyJPanelImpl(new FlowLayout(FlowLayout.LEFT));
			panelLeft.add(createJLabel( "Capo Maschio", fontSizeLabel));
			panelLeft.add(createJLabel(unit.getReparto().getCapoM().getName()+" "
					+unit.getReparto().getCapoM().getSurname(),fontSizeLabel));
			panelRight.add(createButton("edit", e->{
				dialogChef(true);
			}));
			
			panelLeft.add(createJLabel("Capo Femmina", fontSizeLabel));
			panelLeft.add(createJLabel(unit.getReparto().getCapoF().getName()+" "
					+unit.getReparto().getCapoF().getSurname(),fontSizeLabel));
			panelRight.add(createButton("edit", e->{
				dialogChef(false);
			}));
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
					unit.createSq(projectFactoryImpl.getSquadron(nome.getText(), sexM.isSelected()));
					MyJFrameSingletonImpl.getInstance().setNeedToSave();
					((GestioneRepartoMain)MyJFrameSingletonImpl.getInstance().getContenentPane())
						.addSquadToJTree(MyJFrameSingletonImpl.getInstance().getUnit().getContainers().findSquadron(nome.getText()));;
						dial.dispose();
				}));
				pan.add(createJLabel( "<html><U>Nuova squadriglia</U></html>", fontSizeLabel));
				pan.add(info, BorderLayout.CENTER);
				pan.add(button,BorderLayout.SOUTH);
				dial.add(pan);
				dial.pack();
				dial.setLocationRelativeTo(MyJFrameSingletonImpl.getInstance());
				dial.setVisible(true);
			
			}));
			panelSxDx.add(panelLeft,BorderLayout.CENTER);
			panelSxDx.add(panelRight,BorderLayout.EAST);
			panelSxDx.add(panelBot, BorderLayout.SOUTH);
			this.add(panelSxDx);
			this.add(new EditableMemberPanelImpl<>(Type.OverviewReparto, myOptional.empty()));
			
			
		}
		
		
		private void dialogChef(boolean sex){
			JDialog dial= new JDialog();
			MyJPanelImpl outerPanel=new MyJPanelImpl(new BorderLayout());
			MyJPanelImpl panelBot=new MyJPanelImpl();
			PanelCapiReparto panCapo= new PanelCapiReparto("Nuovo Capo Reparto");
			outerPanel.add(panCapo,BorderLayout.CENTER);
			panelBot.add(createButton("OK", e->{
				if(sex==true){
					unit.getReparto().setCapoM( new CapoImpl(panCapo.getNome(), panCapo.getSurname(), panCapo.getDate(),
						panCapo.getSex(),panCapo.getPhone()));
				}
				else{
					unit.getReparto().setCapoF( new CapoImpl(panCapo.getNome(), panCapo.getSurname(), panCapo.getDate(),
						panCapo.getSex(),panCapo.getPhone()));
				}
				dial.dispose();
			}));
			outerPanel.add(panelBot, BorderLayout.SOUTH);
			dial.add(outerPanel);
			dial.pack();
			dial.setLocationRelativeTo(MyJFrameSingletonImpl.getInstance());
			dial.setVisible(true);
		}
	}
	
	
	public String toString(){
		return unit.getName();
	}
	
	
	
}

