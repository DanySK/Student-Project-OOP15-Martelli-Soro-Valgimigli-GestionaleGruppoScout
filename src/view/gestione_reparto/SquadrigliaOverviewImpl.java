package view.gestione_reparto;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import control.myUtil.MyOptional;
import model.reparto.Member;
import view.gui_utility.EditableInfoPanelImpl;
import view.gui_utility.EditableMemberPanelImpl;
import view.gui_utility.EditableMemberPanelImpl.Type;
import view.gui_utility.MyJPanelImpl;

public class SquadrigliaOverviewImpl {
	private final String squadName;

	public SquadrigliaOverviewImpl(final String param) {
		this.squadName = param;
	}

	public class SquadrigliaOverviewImplPane extends MyJPanelImpl {

		private static final long serialVersionUID = -6749522066747263034L;
		private final static int FONTSIZE = 19;
		private final static int FONTSIZEBUTTON = 10;
		private final EditableInfoPanelImpl panelSxDx;
		private final MyJPanelImpl panelCenter;

		public SquadrigliaOverviewImplPane() {
			/*
			 * Instanzio i vari oggetti e sopratutto instanzio tutti i pannelli
			 * che mi servono
			 */
			super(new BorderLayout());
			panelCenter = new MyJPanelImpl(new GridLayout(2, 1));
			panelSxDx = new EditableInfoPanelImpl(squadName, false, FONTSIZE, FONTSIZEBUTTON);
			/*
			 * aggiungo l'intestazione e tutti i pannelli nell'ordine in cui mi
			 * servono
			 */
			this.add(createJLabel("<html><U>Panoramica di " + squadName + "</U></html>", 22), BorderLayout.NORTH);
			this.add(panelCenter, BorderLayout.CENTER);
			panelCenter.add(panelSxDx);
			panelCenter.add(new EditableMemberPanelImpl<Member>(Type.OVERVIEWSQUAD, MyOptional.of(squadName)));
		}
	}

	public String toString() {
		return "Panoramica";
	}
}
