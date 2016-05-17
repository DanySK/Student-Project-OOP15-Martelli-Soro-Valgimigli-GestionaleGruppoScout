package control.exception;

/**
 *Exception create to signal error during creation of a memeber. 
 *This exception is launched when the sex of the member submitted is different from the Squadron sex
 *Each member in a squadron must have the same sex
 * @author lorenzo
 *
 */

public class MemberSexException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2113529536501172249L;
	private static final String message = "Errore: Il sesso del ragazzo\\a inserito\\a non corrisponde\n"
			+ "con quello della squadirglia";
	
	public String getMessage(){
		return this.message;
	}
}
