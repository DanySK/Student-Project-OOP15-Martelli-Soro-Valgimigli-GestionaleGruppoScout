package control;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import control.exception.EntityAlreadyExistsException;
import control.myUtil.myOptional;
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
		m.setId(this.getCode());
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
	public static void main(String[] s){
		Container cnt = new ContainerImpl();
		projectFactory pf = new projectFactoryimpl();
		LocalDate date;
		
		date = Year.of(1998).atMonth(Month.FEBRUARY).atDay(13);
		
		try {
			cnt.addMember(pf.getSimpleMember("Lorenzo", "Valgimigli", date, true));
			cnt.addMember(pf.getSimpleMember("Lorenzo", "Rossi", date, true));
			cnt.addMember(pf.getSimpleMember("Marco", "Cavani", date, true));
			cnt.addMember(pf.getSimpleMember("Giovanni", "Valgimigli", date, true));
			cnt.addMember(pf.getSimpleMember("Alessia", "Valgimigli", date, false));
			

			
			System.out.println(cnt.getMembers());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	

	

	
	
}
