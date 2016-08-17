package view.gestione_reparto;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

import control.UnitImpl;
import model.Squadron;
import view.gui_utility.MyJFrameSingletonImpl;
import view.gui_utility.MySplittedPanelWithTree;
/**
 * Class that create a MyJPanel for the page of Reparto Management and it sets this MyJPanel like the
 * contentPane of MyFrameSingleton
 * @author giovanni
 *
 */
public class GestioneRepartoMain extends MySplittedPanelWithTree{
	private static final long serialVersionUID = -1348459245821012590L;
	private final UnitImpl unit=MyJFrameSingletonImpl.getInstance().getUnit();

	public GestioneRepartoMain(){
		/*
		 * istanzio l'oggetto GestioneRepartoMain e i due pannelli principali
		 * un pannello a sx(JScrollPane) e uno a dx(JPanel)
		 */
		super("Gestione Reparto",MyJFrameSingletonImpl.getInstance().getUnit().getName());
		
		/*aggiung il SelectionListener al JTree*/
		this.setTreeSelectionListener(new TreeSelectionListener() {
			@Override
			public void valueChanged(final TreeSelectionEvent e) {
				final DefaultMutableTreeNode node = (DefaultMutableTreeNode)(getTree().getLastSelectedPathComponent());
				 SwingUtilities.invokeLater(new Runnable(){
					 @Override
					 public void run() { 
						 getPanelRight().remove(getPanelCenter());
						 if(node.getUserObject() instanceof SquadrigliaOverviewImpl){
							 setPanelCenter((((SquadrigliaOverviewImpl)node.getUserObject()). new SquadrigliaOverviewImplPanel()));
						}
						if (node.getUserObject() instanceof RepartoOverviewImpl){
								 setPanelCenter(( (RepartoOverviewImpl)node.getUserObject()). new  RepartoOverviewImplPane());
						}
						if(node.getUserObject() instanceof SquadrigliaManagerImpl){
							setPanelCenter(((SquadrigliaManagerImpl)node.getUserObject()). new SquadrigliaManagerImplPanel());
						}
						 getPanelCenter().repaint();
						 getPanelCenter().validate();
						 getPanelRight().add(getPanelCenter(),BorderLayout.CENTER);
						 getPanelRight().repaint();
						 getPanelRight().validate();
						
					 }
				 });
			}
		});
	
		/*Setto i JToolTip dell'albero*/
		getTree().setCellRenderer(new TooltipTreeRenderer());
		javax.swing.ToolTipManager.sharedInstance().registerComponent(getTree());
		getTree().setVisibleRowCount(1);
	
		/*
		 * popolo il JTree con le varie entrate(al momento è solamente simulato)
		 */
		getRoot().add(new DefaultMutableTreeNode(new RepartoOverviewImpl()));
		unit.getContainers().getSquadrons().forEach(e->{
			final DefaultMutableTreeNode t = new DefaultMutableTreeNode(e.getNome());
			t.add(new DefaultMutableTreeNode(new SquadrigliaOverviewImpl(e.getNome())));
			t.add(new DefaultMutableTreeNode(new SquadrigliaManagerImpl(e.getNome())));
			addNode(t);		
		});
		
	
		/*inserisco il panelBottom e i relativi JButton in panelRight*/
		
	}
	/**
	 * Insert a squadron's Jtree entry in the Jtree
	 * @param squad new squadron to insert
	 */
	public void addSquadToJTree(final Squadron squad){
		final DefaultMutableTreeNode t = new DefaultMutableTreeNode(squad.getNome());
		t.add(new DefaultMutableTreeNode(new SquadrigliaOverviewImpl(squad.getNome())));
		t.add(new DefaultMutableTreeNode(new SquadrigliaManagerImpl(squad.getNome())));
		addNode(t);
	}
	
	public void removeSquadToJTree(final String squadToRemove){
		this.removeNode(squadToRemove);
	}
	public class TooltipTreeRenderer  extends DefaultTreeCellRenderer  {	
		private static final long serialVersionUID = -2924024721151248795L;
		@Override
		public Component getTreeCellRendererComponent(final JTree tree, final Object value, final boolean sel, final boolean expanded,
				final boolean leaf, final int row, final boolean hasFocus) {
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

