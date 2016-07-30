package model;

import java.util.ArrayList;
import java.util.List;

public class SpecialitaImpl implements Specialita {
	private String nome;
	private List<Specialita> reqisiti;
	
	SpecialitaImpl (String nome,List<Specialita>requisiti){
		this.nome=nome;
		this.reqisiti=requisiti;
	}
	@Override
	public List<Specialita> getAllNeededSpecialita() {
		return this.reqisiti;
	}

	@Override
	public List<Specialita> getNeededSpecialita(Member membro) {
		return this.getNeededSpecialita(membro.getSpecialities());
	}

	@Override
	public List<Specialita> getNeededSpecialita(List<Specialita> list) {
		List<Specialita>tmp=new ArrayList<>(this.reqisiti);
		list.forEach(e->{
			tmp.remove(e);
		});
		return tmp;
	}
	@Override
	public boolean isSpacialitaPossible(Member membro) {
		return this.getNeededSpecialita(membro).isEmpty();
	}
	@Override
	public String getName() {
		return this.nome;
	}
	@Override
	public void setName(String name) {
		this.nome=name;
		
	}
	@Override
	public void addNeededSpecialita(Specialita specialita) {
	this.reqisiti.add(specialita);
		
	}
	@Override
	public void removeNeededSpecialita(Specialita specialita) {
	this.reqisiti.remove(specialita);	
	}

}
