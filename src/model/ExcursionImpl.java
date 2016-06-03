package model;

import java.io.Serializable;
import java.time.LocalDate;
import control.myUtil.myOptional;
import model.exception.IllegalDateException;

public class ExcursionImpl implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private myOptional<Integer> prize;
	private LocalDate dateStart;
	private myOptional<LocalDate> dateEnd;
	private myOptional<String> place;
	public ExcursionImpl (LocalDate dateStart) throws IllegalDateException{
		setDateStart(dateStart);
		this.prize=myOptional.empty();
		this.dateEnd=myOptional.empty();
		this.place=myOptional.empty();
	}
	public Integer getPrize(){
		return this.prize.get();
	}
	public String getPlace(){
		return this.place.get();
	}
	public LocalDate getDateStart(){
		return this.dateStart;
	}
	public LocalDate getDateEnd(){
		return this.dateEnd.get();
	}
	public void setPrice(Integer prize){
		if (prize.compareTo(0)<0) throw new IllegalArgumentException();
		this.prize=myOptional.of(prize);
	}
	public void setPlace(String place){
		if (place.equals(null))throw new IllegalArgumentException();
		this.place=myOptional.of(place);
	}
	public void setDateStart(LocalDate dateStart) throws IllegalDateException{
		if (dateStart.equals(null))throw new IllegalArgumentException();
		if (!dateStart.isAfter(LocalDate.now())) throw new IllegalDateException();
		this.dateStart=dateStart;
	}
	public void setDateEnd(LocalDate dateEnd) throws IllegalDateException{
		if (dateEnd.equals(null))throw new IllegalArgumentException();
		if (!dateEnd.isAfter(LocalDate.now())) throw new IllegalDateException();
		if (!dateEnd.isAfter(this.dateStart)) throw new IllegalDateException();
		this.dateEnd=myOptional.of(dateEnd);
	}
}


