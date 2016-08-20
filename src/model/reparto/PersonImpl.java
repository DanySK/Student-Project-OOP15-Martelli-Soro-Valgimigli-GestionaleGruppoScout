package model.reparto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;

public class PersonImpl implements Serializable, Person {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String surname;
	private LocalDate birthday;
	private Boolean sex;

	public PersonImpl(final String name, final String surname, final LocalDate birthday, final Boolean sex) {
		this.name = name;
		this.surname = surname;
		this.birthday = birthday;
		this.sex = sex;
	}

	@Override

	public void setName(final String name) {
		this.name = name;
	}

	@Override
	public void setSurname(final String surname) {
		this.surname = surname;
	}

	@Override
	public void setBirthday(final LocalDate birthday) {
		this.birthday = birthday;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public String getSurname() {
		return this.surname;
	}

	@Override
	public LocalDate getBirthday() {
		return this.birthday;
	}

	@Override
	public void setSex(final Boolean sex) {
		this.sex = sex;
	}

	@Override
	public Boolean getSex() {
		return this.sex;
	}

	@Override
	public int getAnnata() {
		return LocalDate.now().getYear() - this.getBirthday().getYear();
	}

	@Override
	public Period getHowIsHold() {
		return this.birthday.until(LocalDate.now()).normalized();

	}

	@Override
	public Boolean isBirthday() {// da testare
		return (LocalDate.now().getDayOfYear() == (this.birthday.getDayOfYear()));
	}
}
