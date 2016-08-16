package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import control.myUtil.MyOptional;
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
	private MyOptional<Tutor> tutor;
	private MyOptional<String> totem;
	private MyOptional<Integer> annoTasse;

	public MemberImpl(final String name, final String surname, final LocalDate birthday, final Boolean sex)
			throws IllegalYearsException {
		super(name, surname, birthday, sex);
		if (this.getHowIsHold().getYears() < 12 || this.getHowIsHold().getYears() > 17) {
			throw new IllegalYearsException();
		}
		this.tutor = MyOptional.empty();
		this.annoTasse = MyOptional.empty();
		this.totem = MyOptional.empty();
		promise = false;
		this.specialities = new ArrayList<>();
	}

	public MemberImpl(final String name, final String surname, final LocalDate birthday, final Boolean sex,
			final Tutor tutor) throws IllegalYearsException {
		super(name, surname, birthday, sex);
		if (this.getHowIsHold().getYears() < 12 || this.getHowIsHold().getYears() > 17) {
			throw new IllegalYearsException();
		}
		this.tutor = MyOptional.of(tutor);
		this.annoTasse = MyOptional.empty();
		this.totem = MyOptional.empty();
		promise = false;
		this.specialities = new ArrayList<>();

	}

	@Override
	public void setTasse(final Integer anno) {
		if (this.annoTasse.isPresent()) {
			if (this.annoTasse.get() < anno) {
				this.annoTasse = MyOptional.of(anno);
			}
		} else {
			this.annoTasse = MyOptional.of(anno);
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
	public MyOptional<Tutor> getTutor() {
		return this.tutor;
	}

	@Override
	public void setTutorMail(final String mail) {
		if (!this.tutor.isPresent()) {
			this.tutor = MyOptional.of(new TutorImpl());
		}
		this.tutor.get().setEmail(mail);
	}

	@Override
	public MyOptional<String> getTutorMail() {
		if (this.tutor.isPresent()) {
			if (this.tutor.get().getEmail().isPresent()) {
				return this.tutor.get().getEmail();
			}
		}
		return MyOptional.empty();
	}

	@Override
	public void setTutorName(final String name) {
		if (!this.tutor.isPresent()) {
			this.tutor = MyOptional.of(new TutorImpl());
		}
		this.tutor.get().setName(name);

	}

	@Override
	public MyOptional<String> getTutorName() {
		if (this.tutor.isPresent()) {
			if (this.tutor.get().getName().isPresent()) {
				return this.tutor.get().getName();
			}
		}
		return MyOptional.empty();
	}

	@Override
	public void setTutorPhone(final Long phone) {
		if (!this.tutor.isPresent()) {
			this.tutor = MyOptional.of(new TutorImpl());
		}
		this.tutor.get().setPhone(phone);

	}

	@Override
	public MyOptional<Long> getTutorPhone() {
		if (this.tutor.isPresent()) {
			if (this.tutor.get().getPhone().isPresent()) {
				return this.tutor.get().getPhone();
			}
		}
		return MyOptional.empty();
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
		this.totem = MyOptional.of(totem);
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