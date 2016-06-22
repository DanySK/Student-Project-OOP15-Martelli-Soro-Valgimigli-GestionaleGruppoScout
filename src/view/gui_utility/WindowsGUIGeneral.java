
package view.gui_utility;

import java.awt.Color;
import java.awt.Font;
import java.awt.LayoutManager;

import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**Class used to easy create GUI's windows
 * 
 * @author giovanni
 */

public abstract class WindowsGUIGeneral extends JPanel{
	private static final long serialVersionUID = 127205530034950651L;
	
	

	
	protected JPanel callerPanel;

	
	/**Constructor*/
	
	protected WindowsGUIGeneral(JPanel callerPanel) {
		this.callerPanel=callerPanel;
		
	}
	/**Constructor*/
	protected WindowsGUIGeneral(JPanel callerPanel, LayoutManager layout) {
		this(callerPanel);
		this.setLayout(layout);
	}
	
		/**Add button in a multiple-panel window*/
	private JButton createButton(String title, Color c, Font font, ActionListener e){
		JButton button = new JButton(title);
		button.addActionListener(e);
		button.setBorderPainted(false);
		button.setBackground(c);
		button.setFont(font);
		return button;
	}
	
	public void addButton(String title, JPanel panelRequest, Color c, Font font, ActionListener e, Object constraints){
		JButton temp = createButton(title, c, font, e);
		panelRequest.add(temp, constraints);
		this.validate();
	}
	
	public void addButton(String title, JPanel panelRequest, Color c, Font font, ActionListener e){
		JButton temp = createButton(title, c, font, e);
		panelRequest.add(temp);
		this.validate();
	}
		
		
}