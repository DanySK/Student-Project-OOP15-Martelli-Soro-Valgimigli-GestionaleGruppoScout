package control;

import java.time.LocalDate;
import java.util.List;

import control.exception.MemberSexException;
import control.myUtil.Pair;
import model.escursioni.Excursion;
import model.exception.ObjectAlreadyContainedException;
import model.exception.ObjectNotContainedException;
import model.reparto.Member;
import model.reparto.Reparto;
import model.reparto.Roles;
import model.reparto.Squadron;

/**
 * The main class of the control section. This class has inside a Reaparto, a
 * list of excursions. This class fills the gap between modell and View
 * providing all useful method for the View to work.
 * 
 * @author Valgio
 *
 */
public interface Unit {
	/**
	 * Return the name for save the unit
	 * 
	 * @return A name where all " " are replaced with "_"
	 */
	String getName();

	/**
	 * 
	 * @return A container of the Reparto
	 */

	Container getContainers();

	/**
	 * Add excursion in the Excursion List
	 * 
	 * @param exc
	 */
	void addExcursion(Excursion exc);

	/**
	 * Add member in the FreeMember list, and put the member in the event of the
	 * entire unit
	 * 
	 * @param m
	 * @throws ObjectAlreadyContainedException
	 */
	void addMember(Member m) throws ObjectAlreadyContainedException;

	/**
	 * Add new Squadron in the Reparto
	 * 
	 * @param sq
	 * @throws ObjectAlreadyContainedException
	 */
	void createSq(Squadron sq) throws ObjectAlreadyContainedException;

	/**
	 * Remove member from freemember list or from a squadron and from all events
	 * 
	 * @param m
	 * @throws ObjectNotContainedException
	 */
	void removeMember(Member m) throws ObjectNotContainedException;

	/**
	 * Remove Sq from the reparto and replace all member in the freemember List
	 * 
	 * @param sq
	 * @throws ObjectNotContainedException
	 * @throws ObjectAlreadyContainedException
	 */
	void removeSq(Squadron sq) throws ObjectNotContainedException, ObjectAlreadyContainedException;

	/**
	 * Search the member in the freemember List and puts him in a squadron sq
	 * 
	 * @param m
	 * @param sq
	 * @throws ObjectAlreadyContainedException
	 * @throws ObjectNotContainedException
	 * @throws MemberSexException
	 */
	void putMemberInSq(Member m, Squadron sq, Roles rl)
			throws MemberSexException, ObjectNotContainedException, ObjectAlreadyContainedException;

	/**
	 * Method to change member from a squadron to other one
	 * 
	 * @param m
	 * @param sqOld
	 * @param sqNew
	 * @throws ObjectNotContainedException
	 * @throws ObjectAlreadyContainedException
	 * @throws MemberSexException
	 */
	void changeMemberFromSq(Member m, Squadron sqNew, Roles rl)
			throws ObjectNotContainedException, MemberSexException, ObjectAlreadyContainedException;

	/**
	 * Change the name of reparto
	 * 
	 * @param name
	 */
	void setName(String name);

	/**
	 * Provides the info of unit
	 * 
	 * @return
	 */
	String info();

	/**
	 * Special method that provide a List of pair with the general info
	 * 
	 * @return A list of array. In each cell there is a specifically information
	 *         0. Name 1. Number of member 2. Number of Squadron 3. Number of
	 *         boys 4. Number of girls
	 */
	List<Pair<String, String>> getUnitSpecificInfo();

	/**
	 * Provide the last day for pay the annual tax
	 * 
	 * @return
	 */
	LocalDate getLimitDateToPay();

	/**
	 * 
	 * @return List of member which hasn't paid yet
	 */
	List<Member> getMemberDidntPay();

	/**
	 * 
	 * @return Reparto
	 */
	Reparto getReparto();

	/**
	 * Remove excursion Named name from excursion List
	 * 
	 * @param name
	 */
	void removeExcursion(String name);

	/**
	 * Remove specific excursion ( exc ) from excursion List
	 * 
	 * @param exc
	 */
	void removeExcursion(Excursion exc);

	/**
	 * Return member called name and Surname. No defensive copy
	 * 
	 * @param name
	 * @param surname
	 * @return
	 */
	List<Member> getMember(final String name, final String surname);

	/**
	 * 
	 * @return the date of last mail send
	 */
	LocalDate getLastMailSend();

	/**
	 * Set the date of last mail send
	 * 
	 * @param lastMailSend
	 * 
	 */
	void setLastMailSend(LocalDate lastMailSend);

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
}
