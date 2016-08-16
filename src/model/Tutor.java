package model;

import control.myUtil.MyOptional;

public interface Tutor {
	
	/**
	 * 
	 * @return myOptional<Sting> contained the name of the tutor
	 */
	MyOptional<String> getName();
	/**
	 * 
	 * @return myOptional<String> contained the email of the tutor
	 */
	MyOptional<String> getEmail();
	/**
	 * 
	 * @return  myOptional<Long> contained the phone of the tutor
	 */
	MyOptional<Long> getPhone();
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