package control;

import java.util.List;
import java.util.function.Predicate;

import control.exception.EntityAlreadyExistsException;
import control.exception.MemberNotExistException;
import control.exception.SquadronNotExistException;
import model.Member;
import model.Squadron;

/**
 * Class that contains infos, entities and relations.
 * This class implements methods to search entities
 * @author Valgio
 *
 */
public interface Container {

	/**
	 * Research of a member in the whole list. If two or more members match a special graphic
	 * interface will be called.
	 * @param Name
	 * @return
	 * List of Members named Name
	 * @throws IllegalArgumentException
	 */
	public List<Member> findMember(String name)throws IllegalArgumentException;
	 /**
	  * 
	  * @return
	  * Entire list of members
	  */
	public List<Member> getMembers();
	/**
	 * 
	 * @return
	 * 	All members which are incomplete
	 */
	public List<Member> membersIncomplete();
	/**
	 * 
	 * @param p 
	 * @return
	 * A list of member which match with the Predicate
	 */
	public List<Member> members(Predicate<? super Member> p);
	/**
	 * Research of a Squadron in the whole list
	 * @param name
	 * @return
	 * Squadron researched
	 */
	public Squadron findSquadron(String name);
	/**
	 * 
	 * @return
	 * the entire list of squadrons
	 */
	public List<Squadron> getSquadrons();
	/**
	 * 
	 * @param m
	 */
	public void addMember(Member m) throws EntityAlreadyExistsException;
	/**
	 * 
	 * @param sq
	 */
	public void addSquadron(Squadron sq)throws EntityAlreadyExistsException;
	/**
	 * Method for removing member
	 * @param mbr
	 */
	public void removeMember(Member mbr) throws MemberNotExistException;
	/**
	 * Method for removing squadron
	 * @param sq
	 */
	public void removeSquadron(Squadron sq)throws SquadronNotExistException;
	/**
	 * Provides a list of member which they are in no Squadron
	 * @return
	 */
	public List<Member> getFreeMember();

}
