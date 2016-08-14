package model;

import java.time.LocalTime;
import java.util.List;

public interface EventiDiZona extends Excursion {
	/**
	 * 
	 * @return
	 */
	public Reparto getReparto();

	/**
	 * 
	 * @param reparto
	 */
	public void setReparto(Reparto reparto);

	/**
	 * 
	 * @return
	 */
	public List<Attivita> getAllAttivita();

	/**
	 * 
	 * @param orario
	 * @return
	 */
	public List<Attivita> getAllAttivitaInTime(LocalTime orario);

	/**
	 * 
	 * @param attivita
	 */
	public void addAttivita(Attivita attivita);

	/**
	 * 
	 * @param nomeAttivita
	 * @param orarioInizio
	 */
	public void addAttivita(String nomeAttivita, LocalTime orarioInizio);

	/**
	 * 
	 * @param nomeAttivita
	 * @param orarioInizio
	 * @param orarioFine
	 */
	public void addAttivita(String nomeAttivita, LocalTime orarioInizio, LocalTime orarioFine);

	/**
	 * 
	 * @param attivita
	 */
	public void removeAttivita(Attivita attivita);

	/**
	 * 
	 * @return
	 */
	public List<String> getOtherUnits();

	/**
	 * 
	 * @param name
	 */
	public void addOtherUnit(String name);

	/**
	 * 
	 * @param name
	 */
	public void removeOtherUnit(String name);

	/**
	 * 
	 * @param name
	 * @return
	 */
	public boolean containOtherUnit(String name);
}
