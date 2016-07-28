package model;

import java.util.List;
import java.util.Map;

import control.exception.MemberSexException;
import control.exception.MoreLeadersNotPermitException;

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
		 * @throws MoreLeadersNotPermitException 
		 */
		public void setCapoSq(final Member capo) throws MoreLeadersNotPermitException;
		/**
		 * 
		 * @returna a String contained the name of 1st boss of the squadron
		 */
		public Member getCapo();
		/**
		 * remove the capo
		 * @return false if capo does not exist
		 */
		public Boolean removeCapo();
			
		
		/**
		 * set the 2nd boss of the squadron
		 * @param vicecapo
		 * @throws MoreLeadersNotPermitException 
		 */
		public void setVicecapoSq(final Member vicecapo) throws MoreLeadersNotPermitException;
		/**
		 * 
		 * @returna a String contained the name of 2nd boss of the squadron
		 */
		public Member getVice();
		/**
		 * remove the vicecapo
		 * @return false if vicecapo does not exist
		 */
		public Boolean removeVice();
		/**
		 * set the 3rd boss of the squadron
		 * @param trice
		 * @throws MoreLeadersNotPermitException 
		 */
		public void setTriceSq(final Member trice) throws MoreLeadersNotPermitException;
		/**
		 * 
		 * @return a String contained the name of the 3rd boss of the squadron
		 */
		public Member getTrice();
		/**
		 * remove the tricecapo
		 * @return false if tricecapo does not exist
		 */
		public Boolean removeTrice();
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
		/**
		 * 
		 * @return a List<Member> contained the members celebreting their birthday
		 * today.
		 * a list without members will be returned if no one is celebreting birthday
		 */
		public List<Member> getMemberCelebretingBirthday();
		
	}



