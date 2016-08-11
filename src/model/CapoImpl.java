package model;

import java.time.LocalDate;

public class CapoImpl extends PersonImpl implements Capo {

	private static final long serialVersionUID = 1L;
	private String phoneNumber;

	public CapoImpl(String name, String surname, LocalDate birthday, Boolean sex, String phoneNumber) {
		super(name, surname, birthday, sex);
		Long.parseLong(phoneNumber);
		if (phoneNumber.length()!=10){
			throw new IllegalArgumentException();
		}
		this.phoneNumber = phoneNumber;
	}

	@Override
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public String getPhoneNumber() {
		return this.phoneNumber;
	}

}
