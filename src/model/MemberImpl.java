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
	private int identificatore;
	private List<String> competence;
	private List<Specialita> specialities;
	private Boolean promise;
	private myOptional<Tutor> tutor;
	private myOptional<String> totem;
	private myOptional<Integer> annoTasse;
	public MemberImpl(String name, String surname, LocalDate birthday, Boolean sex) {
		super(name, surname, birthday, sex);
		this.tutor = myOptional.empty();
		this.annoTasse=myOptional.empty();
		this.totem=myOptional.empty();
		promise=false;
		competence=new ArrayList<>();
		this.specialities=new ArrayList<>();
	}

	public MemberImpl(String name, String surname, LocalDate birthday, Boolean sex, Tutor tutor) {
		super(name, surname, birthday, sex);
		this.tutor = myOptional.of(tutor);
		this.annoTasse=myOptional.empty();
		this.totem=myOptional.empty();
		promise=false;
		competence=new ArrayList<>();
		this.specialities=new ArrayList<>();
		
	}
	public void setTasse (Integer anno){
		if (this.annoTasse.isPresent()){
			if (this.annoTasse.get()<anno){
				this.annoTasse=myOptional.of(anno);
			}
		}
	}
	public boolean isTaxPaid (Integer anno){
		if (this.annoTasse.isPresent()){
			if(this.annoTasse.get().equals(anno))return true;
		}
		return false;
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

	public List<Specialita> getSpecialities() {
		return this.specialities;
	}
	public myOptional<Tutor> getTutor(){
		return this.tutor;
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
			Specialita specialities) {/*
									 * return false if the competent is not
									 * already contained
									 */
		if (this.specialities.contains(specialities)) {
			this.specialities.remove(specialities);
			return true;
		}
		return false;
	}

	public boolean addSpecialities(Specialita specialities) {
		if (!this.containsSpecialities(specialities)) {
			this.specialities.add(specialities);
			return true;
		} else {
			return false;
		}
	}

	public boolean containsSpecialities(
			Specialita specialities) {/*
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

	public int getId() {
		return this.identificatore;
	}

	public void setId(int id) {
		this.identificatore = id;
	}
	public boolean isComplete(){
		return this.tutor.isPresent();
	}

}