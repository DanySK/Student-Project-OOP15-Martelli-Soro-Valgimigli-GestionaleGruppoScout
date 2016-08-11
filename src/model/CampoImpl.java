package model;

import java.time.LocalDate;

import model.exception.IllegalDateException;

public class CampoImpl extends ExcursionImpl implements Campo {

	private static final long serialVersionUID = 1L;
	private Reparto reparto;
	
	
	public CampoImpl (LocalDate dateStart,LocalDate dateEnd,Reparto reparto,String name) throws Exception{
		super (name,dateStart,reparto.getAllMember());
		this.check(dateStart, dateEnd);
		this.reparto=reparto;
		this.setDateEnd(dateEnd);
	}
	public CampoImpl (LocalDate dateStart,int durata,Reparto reparto,String name) throws IllegalDateException{
		super (name,dateStart,reparto.getAllMember());
		this.check(dateStart, dateStart.plusDays(durata-1));
		this.reparto=reparto;
		this.setDateEnd(dateStart.plusDays(durata));
	}
	public Reparto getReparto() {
		return reparto;
	}

	public void setReparto(Reparto reparto) {
		this.reparto = reparto;
	}
	@Override
	protected void check(LocalDate dateStart, LocalDate dateEnd) throws IllegalDateException {
		if (dateStart.plusDays(2).isAfter(dateEnd)){
			throw new IllegalDateException();
		}
		
	}
}