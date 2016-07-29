package model;

import java.util.ArrayList;
import java.util.List;

import control.Container;
import control.exception.MemberSexException;

public class RepartoImpl implements Reparto {
	private List<Squadron> squadriglie;
	private List<Member> aiutanti;
	private List<Member> membriSenzaSquadriglia=new ArrayList<>();
	private String name;
	private Member capoM;
	private Member capoF;

	public RepartoImpl(Member capoMaschio, Member capoFemmina, List<Member> aiutanti,String name) {
		this.aiutanti = aiutanti;
		this.capoF = capoFemmina;
		this.capoM = capoMaschio;
		this.squadriglie = new ArrayList<>();

		this.setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void addMembroSenzaSquadriglia(Member membro) {
		this.membriSenzaSquadriglia.add(membro);
	}

	public void removeMembroSenzaSquadriglia(Member membro) {
		this.membriSenzaSquadriglia.remove(membro);
	}
	public List<Member> getMembriSenzaSquadriglia(){
		return this.membriSenzaSquadriglia;
	}
	public void spostaMembroInSquadriglia(Member membro, Roles ruolo, Squadron squadriglia) throws MemberSexException {
		squadriglia.addMembro(membro, ruolo);

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

	public void addSquadron(Squadron squadriglia) {
		this.squadriglie.add(squadriglia);
	}

	public void removeSquadron(Squadron squadriglia) {
		this.squadriglie.remove(squadriglia);
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

	public void addAiutante(Member aiutante) {
		this.aiutanti.add(aiutante);
	}

	public void removeAiutanten(Member aiutante) {
		this.aiutanti.remove(aiutante);
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
}