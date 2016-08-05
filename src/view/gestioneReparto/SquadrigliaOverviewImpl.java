package view.gestioneReparto;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;

import view.gui_utility.MyJPanelImpl;

public class SquadrigliaOverviewImpl  {
	private final String squadName;
	
	public SquadrigliaOverviewImpl(String param){
		this.squadName=param;
		
	}
	@Override
	
	public String toString(){
		return this.squadName+"__Overview";
	}
	
	/**
	 * 
	 * @author Giovanni Martelli
	 *
	 */
	
	public class SquadrigliaOverviewImplPanel extends MyJPanelImpl{
		private static final long serialVersionUID = -6749522066747263034L;
		private final int fontSizeLabel=19;
		private final int fontSizeButton=10;
		private	EditableInfoPanelImpl panelSxDx;
		private MyJPanelImpl panelCenter;
		private EditableMemberPanelImpl panelMember;
		public SquadrigliaOverviewImplPanel() {
			/*
			 * Instanzio i vari oggetti e sopratutto instanzio tutti i pannelli che mi servono
			 */
			super(new BorderLayout());
			panelCenter=new MyJPanelImpl(new GridLayout(2, 1));
			panelSxDx=new EditableInfoPanelImpl(squadName, false, fontSizeLabel, fontSizeButton);
			panelMember =new EditableMemberPanelImpl(squadName, false);
			panelMember.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(0,0,0)));
			//JScrollPane panelScroll=new JScrollPane(panelMember);
			/*
			 * aggiungo l'intestazione e tutti i pannelli nell'ordine in cui mi servono
			 */
			this.add(createJLabel("north", "<html><U>Panoramica di "+squadName+"</U></html>", 22),BorderLayout.NORTH);
			this.add(panelCenter, BorderLayout.CENTER);
			panelCenter.add(panelSxDx);
			panelCenter.add(panelMember);
		}		
	}	
}
