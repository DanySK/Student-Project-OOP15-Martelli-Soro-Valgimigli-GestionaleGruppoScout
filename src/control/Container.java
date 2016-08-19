package control;

import java.util.List;
import java.util.function.Predicate;

import control.exception.MemberNotExistException;
import control.exception.SquadronNotExistException;
import model.escursioni.Campo;
import model.escursioni.EventiDiZona;
import model.escursioni.Excursion;
import model.escursioni.Gemellaggi;
import model.escursioni.Uscita;
import model.escursioni.UscitaSquadriglia;
import model.exception.ObjectNotContainedException;
import model.reparto.Member;
import model.reparto.Squadron;

/**
 * Class that contains infos, entities and relations. This class implements
 * methods to search entities
 * 
 * @author Valgio
 *
 */
public interface Container {

	/**
	 * Research of a member in the whole list. If two or more members match a
	 * special graphic interface will be called.
	 * 
	 * @param Name
	 * @return List of Members named Name
	 * @throws IllegalArgumentException
	 */
	List<Member> findMember(String name) throws IllegalArgumentException;

	/**
	 * 
	 * @return Entire list of members
	 */
	List<Member> getMembers();

	/**
	 * 
	 * @return All members which are incomplete
	 */
	List<Member> membersIncomplete();

	/**
	 * 
	 * @param p
	 * @return A list of member which match with the Predicate
	 */
	List<Member> members(Predicate<? super Member> p);

	/**
	 * Research of a Squadron in the whole list
	 * 
	 * @param name
	 * @return Squadron researched
	 */
	Squadron findSquadron(String name);

	/**
	 * 
	 * @return the entire list of squadrons
	 */
	List<Squadron> getSquadrons();

	/**
	 * Provides all member which aren't in a Squadron
	 * @param m
	 */

	List<Member> getFreeMember();

	/**
	 * To detached member from his squadron
	 * 
	 * @param member
	 * @param sq
	 * @throws SquadronNotExistException
	 * @throws MemberNotExistException
	 * @throws ObjectNotContainedException
	 */
	void removeMeberFromSquadron(Member member, Squadron sq)
			throws SquadronNotExistException, MemberNotExistException, ObjectNotContainedException;

	/**
	 * 
	 * @return
	 * the whole collection of Excursion
	 */
	List<Excursion> getExcursion();

	/**
	 * Method useful to search specific Excursion using a predicate
	 * @param p
	 * @return
	 * A list of excursion which matches to the predicate
	 */
	List<Excursion> excursions(Predicate<? super Excursion> p);

	/**
	 * Method to research an Excursion
	 * @param name
	 * @return
	 * Excursion  name
	 */
	Excursion getExcursionNamed(String name);

	/**
	 * Method to search a member using name and surname
	 * @param name
	 * @param surname
	 * @return
	 */
	Member getMember(String name, String surname);

	/**
	 * Method to search specific excursion 
	 * @param name
	 * @return
	 * A Exit called name
	 */
	Uscita getExit(String name);

	/**
	 *  Method to search specific excursion
	 * @param name
	 * @return
	 * A Squadron exit called name
	 */
	UscitaSquadriglia getExcursionSq(String name);

	/**
	 *  Method to search specific excursion
	 * @param name
	 * @return
	 * A event with two unit called name
	 */
	Gemellaggi getTwoUnitEvent(String name);

	/**
	 *  Method to search specific excursion
	 * @param name
	 * @return
	 * A local Event called name
	 */
	EventiDiZona getLocalEvent(String name);

	/**
	 *  Method to search specific excursion
	 * @param name
	 * @return
	 * A camp called name
	 */
	Campo getCamp(String name);

	/**
	 *  Method to search specific excursion
	 * @param sq
	 * @return
	 * A list of Exit of Squadron sq
	 */
	List<UscitaSquadriglia> getExcursionOfSquadron(Squadron sq);
	/**
	 * 
	 * @param name
	 * @param surname
	 * @return
	 */
	List<Member> getMemberNamed(String name, String surname);
	/**
	 * 
	 * @param name
	 * @return
	 */
	List<Member> getMemberWithName(String name);
	/**
	 * 
	 * @param Surname
	 * @return
	 */
	List<Member> getMemberWithSurname(String Surname);
	/**
	 * 
	 * @param name
	 * @param list
	 * @return
	 */
	List<Member> getMemberWithNameFromList(String name, List<Member> list);
	/**
	 * 
	 * @param Surname
	 * @param list
	 * @return
	 */
	List<Member> getMemberWithSurnameFromList(String Surname, List<Member> list);
	/**
	 * 
	 * @param name
	 * @param surname
	 * @param list
	 * @return
	 */
	List<Member> getMemberNamedFromList(String name, String surname, List<Member> list);

}
