package control;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Year;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import model.Excursion;
import model.Member;
import model.Squadron;

public class CheckerImpl implements Checker, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2321120264672768555L;
	private final Integer DAYTOCHECK = 7;

	@Override
	public List<Member> noPaied(Excursion e, List<Member> people) {
		return e.getNonPaganti();
	}

	@Override
	public List<Member> birthday(int nDay, List<Member> people) {
		LocalDate now = LocalDate.now();
		LocalDate limit = LocalDate.now().plus(nDay, ChronoUnit.DAYS);
		
		return people.stream().filter(e -> this.checkDateIsBetween(now, limit, e.getBirthday()))
					          .collect(Collectors.toList());
		
	}

	@Override
	public List<Excursion> excursionInProgram(int nDay, List<Excursion> excursions) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, List<Member>> stdRouting(List<Excursion> excursions, List<Member> people, List<Squadron> sq) {
		
		Map<String, List<Member>> map = new HashMap<>();
		List<Excursion> exc = this.excursionInProgram(DAYTOCHECK, excursions);
		for(Excursion e: exc){
			map.put("Evento del " + e.getDateStart(), e.getNonPaganti());
		}
		List<Member> birthday = this.birthday(DAYTOCHECK, people);
		map.put("Compleanni a breve", birthday);
		
		return map;
	}
	
	private boolean checkDateIsBetween(LocalDate start, LocalDate end, LocalDate birthday){
		
		LocalDate tmp = Year.now().atMonth(birthday.getMonthValue()).atDay(birthday.getDayOfMonth());
		if(tmp.compareTo(start) >= 0 && tmp.compareTo(end) <= 0){
			return true;
		}
		return false;
	}

	
	
}
