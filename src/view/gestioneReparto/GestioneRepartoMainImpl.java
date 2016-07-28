package view.gestioneReparto;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
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
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeSelectionModel;

import control.UnitImpl;
import model.SquadronImpl;
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
	private final JScrollPane panelLeft;
	private final JPanel panelRight;
	private JPanel panelCenter;
	private final JPanel panelBottom;
	private GridBagConstraints c = new GridBagConstraints();
	private final JTree tree;
	private final UnitImpl unit=MyJFrameSingletonImpl.getInstance().getUnit();
	public GestioneRepartoMainImpl(){
		/*
		 * istanzio l'oggetto GestioneRepartoMain e i due pannelli principali
		 * un pannello a sx(JScrollPane) e uno a dex(JPanel)
		 */
		super("Gestione Reparto", MyJFrameSingletonImpl.getInstance().getContenentPane(), new GridBagLayout());
		panelRight = new JPanel(new BorderLayout());
		panelCenter=new JPanel();
		panelBottom=new JPanel(new BorderLayout());
		//--------------->attenzione necessario mutare nome in base al nome del reparto
		
		/*Creo l'albero e il nodo root(reparto)*/
		DefaultMutableTreeNode reparto = new DefaultMutableTreeNode(unit.getName());
		tree = new JTree(reparto);
		tree.setSize(new Dimension(MyJFrameSingletonImpl.getInstance().getHeight(), MyJFrameSingletonImpl.getInstance().getWidth()/4));
		tree.setBackground(panelRight.getBackground());
		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		/*aggiung il SelectionListener al JTree*/
		tree.addTreeSelectionListener(new TreeSelectionListener() {
			
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				 DefaultMutableTreeNode node = (DefaultMutableTreeNode)
                         tree.getLastSelectedPathComponent();
				  SwingUtilities.invokeLater(new Runnable(){

					 @Override
					 public void run() {
						 panelRight.remove(panelCenter);
						 if(node.getUserObject() instanceof SquadrigliaOverviewImpl){
							 
							 panelCenter= (SquadrigliaOverviewImpl)node.getUserObject();
						 }
						 if(node.getUserObject() instanceof String){
							 panelCenter=new JPanel();
							 panelCenter.add(new JButton("Squadriglia: "+(String)node.getUserObject()));
						 }
						 panelRight.add(panelCenter,BorderLayout.CENTER);
						 panelRight.repaint();
						 panelRight.validate();
					 }
				 });
			}
		});
		
		/*
		 * popolo il JTree con le varie entrate(al momento è solamente simulato)
		 */
		
		/*for(Squadron i : new UnitImpl("Ciao").getContainers().getSquadrons()){
			reparto.add(new DefaultMutableTreeNode(new SquadrigliaOverviewImpl(i)));
		}*/
		
		for( int i=0;i < 5;i++){
			reparto.add(new DefaultMutableTreeNode(new SquadrigliaOverviewImpl(new SquadronImpl(Integer.toString(i),false))));
			((DefaultMutableTreeNode)reparto.getLastChild()).add((new DefaultMutableTreeNode(new String(i+" CIAO MONDO COME STAI?"))));
		}
		
		/*Setto i JToolTip dell'albero*/
		tree.setCellRenderer(new TooltipTreeRenderer());
		javax.swing.ToolTipManager.sharedInstance().registerComponent(tree);
		tree.expandRow(0);
		tree.setVisibleRowCount(1);
				
		/*Inserisco il panelLeft*/
		panelLeft=new JScrollPane(tree);
		c.weightx=1;
		c.weighty=1;
		c.fill=GridBagConstraints.BOTH;
		panelLeft.setSize(new Dimension(MyJFrameSingletonImpl.getInstance().getHeight(), MyJFrameSingletonImpl.getInstance().getWidth()/4));
		panelLeft.setViewportView(tree);
		this.add(panelLeft, c);
		
		/*Inserisco il panelRight*/
		c.weightx= 4;
		c.weighty=1;
		c.fill=GridBagConstraints.BOTH;
		this.add(panelRight,c);
		
		/*inserisco il panelBottom e i relativi JButton in panelRight*/
		panelBottom.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, new Color(0,0,0)));
		JButton back=this.getBackButton();
		back.setSize(new Dimension(panelRight.getHeight()/7,panelRight.getHeight()/7));
		panelBottom.add(back, BorderLayout.EAST);
		panelRight.add(panelBottom, BorderLayout.SOUTH);
		
		/* inserisco il panelCenter in panelRight*/
		panelRight.add(panelCenter, BorderLayout.CENTER);
		
		/*Setto questo MyJPanelImpl come Panel in MyFrameSingletonImpl*/
		MyJFrameSingletonImpl.getInstance().setPanel(this);
		
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
			return this;
		}	 
	}	
}
