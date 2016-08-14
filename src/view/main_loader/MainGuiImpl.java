package view.main_loader;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import control.CheckerImpl;
import view.general_utility.WarningNotice;
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
public class MainGuiImpl extends MyJPanelImpl{
	private static final long serialVersionUID = 5093988269737955314L;
	/*
	 * Two Panel Are used:
	 * -South for JButton(GestioneReparto, GestioneTasse,GestioneEventi, Altro)
	 * -Nort for JButton(Options) and background Image
	 */
	private final JPanel south;
	private final JPanel north;
	private final int fontSize = 15;
	private final Image image=Toolkit.getDefaultToolkit().createImage("res/agesci.png"); //background image
	private final ImageIcon img = new ImageIcon("res/alert-icon.png");//options icon
	private String text ="";
	private final JButton opzioni;
	private CheckerImpl check;
	private boolean warning=false;
	
	public MainGuiImpl(){
		super("SCOUTAPP",MyJFrameSingletonImpl.getInstance().getContenentPane(), new BorderLayout());
		south = new MyJPanelImpl("south", this.callerPanel, new GridLayout(2,2));
		north = new MyJPanelImpl("nortg", this.callerPanel, new BorderLayout());
		check=new CheckerImpl();
		/* Add JButton in south panel(GestioneReparto, GestioneEventi,GestioneTasse,Altro)*/
		this.south.add(createButton("GestioneReparto", e->{
			new GestioneRepartoMain();
		}));
		
		this.south.add(createButton("Gestione Tasse", e->{
			new GestioneTasseMain();
		}));
	
		this.south.add(createButton("Gestione Eventi", e->{
			new GestioneEventiMain();
		}));
		
		this.south.add(createButton("Altro", e->{
			new WarningNotice("Al momento non sono presenti espansioni,"+System.lineSeparator()
					+ "quando saranno presenti verrano mostrate qui");
		}));
		
		/* Prepare JButton Opzioni, ActionListener-->open JPopupMenu */
		
		opzioni=new JButton();
		opzioni.setBackground(new Color(42,218,77));
		opzioni.setIcon(img);
		opzioni.setEnabled(false);
		if(this.checkOnStartup()){
			SwingUtilities.invokeLater(new Runnable() {
				
				@Override
				public void run() {
					opzioni.setToolTipText("<html>Ci sono alcuni avvisi importanti<html>");
					
				}
			});
			opzioni.setEnabled(true);
			opzioni.setBackground(new Color(252,168,23));
			opzioni.addActionListener(e->{
				
				JDialog dial = new JDialog();
				MyJPanelImpl pane= new MyJPanelImpl(new BorderLayout());
				pane.add(createJLabel( "<html><U>AVVISI REPARTO</U></html>", fontSize+8),BorderLayout.NORTH);
				text="<html>";
				MyJPanelImpl info=new MyJPanelImpl(new GridLayout(0,2));
			
				check.stdRouting(MyJFrameSingletonImpl.getInstance().getUnit()).keySet().stream()
				.forEach(k->{
					info.add(createJLabel( k+": ", fontSize+5));
					
					check.stdRouting(MyJFrameSingletonImpl.getInstance().getUnit()).get(k).stream()
					.forEach(t->{
						
						text=text+t.getName()+" "+t.getSurname()+"<br>";
						
					});
					text=text+"</html>";
					info.add(createJLabel( text, fontSize));
				});
				
				pane.add(info,BorderLayout.CENTER);
				MyJPanelImpl tmp=new MyJPanelImpl();
				tmp.add(createButton("OK", t->{
					dial.dispose();
				}));
				pane.add(tmp,BorderLayout.SOUTH);
				
				dial.add(pane);
				dial.pack();
				dial.setLocationRelativeTo(MyJFrameSingletonImpl.getInstance());
				dial.setVisible(true);
				
				
			});
		}
		this.north.add(opzioni, BorderLayout.LINE_END);
		
		/*Add South panel and North panel to main panel*/
		this.add(south, BorderLayout.SOUTH);
		this.add(north, BorderLayout.NORTH);
		
		/* Set this panel as componentPane of MyFrameSingleton istance */
		MyJFrameSingletonImpl.getInstance().setPanel(this);
		//////////////test a caso
		/*
		try {
			MyJFrameSingletonImpl.getInstance().getUnit().addExcursion(new CampoImpl(LocalDate.of(2016,8,13), 3,	MyJFrameSingletonImpl.getInstance().getUnit().getReparto(), "Ciao"));
			System.out.println(MyJFrameSingletonImpl.getInstance().getUnit().getContainers().getExcursion().stream().findFirst().get().getName());
		} catch (IllegalDateException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
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
		
		check.stdRouting(MyJFrameSingletonImpl.getInstance().getUnit()).keySet().stream()
		.forEach(e->{
			if(!check.stdRouting(MyJFrameSingletonImpl.getInstance().getUnit()).get(e).isEmpty()){
				warning=true;
			}
		});
		return warning;
	}

}
