package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import view.main_loader.LoaderImpl;

public class Main {
	/**
	 * This class is the App laucher and contains:
	 * 	-Main method
	 * 	-Static code that sets L&F of the entire application
	 * @author Giovanni Martelli
	 */
	/* static part that sets look&fell of the entire application */
	static{
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		    	if ("Nimbus".equals(info.getName())) {
		    		UIManager.setLookAndFeel(info.getClassName());
		            UIDefaults ui = UIManager.getLookAndFeelDefaults();
		            ui.put("Panel.background", new Color(42,218,77));
		            ui.put("OptionPane.background", new Color(42,218,77));
		            ui.put("Button.background", new Color(174,226,84));
		            ui.put("Button.font", new Font("Aria", Font.ITALIC, 30));
		            ui.put("Tree.drawHorizontalLines", true);
		            ui.put("Tree.drawVerticalLines", true);
		            ui.put("Tree.font", new Font("Aria", Font.ITALIC, 18));
		            ui.put("TextArea.background", new Color(42,218,77));
		            break;
		        }
		    }
		} catch (Exception e) {
		    e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		new LoaderImpl();
	}

}
