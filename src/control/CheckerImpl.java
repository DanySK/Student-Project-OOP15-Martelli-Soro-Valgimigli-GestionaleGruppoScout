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
import model.Reparto;

/**
 * 
 * @author Valgio
 *
 */
public class CheckerImpl implements Checker, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2321120264672768555L;
	private static final Integer DAYTOCHECK = 7;
	
	public CheckerImpl(){
	};

	@Override
	public List<Member> noPaied(final Excursion exc) {
		return exc.getNonPaganti();
	}

	@Override
	public List<Member> birthday(final int nDay, final List<Member> people) {
		LocalDate now = LocalDate.now();
		LocalDate limit = LocalDate.now().plus(nDay, ChronoUnit.DAYS);
		
		return people.stream().filter(e -> this.checkDateIsBetween(now, limit, e.getBirthday()))
					          .collect(Collectors.toList());
		
	}

	@Override
	public List<Excursion> excursionInProgram(final int nDay, final List<Excursion> excursions) {
		final LocalDate now = LocalDate.now();
		List<Excursion> exc = new ArrayList<>();
		for( int i = 0; i < nDay; i++){
			for(Excursion e : excursions){
				if(e.getDateStart().equals(now.plus(i, ChronoUnit.DAYS))){
					exc.add(e);
				}
			}
		}
		return exc;
	}
	@Override
	public List<Member> noPaiedMembers(Reparto rep){
		return rep.getMembersNotPaid(Year.now().getValue());
	}

	@Override
	public Map<String, List<Member>> stdRouting(Unit unit) {
		
		List<Member> people = unit.getContainers().getMembers();
		List<Excursion> excursions = unit.getContainers().getExcursion();
		
		Map<String, List<Member>> map = new HashMap<>();
		List<Excursion> exc = this.excursionInProgram(DAYTOCHECK, excursions);
		for(Excursion e: exc){
			map.put("Evento del " + e.getDateStart() + ": " + e.getName(), e.getNonPaganti());
		}
		List<Member> birthday = this.birthday(DAYTOCHECK, people);
		map.put("Compleanni a breve", birthday);
		
		if(this.checkDateIsBetween(unit.getLimitDateToPay(), unit.getLimitDateToPay()
									.plus(- DAYTOCHECK, ChronoUnit.DAYS), LocalDate.now())){
			map.put("Ragazzi che non hanno ancora pagato l'anno", unit.getMemberDidntPay());
		}
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
