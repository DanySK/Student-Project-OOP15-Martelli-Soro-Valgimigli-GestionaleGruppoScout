package view.gestioneReparto;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import control.myUtil.MyOptional;
import model.Member;
import view.gui_utility.EditableInfoPanelImpl;
import view.gui_utility.EditableMemberPanelImpl;
import view.gui_utility.EditableMemberPanelImpl.Type;
import view.gui_utility.MyJPanelImpl;

public class SquadrigliaManagerImpl extends MyJPanelImpl {
	private static final long serialVersionUID = -906799651249952573L;
	private final String squadName;
	
	public SquadrigliaManagerImpl(String name){
		this.squadName=name;
	}
		
	public class SquadrigliaManagerImplPanel extends MyJPanelImpl{
		private static final long serialVersionUID = 4972375090872028432L;
		private final int fontSizeLabel=19;
		private final int fontSizeButton=10;
		private final EditableMemberPanelImpl<Member> panelBottom;
		private final EditableInfoPanelImpl panelCenter;
		public SquadrigliaManagerImplPanel() {
			super(new BorderLayout());
			panelCenter=new EditableInfoPanelImpl(squadName, true, fontSizeLabel, fontSizeButton);
			panelBottom =new EditableMemberPanelImpl<Member>(Type.GestioneSquadriglia, MyOptional.of(squadName));
			panelBottom.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(0,0,0)));
			JPanel T=new JPanel(new GridLayout(2, 0));
			this.add(createJLabel(  "<html><U>Gestione di "+squadName+"</U></html>" ,fontSizeLabel), BorderLayout.NORTH);
			T.add(panelCenter);
			T.add(panelBottom);
			this.add(T, BorderLayout.CENTER);
			
		}
		
	}

	public String toString(){
		return "Gestione";
	}
}
