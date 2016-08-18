package view.online;

import java.awt.BorderLayout;
import java.util.Arrays;

import javax.swing.JComboBox;
import javax.swing.SwingUtilities;

import control.myUtil.MyOptional;
import extra.sito.ExcursionOnline;
import extra.sito.Regioni;
import view.gui_utility.EditableMemberPanelImpl;
import view.gui_utility.EditableMemberPanelImpl.Type;
import view.gui_utility.MyJPanelImpl;

public class EventiBuonaCaccia extends MyJPanelImpl {

	/**
	 * 
	 */
	private final static int FONTSIZE=19;
	private static final long serialVersionUID = -4317317394245136248L;
	private EditableMemberPanelImpl<ExcursionOnline> paneEx;
	public EventiBuonaCaccia(){
		super(new BorderLayout());
		final MyJPanelImpl panelIn=new MyJPanelImpl(new BorderLayout());
		final MyJPanelImpl panelLab=new MyJPanelImpl();
		panelLab.add(createJLabel("Scegli regione: ", FONTSIZE));
		final JComboBox<Regioni> chooser= new JComboBox<>(); 
		panelLab.add(chooser);
		panelIn.add(panelLab,BorderLayout.NORTH);
		
		chooser.addItem(Regioni.NAZIONALE);
		Arrays.asList(Regioni.values()).stream().forEach(e->{
			if(!e.equals(Regioni.NAZIONALE)){chooser.addItem(e);}
		});
		paneEx=new EditableMemberPanelImpl<>(Type.EXCONLINE, MyOptional.of(Regioni.NAZIONALE.toString()));
		panelIn.add(paneEx,BorderLayout.CENTER);
		chooser.addItemListener(e->{
			SwingUtilities.invokeLater(new Runnable() {
				
				@Override
				public void run() {
					paneEx.forceUpdate(((Regioni)chooser.getSelectedItem()).toString());
					validate();
					repaint();
				}
			});
			
		});
		
		this.add(panelIn,BorderLayout.CENTER);
		
		
	}
	public String toString(){
		return "BuonaCaccia";
	}
	
	
}
