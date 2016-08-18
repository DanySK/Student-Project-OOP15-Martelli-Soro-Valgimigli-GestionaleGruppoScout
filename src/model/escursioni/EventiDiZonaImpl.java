package model.escursioni;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import model.exception.IllegalDateException;
import model.exception.ObjectAlreadyContainedException;
import model.exception.ObjectNotContainedException;
import model.reparto.Reparto;

public class EventiDiZonaImpl extends ExcursionImpl implements EventiDiZona {

	private static final long serialVersionUID = 1L;
	private Reparto reparto;
	private List<String> altriReparti;
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
		this.setDateEnd(dateStart.plusDays(duration-1));
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
	public void addActivity(final String nameActivity, final LocalTime startTime) throws ObjectAlreadyContainedException {
		for (final Attivita e:this.attivita){
			if (e.getName().equals(nameActivity)){
				throw new ObjectAlreadyContainedException();
			}
		}
		this.attivita.add(new AttivitaImpl(nameActivity, startTime));
	}

	@Override
	public void addActivity(final String nameActivity, final LocalTime startTime, final LocalTime endTime) throws ObjectAlreadyContainedException {
		for (final Attivita e:this.attivita){
			if (e.getName().equals(nameActivity)){
				throw new ObjectAlreadyContainedException();
			}
		}
		this.attivita.add(new AttivitaImpl(nameActivity, startTime, endTime));
	}

	@Override
	public void removeActivity(final Attivita activity) throws ObjectNotContainedException  {
		if (! this.attivita.contains(activity)){
			throw new ObjectNotContainedException();
		}
		this.attivita.remove(activity);
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
	protected void check(final LocalDate dateStart, final LocalDate dateEnd) throws IllegalDateException {
	}

	@Override
	public void clearUnitsList() {
		this.altriReparti=new ArrayList<>();
	}
}
