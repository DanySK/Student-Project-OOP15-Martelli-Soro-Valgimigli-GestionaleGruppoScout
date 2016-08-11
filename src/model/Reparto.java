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
	public Capo getCapoM();
	/**
	 * 
	 * @param limit
	 */
	public void setDateToPay(LocalDate limit);
	/**
	 * 
	 * @return
	 */
	public LocalDate getDateToPay();
	/**
	 * 
	 * @param capoMaschio
	 */
	
	public void setCapoM(Capo capoMaschio);

	/**
	 * 
	 * @return
	 */
	public Capo getCapoF();

	/**
	 * 
	 * @param capoFemmina
	 */
	public void setCapoF(Capo capoFemmina);

	/**
	 * 
	 * @param squadriglia
	 * @throws ObjectAlreadyContainedException 
	 */
	public void addSquadron(Squadron squadriglia) throws ObjectAlreadyContainedException;

	/**
	 * 
	 * @param squadriglia
	 * @throws ObjectNotContainedException 
	 */
	public void removeSquadron(Squadron squadriglia) throws ObjectNotContainedException;

	/**
	 * 
	 * @param squadriglia
	 * @return
	 */
	public boolean containedSquadron(Squadron squadriglia);

	/**
	 * 
	 * @return
	 */
	public List<Squadron> getAllSquadron();

	/**
	 * 
	 * @return
	 */
	public List<Member> getAllMember();

	/**
	 * 
	 * @param aiutante
	 * @throws ObjectAlreadyContainedException 
	 */
	public void addAiutante(Capo aiutante) throws ObjectAlreadyContainedException;

	/**
	 * 
	 * @param aiutante
	 * @throws ObjectNotContainedException 
	 */
	public void removeAiutante(Capo aiutante) throws ObjectNotContainedException;

	/**
	 * 
	 * @param aiutante
	 * @return
	 */
	public boolean isContainedAiutante(Capo aiutante);

	/**
	 * 
	 * @return
	 */
	public List<Capo> getStaff();

	/**
	 * 
	 * @param membro
	 * @throws ObjectAlreadyContainedException 
	 */
	public void addMembroSenzaSquadriglia(Member membro) throws ObjectAlreadyContainedException;

	/**
	 * 
	 * @param membro
	 * @throws ObjectNotContainedException 
	 */
	public void removeMembroSenzaSquadriglia(Member membro) throws ObjectNotContainedException;
	/**
	 * 
	 * @param anno
	 * @return
	 */
	public List<Member> getMembersNotPaid(int anno);
	/**
	 * 
	 * @param membro
	 * @throws ObjectNotContainedException 
	 */
	public void removeMembro(Member membro) throws ObjectNotContainedException;
	/**
	 * 
	 * @param membro
	 * @throws ObjectNotContainedException 
	 */
	public void removeMemberFromSquadron (Member membro) throws ObjectNotContainedException;
	/**
	 * 
	 * @param membro
	 * @return
	 * @throws ObjectNotContainedException 
	 */
	public Squadron getSquadronOfMember(Member membro) throws ObjectNotContainedException;
	/**
	 * 
	 * @return
	 */
	public List<Member> getMembriSenzaSquadriglia();
	/**
	 * 
	 * @param membro
	 * @param ruolo
	 * @param squadriglia
	 * @throws MemberSexException
	 * @throws Exception 
	 */
	
	public void spostaMembroInSquadriglia(Member membro, Roles ruolo, Squadron squadriglia) throws MemberSexException, Exception;

	/**
	 * 
	 * @return
	 */
	public String getName();

	/**
	 * 
	 * @param name
	 */
	public void setName(String name);

}
