package model.exception;

public class ObjectNotContainedException  extends Exception{
	
    /**
 * 
 */
private static final long serialVersionUID = 1L;
	private static final String MESSAGE = "Errore: impossibile rimuovere un elemento non presente";
	
	public String getMessage(){
		return ObjectNotContainedException.MESSAGE;
	}
}


