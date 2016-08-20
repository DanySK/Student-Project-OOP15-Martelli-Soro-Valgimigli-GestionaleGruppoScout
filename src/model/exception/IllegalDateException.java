package model.exception;

public class IllegalDateException extends Exception {
	/**
	 * Exception create to signal error in a date. This exception is launched
	 * the date is wrong
	 * 
	 * @author Riccardo Soro
	 */
	private static final long serialVersionUID = 1L;
	private static final String MESSAGE = "Errore: inserita data errata";

	public String getMessage() {
		return IllegalDateException.MESSAGE;
	}
}
