package model.exception;

public class ObjectNotContainedException extends Exception {
	/**
	 * Exception create to signal error in a operation. This exception is
	 * launched the object is not contained
	 * 
	 * @author Riccardo Soro
	 */
	private static final long serialVersionUID = 1L;
	private static final String MESSAGE = "Errore: impossibile rimuovere un elemento non presente";

	public String getMessage() {
		return ObjectNotContainedException.MESSAGE;
	}
}
