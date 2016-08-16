package model;

import java.time.LocalTime;
import java.util.List;

public interface EventiDiZona extends Excursion {
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

	/**
	 * 
	 * @return
	 */
	 List<Attivita> getAllAttivita();

	/**
	 * 
	 * @param orario
	 * @return
	 */
	 List<Attivita> getAllAttivitaInTime(LocalTime orario);

	/**
	 * 
	 * @param attivita
	 */
	 void addAttivita(Attivita attivita);

	/**
	 * 
	 * @param nomeAttivita
	 * @param orarioInizio
	 */
	 void addAttivita(String nomeAttivita, LocalTime orarioInizio);

	/**
	 * 
	 * @param nomeAttivita
	 * @param orarioInizio
	 * @param orarioFine
	 */
	 void addAttivita(String nomeAttivita, LocalTime orarioInizio, LocalTime orarioFine);

	/**
	 * 
	 * @param attivita
	 */
	 void removeAttivita(Attivita attivita);

	/**
	 * 
	 * @return
	 */
	 List<String> getOtherUnits();

	/**
	 * 
	 * @param name
	 */
	 void addOtherUnit(String name);

	/**
	 * 
	 * @param name
	 */
	 void removeOtherUnit(String name);

	/**
	 * 
	 * @param name
	 * @return
	 */
	 boolean containOtherUnit(String name);
}
