package view.gui_utility;

import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import control.Unit;
import control.UnitImpl;
/**
 * A class that model a JFrame using the Singleton pattern. Only one frame is(usualy) used in
 * this app, each window is painted changing the contentPane of the unique JFrame.
 * @author giovanni
 */
public class MyJFrameSingletonImpl extends JFrame implements MyJFrameSingleton{
	
	
	private static final long serialVersionUID = -5285934581393069862L;
	private static MyJFrameSingletonImpl myframe=null;
	public final static int HEIGTH;
	public final static int WIDTH;
	private static JPanel myFramePanel=new JPanel();
	private static UnitImpl unit;
	
	static {
		HEIGTH=((int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight()/1.1));
		WIDTH = ((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2);
	}
	/** private Constructor
	 * 
	 */
	@SuppressWarnings("static-access")
	private  MyJFrameSingletonImpl(Unit u){
		this.unit=(UnitImpl) u;
		this.setSize(WIDTH, HEIGTH);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		myFramePanel=new JPanel();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	/**It return the instance of JFrame
	 * 
	 * @return MyFrameSingleton
	 */
	public static MyJFrameSingletonImpl getInstance(){
		return myframe;
	}
	public static MyJFrameSingletonImpl getInstance(Unit u){
		if(myframe==null){
			myframe=new MyJFrameSingletonImpl(u);
		}
		
		return myframe;
	}
	
	/**It set the ContentPane of MyFrameSingleton
	 * 
	 * @param panel The main panel you want to set in this frame
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
	/**It returns the ContentPane already setted in MyFrameSingleton
	 * 
	 * @return JPanel	The main panel setted in this frame
	 */
	public JPanel getContenentPane(){
		return myFramePanel;
	}
	
	/**it return the UnitImpl working on
	 * @return UnitImpl UnitImpl working on
	 */
	public UnitImpl getUnit(){
		return MyJFrameSingletonImpl.unit;
	}
	
	
}
