package control.exception;

public class EntityAlreadyExistsException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5219385976777936370L;
	private static final String msg = "Errore: L'entità inserita è già presente nell'elenco";
	
	public String getMessage(){
		return msg;
	}

}
