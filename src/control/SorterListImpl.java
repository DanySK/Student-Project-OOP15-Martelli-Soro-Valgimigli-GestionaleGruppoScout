package control;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;

public class SorterListImpl implements SorterList, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5058093158670642935L;

	@Override
	public <E> List<E> sortList(final List<E> list, final Comparator<? super E> comparator) {
		list.sort(comparator);
		return list;
	}

}
