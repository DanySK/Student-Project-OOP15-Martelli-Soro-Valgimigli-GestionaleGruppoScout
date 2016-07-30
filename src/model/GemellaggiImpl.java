package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.exception.IllegalDateException;

public class GemellaggiImpl extends ExcursionImpl implements Gemellaggi {

	private static final long serialVersionUID = 1L;
	private Reparto reparto;
	private List<String> altriReparti=new ArrayList<>();
	
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
	public void addOtherUnit (String name){
		this.altriReparti.add(name);
	}
	public void removeOtherUnit(String name){
		this.altriReparti.remove(name);
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
