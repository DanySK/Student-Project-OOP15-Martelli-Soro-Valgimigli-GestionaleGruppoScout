package model;

import java.time.LocalDate;
import java.util.List;

import control.myUtil.MyOptional;
import model.exception.ObjectAlreadyContainedException;
import model.exception.ObjectNotContainedException;

/**
 * @author Riccardo Soro
 *
 */
/**
 * An interface modeling a member of the unit
 *
 */
public interface Member extends Person {
	/**
	 * 
	 * @return true if today is the birthday, else false
	 */
	Boolean isBirthday();

	/**
	 * 
	 * @param year
	 *            of the last payment to set
	 */
	void setTax(Integer year);

	/**
	 * 
	 * @param year
	 * @return true if the year of the last payment is the same, else false
	 */
	boolean isTaxPaid(Integer year);

	/**
	 * 
	 * @return the birthday
	 */
	LocalDate getBirthday();

	/**
	 * 
	 * @return the surname
	 */

	String getSurname();

	/**
	 * 
	 * @return the name
	 */
	String getName();

	/**
	 * @return a list of the specialties of the member
	 */
	List<String> getSpecialities();

	/**
	 * 
	 * 
	 * @param specialities
	 *            to remove
	 * @return false if the specialties is not contained
	 * @throws ObjectNotContainedException
	 */
	void removeSpecialities(String specialities) throws ObjectNotContainedException;

	/**
	 * 
	 * 
	 * @return false if the specialty is already contained
	 * @param specialities
	 *            to add
	 * @throws ObjectAlreadyContainedException
	 */

	void addSpecialities(String specialities) throws ObjectAlreadyContainedException;

	/**
	 * 
	 * @param specialities
	 * @return true if the member contain the specialties, else false
	 */

	boolean containsSpecialities(String specialities);

	/**
	 * 
	 * @return the promise of the member
	 */

	Boolean getPromise();

	/**
	 * 
	 * 
	 * @param promise
	 *            to set
	 */
	void setPromise(boolean promise);

	/**
	 * 
	 * @return true if the member has a totem,else false
	 */
	boolean hasTotem();

	/**
	 * 
	 * @return the id of the member
	 */
	int getId();

	/**
	 * 
	 * @param id
	 *            to set
	 */
	void setId(int id);

	/**
	 * 
	 * @param totem
	 *            to set
	 */
	void setTotem(String totem);

	/**
	 * 
	 * @return the totem of the member
	 */

	String getTotem();

	/**
	 * 
	 * @return true if there is a Tutor for the member,else false
	 */
	boolean isComplete();

	/**
	 * 
	 * @return the phone number of the tutor
	 */
	MyOptional<Long> getTutorPhone();

	/**
	 * 
	 * @param phone
	 *            number of the tutor to set
	 */
	void setTutorPhone(Long phone);

	/**
	 * 
	 * @return the name of the tutor
	 */
	MyOptional<String> getTutorName();

	/**
	 * 
	 * @param name
	 *            of the tutor
	 */
	void setTutorName(String name);

	/**
	 * 
	 * @return the tutor
	 */
	MyOptional<Tutor> getTutor();

	/**
	 * 
	 * @param mail
	 *            of the tutor to set
	 */
	void setTutorMail(String mail);

	/**
	 * 
	 * @return the mail of the tutor
	 */
	MyOptional<String> getTutorMail();

}
