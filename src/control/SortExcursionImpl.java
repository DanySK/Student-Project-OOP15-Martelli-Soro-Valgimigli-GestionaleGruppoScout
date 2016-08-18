package control;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.CampoImpl;
import model.Excursion;
import model.UscitaImpl;

public class SortExcursionImpl implements SortExcursion {

	private static final Comparator<Excursion> BYDATE = ((Excursion e1, Excursion e2) -> e1.getDateStart()
			.compareTo(e2.getDateStart()));
	private static final Comparator<Excursion> BYPRICE = ((Excursion e1, Excursion e2) -> e1.getPrize()
			.compareTo(e2.getPrize()));

	private final SorterList sl = new SorterListImpl();

	@Override
	public List<Excursion> sortByDateOfStart(final List<Excursion> exc) {
		return this.sl.sortList(exc, BYDATE);
	}

	@Override
	public List<Excursion> sortByPrice(final List<Excursion> exc) {
		return this.sl.sortList(exc, BYPRICE);
	}

	@Override
	public Map<String, List<Excursion>> mapExcursion(final List<Excursion> exc) {
		final Map<String, List<Excursion>> map = new HashMap<>();
		map.put("Uscita", new ArrayList<>());
		map.put("Uscita di squadriglia", new ArrayList<>());
		map.put("Campo", new ArrayList<>());

		for (final Excursion e : exc) {
			if (e instanceof UscitaImpl) {
				final List<Excursion> list = map.get("Uscita");
				list.add(e);
				map.replace("Uscita", list);
				continue;
			}
			if (e instanceof CampoImpl) {
				final List<Excursion> list = map.get("Campo");
				list.add(e);
				map.replace("Campo", list);
				continue;
			}
			if (e instanceof UscitaImpl) {
				final List<Excursion> list = map.get("Uscita di squadriglia");
				list.add(e);
				map.replace("Uscita di squadriglia", list);
				continue;
			}
		}
		return map;
	}

	@Override
	public List<Excursion> sortBy(final List<Excursion> exc, final Comparator<Excursion> c) {
		return this.sl.sortList(exc, c);
	}

}
