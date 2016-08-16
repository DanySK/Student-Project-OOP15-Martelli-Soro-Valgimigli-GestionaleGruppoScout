package model;

import java.time.LocalTime;
import java.util.List;
/**
 * 
 * @author Riccardo Soro
 *a class that allow to model a local event and its participants
 */
public interface EventiDiZona extends Excursion {
	/**
	 * 
	 * @return the unit of the event
	 */
	 Reparto getUnit();

	/**
	 * 
	 * @param unit to set
	 */
	 void setUnit(Reparto unit);

	/**
	 * 
	 * @return all activities
	 */
	 List<Attivita> getAllActivities();

	/**
	 * 
	 * @param time of the activities
	 * @return all activities in that time
	 */
	 List<Attivita> getAllActivitiesInTime(LocalTime time);

	/**
	 * 
	 * @param activity to add
	 */
	 void addActivity(Attivita activity);

	/**
	 * 
	 * @param nameActivity to add
	 * @param startTime of the activity
	 */
	 void addActivity(String nameActivity, LocalTime startTime);

	/**
	 * 
	 * @param nameActivity to add
	 * @param startTime of the activity
	 * @param endTime of the activity
	 */
	 void addActivity(String nameActivity, LocalTime startTime, LocalTime endTime);

	/**
	 * 
	 * @param activity to remove
	 */
	 void removeActivity(Attivita activity);

	/**
	 * 
	 * @return the name of the other units
	 */
	 List<String> getOtherUnits();

	/**
	 * 
	 * @param name of the unit to add
	 */
	 void addOtherUnit(String name);

	/**
	 * 
	 * @param name of the unit to remove
	 */
	 void removeOtherUnit(String name);

	/**
	 * 
	 * @param name of the unit
	 * @return true if the unit will take part to the event, however false
	 */
	 boolean containOtherUnit(String name);
}
