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
	
	public PersonImpl(final String name,final String surname,final LocalDate birthday,final Boolean sex){
		this.name = name;
		this.surname = surname;
		this.birthday = birthday;
		this.sex=sex;
	}
	
	public void setName (final String name){
		this.name=name;
	}
	public void setSurname (final String surname){
		this.surname=surname;
	}
	public void setBirthday (final LocalDate birthday){
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
	public void setSex (final Boolean sex){
		this.sex=sex;
	}
	public Boolean getSex(){
		return this.sex;
	}

	public int getAnnata() {
		return LocalDate.now().getYear() - this.getBirthday().getYear();
	}

	public Period getHowIsHold() {
		return this.birthday.until(LocalDate.now()).normalized();
	
	}

	public Boolean isBirthday(){//da testare
		return (LocalDate.now().getDayOfYear()==(this.birthday.getDayOfYear()));
	}
}
