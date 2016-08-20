package model.escursioni;

import model.reparto.Reparto;
/**
 * a simple class that extend Excursion which allow to model a camp
 * @author Riccardo Soro
 *
 */
public interface Campo extends Excursion {
	/**
	 * 
	 * @return the unit that will take part in the excursion
	 */
	 Reparto getReparto();

	/**
	 * 
	 * @param reparto that will take part in the excursion
	 */
	 void setReparto(Reparto reparto);
}
