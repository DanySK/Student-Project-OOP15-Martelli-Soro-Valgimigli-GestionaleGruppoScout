
package view.gui_utility;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;



/**Class used to easy create JPanel and add in it JButton
 * 
 * @author giovanni
 */

public class MyJPanelImpl extends JPanel implements  MyJPanel {
	private static final long serialVersionUID = 127205530034950651L;
	protected JPanel callerPanel;
	

	public MyJPanelImpl(){
		
	}
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
	/* (non-Javadoc)
	 * @see view.gui_utility.MyJPanel#createButton(java.lang.String, java.awt.event.ActionListener)
	 */
	@Override
	public JButton createButton(String title, ActionListener e){
		JButton button = new JButton(title);
		button.setName(title);
		button.addActionListener(e);
		return button;
	}
	/* (non-Javadoc)
	 * @see view.gui_utility.MyJPanel#createButton(java.lang.String, java.awt.Color, java.awt.event.ActionListener)
	 */
	@Override
	public JButton createButton(String title, Color c, ActionListener e){
		JButton button= this.createButton(title,e);
		button.setBackground(c);
		return button;
	}
	/* (non-Javadoc)
	 * @see view.gui_utility.MyJPanel#createButton(java.lang.String, java.awt.Color, java.awt.Font, java.awt.event.ActionListener)
	 */
	
	@Override
	public JButton createButton(String title, Color c, Font f, ActionListener e){
		JButton button = this.createButton(title, c,e);
		button.setFont(f);
		return button;
	}
	/* (non-Javadoc)
	 * @see view.gui_utility.MyJPanel#createJTextArea(java.lang.String, java.lang.String, boolean, int)
	 */
	@Override
	public JTextArea createJTextArea(String name, String text, boolean editable, int fontSize){
		JTextArea area=new JTextArea(text);
		area.setName(name);
		area.setEditable(editable);
		area.setFont(new Font("Aria", Font.ITALIC, fontSize));
		area.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		return area;
	}
	/* (non-Javadoc)
	 * @see view.gui_utility.MyJPanel#createJLabel(java.lang.String, java.lang.String, int)
	 */
	public JLabel createJLabel(String name,Optional<String> text, int fontSize ){
		return createJLabel(name, text.orElse(""), fontSize);
	}
	
	@Override
	public JLabel createJLabel(String name,String  text, int fontSize){
		JLabel label=new JLabel(text, SwingConstants.CENTER);
		label.setName(name);
		label.setFont(new Font("Aria", Font.ITALIC, fontSize));
		label.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		return label;
	}
	/**
	 * Get a JButton with back icon and as action listener it sets MyJFrameSingletonImpl pane as previous panel
	 * @return
	 */
	protected JButton getBackButton(){
		ImageIcon img = new ImageIcon("res/back-icon-small.png");
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
	/* (non-Javadoc)
	 * @see view.gui_utility.MyJPanel#getComponent(java.lang.String)
	 */
	@Override
	public Component getComponent(String name){
		List<Component> comp=Arrays.asList(this.getComponents());
		return comp.stream().filter(e->e.getName().equals(name)).findFirst().get();
	}
	
		
		
}