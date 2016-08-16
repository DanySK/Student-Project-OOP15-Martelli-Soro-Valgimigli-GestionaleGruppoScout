package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import control.myUtil.myOptional;
import model.exception.IllegalYearsException;
import model.exception.ObjectAlreadyContainedException;
import model.exception.ObjectNotContainedException;

public class MemberImpl extends PersonImpl implements Serializable, Member, Person {
	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	private int identificatore;
	private final List<String> specialities;
	private Boolean promise;
	private myOptional<Tutor> tutor;
	private myOptional<String> totem;
	private myOptional<Integer> annoTasse;

	public MemberImpl(final String name, final String surname, final LocalDate birthday, final Boolean sex)
			throws IllegalYearsException {
		super(name, surname, birthday, sex);
		if (this.getHowIsHold().getYears() < 12 || this.getHowIsHold().getYears() > 17) {
			throw new IllegalYearsException();
		}
		this.tutor = myOptional.empty();
		this.annoTasse = myOptional.empty();
		this.totem = myOptional.empty();
		promise = false;
		this.specialities = new ArrayList<>();
	}

	public MemberImpl(final String name, final String surname, final LocalDate birthday, final Boolean sex,
			final Tutor tutor) throws IllegalYearsException {
		super(name, surname, birthday, sex);
		if (this.getHowIsHold().getYears() < 12 || this.getHowIsHold().getYears() > 17) {
			throw new IllegalYearsException();
		}
		this.tutor = myOptional.of(tutor);
		this.annoTasse = myOptional.empty();
		this.totem = myOptional.empty();
		promise = false;
		this.specialities = new ArrayList<>();

	}

	@Override
	public void setTasse(final Integer anno) {
		if (this.annoTasse.isPresent()) {
			if (this.annoTasse.get() < anno) {
				this.annoTasse = myOptional.of(anno);
			}
		} else {
			this.annoTasse = myOptional.of(anno);
		}
	}

	@Override
	public boolean isTaxPaid(final Integer anno) {
		if (this.annoTasse.isPresent()) {
			if (this.annoTasse.get().equals(anno)) {
				return true;
			}
		}
		return false;
	}

	

	@Override
	public List<String> getSpecialities() {
		return this.specialities;
	}

	@Override
	public myOptional<Tutor> getTutor() {
		return this.tutor;
	}

	@Override
	public void setTutorMail(final String mail) {
		if (!this.tutor.isPresent()) {
			this.tutor = myOptional.of(new TutorImpl());
		}
		this.tutor.get().setEmail(mail);
	}

	@Override
	public myOptional<String> getTutorMail() {
		if (this.tutor.isPresent()) {
			if (this.tutor.get().getEmail().isPresent()) {
				return this.tutor.get().getEmail();
			}
		}
		return myOptional.empty();
	}

	@Override
	public void setTutorName(final String name) {
		if (!this.tutor.isPresent()) {
			this.tutor = myOptional.of(new TutorImpl());
		}
		this.tutor.get().setName(name);

	}

	@Override
	public myOptional<String> getTutorName() {
		if (this.tutor.isPresent()) {
			if (this.tutor.get().getName().isPresent()) {
				return this.tutor.get().getName();
			}
		}
		return myOptional.empty();
	}

	@Override
	public void setTutorPhone(final Long phone) {
		if (!this.tutor.isPresent()) {
			this.tutor = myOptional.of(new TutorImpl());
		}
		this.tutor.get().setPhone(phone);

	}

	@Override
	public myOptional<Long> getTutorPhone() {
		if (this.tutor.isPresent()) {
			if (this.tutor.get().getPhone().isPresent()) {
				return this.tutor.get().getPhone();
			}
		}
		return myOptional.empty();
	}
@Override
	public void removeSpecialities(final String specialities) throws ObjectNotContainedException {
		if (!this.specialities.remove(specialities)) {
			throw new ObjectNotContainedException();
		}
	}

	@Override
	public void addSpecialities(final String specialities) throws ObjectAlreadyContainedException {
		if (this.containsSpecialities(specialities)) {
			throw new ObjectAlreadyContainedException();
		}
		this.specialities.add(specialities);
	}

	@Override
	public boolean containsSpecialities(final String specialities2) {
		return this.specialities.contains(specialities2);
	}

	
	
	@Override
	public Boolean getPromise() {
		return this.promise;
	}

	@Override
	public void setPromise(final boolean promessa) {
		this.promise = promessa;
	}

	@Override
	public boolean hasTotem() {
		return totem.isPresent();
	}

	@Override
	public void setTotem(final String totem) {
		this.totem = myOptional.of(totem);
	}

	@Override
	public String getTotem() {
		return this.totem.get();
	}

	@Override
	public int getId() {
		return this.identificatore;
	}

	@Override
	public void setId(final int id) {
		this.identificatore = id;
	}

	@Override
	public boolean isComplete() {
		return this.tutor.isPresent();
	}

}