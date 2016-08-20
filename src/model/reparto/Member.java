package model.reparto;

import java.time.LocalDate;
import java.util.List;

import control.myUtil.MyOptional;
import model.exception.IllegalEmailException;
import model.exception.IllegalPhoneNumberException;
import model.exception.ObjectAlreadyContainedException;
import model.exception.ObjectNotContainedException;

/**
 * An interface modeling a member of the unit
 * 
 * @author Riccardo Soro
 *
 */

public interface Member extends Person {
	/**
	 * 
	 * @return true if today is the birthday, else false
	 */
	Boolean isBirthday();

	/**
	 * set the year of the last payment
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
	 *             if the speciality is not present
	 */
	void removeSpecialities(String specialities) throws ObjectNotContainedException;

	/**
	 * 
	 * 
	 * @return false if the specialty is already contained
	 * @param specialities
	 *            to add
	 * @throws ObjectAlreadyContainedException
	 *             if the speciality is present
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
	 * set the promise
	 * 
	 * @param promise
	 *            to set
	 */
	void setPromise(boolean promise);

	/**
	 * set the totem
	 * 
	 * @return true if the member has a totem,else false
	 */
	boolean hasTotem();

	/**
	 * 
	 * @return the univocal id of the member
	 */
	int getId();

	/**
	 * 
	 * @param id
	 *            to set
	 */
	void setId(int id);

	/**
	 * set the totem
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
	 * @throws IllegalPhoneNumberException
	 *             if the number is not correct
	 */
	void setTutorPhone(Long phone) throws IllegalPhoneNumberException;

	/**
	 * 
	 * @return the name of the tutor
	 */
	MyOptional<String> getTutorName();

	/**
	 * set the name of the tutor
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
	 * set the mail of the tutor
	 * 
	 * @param mail
	 *            of the tutor to set
	 * @throws IllegalEmailException
	 *             if the email is not correct
	 */
	void setTutorMail(String mail) throws IllegalEmailException;

	/**
	 * 
	 * @return the mail of the tutor
	 */
	MyOptional<String> getTutorMail();

	/**
	 * 
	 * @return the path of the member
	 */
	Path getPath();

	/**
	 * set the path of the meber
	 * 
	 * @param path
	 *            to set
	 */
	void setPath(Path path);

}
