package control.exception;

public class SquadronNotExistException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1729803280133186950L;
	private static final String message = "La squadriglie non è presente in questo reparto";
	
	public String getMessage(){
		return message;
	}
}
