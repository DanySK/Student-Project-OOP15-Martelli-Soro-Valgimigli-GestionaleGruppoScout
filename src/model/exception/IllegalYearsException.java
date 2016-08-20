package model.exception;

public class IllegalYearsException extends Exception {

	/**
	 * Exception create to signal error in the creation of a member. This
	 * exception is launched the the member has not the right age
	 * 
	 * @author Riccardo Soro
	 */
	private static final long serialVersionUID = 1L;
	private static final String MESSAGE = "Errore: gli anni del membro devono essere compresi tra 12 e 17";

	public String getMessage() {
		return IllegalYearsException.MESSAGE;
	}
}
