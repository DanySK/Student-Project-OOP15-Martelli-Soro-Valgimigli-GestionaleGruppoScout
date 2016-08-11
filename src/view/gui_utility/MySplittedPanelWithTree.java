package view.gui_utility;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;

import control.MasterProjectImpl;
import view.general_utility.WarningNotice;

public class MySplittedPanelWithTree extends MyJPanelImpl {

	private static final long serialVersionUID = -4922579214047113939L;
	private final JScrollPane panelLeft;
	private final MyJPanelImpl panelRight;
	private	MyJPanelImpl panelCenter;
	private final MyJPanelImpl panelBottom;
	private GridBagConstraints c = new GridBagConstraints();
	private final JTree tree;
	private final DefaultMutableTreeNode root;
	public MySplittedPanelWithTree(String name, Object rootNode){
		/*
		/*
		 * istanzio l'oggetto GestioneRepartoMain e i due pannelli principali
		 * un pannello a sx(JScrollPane) e uno a dx(JPanel)
		 */
		super(name, MyJFrameSingletonImpl.getInstance().getContenentPane(), new GridBagLayout());
		panelRight = new MyJPanelImpl(new BorderLayout());
		panelCenter=new MyJPanelImpl();
		panelBottom=new MyJPanelImpl(new BorderLayout());
		
		/*Creo l'albero e il nodo root(reparto)*/
		root = new DefaultMutableTreeNode(rootNode);
		
		tree = new JTree(root);
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
		//panelBottom.getComponent(1).setEnabled(false);
		panelRight.add(panelBottom, BorderLayout.SOUTH);
		
		/* inserisco il panelCenter in panelRight*/
		panelRight.add(panelCenter, BorderLayout.CENTER);
		
		MyJFrameSingletonImpl.getInstance().setPanel(this);
	
	}
	protected MyJPanelImpl getPanelCenter(){
		return this.panelCenter;
	}
	public JTree getTree(){
		return this.tree;
	}
	public JScrollPane getPanelLeft() {
		return panelLeft;
	}

	public MyJPanelImpl getPanelRight() {
		return panelRight;
	}

	public MyJPanelImpl getPanelBottom() {
		return panelBottom;
	}

	public void setPanelCenter(MyJPanelImpl panelCenter) {
		this.panelCenter = panelCenter;
	}

	public void addNode(DefaultMutableTreeNode t){
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				
				DefaultTreeModel model=(DefaultTreeModel) tree.getModel();				
				model.insertNodeInto(t, root, root.getChildCount());
				panelRight.revalidate();
			}
		});
	}
	protected void setTreeSelectionListener(TreeSelectionListener sel){
		tree.addTreeSelectionListener(sel);
	}
	
	
	
	
	
}
