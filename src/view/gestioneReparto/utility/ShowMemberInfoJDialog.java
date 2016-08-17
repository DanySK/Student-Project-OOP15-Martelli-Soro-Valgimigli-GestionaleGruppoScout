package view.gestioneReparto.utility;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.SwingUtilities;

import control.InfoProjectImpl;
import control.myUtil.Pair;
import model.Member;
import model.exception.ObjectNotContainedException;
import view.general_utility.WarningNotice;
import view.gui_utility.MyJFrameSingletonImpl;
import view.gui_utility.MyJPanelImpl;

public class ShowMemberInfoJDialog extends JDialog {

	private static final long serialVersionUID = 6783431258852717871L;
	private final MyJPanelImpl bot= new MyJPanelImpl();
	private final MyJPanelImpl panel=new MyJPanelImpl(new BorderLayout());
	private final MyJPanelImpl panelIn=new MyJPanelImpl(new GridLayout(0, 2));
	private final static int FONTSIZE=18;
	public ShowMemberInfoJDialog(Member mem){
		super();
		final List<Pair<String, String>> list = (new InfoProjectImpl()).getMemberSpecificalInfo(mem);
		list.forEach(t->{
			panelIn.add(panelIn.createJLabel(t.getX(), FONTSIZE));
			panelIn.add(panelIn.createJLabel(t.getY(), FONTSIZE));
		});
		try {
			panelIn.add(panelIn.createJLabel("RUOLO", FONTSIZE));
			panelIn.add(panelIn.createJLabel(MyJFrameSingletonImpl.getInstance().getUnit()
					.getReparto().getSquadronOfMember(mem).getMembri().get(mem).toString(), FONTSIZE));
		} catch (ObjectNotContainedException e) {
			new WarningNotice(e.getMessage());
		}
		panelIn.add(panelIn.createJLabel("Specialità", FONTSIZE));
		panelIn.add(panelIn.createButton("Vedi",FONTSIZE, e->{
			new SpecialitiesJDialog(mem, false);
		}));
		panel.add(panelIn,BorderLayout.CENTER);
		bot.add(panel.createButton("Ok", g->{
			this.dispose();
		}));
		panel.add(bot, BorderLayout.SOUTH);
		this.add(panel);
		this.pack();
		this.setLocationRelativeTo(null);
	}
	public void addButtonToBot(String title,ActionListener e){
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				
				bot.add(bot.createButton(title, e));
				bot.validate();
				bot.repaint();
				panel.repaint();
				panel.validate();
				repaint();
				validate();
				pack();
			}
		});
		
	}
}
