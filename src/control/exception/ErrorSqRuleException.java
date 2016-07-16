package control.exception;
/**
 *Exception create to signal error during creation of a member. 
 *This exception is launched when the member submitted covers a not free rule
 *Each member in a squadron must have a different rule
 * @author lorenzo
 *
 */
public class ErrorSqRuleException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4155904215251650093L;
	private static final String message = "Errore: inserito un membro che svolge un ruolo gia occupato";
	
	public String getMessage(){
		return message;
	}
}
