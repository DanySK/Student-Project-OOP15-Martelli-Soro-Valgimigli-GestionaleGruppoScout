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
	public Integer getLiv();
	
	
	/**
	 * 
	 * @return a String contained the level of the path ("scoperta","competenza","responsabilità")
	 */
	
	public String getLevel();
	
	/**
	 * 
	 * @return  a String contained the school
	 */
	
	
	public String getSchool();
	
	/**
	 *  set the school of the path
	 * @param school
	 */
	
	
	public void setSchool (String school);
	
	/**
	 * 
	 * @return a String contained the family
	 */
	
	
	public String getFamily();
	
	/**
	  *  set the family of the path
	 * @param family
	 */
	
	
	public void setFamily (String family);
	
	 /**
	  * 
	  * @return a String contained the relations
	  */
	
	
	public String getRelations();
	
	/**
	 *  set the relations of the path
	 * @param relations
	 */
	
	
	public void setRelations (String relations);
	
	/**
	 * 
	 * @return a String contained the faith
	 */
	
	public String getFaith();
	
	/**
	 *  set the faith of the path 
	 * @param faith
	 */
	
	public void setFaith (String faith);
	
	/**
	 * increase the level of the path
	 * @throws IllegalOperationException 
	 */
	
	public void livUp() throws IllegalOperationException;
	
	/**
	 * decrease the level of the path
	 * @throws IllegalOperationException 
	 */
	
	public void livDown() throws IllegalOperationException;
	
	
	
	
	

}
