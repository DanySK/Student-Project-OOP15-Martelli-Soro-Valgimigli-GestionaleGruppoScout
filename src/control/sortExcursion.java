package control;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

import model.Excursion;

public interface sortExcursion {
	/**
	 * 
	 * @param exc
	 * @return
	 */
	public List<Excursion> sortByDateOfStart(List<Excursion> exc);
	/**
	 * 
	 * @param exc
	 * @return
	 */
	public List<Excursion> sortByPrice(List<Excursion> exc);
	/**
	 * 
	 * @param exc
	 * @return
	 */
	public Map<String, List<Excursion>> mapExcursion(List<Excursion> exc);
	/**
	 * 
	 * @param exc
	 * @param c
	 * @return
	 */
	public List<Excursion> sortBy(List<Excursion> exc, Comparator<Excursion> c);
}
