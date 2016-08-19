package control.exception;
/**
 *Exception create to signal error during creation of a member. 
 *This exception is launched when the member submitted has an age out of bound
 *Each member in a Unit must have an age between 12 to 17 age.
 * (the bounds can be different in different groups)
 * @author lorenzo
 *
 */

public class MemberAgeOutOfBoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4450878539454867623L;
	private static final String MESSAGE = "Errore: gli anni inseriti sono fuori range";
	
	public String getMessage(){
		return MESSAGE;
	}

}
