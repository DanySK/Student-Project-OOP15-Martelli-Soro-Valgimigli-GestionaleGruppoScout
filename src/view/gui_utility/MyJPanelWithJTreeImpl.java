package view.gui_utility;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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
/**
 * Class modeling user inteface in all section
 * @author Giovanni Martelli
 *
 */
public class MyJPanelWithJTreeImpl extends MyJPanelImpl implements MyJPanelWithJTree {

	private static final long serialVersionUID = -4922579214047113939L;
	private JScrollPane panelLeft;
	private final MyJPanelImpl panelRight;
	private MyJPanelImpl panelCenter;
	private final MyJPanelImpl panelBottom;
	private final GridBagConstraints c = new GridBagConstraints();
	private final JTree tree;
	private final DefaultMutableTreeNode root;
	private final Map<String, DefaultMutableTreeNode> mapNode = new HashMap<>();

	public MyJPanelWithJTreeImpl(final String name, final Object rootNode) {

		/*
		 * istanzio l'oggetto GestioneRepartoMain e i due pannelli principali un
		 * pannello a sx(JScrollPane) e uno a dx(JPanel)
		 */
		super(name, MyJFrameSingletonImpl.getInstance().getContenentPane(), new GridBagLayout());
		panelRight = new MyJPanelImpl(new BorderLayout());
		panelCenter = new MyJPanelImpl();
		panelBottom = new MyJPanelImpl(new BorderLayout());
		root = new DefaultMutableTreeNode(rootNode);
		tree = new JTree(root);
		panelLeft = new JScrollPane(tree);
		tree.setSize(new Dimension(MyJFrameSingletonImpl.getInstance().getHeight(),
				MyJFrameSingletonImpl.getInstance().getWidth() / 4));
		tree.setBackground(panelRight.getBackground());
		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		updatePanel();
		MyJFrameSingletonImpl.getInstance().setPanel(this);

	}

	private void updatePanel() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub

				removeAll();
				panelRight.removeAll();
				panelCenter.removeAll();
				panelBottom.removeAll();
				panelLeft = new JScrollPane(tree);
				/* Inserisco il panelLeft */
				c.weightx = 1;
				c.weighty = 1;
				c.fill = GridBagConstraints.BOTH;

				panelLeft.setSize(new Dimension(MyJFrameSingletonImpl.getInstance().getHeight(),
						MyJFrameSingletonImpl.getInstance().getWidth() / 4));
				panelLeft.setViewportView(tree);
				add(panelLeft, c);

				/* Inserisco il panelRight */
				c.weightx = 4;
				c.weighty = 1;
				c.fill = GridBagConstraints.BOTH;
				panelRight.setSize(new Dimension(MyJFrameSingletonImpl.getInstance().getHeight(),
						(3 * MyJFrameSingletonImpl.getInstance().getWidth()) / 4));
				add(panelRight, c);
				panelBottom.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(0, 0, 0)));
				final JButton back = getBackButton();
				back.setSize(new Dimension(panelRight.getHeight() / 7, panelRight.getHeight() / 7));
				panelBottom.add(back, BorderLayout.EAST);
				panelBottom.add(createButton("Salva", e -> {
					try {
						(new MasterProjectImpl()).save(MyJFrameSingletonImpl.getInstance().getUnit());
						MyJFrameSingletonImpl.getInstance().resetNeedToSava();
					} catch (Exception e1) {
						new WarningNotice(e1.getMessage());
					}

				}));
				// panelBottom.getComponent(1).setEnabled(false);
				panelRight.add(panelBottom, BorderLayout.SOUTH);

				/* inserisco il panelCenter in panelRight */
				panelRight.add(panelCenter, BorderLayout.CENTER);
				Arrays.asList(getComponents()).stream().forEach(e -> {
					e.repaint();
					e.validate();
				});
				repaint();
				validate();
			}
		});
	}

	protected MyJPanelImpl getPanelCenter() {
		return this.panelCenter;
	}
	/* (non-Javadoc)
	 * @see view.gui_utility.MyJPanelWithJTree#getTree()
	 */
	@Override
	public JTree getTree() {
		return this.tree;
	}
	/* (non-Javadoc)
	 * @see view.gui_utility.MyJPanelWithJTree#getPanelRight()
	 */
	@Override
	public MyJPanelImpl getPanelRight() {
		return panelRight;
	}
	/* (non-Javadoc)
	 * @see view.gui_utility.MyJPanelWithJTree#setPanelCenter(view.gui_utility.MyJPanelImpl)
	 */
	@Override
	public void setPanelCenter(final MyJPanelImpl panelCenter) {
		this.panelCenter = panelCenter;
	}
	/* (non-Javadoc)
	 * @see view.gui_utility.MyJPanelWithJTree#addNode(javax.swing.tree.DefaultMutableTreeNode)
	 */
	@Override
	public void addNode(final DefaultMutableTreeNode t) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				mapNode.put((String) t.getUserObject(), t);
				final DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
				model.insertNodeInto(t, root, root.getChildCount());
				// updatePanel();
			}
		});
	}
	/**
	 * set TreeSelectionListener
	 */
	protected void setTreeSelectionListener(final TreeSelectionListener sel) {
		this.tree.addTreeSelectionListener(sel);
	}
	/* (non-Javadoc)
	 * @see view.gui_utility.MyJPanelWithJTree#removeNode(java.lang.String)
	 */
	@Override
	public void removeNode(final String nodeName) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				final DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
				root.remove(mapNode.get(nodeName));
				model.reload();
				panelRight.revalidate();
				updatePanel();
			}
		});
	}
	/* (non-Javadoc)
	 * @see view.gui_utility.MyJPanelWithJTree#getRoot()
	 */
	@Override
	public DefaultMutableTreeNode getRoot() {
		return root;
	}

}
