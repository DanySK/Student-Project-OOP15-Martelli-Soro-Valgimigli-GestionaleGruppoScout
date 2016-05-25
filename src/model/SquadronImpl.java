package model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
/**
 * Class that describes a squadriglia providing all functions.
 * @author riccardo
 *
 */

public class SquadronImpl implements Serializable,Squadron{
	/**
	 * 
	 */
	private static final long serialVersionUID = -742316483975432020L;

	private Map<Member, Roles> roles;
	
	private String capoSq= "NULL";
	private String viceSq= "NULL";
	private String triceSq= "NULL";
	
	private String nomeSq;
	private Boolean sessoSq;	//true maschi, false donne
	
	private Float cash;
	private String noteCassa;
	private String noteCancelleria;
	private String noteBatteria;
	/**
	 * 
	 * @param nome
	 * @param sesso
	 */
	public SquadronImpl(final String nome, final boolean sesso){
		this.nomeSq = nome;
		this.sessoSq = sesso;
	}
	/**
	 * 
	 * @param nome
	 */
	public void setNome(final String nome){
		this.nomeSq = nome;
	}
	/**
	 * 
	 * @return
	 */
	public String getNome(){
		return this.nomeSq;
	}
	/**
	 * 
	 * @return
	 */
	public boolean getSesso(){
		return this.sessoSq;
	}
	/**
	 * 
	 * @param sex
	 */
	public void setSesso(final boolean sex){
		this.sessoSq = sex;
	}
	/**
	 * 
	 * @param capo
	 */
	public void setCapoSq(final String capo){
		this.capoSq = capo;
	}
	/**
	 * 
	 * @return
	 */
	public String getCapo(){
		return this.capoSq;
	}
	/**
	 * 
	 * @param vicecapo
	 */
	public void setVicecapoSq(final String vicecapo){
		this.viceSq = vicecapo;
	}
	/**
	 * 
	 * @return
	 */
	public String getVice(){
		return this.viceSq;
	}
	/**
	 * 
	 * @param trice
	 */
	public void setTriceSq(final String trice){
		this.triceSq = trice;
	}
	/**
	 * 
	 * @return
	 */
	public String getTrice(){
		return this.triceSq;
	}
	/**
	 * 
	 * @return
	 */
	public String getNoteCassa(){
		return this.noteCassa;
	}
	/**
	 * 
	 * @param note
	 */
	public void setNoteCassa(final String note){
		this.noteCassa = note;
	}
	/**
	 * 
	 * @return
	 */
	public String getNoteBatteria(){
		return this.noteBatteria;
	}
	/**
	 * 
	 * @param note
	 */
	public void setNoteBatteria(final String note){
		this.noteBatteria = note;
	}
	/**
	 * 
	 * @return
	 */
	public String getNoteCancelleria(){
		return this.noteCancelleria;
	}
	/**
	 * 
	 * @param note
	 */
	public void setNoteCancelleria(final String note){
		this.noteCancelleria = note;
	}
	/**                                                                                                                
	 * 
	 * @return
	 */
	public Map<Member, Roles> getMembri(){
		return new HashMap<Member, Roles>(this.roles);
	}
	/**
	 * 
	 * @param membro
	 * @param ruolo
	 */
	public void addMembro(final Member membro, final Roles ruolo){
		if(membro != null && ruolo != null){
			if(! this.roles.values().stream().anyMatch(e -> e.equals(ruolo))){
				this.roles.putIfAbsent(membro, ruolo);
			}
		}
	}
	/**
	 * 
	 * @param cash
	 */
	public void setCash(final float cash){
		this.cash = cash;
	}
	/**
	 * 
	 * @return
	 */
	public float getCash(){
		return cash;
	}
	
}

