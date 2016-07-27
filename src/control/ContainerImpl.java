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
	private List<Squadron> squadronActive;
	private List<Excursion> excursions;
	private List<Integer> code;
	
	public ContainerImpl(){
		this.unit = new ArrayList<>();
		this.squadronActive = new ArrayList<>();
		this.excursions = new ArrayList<>();
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
	public void addMember(Member m) throws EntityAlreadyExistsException {
		if(this.unit.contains(m)){
			throw new EntityAlreadyExistsException();
		}
		//m.setId(this.getCode());
		this.unit.add(m);
	}

	@Override
	public void addSquadron(Squadron sq) throws EntityAlreadyExistsException {
		if(this.squadronActive.contains(sq)){
			throw new EntityAlreadyExistsException();
		}
		this.squadronActive.add(sq);
	}
	
	private int getCode(){
		for(int i=0; i < Integer.MAX_VALUE; i++){
			if(! this.code.contains(i)){
				return i;
			}
		}
		return 0;
	}



	@Override
	public void removeMember(Member mbr) throws MemberNotExistException {
		if(!this.unit.contains(mbr)){
			throw new MemberNotExistException();
		}
		//this.code.remove(mbr.getId());
		this.squadronActive.stream().filter(e -> e.containMember(mbr))
									.forEach(e -> e.getMembri().remove(mbr));
		//eliminazione per le escursioni
		this.unit.remove(mbr);
	}



	@Override
	public void removeSquadron(Squadron sq) throws SquadronNotExistException {
		if(!this.squadronActive.contains(sq)){
			throw new SquadronNotExistException();
		}
		//manca la parte delle escursioni
		this.squadronActive.remove(sq);
	}
	
	@Override
	public List<Member> getFreeMember() {
		return this.unit.stream().filter(e -> {
			if(this.squadronActive.stream().filter(s -> s.containMember(e)).count() > 0){
				return true;
			}
			return false;
		}).collect(Collectors.toList());
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
