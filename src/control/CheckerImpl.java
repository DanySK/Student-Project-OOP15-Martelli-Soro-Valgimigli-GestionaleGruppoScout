package control;

import java.io.Serializable;
import java.time.Year;
import java.util.List;

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
		
	}

	@Override
	public List<Excursion> excursionInProgram(int nDay, List<Excursion> excursions) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void stdRouting(List<Excursion> excursions, List<Member> people, List<Squadron> sq) {
		
	}

	
	
}
