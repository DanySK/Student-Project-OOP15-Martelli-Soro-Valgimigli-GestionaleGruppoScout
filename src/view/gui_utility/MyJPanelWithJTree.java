package view.gui_utility;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

public interface MyJPanelWithJTree {

	/**
	 * Return left-side JTree
	 * @return
	 */
	JTree getTree();

	/**
	 * return left-side JPanel
	 * @return
	 */
	MyJPanelImpl getPanelRight();

	/**
	 * set panelCenter
	 * @param panelCenter
	 */
	void setPanelCenter(MyJPanelImpl panelCenter);

	/**
	 * add a node to Jtree
	 * @param t
	 */
	void addNode(DefaultMutableTreeNode t);

	/**
	 * remove node from JTree
	 * @param nodeName
	 */
	void removeNode(String nodeName);

	/**
	 * return Jtree's root node
	 * @return
	 */
	DefaultMutableTreeNode getRoot();

}