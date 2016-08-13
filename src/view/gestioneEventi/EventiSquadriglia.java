package view.gestioneEventi;

import java.awt.BorderLayout;

import model.Squadron;
import model.SquadronImpl;
import view.gui_utility.MyJPanelImpl;

public class EventiSquadriglia {
	private final SquadronImpl squadImpl;
	public EventiSquadriglia(Squadron squad){
		squadImpl=(SquadronImpl) squad;
	}
	
	
	public class EventiSquadrigliaPanel extends MyJPanelImpl{
	
		private static final long serialVersionUID = 5205825583794848349L;
		private int fontSizeLabel=19;
		public EventiSquadrigliaPanel() {
			super(new BorderLayout());
			this.add(createJLabel("<html><U>Gestione eventi "+squadImpl.getNome()+"</U></html> ", fontSizeLabel+2), BorderLayout.NORTH);
		}
		
	}
	
	public String toString(){
		return "Uscite "+squadImpl.getNome();
	}
	
	
}
