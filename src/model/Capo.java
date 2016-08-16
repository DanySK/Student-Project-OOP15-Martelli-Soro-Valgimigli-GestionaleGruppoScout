package model;
/**
 * 
 * @author Riccardo Soro
 *simple class that allow to model a leader
 */
public interface Capo extends Person {
	/**
	 * 
	 * @param phoneNumber to set
	 */
	 void setPhoneNumber(String phoneNumber);
	/**
	 * 
	 * @return the phone number
	 */
	 String getPhoneNumber();
}
