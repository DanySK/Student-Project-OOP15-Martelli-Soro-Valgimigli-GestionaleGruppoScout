package model.escursioni;

import java.time.LocalTime;
import java.util.List;

import model.exception.ObjectAlreadyContainedException;
import model.exception.ObjectNotContainedException;
import model.reparto.Reparto;
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
	 * @throws ObjectAlreadyContainedException if the object is contained
	 */
	
	
	 void addActivity(Attivita activity) throws ObjectAlreadyContainedException;

	/**
	 * 
	 * @param nameActivity to add
	 * @param startTime of the activity
	 * @throws ObjectAlreadyContainedException if the object is contained
	 */
	
	 void addActivity(String nameActivity, LocalTime startTime) throws ObjectAlreadyContainedException;
	 /**
	  * remove all the units from the list
	  */
	 void clearUnitsList();
	/**
	 * 
	 * @param nameActivity to add
	 * @param startTime of the activity
	 * @param endTime of the activity
	 * @throws ObjectAlreadyContainedException if the object is contained
	 */
	 void addActivity(String nameActivity, LocalTime startTime, LocalTime endTime) throws ObjectAlreadyContainedException;

	/**
	 * 
	 * @param activity to remove
	 * @throws ObjectNotContainedException if the object is notcontained
	 */
	 void removeActivity(Attivita activity) throws ObjectAlreadyContainedException, ObjectNotContainedException;

	/**
	 * 
	 * @return the name of the other units
	 */
	 List<String> getOtherUnits();

	/**
	 * 
	 * @param name of the unit to add
	 * @throws ObjectAlreadyContainedException 
	 */
	 void addOtherUnit(String name) throws ObjectAlreadyContainedException;

	/**
	 * 
	 * @param name of the unit to remove
	 * @throws ObjectNotContainedException 
	 */
	 void removeOtherUnit(String name) throws ObjectNotContainedException;

	/**
	 * 
	 * @param name of the unit
	 * @return true if the unit will take part to the event, however false
	 */
	 boolean containOtherUnit(String name);
}
