package view.gui_utility;

import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class MyFrameSingleton extends JFrame {
	/**
	 * A class model a JFrame using the Singleton pattern. Only one frame is(usualy) used in
	 * this app, each window is painted changing the contentPane of the unique JFrame.
	 * @author giovanni
	 */
	
	private static final long serialVersionUID = -5285934581393069862L;
	private static MyFrameSingleton myframe=null;
	public final static int HEIGTH;
	public final static int WIDTH;
	private static JPanel myFramePanel=new JPanel();
	
	static {
		HEIGTH=((int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight()/1.7));
		WIDTH = ((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2);
	}
	/** private Constructor
	 * 
	 */
	
	private  MyFrameSingleton(){
		this.setSize(WIDTH, HEIGTH);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		myFramePanel=new JPanel();
		this.setVisible(true);
		
		
		
	}
	/**It return the instance of JFrame
	 * 
	 * @return MyFrameSingleton
	 */
	public static MyFrameSingleton getInstance(){
		if(myframe==null){
			myframe=new MyFrameSingleton();
		}
		return myframe;
		
	}
	/**It set the ContentPane of MyFrameSingleton
	 * 
	 * @param panel
	 */
	public void setPanel(JPanel panel){
		SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run() {
				myFramePanel=panel;
				myframe.setContentPane(myFramePanel);
				myframe.setTitle(myFramePanel.getName());
				myframe.validate();
				myframe.repaint();				
			}
		});
	}
	/**It return the ContentPane of MyFrameSingleton
	 * 
	 * @return JPanel
	 */
	public JPanel getMyFramePanel(){
		return myFramePanel;
	}

}
