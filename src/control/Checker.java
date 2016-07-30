package control;

import java.util.List;
import java.util.Map;

import model.Excursion;
import model.Member;
import model.Reparto;

public interface Checker {
	/**
	 * Method that check if who has to pay in the list people has paied
	 * @param e
	 * @return
	 * a list of members no paied
	 */
	public List<Member> noPaied(Excursion e);
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
	 * Finds members didn't pay the annual tax
	 * @param rp
	 * @return
	 */
	public List<Member> noPaiedMembers(Reparto rp);
	/**
	 * List of controls which this method to each time the program is launched
	 * @param u
	 * @return
	 * A map that has for Keys "Excursion" and "Birthday" and for values all members which have the birthday
	 * within Default Numb of days and all members which haven't payed the excursion
	 */
	public Map<String, List<Member>> stdRouting(Unit u);
}
