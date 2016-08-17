package view.gestioneReparto.utility;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextArea;
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
	MyJPanelImpl bot= new MyJPanelImpl();
	MyJPanelImpl panel=new MyJPanelImpl(new BorderLayout());
	public ShowMemberInfoJDialog(Member mem){
		super();
		List<Pair<String, String>> list = (new InfoProjectImpl()).getMemberSpecificalInfo(mem);
		
		
		JTextArea area=panel.createJTextArea( "", false, 18);
		list.forEach(t->{
			area.append(t.getX()+": "+t.getY()+System.lineSeparator());
		});
		try {
			area.append("Ruolo: "+MyJFrameSingletonImpl.getInstance().getUnit()
					.getReparto().getSquadronOfMember(mem).getMembri().get(mem));
		} catch (ObjectNotContainedException e) {
			new WarningNotice(e.getMessage());
		}
		panel.add(area,BorderLayout.CENTER);
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
				System.out.println("CCCCCCCCCCCCCCCAAAAAAAAAAAAAA");
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
