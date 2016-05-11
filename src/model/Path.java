/**
 * 
 */
package scout;

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
	 * @return false if the level is 3
	 */
	
	public boolean livUp();
	
	/**
	 * decrease the level of the path
	 * @return false if the level is 1
	 */
	
	public boolean livDown();
	
	
	
	
	

}
