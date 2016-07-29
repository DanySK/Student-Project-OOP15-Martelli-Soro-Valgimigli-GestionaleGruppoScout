package view.gestioneReparto;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.time.LocalDate;
import java.time.Year;
import java.util.Arrays;
import java.util.Optional;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JViewport;
import javax.swing.SwingUtilities;

import control.exception.MoreLeadersNotPermitException;
import model.Member;
import model.MemberImpl;
import model.Squadron;
import view.gui_utility.MyJFrameSingletonImpl;
import view.gui_utility.MyJPanelImpl;

public class SquadrigliaOverviewImpl extends MyJPanelImpl {
	
	/**
	 * 
	 */
	private enum Ruoli{
		Capo,
		Vice,
		Trice;
	}
	
	private static final long serialVersionUID = -6749522066747263034L;
	
	private final Squadron squad;
	private final int fontSize=15;
	private final Font font=new Font("Aria", Font.ITALIC, 10);
	
	public SquadrigliaOverviewImpl(Squadron param) {
		/*
		 * Instanzio i vari oggetti e sopratutto instanzio tutti i pannelli che mi servono
		 */
		super(new BorderLayout());
		
		squad=param;
		MyJPanelImpl panelCenter=new MyJPanelImpl(new GridLayout(2, 1));
		MyJPanelImpl panelMember=new MyJPanelImpl(new GridLayout(0,4));
		JScrollPane panelScroll=new JScrollPane(panelMember);
		MyJPanelImpl panelSxDx=new MyJPanelImpl(new BorderLayout());
		MyJPanelImpl panelSx = new MyJPanelImpl(new GridLayout(0,2));
		MyJPanelImpl panelDx=new MyJPanelImpl(new GridLayout(0, 1));
		
		/*
		 * aggiungo l'intestazione e tutti i pannelli nell'ordine in cui mi servono
		 */
		this.add(createJLabel("north", "<html><U>Panoramica di "+squad.getNome()+"</U></html>", 22),BorderLayout.NORTH);
		
		
		this.add(panelCenter, BorderLayout.CENTER);
		panelCenter.add(panelSxDx);
		panelCenter.add(panelScroll);
		panelSxDx.add(panelSx, BorderLayout.CENTER);
		panelSxDx.add(panelDx, BorderLayout.EAST);
		panelScroll.setBorder(BorderFactory.createMatteBorder(2, 0,0 , 0, new Color(0,0,0)));
		
		
		
		panelSx.add(createJLabel("capo", "Capo: ", fontSize));
		panelSx.add(createJLabel("capo", "Capo: ", fontSize));
		//panelSx.add(createJLabel("capoName",Optional.of(squad.getCapo().getName()+" "+squad.getCapo().getSurname()), fontSize));
		panelSx.add(createJLabel("vice", "Vice: ",fontSize));
		panelSx.add(createJLabel("capo", "Capo: ", fontSize));
		//panelSx.add(createJLabel("viceName", Optional.of(squad.getVice().getName()+" "+squad.getVice().getSurname()), fontSize));
		panelSx.add(createJLabel("trice","Trice: ", fontSize));
		panelSx.add(createJLabel("capo", "Capo: ", fontSize));
		//panelSx.add(createJLabel("triceName",  Optional.of(squad.getTrice().getName()+" "+squad.getTrice().getSurname()), fontSize));
		for(Component i : Arrays.asList(panelSx.getComponents())){
			if(i instanceof JLabel){((JLabel) i).setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(0,0,0)));}
		}
		
		MemberImpl mem=new MemberImpl("Ciao", "bubu", Year.of(1995).atMonth(12).atDay(11), false, 0);
		for(int i =0; i < 3;i++){
			panelDx.add(createButton("info", null, font, e->{
				JDialog dial=new JDialog();
				dial.setLocationRelativeTo(MyJFrameSingletonImpl.getInstance());
				System.out.println(((JButton)e.getSource()).getName().substring(0,1));
				dial.add(chooseInfo(((JButton)e.getSource()).getName().substring(0,1)));
				dial.pack();
				dial.setVisible(true);
			}));
			panelDx.getComponent("info").setName(i+"info");
			
		}/*
		for(Member i: squad.getMembri().keySet()){
			panelMember.add(createButton(i.getName(), e->{
				JDialog dial=new JDialog();
				dial.setLocationRelativeTo(MyJFrameSingletonImpl.getInstance());
				MyJPanelImpl panel=new MyJPanelImpl();
				panel.add(createJTextArea("", MyJFrameSingletonImpl.getInstance().getUnit().getContainers()
						.findMember(((JButton)e.getSource()).getName()).toString()
						, false, fontSize));
			}));
		}*/
		JViewport header = new JViewport();
		header.add(createJLabel("header", "Membri Squadriglia", 18));
		((JLabel)header.getView()).setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(0,0,0)));
		panelScroll.setColumnHeader(header);
		panelScroll.getColumnHeader().setBackground(this.getBackground());
		panelMember.add(createButton("<html>Lorenzo<br>Valgimigli</html>",e->{}));
		panelMember.getComponent(0).setFont(font);
		for(int i =0; i < 35;i++){
			String k="<html>"+Integer.toString(i)+"<br>CACCA</html>";
			panelMember.add(createButton(k, e->{}));
			((JButton)panelMember.getComponent(k)).setFont(font);
		}
	}
	
	
	
	private JPanel chooseInfo(String num){
		MyJPanelImpl ret=new MyJPanelImpl(new BorderLayout());
		JButton e= ret.createButton("OK", f->{((JDialog)SwingUtilities.getWindowAncestor(((JPanel)((JButton)f.getSource()).getParent()))).dispose();});
		String i=num;
		if(i.equals("0")){
			ret.add(createJTextArea("", "VediCapo", false, fontSize),BorderLayout.CENTER);
		}
		else if(i.equals("1")){
			ret.add(createJTextArea("", "VediVice", false, fontSize),BorderLayout.CENTER);
		}
		else{
			ret.add(createJTextArea("", "VediTrice", false, fontSize),BorderLayout.CENTER);
		}
		ret.getComponent(0).setBackground(ret.getBackground());
		ret.add(e, BorderLayout.SOUTH);
		return ret;
	}
	
	
	
	public String toString(){
		return this.squad.getNome()+"__Overview";
	}
	
	private void evitaExceptio(){
		
	}
}
