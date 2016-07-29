package model;

import java.time.LocalTime;

public interface Attivita {
	/**
	 * 
	 * @return
	 */
	public String getName() ;
	/**
	 * 
	 * @param name
	 */
	public void setName(String name) ;
	/**
	 * 
	 * @return
	 */
	public LocalTime getOrarioInizio();
	/**
	 * 
	 * @param orarioInizio
	 */
	public void setOrarioInizio(LocalTime orarioInizio) ;
	/**
	 * 
	 * @return
	 */
	public boolean haOrarioFine ();
	/**
	 * 
	 * @return
	 */
	public LocalTime getOrarioFine();
	/**
	 * 
	 * @param orarioFine
	 */
	public void setOrarioFine(LocalTime orarioFine);
}
