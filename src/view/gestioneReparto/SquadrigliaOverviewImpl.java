package view.gestioneReparto;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;

import java.time.LocalDate;
import java.time.Year;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JViewport;
import javax.swing.SwingUtilities;

import control.InfoProjectImpl;
import control.UnitImpl;
import control.projectFactoryimpl;
import control.exception.MoreLeadersNotPermitException;
import control.myUtil.Pair;
import model.MemberImpl;
import model.Squadron;
import view.gui_utility.MyJFrameSingletonImpl;
import view.gui_utility.MyJPanelImpl;

public class SquadrigliaOverviewImpl extends MyJPanelImpl {
	
	private static final long serialVersionUID = -6749522066747263034L;
	
	private final String squadName;
	private final Squadron squad; 
	private final int fontSize=20;
	private final Font font=new Font("Aria", Font.ITALIC, 10);
	private final UnitImpl unit=MyJFrameSingletonImpl.getInstance().getUnit();
	public SquadrigliaOverviewImpl(String param) {
		/*
		 * Instanzio i vari oggetti e sopratutto instanzio tutti i pannelli che mi servono
		 */
		super(new BorderLayout());
		squad = MyJFrameSingletonImpl.getInstance().getUnit().getContainers().findSquadron(param);
		squadName=param;
		MyJPanelImpl panelCenter=new MyJPanelImpl(new GridLayout(2, 1));
		MyJPanelImpl panelMember=new MyJPanelImpl(new GridLayout(0,4));
		JScrollPane panelScroll=new JScrollPane(panelMember);
		MyJPanelImpl panelSxDx=new MyJPanelImpl(new BorderLayout());
		MyJPanelImpl panelSx = new MyJPanelImpl(new GridLayout(0,2));
		MyJPanelImpl panelDx=new MyJPanelImpl(new GridLayout(0, 1));
		
		/*
		 * aggiungo l'intestazione e tutti i pannelli nell'ordine in cui mi servono
		 */
		this.add(createJLabel("north", "<html><U>Panoramica di "+squadName+"</U></html>", 22),BorderLayout.NORTH);
		
		try {
			squad.setCapoSq(projectFactoryimpl.getSimpleMember("Lorenzo", "Valgimigli", LocalDate.now(), false));
		} catch (MoreLeadersNotPermitException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		this.add(panelCenter, BorderLayout.CENTER);
		panelCenter.add(panelSxDx);
		panelCenter.add(panelScroll);
		panelSxDx.add(panelSx, BorderLayout.CENTER);
		panelSxDx.add(panelDx, BorderLayout.EAST);
		panelScroll.setBorder(BorderFactory.createMatteBorder(2, 0,0 , 0, new Color(0,0,0)));
		
		
		Iterator<Pair<String,String>> it=(new InfoProjectImpl()).getSquadronSpecificInfo(squadName, unit.getContainers()).iterator();
		
		while(it.hasNext()){
			Pair<String, String> tmp=it.next();
			panelSx.add(createJLabel(tmp.getX(), tmp.getX(), fontSize));
			panelSx.add(createJLabel(tmp.getX()+"Name", tmp.getY(), fontSize));
			if(tmp.getX().equals("Capo: ") || tmp.getX().equals("Vice: ") ||tmp.getX().equals("Trice: ")){
				
				panelDx.add(createButton("info",this.getBackground(), font,e->{
					Pair<String,String> tempArea=tmp;
					JDialog dial = new JDialog();
					dial.setLocationRelativeTo(MyJFrameSingletonImpl.getInstance());
					JPanel panel=new JPanel();
					List<Pair<String,String>> areaText=tempArea.getX().equals("Capo: ")? (new InfoProjectImpl()).getMemberSpecificalInfo(squad.getCapo()):
						tempArea.getX().equals("Vice: ")?(new InfoProjectImpl()).getMemberSpecificalInfo(squad.getVice()):
							(new InfoProjectImpl()).getMemberSpecificalInfo(squad.getVice());
					String area="";
					areaText.stream().forEach(f->{area.concat(f.getX()+":   "+f.getY()+"\n");});
					panel.add(createJTextArea("AreaText", area, false, 18),BorderLayout.CENTER);
					dial.add(panel);
					dial.pack();
					dial.setVisible(true);
					
				}));
			}
			else{
				panelDx.add(createJLabel("null", "", fontSize));
			}
			System.out.println(tmp.getX()+"  "+tmp.getY());
		}
		for(Component i : Arrays.asList(panelSx.getComponents())){
			if(i instanceof JLabel){((JLabel) i).setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(0,0,0)));}
		}
		
	
		
		
		
		
	/*		
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
			
		}*/
		/*for(MemberImpl i: squad.getMembri().keySet()){
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
		
		for(int i =0; i < 35;i++){
			String k="<html>"+Integer.toString(i)+"<br>Membro</html>";
			panelMember.add(createButton(k, e->{}));
			((JButton)panelMember.getComponent(k)).setFont(font);
		}
	}	
	public String toString(){
		return this.squadName+"__Overview";
	}
}
