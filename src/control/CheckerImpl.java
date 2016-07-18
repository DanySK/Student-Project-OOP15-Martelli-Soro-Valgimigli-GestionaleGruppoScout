package control;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Year;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
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
		return null;
	}

	@Override
	public List<Member> birthday(int nDay, List<Member> people) {
		final LocalDate now = LocalDate.now();
		return people.stream()
				.filter(e -> e.getBirthday().compareTo(now) >= 0 &&
						e.getBirthday().compareTo(now.plus(nDay, ChronoUnit.DAYS))<= 0)
				.collect(Collectors.toList());
	}

	@Override
	public List<Excursion> excursionInProgram(int nDay, List<Excursion> excursions) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void stdRouting(List<Excursion> excursions, List<Member> people, List<Squadron> sq) {
		List<Excursion> exc = this.excursionInProgram(DAYTOCHECK, excursions);
		List<Member> m = new ArrayList<>();
		for(Excursion e: exc){
			m.addAll(this.noPaied(e, people));
		}
		
		List<Member> birthday = this.birthday(DAYTOCHECK, people);
	}

	
	
}
