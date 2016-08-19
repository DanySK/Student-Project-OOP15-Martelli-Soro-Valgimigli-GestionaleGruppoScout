package model.escursioni;

import model.reparto.Reparto;

public interface Campo extends Excursion {
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
