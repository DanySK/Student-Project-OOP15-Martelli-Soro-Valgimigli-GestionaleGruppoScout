package control;

import java.util.List;
import java.util.function.Predicate;

import control.exception.MemberNotExistException;
import control.exception.SquadronNotExistException;
import model.Campo;
import model.EventiDiZona;
import model.Excursion;
import model.Gemellaggi;
import model.Member;
import model.Squadron;
import model.Uscita;
import model.UscitaSquadriglia;
import model.exception.ObjectNotContainedException;

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
	  List<Member> findMember(String name)throws IllegalArgumentException;
	 /**
	  * 
	  * @return
	  * Entire list of members
	  */
	  List<Member> getMembers();
	/**
	 * 
	 * @return
	 * 	All members which are incomplete
	 */
	  List<Member> membersIncomplete();
	/**
	 * 
	 * @param p 
	 * @return
	 * A list of member which match with the Predicate
	 */
	  List<Member> members(Predicate<? super Member> p);
	/**
	 * Research of a Squadron in the whole list
	 * @param name
	 * @return
	 * Squadron researched
	 */
	  Squadron findSquadron(String name);
	/**
	 * 
	 * @return
	 * the entire list of squadrons
	 */
	  List<Squadron> getSquadrons();
	/**
	 * 
	 * @param m
	 */
	
	  List<Member> getFreeMember();
	/**
	 * To detached member from his squadron
	 * @param member
	 * @param sq
	 * @throws SquadronNotExistException
	 * @throws MemberNotExistException
	 * @throws ObjectNotContainedException
	 */
	  void removeMeberFromSquadron(Member member, Squadron sq) 
			throws SquadronNotExistException, MemberNotExistException, ObjectNotContainedException;
	/**
	 * 
	 * @return
	 */
	  List<Excursion> getExcursion();
	/**
	 * 
	 * @param p
	 * @return
	 */
	  List<Excursion> excursions(Predicate<? super Excursion> p);
	/**
	 * 
	 * @param name
	 * @return
	 */
	  Excursion getExcursionNamed(String name);
	/**
	 * 
	 * @param name
	 * @param surname
	 * @return
	 */
	  Member getMember(String name, String surname);
	/**
	 * 
	 * @param name
	 * @return
	 */
	  Uscita getExit(String name);
	/**
	 * 
	 * @param name
	 * @return
	 */
	  UscitaSquadriglia getExcursionSq(String name);
	/**
	 * 
	 * @param name
	 * @return
	 */
	  Gemellaggi getTwoUnitEvent(String name);
	/**
	 * 
	 * @param name
	 * @return
	 */
	  EventiDiZona getLocalEvent(String name);
	/**
	 * 
	 * @param name
	 * @return
	 */
	  Campo getCamp(String name);

	

}
