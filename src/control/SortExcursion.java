package control;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

import model.escursioni.Excursion;

public interface SortExcursion {
	/**
	 *  Sort list by Date Of start
	 * @param exc
	 * @return
	 * A list sorted according to the date of started
	 */
	List<Excursion> sortByDateOfStart(List<Excursion> exc);

	/**
	 * Sort list by price
	 * @param exc
	 * @return
	 * A list sorted according to price
	 */
	List<Excursion> sortByPrice(List<Excursion> exc);

	/**
	 * Split the list of excursion according the type of excursion
	 * @param exc
	 * @return
	 * A map of excursion. Key = the type Value = list of excursion of that type
	 */
	Map<String, List<Excursion>> mapExcursion(List<Excursion> exc);

	/**
	 * Sort the list
	 * @param exc
	 * @param c
	 * @return
	 * A list sorted according to comparator c
	 */
	List<Excursion> sortBy(List<Excursion> exc, Comparator<Excursion> c);
}
