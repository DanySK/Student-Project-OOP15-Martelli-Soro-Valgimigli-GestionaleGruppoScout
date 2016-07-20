package control;

import java.util.List;

import control.myUtil.Pair;

public interface Unit {
	/**
	 * 
	 * @return
	 */
	public String getName();
	/**
	 * 
	 * @return
	 */
	public Container getContainers();
	/**
	 * 
	 * @param name
	 */
	public void setName(String name);
	/**
	 * 
	 * @param cnt
	 */
	public void setContainer(Container cnt);
	/**
	 * Provides the info of unit
	 * @return
	 */
	public String info();
	/**
	 * Special method that provide a List of pair with the general info
	 * @return
	 * A list of array. In each cell there is a specifically information
	 * 0. Name
	 * 1. Number of member
	 * 2. Number of Squadron
	 * 3. Number of boys
	 * 4. Number of girls
	 */
	public List<Pair<String, String>> getUnitSpecificInfo();
	
}
