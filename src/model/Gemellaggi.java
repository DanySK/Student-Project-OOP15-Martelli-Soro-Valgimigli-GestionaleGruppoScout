package model;

import java.util.List;

import model.exception.ObjectAlreadyContainedException;
import model.exception.ObjectNotContainedException;

public interface Gemellaggi extends Excursion {
	/**
	 * 
	 * @return
	 */
	 Reparto getReparto();
	 /**
	  * 
	  */
	 void clearUnitsList();
	/**
	 * 
	 * @param reparto
	 */
	 void setReparto(Reparto reparto);

	/**
	 * 
	 * @return
	 */
	 List<String> getOtherUnits();

	/**
	 * 
	 * @param name
	 * @throws ObjectAlreadyContainedException 
	 */
	 void addOtherUnit(String name) throws ObjectAlreadyContainedException;

	/**
	 * 
	 * @param name
	 * @throws ObjectNotContainedException 
	 */
	 void removeOtherUnit(String name) throws ObjectNotContainedException;

	/**
	 * 
	 * @param name
	 * @return
	 */
	 boolean containOtherUnit(String name);
}
