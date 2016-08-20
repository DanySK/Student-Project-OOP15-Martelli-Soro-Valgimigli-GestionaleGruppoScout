package model.exception;

public class IllegalOperationException extends Exception {
	/**
	 * Exception create to signal error in a operation. This exception is
	 * launched the operation is not premitted
	 * 
	 * @author Riccardo Soro
	 */
	private static final long serialVersionUID = 1L;
	private static final String MESSAGE = "Errore: operazione non possibile";

	public String getMessage() {
		return IllegalOperationException.MESSAGE;
	}
}
