package view.main_loader;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import control.CheckerImpl;
import model.Excursion;
import model.Squadron;
import model.Member;
import view.gestioneReparto.GestioneRepartoMainImpl;
import view.gui_utility.MyJFrameSingletonImpl;
import view.gui_utility.MyJPanelImpl;
/**
 * Class that create a MyJPanel for the Main page of this app, and it sets this MyJPanel like the
 * contentPane of MyFrameSingleton
 * @author giovanni
 *
 */
public class MainGuiImpl extends MyJPanelImpl{
	private static final long serialVersionUID = 5093988269737955314L;
	/*
	 * Two Panel Are used:
	 * -South for JButton(GestioneReparto, GestioneTasse,GestioneEventi, Altro)
	 * -Nort for JButton(Options) and background Image
	 */
	private final JPanel south;
	private final JPanel north;
	
	private final Image image=Toolkit.getDefaultToolkit().createImage("res/agesci.png"); //background image
	private final ImageIcon img = new ImageIcon("res/alert-icon.png");//options icon

	private final JButton opzioni;
	
	
	
	public MainGuiImpl(){
		super("SCOUTAPP",MyJFrameSingletonImpl.getInstance().getContenentPane(), new BorderLayout());
		south = new MyJPanelImpl("south", this.callerPanel, new GridLayout(2,2));
		north = new MyJPanelImpl("nortg", this.callerPanel, new BorderLayout());
		
		/* Add JButton in south panel(GestioneReparto, GestioneEventi,GestioneTasse,Altro)*/
		this.south.add(createButton("GestioneReparto", e->{
			new GestioneRepartoMainImpl();
		}));
		
		this.south.add(createButton("Gestione Tasse", e->{
			
		}));
	
		this.south.add(createButton("Gestione Eventi", e->{
			
		}));
		
		this.south.add(createButton("Altro", e->{
			
		}));
		
		/* Prepare JButton Opzioni, ActionListener-->open JPopupMenu */
		opzioni=new JButton();
		opzioni.setBackground(new Color(42,218,77));
		opzioni.setIcon(img);
		opzioni.setEnabled(false);
		
		if(this.checkOnStartup()){
			opzioni.setEnabled(true);
			opzioni.setBackground(new Color(252,168,23));
		}
		this.north.add(opzioni, BorderLayout.LINE_END);
		
		
		/*Add South panel and North panel to main panel*/
		this.add(south, BorderLayout.SOUTH);
		this.add(north, BorderLayout.NORTH);
		
		/* Set this panel as componentPane of MyFrameSingleton istance */
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
	
	private boolean checkOnStartup(){
		List<Excursion> excursion=new ArrayList<>();
		List<Squadron> squadron=new ArrayList<>();
		List<Member> member=new ArrayList<>();
		//return (new CheckerImpl()).stdRouting(excursion, member,squadron).isEmpty();
		return false;
	}

}
