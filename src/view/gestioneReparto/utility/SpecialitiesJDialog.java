package view.gestioneReparto.utility;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.Arrays;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import model.Member;
import model.exception.ObjectAlreadyContainedException;
import model.exception.ObjectNotContainedException;
import view.general_utility.WarningNotice;
import view.gui_utility.MyJFrameSingletonImpl;
import view.gui_utility.MyJPanelImpl;

public class SpecialitiesJDialog extends JDialog {

	private static final long serialVersionUID = -8715597288634279098L;
	private final static int FONTSIZE=19;
	private final static int FONTSIZEAREA=17;
	private final static int FONTSIZEBUTTON=15;
	public SpecialitiesJDialog(Member m, boolean editable){
		super();
		final MyJPanelImpl panel=new MyJPanelImpl(new BorderLayout());
		final MyJPanelImpl inPanel=new MyJPanelImpl(new GridLayout(2, 1));
		final JTextArea area=inPanel.createJTextArea("", editable,FONTSIZEAREA);
		final JScrollPane scroll=new JScrollPane(area);
		if(!editable){
			inPanel.add(inPanel.createJLabel("Specialità del Membro", FONTSIZE));
		}
		else{
			inPanel.add(inPanel.createJLabel("<html><U>Specialità del Membro</U><br>"
					+ "Aggiungere specialità, separandole dalle altre con il tasto \"INVIO\"</html>", FONTSIZE));
		}
		m.getSpecialities().stream().forEach(e->{
			area.append(e);
		});
		inPanel.add(scroll);
		panel.add(inPanel,BorderLayout.CENTER);
		final MyJPanelImpl panelBot=new MyJPanelImpl();
		if(!editable){
			panelBot.add(panelBot.createButton("Ok",FONTSIZEBUTTON, e->{
				this.dispose();
			}));
		}
		else{
			panelBot.setLayout(new FlowLayout(FlowLayout.RIGHT));
			panelBot.add(panelBot.createButton("Annulla", FONTSIZEBUTTON,e->{
				this.dispose();
			}));
			panelBot.add(panelBot.createButton("OK",FONTSIZEBUTTON,e->{
				m.getSpecialities().stream().forEach(j->{
					try {
						m.removeSpecialities(j);
					} catch (ObjectNotContainedException e1) {
						new WarningNotice(e1.getMessage());
					}
					
				});
				if(!area.getText().isEmpty()){
					Arrays.asList(area.getText().split(System.lineSeparator())).stream()
					.forEach(t->{
						try {
							m.addSpecialities(t);
						} catch (ObjectAlreadyContainedException e1) {
							new WarningNotice(e1.getMessage());
						}
					});;
				}
				this.dispose();
			}));
		}
		panel.add(panelBot,BorderLayout.SOUTH);
		this.add(panel);
		this.pack();
		this.setLocationRelativeTo(MyJFrameSingletonImpl.getInstance());
		this.setVisible(true);
	}
}
