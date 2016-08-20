package model.reparto;

import java.time.LocalDate;
import java.util.List;

import control.exception.MemberSexException;
import model.exception.IllegalDateException;
import model.exception.ObjectAlreadyContainedException;
import model.exception.ObjectNotContainedException;

/**
 * a class that allow to modeling an unit and its members
 * 
 * @author Riccardo Soro
 *
 */
public interface Reparto {
	/**
	 * 
	 * @return the male leader
	 */
	Capo getCapoM();

	/**
	 * set the limit date for the tax payment
	 * 
	 * @param limit
	 *            date
	 * @throws IllegalDateException
	 *             if the date is not correct
	 */
	void setDateToPay(LocalDate limit) throws IllegalDateException;

	/**
	 * 
	 * @return the limit date for the tax payment
	 */
	LocalDate getDateToPay();

	/**
	 * set the male leader
	 * 
	 * @param capoMaschio
	 *            to set
	 */

	void setCapoM(Capo capoMaschio);

	/**
	 * 
	 * @return the female leader
	 */
	Capo getCapoF();

	/**
	 * set the female leader
	 * 
	 * @param capoFemmina
	 *            to set
	 */
	void setCapoF(Capo capoFemmina);

	/**
	 * add a squadron in the unit
	 * 
	 * @param squadriglia
	 *            to add
	 * @throws ObjectAlreadyContainedException
	 *             if the squadron is present
	 */
	void addSquadron(Squadron squadriglia) throws ObjectAlreadyContainedException;

	/**
	 * remove a squadron from the unit
	 * 
	 * @param squadriglia
	 *            to remove
	 * @throws ObjectNotContainedException
	 *             if the squadron is not present
	 */
	void removeSquadron(Squadron squadriglia) throws ObjectNotContainedException;

	/**
	 * check if a squadron is present in the unit
	 * 
	 * @param squadriglia
	 *            to check
	 * @return true if the squadron is present, else false
	 */
	boolean containedSquadron(Squadron squadriglia);

	/**
	 * 
	 * @return all squadron contained in the unit
	 */
	List<Squadron> getAllSquadron();

	/**
	 * 
	 * @return all member with and without squadron contained in the unit
	 */
	List<Member> getAllMember();

	/**
	 * 
	 * @return all member with squadron contained in the unit
	 */
	List<Member> getAllMemberWithSquadron();

	/**
	 * add an helper
	 * 
	 * @param aiutante
	 *            to add
	 * @throws ObjectAlreadyContainedException
	 *             if the helper is contained
	 */
	void addAiutante(Capo aiutante) throws ObjectAlreadyContainedException;

	/**
	 * remove an helper
	 * 
	 * @param aiutante
	 *            to remove
	 * @throws ObjectNotContainedException
	 *             if the helper is not present
	 */
	void removeAiutante(Capo aiutante) throws ObjectNotContainedException;

	/**
	 * check if an helper is present
	 * 
	 * @param aiutante
	 *            to check
	 * @return true if the helper is container, else false
	 */
	boolean isContainedAiutante(Capo aiutante);

	/**
	 * 
	 * @return the staff composed of the male and female leader and the helpers
	 */
	List<Capo> getStaff();

	/**
	 * add a member without squadron
	 * 
	 * @param membro
	 *            to add
	 * @throws ObjectAlreadyContainedException
	 *             if the member without squadron is present
	 */
	void addMembroSenzaSquadriglia(Member membro) throws ObjectAlreadyContainedException;

	/**
	 * remove a member without squadron
	 * 
	 * @param membro
	 *            to remove
	 * @throws ObjectNotContainedException
	 *             if the member without squadron in not present
	 */
	void removeMembroSenzaSquadriglia(Member membro) throws ObjectNotContainedException;

	/**
	 * @param anno
	 *            to check
	 * @return a list contained the members that have not paid the tax
	 */
	List<Member> getMembersNotPaid(int anno);

	/**
	 * remove a member
	 * 
	 * @param membro
	 *            to remove
	 * @throws ObjectNotContainedException
	 *             if the member is not present
	 */
	void removeMembro(Member membro) throws ObjectNotContainedException;

	/**
	 * remove a member from a squadron
	 * 
	 * @param membro
	 *            to remove
	 * @throws ObjectNotContainedException
	 *             if the member is no present is squadrons
	 */
	void removeMemberFromSquadron(Member membro) throws ObjectNotContainedException;

	/**
	 * 
	 * @param membro
	 * @return the squadron of the member
	 * @throws ObjectNotContainedException
	 *             if the members is not present
	 */
	Squadron getSquadronOfMember(Member membro) throws ObjectNotContainedException;

	/**
	 * 
	 * @return all members without a squadron
	 */
	List<Member> getMembriSenzaSquadriglia();

	/**
	 * move a member in a squadron
	 * 
	 * @param membro
	 *            member to move
	 * @param ruolo
	 *            role in the squadron
	 * @param squadriglia
	 *            new squadron of the member
	 * @throws MemberSexException
	 *             if the squadron sex and the sex of the member are differents
	 * @throws ObjectNotContainedException
	 *             if the member is not present
	 * @throws ObjectAlreadyContainedException
	 *             if the member is present in the squadron
	 */

	void spostaMembroInSquadriglia(Member membro, Roles ruolo, Squadron squadriglia)
			throws MemberSexException, ObjectNotContainedException, ObjectAlreadyContainedException;

	/**
	 * 
	 * @return the name of the unit
	 */
	String getName();

	/**
	 * set the name of the unit
	 * 
	 * @param name
	 *            to set
	 */
	void setName(String name);

	/**
	 * 
	 * @return a list of the helper
	 */
	List<Capo> getAiutanti();

}
