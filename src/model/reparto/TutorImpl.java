package model.reparto;

import java.io.Serializable;

import control.myUtil.MyOptional;

public class TutorImpl implements Tutor, Serializable {

	private static final long serialVersionUID = 1L;
	private MyOptional<String> email;
	private MyOptional<String> name;
	private MyOptional<Long> phone;

	public TutorImpl(final String email, final String name, final Long phone) {
		this.email = MyOptional.ofNullable(email);
		this.name = MyOptional.ofNullable(name);
		this.phone = MyOptional.ofNullable(phone);
	}

	public TutorImpl() {
		this.email = MyOptional.empty();
		this.name = MyOptional.empty();
		this.phone = MyOptional.empty();
	}

	@Override
	public MyOptional<String> getName() {
		return this.name;
	}

	public MyOptional<String> getEmail() {
		return this.email;
	}

	@Override
	public MyOptional<Long> getPhone() {
		return this.phone;
	}

	@Override
	public void setName(final String name) {
		this.name = MyOptional.of(name);
	}

	@Override
	public void setEmail(final String email) {
		this.email = MyOptional.of(email);
	}

	@Override
	public void setPhone(final Long phone) {
		this.phone = MyOptional.of(phone);
	}

}