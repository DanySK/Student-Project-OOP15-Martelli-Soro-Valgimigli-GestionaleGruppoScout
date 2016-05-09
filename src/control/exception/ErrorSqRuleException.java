package control.exception;
/**
 * Exception creata per segnalare un eventuale errore nell'immisione dei dati di un ragazzo
 * Questa eccezione viene lanciata quando viene assegnato un ruolo ad un ragazzo gia svolto da 
 * qualcun'altro. 
 * Infatti ogni Squadriglia deve avere al massimo un membro per ogni ruolo (Non tutti i ruoli devono essere presenti).
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
		return this.message;
	}
}
