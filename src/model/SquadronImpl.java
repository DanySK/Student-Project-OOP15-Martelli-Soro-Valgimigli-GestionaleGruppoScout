package model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import control.exception.MemberSexException;
import control.myUtil.myOptional;
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

	private Map<Member,Roles> map;
	
	private myOptional<String> capoSq;
	private myOptional<String> viceSq;
	private myOptional<String> triceSq;
	
	private String nomeSq;
	private Boolean sessoSq;	//true maschi, false donne
	
	private Float cash;
	private myOptional<String> noteCassa;
	private myOptional<String> noteCancelleria;
	private myOptional<String> noteBatteria;
	/**
	 * 
	 * @param nome
	 * @param sesso
	 */
	public SquadronImpl(final String nome, final Boolean sesso){
		if (nome==null || sesso==null) throw new IllegalArgumentException();
		this.nomeSq = nome;
		this.sessoSq = sesso;
		this.cash=(float)0;
		map=new HashMap<>();
	}
	/**
	 * 
	 * @param nome
	 */
	public void setNome(final String nome){
		if (nome==null) throw new IllegalArgumentException();
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
	public Boolean getSesso(){
		return this.sessoSq;
	}
	/**
	 * 
	 * @param sex
	 */
	public void setSesso(final Boolean sex){
		if (sex==null) throw new IllegalArgumentException();
		this.sessoSq = sex;
	}
	/**
	 * 
	 * @param capo
	 */
	public void setCapoSq(final String capo){
		if (capo==null) throw new IllegalArgumentException();
		this.capoSq= myOptional.of(capo);
	}
	/**
	 * 
	 * @return
	 */
	public String getCapo(){
		return this.capoSq.get();
	}
	/**
	 * 
	 * @param vicecapo
	 */
	public void setVicecapoSq(final String vicecapo){
		if (vicecapo==null) throw new IllegalArgumentException();
		this.viceSq= myOptional.of(vicecapo);
	}
	/**
	 * 
	 * @return
	 */
	public String getVice(){
		return this.viceSq.get();
	}
	/**
	 * 
	 * @param trice
	 */
	public void setTriceSq(final String trice){
		if (trice==null) throw new IllegalArgumentException();
		this.triceSq= myOptional.of(trice);
	}
	/**
	 * 
	 * @return
	 */
	public String getTrice(){
		return this.triceSq.get();
	}
	/**
	 * 
	 * @return
	 */
	public String getNoteCassa(){
		return this.noteCassa.get();
	}
	/**
	 * 
	 * @param note
	 */
	public void setNoteCassa(final String note){
		if (note==null) throw new IllegalArgumentException();
		this.noteCassa = myOptional.of(note);
	}
	/**
	 * 
	 * @return
	 */
	public String getNoteBatteria(){
		return this.noteBatteria.get();
	}
	/**
	 * 
	 * @param note
	 */
	public void setNoteBatteria(final String note){
		if (note==null) throw new IllegalArgumentException();
		this.noteBatteria= myOptional.of(note);
	}
	/**
	 * 
	 * @return
	 */
	public String getNoteCancelleria(){
		return this.noteCancelleria.get();
	}
	/**
	 * 
	 * @param note
	 */
	public void setNoteCancelleria(final String note){
		if (note==null) throw new IllegalArgumentException();
		this.noteCancelleria= myOptional.of(note);
	}
	/**                                                                                                                
	 * 
	 * @return
	 */
	public Map<Member, Roles> getMembri(){
		return this.map;
	}
	/**
	 * 
	 * @param membro
	 * @param ruolo
	 */
	public boolean containMember (final Member membro){
		return this.map.containsKey(membro);
	}
	public Boolean addMembro(final Member membro, final Roles ruolo) throws MemberSexException{
		if (membro==null || ruolo==null) throw new IllegalArgumentException();
		if (membro.getSex()!=this.sessoSq) throw new MemberSexException();
		if (map.containsKey(membro)) return false;
		map.put(membro, ruolo);
		return true;
			
		
	}
	/**
	 * 
	 * @param cash
	 */
	public void setCash(final Float cash){
		if (cash==null) throw new IllegalArgumentException();
		if (cash<0) throw new IllegalArgumentException();
		this.cash = cash;
	}
	/**
	 * 
	 * @return
	 */
	public float getCash(){
		return cash;
	}
	@Override
	public Boolean removeMembro(Member membro) {
		if (membro==null) throw new IllegalArgumentException();
		if (map.containsKey(membro)){
			map.remove(membro);
			return true;
		}
		return false;
	}
	


	
	
}

