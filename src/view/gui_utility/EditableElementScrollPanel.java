package view.gui_utility;

import java.util.List;

public interface EditableElementScrollPanel<E> {

	/**
	 * update List of JButton and repaint
	 */
	void updateMember();

	/**
	 * return List of elements in the panel
	 * @return
	 */
	List<E> getList();

	/**
	 * update changing squadName parameter
	 * @param newParam
	 */
	void forceUpdate(String newParam);

}