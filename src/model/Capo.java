package model;

import model.exception.IllegalPhoneNumberException;

/**
 * 
 * @author Riccardo Soro
 *simple class that allow to model a leader
 */
public interface Capo extends Person {
	/**
	 * 
	 * @param phoneNumber to set
	 * @throws IllegalPhoneNumberException 
	 */
	 void setPhoneNumber(String phoneNumber) throws IllegalPhoneNumberException;
	/**
	 * 
	 * @return the phone number
	 */
	 String getPhoneNumber();
}
