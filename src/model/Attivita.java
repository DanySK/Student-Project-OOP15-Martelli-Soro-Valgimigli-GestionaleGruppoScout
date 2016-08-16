package model;

import java.time.LocalTime;

public interface Attivita {
	/**
	 * 
	 * @return
	 */
	 String getName() ;
	/**
	 * 
	 * @param name
	 */
	 void setName(String name) ;
	/**
	 * 
	 * @return
	 */
	 LocalTime getOrarioInizio();
	/**
	 * 
	 * @param orarioInizio
	 */
	 void setOrarioInizio(LocalTime orarioInizio) ;
	/**
	 * 
	 * @return
	 */
	 boolean haOrarioFine ();
	/**
	 * 
	 * @return
	 */
	 LocalTime getOrarioFine();
	/**
	 * 
	 * @param orarioFine
	 */
	 void setOrarioFine(LocalTime orarioFine);
}
