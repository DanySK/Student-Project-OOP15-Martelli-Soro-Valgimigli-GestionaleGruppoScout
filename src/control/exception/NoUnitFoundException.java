package control.exception;

public class NoUnitFoundException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6640057458701756522L;
	private final static String message = "Errore. La cartella di salvataggio non contiene\n"
			+ "Reparti salvati";
	
	public String getMessage(){
		return message;
	}

}
