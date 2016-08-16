package control.exception;

public class ProjectFilesCreationException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7415009559154631350L;
	private final static String MESSAGE = "Errore durante la creazione del file di impostazione";
	
	public String getMessage(){
		return MESSAGE;
	}
}
