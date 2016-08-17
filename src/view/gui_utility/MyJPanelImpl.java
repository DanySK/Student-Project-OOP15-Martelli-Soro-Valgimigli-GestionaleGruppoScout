
package view.gui_utility;

import java.awt.Color;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import view.Main;



/**Class used to easy create JPanel and add in it JButton
 * 
 * @author giovanni
 */

public class MyJPanelImpl extends JPanel implements  MyJPanel{
	private static final long serialVersionUID = 127205530034950651L;
	protected JPanel callerPanel;
	
	/**Constructor
	 * 
	 */
	public MyJPanelImpl(){
		
	}
	/**Constructor
	 * @param layout JPanel layout
	 * */
	public MyJPanelImpl(LayoutManager layout){
		this.setLayout(layout);
	}
	
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
	/**
	 * create a JBUtton with title and actionListener
	 * @param title
	 * @param e
	 * @return
	 */
	@Override
	public JButton createButton(String title, ActionListener e){
		JButton button = new JButton(title);
		button.setName(title);
		button.addActionListener(e);
		return button;
	}
	/** create a JButton with title,color and actionListener
	 * @param title
	 * @param c
	 * @param e
	 * @return
	 */
	@Override
	public JButton createButton(String title, Color c, ActionListener e){
		JButton button= this.createButton(title,e);
		button.setBackground(c);
		return button;
	}

	/**
	 * creare a JButton with title, color, font and actionListener
	 * @param title
	 * @param c
	 * @param f
	 * @param e
	 * @return
	 */
	
	@Override
	public JButton createButton(String title, Color c, Font f, ActionListener e){
		JButton button = this.createButton(title, c,e);
		button.setFont(f);
		return button;
	}
	/**
	 * create a JButton with title,fontSize and actionlistener
	 * @param title
	 * @param fontSize
	 * @param e
	 * @return
	 */

	@Override
	public JButton createButton(String title, int fontSize, ActionListener e){
		JButton button=new JButton(title);
		button.setName(title);
		button.addActionListener(e);
		button.setFont(new Font("Aria", Font.ITALIC, fontSize));
		return button;
	}

	/**
	 * return a TextArea with text, fontsize
	 * @param text area initial text
	 * @param editable true if areaText has to be editable
	 * @param fontSize font size dimensin
	 * @return
	 */
	@Override
	public JTextArea createJTextArea(String text, boolean editable, int fontSize){
		JTextArea area=new JTextArea(text);
		area.setEditable(editable);
		area.setFont(new Font("Aria", Font.ITALIC, fontSize));
		area.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		return area;
	}
	/**return a JLabel with text and fontSize
	 * 
	 * @param text Label text
	 * @param fontSize font size dimension
	 * @return
	 */
	@Override
	public JLabel createJLabel(String  text, int fontSize){
		JLabel label=new JLabel(text, SwingConstants.CENTER);
		label.setFont(new Font("Aria", Font.ITALIC, fontSize));
		label.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		return label;
	}
	/**
	 * Get a JButton with back icon and as action listener it sets MyJFrameSingletonImpl pane as previous panel
	 * @return JButton created
	 */
	protected JButton getBackButton(){
		ImageIcon img = new ImageIcon(Main.class.getResource("/backIconSmall.png"));
		JButton t=new JButton();
		t.addActionListener(e->{
			
			try {
				MyJFrameSingletonImpl.getInstance().setPanel(callerPanel.getClass().newInstance());
			} catch (InstantiationException | IllegalAccessException e1) {
				
			}
		});
		t.setIcon(img);
		return t;
	}

	
	
	
		
		
}