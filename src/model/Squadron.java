package model;

import java.util.Map;

import control.exception.MemberSexException;

public interface Squadron {
	
	
	/**
	 * Class that describes a squadriglia providing all functions.
	 * @author riccardo
	 *
	 */

	
		/**set the name of the squadron
		 * 
		 * @param nome
		 */
		public void setNome(final String nome);
		/**
		 * 
		 * @return a String contained the name of the squadron
		 */
		public String getNome();
		/**
		 * 
		 * @return a Boolean contained true if the sex is male, false is it is famale
		 */
		public Boolean getSesso();
		/**
		 * set the sex of the members of the squadron
		 * @param sex
		 */
		public void setSesso(final Boolean sex);
		/**
		 * set the 1st boss of the squadron
		 * @param capo
		 */
		public void setCapoSq(final String capo);
		/**
		 * 
		 * @returna a String contained the name of 1st boss of the squadron
		 */
		public String getCapo();
		/**
		 * set the 2nd boss of the squadron
		 * @param vicecapo
		 */
		public void setVicecapoSq(final String vicecapo);
		/**
		 * 
		 * @returna a String contained the name of 2nd boss of the squadron
		 */
		public String getVice();
		/**
		 * set the 3rd boss of the squadron
		 * @param trice
		 */
		public void setTriceSq(final String trice);
		/**
		 * 
		 * @return a String contained the name of the 3rd boss of the squadron
		 */
		public String getTrice();
		/**
		 * 
		 * @return a String conatined notes about the cash of the squadron
		 */
		public String getNoteCassa();
		/**
		 * set the notes about the cash of the squadron
		 * @param note
		 */
		public void setNoteCassa(final String note);
		/**
		 * 
		 * @return a String contained notes about pots
		 */
		public String getNoteBatteria();
		/**
		 * set a notes about pots
		 * @param note
		 */
		public void setNoteBatteria(final String note);
		/**
		 * 
		 * @return a String contained notes about chancery
		 */
		public String getNoteCancelleria();
		/**
		 * set a notes about chancery
		 * @param note
		 */
		public void setNoteCancelleria(final String note);
		/**
		 * 
		 * @return a Map<Member, Roles> contained all the members and their roles
		 */
		public Map<Member, Roles> getMembri();
		/**
		 * add a Member
		 * @param membro
		 * @param ruolo
		 * @throws MemberSexException 
		 */
		public Boolean addMembro(final Member membro, final Roles ruolo) throws MemberSexException;
		/**
		 * 
		 * @param membro
		 * @return flase if the member is not contained
		 */
		public Boolean removeMembro (final Member membro);
			
		
		/**
		 * 
		 * @param membro
		 * @return true if the member is contained
		 */
		public boolean containMember (final Member membro);
		
		
		/**
		 * set the cash of the squadron
		 * @param cash
		 */
		
		
		public void setCash(final Float cash);
		/**
		 * 
		 * @return a float contaied the cash of the squadron
		 */
		public float getCash();
		
	}



