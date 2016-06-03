package view.utility;

import java.awt.Color;


import java.awt.Dimension;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public abstract class WindowsGUIGeneral extends Thread {
	protected final JFrame frame;
	protected JPanel panel;
	private final static Dimension dim;
	public final static int HEIGTH;
	public final static int WIDTH;
	private Map<String, JButton> buttons  = new HashMap<>();
	
	
	private JFrame parent;
	
	/*this sets the window's dimension*/
	static {
		dim = Toolkit.getDefaultToolkit().getScreenSize();
		HEIGTH=((int)(dim.getHeight()/1.7));
		WIDTH = ((int)dim.getWidth()/2);
	
	
	}
	/**Constructor*/
	
	protected WindowsGUIGeneral(String title,JFrame parent ) {
		this.frame = new JFrame(title);
		this.frame.setSize(WIDTH, HEIGTH);
		this.frame.setLocationRelativeTo(null);
		this.panel = new JPanel();
		hideParent(parent);
		frame.setContentPane(panel);
	}
	/**Constructor*/
	protected WindowsGUIGeneral(String title,JFrame parent, LayoutManager layout) {
		this(title, parent);
		frame.setLayout(layout);
	}
	/** LayoutManager setter*/
	protected void setLayout(LayoutManager layout){
		this.panel.setLayout(layout);
	}
	/**LayoutManager getter*/
	protected LayoutManager getLayout(){
		return this.panel.getLayout();
	}
		/**Add button in a multiple-panel window*/
	private JButton createButton(String title, Color c, Font font, ActionListener e){
		JButton button = new JButton(title);
		button.addActionListener(e);
		button.setFocusPainted(false);
		button.setBorderPainted(false);
		button.setBackground(c);
		button.setFont(font);
		return button;
	}
	
	public void addButton(String title, JPanel panelRequest, Color c, Font font, ActionListener e, Object constraints){
		JButton temp = createButton(title, c, font, e);
		buttons.put(title, temp);
		panelRequest.add(temp, constraints);
		this.frame.validate();
		
		
	}
	
	public void addButton(String title, JPanel panelRequest, Color c, Font font, ActionListener e){
		JButton temp = createButton(title, c, font, e);
		buttons.put(title, temp);
		panelRequest.add(temp);
		this.frame.validate();
	}
		
	/**Hide frame of the window who called this window*/
	protected void hideParent(JFrame parentalFrame){
		parent=parentalFrame;
		parent.setVisible(false);
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        SwingUtilities.invokeLater(new Runnable(){
		        	public void run() {
		        		parentalFrame.setVisible(true);
					}
		        });
		        frame.dispose();
		       
		    }
		});
	}
	
	/**get frame who called this window*/
	protected JFrame getParent(){
		return this.parent;
	}
	
	
	public JFrame getFrame(){
		return this.frame;
	}
	
		
}
