package view.gestioneEventi.utility;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.ScrollPane;
import java.util.Arrays;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import model.EventiDiZona;
import model.Excursion;
import model.Gemellaggi;
import model.GemellaggiImpl;
import model.exception.ObjectAlreadyContainedException;
import view.general_utility.WarningNotice;
import view.gui_utility.MyJFrameSingletonImpl;
import view.gui_utility.MyJPanelImpl;

public class OtherUnitJDialog extends JDialog {

	private static final long serialVersionUID = 1710237645233014036L;
	private final Excursion exc;
	private final int fontSize=15;
	public OtherUnitJDialog(Excursion exc, boolean editable){
		this.exc=exc;
		final MyJPanelImpl panel=new MyJPanelImpl(new BorderLayout());
		final MyJPanelImpl inContainer=new MyJPanelImpl(new GridLayout(2, 1));
		final MyJPanelImpl bot= new MyJPanelImpl(new FlowLayout(FlowLayout.RIGHT));
		final JTextArea area=new JTextArea();
		area.setEditable(editable);
		
		final JScrollPane scroll=new JScrollPane(area);
		if(exc instanceof Gemellaggi){
			((Gemellaggi)exc).getOtherUnits().stream().forEach(e->{
				area.append(e);
			});
		}
		else{
			((EventiDiZona)exc).getOtherUnits().stream().forEach(e->{
				area.append(e);
			});
		}
		if(editable)inContainer.add(inContainer.createJLabel("<html>Aggiungi altri reparti,"
				+ "<br>separandoli con il tasto \"INVIO\"", fontSize));
		else inContainer.add(inContainer.createJLabel("Reparti che parteciperanno all'escursione", fontSize));
		inContainer.add(scroll);
		if(editable){
			bot.add(bot.createButton("Annulla", fontSize, e->{
				dispose();
			}));
		}
		bot.add(bot.createButton("OK",fontSize, e->{
			if(editable){
				if(exc instanceof Gemellaggi){
					((Gemellaggi)exc).clearUnitsList();
					Arrays.asList(area.getText().split(System.lineSeparator())).stream()
					.forEach(u->{
						try {
							((Gemellaggi)exc).addOtherUnit(u);
						} catch (ObjectAlreadyContainedException e1) {
							new WarningNotice(e1.getMessage());
						}
					});
					
				}
				else{
					((EventiDiZona)exc).clearUnitsList();
					Arrays.asList(area.getText().split(System.lineSeparator())).stream()
					.forEach(u->{
						try {
							((EventiDiZona)exc).addOtherUnit(u);
						} catch (ObjectAlreadyContainedException e1) {
							new WarningNotice(e1.getMessage());
						}
					});
				}
				dispose();
			}
			else dispose();
		}));
		
		panel.add(inContainer,BorderLayout.CENTER);
		panel.add(bot,BorderLayout.SOUTH);
		this.add(panel);
		this.pack();
		this.setLocationRelativeTo(MyJFrameSingletonImpl.getInstance());
		this.setVisible(true);
		
		
	}
	
}
