package model.reparto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import control.exception.MemberSexException;
import model.exception.IllegalDateException;
import model.exception.ObjectAlreadyContainedException;
import model.exception.ObjectNotContainedException;

public class RepartoImpl implements Reparto, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final List<Squadron> squadriglie;
	private final List<Capo> aiutanti;
	private final List<Member> membriSenzaSquadriglia;
	private final List<Integer> idUsati;
	private LocalDate limitePerTasseAnnuali;
	private String name;
	private Capo capoM;
	private Capo capoF;

	public RepartoImpl(final Capo capoMaschio, final Capo capoFemmina, final List<Capo> aiutanti, final String name)
			throws MemberSexException {
		if (capoMaschio.getSex().equals(capoFemmina.getSex())) {
			throw new MemberSexException();
		}
		this.aiutanti = aiutanti;
		this.capoF = capoFemmina;
		this.capoM = capoMaschio;
		this.squadriglie = new ArrayList<>();
		this.idUsati = new ArrayList<>();
		this.membriSenzaSquadriglia = new ArrayList<>();
		this.name = name;
		this.limitePerTasseAnnuali = LocalDate.now().plusYears(1);
	}

	@Override
	public void setDateToPay(final LocalDate limit) throws IllegalDateException {
		if (limit.isBefore(LocalDate.now())) {
			throw new IllegalDateException();
		}
		this.limitePerTasseAnnuali = limit;
	}

	@Override
	public LocalDate getDateToPay() {
		return this.limitePerTasseAnnuali;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(final String name) {
		this.name = name;
	}

	@Override
	public void addMembroSenzaSquadriglia(final Member membro) throws ObjectAlreadyContainedException {
		if (!this.membriSenzaSquadriglia.add(membro)) {
			throw new ObjectAlreadyContainedException();
		}
	}

	@Override
	public void removeMembroSenzaSquadriglia(final Member membro) throws ObjectNotContainedException {
		if (!this.membriSenzaSquadriglia.remove(membro)) {
			throw new ObjectNotContainedException();
		}
	}

	@Override
	public List<Member> getMembriSenzaSquadriglia() {
		return this.membriSenzaSquadriglia;
	}

	@Override
	public void spostaMembroInSquadriglia(final Member membro, final Roles ruolo, final Squadron squadriglia)
			throws ObjectNotContainedException, MemberSexException, ObjectAlreadyContainedException {
		if (!this.membriSenzaSquadriglia.contains(membro)) {
			throw new ObjectNotContainedException();
		}
		squadriglia.addMembro(membro, ruolo);
		membro.setId(this.getFreeId());
		this.membriSenzaSquadriglia.remove(membro);
	}

	@Override
	public void removeMembro(final Member membro) throws ObjectNotContainedException {
		this.getSquadronOfMember(membro).removeMembro(membro);
		this.idUsati.remove((Integer) membro.getId());
	}

	@Override
	public Squadron getSquadronOfMember(final Member membro) throws ObjectNotContainedException {
		for (final Squadron e : this.squadriglie) {
			if (e.containMember(membro)) {
				return e;
			}
		}
		throw new ObjectNotContainedException();
	}

	@Override
	public void removeMemberFromSquadron(final Member membro) throws ObjectNotContainedException {
		this.removeMembro(membro);
		this.membriSenzaSquadriglia.add(membro);
	}

	@Override
	public Capo getCapoM() {
		return capoM;
	}

	@Override
	public List<Member> getMembersNotPaid(final int anno) {
		final List<Member> tmp = new ArrayList<>();
		this.membriSenzaSquadriglia.forEach(z -> {
			if (!z.isTaxPaid(anno)) {
				tmp.add(z);
			}
		});
		this.squadriglie.forEach(e -> {
			new ArrayList<>(e.getMembri().keySet()).forEach(g -> {
				if (!g.isTaxPaid(anno)) {
					tmp.add(g);
				}
			});
		});
		return tmp;
	}

	@Override
	public void setCapoM(final Capo capoMaschio) {
		this.capoM = capoMaschio;
	}

	@Override
	public Capo getCapoF() {
		return capoF;
	}

	@Override
	public void setCapoF(final Capo capoFemmina) {
		this.capoF = capoFemmina;
	}

	@Override
	public void addSquadron(final Squadron squadriglia) throws ObjectAlreadyContainedException {
		if (!this.squadriglie.add(squadriglia)) {
			throw new ObjectAlreadyContainedException();
		}
	}

	@Override
	public void removeSquadron(final Squadron squadriglia) throws ObjectNotContainedException {
		if (!this.squadriglie.remove(squadriglia)) {
			throw new ObjectNotContainedException();
		}
	}

	@Override
	public boolean containedSquadron(final Squadron squadriglia) {
		return this.squadriglie.contains(squadriglia);
	}

	@Override
	public List<Squadron> getAllSquadron() {
		return this.squadriglie;
	}

	@Override
	public List<Member> getAllMember() {
		final List<Member> tmp = new ArrayList<>();
		squadriglie.forEach(e -> {
			tmp.addAll(e.getMembri().keySet());
		});
		tmp.addAll(this.membriSenzaSquadriglia);
		return tmp;
	}

	@Override
	public void addAiutante(final Capo aiutante) throws ObjectAlreadyContainedException {
		if (!this.aiutanti.add(aiutante)) {
			throw new ObjectAlreadyContainedException();
		}
	}

	@Override
	public void removeAiutante(final Capo aiutante) throws ObjectNotContainedException {
		if (!this.aiutanti.remove(aiutante)) {
			throw new ObjectNotContainedException();
		}
	}

	@Override
	public boolean isContainedAiutante(final Capo aiutante) {
		return this.aiutanti.contains(aiutante);
	}

	@Override
	public List<Capo> getStaff() {
		final List<Capo> tmp = new ArrayList<>();
		tmp.add(capoF);
		tmp.add(capoM);
		tmp.addAll(aiutanti);
		return tmp;
	}

	@Override
	public List<Capo> getAiutanti() {
		return this.aiutanti;
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

	@Override
	public List<Member> getAllMemberWithSquadron() {
		final List<Member> tmp = new ArrayList<>();
		this.squadriglie.forEach(e -> {
			tmp.addAll(e.getMembri().keySet());
		});
		return tmp;
	}
}