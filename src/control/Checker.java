package control;

import java.util.List;

import model.Excursion;
import model.Member;
import model.Squadron;

public interface Checker {
	/**
	 * Method that check if who has to pay in the list people has paied
	 * @param e
	 * @return
	 * a list of members no paied
	 */
	public List<Member> noPaied(Excursion e, List<Member> people);
	/**
	 * Check the birthday of all member and select each person who has birthday
	 * in the range: today : today + nDay
	 * @return
	 * A list of person that will have a birthday in nDay
	 */
	public List<Member> birthday(int nDay, List<Member> people);
	/**
	 * Check the date for each excursion in list and select Excursion will happen within nDay
	 * @param nDay
	 * @return
	 * A list of Excursion 
	 */
	public List<Excursion> excursionInProgram(int nDay, List<Excursion> excursions);
	/**
	 * List of controls which this method to each time the program is launched 
	 */
	public void stdRouting(List<Excursion> excursions, List<Member> people, List<Squadron> sq );
}
