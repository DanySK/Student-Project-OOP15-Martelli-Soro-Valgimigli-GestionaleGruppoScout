package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import control.exception.MemberSexException;
import control.exception.MoreLeadersNotPermitException;
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
	
	private myOptional<Member> capoSq;
	private myOptional<Member> viceSq;
	private myOptional<Member> triceSq;
	
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
		//map=new HashMap<>();
		capoSq=myOptional.empty();
		viceSq=myOptional.empty();
		triceSq=myOptional.empty();
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
		if (sex.equals(null)) throw new IllegalArgumentException();
		this.sessoSq = sex;
	}
	/**
	 * 
	 * @param capo
	 * @throws MoreLeadersNotPermitException 
	 */
	public void setCapoSq(final Member capo) throws MoreLeadersNotPermitException{
		if (capo.equals(null)) throw new IllegalArgumentException();
		if (this.capoSq.isPresent()) throw new MoreLeadersNotPermitException();
		this.capoSq= myOptional.of(capo);
	}
	/**
	 * 
	 * @return
	 */
	public Member getCapo(){
		return this.capoSq.get();
	}
	/**
	 * 
	 * @param vicecapo
	 * @throws MoreLeadersNotPermitException 
	 */
	public void setVicecapoSq(final Member vicecapo) throws MoreLeadersNotPermitException{
		if (vicecapo.equals(null)) throw new IllegalArgumentException();
		if (this.viceSq.isPresent()) throw new MoreLeadersNotPermitException();
		this.viceSq= myOptional.of(vicecapo);
	}
	/**
	 * 
	 * @return
	 */
	public Member getVice(){
		return this.viceSq.get();
	}
	/**
	 * 
	 * @param trice
	 * @throws MoreLeadersNotPermitException 
	 */
	public void setTriceSq(final Member trice) throws MoreLeadersNotPermitException{
		if (trice.equals(null)) throw new IllegalArgumentException();
		if (this.triceSq.isPresent()) throw new MoreLeadersNotPermitException();
		this.triceSq= myOptional.of(trice);
	}
	/**
	 * 
	 * @return
	 */
	public Member getTrice(){
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
		if (note.equals(null)) throw new IllegalArgumentException();
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
		if (note.equals(null)) throw new IllegalArgumentException();
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
		if (note.equals(null)) throw new IllegalArgumentException();
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
		if (membro.equals(null) || ruolo.equals(null)) throw new IllegalArgumentException();
		if (!membro.getSex().equals(this.sessoSq)) throw new MemberSexException();
		if (map.containsKey(membro)) return false;
		map.put(membro, ruolo);
		return true;
	}
	/**
	 * 
	 * @param cash
	 */
	public void setCash(final Float cash){
		if (cash.equals(null)) throw new IllegalArgumentException();
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
		if (membro.equals(null)) throw new IllegalArgumentException();
		if (map.containsKey(membro)){
			map.remove(membro);
			return true;
		}
		return false;
	}
	@Override
	public Boolean removeCapo() {
		if (this.getCapo().equals(myOptional.empty())) return false;
		this.capoSq=myOptional.empty();
		return true;
	}
	@Override
	public Boolean removeVice() {
		if (this.getVice().equals(myOptional.empty())) return false;
		this.viceSq=myOptional.empty();
		return true;
	}
	@Override
	public Boolean removeTrice() {
		if (this.getTrice().equals(myOptional.empty())) return false;
		this.triceSq=myOptional.empty();
		return true;
	}
	@Override
	public List<Member> getMemberCelebretingBirthday() {
		List<Member> tmp=new ArrayList<>();
		this.map.keySet().forEach(e->{
			if (e.isBirthday()) tmp.add(e);
		});
		return tmp;
	}
	


	
	
}

