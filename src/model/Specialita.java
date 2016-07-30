package model;

import java.util.List;

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
	 */
	void addNeededSpecialita (Specialita specialita);
	/**
	 * 
	 * @param specialita
	 */
	void removeNeededSpecialita (Specialita specialita);
}
