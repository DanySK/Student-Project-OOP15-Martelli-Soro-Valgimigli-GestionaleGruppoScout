package view.gestioneReparto;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;

import control.MasterProjectImpl;
import control.UnitImpl;
import model.Squadron;
import view.general_utility.WarningNotice;
import view.gui_utility.MyJFrameSingletonImpl;
import view.gui_utility.MyJPanelImpl;
/**
 * Class that create a MyJPanel for the page of Reparto Management and it sets this MyJPanel like the
 * contentPane of MyFrameSingleton
 * @author giovanni
 *
 */
public class GestioneRepartoMainImpl extends MyJPanelImpl{
	private static final long serialVersionUID = -1348459245821012590L;
	private JScrollPane panelLeft;
	private final JPanel panelRight;
	private JPanel panelCenter;
	private final MyJPanelImpl panelBottom;
	private GridBagConstraints c = new GridBagConstraints();
	private JTree tree;
	DefaultMutableTreeNode reparto;
	private final UnitImpl unit=MyJFrameSingletonImpl.getInstance().getUnit();
	public GestioneRepartoMainImpl(){
		
		/*
		 * istanzio l'oggetto GestioneRepartoMain e i due pannelli principali
		 * un pannello a sx(JScrollPane) e uno a dex(JPanel)
		 */
		super("Gestione Reparto", MyJFrameSingletonImpl.getInstance().getContenentPane(), new GridBagLayout());
		panelRight = new MyJPanelImpl(new BorderLayout());
		panelCenter=new MyJPanelImpl();
		panelBottom=new MyJPanelImpl(new BorderLayout());
		
		/*Creo l'albero e il nodo root(reparto)*/
		reparto = new DefaultMutableTreeNode(new RepartoOverviewImpl());
		tree = new JTree(reparto);
		tree.setSize(new Dimension(MyJFrameSingletonImpl.getInstance().getHeight(), MyJFrameSingletonImpl.getInstance().getWidth()/4));
		tree.setBackground(panelRight.getBackground());
		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);			
		panelLeft=new JScrollPane(tree);
		panelLeft.repaint();
		panelLeft.validate();
		
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
						 if(node.getUserObject() instanceof SquadrigliaOverviewImpl){
							panelCenter=((SquadrigliaOverviewImpl)node.getUserObject()). new SquadrigliaOverviewImplPanel();
							panelBottom.getComponent(1).setEnabled(false);
						 }
						 if (node.getUserObject() instanceof RepartoOverviewImpl){
							 panelCenter = (RepartoOverviewImpl)node.getUserObject();
							 panelBottom.getComponent(1).setEnabled(true);
						 }
						 if(node.getUserObject() instanceof SquadrigliaManagerImpl){
							 panelCenter=((SquadrigliaManagerImpl)node.getUserObject()). new SquadrigliaManagerImplPanel();
							 panelBottom.getComponent(1).setEnabled(true);
						 }
						 panelCenter.repaint();
						 panelCenter.validate();
						 panelRight.add(panelCenter,BorderLayout.CENTER);
						 panelRight.repaint();
						 panelRight.validate();
						 if(MyJFrameSingletonImpl.getInstance().getNeedToSave()){
							SwingUtilities.invokeLater(new Runnable() {
								@Override
								public void run() {
									panelBottom.getComponent(1).setEnabled(true);
								}
							});
							JOptionPane.showMessageDialog(null, "<html><U>Attenzione!!</U><br>Ci sono modifiche"
								+ "<br>non salvate.<br>Cliccare sul pulsante \"Salva\" <br>per scriverle su disco");
						}
					 }
				 });
			}
		});
	
		/*Setto i JToolTip dell'albero*/
		tree.setCellRenderer(new TooltipTreeRenderer());
		javax.swing.ToolTipManager.sharedInstance().registerComponent(tree);
		tree.setVisibleRowCount(1);
	
		/*
		 * popolo il JTree con le varie entrate(al momento è solamente simulato)
		 */
		unit.getContainers().getSquadrons().forEach(e->{
			DefaultMutableTreeNode t = new DefaultMutableTreeNode(e.getNome());
			t.add(new DefaultMutableTreeNode(new SquadrigliaOverviewImpl(e.getNome())));
			t.add(new DefaultMutableTreeNode(new SquadrigliaManagerImpl(e.getNome())));
			reparto.add(t);			
		});
	
		/*inserisco il panelBottom e i relativi JButton in panelRight*/
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
		
		/*Setto questo MyJPanelImpl come Panel in MyFrameSingletonImpl*/
		MyJFrameSingletonImpl.getInstance().setPanel(this);
	}
	/**
	 * Insert a squadron's Jtree entry in the tree
	 * @param squad new squadron to insert
	 */
	public void updateTree(Squadron squad){
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				DefaultMutableTreeNode t = new DefaultMutableTreeNode(squad.getNome());
				t.add(new DefaultMutableTreeNode(new SquadrigliaOverviewImpl(squad.getNome())));
				t.add(new DefaultMutableTreeNode(new SquadrigliaManagerImpl(squad.getNome())));
				DefaultTreeModel model=(DefaultTreeModel) tree.getModel();				
				model.insertNodeInto(t, reparto, reparto.getChildCount());
				panelRight.revalidate();
			}
		});
	}
	
	public class TooltipTreeRenderer  extends DefaultTreeCellRenderer  {	
		
		private static final long serialVersionUID = -2924024721151248795L;

		@Override
		public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
			super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);		
			if( ((DefaultMutableTreeNode)value).getUserObject() instanceof SquadrigliaOverviewImpl){
				setToolTipText("<html>In questa sezione viene mostrata un'anteprima della squadriglia,<br>"+
						"comprendente l'elenco degli incarichi assegnati e l'elenco dei componenti.</html>");
			}
			else if(((DefaultMutableTreeNode)value).getUserObject() instanceof SquadrigliaManagerImpl){
				setToolTipText("<html>In questa sezione è possibile modificare la squadriglia;<br>"
						+ "aggiungendo membri, assegnando incarichi, etc...");
			}
			return this;
		}	 
	}
}	

