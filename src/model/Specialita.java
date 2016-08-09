package model;

import java.util.List;

import model.exception.ObjectAlreadyContainedException;
import model.exception.ObjectNotContainedException;

public interface Specialita {
	/**
	 * 
	 * @return
	 */
	List<Specialita> getAllNeededSpecialita ();
	/**
	 * 
	 * @param membro
	 * @return
	 */
	List<Specialita> getNeededSpecialita (Member membro);
	/**
	 * 
	 * @param list
	 * @return
	 */
	List<Specialita> getNeededSpecialita (List<Specialita> list);
	/**
	 * 
	 * @param membro
	 * @return
	 */
	boolean isSpacialitaPossible (Member membro);
	/**
	 * 
	 * @return
	 */
	String getName();
	/**
	 * 
	 * @param name
	 */
	void setName(String name);
	/**
	 * 
	 * @param specialita
	 * @throws ObjectAlreadyContainedException 
	 */
	void addNeededSpecialita (Specialita specialita) throws ObjectAlreadyContainedException;
	/**
	 * 
	 * @param specialita
	 * @throws ObjectNotContainedException 
	 */
	void removeNeededSpecialita (Specialita specialita) throws ObjectNotContainedException;
}
