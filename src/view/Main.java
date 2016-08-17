package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import view.main_loader.LoaderImpl;

public class Main {
	/**
	 * This class is the App laucher and contains: -Main method -Static code
	 * that sets L&F of the entire application
	 * 
	 * @author Giovanni Martelli
	 */
	/* static part that sets look&fell of the entire application */
	static {
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					UIDefaults ui = UIManager.getLookAndFeelDefaults();
					ui.put("Panel.background",MyColor.BACKGROUND.get());
					ui.put("OptionPane.background", MyColor.BACKGROUND.get());
					ui.put("Button.background", MyColor.JBUTTON.get());
					ui.put("Button.font", new Font("Aria", Font.ITALIC, 30));
					ui.put("Tree.drawHorizontalLines", true);
					ui.put("Tree.drawVerticalLines", true);
					ui.put("Tree.font", new Font("Aria", Font.ITALIC, 18));
					ui.put("TextArea.background", MyColor.BACKGROUND.get());
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static enum MyColor{
		BACKGROUND(48,216,29),
		JBUTTON(255,255,51),
		BLACK(0,0,0);
		
		private final int a;
		private final int b;
		private final int c;
		public Color get(){
			return new Color(a,b,c);
		}
		private MyColor(int a, int b, int c){
			this.a=a;
			this.b=b;
			this.c=c;
		}
	}

	public static void main(String[] args) {
		new LoaderImpl();
	}

}
