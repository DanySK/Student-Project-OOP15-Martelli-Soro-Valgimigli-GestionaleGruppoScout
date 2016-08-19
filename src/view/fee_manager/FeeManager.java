
package view.fee_manager;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

import control.UnitImpl;
import view.gui_utility.MyJFrameSingletonImpl;
import view.gui_utility.MyJPanelWithJTree;
import view.unit_manager.UnitOverview;

public class FeeManager extends MyJPanelWithJTree {
	private static final long serialVersionUID = -3022959242441377373L;
	private final MyJPanelWithJTree me;
	private final UnitImpl unit = MyJFrameSingletonImpl.getInstance().getUnit();

	public FeeManager() {

		/*
		 * istanzio l'oggetto GestioneRepartoMain e i due pannelli principali un
		 * pannello a sx(JScrollPane) e uno a dx(JPanel)
		 */
		super("Gestione Tasse", MyJFrameSingletonImpl.getInstance().getUnit().getName());
		this.me = this;

		/* aggiung il SelectionListener al JTree */
		setTreeSelectionListener(new TreeSelectionListener() {
			@Override
			public void valueChanged(final TreeSelectionEvent e) {
				final DefaultMutableTreeNode node = (DefaultMutableTreeNode) getTree().getLastSelectedPathComponent();
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						me.getPanelRight().remove(getPanelCenter());
						if (node.getUserObject() instanceof UnitFeeManager) {
							setPanelCenter(((UnitFeeManager) node
									.getUserObject()).new GestioneTasseRepartoImplPane());
						}
						if (node.getUserObject() instanceof SquadronFeeManager) {
							setPanelCenter(((SquadronFeeManager) node
									.getUserObject()).new GestioneTasseSquadrigliaImplPane());
						}
						if(node.getUserObject() instanceof ExcursionFeeUnitManager){
							setPanelCenter(((ExcursionFeeUnitManager) node
									.getUserObject()).new GestioneTasseExcursionRepartoImplPane());
						}

						getPanelCenter().repaint();
						getPanelCenter().validate();
						getPanelRight().add(getPanelCenter(), BorderLayout.CENTER);
						getPanelRight().repaint();
						getPanelRight().validate();
					}
				});
			}
		});
		/* Setto i JToolTip dell'albero */
		getTree().setCellRenderer(new TooltipTreeRenderer());
		javax.swing.ToolTipManager.sharedInstance().registerComponent(getTree());
		getTree().setVisibleRowCount(1);
		
		/*Popolo il JTree*/
		this.getRoot().add(new DefaultMutableTreeNode(new UnitFeeManager(unit.getReparto().getName())));
		this.getRoot().add(new DefaultMutableTreeNode(new ExcursionFeeUnitManager()));
		unit.getContainers().getSquadrons().forEach(e -> {
			final DefaultMutableTreeNode t = new DefaultMutableTreeNode(e.getNome());
			t.add(new DefaultMutableTreeNode(new SquadronFeeManager(e.getNome())));
			addNode(t);
		});

	}
	public class TooltipTreeRenderer extends DefaultTreeCellRenderer {
		private static final long serialVersionUID = -2924024721151248795L;

		@Override
		public Component getTreeCellRendererComponent(final JTree tree, final Object value, final boolean sel,
				final boolean expanded, final boolean leaf, final int row, final boolean hasFocus) {
			super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
			if (((DefaultMutableTreeNode) value).getUserObject() instanceof ExcursionFeeUnitManager) {
				setToolTipText("<html>In questa sezione è possibile gestire i pagamenti delle escursioni<br>"
						+ "per i membri che non appartengono a nessuna squadriglia.</html>");
			} else if (((DefaultMutableTreeNode) value).getUserObject() instanceof UnitFeeManager) {
				setToolTipText("<html>In questa sezione è possibile gestire i pagamenti della quona annuale</html>");
			} else if (((DefaultMutableTreeNode) value).getUserObject() instanceof UnitOverview) {
				setToolTipText("<html>In questa sezione è è possibile gestire i pagamenti della quota annuale<br>"
						+ "e delle escursioni per i membri presenti nella squadriglia</html>");
			}
			return this;
		}
	}
}
