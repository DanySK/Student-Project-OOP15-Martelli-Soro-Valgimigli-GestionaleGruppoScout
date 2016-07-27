package control.exception;

public class MemberNotExistException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2959381902423759427L;
	private static final String message = "Il ragazzo selezionato non è presente in questo reparto";
	
	public String getMessage(){
		return message;
	}
}
