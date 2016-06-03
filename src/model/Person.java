package model;

import java.time.LocalDate;

public interface Person {
	public void setName (String name);
	public void setSurname (String surname);
	public void setBirthday (LocalDate birthday);
	public void setSex (Boolean sex);
	public Boolean getSex();
	public String getName ();
	public String getSurname ();
	public LocalDate getBirthday ();
	public Boolean isBirthday();
	
}
