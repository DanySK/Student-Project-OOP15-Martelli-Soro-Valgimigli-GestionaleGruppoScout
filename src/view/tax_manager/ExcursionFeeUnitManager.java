package view.tax_manager;

import java.awt.BorderLayout;
import java.lang.reflect.Member;

import control.myUtil.MyOptional;
import view.gui_utility.EditableElementScrollPanelImpl;
import view.gui_utility.EditableElementScrollPanelImpl.Type;
import view.gui_utility.MyJPanelImpl;

public class ExcursionFeeUnitManager{
	

public class GestioneTasseExcursionRepartoImplPane extends MyJPanelImpl {

	private static final long serialVersionUID = 5230534611226147187L;
	
	private final static int FONTSIZE = 19;
	private final static int FONTSIZELABEL=15;
	public GestioneTasseExcursionRepartoImplPane(){
		super(new BorderLayout());
		final MyJPanelImpl panel=new MyJPanelImpl(new BorderLayout());
		final EditableElementScrollPanelImpl<Member>mem= new EditableElementScrollPanelImpl<>(Type.EXCREPTASSE, MyOptional.empty());
		this.add(panel.createJLabel("<html><U>Gestione Tasse Escursioni</U></html>", FONTSIZE),BorderLayout.NORTH);
		panel.add(panel.createJLabel("Membri che non hanno pagato le escursioni", FONTSIZELABEL),BorderLayout.NORTH);
		panel.add(mem,BorderLayout.CENTER);
		this.add(panel,BorderLayout.CENTER);
	}
	}
public String toString(){
	return "Tasse Escursioni";
}
}
