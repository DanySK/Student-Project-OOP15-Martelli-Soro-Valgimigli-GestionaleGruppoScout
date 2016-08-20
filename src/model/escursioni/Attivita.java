package model.escursioni;

import java.time.LocalTime;

/**
 * a simple class that allow to model an activity
 * 
 * @author Riccardo Soro
 * 
 */
public interface Attivita {
	/**
	 * 
	 * @return the name of the activity
	 */
	String getName();

	/**
	 * 
	 * @param name
	 *            to set
	 */
	void setName(String name);

	/**
	 * 
	 * @return the start time
	 */
	LocalTime getOrarioInizio();

	/**
	 * 
	 * @param startTime
	 *            to set
	 */
	void setOrarioInizio(LocalTime startTime);

	/**
	 * 
	 * @return true if the end time is presente,else false
	 */
	boolean haOrarioFine();

	/**
	 * 
	 * @return the end time
	 */
	LocalTime getOrarioFine();

	/**
	 * 
	 * @param end
	 *            time to set
	 */
	void setOrarioFine(LocalTime endTime);
}
