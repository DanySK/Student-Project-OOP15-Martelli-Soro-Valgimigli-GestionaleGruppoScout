package view.gestioneEventi;

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
import view.gestioneReparto.RepartoOverviewImpl;
import view.gestioneReparto.SquadrigliaManagerImpl;
import view.gestioneReparto.SquadrigliaOverviewImpl;
import view.gestioneReparto.GestioneRepartoMain.TooltipTreeRenderer;
import view.gestioneReparto.RepartoOverviewImpl.RepartoOverviewImplPane;
import view.gestioneReparto.SquadrigliaManagerImpl.SquadrigliaManagerImplPanel;
import view.gestioneReparto.SquadrigliaOverviewImpl.SquadrigliaOverviewImplPanel;
import view.gui_utility.MyJFrameSingletonImpl;
import view.gui_utility.MySplittedPanelWithTree;

public class GestioneEventiMain extends MySplittedPanelWithTree{

	private final UnitImpl unit=MyJFrameSingletonImpl.getInstance().getUnit();
	private final MySplittedPanelWithTree me;
	public GestioneEventiMain(){
		/*	/*
		 * istanzio l'oggetto GestioneRepartoMain e i due pannelli principali
		 * un pannello a sx(JScrollPane) e uno a dx(JPanel)
		 */
		super("Gestione Reparto",MyJFrameSingletonImpl.getInstance().getUnit().getName());
		me=this;
		
		/*aggiung il SelectionListener al JTree*/
		this.setTreeSelectionListener(new TreeSelectionListener() {
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				 DefaultMutableTreeNode node = (DefaultMutableTreeNode)(me.getTree().getLastSelectedPathComponent());
				 SwingUtilities.invokeLater(new Runnable(){
					 @Override
					 public void run() { 
						 me.getPanelRight().remove(getPanelCenter());
							 if(node.getUserObject() instanceof EventiReparto){
								 setPanelCenter((((EventiReparto)node.getUserObject()). new EventiRepartoPane()));
								 
							 }
							 if (node.getUserObject() instanceof EventiSquadriglia){
								 me.setPanelCenter(( (EventiSquadriglia)node.getUserObject()). new  EventiSquadrigliaPanel());
								 
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
		 * popolo il JTree con le varie entrate
		 */
		me.getRoot().add(new DefaultMutableTreeNode(new EventiReparto()));
		unit.getContainers().getSquadrons().forEach(e->{
			DefaultMutableTreeNode t = new DefaultMutableTreeNode(e.getNome());
			t.add(new DefaultMutableTreeNode(new EventiSquadriglia(e.getNome())));
			me.addNode(t);		
		});
		
	
		/*inserisco il panelBottom e i relativi JButton in panelRight*/
		
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

