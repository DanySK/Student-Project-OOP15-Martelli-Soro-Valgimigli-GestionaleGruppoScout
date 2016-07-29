package control;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import control.exception.EntityAlreadyExistsException;
import control.exception.MemberNotExistException;
import control.exception.SquadronNotExistException;
import model.Excursion;
import model.Member;
import model.Squadron;

public class ContainerImpl implements Container, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5561899153889400211L;
	private List<Member> unit;
	private List<Member> freeMember;
	private List<Squadron> squadronActive;
	private List<Excursion> excursions;
	
	public ContainerImpl(List<Squadron> sq, List<Member> freeMember, List<Excursion> exc){
		this.unit = freeMember;
		sq.forEach(e -> this.unit.addAll(e.getMembri().keySet()));
		this.squadronActive = sq;
		this.excursions = exc;
	}
	
	

	@Override
	public List<Member> findMember(String name) throws IllegalArgumentException {
		return this.unit.stream()
						.filter(e -> e.getName().equals(name))
						.collect(Collectors.toList());
	}

	@Override
	public List<Member> membersIncomplete() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Member> getMembers() {
		return new ArrayList<Member>(this.unit);
	}

	@Override
	public Squadron findSquadron(String name) {
		return (Squadron) this.squadronActive.stream()
											 .filter(e -> e.getNome()
											 .equals(name));
	}

	@Override
	public List<Member> members(Predicate<? super Member> p) {
		return this.unit.stream()
						.filter(p)
						.collect(Collectors.toList());
	}
	
	@Override
	public List<Squadron> getSquadrons() {
		return new ArrayList<Squadron>(this.squadronActive);
	}
	
	
	@Override
	public List<Member> getFreeMember() {
		return this.freeMember;
	}

	@Override
	public void removeMeberFromSquadron(Member member, Squadron sq)
			throws SquadronNotExistException, MemberNotExistException {
		if(!this.squadronActive.contains(sq)){
			throw new SquadronNotExistException();
		}
		if(!this.unit.contains(member) || ! sq.containMember(member)){
			throw new MemberNotExistException();
		}
		
		this.squadronActive.get(this.squadronActive.indexOf(sq)).removeMembro(member);
		
		
	}
	

	

	

	
	
}
