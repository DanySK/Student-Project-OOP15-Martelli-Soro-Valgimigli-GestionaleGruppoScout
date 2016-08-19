package model.escursioni;

import model.reparto.Reparto;

public interface Uscita extends Excursion{
	/**
	 * 
	 * @return
	 */
	Reparto getReparto();

	/**
	 * 
	 * @param reparto
	 */
	void setReparto(Reparto reparto);
}
