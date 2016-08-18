package control;

import java.util.Comparator;
import java.util.List;

/**
 * Calss create with the target of sort a list
 * 
 * @author Valgio
 *
 */
public interface SorterList {
	/**
	 * Method the sort the list in input using the Comparator in input
	 * 
	 * @param list
	 * @param comparator
	 * @return A list with the element in list sorted by comparator
	 */
	<E> List<E> sortList(List<E> list, Comparator<? super E> comparator);
}
