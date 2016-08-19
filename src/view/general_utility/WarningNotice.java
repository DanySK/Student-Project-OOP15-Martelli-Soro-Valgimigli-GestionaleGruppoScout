package view.general_utility;

import java.awt.BorderLayout;

import javax.swing.JDialog;

import view.gui_utility.MyJFrameSingletonImpl;
import view.gui_utility.MyJPanelImpl;

/** 
 * Semplicissima classe  utilizzata per mostrare a video un messaggio d'errore
 */

/**
 * @param stringa
 */
public class WarningNotice {
	private final static int FONTSIZE = 18;

	public WarningNotice(final String stringa) {
		final JDialog dial = new JDialog();
		final MyJPanelImpl pan = new MyJPanelImpl(new BorderLayout());
		final MyJPanelImpl flow = new MyJPanelImpl();
		pan.add(pan.createJTextArea(stringa, false, FONTSIZE), BorderLayout.CENTER);
		flow.add(flow.createButton("OK", e -> {
			dial.dispose();
		}));
		dial.add(pan);
		pan.add(flow, BorderLayout.SOUTH);
		dial.pack();
		dial.setLocationRelativeTo(MyJFrameSingletonImpl.getInstance());
		dial.setTitle("ERRORE");
		dial.setVisible(true);
	}

}
