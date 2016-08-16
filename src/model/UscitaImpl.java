package model;

import java.io.Serializable;
import java.time.LocalDate;

import model.exception.IllegalDateException;

public class UscitaImpl extends ExcursionImpl implements Uscita, Serializable {

	private static final long serialVersionUID = 1L;
	private Reparto reparto;

	public UscitaImpl(final LocalDate dateStart,final Reparto reparto,final String name) throws IllegalDateException {
		super(name, dateStart, reparto.getAllMember());
		this.reparto=reparto;
		this.setDateEnd(dateStart.plusDays(2));
	}

	public Reparto getReparto() {
		return reparto;
	}

	public void setReparto(final Reparto reparto) {
		this.reparto = reparto;
	}

	@Override
	protected void check(final LocalDate dateStart,final LocalDate dateEnd) throws IllegalDateException {
	}

}
