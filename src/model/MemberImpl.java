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
	private final List<String> competence;
	private final List<Specialita> specialities;
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
		competence = new ArrayList<>();
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
		competence = new ArrayList<>();
		this.specialities = new ArrayList<>();

	}

	public void setTasse(final Integer anno) {
		if (this.annoTasse.isPresent()) {
			if (this.annoTasse.get() < anno) {
				this.annoTasse = myOptional.of(anno);
			}
		} else {
			this.annoTasse = myOptional.of(anno);
		}
	}

	public boolean isTaxPaid(final Integer anno) {
		if (this.annoTasse.isPresent()) {
			if (this.annoTasse.get().equals(anno)) {
				return true;
			}
		}
		return false;
	}

	public void addCompetence(final String competence) throws ObjectAlreadyContainedException {
		if (this.competence.contains(competence)) {
			throw new ObjectAlreadyContainedException();
		}
		this.competence.add(competence);
	}

	public List<String> getCompetence() {
		return this.competence;
	}

	public List<Specialita> getSpecialities() {
		return this.specialities;
	}

	public myOptional<Tutor> getTutor() {
		return this.tutor;
	}

	public void setTutorMail(final String mail) {
		if (!this.tutor.isPresent()) {
			this.tutor = myOptional.of(new TutorImpl());
		}
		this.tutor.get().setEmail(mail);
	}

	public myOptional<String> getTutorMail() {
		if (this.tutor.isPresent()) {
			if (this.tutor.get().getEmail().isPresent()) {
				return this.tutor.get().getEmail();
			}
		}
		return myOptional.empty();
	}

	public void setTutorName(final String name) {
		if (!this.tutor.isPresent()) {
			this.tutor = myOptional.of(new TutorImpl());
		}
		this.tutor.get().setName(name);

	}

	public myOptional<String> getTutorName() {
		if (this.tutor.isPresent()) {
			if (this.tutor.get().getName().isPresent()) {
				return this.tutor.get().getName();
			}
		}
		return myOptional.empty();
	}

	public void setTutorPhone(final Long phone) {
		if (!this.tutor.isPresent()) {
			this.tutor = myOptional.of(new TutorImpl());
		}
		this.tutor.get().setPhone(phone);

	}

	public myOptional<Long> getTutorPhone() {
		if (this.tutor.isPresent()) {
			if (this.tutor.get().getPhone().isPresent()) {
				return this.tutor.get().getPhone();
			}
		}
		return myOptional.empty();
	}

	public void removeSpecialities(final Specialita specialities) throws ObjectNotContainedException {
		if (!this.specialities.remove(specialities)) {
			throw new ObjectNotContainedException();
		}
	}

	public void addSpecialities(final Specialita specialities) throws ObjectAlreadyContainedException {
		if (this.containsSpecialities(specialities)) {
			throw new ObjectAlreadyContainedException();
		}
		this.specialities.add(specialities);
	}

	public boolean containsSpecialities(final Specialita specialities) {
		return this.specialities.contains(specialities);
	}

	public void removeCompetence(final String competence) throws ObjectNotContainedException {
		if (! this.competence.remove(competence)) {
			throw new ObjectNotContainedException();
		}
	}

	public boolean isContainingCompetence(final String competence) {
		return this.competence.contains(competence);
	}

	public Boolean getPromise() {
		return this.promise;
	}

	public void setPromise(final boolean promessa) {
		this.promise = promessa;
	}

	public boolean hasTotem() {
		return totem.isPresent();
	}

	public void setTotem(final String totem) {
		this.totem = myOptional.of(totem);
	}

	public String getTotem() {
		return this.totem.get();
	}

	public int getId() {
		return this.identificatore;
	}

	public void setId(final int id) {
		this.identificatore = id;
	}

	public boolean isComplete() {
		return this.tutor.isPresent();
	}

}