package model;

import java.time.LocalDate;
import java.util.List;

import model.exception.IllegalDateException;
import model.exception.ObjectAlreadyContainedException;
import model.exception.ObjectNotContainedException;

public class GemellaggiImpl extends ExcursionImpl implements Gemellaggi {

	private static final long serialVersionUID = 1L;
	private Reparto reparto;
	private List<String> altriReparti;
	
	public GemellaggiImpl (LocalDate dateStart,LocalDate dateEnd,Reparto reparto,String name,List<String>altriReparti) throws Exception{
		super (name,dateStart,reparto.getAllMember());
		this.check(dateStart, dateEnd);
		this.reparto=reparto;
		this.setDateEnd(dateEnd);
		this.altriReparti=altriReparti;
	}
	public GemellaggiImpl (LocalDate dateStart,int durata,Reparto reparto,String name,List<String>altriReparti) throws Exception{
		super (name,dateStart,reparto.getAllMember());
		this.check(dateStart, dateStart.plusDays(durata-1));
		this.reparto=reparto;
		this.setDateEnd(dateStart.plusDays(durata));
		this.altriReparti=altriReparti;
	}
	public Reparto getReparto() {
		return reparto;
	}

	public void setReparto(Reparto reparto) {
		this.reparto = reparto;
	}
	public List<String> getOtherUnits(){
		return this.altriReparti;
	}
	public void addOtherUnit (String name)throws ObjectAlreadyContainedException {
		if (this.altriReparti.contains(name)) throw new ObjectAlreadyContainedException();
		this.altriReparti.add(name);
	}
	public void removeOtherUnit(String name) throws ObjectNotContainedException{
		if (!this.altriReparti.remove(name))throw new ObjectNotContainedException ();
	}
	public boolean containOtherUnit(String name){
		return this.altriReparti.contains(name);
	}
	@Override
	protected void check(LocalDate dateStart, LocalDate dateEnd) throws Exception {
		if (dateStart.plusDays(1).isAfter(dateEnd)){
			throw new IllegalDateException();
		}
	}
}
