package model;

import java.util.List;
/**
 * @author riccardo
 *
 */
/**
 * An interface modelling a member of the unit
 *
 */
public interface Member {
		


	 	/**
	 	 * Add a competence. 
	 	 * @return false if the competent is already contained
	 	 * @param competence
	 	 */
	   boolean addCompetence (String competence);
			
	   	/**
		 * @return a list of the competences of the member
		 */
		public List<String> getCompetence();
			
		 /**
		 * @return a list of the specialities of the member
		 */
		public List<String> getSpecialities();
		/**
		 * Remove a specialities from the member
		 * @param specialities
		 * @return false if the specialities is not contained
		 */
		public boolean removeSpecialities (String specialities);
		
		/**
	 	 * Add a speciality. 
	 	 * @return false if the speciality is already contained
	 	 * @param specialities
	 	 */
		
		public boolean addSpecialities(String specialities);
		/**
		 * 
		 * @param specialities
		 * @return true if the member contain the aspecialities
		 */
		
		public boolean containsSpecialities (String specialities);
		/**
		  * Remove a competence from the member
		 * @param competence
		 * @return false if the competence is not contained
		 */
		
		public boolean removeCompetence (String competence);
		/**
		 * 
		 * @param competence
		 * @return true if the member contain the competence
		 */
		
		public boolean containsCompetence (String competence);
		/**
		 * 
		 * @return the promise of the member
		 */
		
		public boolean getPromise();
		
		
		/** set the promise of the member
		 * 
		 * @param promessa
		 */
		public void setPromise(boolean promessa);
		
		/**
		 * 
		 * @return true if the member has a totem
		 */
		public boolean hasTotem();
		
		
		/**set the totem of the member
		 * 
		 * @param totem
		 */
		public void setTotem(String totem);
		/**
		 * 
		 * @return the totem of the member
		 */
		
		public String getTotem ();
		
		




}
