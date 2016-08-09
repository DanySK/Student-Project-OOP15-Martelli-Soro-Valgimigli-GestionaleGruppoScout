package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import control.myUtil.myOptional;
import model.exception.ObjectAlreadyContainedException;
import model.exception.ObjectNotContainedException;

public class RepartoImpl implements Reparto,Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Squadron> squadriglie;
	private List<Member> aiutanti;
	private List<Member> membriSenzaSquadriglia;
	private List<Integer> idUsati;
	private myOptional<LocalDate> limitePerTasseAnnuali;
	private String name;
	private Member capoM;
	private Member capoF;

	public RepartoImpl(Member capoMaschio, Member capoFemmina, List<Member> aiutanti,String name) {
		this.aiutanti = aiutanti;
		this.capoF = capoFemmina;
		this.capoM = capoMaschio;
		this.squadriglie = new ArrayList<>();
		this.idUsati = new ArrayList<>();
		this.membriSenzaSquadriglia=new ArrayList<>();

		this.setName(name);
	}
	public void setDateToPay(LocalDate limit){
		this.limitePerTasseAnnuali = myOptional.of(limit);
	}
	public LocalDate getDateToPay(){
		if(this.limitePerTasseAnnuali.isPresent())
			return this.limitePerTasseAnnuali.get();
		else 
			return null;
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void addMembroSenzaSquadriglia(Member membro) throws ObjectAlreadyContainedException {
		if (!this.membriSenzaSquadriglia.add(membro)) throw new ObjectAlreadyContainedException();
	}
	public void removeMembroSenzaSquadriglia(Member membro) throws ObjectNotContainedException {
		if (!this.membriSenzaSquadriglia.remove(membro))throw new ObjectNotContainedException();
	}
	public List<Member> getMembriSenzaSquadriglia(){
		return this.membriSenzaSquadriglia;
	}
	public void spostaMembroInSquadriglia(Member membro, Roles ruolo, Squadron squadriglia) throws Exception {
		if(!this.membriSenzaSquadriglia.remove(membro)){
			throw new Exception("Pippo");
		}
		membro.setId(this.getFreeId());
		squadriglia.addMembro(membro, ruolo);

	}
	public void remveMembro(Member membro) throws ObjectNotContainedException{
		this.getSquadronOfMember(membro).removeMembro(membro);
		this.idUsati.remove(membro.getId());
	}
	public Squadron getSquadronOfMember(Member membro) throws ObjectNotContainedException{
		for (Squadron e:this.squadriglie){
			if (e.containMember(membro)){
				return e;
			}
		}
		throw new ObjectNotContainedException();
	}
	public void removeMemberFromSquadron (Member membro) throws ObjectNotContainedException{
		this.remveMembro(membro);
		this.membriSenzaSquadriglia.add(membro);
	}
	public Member getCapoM() {
		return capoM;
	}
	public List<Member> getMembersNotPaid(int anno){
		List<Member> tmp=new ArrayList<>();
		this.squadriglie.forEach(e->{
			new ArrayList<>(e.getMembri().keySet()).forEach(g->{
				if (!g.isTaxPaid(anno)){
					tmp.add(g);
				}
			});
		});
		return tmp;
	}
	public void setCapoM(Member capoMaschio) {
		this.capoM = capoMaschio;
	}

	public Member getCapoF() {
		return capoF;
	}

	public void setCapoF(Member capoFemmina) {
		this.capoF = capoFemmina;
	}

	public void addSquadron(Squadron squadriglia) throws ObjectAlreadyContainedException {
		if(!this.squadriglie.add(squadriglia)){
			throw new ObjectAlreadyContainedException();
		}
	}

	public void removeSquadron(Squadron squadriglia) throws ObjectNotContainedException {
		if (!this.squadriglie.remove(squadriglia)){
			throw new ObjectNotContainedException();
		}
	}

	public boolean containedSquadron(Squadron squadriglia) {
		return this.squadriglie.contains(squadriglia);
	}

	public List<Squadron> getAllSquadron() {
		return this.squadriglie;
	}
	
	public List<Member> getAllMember() {
		List<Member> tmp = new ArrayList<>();
		squadriglie.forEach(e -> {
			tmp.addAll(e.getMembri().keySet());
		});
		return tmp;
	}

	public void addAiutante(Member aiutante) throws ObjectAlreadyContainedException {
		if (!this.aiutanti.add(aiutante)){
			throw new ObjectAlreadyContainedException ();
		}
	}

	public void removeAiutanten(Member aiutante) throws ObjectNotContainedException {
		if (!this.aiutanti.remove(aiutante)){
			throw new ObjectNotContainedException();
		}
	}

	public boolean containedAiutante(Member aiutante) {
		return this.squadriglie.contains(aiutante);
	}

	public List<Member> getStaff() {
		List<Member> tmp = new ArrayList<>();
		tmp.add(capoF);
		tmp.add(capoM);
		tmp.addAll(aiutanti);
		return tmp;
	}
	private int getFreeId(){
		int tmp=1;
		while (true){
			if (!this.idUsati.contains(tmp)){
				this.idUsati.add(tmp);
				return tmp;
			}
			tmp ++;
		}
	}
}