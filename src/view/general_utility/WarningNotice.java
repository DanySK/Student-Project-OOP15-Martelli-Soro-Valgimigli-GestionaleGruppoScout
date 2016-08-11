package view.general_utility;
import java.awt.BorderLayout;

import javax.swing.JDialog;

import view.gui_utility.MyJFrameSingletonImpl;
import view.gui_utility.MyJPanelImpl;


/** 
 * Semplicissima classe  utilizzata per mostrare a video un messaggio d'errore
 */

/**@param stringa*/
public class WarningNotice{
	
	private final String str;
	public WarningNotice(String stringa){
		this.str = stringa;
		JDialog dial =new JDialog();
		MyJPanelImpl pan=new MyJPanelImpl(new BorderLayout());
		MyJPanelImpl flow=new MyJPanelImpl();
		pan.add(pan.createJTextArea(str, false, 18),BorderLayout.CENTER);
		flow.add(flow.createButton("OK", e->{
			dial.dispose();
		}));
		dial.add(pan);
		pan.add(flow,BorderLayout.SOUTH);
		dial.pack();
		dial.setLocationRelativeTo(MyJFrameSingletonImpl.getInstance());
		dial.setVisible(true);
	}

}
