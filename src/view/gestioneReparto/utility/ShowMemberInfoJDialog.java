package view.gestioneReparto.utility;

import java.awt.BorderLayout;
import java.awt.GridLayout;
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
	MyJPanelImpl botInfo=new MyJPanelImpl(new BorderLayout());
	MyJPanelImpl panel=new MyJPanelImpl(new BorderLayout());
	MyJPanelImpl container=new MyJPanelImpl(new BorderLayout());
	Member mem;
	public ShowMemberInfoJDialog(Member mem){
		super();
		List<Pair<String, String>> list = (new InfoProjectImpl()).getMemberSpecificalInfo(mem);
		this.mem=mem;
		
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
		container.add(area,BorderLayout.CENTER);
		
		container.add(botInfo,BorderLayout.SOUTH);
		
		panel.add(container,BorderLayout.CENTER);
		bot.add(panel.createButton("Ok", g->{
			this.dispose();
		}));
		panel.add(bot, BorderLayout.SOUTH);
		this.add(panel);
		this.pack();
		this.setLocationRelativeTo(null);
	}
	public ShowMemberInfoJDialog(Member mem, boolean spec){
		this(mem);
		if(spec){
			SwingUtilities.invokeLater(new Runnable() {
				
				@Override
				public void run() {
					botInfo.add(botInfo.createJLabel("Specialità", 18),BorderLayout.WEST);
					botInfo.add(botInfo.createButton("vedi", e->{
						JDialog dial=new JDialog();
						MyJPanelImpl panel=new MyJPanelImpl(new BorderLayout());
						MyJPanelImpl container=new MyJPanelImpl(new GridLayout(2,1));
						JTextArea areaSpec=new JTextArea();
						mem.getSpecialities().stream().forEach(t->{
							areaSpec.append(t);
							
						});
					
						container.add(container.createJLabel("Specialità Membero",18));
						container.add(areaSpec);
						panel.add(container);
						dial.add(panel);
						dial.pack();
						dial.setLocationRelativeTo(MyJFrameSingletonImpl.getInstance());
						dial.setVisible(true);
						
					}),BorderLayout.EAST);
					container.validate();
					container.repaint();
				}
			});
		}
		
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
