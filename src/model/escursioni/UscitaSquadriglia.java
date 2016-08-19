package model.escursioni;

import model.reparto.Squadron;

public interface UscitaSquadriglia extends Excursion {

	/**
	 * 
	 * @return
	 */
	Squadron getSquadriglia();

	/**
	 * 
	 * @param squadriglia
	 */
	void setReparto(Squadron squadriglia);
}
