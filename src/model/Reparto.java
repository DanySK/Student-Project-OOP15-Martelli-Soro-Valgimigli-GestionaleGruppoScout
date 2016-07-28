package model;

import java.util.List;

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
}
