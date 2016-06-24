package view;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import java.awt.Toolkit;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;

import view.altro.AltroMain;
import view.gestioneEventi.GestioneEventiMain;
import view.gestioneReparto.GestioneRepartoMain;
import view.gestioneTasse.GestioneTasseMain;
import view.gui_utility.MyJFrameSingletonImpl;
import view.gui_utility.MyJPanelImpl;
/**
 * Class that create a JPanel for the Main page of this app, and it this JPanel like the
 * contentPane of MyFrameSingleton
 * @author giovanni
 *
 */
public class MainGuiImpl extends MyJPanelImpl implements Runnable, MainGui{
	private static final long serialVersionUID = 5093988269737955314L;
	
	private final JPanel south;
	private final JPanel north;
	
	private final Image image=Toolkit.getDefaultToolkit().createImage("res/agesci.png"); //background image
	private ImageIcon img = new ImageIcon("res/options.png");//options icon
	
	private final JPopupMenu popup= new JPopupMenu();
	private final JButton opzioni=new JButton();
	
	/*
	 * setta il look&feel dell'intera applicazione
	 */
	
	static{
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		    	if ("Nimbus".equals(info.getName())) {
		    		UIManager.setLookAndFeel(info.getClassName());
		            UIDefaults ui = UIManager.getLookAndFeelDefaults();
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
		    
		}
	}
	
	public MainGuiImpl(){
		super("SCOUTAPP",MyJFrameSingletonImpl.getInstance().getMyFramePanel());
		south = new MyJPanelImpl("south", this.callerPanel, new GridLayout(2,2));
		north = new MyJPanelImpl("nortg", this.callerPanel, new BorderLayout());
		
		
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

	/** Method to lauch this class(and the entire GUI
	 * 	to launch use: new Thread(new MainGuiImpl()).start();
	 */
	@Override
	public void run() {
		this.setLayout(new BorderLayout());
		
		this.south.add(createButton("Gestione Reparto", e->{
			new Thread(new GestioneRepartoMain(MyJFrameSingletonImpl.getInstance().getMyFramePanel())).start();
		}));
		
		this.south.add(createButton("Gestione Tasse", e->{
			new Thread(new GestioneTasseMain(MyJFrameSingletonImpl.getInstance().getMyFramePanel())).start();
		}));
	
		this.south.add(createButton("Gestione Eventi", e->{
			new Thread(new GestioneEventiMain(MyJFrameSingletonImpl.getInstance().getMyFramePanel())).start();
		}));
		
		this.south.add(createButton("Altro", e->{
			new Thread(new AltroMain(MyJFrameSingletonImpl.getInstance().getMyFramePanel())).start();
		}));
		
		/*parte in cui si aggiungono le voci al JPopupMenu */
		popup.add(new JMenuItem(new AbstractAction("Option 2") {
           private static final long serialVersionUID = -862401522282125430L;
           public void actionPerformed(ActionEvent e) {
                
           }
		}));
		popup.add(new JMenuItem(new AbstractAction("Option 3") {
            /**
			 * 
			 */
			private static final long serialVersionUID = 960119885292445229L;

			public void actionPerformed(ActionEvent e) {
                
            }
		}));
		popup.add(new JMenuItem(new AbstractAction("Option 4") {
            /**
			 * 
			 */
			private static final long serialVersionUID = 2033028040248996602L;

			public void actionPerformed(ActionEvent e) {
                
            }
		}));
		opzioni.setBackground(new Color(42,218,77));
		opzioni.setIcon(img);
		opzioni.addActionListener(e->{
			popup.show(this.opzioni, opzioni.getWidth()/2, opzioni.getHeight()/2);
		});
		this.north.add(opzioni, BorderLayout.LINE_START);
		
		/*aggiungo i due pannelli secondari al pannello principale*/
		this.add(south, BorderLayout.SOUTH);
		this.add(north, BorderLayout.NORTH);
		
		
		MyJFrameSingletonImpl.getInstance().setPanel(this);
	
		
		
		
	}
	
	  
	
	

}
