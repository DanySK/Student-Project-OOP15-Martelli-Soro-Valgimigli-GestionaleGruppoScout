package model;

import java.time.LocalDate;

import model.exception.IllegalPhoneNumberException;

public class CapoImpl extends PersonImpl implements Capo {

	private static final long serialVersionUID = 1L;
	private String phoneNumber;

	public CapoImpl(final String name, final String surname, final LocalDate birthday,final  Boolean sex, final String phoneNumber) throws IllegalPhoneNumberException  {
		super(name, surname, birthday, sex);
		try {
			Long.parseLong(phoneNumber);
		}catch(NumberFormatException E){
			throw new IllegalPhoneNumberException();
		}
		if (phoneNumber.length()!=10){
			throw new IllegalPhoneNumberException();
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
