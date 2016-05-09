package control.exception;
/**
 * Exception creata per segnalare un eventuale errore nell'immisione dei dati di un ragazzo
 * Questa eccezione viene lanciata quando viene inserito un mebro che copre una posizione gerarchica
 * gia occupata da altri. 
 * Infatti ogni Squadriglia può ospitare solo un membro per ogni posizione gerarchica.
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
