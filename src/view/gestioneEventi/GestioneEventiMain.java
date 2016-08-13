package view.gestioneEventi;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

import control.MasterProjectImpl;
import control.UnitImpl;
import view.general_utility.WarningNotice;
import view.gui_utility.MyJFrameSingletonImpl;
import view.gui_utility.MyJPanelImpl;

public class GestioneEventiMain extends MyJPanelImpl {
	private static final long serialVersionUID = 1746284130120063121L;
	
	private final JScrollPane panelLeft;
	private final JPanel panelRight;
	private JPanel panelCenter;
	private final MyJPanelImpl panelBottom;
	private GridBagConstraints c = new GridBagConstraints();
	private final JTree tree;
	private final DefaultMutableTreeNode reparto;
	private final UnitImpl unit=MyJFrameSingletonImpl.getInstance().getUnit();
	public GestioneEventiMain() {
		/*
		/*
		 * istanzio l'oggetto GestioneRepartoMain e i due pannelli principali
		 * un pannello a sx(JScrollPane) e uno a dx(JPanel)
		 */
		super("Gestione Reparto", MyJFrameSingletonImpl.getInstance().getContenentPane(), new GridBagLayout());
		panelRight = new MyJPanelImpl(new BorderLayout());
		panelCenter=new MyJPanelImpl();
		panelBottom=new MyJPanelImpl(new BorderLayout());
		
		/*Creo l'albero e il nodo root(reparto)*/
		reparto = new DefaultMutableTreeNode(unit.getName());
		
		tree = new JTree(reparto);
		tree.setSize(new Dimension(MyJFrameSingletonImpl.getInstance().getHeight(), MyJFrameSingletonImpl.getInstance().getWidth()/4));
		tree.setBackground(panelRight.getBackground());
		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);			
		panelLeft=new JScrollPane(tree);
		
		/*panelLeft.repaint();
		panelLeft.validate();*/
		
		/*Inserisco il panelLeft*/
		c.weightx=1;
		c.weighty=1;
		c.fill=GridBagConstraints.BOTH;
		panelLeft.setSize(new Dimension(MyJFrameSingletonImpl.getInstance().getHeight(), MyJFrameSingletonImpl.getInstance().getWidth()/4));
		panelLeft.setViewportView(tree);
		add(panelLeft, c);
		
		/*Inserisco il panelRight*/
		c.weightx= 4;
		c.weighty=1;
		c.fill=GridBagConstraints.BOTH;
		add(panelRight,c);
		
		/*aggiung il SelectionListener al JTree*/
		tree.addTreeSelectionListener(new TreeSelectionListener() {
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				 DefaultMutableTreeNode node = (DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
				 SwingUtilities.invokeLater(new Runnable(){
					 @Override
					 public void run() { 
						 panelRight.remove(panelCenter);
						 if(node.getUserObject() instanceof EventiReparto){
							 panelCenter=((EventiReparto)node.getUserObject()). new EventiRepartoPane();
						 }
						 if(node.getUserObject() instanceof EventiSquadriglia){
							 panelCenter=((EventiSquadriglia)node.getUserObject()). new EventiSquadrigliaPanel();
						 }
						 panelCenter.repaint();
						 panelCenter.validate();
						 panelRight.add(panelCenter,BorderLayout.CENTER);
						 panelRight.repaint();
						 panelRight.validate();
					 } 
				 });
			}
		});
		reparto.add(new DefaultMutableTreeNode(new EventiReparto()));
		unit.getContainers().getSquadrons().forEach(e->{
			DefaultMutableTreeNode t = new DefaultMutableTreeNode(e.getNome());
			t.add(new DefaultMutableTreeNode(new EventiSquadriglia(e)));
			
			reparto.add(t);			
		});
		
		panelBottom.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(0,0,0)));
		JButton back=this.getBackButton();
		back.setSize(new Dimension(panelRight.getHeight()/7,panelRight.getHeight()/7));
		panelBottom.add(back, BorderLayout.EAST);
		panelBottom.add(createButton("Salva", e->{
			try {
				(new MasterProjectImpl()).save(MyJFrameSingletonImpl.getInstance().getUnit());
				MyJFrameSingletonImpl.getInstance().resetNeedToSava();
			} catch (Exception e1) {
				new WarningNotice(e1.getMessage());
			}
			
		}));
		panelBottom.getComponent(1).setEnabled(false);
		panelRight.add(panelBottom, BorderLayout.SOUTH);
		
		/* inserisco il panelCenter in panelRight*/
		panelRight.add(panelCenter, BorderLayout.CENTER);
		
		MyJFrameSingletonImpl.getInstance().setPanel(this);
	}

	
	

	

}
