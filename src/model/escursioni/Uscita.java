package model.escursioni;

import model.reparto.Reparto;

/**
 * a class that allow to modeling an "Uscita"
 * 
 * @author Riccardo Soro
 *
 */
public interface Uscita extends Excursion {
	/**
	 * 
	 * @return the unit that will take part to the excursion
	 */
	Reparto getReparto();

	/**
	 * set the unit that will take part to the excursion
	 * 
	 * @param unit
	 *            to set
	 */
	void setReparto(Reparto reparto);
}
