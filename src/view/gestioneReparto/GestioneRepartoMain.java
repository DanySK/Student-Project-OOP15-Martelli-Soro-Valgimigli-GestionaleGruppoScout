package view.gestioneReparto;

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
		private final MySplittedPanelWithTree me;
	public GestioneRepartoMain(){
		/*	/*
		 * istanzio l'oggetto GestioneRepartoMain e i due pannelli principali
		 * un pannello a sx(JScrollPane) e uno a dx(JPanel)
		 */
		super("Gestione Eventi",MyJFrameSingletonImpl.getInstance().getUnit().getName());
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
						 try{
						
							 if(node.getUserObject() instanceof SquadrigliaOverviewImpl){
								 setPanelCenter((((SquadrigliaOverviewImpl)node.getUserObject()). new SquadrigliaOverviewImplPanel()));
								 me.getPanelBottom().getComponent(1).setEnabled(false);
							 }
							 if (node.getUserObject() instanceof RepartoOverviewImpl){
								 me.setPanelCenter(( (RepartoOverviewImpl)node.getUserObject()). new  RepartoOverviewImplPane());
								 getPanelBottom().getComponent(1).setEnabled(true);
							 }
							 if(node.getUserObject() instanceof SquadrigliaManagerImpl){
								 setPanelCenter(((SquadrigliaManagerImpl)node.getUserObject()). new SquadrigliaManagerImplPanel());
								 getPanelBottom().getComponent(1).setEnabled(true);
							 }
							 
						 }catch(NullPointerException k){
							//new WarningNotice("Squadriglia rimossa con successo");
						}
						
						 getPanelCenter().repaint();
						 getPanelCenter().validate();
						 getPanelRight().add(getPanelCenter(),BorderLayout.CENTER);
						 getPanelRight().repaint();
						 getPanelRight().validate();
						/* if(MyJFrameSingletonImpl.getInstance().getNeedToSave()){
							SwingUtilities.invokeLater(new Runnable() {
								@Override
								public void run() {
									getPanelBottom().getComponent(1).setEnabled(true);
								}
							});
							JOptionPane.showMessageDialog(null, "<html><U>Attenzione!!</U><br>Ci sono modifiche"
								+ "<br>non salvate.<br>Cliccare sul pulsante \"Salva\" <br>per scriverle su disco");
						}*/
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
		me.getRoot().add(new DefaultMutableTreeNode(new RepartoOverviewImpl()));
		unit.getContainers().getSquadrons().forEach(e->{
			DefaultMutableTreeNode t = new DefaultMutableTreeNode(e.getNome());
			t.add(new DefaultMutableTreeNode(new SquadrigliaOverviewImpl(e.getNome())));
			t.add(new DefaultMutableTreeNode(new SquadrigliaManagerImpl(e.getNome())));
			me.addNode(t);		
		});
		
	
		/*inserisco il panelBottom e i relativi JButton in panelRight*/
		
	}
	/**
	 * Insert a squadron's Jtree entry in the Jtree
	 * @param squad new squadron to insert
	 */
	public void addSquadToJTree(Squadron squad){
		DefaultMutableTreeNode t = new DefaultMutableTreeNode(squad.getNome());
		t.add(new DefaultMutableTreeNode(new SquadrigliaOverviewImpl(squad.getNome())));
		t.add(new DefaultMutableTreeNode(new SquadrigliaManagerImpl(squad.getNome())));
		me.addNode(t);
	}
	
	public void removeSquadToJTree(String squadToRemove){
		this.removeNode(squadToRemove);
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

