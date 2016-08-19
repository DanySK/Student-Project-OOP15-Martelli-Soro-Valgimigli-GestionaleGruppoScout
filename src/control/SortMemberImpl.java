package control;

import java.util.Comparator;
import java.util.List;

import model.reparto.Member;

public class SortMemberImpl implements SortMember {

	private static final Comparator<Member> CMPBYNAME = (Member e1, Member e2) -> e1.getName().compareTo(e2.getName());

	private static final Comparator<Member> CMPBYAGE = (Member e1, Member e2) -> e1.getBirthday()
			.compareTo(e2.getBirthday());

	private static final Comparator<Member> CMPBYSURNAME = (Member e1, Member e2) -> e1.getSurname()
			.compareTo(e2.getSurname());

	private static final Comparator<Member> CMPBYNUMBOFSPEC = (Member e1,
			Member e2) -> e1.getSpecialities().size() > e2.getSpecialities().size() ? 1
					: e1.getSpecialities().size() < e2.getSpecialities().size() ? -1 : 0;
	private final SorterList sl = new SorterListImpl();

	@Override
	public List<Member> sortByName(final List<Member> members) {
		return this.sl.sortList(members, CMPBYNAME);
	}

	@Override
	public List<Member> sortByAge(final List<Member> members) {
		return this.sl.sortList(members, CMPBYAGE);
	}

	@Override
	public List<Member> sortBySurname(final List<Member> members) {
		return this.sl.sortList(members, CMPBYSURNAME);
	}

	@Override
	public List<Member> sortByNOfSpecialties(final List<Member> members) {
		return this.sl.sortList(members, CMPBYNUMBOFSPEC);
	}

}
