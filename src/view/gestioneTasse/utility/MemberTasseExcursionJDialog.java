package view.gestioneTasse.utility;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import model.Excursion;
import model.Member;
import model.exception.ObjectNotContainedException;
import view.general_utility.WarningNotice;
import view.gui_utility.EditableMemberPanelImpl;
import view.gui_utility.MyJFrameSingletonImpl;
import view.gui_utility.MyJPanelImpl;

public class MemberTasseExcursionJDialog extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2645669972966333035L;
	private final int fontSizeLabel=19;
	private List<Excursion> list;
	private Member me;
	private JTextArea area;
	private MyJPanelImpl memPane;
	private JScrollPane scroll;
	public MemberTasseExcursionJDialog(Member me, EditableMemberPanelImpl<Member> parent ){
		this.me=me;
	
		this.list=new ArrayList<>();	
		memPane=new MyJPanelImpl();
		
		scroll = new JScrollPane(memPane);
		memPane.setPreferredSize(scroll.getPreferredSize());
		MyJPanelImpl panel = new MyJPanelImpl(new BorderLayout());
		MyJPanelImpl panelCentral=new MyJPanelImpl(new GridLayout(2, 1));
		
		MyJPanelImpl panBot=new MyJPanelImpl();
		panel.add(panelCentral.createJLabel( "<html><U>Pagamenti Escursioni "+me.getName()+" "+me.getSurname()+
				"</U></html>", fontSizeLabel),BorderLayout.NORTH);
		area=panel.createJTextArea("Questo membro non ha pagato le seguenti escursioni;"
				+ "\ncliccare su un pulsante per pagare l'escursione", false, fontSizeLabel-2);
		area.setEditable(false);
		updateEscursion();
		
	
		
		panBot.add(panelCentral.createButton("Paga Tutte", u->{
			list.stream().forEach(e->{
				try {
					e.setPaied(me);
				} catch (ObjectNotContainedException e1) {
					new WarningNotice(e1.getMessage());
				}
			});
		}));
		panelCentral.add(area);
		panelCentral.add(memPane);
		panBot.add(panelCentral.createButton("OK", u->{
			parent.updateMember();
			this.dispose();
			
		}));
		panel.add(panelCentral,BorderLayout.CENTER);
		panel.add(panBot,BorderLayout.SOUTH);
		this.add(panel);
		this.pack();
		this.setLocationRelativeTo(MyJFrameSingletonImpl.getInstance());
	}
	private void updateEscursion(){
		
		list.clear();
		MyJFrameSingletonImpl.getInstance().getUnit().getContainers().getExcursion().stream()
		.forEach(e->{
			if(e.getNotPaied().contains(me)){list.add(e);};
		});
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				memPane.removeAll();
				list.stream().forEach(e->{
					memPane.add(memPane.createButton(e.getName(), k->{
						try {
							e.setPaied(me);
							new WarningNotice("Pagamento registrato");
							
						} catch (ObjectNotContainedException e1) {
							new WarningNotice(e1.getMessage());
						}
						updateEscursion();
						
						
						
					}));
				});
				memPane.validate();
				memPane.repaint();
				scroll.revalidate();
				scroll.repaint();
				
			}
		});
	}
}
