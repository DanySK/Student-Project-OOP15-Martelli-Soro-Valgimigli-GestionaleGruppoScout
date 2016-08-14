package model;

import java.util.List;

import model.exception.ObjectAlreadyContainedException;
import model.exception.ObjectNotContainedException;

public interface Gemellaggi extends Excursion {
	/**
	 * 
	 * @return
	 */
	public Reparto getReparto();

	/**
	 * 
	 * @param reparto
	 */
	public void setReparto(Reparto reparto);

	/**
	 * 
	 * @return
	 */
	public List<String> getOtherUnits();

	/**
	 * 
	 * @param name
	 * @throws ObjectAlreadyContainedException 
	 */
	public void addOtherUnit(String name) throws ObjectAlreadyContainedException;

	/**
	 * 
	 * @param name
	 * @throws ObjectNotContainedException 
	 */
	public void removeOtherUnit(String name) throws ObjectNotContainedException;

	/**
	 * 
	 * @param name
	 * @return
	 */
	public boolean containOtherUnit(String name);
}
