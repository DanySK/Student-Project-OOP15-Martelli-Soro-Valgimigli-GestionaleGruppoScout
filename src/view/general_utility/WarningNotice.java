package view.general_utility;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
/** @author Giovanni M. Martelli
 */


/** 
 * Semplicissima classe  utilizzata per mostrare a video un messaggio d'errore
 */

/**@param stringa*/
public class WarningNotice{
	
	String str;
	WarningNotice(String stringa){
		this.str = stringa;
		JOptionPane.showMessageDialog(new JFrame(), str);
	}

}
