package model.escursioni;

import model.reparto.Squadron;

/**
 * a simple class that allow to model an "Uscita" of a squadron
 * 
 * @author Riccardo Soro
 *
 */
public interface UscitaSquadriglia extends Excursion {

	/**
	 * 
	 * @return the squadron that will take part to the excursion
	 */
	Squadron getSquadriglia();

	/**
	 * set the squadron that will take part to the excursion
	 * 
	 * @param squadriglia
	 *            to set
	 */
	void setReparto(Squadron squadriglia);
}
