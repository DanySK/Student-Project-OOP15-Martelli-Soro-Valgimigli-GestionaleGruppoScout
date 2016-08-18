package model.reparto;

import java.util.List;
import java.util.Map;

import control.exception.MemberSexException;
import control.exception.MoreLeadersNotPermitException;
import model.exception.ObjectAlreadyContainedException;
import model.exception.ObjectNotContainedException;

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
		 void setNome(final String nome);
		/**
		 * 
		 * @return a String contained the name of the squadron
		 */
		 String getNome();
		/**
		 * 
		 * @return a Boolean contained true if the sex is male, false is it is famale
		 */
		 Boolean getSesso();
		/**
		 * set the sex of the members of the squadron
		 * @param sex
		 */
		 void setSesso(final Boolean sex);
		/**
		 * set the 1st boss of the squadron
		 * @param capo
		 * @throws MoreLeadersNotPermitException 
		 */
		 void setCapoSq(final Member capo) throws MoreLeadersNotPermitException;
		/**
		 * 
		 * @returna a String contained the name of 1st boss of the squadron
		 */
		 Member getCapo();
		/**
		 * remove the capo
		 * @return false if capo does not exist
		 * @throws ObjectNotContainedException 
		 */
		 void removeCapo() throws ObjectNotContainedException;
			
		
		/**
		 * set the 2nd boss of the squadron
		 * @param vicecapo
		 * @throws MoreLeadersNotPermitException 
		 */
		 void setVicecapoSq(final Member vicecapo) throws MoreLeadersNotPermitException;
		/**
		 * 
		 * @returna a String contained the name of 2nd boss of the squadron
		 */
		 Member getVice();
		/**
		 * remove the vicecapo
		 * @return false if vicecapo does not exist
		 * @throws ObjectNotContainedException 
		 */
		 void removeVice() throws ObjectNotContainedException;
		/**
		 * set the 3rd boss of the squadron
		 * @param trice
		 * @throws MoreLeadersNotPermitException 
		 */
		 void setTriceSq(final Member trice) throws MoreLeadersNotPermitException;
		/**
		 * 
		 * @return a String contained the name of the 3rd boss of the squadron
		 */
		 Member getTrice();
		/**
		 * remove the tricecapo
		 * @return false if tricecapo does not exist
		 * @throws ObjectNotContainedException 
		 */
		 void removeTrice() throws ObjectNotContainedException;
		/**
		 * 
		 * @return a String conatined notes about the cash of the squadron
		 */
		 String getNoteCassa();
		/**
		 * set the notes about the cash of the squadron
		 * @param note
		 */
		 void setNoteCassa(final String note);
		/**
		 * 
		 * @return a String contained notes about pots
		 */
		 String getNoteBatteria();
		/**
		 * set a notes about pots
		 * @param note
		 */
		 void setNoteBatteria(final String note);
		/**
		 * 
		 * @return a String contained notes about chancery
		 */
		 String getNoteCancelleria();
		/**
		 * set a notes about chancery
		 * @param note
		 */
		 void setNoteCancelleria(final String note);
		/**
		 * 
		 * @return a Map<Member, Roles> contained all the members and their roles
		 */
		 Map<Member, Roles> getMembri();
		/**
		 * add a Member
		 * @param membro
		 * @param ruolo
		 * @throws MemberSexException 
		 * @throws ObjectAlreadyContainedException 
		 */
		 void addMembro(final Member membro, final Roles ruolo) throws MemberSexException, ObjectAlreadyContainedException;
		/**
		 * 
		 * @param membro
		 * @throws ObjectNotContainedException 
		 */
		 void removeMembro (final Member membro) throws ObjectNotContainedException;
			
		
		/**
		 * 
		 * @param membro
		 * @return true if the member is contained
		 */
		 boolean containMember (final Member membro);
		
		
		/**
		 * set the cash of the squadron
		 * @param cash
		 */
		
		
		 void setCash(final Float cash);
		/**
		 * 
		 * @return a float contaied the cash of the squadron
		 */
		 float getCash();
		/**
		 * 
		 * @return a List<Member> contained the members celebreting their birthday
		 * today.
		 * a list without members will be returned if no one is celebreting birthday
		 */
		 List<Member> getMemberCelebretingBirthday();
		/**
		 * 
		 * @return
		 */
		 boolean isCapoPresent ();
		/**
		 * 
		 * @return
		 */
		 boolean isVicecapoPresent ();
		/**
		 * 
		 * @return
		 */
		 boolean isTricecapoPresent ();
		
	}



