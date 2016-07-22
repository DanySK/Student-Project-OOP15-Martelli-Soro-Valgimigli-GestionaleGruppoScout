package model;

import control.myUtil.myOptional;

public interface Tutor {
	
	/**
	 * 
	 * @return myOptional<Sting> contained the name of the tutor
	 */
	public myOptional<String> getName();
	/**
	 * 
	 * @return myOptional<String> contained the email of the tutor
	 */
	public myOptional<String> getEmail();
	/**
	 * 
	 * @return  myOptional<Long> contained the phone of the tutor
	 */
	public myOptional<Long> getPhone();
	/**
	 * 
	 * @param name of the tutor
	 */
	public void setName(String name);
	/**
	 * 
	 * @param email of the tutor
	 */
	public void setEmail(String email);
	/**
	 * 
	 * @param phone of the tutor
	 */
	public void setPhone(Long phone);
	
}