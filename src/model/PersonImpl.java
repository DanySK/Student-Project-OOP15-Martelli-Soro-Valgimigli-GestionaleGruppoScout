package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;

public class PersonImpl implements Serializable,Person {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String surname;
	private LocalDate birthday;
	private Boolean sex;
	
	public PersonImpl(String name, String surname, LocalDate birthday,Boolean sex){
		if (name.equals(null) || surname.equals(null) || birthday.equals(null)) throw new IllegalArgumentException();
		this.name = name;
		this.surname = surname;
		this.birthday = birthday;
		this.sex=sex;
	}
	
	public void setName (String name){
		if (name.equals(null)) throw new IllegalArgumentException();
		this.name=name;
	}
	public void setSurname (String surname){
		if (surname.equals(null)) throw new IllegalArgumentException();
		this.surname=surname;
	}
	public void setBirthday (LocalDate birthday){
		if (birthday.equals(null)) throw new IllegalArgumentException();
		this.birthday=birthday;
	}
	public String getName (){
		return this.name;
	}
	public String getSurname (){
		return this.surname;
	}
	public LocalDate getBirthday (){
		return this.birthday;
	}
	public void setSex (Boolean sex){
		if (sex.equals(null)) throw new IllegalArgumentException();
		this.sex=sex;
	}
	public Boolean getSex(){
		return this.sex;
	}

	public int getAnnata() {
		return (LocalDate.now().getYear() - this.getBirthday().getYear());
	}

	public Period getHowIsHold() {
		return LocalDate.now().until(this.birthday).normalized();
	
	}

	public Boolean isBirthday(){//da testare
		return (LocalDate.now().getDayOfYear()==(this.birthday.getDayOfYear()));
	}
}
