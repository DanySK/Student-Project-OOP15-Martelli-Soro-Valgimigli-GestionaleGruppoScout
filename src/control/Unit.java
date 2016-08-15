package control;

import java.time.LocalDate;
import java.util.List;

import control.myUtil.Pair;
import model.Excursion;
import model.Member;
import model.Reparto;
import model.Roles;
import model.Squadron;

public interface Unit {
	/**
	 * 
	 * @return
	 */
	public String getName();
	/**
	 * 
	 * @return
	 */
	
	public Container getContainers();
	/**
	 * 
	 * @param exc
	 */
	public void addExcursion(Excursion exc);
	/**
	 * 
	 * @param m
	 */
	public void addMember(Member m);
	/**
	 * 
	 * @param sq
	 */
	public void createSq(Squadron sq);
	/**
	 * 
	 * @param m
	 */
	public void removeMember(Member m);
	/**
	 * 
	 * @param sq
	 */
	public void removeSq(Squadron sq);
	/**
	 * 
	 * @param m
	 * @param sq
	 */
	public void putMemberInSq(Member m, Squadron sq, Roles rl);
	/**
	 * Method to change member from a squadron to other one
	 * @param m
	 * @param sqOld
	 * @param sqNew
	 */
	public void changeMemberFromSq(Member m, Squadron sqNew, Roles rl);
	/**
	 * 
	 * @param name
	 */
	public void setName(String name);
	/**
	 * Provides the info of unit
	 * @return
	 */
	public String info();
	/**
	 * Special method that provide a List of pair with the general info
	 * @return
	 * A list of array. In each cell there is a specifically information
	 * 0. Name
	 * 1. Number of member
	 * 2. Number of Squadron
	 * 3. Number of boys
	 * 4. Number of girls
	 */
	public List<Pair<String, String>> getUnitSpecificInfo();
	/**
	 * 
	 * @return
	 */
	public LocalDate getLimitDateToPay();
	/**
	 * 
	 * @return
	 */
	public List<Member> getMemberDidntPay();
	/**
	 * 
	 * @return
	 */
	public Reparto getReparto();
	/**
	 * 
	 * @param name
	 */
	public void removeExcursion(String name);
	/**
	 * 
	 * @param exc
	 */
	public void removeExcursion (Excursion exc);
	
}
