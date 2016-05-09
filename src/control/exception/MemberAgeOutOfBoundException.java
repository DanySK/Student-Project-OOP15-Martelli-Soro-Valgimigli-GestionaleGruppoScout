package control.exception;
/**
 * Exception creata per segnalare un eventuale errore nell'immisione dei dati di un ragazzo
 * Questa eccezione viene lanciata quando viene inserita un eta sbagliata. 
 * Infatti  nel reparto ci sono solo ragazzi di età compresa tra gli 11 e i 17 anni
 * @author lorenzo
 *
 */

public class MemberAgeOutOfBoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4450878539454867623L;
	private static final String message = "Errore: gli anni inseriti sono fuori range";
	
	public String getMessage(){
		return this.message;
	}

}
