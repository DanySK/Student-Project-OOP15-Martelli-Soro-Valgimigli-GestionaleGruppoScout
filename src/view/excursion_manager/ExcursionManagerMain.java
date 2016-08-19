package view.excursion_manager;

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
/**
 * Class that display Excursion Management main page
 * @author Giovanni Martelli
 *
 */
public class ExcursionManagerMain extends MyJPanelWithJTree {

	private static final long serialVersionUID = -6671105472838081521L;
	private final UnitImpl unit = MyJFrameSingletonImpl.getInstance().getUnit();
	private final MyJPanelWithJTree me;
	
	public ExcursionManagerMain() {
		/*
		 * istanzio l'oggetto GestioneRepartoMain e i due pannelli principali un
		 * pannello a sx(JScrollPane) e uno a dx(JPanel)
		 */
		super("Gestione Reparto", MyJFrameSingletonImpl.getInstance().getUnit().getName());
		me = this;
		/* aggiung il SelectionListener al JTree */
		this.setTreeSelectionListener(new TreeSelectionListener() {
			@Override
			public void valueChanged(final TreeSelectionEvent e) {
				final DefaultMutableTreeNode node = (DefaultMutableTreeNode) (me.getTree()
						.getLastSelectedPathComponent());
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						me.getPanelRight().remove(getPanelCenter());
						if (node.getUserObject() instanceof UnitExcursionImpl) {
							setPanelCenter(((UnitExcursionImpl) node.getUserObject()).new UnitExcursionPaneImpl());

						}
						if (node.getUserObject() instanceof SquadronExcursion) {
							me.setPanelCenter(((SquadronExcursion) node.getUserObject()).new SquadronExcursionPaneImpl());

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
		/*
		 * popolo il JTree con le varie entrate
		 */
		me.getRoot().add(new DefaultMutableTreeNode(new UnitExcursionImpl()));
		unit.getContainers().getSquadrons().forEach(e -> {
			final DefaultMutableTreeNode t = new DefaultMutableTreeNode(e.getNome());
			t.add(new DefaultMutableTreeNode(new SquadronExcursion(e.getNome())));
			me.addNode(t);
		});
		/* inserisco il panelBottom e i relativi JButton in panelRight */

	}

	public class TooltipTreeRenderer extends DefaultTreeCellRenderer {
		private static final long serialVersionUID = -2924024721151248795L;

		@Override
		public Component getTreeCellRendererComponent(final JTree tree, final Object value, final boolean sel,
				final boolean expanded, final boolean leaf, final int row, final boolean hasFocus) {
			super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
			if (((DefaultMutableTreeNode) value).getUserObject() instanceof UnitExcursionImpl) {
				setToolTipText("<html>In questa sezione è possibile gestire tutti gli eventi, del reparto</html>");
			} else if (((DefaultMutableTreeNode) value).getUserObject() instanceof SquadronExcursion) {
				setToolTipText("<html>In questa sezione è possibile gestire le uscite di squadriglia</html>");
			}
			return this;
		}
	}
}
