package control;

import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;

import model.Excursion;
import model.Member;
import model.Reparto;

/**
 * Class that check birthday and payment
 * 
 * @author Valgio
 *
 */
public interface Checker {
	/**
	 * Method that check if who has to pay in the list people has paied
	 * 
	 * @param e
	 * @return a list of members no paied
	 */
	List<Member> noPaied(Excursion exc);

	/**
	 * Check the birthday of all member and select each person who has birthday
	 * in the range: today : today + nDay
	 * 
	 * @return A list of person that will have a birthday in nDay
	 */
	List<Member> birthday(int nDay, List<Member> people);

	/**
	 * Check the date for each excursion in list and select Excursion will
	 * happen within nDay
	 * 
	 * @param nDay
	 * @return A list of Excursion
	 */
	List<Excursion> excursionInProgram(int nDay, List<Excursion> excursions);

	/**
	 * Finds members didn't pay the annual tax
	 * 
	 * @param rp
	 * @return
	 */
	List<Member> noPaiedMembers(Reparto rep);

	/**
	 * List of controls which this method to each time the program is launched
	 * 
	 * @param u
	 * @return Info about birthday and Excursion in program
	 * @throws MessagingException
	 */
	Map<String, List<String>> stdRouting(Unit unit) throws MessagingException;
}
