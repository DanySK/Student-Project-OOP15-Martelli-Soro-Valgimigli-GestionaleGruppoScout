package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import control.exception.MemberSexException;
import model.exception.ObjectAlreadyContainedException;
import model.exception.ObjectNotContainedException;

public class RepartoImpl implements Reparto, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Squadron> squadriglie;
	private List<Capo> aiutanti;
	private List<Member> membriSenzaSquadriglia;
	private List<Integer> idUsati;
	private LocalDate limitePerTasseAnnuali;
	private String name;
	private Capo capoM;
	private Capo capoF;

	public RepartoImpl(Capo capoMaschio, Capo capoFemmina, List<Capo> aiutanti, String name) throws MemberSexException {
		if (capoMaschio.getSex().equals(capoFemmina.getSex()))
			throw new MemberSexException();
		this.aiutanti = aiutanti;
		this.capoF = capoFemmina;
		this.capoM = capoMaschio;
		this.squadriglie = new ArrayList<>();
		this.idUsati = new ArrayList<>();
		this.membriSenzaSquadriglia = new ArrayList<>();
		this.setName(name);
		this.limitePerTasseAnnuali=LocalDate.now().plusYears(1);
	}

	public void setDateToPay(LocalDate limit) {
		this.limitePerTasseAnnuali = limit;
	}

	public LocalDate getDateToPay() {
		return this.limitePerTasseAnnuali;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void addMembroSenzaSquadriglia(Member membro) throws ObjectAlreadyContainedException {
		if (!this.membriSenzaSquadriglia.add(membro)) {
			throw new ObjectAlreadyContainedException();
		}
	}

	public void removeMembroSenzaSquadriglia(Member membro) throws ObjectNotContainedException {
		if (!this.membriSenzaSquadriglia.remove(membro)) {
			throw new ObjectNotContainedException();
		}
	}

	public List<Member> getMembriSenzaSquadriglia() {
		return this.membriSenzaSquadriglia;
	}

	public void spostaMembroInSquadriglia(Member membro, Roles ruolo, Squadron squadriglia)
			throws ObjectNotContainedException, MemberSexException, ObjectAlreadyContainedException {
		if (!this.membriSenzaSquadriglia.remove(membro)) {
			throw new ObjectNotContainedException();
		}
		membro.setId(this.getFreeId());
		squadriglia.addMembro(membro, ruolo);

	}

	public void removeMembro(Member membro) throws ObjectNotContainedException {
		this.getSquadronOfMember(membro).removeMembro(membro);
		this.idUsati.remove((Integer) membro.getId());
	}

	public Squadron getSquadronOfMember(Member membro) throws ObjectNotContainedException {
		for (Squadron e : this.squadriglie) {
			if (e.containMember(membro)) {
				return e;
			}
		}
		throw new ObjectNotContainedException();
	}

	public void removeMemberFromSquadron(Member membro) throws ObjectNotContainedException {
		this.removeMembro(membro);
		this.membriSenzaSquadriglia.add(membro);
	}

	public Capo getCapoM() {
		return capoM;
	}

	public List<Member> getMembersNotPaid(int anno) {
		List<Member> tmp = new ArrayList<>();
		this.squadriglie.forEach(e -> {
			new ArrayList<>(e.getMembri().keySet()).forEach(g -> {
				if (!g.isTaxPaid(anno)) {
					tmp.add(g);
				}
			});
		});
		return tmp;
	}

	public void setCapoM(Capo capoMaschio) {
		this.capoM = capoMaschio;
	}

	public Capo getCapoF() {
		return capoF;
	}

	public void setCapoF(Capo capoFemmina) {
		this.capoF = capoFemmina;
	}

	public void addSquadron(Squadron squadriglia) throws ObjectAlreadyContainedException {
		if (!this.squadriglie.add(squadriglia)) {
			throw new ObjectAlreadyContainedException();
		}
	}

	public void removeSquadron(Squadron squadriglia) throws ObjectNotContainedException {
		if (!this.squadriglie.remove(squadriglia)) {
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

	public void addAiutante(Capo aiutante) throws ObjectAlreadyContainedException {
		if (!this.aiutanti.add(aiutante)) {
			throw new ObjectAlreadyContainedException();
		}
	}

	public void removeAiutante(Capo aiutante) throws ObjectNotContainedException {
		if (!this.aiutanti.remove(aiutante)) {
			throw new ObjectNotContainedException();
		}
	}

	public boolean isContainedAiutante(Capo aiutante) {
		return this.aiutanti.contains(aiutante);
	}

	public List<Capo> getStaff() {
		List<Capo> tmp = new ArrayList<>();
		tmp.add(capoF);
		tmp.add(capoM);
		tmp.addAll(aiutanti);
		return tmp;
	}

	private int getFreeId() {
		int tmp = 1;
		while (true) {
			if (!this.idUsati.contains(tmp)) {
				this.idUsati.add(tmp);
				return tmp;
			}
			tmp++;
		}
	}
}