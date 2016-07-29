package control;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.CampoImpl;
import model.Excursion;
import model.UscitaImpl;

public class sortExcursionImpl extends SorterListImpl implements sortExcursion {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3951862914381896791L;
	private static final Comparator<Excursion> BYDATE = ((Excursion e1, Excursion e2) ->
														e1.getDateStart().compareTo(e2.getDateStart()));
	private static final Comparator<Excursion> BYPRICE = ((Excursion e1, Excursion e2) ->
														e1.getPrize().compareTo(e2.getPrize()));
	

	@Override
	public List<Excursion> sortByDateOfStart(List<Excursion> exc) {
		return this.sortList(exc, BYDATE);
	}

	@Override
	public List<Excursion> sortByPrice(List<Excursion> exc) {
		return this.sortList(exc, BYPRICE);
	}

	@Override
	public Map<String, List<Excursion>> mapExcursion(List<Excursion> exc) {
		Map<String, List<Excursion>> map = new HashMap<>();
		map.put("Uscita", new ArrayList<>());
		map.put("Uscita di squadriglia", new ArrayList<>());
		map.put("Campo", new ArrayList<>());
		
		for(Excursion e : exc){
			if(e instanceof UscitaImpl){
				List<Excursion> list = map.get("Uscita");
				list.add(e);
				map.replace("Uscita", list);
				continue;
			}
			if(e instanceof CampoImpl){
				List<Excursion> list = map.get("Campo");
				list.add(e);
				map.replace("Campo", list);
				continue;
			}
			if(e instanceof UscitaImpl){
				List<Excursion> list = map.get("Uscita di squadriglia");
				list.add(e);
				map.replace("Uscita di squadriglia", list);
				continue;
			}
		}
		return map;
	}

	@Override
	public List<Excursion> sortBy(List<Excursion> exc, Comparator<Excursion> c) {
		return this.sortList(exc, c);
	}

}
