/**
 * 
 */
package model;

import model.exception.IllegalOperationException;

/**
 * @author riccardo soro
 *
 */
/**
 * An interface modelling the path of the member
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
	 * @return a String contained the level of the path ("scoperta","competenza","responsabilità")
	 */
	
	 String getLevel();
	
	/**
	 * 
	 * @return  a String contained the school
	 */
	
	
	 String getSchool();
	
	/**
	 *  set the school of the path
	 * @param school
	 */
	
	
	 void setSchool (String school);
	
	/**
	 * 
	 * @return a String contained the family
	 */
	
	
	 String getFamily();
	
	/**
	  *  set the family of the path
	 * @param family
	 */
	
	
	 void setFamily (String family);
	
	 /**
	  * 
	  * @return a String contained the relations
	  */
	
	
	 String getRelations();
	
	/**
	 *  set the relations of the path
	 * @param relations
	 */
	
	
	 void setRelations (String relations);
	
	/**
	 * 
	 * @return a String contained the faith
	 */
	
	 String getFaith();
	
	/**
	 *  set the faith of the path 
	 * @param faith
	 */
	
	 void setFaith (String faith);
	
	/**
	 * increase the level of the path
	 * @throws IllegalOperationException 
	 */
	
	 void livUp() throws IllegalOperationException;
	
	/**
	 * decrease the level of the path
	 * @throws IllegalOperationException 
	 */
	
	 void livDown() throws IllegalOperationException;
	
	
	
	
	

}
