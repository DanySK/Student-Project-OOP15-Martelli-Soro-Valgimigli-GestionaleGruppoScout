package view.gui_utility;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.Optional;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import model.Member;

public interface MyJPanel {

	/**
	 * 
	 * @param title JButton's text(and also name)
	 * @param e	ActionListener that will be addedd to the JButton
	 * @return JButton created
	 */
	JButton createButton(String title, ActionListener e);

	/**
	 * 
	 * @param title	JButton's text(and also name)
	 * @param c	JButton's background color
	 * @param e	ActionListener that will be addedd to the JButton
	 * @return JButton created
	 */
	JButton createButton(String title, Color c, ActionListener e);

	/**
	 * 
	 * @param title	JButton's text(and also name)
	 * @param c	JButton's background color
	 * @param f JButton's text font
	 * @param e	ActionListener that will be addedd to the JButton
	 * @return JButton created
	 */

	JButton createButton(String title, Color c, Font f, ActionListener e);

	/**
	 * 
	 * @param name JTextArea's name
	 * @param text JTextArea's text
	 * @param editable is editable?true:false :)
	 * @param fontSize JTextArea's font size
	 * @return JTextArea created
	 */
	JTextArea createJTextArea(String name, String text, boolean editable, int fontSize);

	/**
	 * 
	 * @param name JLabel's name
	 * @param text JLabel's text(can be null)
	 * @param fontSize	JLabel's text font size
	 * @return	JLabel created
	 */
	JLabel createJLabel(String name, Optional<Member> text, int fontSize);

	/**
	 * 
	 * @param name JLabel's name
	 * @param text JLabel's text(can't be null)
	 * @param fontSize	JLabel's text font size
	 * @return JLabel created
	 */
	JLabel createJLabel(String name, String text, int fontSize);

	/**
	 * 
	 * @param name Searched component's name	
	 * @return	Component founded(can fail and throw exception
	 */
	Component getComponent(String name);

}