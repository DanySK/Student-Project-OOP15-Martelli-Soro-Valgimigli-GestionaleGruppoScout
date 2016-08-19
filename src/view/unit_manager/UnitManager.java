package view.unit_manager;

import model.reparto.Squadron;

public interface UnitManager {

	/**
	 * Insert a squadron's Jtree entry in the Jtree
	 * 
	 * @param squad
	 *            new squadron to insert
	 */
	void addSquadToJTree(Squadron squad);

	/**
	 * remove a squadron's Jtree entry from the Jtree
	 * 
	 * @param squad
	 *            squadron to remove
	 */
	void removeSquadToJTree(String squadToRemove);

}