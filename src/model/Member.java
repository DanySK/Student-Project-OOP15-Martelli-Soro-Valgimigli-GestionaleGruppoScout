package model;

import java.time.LocalDate;
import java.util.List;

import control.myUtil.myOptional;
import model.exception.ObjectAlreadyContainedException;
import model.exception.ObjectNotContainedException;

/**
 * @author riccardo
 *
 */
/**
 * An interface modelling a member of the unit
 *
 */
public interface Member extends Person {
	/**
	 * 
	 * @return true if today is the birthday
	 */
	Boolean isBirthday();

	/**
	 * 
	 * @param anno
	 */
	void setTasse(Integer anno);

	/**
	 * 
	 * @param anno
	 * @return
	 */
	boolean isTaxPaid(Integer anno);

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
	 * Add a competence.
	 * 
	 * @return false if the competent is already contained
	 * @param competence
	 * @throws ObjectAlreadyContainedException
	 */
	void addCompetence(String competence) throws ObjectAlreadyContainedException;

	/**
	 * @return a list of the competences of the member
	 */
	List<String> getCompetence();

	/**
	 * @return a list of the specialities of the member
	 */
	List<Specialita> getSpecialities();

	/**
	 * Remove a specialities from the member
	 * 
	 * @param specialities
	 * @return false if the specialities is not contained
	 * @throws ObjectNotContainedException
	 */
	void removeSpecialities(Specialita specialities) throws ObjectNotContainedException;

	/**
	 * Add a speciality.
	 * 
	 * @return false if the speciality is already contained
	 * @param specialities
	 * @throws ObjectAlreadyContainedException
	 */

	void addSpecialities(Specialita specialities) throws ObjectAlreadyContainedException;

	/**
	 * 
	 * @param specialities
	 * @return true if the member contain the aspecialities
	 */

	boolean containsSpecialities(Specialita specialities);

	/**
	 * Remove a competence from the member
	 * 
	 * @param competence
	 * @throws ObjectNotContainedException
	 */

	void removeCompetence(String competence) throws ObjectNotContainedException;

	/**
	 * 
	 * @param competence
	 * @return true if the member contain the competence
	 */

	boolean isContainingCompetence(String competence);

	/**
	 * 
	 * @return the promise of the member
	 */

	Boolean getPromise();

	/**
	 * set the promise of the member
	 * 
	 * @param promessa
	 */
	void setPromise(boolean promessa);

	/**
	 * 
	 * @return true if the member has a totem
	 */
	boolean hasTotem();

	/**
	 * 
	 * @return
	 */
	int getId();

	/**
	 * 
	 * @param id
	 */
	void setId(int id);

	/**
	 * 
	 * @param totem
	 */
	void setTotem(String totem);

	/**
	 * 
	 * @return the totem of the member
	 */

	String getTotem();

	/**
	 * 
	 * @return true if there is a Tutor for the member
	 */
	boolean isComplete();

	/**
	 * 
	 * @return
	 */
	myOptional<Long> getTutorPhone();

	/**
	 * 
	 * @param phone
	 */
	void setTutorPhone(Long phone);

	/**
	 * 
	 * @return
	 */
	myOptional<String> getTutorName();

	/**
	 * 
	 * @param name
	 */
	void setTutorName(String name);

	/**
	 * 
	 * @return
	 */
	myOptional<Tutor> getTutor();

	/**
	 * 
	 * @param mail
	 */
	void setTutorMail(String mail);

	/**
	 * 
	 * @return
	 */
	myOptional<String> getTutorMail();

}
