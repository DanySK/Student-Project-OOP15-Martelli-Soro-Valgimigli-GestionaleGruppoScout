
package view.gestione_tasse;

import java.awt.BorderLayout;

import javax.swing.SwingUtilities;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import control.UnitImpl;
import view.gui_utility.MyJFrameSingletonImpl;
import view.gui_utility.MySplittedPanelWithTree;

public class GestioneTasseMain extends MySplittedPanelWithTree {
	private static final long serialVersionUID = -3022959242441377373L;
	private final MySplittedPanelWithTree me;
	private final UnitImpl unit = MyJFrameSingletonImpl.getInstance().getUnit();

	public GestioneTasseMain() {

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
						if (node.getUserObject() instanceof GestioneTasseRepartoImpl) {
							setPanelCenter(((GestioneTasseRepartoImpl) node
									.getUserObject()).new GestioneTasseRepartoImplPane());
						}
						if (node.getUserObject() instanceof GestioneTasseSquadrigliaImpl) {
							setPanelCenter(((GestioneTasseSquadrigliaImpl) node
									.getUserObject()).new GestioneTasseSquadrigliaImplPane());
						}
						if(node.getUserObject() instanceof GestioneTasseExcursionRepartoImpl){
							setPanelCenter(((GestioneTasseExcursionRepartoImpl) node
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
		this.getRoot().add(new DefaultMutableTreeNode(new GestioneTasseRepartoImpl(unit.getReparto().getName())));
		this.getRoot().add(new DefaultMutableTreeNode(new GestioneTasseExcursionRepartoImpl()));
		unit.getContainers().getSquadrons().forEach(e -> {
			final DefaultMutableTreeNode t = new DefaultMutableTreeNode(e.getNome());
			t.add(new DefaultMutableTreeNode(new GestioneTasseSquadrigliaImpl(e.getNome())));
			addNode(t);
		});

	}

}
