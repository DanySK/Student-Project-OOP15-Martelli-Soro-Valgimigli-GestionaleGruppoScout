package view.gestioneReparto.utility;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JDialog;

import control.myUtil.MyOptional;
import model.Member;
import view.gestioneReparto.utility.JTextAreaDialog.OB;
import view.gestioneReparto.utility.JTextAreaDialog.TextAreaType;
import view.gui_utility.MyJFrameSingletonImpl;
import view.gui_utility.MyJPanelImpl;

public class PathJDialog extends JDialog {

	private static final long serialVersionUID = 2106257742400385767L;
	private final static int FONTSIZE=18;
	
	public PathJDialog(Member mem,boolean editable){
		super();
		MyJPanelImpl panel =new MyJPanelImpl(new BorderLayout());
		MyJPanelImpl internal = new MyJPanelImpl(new GridLayout(0, 1));
		MyJPanelImpl right=new MyJPanelImpl(new GridLayout(0, 1));
		MyJPanelImpl bot=new MyJPanelImpl();
	
		if(!editable){
			internal.add(panel.createJLabel("Livello", FONTSIZE));
			right.add(panel.createJLabel( mem.getPath().getLevel(), FONTSIZE));
			right.add(panel.createButton("vedi", FONTSIZE, e->{
				new JTextAreaDialog<>(TextAreaType.OBBVIEW, mem, MyOptional.of(OB.FM));
			}));
			right.add(panel.createButton("vedi", FONTSIZE, e->{
				new JTextAreaDialog<>(TextAreaType.OBBVIEW, mem, MyOptional.of(OB.SCL));
			}));
			right.add(panel.createButton("vedi", FONTSIZE, e->{
				new JTextAreaDialog<>(TextAreaType.OBBVIEW, mem, MyOptional.of(OB.FD));
			}));
			right.add(panel.createButton("vedi", FONTSIZE, e->{
				new JTextAreaDialog<>(TextAreaType.OBBVIEW, mem, MyOptional.of(OB.RLZN));
			}));
		}
		else{
			right.add(panel.createButton("edit", FONTSIZE, e->{
				new JTextAreaDialog<>(TextAreaType.OBBEDIT, mem, MyOptional.of(OB.FM));
			}));
			right.add(panel.createButton("edit", FONTSIZE, e->{
				new JTextAreaDialog<>(TextAreaType.OBBEDIT, mem, MyOptional.of(OB.SCL));
			}));
			right.add(panel.createButton("edit", FONTSIZE, e->{
				new JTextAreaDialog<>(TextAreaType.OBBEDIT, mem, MyOptional.of(OB.FD));
			}));
			right.add(panel.createButton("edit", FONTSIZE, e->{
				new JTextAreaDialog<>(TextAreaType.OBBEDIT, mem, MyOptional.of(OB.RLZN));
			}));
		}
		bot.add(bot.createButton("OK", FONTSIZE,e->{
			this.dispose();
		}));
		panel.add(panel.createJLabel("<html></U>Cammino Membro</U></html>", FONTSIZE),BorderLayout.NORTH);
		internal.add(panel.createJLabel("Famiglia", FONTSIZE));
		internal.add(panel.createJLabel("Scuola", FONTSIZE));
		internal.add(panel.createJLabel("Fede", FONTSIZE));
		internal.add(panel.createJLabel("Relazioni", FONTSIZE));
		panel.add(internal,BorderLayout.WEST);
		panel.add(right,BorderLayout.EAST);
		panel.add(bot,BorderLayout.SOUTH);
		this.add(panel);
		this.pack();
		this.setLocationRelativeTo(MyJFrameSingletonImpl.getInstance());
		this.setVisible(true);
	}
}
