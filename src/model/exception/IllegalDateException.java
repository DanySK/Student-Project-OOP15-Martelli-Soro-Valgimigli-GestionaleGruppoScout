package model.exception;

public class IllegalDateException extends Exception {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private static final String message = "Errore: inserita data già passata";
	
	public String getMessage(){
		return IllegalDateException.message;
	}
}
