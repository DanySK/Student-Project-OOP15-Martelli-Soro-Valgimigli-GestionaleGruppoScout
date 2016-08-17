package view.gui_utility;

import javax.swing.JPanel;

import control.UnitImpl;

public interface MyJFrameSingleton {

	
	
	/**It set the ContentPane of MyFrameSingleton
	 * 
	 * @param panel The main panel you want to set in this frame
	 */
	void setPanel(JPanel panel);

	/**It returns the ContentPane already setted in MyFrameSingleton
	 * 
	 * @return JPanel	The main panel setted in this frame
	 */
	JPanel getContenentPane();

	/**it return the UnitImpl working on
	 * @return UnitImpl UnitImpl working on
	 */
	UnitImpl getUnit();

	/**
	 * set that application has unsaved changes
	 */
	void setNeedToSave();

	/**
	 * sets tha application doesn't need to save changes
	 */
	void resetNeedToSava();

}