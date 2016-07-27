package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import control.MasterProjectImpl;
import view.general_utility.WarningNotice;
import view.gui_utility.LoaderManager;
import view.gui_utility.MyJPanelImpl;


public class LoaderImpl extends MyJPanelImpl {
	/* static part that sets look&fell of the entire application */
	static{
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		    	if ("Nimbus".equals(info.getName())) {
		    		UIManager.setLookAndFeel(info.getClassName());
		            UIDefaults ui = UIManager.getLookAndFeelDefaults();
		            ui.put("Panel.background", new Color(42,218,77));
		            ui.put("Button.background", new Color(174,226,84));
		            ui.put("Button.font", new Font("Aria", Font.ITALIC, 30));
		            ui.put("PopupMenu.background", new Color(42,218,77));
		            ui.put("Menu.background",new Color(42,218,77));
		            ui.put("Menu.opaque", false);
		            ui.put("MenuItem.background", new Color(42,218,65));
		            ui.put("MenuItem.opaque", true);
		            ui.put("PopupMenu.contentMargins",new Insets(1,1,1,1));
		            ui.put("Tree.drawHorizontalLines", true);
		            ui.put("Tree.drawVerticalLines", true);
		            ui.put("Tree.font", new Font("Aria", Font.ITALIC, 18));
		            break;
		        }
		    }
		} catch (Exception e) {
		    e.printStackTrace();
		}
	}
	private static final long serialVersionUID = 2929901797522523355L;
	private MasterProjectImpl project;
	private final JFrame frame=new JFrame();
	private final JButton load;
	private final JButton create;
	
	
	public LoaderImpl(){
		super("Loader", new JPanel());
		
		try{
			this.project=new MasterProjectImpl();
		}catch(Exception e){
			@SuppressWarnings("unused")
			WarningNotice warning=new WarningNotice(e.getMessage());
		}
		
		load=new JButton("Load");
		create=new JButton("Create New");
		load.setToolTipText("Carica un reparto creato in precedenza");
		LoaderManager loader=new LoaderManager(project);
		load.addActionListener(e->{loader. new LoadUnit(); this.frame.dispose();});
		create.setToolTipText("Crea un nuovo reparto e aggiungilo alla lista dei reparti gestiti");
		create.addActionListener(e->{loader. new CreateUnit(); this.frame.dispose();});
		this.add(load);
		this.add(create);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(this);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
	}
}
