package view.main_loader;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import control.MasterProjectImpl;
import view.general_utility.WarningNotice;
import view.gui_utility.MyJPanelImpl;

/**
 * Class tha create the App Loader. SCOUTAPP will start from this
 * 
 * @author Giovanni Martelli
 */

public class LoaderImpl extends MyJPanelImpl {

	private static final long serialVersionUID = 2929901797522523355L;

	private MasterProjectImpl project;
	private final JFrame frame;
	private final MyJPanelImpl panelBotton = new MyJPanelImpl();
	private String northString;

	public LoaderImpl() {

		super(new BorderLayout());
		/*
		 * Inizializzo il MasterProjectImpl
		 */
		this.frame = new JFrame();
		try {
			this.project = new MasterProjectImpl();
		} catch (Exception e) {
			new WarningNotice(e.getMessage());
		}
		/*
		 * Inizializzo e personalizzo i vari componenti
		 */
		// JTextArea in alto
		northString = "Benvenuto nella schermata iniziale di SCOUTAPP!" + System.lineSeparator()
				+ "Qui potrai caricare un reparto o crearne di nuovi." + System.lineSeparator()
				+ "Il tasto Opzioni ti permette di modificare le impostazioni del programma";
		JTextArea areaNorth = createJTextArea(northString, false, 18);
		this.add(areaNorth, BorderLayout.NORTH);
		areaNorth.setBackground(this.getBackground());

		// tasto load
		LoaderUtil loader = new LoaderUtil(project);
		panelBotton.add(this.createButton("Salvataggi", e -> {
			loader.new LoadUnit();
			this.frame.dispose();
		}));

		// tasto crea
		panelBotton.add(this.createButton("Crea", e -> {
			loader.new CreateUnit();
			this.frame.dispose();
		}));

		// tasto opzioni
		panelBotton.add(this.createButton("Opzioni", e -> {
			LoaderUtil.LoaderOptions optPanel = loader.new LoaderOptions();
			optPanel.getPanelBottom().add(this.createButton("ok", f -> {
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						areaNorth.setText(northString);
						remove(optPanel);
						add(panelBotton, BorderLayout.CENTER);
						repaint();
						validate();
						frame.pack();
						frame.repaint();
						frame.validate();
					}
				});
			}));
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					areaNorth.setText("Qui puoi cambiare la directory di salvataggio dei dati");
					remove(panelBotton);
					add(optPanel, BorderLayout.CENTER);
					repaint();
					validate();
					frame.repaint();
					frame.validate();
				}
			});
		}));
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
