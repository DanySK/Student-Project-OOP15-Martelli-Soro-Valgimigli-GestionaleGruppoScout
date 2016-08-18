package control;

import java.time.LocalDate;
import java.util.List;

import control.exception.MemberSexException;
import control.myUtil.Pair;
import model.Excursion;
import model.Member;
import model.Reparto;
import model.Roles;
import model.Squadron;
import model.exception.ObjectAlreadyContainedException;
import model.exception.ObjectNotContainedException;

public interface Unit {
	/**
	 * 
	 * @return
	 */
	String getName();

	/**
	 * 
	 * @return
	 */

	Container getContainers();

	/**
	 * 
	 * @param exc
	 */
	void addExcursion(Excursion exc);

	/**
	 * 
	 * @param m
	 * @throws ObjectAlreadyContainedException
	 */
	void addMember(Member m) throws ObjectAlreadyContainedException;

	/**
	 * 
	 * @param sq
	 * @throws ObjectAlreadyContainedException
	 */
	void createSq(Squadron sq) throws ObjectAlreadyContainedException;

	/**
	 * 
	 * @param m
	 * @throws ObjectNotContainedException
	 */
	void removeMember(Member m) throws ObjectNotContainedException;

	/**
	 * 
	 * @param sq
	 * @throws ObjectNotContainedException
	 * @throws ObjectAlreadyContainedException
	 */
	void removeSq(Squadron sq) throws ObjectNotContainedException, ObjectAlreadyContainedException;

	/**
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
	 * 
	 * @return
	 */
	LocalDate getLimitDateToPay();

	/**
	 * 
	 * @return
	 */
	List<Member> getMemberDidntPay();

	/**
	 * 
	 * @return
	 */
	Reparto getReparto();

	/**
	 * 
	 * @param name
	 */
	void removeExcursion(String name);

	/**
	 * 
	 * @param exc
	 */
	void removeExcursion(Excursion exc);

	/**
	 * 
	 * @param name
	 * @param surname
	 * @return
	 */
	Member getMember(final String name, final String surname);

	/**
	 * 
	 * @return
	 */
	LocalDate getLastMailSend();

	/**
	 * 
	 * @param lastMailSend
	 */
	void setLastMailSend(LocalDate lastMailSend);
}
