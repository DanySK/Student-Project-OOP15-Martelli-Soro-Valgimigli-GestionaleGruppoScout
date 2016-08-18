package control;

import java.util.List;

import model.Member;

public interface SortMember{
	/**
	 * Sort the list according to the name
	 * @param members
	 * @return
	 */
	List<Member> sortByName(List<Member> members);
	/**
	 * Sort the list according to the Age
	 * @param members
	 * @return
	 */
	 List<Member> sortByAge(List<Member> members);
	/**
	 * Sort the list according to the Surname
	 * @param members
	 * @return
	 */
	 List<Member> sortBySurname(List<Member> members);
	/**
	 * Sort the list according to the Numbers of Specialties
	 * @param members
	 * @return
	 */
	 List<Member> sortByNOfSpecialties(List<Member> members);
}
