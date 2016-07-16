package control;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Optional;

import control.myUtil.myOptional;
import model.*;
import view.general_utility.WarningNotice;

public class projectFactoryimpl implements projectFactory, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6423409525615896639L;
	@Override
	public Member getSimpleMember(String nome,String cognome,LocalDate dataNascita, boolean sex){
		Member prj_member = null;
		try{
			prj_member = new MemberImpl(nome, cognome, dataNascita, sex);
		}catch(Exception e){
			new WarningNotice(e.getMessage());
		}
		
		return prj_member;
	}
	@Override
	public Member getMember(String name, String surname, LocalDate birthday,
			myOptional<String> nameTutor, myOptional<String> mailTutor, myOptional<String> phoneTutor){
		Member prj_member = null;
		try{
			prj_member = new MemberImpl(name, surname, birthday,nameTutor, mailTutor,
					phoneTutor);
		}catch(Exception e){
			new WarningNotice(e.getMessage());
		}
		return prj_membro;
	}
	@Override
	public ExcursionImpl getGeneralExcursion(LocalDate dateStart, myOptional<LocalDate> dateEnd,
			myOptional<String> place) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Squadron getSquadron(String name, Boolean sex) {
		Squadron sq = null;
		try{
			sq = new SquadronImpl(name, sex);
		}catch(Exception e){
			new WarningNotice(e.getMessage());
		}
		return sq;
	}
}
