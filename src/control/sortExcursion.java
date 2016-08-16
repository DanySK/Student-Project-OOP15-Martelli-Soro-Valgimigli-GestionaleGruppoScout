package control;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

import model.Excursion;

public interface SortExcursion {
	/**
	 * 
	 * @param exc
	 * @return
	 */
	List<Excursion> sortByDateOfStart(List<Excursion> exc);
	/**
	 * 
	 * @param exc
	 * @return
	 */
	List<Excursion> sortByPrice(List<Excursion> exc);
	/**
	 * 
	 * @param exc
	 * @return
	 */
	Map<String, List<Excursion>> mapExcursion(List<Excursion> exc);
	/**
	 * 
	 * @param exc
	 * @param c
	 * @return
	 */
	List<Excursion> sortBy(List<Excursion> exc, Comparator<Excursion> c);
}
