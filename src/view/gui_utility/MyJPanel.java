package view.gui_utility;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public interface MyJPanel {

	/**Create a JButton
	 * 
	 * @param title JButton's title
	 * @param e JButton's ActionListener
	 * @return The JButton created
	 */
	JButton createButton(String title, ActionListener e);

	/**Create a JButton
	 * 
	 * @param title JButton's title
	 * @param e JButton's ActionListener
	 * @param c	JButton's background color
	 * @return The JButton created
	 */
	JButton createButton(String title, Color c, ActionListener e);

	/**Create a JButton
	 * 
	 * @param title JButton's title
	 * @param e JButton's ActionListener
	 * @param c	JButton's background color
	 * @param f	JButton Title's font
	 * @return The JButton created
	 */

	JButton createButton(String title, Color c, Font f, ActionListener e);

	/**
	 * Create a JTextArea
	 * @param name component name
	 * @param text text to display
	 * @param editable editable or not
	 * @param fontSize font dimension
	 * @return
	 */
	JTextArea createJTextArea(String name, String text, boolean editable, int fontSize);

	/**
	 * Create a JLabel
	 * @param name component name
	 * @param text text to display
	 * @param fontSize font dimension
	 * @return
	 */
	JLabel createJLabel(String name, String text, int fontSize);

	Component getComponent(String name);

}