package view.gui_utility;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;

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
/*
	/**
	 * 
	 * @param name JTextArea's name
	 * @param text JTextArea's text
	 * @param editable is editable?true:false :)
	 * @param fontSize JTextArea's font size
	 * @return JTextArea created
	 */
	JTextArea createJTextArea( String text, boolean editable, int fontSize);

	

	/**
	 * 
	 * @param name JLabel's name
	 * @param text JLabel's text(can't be null)
	 * @param fontSize	JLabel's text font size
	 * @return JLabel created
	 */
	JLabel createJLabel( String text, int fontSize);



}