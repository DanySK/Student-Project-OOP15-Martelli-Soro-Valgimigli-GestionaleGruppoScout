package model;

import java.time.LocalTime;
/**
 * 
 * @author Riccardo Soro
 * a simple class that allow to model an activity
 */
public interface Attivita {
	/**
	 * 
	 * @return the name
	 */
	 String getName();
	/**
	 * 
	 * @param name to set
	 */
	 void setName(String name);
	/**
	 * 
	 * @return the end time
	 */
	 LocalTime getOrarioInizio();
	/**
	 * 
	 * @param startTime to set
	 */
	 void setOrarioInizio(LocalTime startTime);
	/**
	 * 
	 * @return the start time
	 */
	 boolean haOrarioFine();
	/**
	 * 
	 * @return the end time
	 */
	 LocalTime getOrarioFine();
	/**
	 * 
	 * @param endTime to set
	 */
	 void setOrarioFine(LocalTime endTime);
}
