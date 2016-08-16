package model;

import control.myUtil.myOptional;

public interface Tutor {
	
	/**
	 * 
	 * @return myOptional<Sting> contained the name of the tutor
	 */
	myOptional<String> getName();
	/**
	 * 
	 * @return myOptional<String> contained the email of the tutor
	 */
	myOptional<String> getEmail();
	/**
	 * 
	 * @return  myOptional<Long> contained the phone of the tutor
	 */
	myOptional<Long> getPhone();
	/**
	 * 
	 * @param name of the tutor
	 */
	void setName(String name);
	/**
	 * 
	 * @param email of the tutor
	 */
	void setEmail(String email);
	/**
	 * 
	 * @param phone of the tutor
	 */
	void setPhone(Long phone);
	
}