package model;

import java.time.LocalDate;

import model.exception.IllegalDateException;

public class CampoImpl extends ExcursionImpl implements Campo {

	private static final long serialVersionUID = 1L;
	private Reparto reparto;
	
	
	public CampoImpl (final LocalDate dateStart,final LocalDate dateEnd,final Reparto reparto,final String name) throws IllegalDateException{
		super (name,dateStart,reparto.getAllMember());
		this.check(dateStart, dateEnd);
		this.reparto=reparto;
		this.setDateEnd(dateEnd);
	}
	public CampoImpl (final LocalDate dateStart,final int durata,final Reparto reparto,final String name) throws IllegalDateException{
		super (name,dateStart,reparto.getAllMember());
		this.check(dateStart, dateStart.plusDays(durata-1));
		this.reparto=reparto;
		this.setDateEnd(dateStart.plusDays(durata));
	}
	public Reparto getReparto() {
		return reparto;
	}

	public void setReparto(final Reparto reparto) {
		this.reparto = reparto;
	}
	@Override
	final protected void check(final LocalDate dateStart,final LocalDate dateEnd) throws IllegalDateException {
		if (dateStart.plusDays(2).isAfter(dateEnd)){
			throw new IllegalDateException();
		}
		
	}
}