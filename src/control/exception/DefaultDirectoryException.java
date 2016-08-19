package control.exception;

public class DefaultDirectoryException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3860989946554988240L;
	private final static String MESSAGE = "Errore durante la creazione della directory di default";
	
	public String getMessage(){
		return MESSAGE;
	}
}
