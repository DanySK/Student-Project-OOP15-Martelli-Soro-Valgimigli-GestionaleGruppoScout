package view.gestioneReparto;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import control.myUtil.MyOptional;
import model.Member;
import view.gui_utility.EditableInfoPanelImpl;
import view.gui_utility.EditableMemberPanelImpl;
import view.gui_utility.EditableMemberPanelImpl.Type;
import view.gui_utility.MyJPanelImpl;

public class SquadrigliaOverviewImpl  {
	private final String squadName;
	
	public SquadrigliaOverviewImpl(String param){
		this.squadName=param;
		
	}
	@Override
	
	public String toString(){
		return "Panoramica";
	}
	public class SquadrigliaOverviewImplPanel extends MyJPanelImpl{
		private static final long serialVersionUID = -6749522066747263034L;
		private final int FONTSIZE=19;
		private final int FONTSIZEBUTTON=10;
		private	final EditableInfoPanelImpl panelSxDx;
		private final MyJPanelImpl panelCenter;
		public SquadrigliaOverviewImplPanel() {
			/*
			 * Instanzio i vari oggetti e sopratutto instanzio tutti i pannelli che mi servono
			 */
			super(new BorderLayout());
			panelCenter=new MyJPanelImpl(new GridLayout(2, 1));
			panelSxDx=new EditableInfoPanelImpl(squadName, false, FONTSIZE, FONTSIZEBUTTON);
			/*
			 * aggiungo l'intestazione e tutti i pannelli nell'ordine in cui mi servono
			 */
			this.add(createJLabel( "<html><U>Panoramica di "+squadName+"</U></html>", 22),BorderLayout.NORTH);
			this.add(panelCenter, BorderLayout.CENTER);
			panelCenter.add(panelSxDx);
			panelCenter.add(new EditableMemberPanelImpl<Member>(Type.OVERVIEWSQUAD, MyOptional.of(squadName)));
		}		
	}	
}
