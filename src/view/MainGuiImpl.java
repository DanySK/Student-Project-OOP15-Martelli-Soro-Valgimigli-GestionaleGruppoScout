package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import view.altro.AltroMain;
import view.gestioneEventi.GestioneEventiMain;
import view.gestioneReparto.GestioneRepartoMain;
import view.gestioneTasse.GestioneTasseMain;
import view.gui_utility.MyJFrameSingletonImpl;
import view.gui_utility.MyJPanelImpl;
/**
 * Class that create a MyJPanel for the Main page of this app, and it sets this MyJPanel like the
 * contentPane of MyFrameSingleton
 * @author giovanni
 *
 */
public class MainGuiImpl extends MyJPanelImpl implements  MainGui{
	private static final long serialVersionUID = 5093988269737955314L;
	/*
	 * Two Panel Are used:
	 * -South for JButton(GestioneReparto, GestioneTasse,GestioneEventi, Altro)
	 * -Nort for JButton(Options) and background Image
	 */
	private final JPanel south;
	private final JPanel north;
	
	private final Image image=Toolkit.getDefaultToolkit().createImage("res/agesci.png"); //background image
	private final ImageIcon img = new ImageIcon("res/options.png");//options icon

	private final JPopupMenu popupMenu;
	private final JButton opzioni;
	
	/*
	 * static part that sets look&fell of the entire application
	 */
	static{
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		    	if ("Nimbus".equals(info.getName())) {
		    		UIManager.setLookAndFeel(info.getClassName());
		            UIDefaults ui = UIManager.getLookAndFeelDefaults();
		            ui.put("Panel.background", new Color(42,218,77));
		            ui.put("Button.background", new Color(174,226,84));
		            ui.put("Button.font", new Font("Aria", Font.ITALIC, 30));
		            ui.put("PopupMenu.background", new Color(42,218,77));
		            ui.put("Menu.background",new Color(42,218,77));
		            ui.put("Menu.opaque", false);
		            ui.put("MenuItem.background", new Color(42,218,65));
		            ui.put("MenuItem.opaque", true);
		            ui.put("PopupMenu.contentMargins",new Insets(1,1,1,1));
		            break;
		        }
		    }
		} catch (Exception e) {
		    e.printStackTrace();
		}
	}
	
	public MainGuiImpl(){
		super("SCOUTAPP",MyJFrameSingletonImpl.getInstance().getContenentPane(), new BorderLayout());
		south = new MyJPanelImpl("south", this.callerPanel, new GridLayout(2,2));
		north = new MyJPanelImpl("nortg", this.callerPanel, new BorderLayout());
		
		/*
		 * Add JButton in south panel(GestioneReparto, GestioneEventi,GestioneTasse,Altro)
		 */
		this.south.add(createButton("Gestione Reparto", e->{
			new GestioneRepartoMain(MyJFrameSingletonImpl.getInstance().getContenentPane());
		}));
		
		this.south.add(createButton("Gestione Tasse", e->{
			new Thread(new GestioneTasseMain(MyJFrameSingletonImpl.getInstance().getContenentPane())).start();
		}));
	
		this.south.add(createButton("Gestione Eventi", e->{
			new Thread(new GestioneEventiMain(MyJFrameSingletonImpl.getInstance().getContenentPane())).start();
		}));
		
		this.south.add(createButton("Altro", e->{
			new Thread(new AltroMain(MyJFrameSingletonImpl.getInstance().getContenentPane())).start();
		}));
		/*
		 * Prepare JButton Opzioni, ActionListener-->open JPopupMenu
		 */
		opzioni=new JButton();
		popupMenu= new JPopupMenu();
		opzioni.setBackground(new Color(42,218,77));
		opzioni.setIcon(img);
		opzioni.addActionListener(e->{
			popupMenu.show(this.opzioni, opzioni.getWidth()/2, opzioni.getHeight()/2);
		});
		/*
		 * add JMenuItem to JPopUpMenu
		 */
		popupMenu.add(new JMenuItem(new AbstractAction("Option 2") {
           private static final long serialVersionUID = -862401522282125430L;
           public void actionPerformed(ActionEvent e) {
                
           }
		}));
		this.north.add(opzioni, BorderLayout.LINE_START);
		/*
		 * Add South panel and North panel to main panel 
		 */
		this.add(south, BorderLayout.SOUTH);
		this.add(north, BorderLayout.NORTH);
		
		/*
		 * Set this panel as componentPane of MyFrameSingleton istance
		 */
		MyJFrameSingletonImpl.getInstance().setPanel(this);
	}

	/**Override used to paint the background image
	 * 
	 */
	
	@Override
	public void paintComponent(Graphics g){
		    super.paintComponent(g);
		    if (image != null){
		        g.drawImage(image, 0,0,getWidth(), getHeight(), this);
		    }
	}

}
