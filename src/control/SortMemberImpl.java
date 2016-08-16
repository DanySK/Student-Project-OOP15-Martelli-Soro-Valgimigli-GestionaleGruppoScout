package control;

import java.util.Comparator;
import java.util.List;

import model.Member;

public class SortMemberImpl extends SorterListImpl implements SortMember {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8587887104818565221L;
	
	private static final Comparator<Member> CMPBYNAME = (Member e1, Member e2) ->
												e1.getName().compareTo(e2.getName());
												
	private static final Comparator<Member> CMPBYAGE = (Member e1, Member e2) ->
												e1.getBirthday().compareTo(e2.getBirthday());
												
	private static final Comparator<Member> CMPBYSURNAME = (Member e1, Member e2) ->
												e1.getSurname().compareTo(e2.getSurname());
												
	private static final Comparator<Member> CMPBYNUMBOFSPEC = (Member e1, Member e2) ->
												e1.getSpecialities().size() > e2.getSpecialities().size() ?
												1 :
												e1.getSpecialities().size() < e2.getSpecialities().size() ?
												-1 : 0;
	private static final Comparator<Member> CMPBYNUMBOFCOMP =  (Member e1, Member e2) ->
												e1.getCompetence().size() > e2.getCompetence().size() ?
												1 :
												e1.getCompetence().size() < e2.getCompetence().size() ?
												-1 : 0;
	@Override
	public List<Member> sortByName(final List<Member> members) {
		return this.sortList(members, CMPBYNAME);
	}

	@Override
	public List<Member> sortByAge(final List<Member> members) {
		return this.sortList(members, CMPBYAGE);
	}

	@Override
	public List<Member> sortBySurname(final List<Member> members) {
		return this.sortList(members, CMPBYSURNAME);
	}

	@Override
	public List<Member> sortByNOfSpecialties(final List<Member> members) {
		return this.sortList(members, CMPBYNUMBOFSPEC);
	}

	@Override
	public List<Member> sortByNOfCompetences(final List<Member> members) {
		return this.sortList(members, CMPBYNUMBOFCOMP);
	}

}
