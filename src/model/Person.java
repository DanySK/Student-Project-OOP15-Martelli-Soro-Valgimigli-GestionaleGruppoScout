package model;

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
	 * @return
	 */
	 int getAnnata();

	/**
	 * 
	 * @return
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
	 *            of the person (1 male)
	 */
	 void setSex(Boolean sex);

	/**
	 * 
	 * @return true if the person is male
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
	 * @return true inf today is the birthday of the peson
	 */
	 Boolean isBirthday();

}
