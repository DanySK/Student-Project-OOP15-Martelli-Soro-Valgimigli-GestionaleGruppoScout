package model;

import java.io.Serializable;
import java.time.LocalDate;

import model.exception.IllegalDateException;

public class UscitaImpl extends ExcursionImpl implements Uscita, Serializable {

	private static final long serialVersionUID = 1L;
	private Reparto reparto;

	public UscitaImpl(LocalDate dateStart, Reparto reparto, String name) throws IllegalDateException {
		super(name, dateStart, reparto.getAllMember());
		this.setReparto(reparto);
		this.setDateEnd(dateStart.plusDays(2));
	}

	public Reparto getReparto() {
		return reparto;
	}

	public void setReparto(Reparto reparto) {
		this.reparto = reparto;
	}

	@Override
	protected void check(LocalDate dateStart, LocalDate dateEnd) throws IllegalDateException {
		return;
	}

}
