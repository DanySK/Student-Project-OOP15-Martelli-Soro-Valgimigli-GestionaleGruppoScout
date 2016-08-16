package model;

import java.time.LocalDate;
import java.util.List;

import control.exception.MemberSexException;
import model.exception.ObjectAlreadyContainedException;
import model.exception.ObjectNotContainedException;

public interface Reparto {
	/**
	 * 
	 * @return
	 */
	 Capo getCapoM();
	/**
	 * 
	 * @param limit
	 */
	 void setDateToPay(LocalDate limit);
	/**
	 * 
	 * @return
	 */
	 LocalDate getDateToPay();
	/**
	 * 
	 * @param capoMaschio
	 */
	
	 void setCapoM(Capo capoMaschio);

	/**
	 * 
	 * @return
	 */
	 Capo getCapoF();

	/**
	 * 
	 * @param capoFemmina
	 */
	 void setCapoF(Capo capoFemmina);

	/**
	 * 
	 * @param squadriglia
	 * @throws ObjectAlreadyContainedException 
	 */
	 void addSquadron(Squadron squadriglia) throws ObjectAlreadyContainedException;

	/**
	 * 
	 * @param squadriglia
	 * @throws ObjectNotContainedException 
	 */
	 void removeSquadron(Squadron squadriglia) throws ObjectNotContainedException;

	/**
	 * 
	 * @param squadriglia
	 * @return
	 */
	 boolean containedSquadron(Squadron squadriglia);

	/**
	 * 
	 * @return
	 */
	 List<Squadron> getAllSquadron();

	/**
	 * 
	 * @return
	 */
	 List<Member> getAllMember();
	 /**
	  * 
	  * @return
	  */
	 List<Member> getAllMemberWithSquadron();
	/**
	 * 
	 * @param aiutante
	 * @throws ObjectAlreadyContainedException 
	 */
	 void addAiutante(Capo aiutante) throws ObjectAlreadyContainedException;

	/**
	 * 
	 * @param aiutante
	 * @throws ObjectNotContainedException 
	 */
	 void removeAiutante(Capo aiutante) throws ObjectNotContainedException;

	/**
	 * 
	 * @param aiutante
	 * @return
	 */
	 boolean isContainedAiutante(Capo aiutante);

	/**
	 * 
	 * @return
	 */
	 List<Capo> getStaff();

	/**
	 * 
	 * @param membro
	 * @throws ObjectAlreadyContainedException 
	 */
	 void addMembroSenzaSquadriglia(Member membro) throws ObjectAlreadyContainedException;

	/**
	 * 
	 * @param membro
	 * @throws ObjectNotContainedException 
	 */
	 void removeMembroSenzaSquadriglia(Member membro) throws ObjectNotContainedException;
	/**
	 * 
	 * @param anno
	 * @return
	 */
	 List<Member> getMembersNotPaid(int anno);
	/**
	 * 
	 * @param membro
	 * @throws ObjectNotContainedException 
	 */
	 void removeMembro(Member membro) throws ObjectNotContainedException;
	/**
	 * 
	 * @param membro
	 * @throws ObjectNotContainedException 
	 */
	 void removeMemberFromSquadron (Member membro) throws ObjectNotContainedException;
	/**
	 * 
	 * @param membro
	 * @return
	 * @throws ObjectNotContainedException 
	 */
	 Squadron getSquadronOfMember(Member membro) throws ObjectNotContainedException;
	/**
	 * 
	 * @return
	 */
	 List<Member> getMembriSenzaSquadriglia();
	/**
	 * 
	 * @param membro
	 * @param ruolo
	 * @param squadriglia
	 * @throws MemberSexException
	 * @throws ObjectNotContainedException
	 * @throws ObjectAlreadyContainedException
	 */
	
	
	 void spostaMembroInSquadriglia(Member membro, Roles ruolo, Squadron squadriglia) throws MemberSexException,ObjectNotContainedException,ObjectAlreadyContainedException;

	/**
	 * 
	 * @return
	 */
	 String getName();

	/**
	 * 
	 * @param name
	 */
	 void setName(String name);

}
