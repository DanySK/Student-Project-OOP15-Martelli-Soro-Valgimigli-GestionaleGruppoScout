package model;

import java.util.ArrayList;
import java.util.List;

import model.exception.ObjectAlreadyContainedException;
import model.exception.ObjectNotContainedException;

public class SpecialitaImpl implements Specialita {
	private String nome;
	final private List<Specialita> reqisiti;
	
	SpecialitaImpl (final String nome,final List<Specialita>requisiti){
		this.nome=nome;
		this.reqisiti=requisiti;
	}
	@Override
	public List<Specialita> getAllNeededSpecialita() {
		return this.reqisiti;
	}

	@Override
	public List<Specialita> getNeededSpecialita(final Member membro) {
		return this.getNeededSpecialita(membro.getSpecialities());
	}

	@Override
	public List<Specialita> getNeededSpecialita(final List<Specialita> list) {
		final List<Specialita>tmp=new ArrayList<>(this.reqisiti);
		list.forEach(e->{
			tmp.remove(e);
		});
		return tmp;
	}
	@Override
	public boolean isSpacialitaPossible(final Member membro) {
		return this.getNeededSpecialita(membro).isEmpty();
	}
	@Override
	public String getName() {
		return this.nome;
	}
	@Override
	public void setName(final String name) {
		this.nome=name;
		
	}
	@Override
	public void addNeededSpecialita(final Specialita specialita) throws ObjectAlreadyContainedException {
	if (!this.reqisiti.add(specialita)){
		throw new ObjectAlreadyContainedException();
	}
		
	}
	@Override
	public void removeNeededSpecialita(final Specialita specialita) throws ObjectNotContainedException {
	if (!this.reqisiti.remove(specialita)){
		throw new ObjectNotContainedException();
	}
	}

}
