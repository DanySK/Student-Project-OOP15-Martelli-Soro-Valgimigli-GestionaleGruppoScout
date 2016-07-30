package view.gestioneReparto;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JViewport;

import view.gui_utility.MyJFrameSingletonImpl;
import view.gui_utility.MyJPanelImpl;

public class SquadrigliaOverviewImpl extends MyJPanelImpl {
	
	private static final long serialVersionUID = -6749522066747263034L;
	
	private final String squadName;
	private final int fontSize=19;
	private final int fontSizeButton=10;
	
	public SquadrigliaOverviewImpl(String param) {
		/*
		 * Instanzio i vari oggetti e sopratutto instanzio tutti i pannelli che mi servono
		 */
		super(new BorderLayout());
		MyJFrameSingletonImpl.getInstance().getUnit().getContainers().findSquadron(param);
		squadName=param;
		MyJPanelImpl panelCenter=new MyJPanelImpl(new GridLayout(2, 1));
		MyJPanelImpl panelMember=new MyJPanelImpl(new GridLayout(0,4));
		JScrollPane panelScroll=new JScrollPane(panelMember);
		MyJPanelImpl panelSxDx=new EditableInfoPanel(squadName, false, fontSize, fontSizeButton);
	
				
		/*
		 * aggiungo l'intestazione e tutti i pannelli nell'ordine in cui mi servono
		 */
		
		this.add(createJLabel("north", "<html><U>Panoramica di "+squadName+"</U></html>", 22),BorderLayout.NORTH);
		this.add(panelCenter, BorderLayout.CENTER);
		panelCenter.add(panelSxDx);
		panelCenter.add(panelScroll);
		panelScroll.setBorder(BorderFactory.createMatteBorder(2, 0,0 , 0, new Color(0,0,0)));

		if(panelMember.getComponents().length==0){
			panelMember.add(createJLabel("noMember", "<html>Al momento non ci sono membri nella squadriglia<br>"
					+ "Visita la sezione di gestione della squadriglia per aggiungerne</html>", fontSize-7));
			
		}
		/*
		 * Aggiungo l'header a panelMember, 
		 */
		
		//Va modificato per aggiungere i bottoni delle info su cancelleria e cassa
		JViewport header = new JViewport();
		header.add(createJLabel("header", "Membri Squadriglia", 18));
		((JLabel)header.getView()).setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(0,0,0)));
		panelScroll.setColumnHeader(header);
		panelScroll.getColumnHeader().setBackground(this.getBackground());
		
	}	
	public String toString(){
		return this.squadName+"__Overview";
	}
}
