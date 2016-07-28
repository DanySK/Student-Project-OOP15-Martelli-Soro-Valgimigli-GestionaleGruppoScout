package model;

import java.util.ArrayList;
import java.util.List;

import control.Container;

public class RepartoImpl implements Reparto{
	private List<Squadron> squadriglie;
	private List<Member> aiutanti;
	private Member capoM;
	private Member capoF;

	public RepartoImpl(Container container, Member capoMaschio, Member capoFemmina, List<Member> aiutanti) {
		this.squadriglie = container.getSquadrons();
		this.aiutanti = aiutanti;
		this.capoF = capoFemmina;
		this.capoM = capoMaschio;

	}

	public Member getCapoM() {
		return capoM;
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
		squadriglie.forEach(e->{
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