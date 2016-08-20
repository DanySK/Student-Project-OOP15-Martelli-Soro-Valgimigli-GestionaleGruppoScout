package model.exception;

public class ObjectAlreadyContainedException extends Exception {

	/**
	 * Exception create to signal error in a operation. This exception is
	 * launched the object is already contained
	 * 
	 * @author Riccardo Soro
	 */
	private static final long serialVersionUID = 1L;
	private static final String MESSAGE = "Errore: impossibile inserire un oggetto già presente";

	public String getMessage() {
		return ObjectAlreadyContainedException.MESSAGE;
	}
}
