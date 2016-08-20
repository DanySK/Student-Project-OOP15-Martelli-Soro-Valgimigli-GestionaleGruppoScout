package control.exception;

public class EntityAlreadyExistsException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5219385976777936370L;
	private static final String MSG = "Errore: L'entit� inserita � gi� presente nell'elenco";
	
	public String getMessage(){
		return MSG;
	}

}
