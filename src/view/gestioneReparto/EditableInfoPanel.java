package view.gestioneReparto;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import control.InfoProjectImpl;
import control.myUtil.Pair;
import view.gui_utility.MyJFrameSingletonImpl;
import view.gui_utility.MyJPanelImpl;

public class EditableInfoPanel extends MyJPanelImpl {
	
	private static final long serialVersionUID = 3525477272751960285L;

	static enum ChefLabel{
		Nome("Nome: ", ""),
		Sesso("Sesso: ", ""),
		Capo("Capo: ", "" ),
		Vice("Vice: ", ""),
		Trice("Trice: ", ""),
		Membri("Numero di membri: ", "");
		private final String name;
		private String value;
		
		public String getName(){return this.name;}
		public String getValue(){return this.value;}
		public void setValue(String value){this.value=value;}
		private ChefLabel(String name, String value){
			this.name=name;
			this.value=value;
		}
	}
	private final String squadName;
	private final boolean editable;
	private final int fontSizeLabel;
	private final int fontSizeButton;
	public EditableInfoPanel(String squad,boolean isManager, int fontSizeLabel, int fontSizeButton){
		super(new BorderLayout());
		this.editable=isManager;
		this.fontSizeButton=fontSizeButton;
		this.fontSizeLabel=fontSizeLabel;
		MyJPanelImpl panelSx = new MyJPanelImpl(new GridLayout(0,2));
		MyJPanelImpl panelDx=new MyJPanelImpl(new GridLayout(0, 1));
		this.squadName=squad;
		/*parte in cui setto tutto*/
		List<Pair<String,String>> list= (new InfoProjectImpl())
				.getSquadronSpecificInfo(squadName, MyJFrameSingletonImpl.getInstance().getUnit().getContainers());
		Arrays.asList(EditableInfoPanel.ChefLabel.values()).stream().forEach(i->{
			i.setValue(list.stream().filter(e->e.getX().equals(i.getName())).findFirst()
					.orElse(new Pair<>(i.getName(), "non settato")).getY());
			if(isUsefull(i.getName())){		
				panelSx.add(createJLabel(i.getName(), i.getName(), fontSizeLabel));
				panelSx.add(createJLabel(i.getName()+"Val", i.getValue(), fontSizeLabel));
				if(i.getName().equals("Capo: ") || i.getName().equals("Vice: ") ||i.getName().equals("Trice: ")){
					panelDx.add(instanceJButton(i));
				}
				else{
					panelDx.add(createJLabel("null", "",fontSizeLabel));
				}
			}
		});
		this.add(panelSx, BorderLayout.CENTER);
		this.add(panelDx, BorderLayout.EAST);
		
		this.repaint();
		this.validate();
		
	}
	
	private boolean isUsefull(String param){
		if(!editable){
			if(param.equals("Nome: ")){return false;}
			return true;
		}
		else{
			if(param.equals("Nome: ") || param.equals("Numero di membri: ") || param.equals("Sesso: ")){return false;}
		}
		return true;
	}
	
	private JButton instanceJButton(ChefLabel i){
		if(!editable){
			return createButton("info",this.getBackground(), new Font("Aria", Font.ITALIC,fontSizeButton),e->{
				JDialog dial = new JDialog();
				dial.setLocationRelativeTo(MyJFrameSingletonImpl.getInstance());
				JPanel panel=new JPanel();
				if(i.getValue().equals("non settato")){panel.add(createJTextArea("nullArea", "Info non disponibile;"+System.lineSeparator()
					+ "in quanto tale carica non è stata assegnata", false, fontSizeLabel));}
				else{
					List<Pair<String,String>> areaText= (new InfoProjectImpl()).getMemberSpecificalInfo(MyJFrameSingletonImpl.getInstance()
						.getUnit().getContainers().findMember(i.getValue()).stream().findFirst().get());
					String area="";
					areaText.stream().forEach(f->{area.concat(f.getX()+":   "+f.getY()+"\n");});
					panel.add(createJTextArea("AreaText", area, false, 18),BorderLayout.CENTER);
				}
				panel.getComponent(0).setBackground(panel.getBackground());
				dial.add(panel);
				dial.pack();
				dial.setVisible(true);
			
			});
		}
		else return new JButton("Edit");
		
	}
}
