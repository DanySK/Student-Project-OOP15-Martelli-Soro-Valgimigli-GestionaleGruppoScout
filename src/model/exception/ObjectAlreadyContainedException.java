package model.exception;

public class ObjectAlreadyContainedException extends Exception{
	
	    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private static final String message = "Errore: inserito oggetto gi� presente";
		
		public String getMessage(){
			return ObjectAlreadyContainedException.message;
		}
	}

