package view.gestioneEventi;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.SwingUtilities;

import control.myUtil.myOptional;
import model.Campo;
import model.CampoImpl;
import model.EventiDiZona;
import model.Excursion;
import model.Gemellaggi;
import model.Uscita;
import view.gestioneEventi.utility.AddExcursionJDialog;
import view.gestioneEventi.utility.AddExcursionJDialog.TypeExcursion;
import view.gui_utility.EditableMemberPanelImpl;
import view.gui_utility.EditableMemberPanelImpl.Type;
import view.gui_utility.MyJFrameSingletonImpl;
import view.gui_utility.MyJPanelImpl;

public class EventiReparto {

	
	
	public class EventiRepartoPane extends MyJPanelImpl{
		private static final long serialVersionUID = 5205825583794848349L;
		private int fontSizeLabel=19;
		private final MyJPanelImpl panelCenter;
		private final MyJPanelImpl panelTopContainer;
		private final MyJPanelImpl panelTopInfo;
		private final MyJPanelImpl panelTopButton;
		private final EditableMemberPanelImpl<Excursion> panelBot;
		
		public EventiRepartoPane() {
			super(new BorderLayout());
			this.panelCenter=new MyJPanelImpl(new GridLayout(2,1));
			this.add(createJLabel("<html><U>Gestione eventi reparto</U></html> ", fontSizeLabel+2), BorderLayout.NORTH);
			this.panelTopInfo=new MyJPanelImpl(new GridLayout(0, 2));
			updatePaneInfo();
			this.panelTopButton=new MyJPanelImpl(new FlowLayout(FlowLayout.LEFT));
			this.panelTopContainer=new MyJPanelImpl(new BorderLayout());
			this.panelTopContainer.add(panelTopInfo, BorderLayout.CENTER);
			this.panelCenter.add(panelTopContainer);
			this.panelBot=new EditableMemberPanelImpl<Excursion>(Type.RepartoEventi, myOptional.empty());
			panelTopButton.add(createButton("<html>Aggiungi<br>campo</html>", 16, e->{
				new AddExcursionJDialog(TypeExcursion.Campo, myOptional.empty(),this);
			}));
			this.panelTopContainer.add(panelTopButton, BorderLayout.SOUTH);
			
			this.panelCenter.add(panelBot);
			this.add(panelCenter,BorderLayout.CENTER);
		}
		public void updatePaneInfo(){
			SwingUtilities.invokeLater(new Runnable() {
				
				public void run() {
					Long i = MyJFrameSingletonImpl.getInstance().getUnit().getContainers().getExcursion().stream()
							.filter(e->e instanceof Campo).count();
					panelTopInfo.add(createJLabel("Campi in programma: ", fontSizeLabel));
					panelTopInfo.add(createJLabel(i.toString(), fontSizeLabel));
					i = MyJFrameSingletonImpl.getInstance().getUnit().getContainers().getExcursion().stream()
							.filter(e->e instanceof EventiDiZona).count();
					panelTopInfo.add(createJLabel("Eventi in programma: ", fontSizeLabel));
					panelTopInfo.add(createJLabel(i.toString(), fontSizeLabel));
					i = MyJFrameSingletonImpl.getInstance().getUnit().getContainers().getExcursion().stream()
							.filter(e->e instanceof Gemellaggi).count();
					panelTopInfo.add(createJLabel("Gemellaggi in programma: ", fontSizeLabel));
					panelTopInfo.add(createJLabel(i.toString(), fontSizeLabel));
					i = MyJFrameSingletonImpl.getInstance().getUnit().getContainers().getExcursion().stream()
							.filter(e->e instanceof Uscita).count();
					panelTopInfo.add(createJLabel("Uscite in programma: ", fontSizeLabel));
					panelTopInfo.add(createJLabel(i.toString(), fontSizeLabel));
					panelTopInfo.repaint();
					panelTopInfo.validate();
				
				}
			});
			
		}
		
		public void updateEventi(){
			panelBot.updateMember();
		}
	}
	

	
	public String toString(){
		return "Uscite Reparto";
	}
	
	
}
