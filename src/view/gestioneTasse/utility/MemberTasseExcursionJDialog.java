package view.gestioneTasse.utility;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.List;
import java.util.Map;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import model.Excursion;
import model.Member;
import model.exception.ObjectNotContainedException;
import view.general_utility.WarningNotice;
import view.gui_utility.MyJPanelImpl;

public class MemberTasseExcursionJDialog extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2645669972966333035L;
	private final int fontSizeLabel=19;
	
	
	public MemberTasseExcursionJDialog(Member me , Map<Member,List<Excursion>> map){
		MyJPanelImpl memPane=new MyJPanelImpl();
		
		JScrollPane scroll = new JScrollPane(memPane);
		memPane.setPreferredSize(scroll.getPreferredSize());
		MyJPanelImpl panel = new MyJPanelImpl(new BorderLayout());
		MyJPanelImpl panelCentral=new MyJPanelImpl(new GridLayout(2, 1));
		MyJPanelImpl panBot=new MyJPanelImpl();
		panel.add(panelCentral.createJLabel( "<html><U>Pagamento Membro</U></html>", fontSizeLabel),BorderLayout.NORTH);
		JTextArea area= new JTextArea("Questo membro non ha pagato le seguenti escursioni:");
		map.get(me).stream().forEach(e->{
			area.append(e.getName());
			memPane.add(memPane.createButton(e.getName(), k->{
				try {
					e.setPagante(me);
				} catch (ObjectNotContainedException e1) {
					new WarningNotice(e1.getMessage());
				}
			}));
		});
		if(area.getLineCount()>1){
			area.append("Cliccare su un pulsante per pagare un'escursione.");
		}
		
		panBot.add(panelCentral.createButton("Paga Tutte", u->{
			map.get(me).stream().forEach(e->{
				try {
					e.setPagante(me);
				} catch (ObjectNotContainedException e1) {
					new WarningNotice(e1.getMessage());
				}
			});
		}));
		panBot.add(panelCentral.createButton("Annulla", u->this.dispose()));
	}
}
