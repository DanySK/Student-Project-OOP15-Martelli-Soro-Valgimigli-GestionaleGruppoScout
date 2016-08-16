package model;

import java.time.LocalDate;
import java.util.List;

import model.exception.IllegalDateException;
import model.exception.ObjectAlreadyContainedException;
import model.exception.ObjectNotContainedException;

public class GemellaggiImpl extends ExcursionImpl implements Gemellaggi {

	private static final long serialVersionUID = 1L;
	private Reparto reparto;
	private final List<String> altriReparti;

	public GemellaggiImpl(final LocalDate dateStart, final LocalDate dateEnd, final Reparto reparto, final String name,
			final List<String> altriReparti) throws IllegalDateException {
		super(name, dateStart, reparto.getAllMember());
		this.check(dateStart, dateEnd);
		this.reparto = reparto;
		this.setDateEnd(dateEnd);
		this.altriReparti = altriReparti;
	}

	public GemellaggiImpl(final LocalDate dateStart, final int durata, final Reparto reparto, final String name,
			final List<String> altriReparti) throws IllegalDateException {
		super(name, dateStart, reparto.getAllMember());
		this.check(dateStart, dateStart.plusDays(durata - 1));
		this.reparto = reparto;
		this.setDateEnd(dateStart.plusDays(durata));
		this.altriReparti = altriReparti;
	}

	@Override
	public Reparto getReparto() {
		return reparto;
	}

	@Override
	public void setReparto(final Reparto reparto) {
		this.reparto = reparto;
	}

	@Override
	public List<String> getOtherUnits() {
		return this.altriReparti;
	}

	@Override
	public void addOtherUnit(final String name) throws ObjectAlreadyContainedException {
		if (this.altriReparti.contains(name)) {
			throw new ObjectAlreadyContainedException();
		}
		this.altriReparti.add(name);
	}

	@Override
	public void removeOtherUnit(final String name) throws ObjectNotContainedException {
		if (!this.altriReparti.remove(name)) {
			throw new ObjectNotContainedException();
		}
	}

	@Override
	public boolean containOtherUnit(final String name) {
		return this.altriReparti.contains(name);
	}

	@Override
	final protected void check(final LocalDate dateStart, final LocalDate dateEnd) throws IllegalDateException {
		if (dateStart.plusDays(1).isAfter(dateEnd)) {
			throw new IllegalDateException();
		}
	}

	@Override
	public void clearUnitsList() {
	this.altriReparti.clear();
	}
}
