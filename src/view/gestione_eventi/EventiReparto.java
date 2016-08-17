package view.gestione_eventi;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.stream.Collectors;

import javax.swing.SwingUtilities;

import control.SortExcursionImpl;
import control.myUtil.MyOptional;
import model.Campo;
import model.EventiDiZona;
import model.Excursion;
import model.Gemellaggi;
import model.Uscita;
import view.gestione_eventi.utility.AddExcursionJDialog;
import view.gestione_eventi.utility.AddExcursionJDialog.TypeExcursion;
import view.gui_utility.EditableMemberPanelImpl;
import view.gui_utility.EditableMemberPanelImpl.Type;
import view.gui_utility.MyJFrameSingletonImpl;
import view.gui_utility.MyJPanelImpl;
import view.gui_utility.SearchElementJDialog;
import view.gui_utility.SearchElementJDialog.SearchType;

public class EventiReparto {

	
	
	public class EventiRepartoPane extends MyJPanelImpl{
		private static final long serialVersionUID = 5205825583794848349L;
		private final static int FONTSIZELABEL=19;
		private final static int FONTSIZELABELBIG=21;
		private final static int FONTSIZEBUTTON=12;
		private final MyJPanelImpl panelCenter;
		private final MyJPanelImpl panelTopContainer;
		private final MyJPanelImpl panelTopInfo;
		private final MyJPanelImpl panelTopButton;
		private final EditableMemberPanelImpl<Excursion> panelBot;
		private final static String NOTHING="Niente in programma";
		public EventiRepartoPane() {
			super(new BorderLayout());
			this.add(createJLabel("<html><U>Gestione eventi reparto</U></html> ", FONTSIZELABELBIG), BorderLayout.NORTH);
			this.panelCenter=new MyJPanelImpl(new GridLayout(2,1));
			this.panelTopInfo=new MyJPanelImpl(new GridLayout(0, 2));
			this.updatePaneInfo();
			this.panelTopButton=new MyJPanelImpl(new FlowLayout(FlowLayout.LEFT));
			this.panelTopContainer=new MyJPanelImpl(new BorderLayout());
			this.panelTopContainer.add(panelTopInfo, BorderLayout.CENTER);
			this.panelCenter.add(panelTopContainer);
			this.panelBot=new EditableMemberPanelImpl<Excursion>(Type.EXCREP, MyOptional.empty());
			panelTopButton.add(createButton("<html>Aggiungi<br>Campo</html>", FONTSIZEBUTTON, e->{
				new AddExcursionJDialog(TypeExcursion.Campo, MyOptional.empty(),this);
			}));
			panelTopButton.add(createButton("<html>Aggiungi<br>Evento di zona",FONTSIZEBUTTON,e->{
				new AddExcursionJDialog(TypeExcursion.Evento_di_Zona, MyOptional.empty(),this);
			}));
			panelTopButton.add(createButton("<html>Aggiungi<br>Gemellaggio",FONTSIZEBUTTON,e->{
				new AddExcursionJDialog(TypeExcursion.Gemellaggio, MyOptional.empty(),this);
			}));
			panelTopButton.add(createButton("<html>Aggiungi<br>Uscita",FONTSIZEBUTTON,e->{
				new AddExcursionJDialog(TypeExcursion.Uscita, MyOptional.empty(),this);
			}));
			panelTopButton.add(createButton("<html>Rimuovi<br>Escursione</html>", FONTSIZEBUTTON,e->{
				new SearchElementJDialog<>(SearchType.removeExcursion,MyOptional.empty(), MyOptional.empty(), this);
			}));
			this.panelTopContainer.add(panelTopButton, BorderLayout.SOUTH);
			
			this.panelCenter.add(panelBot);
			this.add(panelCenter,BorderLayout.CENTER);
		}
		public final void updatePaneInfo(){
			SwingUtilities.invokeLater(new Runnable() {
				
				public void run() {
					panelTopInfo.removeAll();
					//Long i =1 ;
					panelTopInfo.add(createJLabel("Prossimo Campo: ", FONTSIZELABEL));
					panelTopInfo.add(createJLabel((new SortExcursionImpl().sortByDateOfStart(MyJFrameSingletonImpl.getInstance().getUnit().getContainers().getExcursion().stream()
							.filter(e->e instanceof Campo).collect(Collectors.toList())))
							.stream().map(t->new String(t.getName()+"("+t.getDateStart()+")")).findFirst().orElse(NOTHING), FONTSIZELABEL));
					
					panelTopInfo.add(createJLabel("Prossimo Evento: ", FONTSIZELABEL));
					panelTopInfo.add(createJLabel((new SortExcursionImpl().sortByDateOfStart(MyJFrameSingletonImpl.getInstance().getUnit().getContainers().getExcursion().stream()
							.filter(e->e instanceof EventiDiZona).collect(Collectors.toList())))
							.stream().map(t->new String(t.getName()+"("+t.getDateStart()+")")).findFirst().orElse(NOTHING), FONTSIZELABEL));
					
					panelTopInfo.add(createJLabel("Prossimo Gemellaggio: ", FONTSIZELABEL));
					panelTopInfo.add(createJLabel((new SortExcursionImpl().sortByDateOfStart(MyJFrameSingletonImpl.getInstance().getUnit().getContainers().getExcursion().stream()
							.filter(e->e instanceof Gemellaggi).collect(Collectors.toList())))
							.stream().map(t->new String(t.getName()+"("+t.getDateStart()+")")).findFirst().orElse(NOTHING), FONTSIZELABEL));
					
					panelTopInfo.add(createJLabel("Prossima Uscita: ", FONTSIZELABEL));
					panelTopInfo.add(createJLabel((new SortExcursionImpl().sortByDateOfStart(MyJFrameSingletonImpl.getInstance().getUnit().getContainers().getExcursion().stream()
							.filter(e->e instanceof Uscita).collect(Collectors.toList())))
							.stream().map(t->new String(t.getName()+"("+t.getDateStart()+")")).findFirst().orElse(NOTHING), FONTSIZELABEL));
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
