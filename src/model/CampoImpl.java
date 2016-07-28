package model;

import java.time.LocalDate;

import model.exception.IllegalDateException;

public class CampoImpl extends ExcursionImpl {

	private static final long serialVersionUID = 1L;
	private Reparto reparto;
	
	public CampoImpl (LocalDate dateStart,LocalDate dateEnd,Reparto reparto) throws IllegalDateException{
		super (dateStart,reparto.getAllMember());
		this.reparto=reparto;
		this.setDateEnd(dateEnd);
	}
	public CampoImpl (LocalDate dateStart,int durata,Reparto reparto) throws IllegalDateException{
		super (dateStart,reparto.getAllMember());
		this.reparto=reparto;
		this.setDateEnd(dateStart.plusDays(durata));
	}
	public Reparto getReparto() {
		return reparto;
	}

	public void setReparto(Reparto reparto) {
		this.reparto = reparto;
	}
}