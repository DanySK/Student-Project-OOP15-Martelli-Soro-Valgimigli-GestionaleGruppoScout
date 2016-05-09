package control.exception;

/**
 * Exception creata per segnalare un eventuale errore nell'immisione dei dati di un ragazzo
 * Questa eccezione viene lanciata quando viene inserita una sessualità sbagliata. 
 * Infatti ogni Squadriglia può ospitare persone tutto dello stesso sesso.
 * @author lorenzo
 *
 */

public class MemberSexException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2113529536501172249L;
	private static final String message = "Errore: Il sesso del ragazzo\\a inserito\\a non corrisponde\n"
			+ "con quello della squadirglia";
	
	public String getMessage(){
		return this.message;
	}
}
