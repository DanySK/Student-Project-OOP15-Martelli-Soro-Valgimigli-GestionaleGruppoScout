package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

import model.exception.IllegalDateException;

public class UscitaSquadrigliaImpl extends ExcursionImpl implements UscitaSquadriglia, Serializable {

	private static final long serialVersionUID = 1L;
	private Squadron squadriglia;

	public UscitaSquadrigliaImpl(LocalDate dateStart, LocalDate dateEnd, Squadron squadriglia, String name)
			throws Exception {
		super(name, dateStart, new ArrayList<>(squadriglia.getMembri().keySet()));
		this.squadriglia = squadriglia;
		this.setDateEnd(dateEnd);
	}

	public UscitaSquadrigliaImpl(LocalDate dateStart, int durata, Squadron squadriglia, String name) throws IllegalDateException {
		super(name, dateStart, new ArrayList<>(squadriglia.getMembri().keySet()));
		this.squadriglia = squadriglia;
		this.setDateEnd(dateStart.plusDays(durata));
	}

	public Squadron getSquadriglia() {
		return this.squadriglia;
	}

	public void setReparto(Squadron squadriglia) {
		this.squadriglia = squadriglia;
	}

	@Override
	protected void check(LocalDate dateStart, LocalDate dateEnd) throws IllegalDateException {
		return;

	}
}
