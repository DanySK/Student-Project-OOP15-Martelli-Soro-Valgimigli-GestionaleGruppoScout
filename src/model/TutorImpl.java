package model;

import java.io.Serializable;

import control.myUtil.myOptional;

public class TutorImpl implements Tutor,Serializable {

	private static final long serialVersionUID = 1L;
	private myOptional<String> email;
	private myOptional<String> name;
	private myOptional<Long> phone;

	public TutorImpl(final String email,final String name,final Long phone) {
		this.email=myOptional.ofNullable(email);
		this.name=myOptional.ofNullable (name);
		this.phone=myOptional.ofNullable(phone);
	}
	public TutorImpl (){
		this.email=myOptional.empty();
		this.name=myOptional.empty();
		this.phone=myOptional.empty();
	}
	public myOptional<String> getName(){
		return this.name;
	}
	public myOptional<String> getEmail(){
		return this.email;
	}
	public myOptional<Long> getPhone(){
		return this.phone;
	}
	public void setName(final String name){
		this.name=myOptional.of(name);
	}
	public void setEmail(final String email){
		this.email=myOptional.of(email);
	}
	public void setPhone(final Long phone){
		this.phone=myOptional.of(phone);
	}
	
}