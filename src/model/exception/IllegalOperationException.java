package model.exception;

public class IllegalOperationException  extends Exception{
	
    /**
 * 
 */
private static final long serialVersionUID = 1L;
	private static final String message = "Errore: operazione";
	
	public String getMessage(){
		return IllegalOperationException.message;
	}
}

