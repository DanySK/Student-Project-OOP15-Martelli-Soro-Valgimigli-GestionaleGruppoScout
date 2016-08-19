package model.reparto;

import java.time.LocalDate;
import java.time.Period;

public interface Person {
	/**
	 * 
	 * @param name
	 *            of the person
	 */
	void setName(String name);

	/**
	 * 
	 * @param surname
	 *            of the person
	 */
	void setSurname(String surname);

	/**
	 * 
	 * @return the difference between the year of birth and the actual year
	 */
	int getAnnata();

	/**
	 * 
	 * @return how is hold the person
	 */
	Period getHowIsHold();

	/**
	 * 
	 * @param birthday
	 *            of the person
	 */

	void setBirthday(LocalDate birthday);

	/**
	 * 
	 * @param sex
	 *            of the person (true if male, else false) to set
	 */
	void setSex(Boolean sex);

	/**
	 * 
	 * @return true if the person is male, else false
	 */
	Boolean getSex();

	/**
	 * 
	 * @return a String contained the name of the person
	 */
	String getName();

	/**
	 * 
	 * @return a String contained the surname of the person
	 */
	String getSurname();

	/**
	 * 
	 * @return a LocalDate contained the birthday of the person
	 */
	LocalDate getBirthday();

	/**
	 * 
	 * @return true if today is the birthday of the person
	 */
	Boolean isBirthday();

}
