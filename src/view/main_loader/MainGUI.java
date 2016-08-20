package view.main_loader;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import control.CheckerImpl;
import view.Main;
import view.excursion_manager.ExcursionManagerMain;
import view.fee_manager.FeeManagerMain;
import view.gui_utility.MyJFrameSingletonImpl;
import view.gui_utility.MyJPanelImpl;
import view.online.OnlineMainImpl;
import view.unit_manager.UnitManagerMain;

/**
 * Class that create a MyJPanel for the Main page of this app, and it sets this
 * MyJPanel like the contentPane of MyFrameSingleton
 * 
 * @author giovanni
 *
 */
public class MainGUI extends MyJPanelImpl {
	private static final long serialVersionUID = 5093988269737955314L;
	/*
	 * Two Panel Are used: -South for JButton(GestioneReparto,
	 * GestioneTasse,GestioneEventi, Altro) -Nort for JButton(Options) and
	 * background Image
	 */
	private final CheckerImpl check = new CheckerImpl();
	private final Image image = Toolkit.getDefaultToolkit().createImage(Main.class.getResource("/agesci.png"));
	private final ImageIcon img = new ImageIcon(Main.class.getResource("/alertIcon.png"));
	private final static int FONTSIZE = 15;
	private String text = "";
	private final JButton opzioni;
	private boolean warning = false;

	public MainGUI() {
		super("SCOUTAPP", MyJFrameSingletonImpl.getInstance().getContenentPane(), new BorderLayout());
		

		final JPanel south = new MyJPanelImpl("south", this.callerPanel, new GridLayout(2, 2));
		final JPanel north = new MyJPanelImpl("nortg", this.callerPanel, new BorderLayout());

		

		/*
		 * Add JButton in south panel(GestioneReparto,
		 * GestioneEventi,GestioneTasse,Altro)
		 */
		south.add(createButton("GestioneReparto", e -> {
			new UnitManagerMain();
		}));

		south.add(createButton("Gestione Tasse", e -> {
			new FeeManagerMain();
		}));

		south.add(createButton("Gestione Eventi", e -> {
			new ExcursionManagerMain();
		}));

		south.add(createButton("Online", e -> {
			new OnlineMainImpl();
			
		}));

		/* Prepare JButton Opzioni, ActionListener-->open JPopupMenu */

		opzioni = new JButton();
		opzioni.setBackground(new Color(42, 218, 77));
		opzioni.setIcon(img);
		opzioni.setEnabled(false);
		if (this.checkOnStartup()) {
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					opzioni.setToolTipText("<html>Ci sono alcuni avvisi importanti<html>");
				}
			});
			opzioni.setEnabled(true);
			opzioni.setBackground(new Color(252, 168, 23));
			opzioni.addActionListener(e -> {
				final JDialog dial = new JDialog();
				final MyJPanelImpl pane = new MyJPanelImpl(new BorderLayout());
				pane.add(createJLabel("<html><U>AVVISI REPARTO</U></html>", FONTSIZE + 8), BorderLayout.NORTH);
				text = "<html>";
				final MyJPanelImpl info = new MyJPanelImpl(new GridLayout(0, 2));
				
				final Map<String,List<String>> mapAvvisi=check.stdRouting(MyJFrameSingletonImpl.getInstance().getUnit());
				mapAvvisi.keySet().stream().forEach(k->{
					info.add(createJLabel(k + ": ", FONTSIZE + 5));
					mapAvvisi.get(k).stream().forEach(t -> {
						text = text + t + "<br>";

					});
					text = text + "</html>";
					info.add(createJLabel(text, FONTSIZE));
					text = "<html>";
				});

				pane.add(info, BorderLayout.CENTER);
				final MyJPanelImpl tmp = new MyJPanelImpl();
				tmp.add(createButton("OK", t -> {
					dial.dispose();
				}));
				pane.add(tmp, BorderLayout.SOUTH);

				dial.add(pane);
				dial.pack();
				dial.setLocationRelativeTo(MyJFrameSingletonImpl.getInstance());
				dial.setVisible(true);

			});
		}
		north.add(opzioni, BorderLayout.LINE_END);

		/* Add South panel and North panel to main panel */
		this.add(south, BorderLayout.SOUTH);
		this.add(north, BorderLayout.NORTH);

		/* Set this panel as componentPane of MyFrameSingleton istance */
		MyJFrameSingletonImpl.getInstance().setPanel(this);

	}

	/**
	 * Override used to paint the background image
	 * 
	 */

	@Override
	public void paintComponent(final Graphics g) {
		super.paintComponent(g);
		try {
			if (image != null) {
				g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
			}
		} catch (final Exception e) {
			System.err.println("");
		}
	}

	private boolean checkOnStartup() {
		check.stdRouting(MyJFrameSingletonImpl.getInstance().getUnit()).keySet().stream().forEach(e -> {
			if (!check.stdRouting(MyJFrameSingletonImpl.getInstance().getUnit()).get(e).isEmpty()) {
				warning = true;
			}
		});
		return warning;
	}

}
