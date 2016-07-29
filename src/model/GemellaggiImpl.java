package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.exception.IllegalDateException;

public class GemellaggiImpl extends ExcursionImpl {

	private static final long serialVersionUID = 1L;
	private Reparto reparto;
	private List<String> altriReparti=new ArrayList<>();
	
	public GemellaggiImpl (LocalDate dateStart,LocalDate dateEnd,Reparto reparto,String name,List<String>altriReparti) throws IllegalDateException{
		super (name,dateStart,reparto.getAllMember());
		this.reparto=reparto;
		this.setDateEnd(dateEnd);
		this.altriReparti=altriReparti;
	}
	public GemellaggiImpl (LocalDate dateStart,int durata,Reparto reparto,String name,List<String>altriReparti) throws IllegalDateException{
		super (name,dateStart,reparto.getAllMember());
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
}
