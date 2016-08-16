package model.exception;

public class IllegalYearsException extends Exception{
	
    /**
 * 
 */
private static final long serialVersionUID = 1L;
	private static final String MESSAGE = "Errore: gli anni del membro devono essere compresi tra 12 e 17";
	
	public String getMessage(){
		return IllegalYearsException.MESSAGE;
	}
}
