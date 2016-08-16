package model.exception;

public class IllegalDateException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private static final String MESSAGE = "Errore: inserita data errata";
	
	public String getMessage(){
		return IllegalDateException.MESSAGE;
	}
}
