package model.exception;

public class IllegalOperationException  extends Exception{
	
    /**
 * 
 */
private static final long serialVersionUID = 1L;
	private static final String MESSAGE = "Errore: operazione non possibile";
	
	public String getMessage(){
		return IllegalOperationException.MESSAGE;
	}
}

