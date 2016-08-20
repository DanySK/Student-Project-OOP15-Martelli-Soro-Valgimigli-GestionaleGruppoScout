package model.exception;

public class IllegalEmailException extends Exception {
	/**
	 * 
	 * Exception create to signal error in a email. This exception is launched
	 * the email is wrong
	 * 
	 * @author Riccardo Soro
	 */

	private static final long serialVersionUID = 1L;
	private static final String MESSAGE = "Errore: email non valida ";

	public String getMessage() {
		return IllegalEmailException.MESSAGE;
	}
}
