package view.gui_utility;

import javax.swing.JPanel;

/**
 * A class that model a JFrame using the Singleton pattern. Only one frame is(usualy) used in
 * this app, each window is painted changing the contentPane of the unique JFrame.
 * @author giovanni
 */
public interface MyJFrameSingleton {
	
	/**It set the ContentPane of MyFrameSingleton
	 * 
	 * @param panel The main panel you want to set in this frame
	 */
	public void setPanel(JPanel panel);
	
	/**It returns the ContentPane already setted in MyFrameSingleton
	 * 
	 * @return JPanel	The main panel setted in this frame
	 */
	public JPanel getContenentPane();
	
}