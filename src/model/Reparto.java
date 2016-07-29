package model;

import java.util.List;

import control.exception.MemberSexException;

public interface Reparto {
	/**
	 * 
	 * @return
	 */
	public Member getCapoM();

	/**
	 * 
	 * @param capoMaschio
	 */
	public void setCapoM(Member capoMaschio);

	/**
	 * 
	 * @return
	 */
	public Member getCapoF();

	/**
	 * 
	 * @param capoFemmina
	 */
	public void setCapoF(Member capoFemmina);

	/**
	 * 
	 * @param squadriglia
	 */
	public void addSquadron(Squadron squadriglia);

	/**
	 * 
	 * @param squadriglia
	 */
	public void removeSquadron(Squadron squadriglia);

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
	 */
	public void addAiutante(Member aiutante);

	/**
	 * 
	 * @param aiutante
	 */
	public void removeAiutanten(Member aiutante);

	/**
	 * 
	 * @param aiutante
	 * @return
	 */
	public boolean containedAiutante(Member aiutante);

	/**
	 * 
	 * @return
	 */
	public List<Member> getStaff();

	/**
	 * 
	 * @param membro
	 */
	public void addMembroSenzaSquadriglia(Member membro);

	/**
	 * 
	 * @param membro
	 */
	public void removeMembroSenzaSquadriglia(Member membro);
	/**
	 * 
	 * @param anno
	 * @return
	 */
	public List<Member> getMembersNotPaid(int anno);
	/**
	 * 
	 * @param membro
	 */
	public void remveMembro(Member membro);
	/**
	 * 
	 * @param membro
	 */
	public void removeMemberFromSquadron (Member membro);
	/**
	 * 
	 * @param membro
	 * @return
	 */
	public Squadron getSquadronOfMember(Member membro);
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
	 */
	
	public void spostaMembroInSquadriglia(Member membro, Roles ruolo, Squadron squadriglia) throws MemberSexException;

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
