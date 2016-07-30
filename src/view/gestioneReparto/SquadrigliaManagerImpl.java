package view.gestioneReparto;

import java.awt.BorderLayout;

import view.gui_utility.MyJPanelImpl;

public class SquadrigliaManagerImpl extends MyJPanelImpl {

	
	private static final long serialVersionUID = -906799651249952573L;

	private final int fontSizeLabel=19;
	private final int fontSizeButton=10;
	private final String name;
	public SquadrigliaManagerImpl(String name) {
		super(new BorderLayout());
		this.name=name;
		
		this.add(new EditableInfoPanel(name, true, fontSizeLabel, fontSizeButton));
		
	}
	
	public String toString(){
		return this.name+"__Manager";
	}
}
