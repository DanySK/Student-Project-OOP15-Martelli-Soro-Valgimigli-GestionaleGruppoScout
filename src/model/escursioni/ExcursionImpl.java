package model.escursioni;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import control.myUtil.Pair;
import control.myUtil.MyOptional;
import model.exception.IllegalDateException;
import model.exception.ObjectAlreadyContainedException;
import model.exception.ObjectNotContainedException;
import model.reparto.Member;

public abstract class ExcursionImpl implements Excursion, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MyOptional<Integer> prize;
	private LocalDate dateStart;
	private String name;
	private MyOptional<LocalDate> dateEnd;
	private MyOptional<String> place;
	private List<Pair<Member, Boolean>> partecipanti;

	public ExcursionImpl(final LocalDate dateStart, final String name) throws IllegalDateException {
		if (dateStart.isBefore(LocalDate.now())) {
			throw new IllegalDateException();
		}
		this.partecipanti = new ArrayList<>();
		this.dateStart = dateStart;
		this.prize = MyOptional.empty();
		this.dateEnd = MyOptional.empty();
		this.place = MyOptional.empty();
		this.name = name;
	}

	public ExcursionImpl(final String name, final LocalDate dateStart, final List<Member> partecipants)
			throws IllegalDateException {

		this(dateStart, name);
		partecipants.forEach(e -> {
			this.partecipanti.add(new Pair<>(e, false));
		});
	}

	protected abstract void check(LocalDate dateStart, LocalDate dateEnd) throws IllegalDateException;

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(final String name) {
		this.name = name;
	}

	@Override
	public void addPartecipant(final Member partecipant, final Boolean paied) throws ObjectAlreadyContainedException {
		if (this.containMember(partecipant)) {
			throw new ObjectAlreadyContainedException();
		}
		this.partecipanti.add(new Pair<>(partecipant, paied));
	}

	@Override
	public void removePartecipant(final Member partecipante) throws ObjectNotContainedException {
		if (!this.containMember(partecipante)) {
			throw new ObjectNotContainedException();
		}
		this.partecipanti = this.partecipanti.stream().filter(e -> !(e.getX().equals(partecipante)))
				.collect(Collectors.toList());
	}

	@Override
	public List<Member> getNotPaied() {
		final List<Member> tmp = new ArrayList<>();
		this.partecipanti.forEach(e -> {
			if (!e.getY()) {
				tmp.add(e.getX());
			}
		});
		return tmp;
	}

	@Override
	public List<Member> getAllPartecipants() {
		final List<Member> tmp = new ArrayList<>();
		this.partecipanti.forEach(e -> {
			tmp.add(e.getX());
		});
		return tmp;
	}

	@Override
	public List<Member> getAllPaied() {
		final List<Member> tmp = new ArrayList<>();
		this.partecipanti.forEach(e -> {
			if (e.getY()) {
				tmp.add(e.getX());
			}
		});
		return tmp;
	}

	@Override
	public void setPaied(final Member partecipante) throws ObjectNotContainedException {
		if (!this.containMember(partecipante)) {
			throw new ObjectNotContainedException();
		}
		this.partecipanti.forEach(e -> {
			if (e.getX().equals(partecipante)) {
				e.setY(true);
			}
		});
	}

	@Override
	public boolean containMember(final Member partecipante) {
		for (final Pair<Member, Boolean> e : this.partecipanti) {
			if (e.getX().equals(partecipante)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean isPaied(final Member partecipante) throws ObjectNotContainedException {
		if (!this.containMember(partecipante)) {
			throw new ObjectNotContainedException();
		}
		for (final Pair<Member, Boolean> e : this.partecipanti) {
			if (e.getX().equals(partecipante)) {
				return e.getY();
			}
		}
		return false;
	}

	@Override
	public Integer getPrize() {
		return this.prize.orElse(0);
	}

	@Override
	public String getPlace() {
		return this.place.orElse("Luogo non impostato");
	}

	@Override
	public LocalDate getDateStart() {
		return this.dateStart;
	}

	@Override
	public LocalDate getDateEnd() {
		return this.dateEnd.orElse(this.dateStart);
	}

	@Override
	public void setPrice(final Integer prize) {
		if (prize.compareTo(0) < 0) {
			throw new IllegalArgumentException();
		}
		this.prize = MyOptional.of(prize);
	}

	@Override
	public void setPlace(final String place) {
		this.place = MyOptional.of(place);
	}

	@Override
	public void setDateStart(final LocalDate dateStart) throws IllegalDateException {
		if (!dateStart.isAfter(LocalDate.now())) {
			throw new IllegalDateException();
		}
		this.dateStart = dateStart;
	}

	@Override
	public void setDateEnd(final LocalDate dateEnd) throws IllegalDateException {
		if (dateEnd.isBefore(LocalDate.now())) {
			throw new IllegalDateException();
		}
		if (dateEnd.isBefore(this.dateStart)) {
			throw new IllegalDateException();
		}
		this.dateEnd = MyOptional.of(dateEnd);
	}

	@Override
	public List<Member> getAllBirthdays() {
		final List<Member> tmp = new ArrayList<>();
		partecipanti.forEach(e -> {
			if (this.dateEnd.isPresent()) {
				if (e.getX().getBirthday().getDayOfYear() >= this.dateStart.getDayOfYear()
						&& e.getX().getBirthday().getDayOfYear() <= this.dateEnd.get().getDayOfYear()) {
					tmp.add(e.getX());
				}
			} else {
				if (e.getX().getBirthday().getDayOfYear() == this.dateStart.getDayOfYear()) {
					tmp.add(e.getX());
				}
			}
		});
		return tmp;
	}
}
