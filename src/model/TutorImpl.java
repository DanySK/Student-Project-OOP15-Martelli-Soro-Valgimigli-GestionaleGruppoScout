package model;

import control.myUtil.myOptional;

public class TutorImpl implements Tutor {
	private myOptional<String> email;
	private myOptional<String> name;
	private myOptional<Long> phone;

	public TutorImpl(String email, String name, Long phone) {
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
	public void setName(String name){
		this.name=myOptional.of(name);
	}
	public void setEmail(String email){
		this.email=myOptional.of(email);
	}
	public void setPhone(Long phone){
		this.phone=myOptional.of(phone);
	}
	
}