package model.reparto;

import model.exception.IllegalPhoneNumberException;

/**
 * simple class that allow to model a leader
 * 
 * @author Riccardo Soro
 *
 */
public interface Capo extends Person {
	/**
	 * set the phone number of the leader
	 * 
	 * @param phoneNumber
	 *            to set
	 * @throws IllegalPhoneNumberException
	 *             if the number is not correct
	 */
	void setPhoneNumber(String phoneNumber) throws IllegalPhoneNumberException;

	/**
	 * 
	 * @return the phone number
	 */
	String getPhoneNumber();
}
