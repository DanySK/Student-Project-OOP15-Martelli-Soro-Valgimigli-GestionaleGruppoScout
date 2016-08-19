package model.escursioni;

import java.util.List;

import model.exception.ObjectAlreadyContainedException;
import model.exception.ObjectNotContainedException;
import model.reparto.Reparto;

/**
 * 
 * @author Riccardo Soro
 *
 */
public interface Gemellaggi extends Excursion {
	/**
	 * 
	 * @return the unit that will take part in the excursion
	 */
	Reparto getReparto();

	/**
	 * remove all the other units
	 */
	void clearUnitsList();

	/**
	 * 
	 * @param reparto
	 *            to set
	 */
	void setReparto(Reparto reparto);

	/**
	 * 
	 * @return all the other units
	 */
	List<String> getOtherUnits();

	/**
	 * 
	 * @param name of the unit to add
	 * @throws ObjectAlreadyContainedException if the unit's name is contained
	 */
	void addOtherUnit(String name) throws ObjectAlreadyContainedException;

	/**
	 * 
	 * @param name of the unit to remove
	 * @throws ObjectNotContainedException if the unit's name is not contained
	 */
	void removeOtherUnit(String name) throws ObjectNotContainedException;

	/**
	 * 
	 * @param name of the unit
	 * @return true if the unit's name is contained, else false
	 */
	boolean containOtherUnit(String name);
}
