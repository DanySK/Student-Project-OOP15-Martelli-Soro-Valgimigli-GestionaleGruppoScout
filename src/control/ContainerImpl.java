package control;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import control.exception.MemberNotExistException;
import control.exception.SquadronNotExistException;
import model.escursioni.Campo;
import model.escursioni.EventiDiZona;
import model.escursioni.Excursion;
import model.escursioni.Gemellaggi;
import model.escursioni.Uscita;
import model.escursioni.UscitaSquadriglia;
import model.exception.ObjectNotContainedException;
import model.reparto.Member;
import model.reparto.Squadron;
import view.general_utility.WarningNotice;

public class ContainerImpl implements Container, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5561899153889400211L;
	final private List<Member> unit;
	final private List<Member> freeMember;
	final private List<Squadron> squadronActive;
	final private List<Excursion> excs;

	public ContainerImpl(final List<Squadron> sq, final List<Member> freeMember, final List<Excursion> exc) {
		this.unit = new ArrayList<>(freeMember);
		sq.forEach(e -> this.unit.addAll(e.getMembri().keySet()));
		this.freeMember = freeMember;
		this.squadronActive = sq;
		this.excs = exc;
	}

	@Override
	public List<Member> findMember(final String name) throws IllegalArgumentException {
		System.out.println(name);
		return this.unit.stream().filter(e -> e.getName().equalsIgnoreCase(name)).collect(Collectors.toList());
	}

	@Override
	public List<Member> membersIncomplete() {
		return this.unit.stream().filter(e -> e.isComplete()).collect(Collectors.toList());
	}

	@Override
	public List<Member> getMembers() {
		return this.unit;
	}

	@Override
	public Squadron findSquadron(final String name) {
		return this.squadronActive.stream().filter(e -> e.getNome().equalsIgnoreCase(name)).findFirst().get();
	}

	@Override
	public List<Member> members(final Predicate<? super Member> p) {
		return this.unit.stream().filter(p).collect(Collectors.toList());
	}

	@Override
	public List<Squadron> getSquadrons() {
		return this.squadronActive;
	}

	@Override
	public List<Member> getFreeMember() {
		return this.freeMember;
	}

	@Override
	public void removeMeberFromSquadron(final Member member, final Squadron sq)
			throws SquadronNotExistException, MemberNotExistException, ObjectNotContainedException {
		if (!this.squadronActive.contains(sq)) {
			throw new SquadronNotExistException();
		}
		if (!this.unit.contains(member) || !sq.containMember(member)) {
			throw new MemberNotExistException();
		}

		try {
			this.squadronActive.get(this.squadronActive.indexOf(sq)).removeMembro(member);
			this.freeMember.add(member);
		} catch (ObjectNotContainedException e) {
			new WarningNotice(e.getMessage());
		}
	}

	@Override
	public List<Excursion> getExcursion() {
		return this.excs;
	}

	@Override
	public List<Excursion> excursions(final Predicate<? super Excursion> p) {
		return this.excs.stream().filter(p).collect(Collectors.toList());
	}

	@Override
	public Excursion getExcursionNamed(final String name) {
		return this.excs.stream().filter(e -> e.getName().equalsIgnoreCase(name)).findFirst().get();
	}

	@Override
	public Member getMember(final String name, final String surname) {
		return this.unit.stream()
				.filter(e -> e.getName().equalsIgnoreCase(name) && e.getSurname().equalsIgnoreCase(surname)).findFirst()
				.get();
	}

	@Override
	public Uscita getExit(final String name) {
		Uscita tmp = null;
		for (final Excursion exc : this.excs) {
			if (exc.getName().equalsIgnoreCase(name) && exc instanceof Uscita) {
				tmp = (Uscita) exc;
			}
		}

		return tmp;
	}

	@Override
	public UscitaSquadriglia getExcursionSq(final String name) {
		UscitaSquadriglia tmp = null;
		for (final Excursion exc : this.excs) {
			if (exc.getName().equalsIgnoreCase(name) && exc instanceof UscitaSquadriglia) {
				tmp = (UscitaSquadriglia) exc;
			}
		}

		return tmp;
	}

	@Override
	public Gemellaggi getTwoUnitEvent(final String name) {
		Gemellaggi tmp = null;
		for (final Excursion exc : this.excs) {
			if (exc.getName().equalsIgnoreCase(name) && exc instanceof Gemellaggi) {
				tmp = (Gemellaggi) exc;
			}
		}

		return tmp;
	}

	@Override
	public EventiDiZona getLocalEvent(final String name) {
		EventiDiZona tmp = null;
		for (final Excursion exc : this.excs) {
			if (exc.getName().equalsIgnoreCase(name) && exc instanceof EventiDiZona) {
				tmp = (EventiDiZona) exc;
			}
		}

		return tmp;
	}

	@Override
	public Campo getCamp(final String name) {
		Campo tmp = null;
		for (final Excursion exc : this.excs) {
			if (exc.getName().equalsIgnoreCase(name) && exc instanceof Campo) {
				tmp = (Campo) exc;
			}
		}

		return tmp;
	}

	@Override
	public List<UscitaSquadriglia> getExcursionOfSquadron(final Squadron sq) {
		return this.excs.stream().filter(e -> e instanceof UscitaSquadriglia).map(e -> ((UscitaSquadriglia) e))
				.filter(e -> ((UscitaSquadriglia) e).getSquadriglia().equals(sq)).collect(Collectors.toList());
	}
	
	@Override
	public List<Member> getMemberNamed(final String name, final String surname) {
		return this.unit.stream()
				.filter(e -> e.getName().equalsIgnoreCase(name) && e.getSurname().equalsIgnoreCase(surname))
				.collect(Collectors.toList());
	}
	@Override
	public List<Member> getMemberWithName(final String name){
		return this.unit.stream()
				.filter(e -> e.getName().equalsIgnoreCase(name))
				.collect(Collectors.toList());
	}
	@Override
	public List<Member> getMemberWithSurname(final String Surname){
		return this.unit.stream()
				.filter(e -> e.getSurname().equalsIgnoreCase(Surname))
				.collect(Collectors.toList());
	}
	@Override
	public List<Member> getMemberWithNameFromList(final String name, final List<Member> list){
		return list.stream()
				.filter(e -> e.getName().equalsIgnoreCase(name))
				.collect(Collectors.toList());
	}
	@Override
	public List<Member> getMemberWithSurnameFromList(final String Surname,  final List<Member> list){
		return list.stream()
				.filter(e -> e.getSurname().equalsIgnoreCase(Surname))
				.collect(Collectors.toList());
	}
}



