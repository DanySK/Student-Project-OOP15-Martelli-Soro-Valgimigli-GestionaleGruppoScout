package view.gestioneEventi;

import java.awt.BorderLayout;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.stream.Collectors;

import javax.swing.JDialog;
import javax.swing.SwingUtilities;

import control.SortExcursionImpl;
import control.myUtil.myOptional;
import model.Campo;
import model.EventiDiZona;
import model.Excursion;
import model.Gemellaggi;
import model.Squadron;
import model.SquadronImpl;
import model.Uscita;
import model.UscitaSquadriglia;
import view.gestioneEventi.utility.AddExcursionJDialog;
import view.gestioneEventi.utility.AddExcursionJDialog.TypeExcursion;
import view.gui_utility.EditableMemberPanelImpl;
import view.gui_utility.MyJFrameSingletonImpl;
import view.gui_utility.MyJPanelImpl;
import view.gui_utility.EditableMemberPanelImpl.Type;

public class EventiSquadriglia {
	private final SquadronImpl squadImpl;
	public EventiSquadriglia(Squadron squad){
		squadImpl=(SquadronImpl) squad;
	}
	
	
	public class EventiSquadrigliaPanel extends MyJPanelImpl{
	
		private static final long serialVersionUID = 5205825583794848349L;
		private int fontSizeLabel=19;
		private final MyJPanelImpl panelCenter;
		private final MyJPanelImpl panelTopContainer;
		private final MyJPanelImpl panelTopInfo;
		private final MyJPanelImpl panelTopButton;
		private final EditableMemberPanelImpl<Excursion> panelBot;
		public EventiSquadrigliaPanel() {
			super(new BorderLayout());
			this.add(createJLabel("<html><U>Gestione eventi "+squadImpl.getNome()+"</U></html> ", fontSizeLabel+2), BorderLayout.NORTH);
			this.panelCenter=new MyJPanelImpl(new GridLayout(2,1));
			this.panelTopInfo=new MyJPanelImpl(new GridLayout(0, 2));
			this.updatePaneInfo();
			this.panelTopButton=new MyJPanelImpl(new FlowLayout(FlowLayout.LEFT));
			this.panelTopContainer=new MyJPanelImpl(new BorderLayout());
			this.panelTopContainer.add(panelTopInfo, BorderLayout.CENTER);
			this.panelCenter.add(panelTopContainer);
			this.panelBot=new EditableMemberPanelImpl<Excursion>(Type.SquadrigliaEventi, myOptional.empty());
			panelTopButton.add(createButton("<html>Aggiungi<br>Uscita</html>", 12, e->{
				new AddExcursionJDialog(TypeExcursion.Uscita_Squadriglia, myOptional.of(squadImpl.getNome()), this);
			}));
			
			panelTopButton.add(createButton("<html>Rimuovi<br>Uscita</html>", 12,e->{
				JDialog dial =new JDialog();
				MyJPanelImpl pan=new MyJPanelImpl(new BorderLayout());
				MyJPanelImpl bot=new MyJPanelImpl(new FlowLayout(FlowLayout.LEFT));
				
			}));
			this.panelTopContainer.add(panelTopButton, BorderLayout.SOUTH);
			
			this.panelCenter.add(panelBot);
			this.add(panelCenter,BorderLayout.CENTER);
		}
		public void updatePaneInfo(){
			SwingUtilities.invokeLater(new Runnable() {
				
				public void run() {
					//Long i =1 ;
					panelTopInfo.add(createJLabel("Prossimo Uscita: ", fontSizeLabel));
					
					panelTopInfo.add(createJLabel((new SortExcursionImpl()).sortByDateOfStart(MyJFrameSingletonImpl.getInstance()
							.getUnit().getContainers().getExcursion().stream().filter(e->e instanceof UscitaSquadriglia)
							.collect(Collectors.toList())).stream()
							.map(t->new String(t.getName()+"("+t.getDateStart()+")")).findFirst().orElse("Niente in programma"),
							fontSizeLabel));
					
					/*panelTopInfo.add(createJLabel("Prossimo Evento: ", fontSizeLabel));
					panelTopInfo.add(createJLabel((new sortExcursionImpl().sortByDateOfStart(MyJFrameSingletonImpl.getInstance().getUnit().getContainers().getExcursion().stream()
							.filter(e->e instanceof EventiDiZona).collect(Collectors.toList())))
							.stream().map(t->new String(t.getName()+"("+t.getDateStart()+")")).findFirst().orElse("Niente in programma"), fontSizeLabel));
					
					panelTopInfo.add(createJLabel("Prossimo Gemellaggio: ", fontSizeLabel));
					panelTopInfo.add(createJLabel((new sortExcursionImpl().sortByDateOfStart(MyJFrameSingletonImpl.getInstance().getUnit().getContainers().getExcursion().stream()
							.filter(e->e instanceof Gemellaggi).collect(Collectors.toList())))
							.stream().map(t->new String(t.getName()+"("+t.getDateStart()+")")).findFirst().orElse("Niente in programma"), fontSizeLabel));
					
					panelTopInfo.add(createJLabel("Prossima Uscita: ", fontSizeLabel));
					panelTopInfo.add(createJLabel((new sortExcursionImpl().sortByDateOfStart(MyJFrameSingletonImpl.getInstance().getUnit().getContainers().getExcursion().stream()
							.filter(e->e instanceof Uscita).collect(Collectors.toList())))
							.stream().map(t->new String(t.getName()+"("+t.getDateStart()+")")).findFirst().orElse("Niente in programma"), fontSizeLabel));
					*/
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
		return "Uscite "+squadImpl.getNome();
	}
	
	
}
