package view.main_loader;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import control.MasterProjectImpl;
import view.general_utility.WarningNotice;
import view.gui_utility.MyJPanelImpl;
/**Class tha create the App Loader. SCOUTAPP will start from this
 * @author Giovanni Martelli
 */

public class LoaderImpl extends MyJPanelImpl {
	
	private static final long serialVersionUID = 2929901797522523355L;
	
	private MasterProjectImpl project;
	private final JFrame frame;
	private final MyJPanelImpl panelBotton=new MyJPanelImpl();
	private String northString;
	public LoaderImpl(){
		
		super(new BorderLayout());
		/*
		 * Inizializzo il MasterProjectImpl
		 */
		this.frame=new JFrame();
		try{
			this.project=new MasterProjectImpl();
		}catch(Exception e){
			new WarningNotice(e.getMessage());
		}
		/*
		 * Inizializzo e personalizzo i vari componenti
		 */
		
			//JTextArea in alto
		northString="Benvenuto nella schermata iniziale di SCOUTAPP!"+System.lineSeparator()+
				"Qui potrai caricare un reparto o crearne di nuovi."+System.lineSeparator()+
				"Il tasto Opzioni ti permette di modificare le impostazioni del programma";
		this.add(createJTextArea("north", northString, false, 18),BorderLayout.NORTH);
		this.getComponent("north").setBackground(this.getBackground());
			
			//tasto load
		LoaderUtil loader=new LoaderUtil(project);	
		panelBotton.add(this.createButton("Carica", e->{loader. new LoadUnit();this.frame.dispose();}));
		((JButton)panelBotton.getComponent("Carica")).setToolTipText("Qui è possibile caricare i reparti salvati");
			//tasto crea
		panelBotton.add(this.createButton("Crea", e->{loader. new CreateUnit(); this.frame.dispose();}));
		panelBotton.getComponent("Crea").setPreferredSize(panelBotton.getComponent("Carica").getPreferredSize());
		((JButton)panelBotton.getComponent("Crea")).setToolTipText("Qui è possibile creare nuovi reparti");
			
			//tasto opzioni
		panelBotton.add(this.createButton("Opzioni", e->{
			String oldText=((JTextArea)getComponent("north")).getText();
			LoaderUtil.LoaderOptions optPanel= loader. new LoaderOptions();
			optPanel.getPanelBottom().add(this.createButton("ok",f->{
				SwingUtilities.invokeLater(new Runnable(){
					@Override
					public void run(){
						((JTextArea)getComponent("north")).setText(oldText);
						remove(optPanel);
						add(panelBotton,BorderLayout.CENTER);
						repaint();
						validate();
						frame.pack();
						frame.repaint();
						frame.validate();
					}
				});
			}),BorderLayout.EAST);
			SwingUtilities.invokeLater(new Runnable(){
				@Override
				public void run() {
					((JTextArea)getComponent("north")).setText("Qui puoi cambiare la directory di salvataggio dei dati");
					remove(panelBotton);
					add(optPanel, BorderLayout.CENTER);
					repaint();
					validate();
					frame.repaint();
					frame.validate();
				}
			});
		}));
		((JButton)panelBotton.getComponent("Opzioni")).setToolTipText("Qui è possibile cambiare la directory di salvataggio");
		add(panelBotton, BorderLayout.CENTER);
		
		/*
		 * Aggiungo il tutto al JFrame
		 */
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(this);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
	}
}
