package model.exception;

public class IllegalPhoneNumberException extends Exception{
	
    /**
 * 
 */
private static final long serialVersionUID = 1L;
	private static final String MESSAGE = "Errore: il numero insetrito non è valido (10 cifre)";
	
	public String getMessage(){
		return IllegalPhoneNumberException.MESSAGE;
	}
}
