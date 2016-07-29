package model;

import java.util.List;

public interface Gemellaggi {
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
	 */
	public void addOtherUnit(String name);

	/**
	 * 
	 * @param name
	 */
	public void removeOtherUnit(String name);

	/**
	 * 
	 * @param name
	 * @return
	 */
	public boolean containOtherUnit(String name);
}
