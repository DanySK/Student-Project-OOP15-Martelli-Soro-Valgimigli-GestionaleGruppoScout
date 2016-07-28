package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import control.myUtil.Pair;
import control.myUtil.myOptional;
import model.exception.IllegalDateException;

public abstract class ExcursionImpl implements Excursion,Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private myOptional<Integer> prize;
	private LocalDate dateStart;
	private String name;
	private myOptional<LocalDate> dateEnd;
	private myOptional<String> place;
	private List<Pair<Member, Boolean>> partecipanti = new ArrayList<>();

	public ExcursionImpl(LocalDate dateStart,String name) throws IllegalDateException {
		setDateStart(dateStart);
		this.prize = myOptional.empty();
		this.dateEnd = myOptional.empty();
		this.place = myOptional.empty();
		this.name=name;
	}

	public ExcursionImpl(String name,LocalDate dateStart, List<Member> partecipanti) throws IllegalDateException {
		this(dateStart,name);
		partecipanti.forEach(e -> {
			this.partecipanti.add(new Pair<>(e, false));
		});
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void addPartecipante(Member partecipante, Boolean pagato) {
		this.partecipanti.add(new Pair<>(partecipante, pagato));
	}

	public void removePartecipante(Member partecipante) {
		if(this.isPagante(partecipante)){
			this.partecipanti.remove(new Pair<>(partecipante,true));
		}else{
			this.partecipanti.remove(new Pair<>(partecipante,false));
		}
	}

	public List<Member> getNonPaganti() {
		List<Member> tmp = new ArrayList<>();
		this.partecipanti.forEach(e -> {
			if (!e.getY()) {
				tmp.add(e.getX());
			}
		});
		return tmp;
	}

	public List<Member> getAllPartecipanti() {
		List<Member> tmp = new ArrayList<>();
		this.partecipanti.forEach(e -> {
			tmp.add(e.getX());
		});
		return tmp;
	}

	public List<Member> getAllPaganti() {
		List<Member> tmp = new ArrayList<>();
		this.partecipanti.forEach(e -> {
			if (e.getY()) {
				tmp.add(e.getX());
			}
		});
		return tmp;
	}

	public void setPagante(Member partecipante) {
		this.partecipanti.forEach(e -> {
			if (e.getX().equals(partecipante)) {
				e.setY(true);
			}
		});
	}

	public boolean containMember(Member partecipante) {
		for (Pair<Member, Boolean> e : this.partecipanti) {
			if (e.getX().equals(partecipante)) {
				return true;
			}
		}
		return false;
	}

	public boolean isPagante(Member partecipante) {
		for (Pair<Member, Boolean> e : this.partecipanti) {
			if (e.getX().equals(partecipante)) {
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

	public List<Member> getAllBirthdays(){
		List<Member> tmp=new ArrayList<>();
		partecipanti.forEach(e->{
			if (this.dateEnd.isPresent()){
				if (e.getX().getBirthday().getDayOfYear()>=this.dateStart.getDayOfYear()&&
						e.getX().getBirthday().getDayOfYear()<=this.dateEnd.get().getDayOfYear()){
					tmp.add(e.getX());
				}
			}else{
				if (e.getX().getBirthday().getDayOfYear()==this.dateStart.getDayOfYear()){
					tmp.add(e.getX());
				}
			}
		});
		return tmp;
	}
}
