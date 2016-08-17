package model.exception;

public class IllegalEmailException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private static final String MESSAGE = "Errore: email non valida ";
	
	public String getMessage(){
		return IllegalEmailException.MESSAGE;
	}
}
