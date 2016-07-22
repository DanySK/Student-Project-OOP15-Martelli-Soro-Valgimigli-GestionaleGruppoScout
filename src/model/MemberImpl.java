package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import control.myUtil.myOptional;

public class MemberImpl extends PersonImpl implements Serializable, Member, Person {
	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	private final int identificatore;
	private List<String> competence;
	private List<String> specialities;
	private Boolean promise;
	private myOptional<TutorImpl> tutor;
	private myOptional<String> totem;

	public MemberImpl(String name, String surname, LocalDate birthday, Boolean sex,int id) {
		super(name, surname, birthday, sex);
		this.tutor = myOptional.empty();
		this.identificatore=id;
	}

	public MemberImpl(String name, String surname, LocalDate birthday, Boolean sex, TutorImpl tutor,int id) {
		super(name, surname, birthday, sex);
		this.tutor = myOptional.of(tutor);
		this.identificatore=id;
	}

	public boolean addCompetence(String competence) {
		/*
		 * return false if the competent is already contained
		 */
		if (this.competence.contains(competence)) {
			return false;
		}
		this.competence.add(competence);
		return true;
	}

	public List<String> getCompetence() {
		return this.competence;
	}

	public List<String> getSpecialities() {
		return this.specialities;
	}

	public void setTutorMail(String mail) {
		if (!this.tutor.isPresent()) {
			this.tutor = myOptional.of(new TutorImpl());
		}
		this.tutor.get().setEmail(mail);
		return;
	}

	public myOptional<String> getTutorMail() {
		if (this.tutor.isPresent()) {
			if (this.tutor.get().getEmail().isPresent()) {
				return this.tutor.get().getEmail();
			}
		}
		return myOptional.empty();
	}

	public void setTutorName(String name) {
		if (!this.tutor.isPresent()) {
			this.tutor = myOptional.of(new TutorImpl());
		}
		this.tutor.get().setName(name);
		return;
	}

	public myOptional<String> getTutorName() {
		if (this.tutor.isPresent()) {
			if (this.tutor.get().getName().isPresent()) {
				return this.tutor.get().getName();
			}
		}
		return myOptional.empty();
	}

	public void setTutorPhone(Long phone) {
		if (!this.tutor.isPresent()) {
			this.tutor = myOptional.of(new TutorImpl());
		}
		this.tutor.get().setPhone(phone);
		return;
	}

	public myOptional<Long> getTutorPhone() {
		if (this.tutor.isPresent()) {
			if (this.tutor.get().getPhone().isPresent()) {
				return this.tutor.get().getPhone();
			}
		}
		return myOptional.empty();
	}

	public boolean removeSpecialities(
			String specialities) {/*
									 * return false if the competent is not
									 * already contained
									 */
		if (this.specialities.contains(specialities)) {
			this.specialities.remove(specialities);
			return true;
		}
		return false;
	}

	public boolean addSpecialities(String specialities) {
		if (!this.containsSpecialities(specialities)) {
			this.specialities.add(specialities);
			return true;
		} else {
			return false;
		}
	}

	public boolean containsSpecialities(
			String specialities) {/*
									 * return true if the competent is contained
									 */
		return this.specialities.contains(specialities);
	}

	public boolean removeCompetence(
			String competence) {/*
								 * return false if the competent is not already
								 * contained
								 */

		return this.competence.remove(competence);

	}

	public boolean containsCompetence(
			String competence) {/* return true if the competent is contained */
		return this.competence.contains(competence);
	}

	public boolean getPromise() {
		return this.promise;
	}
	public int getHowOldIs(){
		return (LocalDate.now().getYear()-this.getBirthday().getYear());
	}
	public void setPromise(boolean promessa) {
		this.promise = promessa;
	}

	public boolean hasTotem() {
		return totem.isPresent();
	}

	public void setTotem(String totem) {
		this.totem = myOptional.of(totem);
	}

	public String getTotem() {

		return this.totem.get();
	}
	public int getId(){
		return this.identificatore;
	}

}