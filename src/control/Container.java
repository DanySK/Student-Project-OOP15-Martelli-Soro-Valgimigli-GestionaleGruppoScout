package control;

import java.util.List;
import java.util.function.Predicate;

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
	public List<Member> findMember(String Name)throws IllegalArgumentException;
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
	public List<Member> members(Predicate p);
	/**
	 * Research of a Squadron in the whole list
	 * @param name
	 * @return
	 * Squadron researched
	 */
	public Squadron findSquadron(String name);

}
