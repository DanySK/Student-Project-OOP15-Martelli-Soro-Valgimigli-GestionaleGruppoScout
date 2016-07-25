package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import control.myUtil.Pair;

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
	public Date getDateStart();

	/**
	 * 
	 * @return a Date contained the end date excursion
	 */
	public Date getDateEnd();

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
	 */
	public void setDateStart(Date dateStart);

	/**
	 * set the end date excursion
	 * 
	 * @param dateEnd
	 */
	public void setDateEnd(Date dateEnd);

	/**
	 * add a member in the excursion
	 * 
	 * @param idPartecipante
	 *            is the identifier of the member
	 * @param pagato,
	 *            must be true if the member has just paid
	 */
	public void addPartecipante(int idPartecipante, boolean pagato);

	/**
	 * remove a member from the excursion
	 * 
	 * @param idPartecipante
	 *            is the identifier of the member
	 */
	public void removePartecipante(int idPartecipante);

	/**
	 * 
	 * @return a List<Integer> contained the ids of the members who has to pay
	 */
	public List<Integer> getNonPaganti();

	/**
	 * 
	 *@return a List<Integer> contained the ids of the members who will take
	 *         part in the excursion
	 */
	public List<Integer> getAllPartecipanti();
	/**
	 * 
	 * @return a List<Integer> contained the ids of the members who has NOT to pay
	 */
	public List<Integer> getAllPaganti();
	/**
	 * set true the payment boolean
	 * @param idPartecipante of the member that has paid
	 */
	public void setPagante(Integer idPartecipante);
	/**
	 * 
	 * @param idPartecipante of the member
	 * @return true if the member will take part in the excursion
	 */
	public boolean containMember(Integer idPartecipante);
	/**
	 * 
	 * @param idPartecipante of the member
	 * @return true if the member had paid
	 */
	public boolean isPagante (Integer idPartecipante);
	/**
	 * 
	 * @param membri is a list of Member where search the ids of the members
	 * @return a List<Integer> contained the members who has the birthday
	 */
	public List<Integer> getAllBirthdays(List<MemberImpl> membri);
}
