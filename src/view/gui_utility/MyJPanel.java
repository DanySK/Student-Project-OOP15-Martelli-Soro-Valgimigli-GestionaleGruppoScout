package view.gui_utility;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
/**Class used to easy create JPanel and add in it JButton
 * 
 * @author giovanni
 */
public interface MyJPanel {
	
	/**Create a JButton
	 * 
	 * @param title JButton's title
	 * @param e JButton's ActionListener
	 * @return The JButton created
	 */
	public JButton createButton(String title, ActionListener e);
	
	/**Create a JButton
	 * 
	 * @param title JButton's title
	 * @param e JButton's ActionListener
	 * @param c	JButton's background color
	 * @return The JButton created
	 */
	public JButton createButton(String title, Color c, ActionListener e);
	
	/**Create a JButton
	 * 
	 * @param title JButton's title
	 * @param e JButton's ActionListener
	 * @param c	JButton's background color
	 * @param f	JButton Title's font
	 * @return The JButton created
	 */
	
	public JButton createButton(String title, Color c, Font f, ActionListener e);
	
	
}