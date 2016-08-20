package model.exception;

public class IllegalPhoneNumberException extends Exception {

	/**
	 * Exception create to signal error in the phone numebr. This exception is
	 * launched the phone numer is wrong
	 * 
	 * @author Riccardo Soro
	 */
	private static final long serialVersionUID = 1L;
	private static final String MESSAGE = "Errore: il numero insetrito non è valido (10 cifre)";

	public String getMessage() {
		return IllegalPhoneNumberException.MESSAGE;
	}
}
