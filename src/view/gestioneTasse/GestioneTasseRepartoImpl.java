package view.gestioneTasse;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.time.LocalDate;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import control.Unit;
import control.myUtil.myOptional;
import model.Member;
import view.gui_utility.EditableMemberPanelImpl;
import view.gui_utility.EditableMemberPanelImpl.Type;
import view.gui_utility.MyJFrameSingletonImpl;
import view.gui_utility.MyJPanelImpl;

public class GestioneTasseRepartoImpl {

	private final String repName;
	private final Unit unit;
	
	public GestioneTasseRepartoImpl(String name){
		this.repName=name;
		this.unit=MyJFrameSingletonImpl.getInstance().getUnit();
		
	}
	
	public class GestioneTasseRepartoImplPane extends MyJPanelImpl{
		private static final long serialVersionUID = -3959125437548824576L;
		private final int fontSizeLabel=19;
		private final MyJPanelImpl infoLeft;
		private final MyJPanelImpl infoRight;
		private final MyJPanelImpl center;
		private final MyJPanelImpl infoLeContainer;
		@SuppressWarnings("unchecked")
		public GestioneTasseRepartoImplPane(){
			super(new BorderLayout());
			this.add(createJLabel("<html><U>Gestione tasse "+repName+"</U></html>", fontSizeLabel), BorderLayout.NORTH);
			this.center=new MyJPanelImpl(new BorderLayout());
			this.infoLeft = new MyJPanelImpl(new GridLayout(0, 2));
			this.infoRight = new MyJPanelImpl(new GridLayout(0,1));
			this.infoLeContainer= new MyJPanelImpl(new BorderLayout());
			infoLeContainer.add(infoLeft, BorderLayout.CENTER);
			infoLeContainer.add(infoRight, BorderLayout.EAST);
			infoLeft.add(createJLabel( "Limite pagamento retta:", fontSizeLabel-3));
			infoLeft.add(createJLabel( MyJFrameSingletonImpl.getInstance().getUnit().getReparto().getDateToPay().toString(), fontSizeLabel-3));
			infoRight.add(createButton("Cambia", e->{
				JDialog dial = new JDialog();
				MyJPanelImpl panel=new MyJPanelImpl(new BorderLayout());
				panel.add(createJLabel( "<html><U>Nuova data", fontSizeLabel));
				MyJPanelImpl panelInternal = new MyJPanelImpl();
				JTextField anno=new JTextField();
				JTextField mese=new JTextField();
				JTextField giorno=new JTextField();
				MyJPanelImpl date=new MyJPanelImpl(new GridLayout(1,6));
				date.add(createJLabel( "giorno", fontSizeLabel-4));
				date.add(giorno);
				date.add(createJLabel( "mese", fontSizeLabel-4));
				date.add(mese);
				date.add(createJLabel("anno", fontSizeLabel-4));
				date.add(anno);
				panel.add(date,BorderLayout.CENTER);
				panelInternal.add(createButton("Salva", k->{
					unit.getReparto().setDateToPay(LocalDate.of(Integer.parseInt(anno.getText()),
							Integer.parseInt(mese.getText()), Integer.parseInt(giorno.getText())));
						MyJFrameSingletonImpl.getInstance().setNeedToSave();
						SwingUtilities.invokeLater(new Runnable() {
							public void run() {
								infoLeft.removeAll();
								infoLeft.add(createJLabel( "Limite pagamento retta:", fontSizeLabel-3));
								infoLeft.add(createJLabel(MyJFrameSingletonImpl.getInstance().getUnit().getReparto().getDateToPay().toString(), fontSizeLabel-3));
								infoLeft.add(new JLabel());
								infoLeft.add(new JLabel());
								infoLeft.validate();
								center.validate();
								}
						});
						dial.dispose();
				}));
				panelInternal.add(createButton("Annulla", p->{
					dial.dispose();
				}));
				panel.add(panelInternal, BorderLayout.SOUTH);
				dial.add(panel);
				dial.pack();
				dial.setLocationRelativeTo(MyJFrameSingletonImpl.getInstance());
				dial.setVisible(true);
			}));
			this.infoLeft.add(new JLabel());
			this.infoLeft.add(new JLabel());
			this.infoRight.add(new JLabel());
			this.center.add(infoLeContainer,BorderLayout.NORTH);
			center.add(new EditableMemberPanelImpl<Member>(Type.TasseReparto, myOptional.empty()));
			this.add(center,BorderLayout.CENTER);
			this.infoLeContainer.add(createJLabel("<html><U>Membri che non hanno pagato la tassa annuale</U></html>",fontSizeLabel),
					BorderLayout.SOUTH); 
			((JLabel)this.infoLeContainer.getComponent(2)).setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(0,0,0)));
			((EditableMemberPanelImpl<Member>)this.center.getComponent(1)).setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, new Color(0,0,0)));
			
		}
	}
	
	public String toString(){
		return this.repName;
	}
	
	
	
	
}
