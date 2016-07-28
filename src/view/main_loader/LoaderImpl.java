package view.main_loader;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
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
	private final JButton load;
	private final JButton create;
	private final JButton options;
	private final JTextArea northText;
	private final JButton ok=new JButton("OK");
	private final JPanel panelBotton=new JPanel();
	public LoaderImpl(){
		
		super("Loader", new JPanel(), new BorderLayout());
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
		northText=new JTextArea();
		northText.setFont(new Font("Aria", Font.ITALIC,18));
		northText.setEditable(false);
		northText.setBackground(this.getBackground());
		northText.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		northText.setText("Benvenuto nella schermata iniziale di SCOUTAPP!"+System.lineSeparator()+
				"Qui potrai caricare un reparto o crearne di nuovi."+System.lineSeparator()+
				"Il tasto Opzioni ti permette di modificare le impostazioni del programma");
		
			//tasto load
		LoaderUtil loader=new LoaderUtil(project);		
		load=new JButton("Carica");
		load.setToolTipText("Carica un reparto creato in precedenza");
		load.addActionListener(e->{loader. new LoadUnit(); this.frame.dispose();});
		
			//tasto create
		create=new JButton("Crea");
		create.setPreferredSize(load.getPreferredSize());
		create.setToolTipText("Crea un nuovo reparto e aggiungilo alla lista dei reparti gestiti");
		create.addActionListener(e->{loader. new CreateUnit(); this.frame.dispose();});
		
			//tasto options
		options=new JButton("Opzioni");
		options.setToolTipText("Opzioni del programma");
		options.setIcon(new ImageIcon("res/options.png"));
		options.addActionListener(e->{
			String oldText=northText.getText();
			LoaderUtil.LoaderOptions optPanel= loader. new LoaderOptions();
			ok.setSize(ok.getPreferredSize());
			optPanel.getPanelBottom().add(ok, BorderLayout.EAST);
			ok.addActionListener(f->{
				SwingUtilities.invokeLater(new Runnable(){
					@Override
					public void run() {
						northText.setText(oldText);
						remove(optPanel);
						add(panelBotton,BorderLayout.CENTER);
						repaint();
						validate();
						frame.pack();
						frame.repaint();
						frame.validate();
					}
					
				});
			});
			SwingUtilities.invokeLater(new Runnable(){
				@Override
				public void run() {
					northText.setText("Qui puoi cambiare la directory di salvataggio dei dati");
					remove(panelBotton);
					add(optPanel, BorderLayout.CENTER);
					repaint();
					validate();
					frame.repaint();
					frame.validate();
					
				}
				
			});
		});
		
		/*
		 * Aggiungo tutti i componenti al pannello principale (this)
		 */
		this.add(northText, BorderLayout.NORTH);
		panelBotton.add(load);
		panelBotton.add(create);
		panelBotton.add(options,BorderLayout.LINE_END);
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
