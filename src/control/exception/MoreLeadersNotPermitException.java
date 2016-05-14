package control.exception;
/**
 *Exception create to signal error during creation of a memeber. 
 *This exception is launched when the member submitted covers a not free position in the hierarchy
 *Each member in a squadron must have a different position
 * @author lorenzo
 *
 */

public class MoreLeadersNotPermitException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8159666016468713151L;
	private static final String message = "Errore: inserito un ragazzo che copre una posizione gerarchica \n"
			+ "non disponibile";
	
	public String getMessage(){
		return this.message;
	}

}
