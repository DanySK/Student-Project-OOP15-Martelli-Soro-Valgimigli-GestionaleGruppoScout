package view.online;

import java.awt.BorderLayout;

import javax.swing.SwingUtilities;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import view.gui_utility.MySplittedPanelWithTree;

public class OnlineMainImpl extends MySplittedPanelWithTree {

	private static final long serialVersionUID = 3771477369943096404L;
	
	
	public OnlineMainImpl() {
		super("Online", "Funzioni Online");
		
			
		
		/*aggiung il SelectionListener al JTree*/
		this.setTreeSelectionListener(new TreeSelectionListener() {
			@Override
			public void valueChanged(final TreeSelectionEvent e) {
				final DefaultMutableTreeNode node = (DefaultMutableTreeNode)(getTree().getLastSelectedPathComponent());
				 SwingUtilities.invokeLater(new Runnable(){
					 @Override
					 public void run() { 
						 getPanelRight().remove(getPanelCenter());
						 if(node.getUserObject() instanceof EventiBuonaCaccia){
							 setPanelCenter((EventiBuonaCaccia)node.getUserObject());
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
		/*getTree().setCellRenderer(new TooltipTreeRenderer());
		javax.swing.ToolTipManager.sharedInstance().registerComponent(getTree());
		getTree().setVisibleRowCount(1);*/
	
		/*
		 * popolo il JTree con le varie entrate(al momento è solamente simulato)
		 */
		getRoot().add(new DefaultMutableTreeNode(new EventiBuonaCaccia()));
		
	
		
	}
	/*
	public class TooltipTreeRenderer  extends DefaultTreeCellRenderer  {	
		private static final long serialVersionUID = -2924024721151248795L;
		@Override
		public Component getTreeCellRendererComponent(final JTree tree, final Object value, final boolean sel, final boolean expanded,
				final boolean leaf, final int row, final boolean hasFocus) {
			super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);		
			if( ((DefaultMutableTreeNode)value).getUserObject() instanceof SquadrigliaOverviewImpl.SquadrigliaOverviewImplPanel){
				setToolTipText("<html>In questa sezione viene mostrata un'anteprima della squadriglia,<br>"+
						"comprendente l'elenco degli incarichi assegnati e l'elenco dei componenti.</html>");
			}
			else if(((DefaultMutableTreeNode)value).getUserObject() instanceof SquadrigliaManagerImpl.SquadrigliaManagerImplPanel){
				setToolTipText("<html>In questa sezione è possibile modificare la squadriglia;<br>"
						+ "aggiungendo membri, assegnando incarichi, etc...");
			}
			else if(((DefaultMutableTreeNode)value).getUserObject() instanceof RepartoOverviewImpl.RepartoOverviewImplPane){
				setToolTipText("aaaa");
			}
			return this;
		}	 
	}*/
}	

