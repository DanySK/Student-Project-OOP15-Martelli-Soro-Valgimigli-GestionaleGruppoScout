package model;

import java.io.Serializable;

import control.myUtil.MyOptional;

public class TutorImpl implements Tutor,Serializable {

	private static final long serialVersionUID = 1L;
	private MyOptional<String> email;
	private MyOptional<String> name;
	private MyOptional<Long> phone;

	public TutorImpl(final String email,final String name,final Long phone) {
		this.email=MyOptional.ofNullable(email);
		this.name=MyOptional.ofNullable (name);
		this.phone=MyOptional.ofNullable(phone);
	}
	public TutorImpl (){
		this.email=MyOptional.empty();
		this.name=MyOptional.empty();
		this.phone=MyOptional.empty();
	}
	public MyOptional<String> getName(){
		return this.name;
	}
	public MyOptional<String> getEmail(){
		return this.email;
	}
	public MyOptional<Long> getPhone(){
		return this.phone;
	}
	public void setName(final String name){
		this.name=MyOptional.of(name);
	}
	public void setEmail(final String email){
		this.email=MyOptional.of(email);
	}
	public void setPhone(final Long phone){
		this.phone=MyOptional.of(phone);
	}
	
}