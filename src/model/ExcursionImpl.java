package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import control.myUtil.Pair;
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
	private List<Pair<Integer, Boolean>> partecipanti = new ArrayList<>();

	private ExcursionImpl(LocalDate dateStart) throws IllegalDateException {
		setDateStart(dateStart);
		this.prize = myOptional.empty();
		this.dateEnd = myOptional.empty();
		this.place = myOptional.empty();
	}

	public ExcursionImpl(LocalDate dateStart, List<Integer> idPartecipanti) throws IllegalDateException {
		this(dateStart);
		idPartecipanti.forEach(e -> {
			this.partecipanti.add(new Pair<>(e, false));
		});
	}

	public void addPartecipante(int idPartecipante, boolean pagato) {
		this.partecipanti.add(new Pair<>(idPartecipante, pagato));
	}

	public void removePartecipante(int idPartecipante) {
		this.partecipanti.remove(idPartecipante);
	}

	public List<Integer> getNonPaganti() {
		List<Integer> tmp = new ArrayList<>();
		this.partecipanti.forEach(e -> {
			if (!e.getY()) {
				tmp.add(e.getX());
			}
		});
		return tmp;
	}
	public List<Integer> getAllPartecipanti(){
		List<Integer> tmp = new ArrayList<>();
		this.partecipanti.forEach(e -> {
				tmp.add(e.getX());
		});
		return tmp;
	}
	public List<Integer> getAllPaganti(){
		List<Integer> tmp = new ArrayList<>();
		this.partecipanti.forEach(e -> {
			if (e.getY()) {
				tmp.add(e.getX());
			}
		});
		return tmp;
	}
	public void setPagante(Integer idPartecipante){
		this.partecipanti.forEach(e->{
			if(e.getX().equals(idPartecipante)){
				e.setY(true);
			}
		});
	}
	public boolean containMember(Integer idPartecipante){
		for(Pair<Integer,Boolean> e:this.partecipanti){
			if (e.getX().equals(idPartecipante)){
				return true;
			}
		}
		return false;
	}
	public boolean isPagante (Integer idPartecipante){
		for (Pair<Integer,Boolean>e:this.partecipanti){
			if(e.getX().equals(idPartecipante)){
				return e.getY();
			}
		}
		return false;
	}
	public Integer getPrize() {
		return this.prize.get();
	}

	public String getPlace() {
		return this.place.get();
	}

	public LocalDate getDateStart() {
		return this.dateStart;
	}

	public LocalDate getDateEnd() {
		return this.dateEnd.get();
	}

	public void setPrice(Integer prize) {
		if (prize.compareTo(0) < 0)
			throw new IllegalArgumentException();
		this.prize = myOptional.of(prize);
	}

	public void setPlace(String place) {
		if (place.equals(null))
			throw new IllegalArgumentException();
		this.place = myOptional.of(place);
	}

	public void setDateStart(LocalDate dateStart) throws IllegalDateException {
		if (dateStart.equals(null))
			throw new IllegalArgumentException();
		if (!dateStart.isAfter(LocalDate.now()))
			throw new IllegalDateException();
		this.dateStart = dateStart;
	}

	public void setDateEnd(LocalDate dateEnd) throws IllegalDateException {
		if (dateEnd.equals(null))
			throw new IllegalArgumentException();
		if (!dateEnd.isAfter(LocalDate.now()))
			throw new IllegalDateException();
		if (!dateEnd.isAfter(this.dateStart))
			throw new IllegalDateException();
		this.dateEnd = myOptional.of(dateEnd);
	}
}
