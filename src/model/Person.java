package model;

import java.io.Serializable;
import java.time.LocalDate;

public class Person implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String surname;
	//private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	private LocalDate birthday;
	
	public Person(String name, String surname, LocalDate birthday){
		if (name==null || surname==null || birthday==null) throw new IllegalArgumentException();
		this.name = name;
		this.surname = surname;
		this.birthday = birthday;
	}
	
	public void setName (String name){
		if (name==null) throw new IllegalArgumentException();
		this.name=name;
	}
	public void setSurname (String surname){
		if (surname==null) throw new IllegalArgumentException();
		this.surname=surname;
	}
	public void setBirthday (LocalDate birthday){
		if (birthday==null) throw new IllegalArgumentException();
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
	public Boolean isBirthday(){//da testare
		return (LocalDate.now().getDayOfYear()==(this.birthday.getDayOfYear()));
	}
}
