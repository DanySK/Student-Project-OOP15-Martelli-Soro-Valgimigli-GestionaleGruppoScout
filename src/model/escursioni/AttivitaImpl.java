package model.escursioni;

import java.time.LocalTime;

import control.myUtil.MyOptional;

public class AttivitaImpl implements Attivita {
	private String name;
	private LocalTime orarioInizio;
	private MyOptional<LocalTime> orarioFine;

	public AttivitaImpl(final String name, final LocalTime orarioInizio) {
		this.name = name;
		this.orarioInizio = orarioInizio;
		this.orarioFine = MyOptional.empty();
	}

	public AttivitaImpl(final String name, final LocalTime orarioInizio, final LocalTime orarioFine) {
		this.name = name;
		this.orarioInizio = orarioInizio;
		this.orarioFine = MyOptional.of(orarioFine);
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void setName(final String name) {
		this.name = name;
	}

	@Override
	public LocalTime getOrarioInizio() {
		return orarioInizio;
	}

	@Override
	public void setOrarioInizio(final LocalTime startTime) {
		this.orarioInizio = startTime;
	}

	@Override
	public boolean haOrarioFine() {
		return this.orarioFine.isPresent();
	}

	@Override
	public LocalTime getOrarioFine() {
		return this.orarioFine.get();
	}

	@Override
	public void setOrarioFine(final LocalTime endTime) {
		this.orarioFine = MyOptional.of(endTime);
	}
}
