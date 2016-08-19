package view.gui_utility;

import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import control.Unit;
import control.UnitImpl;

/**
 * A class that model a JFrame using the Singleton pattern. Only one frame
 * is(usualy) used in this app, each window is painted changing the contentPane
 * of the unique JFrame.
 * 
 * @author giovanni
 */
public final class MyJFrameSingletonImpl extends JFrame implements MyJFrameSingleton {

	private static final long serialVersionUID = -5285934581393069862L;
	private static MyJFrameSingletonImpl myframe;
	public final static int HEIGTH;
	public final static int WIDTH;
	private boolean needSave = false;
	private static JPanel myFramePanel = new JPanel();
	private static UnitImpl unit;

	static {
		HEIGTH = ((int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 1.2));
		WIDTH = ((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 1.7));
	}

	/**
	 * private Constructor
	 * 
	 */
	@SuppressWarnings("static-access")
	private MyJFrameSingletonImpl(Unit u) {
		this.unit = (UnitImpl) u;
		this.setSize(WIDTH, HEIGTH);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		myFramePanel = new JPanel();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				int confirmed = JOptionPane.YES_OPTION;
				if (MyJFrameSingletonImpl.getInstance().isToBeSaved()) {
					confirmed = JOptionPane.showConfirmDialog(null,
							"<html>Ci sono modifiche non salvate!<br>"
									+ "Sicuro di voler proseguire senza salvare?</html>",
							"ATTENZIONE!", JOptionPane.YES_NO_OPTION);

				}
				if (confirmed == JOptionPane.NO_OPTION) {

				} else {
					dispose();
					System.exit(0);
				}

			}
		});
	}

	/**
	 * It return the instance of JFrame
	 * 
	 * @return MyFrameSingleton
	 */

	public static MyJFrameSingletonImpl getInstance() {
		return myframe;
	}

	/**
	 * first call to getInstance() must be getInstance(Unit u)
	 * 
	 * @param u
	 *            Unit in use
	 * @return
	 */

	public static MyJFrameSingleton getInstance(final Unit u) {
		if (myframe == null) {
			myframe = new MyJFrameSingletonImpl(u);
		}

		return myframe;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see view.gui_utility.MyJFrameSingleton#setPanel(javax.swing.JPanel)
	 */
	@Override
	public void setPanel(final JPanel panel) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				myFramePanel = panel;
				myframe.setContentPane(myFramePanel);
				myframe.setTitle(myFramePanel.getName());
				myframe.validate();
				myframe.repaint();
			}
		});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see view.gui_utility.MyJFrameSingleton#getContenentPane()
	 */
	@Override
	public JPanel getContenentPane() {
		return myFramePanel;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see view.gui_utility.MyJFrameSingleton#getUnit()
	 */
	@Override
	public UnitImpl getUnit() {
		return MyJFrameSingletonImpl.unit;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see view.gui_utility.MyJFrameSingleton#setNeedToSave()
	 */
	@Override
	public void setNeedToSave() {
		this.needSave = true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see view.gui_utility.MyJFrameSingleton#resetNeedToSava()
	 */
	@Override
	public void resetNeedToSava() {
		this.needSave = false;
	}

	private boolean isToBeSaved() {
		return this.needSave;
	}

}
