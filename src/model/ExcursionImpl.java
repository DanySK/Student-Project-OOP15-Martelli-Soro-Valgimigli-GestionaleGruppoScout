package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import control.myUtil.Pair;
import control.myUtil.myOptional;
import model.exception.IllegalDateException;
import model.exception.ObjectAlreadyContainedException;
import model.exception.ObjectNotContainedException;

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
	private final List<Pair<Member, Boolean>> partecipanti = new ArrayList<>();

	public ExcursionImpl(final LocalDate dateStart,final String name) throws IllegalDateException {
		if (dateStart.isBefore(LocalDate.now())){
			throw new IllegalDateException();
		}
		this.dateStart=dateStart;
		this.prize = myOptional.empty();
		this.dateEnd = myOptional.empty();
		this.place = myOptional.empty();
		this.name=name;
	}
	
	public ExcursionImpl(final String name,final LocalDate dateStart,final  List<Member> partecipanti) throws IllegalDateException {
		
		this(dateStart,name);
		partecipanti.forEach(e -> {
			this.partecipanti.add(new Pair<>(e, false));
		});
	}
	protected abstract void check(LocalDate dateStart,LocalDate dateEnd)throws IllegalDateException;
	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public void addPartecipante(final Member partecipante,final  Boolean pagato) throws ObjectAlreadyContainedException {
		if (this.containMember(partecipante)){
			throw new ObjectAlreadyContainedException ();
		}
		this.partecipanti.add(new Pair<>(partecipante, pagato));
	}

	public void removePartecipante(final Member partecipante) throws ObjectNotContainedException {
		if (!this.containMember(partecipante)){
			throw new ObjectNotContainedException();
		}
		if(this.isPagante(partecipante)){
			this.partecipanti.remove(new Pair<>(partecipante,true));
		}else{
			this.partecipanti.remove(new Pair<>(partecipante,false));
		}
	}

	public List<Member> getNonPaganti() {
		final List<Member> tmp = new ArrayList<>();
		this.partecipanti.forEach(e -> {
			if (!e.getY()) {
				tmp.add(e.getX());
			}
		});
		return tmp;
	}

	public List<Member> getAllPartecipanti() {
		final List<Member> tmp = new ArrayList<>();
		this.partecipanti.forEach(e -> {
			tmp.add(e.getX());
		});
		return tmp;
	}

	public List<Member> getAllPaganti() {
		final List<Member> tmp = new ArrayList<>();
		this.partecipanti.forEach(e -> {
			if (e.getY()) {
				tmp.add(e.getX());
			}
		});
		return tmp;
	}

	public void setPagante(final Member partecipante) throws ObjectNotContainedException {
		if (! this.containMember(partecipante)){
			throw new ObjectNotContainedException();
		}
		this.partecipanti.forEach(e -> {
			if (e.getX().equals(partecipante)) {
				e.setY(true);
			}
		});
	}

	public boolean containMember(final Member partecipante) {
		for (final Pair<Member, Boolean> e : this.partecipanti) {
			if (e.getX().equals(partecipante)) {
				return true;
			}
		}
		return false;
	}

	public boolean isPagante(final Member partecipante) throws ObjectNotContainedException {
		if (! this.containMember(partecipante)) {
			throw new ObjectNotContainedException();
		}
		for (final Pair<Member, Boolean> e : this.partecipanti) {
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

	public void setPrice(final Integer prize) {
		if (prize.compareTo(0) < 0){
			throw new IllegalArgumentException();
		}
		this.prize = myOptional.of(prize);
	}

	public void setPlace(final String place) {
		this.place = myOptional.of(place);
	}

	public void setDateStart(final LocalDate dateStart) throws IllegalDateException {
		if (! dateStart.isAfter(LocalDate.now())){
			throw new IllegalDateException();
		}
		this.dateStart = dateStart;
	}

	public void setDateEnd(final LocalDate dateEnd) throws IllegalDateException {
		if (! dateEnd.isAfter(LocalDate.now())){
			throw new IllegalDateException();
		}
		if (! dateEnd.isAfter(this.dateStart)){
			throw new IllegalDateException();
		}
		this.dateEnd = myOptional.of(dateEnd);
	}

	public List<Member> getAllBirthdays(){
		final List<Member> tmp=new ArrayList<>();
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
