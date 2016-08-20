/**
 * 
 */
package model.reparto;

import model.exception.IllegalOperationException;

/**
 * @author Riccardo Soro
 *
 */
/**
 * An interface modeling the path of the member
 *
 */
public interface Path {

	/**
	 * 
	 * @return a Integer contained the level of the path (1-3)
	 */
	Integer getLiv();

	/**
	 * 
	 * @return a String contained the level of the path
	 *         ("scoperta","competenza","responsabilità")
	 */

	String getLevel();

	/**
	 * 
	 * @return a String contained the school note
	 */

	String getSchool();

	/**
	 * 
	 * @param school
	 *            note to set
	 */

	void setSchool(String school);

	/**
	 * 
	 * @return a String contained the family note
	 */

	String getFamily();

	/**
	 * 
	 * @param family
	 *            note to set
	 */

	void setFamily(String family);

	/**
	 * 
	 * @return a String contained the relations note
	 */

	String getRelations();

	/**
	 * 
	 * @param relations
	 *            note to set
	 */

	void setRelations(String relations);

	/**
	 * 
	 * @return a String contained the faith note
	 */

	String getFaith();

	/**
	 * 
	 * @param faith
	 *            note to set
	 */

	void setFaith(String faith);

	/**
	 * increase the level of the path
	 * 
	 * @throws IllegalOperationException
	 *             if the level is 3
	 */

	void livUp() throws IllegalOperationException;

	/**
	 * decrease the level of the path
	 * 
	 * @throws IllegalOperationException
	 *             if the level is 1
	 */

	void livDown() throws IllegalOperationException;

}
