
package view.gui_utility;

import java.awt.Color;

import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;



/**Class used to easy create JPanel and add in it JButton
 * 
 * @author giovanni
 */

public class MyJPanelImpl extends JPanel/* implements MyJPanel*/ {
	private static final long serialVersionUID = 127205530034950651L;
	protected JPanel callerPanel;
	
	/**Constructor
	 * @param title JPanel title
	 * @param callerPanel JPanel who called/contains this panel
	 * */
	
	public MyJPanelImpl(String title, JPanel callerPanel) {
		this.setName(title);
		this.callerPanel=callerPanel;
		this.setBackground(new Color(42,218,77));
		this.setOpaque(false);
	}
	
	/**Constructor
	 * @param title JPanel's title
	 * @param callerPanel JPanel who called/contains this panel
	 * @param LayoutManager JPanel's Layout
	 * */
	
	public MyJPanelImpl(String title, JPanel callerPanel, LayoutManager layout) {
		this(title, callerPanel);
		this.setLayout(layout);
	}
	/**Create a JButton
	 * 
	 * @param title JButton's title
	 * @param e JButton's ActionListener
	 * @return The JButton created
	 */
	protected JButton createButton(String title, ActionListener e){
		JButton button = new JButton(title);
		button.addActionListener(e);
		return button;
	}
	/**Create a JButton
	 * 
	 * @param title JButton's title
	 * @param e JButton's ActionListener
	 * @param c	JButton's background color
	 * @return The JButton created
	 */
	protected JButton createButton(String title, Color c, ActionListener e){
		JButton button= this.createButton(title,e);
		button.setBackground(c);
		return button;
	}
	/**Create a JButton
	 * 
	 * @param title JButton's title
	 * @param e JButton's ActionListener
	 * @param c	JButton's background color
	 * @param f	JButton Title's font
	 * @return The JButton created
	 */
	
	protected JButton createButton(String title, Color c, Font f, ActionListener e){
		JButton button = this.createButton(title, c,e);
		button.setFont(f);
		return button;
	}
	
	protected JButton getBackButton(){
		ImageIcon img = new ImageIcon("res/back-icon.png");//options icon
		JButton t=new JButton();
		t.addActionListener(e->{
			MyJFrameSingletonImpl.getInstance().setPanel(this.callerPanel);
		});
		t.setIcon(img);
		return t;
	}
	
		
		
}