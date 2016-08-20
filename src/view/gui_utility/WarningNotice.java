package view.gui_utility;

import java.awt.BorderLayout;

import javax.swing.JDialog;

/** 
 * Simple class used to display Errors

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
