package model;

import java.time.LocalDate;

public interface Person {
	/**
	 * 
	 * @param name of the person
	 */
	public void setName (String name);
	/**
	 * 
	 * @param surname of the person
	 */
	public void setSurname (String surname);
	/**
	 * 
	 * @param birthday of the person
	 */
	public void setBirthday (LocalDate birthday);
	/**
	 * 
	 * @param sex of the person (1 male)
	 */
	public void setSex (Boolean sex);
	/**
	 * 
	 * @return true if the person is male
	 */
	public Boolean getSex();
	/**
	 * 
	 * @return a String contained the name of the person
	 */
	public String getName ();
	/**
	 * 
	 * @return a String contained the surname of the person
	 */
	public String getSurname ();
	/**
	 * 
	 * @return a LocalDate contained the birthday of the person
	 */
	public LocalDate getBirthday ();
	/**
	 * 
	 * @return true inf today is the birthday of the peson
	 */
	public Boolean isBirthday();
	
}
