package model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import model.exception.IllegalDateException;

public class EventiDiZonaImpl extends ExcursionImpl implements EventiDiZona {

	private static final long serialVersionUID = 1L;
	private Reparto reparto;
	private final List<String> altriReparti;
	private final List<Attivita> attivita = new ArrayList<>();

	public EventiDiZonaImpl(final LocalDate dateStart, final LocalDate dateEnd, final Reparto unit, 
			final String name, final List<String> otherUnits) throws IllegalDateException {
		super(name, dateStart, unit.getAllMember());
		this.reparto = unit;
		this.setDateEnd(dateEnd);
		this.altriReparti = otherUnits;
	}

	public EventiDiZonaImpl(final LocalDate dateStart, final int duration, final Reparto unit, final String name,
			final List<String> otherUnits) throws IllegalDateException {
		super(name, dateStart, unit.getAllMember());
		this.reparto = unit;
		this.setDateEnd(dateStart.plusDays(duration));
		this.altriReparti = otherUnits;
	}

	@Override
	public Reparto getUnit() {
		return reparto;
	}

	@Override
	public void setUnit(final Reparto unit) {
		this.reparto = unit;
	}

	@Override
	public List<Attivita> getAllActivities() {
		return this.attivita;
	}

	@Override
	public List<Attivita> getAllActivitiesInTime(final LocalTime time) {
		final List<Attivita> tmp = new ArrayList<>();
		this.attivita.forEach(e -> {
			if (!e.haOrarioFine() && e.getOrarioInizio().equals(time)) {
				tmp.add(e);
			} else if (e.haOrarioFine()) {
				if (e.getOrarioFine().isAfter(time) && e.getOrarioInizio().isBefore(time)) {
					tmp.add(e);
				}
			}
		});
		return tmp;
	}

	@Override
	public void addActivity(final Attivita activity) {
		this.attivita.add(activity);
	}

	@Override
	public void addActivity(final String nameActivity, final LocalTime startTime) {
		this.attivita.add(new AttivitaImpl(nameActivity, startTime));
	}

	@Override
	public void addActivity(final String nameActivity, final LocalTime startTime, final LocalTime endTime) {
		this.attivita.add(new AttivitaImpl(nameActivity, startTime, endTime));
	}

	@Override
	public void removeActivity(final Attivita activity) {
		this.attivita.remove(attivita);
	}

	@Override
	public List<String> getOtherUnits() {
		return this.altriReparti;
	}

	@Override
	public void addOtherUnit(final String name) {
		this.altriReparti.add(name);
	}

	@Override
	public void removeOtherUnit(final String name) {
		this.altriReparti.remove(name);
	}

	@Override
	public boolean containOtherUnit(final String name) {
		return this.altriReparti.contains(name);
	}

	@Override
	protected void check(final LocalDate dateStart, final LocalDate dateEnd) throws IllegalDateException {
	}
}
