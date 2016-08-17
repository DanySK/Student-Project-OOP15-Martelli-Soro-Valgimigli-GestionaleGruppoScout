package view.gui_utility;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public interface MyJPanel {

	/**
	 * create a JBUtton with title and actionListener
	 * @param title
	 * @param e
	 * @return
	 */
	JButton createButton(String title, ActionListener e);

	/**
	 * create a JButton with title,color and actionListener
	 * @param title
	 * @param c
	 * @param e
	 * @return
	 */
	JButton createButton(String title, Color c, ActionListener e);

	/**
	 * creare a JButton with title, color, font and actionListener
	 * @param title
	 * @param c
	 * @param f
	 * @param e
	 * @return
	 */

	JButton createButton(String title, Color c, Font f, ActionListener e);

	/**
	 * create a JButton with title,fontSize and actionlistener
	 * @param title
	 * @param fontSize
	 * @param e
	 * @return
	 */

	JButton createButton(String title, int fontSize, ActionListener e);

	/**
	 * return a TextArea with text, fontsize
	 * @param text area initial text
	 * @param editable true if areaText has to be editable
	 * @param fontSize font size dimensin
	 * @return
	 */
	JTextArea createJTextArea(String text, boolean editable, int fontSize);

	/**return a JLabel with text and fontSize
	 * 
	 * @param text Label text
	 * @param fontSize font size dimension
	 * @return
	 */
	JLabel createJLabel(String text, int fontSize);

}