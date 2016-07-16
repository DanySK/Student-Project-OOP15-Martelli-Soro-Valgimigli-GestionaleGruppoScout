package control;

import java.time.LocalDate;
import java.util.Optional;

import control.myUtil.myOptional;
import model.*;
import view.general_utility.WarningNotice;

public class projectFactoryimpl implements projectFactory {
	
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
	@Override
	public Squadron getSquadronWithBoss(String name, Boolean sex, myOptional<String> nameLeader,
			myOptional<String> nameSecond) {
		Squadron sq = null;
		try{
			sq = new SquadronImpl(name, sex);
			/*
			 * Manca la parte della ricerca e dell'inserimento
			 */
			sq.setCapoSq(capo);
			sq.setVicecapoSq(vicecapo);
		}catch(Exception e){
			new WarningNotice(e.getMessage());
		}
		return sq;
	}
}
