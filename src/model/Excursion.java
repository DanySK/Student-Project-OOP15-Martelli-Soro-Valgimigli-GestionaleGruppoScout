package model;

import java.time.LocalDate;
import java.util.List;

import model.exception.IllegalDateException;
import model.exception.ObjectAlreadyContainedException;
import model.exception.ObjectNotContainedException;

/**
 * @author riccardo
 *
 */
/**
 * An interface modelling a excursion
 *
 */
public interface Excursion {
	/**
	 * 
	 * @return an Integer contained the price of the excursion
	 */
	public Integer getPrize();

	/**
	 * 
	 * @return a String contained the place of the excursion
	 */
	public String getPlace();

	/**
	 * 
	 * @return a Date contained the start date excursion
	 */
	public LocalDate getDateStart();

	/**
	 * 
	 * @return a Date contained the end date excursion
	 */
	public LocalDate getDateEnd();

	/**
	 * set the price of the excursion
	 * 
	 * @param prize
	 */
	public void setPrice(Integer prize);

	/**
	 * set the place of the excursion
	 * 
	 * @param place
	 */
	public void setPlace(String place);

	/**
	 * set the start date excursion
	 * 
	 * @param dateStart
	 * @throws IllegalDateException
	 */
	public void setDateStart(LocalDate dateStart) throws IllegalDateException;

	/**
	 * set the end date excursion
	 * 
	 * @param dateEnd
	 * @throws IllegalDateException
	 */
	public void setDateEnd(LocalDate dateEnd) throws IllegalDateException;

	/**
	 * add a member in the excursion
	 * 
	 * @param member
	 * @param pagato,
	 *            must be true if the member has just paid
	 * @throws ObjectAlreadyContainedException 
	 */
	public void addPartecipante(Member partecipante, Boolean pagato) throws ObjectAlreadyContainedException;

	/**
	 * remove a member from the excursion
	 * 
	 * @param member
	 * @throws ObjectNotContainedException 
	 */
	public void removePartecipante(Member partecipante) throws ObjectNotContainedException;

	/**
	 * 
	 * @return a List<Member> contained the members who has to pay
	 */
	public List<Member> getNonPaganti();

	/**
	 * 
	 * @return a List<Member> contained the members who will take part in the
	 *         excursion
	 */
	public List<Member> getAllPartecipanti();

	/**
	 * 
	 * @return a List<Integer> contained the ids of the members who has NOT to
	 *         pay
	 */
	public List<Member> getAllPaganti();

	/**
	 * set true the payment boolean
	 * 
	 * @param member
	 *            that has paid
	 * @throws ObjectNotContainedException 
	 */
	public void setPagante(Member partecipante) throws ObjectNotContainedException;

	/**
	 * 
	 * @param member
	 * @return true if the member will take part in the excursion
	 */
	public boolean containMember(Member partecipante);

	/**
	 * 
	 * @param member
	 * @return true if the member had paid
	 * @throws ObjectNotContainedException 
	 */
	public boolean isPagante(Member partecipante) throws ObjectNotContainedException;

	/**
	 * 
	 * @return a List<Member> contained the members who has the birthday
	 */
	public List<Member> getAllBirthdays();

	/**
	 * 
	 * @return
	 */
	public String getName();

	/**
	 * 
	 * @param name
	 */
	public void setName(String name);
}
