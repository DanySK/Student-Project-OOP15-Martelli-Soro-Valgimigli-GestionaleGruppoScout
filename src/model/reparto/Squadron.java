package model.reparto;

import java.util.List;
import java.util.Map;

import control.exception.MemberSexException;
import model.exception.ObjectAlreadyContainedException;
import model.exception.ObjectNotContainedException;

/**
 * Class that describes a squadron providing all functions.
 * 
 * @author Riccardo Soro
 *
 */
public interface Squadron {

	/**
	 * set the name of the squadron
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
	 * @return a Boolean contained true if the sex is male, false is it is
	 *         famale
	 */
	Boolean getSesso();

	/**
	 * set the sex of the members of the squadron
	 * 
	 * @param sex
	 *            to set
	 */
	void setSesso(final Boolean sex);

	/**
	 * set the 1st boss of the squadron
	 * 
	 * @param capo
	 *            member to set as 1 leader
	 * @throws IllegalArgumentException
	 *             if the member is a already a leader
	 */
	void setCapoSq(final Member capo) throws IllegalArgumentException;

	/**
	 * 
	 * @return the 1 leader of the squadron
	 */
	Member getCapo();

	/**
	 * remove the 1 leader
	 * 
	 * 
	 * @throws ObjectNotContainedException
	 *             if the 1 leader is not present
	 */
	void removeCapo() throws ObjectNotContainedException;

	/**
	 * set the 2nd leader of the squadron
	 * 
	 * @param vicecapo
	 *            leader to set
	 * @throws IllegalArgumentException
	 *             id the member is already a leader
	 */
	void setVicecapoSq(final Member vicecapo) throws IllegalArgumentException;

	/**
	 * 
	 * @return the 2nd leader of the squadron
	 */
	Member getVice();

	/**
	 * remove the 2nd leader of the squadron
	 * 
	 * @throws ObjectNotContainedException
	 *             if the 2nd leader is not present
	 */
	void removeVice() throws ObjectNotContainedException;

	/**
	 * set the 3rd leader of the squadron
	 * 
	 * @param trice
	 *            leader to set
	 * @throws IllegalArgumentException
	 *             if the member is already a leader
	 */
	void setTriceSq(final Member trice) throws IllegalArgumentException;

	/**
	 * 
	 * @return @return the 3rd leader of the squadron
	 */
	Member getTrice();

	/**
	 * remove the 3rd leader of the squadron
	 * 
	 * @throws ObjectNotContainedException
	 *             if the 3rd leader is not present
	 */
	void removeTrice() throws ObjectNotContainedException;

	/**
	 * 
	 * @return a String contained notes about the cash of the squadron
	 */
	String getNoteCassa();

	/**
	 * set the notes about the cash of the squadron
	 * 
	 * @param note
	 *            to set
	 */
	void setNoteCassa(final String note);

	/**
	 * 
	 * @return a String contained notes about pots
	 */
	String getNoteBatteria();

	/**
	 * set a notes about pots
	 * 
	 * @param note
	 *            to set
	 */
	void setNoteBatteria(final String note);

	/**
	 * 
	 * @return a String contained notes about chancery
	 */
	String getNoteCancelleria();

	/**
	 * set a notes about chancery
	 * 
	 * @param note
	 *            to set
	 */
	void setNoteCancelleria(final String note);

	/**
	 * 
	 * @return a Map<Member, Roles> contained all the members and their roles
	 */
	Map<Member, Roles> getMembri();

	/**
	 * add a Member in the squadron
	 * 
	 * @param membro
	 *            member to add
	 * @param ruolo
	 *            roles of the member
	 * @throws MemberSexException
	 *             if the sex of the member and the sex of the squadron are
	 *             different
	 * @throws ObjectAlreadyContainedException
	 *             if the member is present in the squadron
	 */
	void addMembro(final Member membro, final Roles ruolo) throws MemberSexException, ObjectAlreadyContainedException;

	/**
	 * remove a member
	 * 
	 * @param membro
	 *            member to remove
	 * @throws ObjectNotContainedException
	 *             if the member is not present
	 */
	void removeMembro(final Member membro) throws ObjectNotContainedException;

	/**
	 * check if a member is present in the squadron
	 * 
	 * @param membro
	 *            member to check
	 * @return true if the member is contained,else false
	 */
	boolean containMember(final Member membro);

	/**
	 * set the cash of the squadron
	 * 
	 * @param cash
	 *            to set
	 */

	void setCash(final Float cash);

	/**
	 * 
	 * @return a float contained the cash of the squadron
	 */
	float getCash();

	/**
	 * 
	 * @return a List<Member> contained the members celebrating their birthday
	 *         today. a list without members will be returned if no one is
	 *         Celebrating birthday
	 */
	List<Member> getMemberCelebretingBirthday();

	/**
	 * check if the 1st leader is present
	 * 
	 * @return true if the 1st leader is present, else false
	 */
	boolean isCapoPresent();

	/**
	 * check if the 2nd leader is present
	 * 
	 * @return true if the 2nd leader is present, else false
	 */
	boolean isVicecapoPresent();

	/**
	 * check if the 3rd leader is present
	 * 
	 * @return true if the 3rd leader is present, else false
	 */
	boolean isTricecapoPresent();

}
