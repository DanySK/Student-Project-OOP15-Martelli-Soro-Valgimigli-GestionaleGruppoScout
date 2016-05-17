package model;

import java.io.Serializable;
import java.time.LocalDate;

public class Person implements Serializable {
	private String name;
	private String surname;
	//private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	private LocalDate birthday;
	
	public Person(String name, String surname, LocalDate birthday){
		this.name = name;
		this.surname = surname;
		this.birthday = birthday;
	}
	
	public void setName (String name){
		this.name=name;
	}
	public void setSurname (String surname){
		this.surname=surname;
	}
	public void setBirthday (LocalDate birthday){
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
}
