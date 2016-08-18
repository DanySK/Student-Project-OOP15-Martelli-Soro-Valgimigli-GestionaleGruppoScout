package model.escursioni;

import java.io.Serializable;
import java.time.LocalDate;

import model.exception.IllegalDateException;
import model.reparto.Reparto;

public class UscitaImpl extends ExcursionImpl implements Uscita, Serializable {

	private static final long serialVersionUID = 1L;
	private Reparto reparto;

	public UscitaImpl(final LocalDate dateStart, final Reparto reparto, final String name) throws IllegalDateException {
		super(name, dateStart, reparto.getAllMember());
		this.reparto = reparto;
		this.setDateEnd(dateStart.plusDays(2));
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
	protected void check(final LocalDate dateStart, final LocalDate dateEnd) throws IllegalDateException {
	}

}
