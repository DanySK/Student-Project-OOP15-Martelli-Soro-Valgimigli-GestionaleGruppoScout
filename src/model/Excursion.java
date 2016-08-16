package model;

import java.time.LocalDate;
import java.util.List;

import model.exception.IllegalDateException;
import model.exception.ObjectAlreadyContainedException;
import model.exception.ObjectNotContainedException;

/**
 * @author riccardo
 * a class to model an excursion
 */

public interface Excursion {
	/**
	 * 
	 * @return an Integer contained the price of the excursion
	 */
	 Integer getPrize();

	/**
	 * 
	 * @return a String contained the place of the excursion
	 */
	 String getPlace();

	/**
	 * 
	 * @return a Date contained the start date excursion
	 */
	 LocalDate getDateStart();

	/**
	 * 
	 * @return a Date contained the end date excursion
	 */
	 LocalDate getDateEnd();

	/**
	 *
	 *
	 * @param prize to set
	 */
	 void setPrice(Integer prize);

	/**
	 *
	 * 
	 * @param place to set
	 */
	 void setPlace(String place);

	/**
	 *
	 * 
	 * @param dateStart to set
	 * @throws IllegalDateException if the date is not permitted
	 */
	 void setDateStart(LocalDate dateStart) throws IllegalDateException;

	/**
	 * 
	 * 
	 * @param dateEnd to set
	 * @throws IllegalDateException if the date is not permitted
	 */
	 void setDateEnd(LocalDate dateEnd) throws IllegalDateException;

	/**
	 * 
	 * 
	 * @param member to add
	 * @param pagato must be true if the member has just paid
	 * @throws ObjectAlreadyContainedException 
	 */
	 void addPartecipant(Member partecipante, Boolean pagato) throws ObjectAlreadyContainedException;

	/**
	 * remove a member from the excursion
	 * 
	 * @param member
	 * @throws ObjectNotContainedException 
	 */
	 void removePartecipant(Member partecipante) throws ObjectNotContainedException;

	/**
	 * 
	 * @return a List<Member> contained the members who has to pay
	 */
	 List<Member> getNotPaied();

	/**
	 * 
	 * @return a List<Member> contained the members who will take part in the
	 *         excursion
	 */
	 List<Member> getAllPatyecipants();

	/**
	 * 
	 * @return a List<Integer> contained the ids of the members who has NOT to
	 *         pay
	 */
	 List<Member> getAllPaied();

	/**
	 * set true the payment boolean
	 * 
	 * @param member
	 *            that has paid
	 * @throws ObjectNotContainedException 
	 */
	 void setPaied(Member partecipante) throws ObjectNotContainedException;

	/**
	 * 
	 * @param member
	 * @return true if the member will take part in the excursion
	 */
	 boolean containMember(Member partecipante);

	/**
	 * 
	 * @param member
	 * @return true if the member had paid
	 * @throws ObjectNotContainedException 
	 */
	 boolean isPaied(Member partecipante) throws ObjectNotContainedException;

	/**
	 * 
	 * @return a List<Member> contained the members who has the birthday
	 */
	 List<Member> getAllBirthdays();

	/**
	 * 
	 * @return
	 */
	 String getName();

	/**
	 * 
	 * @param name
	 */
	 void setName(String name);
}
