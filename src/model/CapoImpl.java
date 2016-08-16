package model;

import java.time.LocalDate;

public class CapoImpl extends PersonImpl implements Capo {

	private static final long serialVersionUID = 1L;
	private String phoneNumber;

	public CapoImpl(final String name, final String surname, final LocalDate birthday,final  Boolean sex, final String phoneNumber) throws IllegalArgumentException,NumberFormatException {
		super(name, surname, birthday, sex);
		Long.parseLong(phoneNumber);
		if (phoneNumber.length()!=10){
			throw new IllegalArgumentException();
		}
		this.phoneNumber = phoneNumber;
	}

	@Override
	public void setPhoneNumber(final String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public String getPhoneNumber() {
		return this.phoneNumber;
	}

}
